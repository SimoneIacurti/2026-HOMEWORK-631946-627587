package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;

import it.uniroma3.diadia.Partita;

public class ComandoFine implements Comando {
	private IO io;
	@Override
	public void esegui(Partita partita) {
		io.mostraMessaggio("Grazie di aver giocato!");
	}

	@Override
	public void setParametro(String parametro) {
	}
	
	public String getNome() {
		return "fine";
	}
	
	public String getParametro() {
		return null;
	}
	
	@Override
	public void setIo(IO io) {
		this.io = io;
	}
}
