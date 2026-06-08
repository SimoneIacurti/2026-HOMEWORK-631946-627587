package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {

	
	private Stanza stanzaCorrente;
    private Labirinto labirinto;
	private boolean finita;
	private Giocatore giocatore;
	private IO io;
	
	public Partita(IO console){
		this.finita = false;
		this.labirinto=Labirinto.creaLabirintoDefault();
		this.stanzaCorrente=labirinto.getStanzaIniziale();
		this.giocatore=new Giocatore();
		this.giocatore.setCfu(Proprieta.getCFUiniziali());
		this.io=console;
	}
	
	public Partita(Labirinto labirinto, IO console){
		this.finita = false;
		this.labirinto=labirinto;
		this.stanzaCorrente=this.labirinto.getStanzaIniziale();
		this.giocatore=new Giocatore();
		this.giocatore.setCfu(Proprieta.getCFUiniziali());
		this.io=console;
	}


	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}

	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}
	
	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.getStanzaCorrente().equals(this.labirinto.getStanzaVincente());
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || (this.giocatore.getCfu() == 0);
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}
	
	public Giocatore getGiocatore() {
        return this.giocatore;
    }
	
	public Labirinto getLabirinto() {
		return this.labirinto;
	}
	
	public boolean giocatoreIsVivo() {
		return this.giocatore.getCfu()>0;
	}
	
	public void setLabirinto(Labirinto labirinto) {
		this.labirinto=labirinto;
		this.stanzaCorrente=labirinto.getStanzaIniziale();
	}
	
	  public IO getIo() {
	    	return this.io;
	    }
	
}
