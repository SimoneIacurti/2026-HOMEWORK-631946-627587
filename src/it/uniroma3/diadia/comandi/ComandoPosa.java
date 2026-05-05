package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa implements Comando {
	private String nomeAttrezzo;
	private IO io;
	@Override
	public void esegui(Partita partita) {
		if(nomeAttrezzo==null) {
			io.mostraMessaggio("Quale oggetto vuoi posare?");
			return;
		} else {
			Attrezzo attrezzo=partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
			if(attrezzo==null) {
				io.mostraMessaggio("Oggetto inesistente, errore");
				return;
			} else {
				if(partita.getStanzaCorrente().addAttrezzo(attrezzo)) {
					partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
					io.mostraMessaggio("Oggetto posato nella stanza");
				} else {
					io.mostraMessaggio("Impossibile posare l'oggetto, stanza piena");
				}
			}
		}
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo=parametro;
	}
	
	@Override
	public void setIo(IO io) {
		this.io = io;
	}
	
	public String getNome() {
		return "posa";
	}
	
	public String getParametro() {
		return nomeAttrezzo;
	}

}
