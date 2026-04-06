package it.uniroma3.diadia;







import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine","prendi","posa"};

	private Partita partita;
	private IOConsole IO;

	public DiaDia() {
		this.partita = new Partita();
		this.IO=new IOConsole();
	}

	public void gioca() {
		String istruzione; 

		this.IO.mostraMessaggio(MESSAGGIO_BENVENUTO);		
		do		
			istruzione = this.IO.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);
		if(comandoDaEseguire.getNome()==null) {
			this.IO.mostraMessaggio("Errore comando non inserito, riprova con uno valido");
			return false;
		}
		
		if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine(); 
			return true;
		} else if (comandoDaEseguire.getNome().equals("vai"))
			this.vai(comandoDaEseguire.getParametro());
		else if(comandoDaEseguire.getNome().equals("prendi"))
			this.prendi(comandoDaEseguire.getParametro());
		else if(comandoDaEseguire.getNome().equals("posa"))
			this.posa(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto();
		else
			this.IO.mostraMessaggio("Comando sconosciuto");
		if (this.partita.vinta()) {
			this.IO.mostraMessaggio("Hai vinto!");
			return true;
		} else
			return false;
	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			this.IO.mostraMessaggio(elencoComandi[i]+" ");
		this.IO.mostraMessaggio(" ");
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null)
			this.IO.mostraMessaggio("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			this.IO.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getGiocatore().getCfu();
			this.partita.getGiocatore().setCfu(cfu--);
		}
		this.IO.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		this.IO.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}
	
	public void prendi(String nomeAttrezzo) {
		if(nomeAttrezzo==null) {
			this.IO.mostraMessaggio("Quale oggetto vuoi prendere?");
			return;
		} else {
			Attrezzo attrezzo=this.partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
			if(attrezzo==null) {
				this.IO.mostraMessaggio("Oggetto inesistente, errore");
				return;
			}
			if(this.partita.getGiocatore().getBorsa().addAttrezzo(attrezzo)==true) {
				this.partita.getStanzaCorrente().removeAttrezzo(attrezzo);
				this.IO.mostraMessaggio("Oggetto rimosso e messo in borsa");
			} else {
				this.IO.mostraMessaggio("Impossibile aggiungere l'oggetto in borsa");
			}
		}
		
	}
	
	public void posa(String nomeAttrezzo) {
		if(nomeAttrezzo==null) {
			this.IO.mostraMessaggio("Quale oggetto vuoi posare?");
			return;
		} else {
			Attrezzo attrezzo=this.partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
			if(attrezzo==null) {
				this.IO.mostraMessaggio("Oggetto inesistente, errore");
				return;
			} else {
				if(this.partita.getStanzaCorrente().addAttrezzo(attrezzo)) {
					this.partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
					this.IO.mostraMessaggio("Oggetto posato nella stanza");
				} else {
					this.IO.mostraMessaggio("Impossibile posare l'oggetto, stanza piena");
				}
			}
		}
	}

	public static void main(String[] argc) {
		DiaDia gioco = new DiaDia();
		gioco.gioca();
	}
}