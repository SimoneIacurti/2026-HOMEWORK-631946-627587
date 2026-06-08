package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.Proprieta;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio{
		private static final String MESSAGGIO_MORSO = "il cane ha morso, perdi CFU";
		private static final String MESSAGGIO_RICOMPENSA = "tieni un elmo";
		
		private final String ciboPreferito;
		
		public Cane(String nome, String presentazione) {
			super(nome, presentazione);
			this.ciboPreferito = Proprieta.getCiboPreferito();
		}
		
		@Override
		public String agisci(Partita partita) {
			int cfuAttuali = partita.getGiocatore().getCfu();
			partita.getGiocatore().setCfu(cfuAttuali - 1);
			
			return MESSAGGIO_MORSO;
		}
		
		@Override
		public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
			if (!attrezzo.getNome().equalsIgnoreCase(this.ciboPreferito)) {
				return this.agisci(partita);
			}
			Attrezzo elmo = new Attrezzo("elmo", 10);
			partita.getLabirinto().addAttrezzoLabirinto(partita.getStanzaCorrente(), elmo);
			
			return MESSAGGIO_RICOMPENSA;
		}
}
