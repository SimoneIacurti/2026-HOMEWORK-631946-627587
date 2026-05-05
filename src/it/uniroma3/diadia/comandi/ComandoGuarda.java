package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;

import it.uniroma3.diadia.Partita;

public class ComandoGuarda implements Comando {
	private IO io;
	@Override
	public void esegui(Partita partita) {
		io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		io.mostraMessaggio("Hai ancora: "+partita.getGiocatore().getCfu()+ "CFU");
		io.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
	}

	@Override
	public void setParametro(String parametro) {
		// TODO Auto-generated method stub

	}
	
	public String getNome() {
		return "guarda";
	}
	
	public String getParametro() {
		return null;
	}
	
	@Override
	public void setIo(IO io) {
		this.io = io;
	}

}
