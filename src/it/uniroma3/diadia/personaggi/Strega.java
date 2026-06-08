package it.uniroma3.diadia.personaggi;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzioni;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import java.util.List;
public class Strega extends AbstractPersonaggio{
		private static final String MSG_EDUCATO = "Visto che hai mostrato buone maniere, ti teletrasporto in una stanza ricca di tesori... Hihihi!";
		private static final String MSG_MALEDUCATO = "Che scortesia! Per punizione, ti spedisco nel luogo più desolato di questo labirinto!";
		private static final String MSG_REGALO = "Ahahah! Sei proprio uno sciocco, questo lo tengo io per le mie pozioni!";
		private static final String MSG_NESSUNA_USCITA = "Mmm... sei fortunato, questa stanza non ha uscite. Rimaniamo qui.";

		public Strega(String nome, String presentazione) {
			super(nome, presentazione);
		}
		
		@Override
		public String agisci(Partita partita) {
			Stanza stanzaCorrente = partita.getStanzaCorrente();
			List<Direzioni> direzioni = stanzaCorrente.getDirezioni();
			
			if (direzioni == null || direzioni.isEmpty()) {
				return MSG_NESSUNA_USCITA;
			}

			Stanza stanzaDestinazione = null;
			boolean haSalutato = this.haSalutato();
			

			for (Direzioni direzione : direzioni) {
				Stanza stanzaAdiacente = stanzaCorrente.getStanzaAdiacente(direzione);
				if (stanzaDestinazione == null) {
					stanzaDestinazione = stanzaAdiacente;
					continue;
				}
				
				int numeroAttrezziAdiacente = stanzaAdiacente.getAttrezzi().size();
				int numeroAttrezziDestinazione = stanzaDestinazione.getAttrezzi().size();
				if (haSalutato) {
					if (numeroAttrezziAdiacente > numeroAttrezziDestinazione) {
						stanzaDestinazione = stanzaAdiacente;
					}
				} else {
					if (numeroAttrezziAdiacente < numeroAttrezziDestinazione) {
						stanzaDestinazione = stanzaAdiacente;
					}
				}
			}
			
			partita.setStanzaCorrente(stanzaDestinazione);
			return haSalutato ? MSG_EDUCATO : MSG_MALEDUCATO;
		}
		
		@Override
		public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
			return MSG_REGALO;
		}
}
