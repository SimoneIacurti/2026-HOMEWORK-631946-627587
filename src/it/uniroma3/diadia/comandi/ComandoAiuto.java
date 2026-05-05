package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;

import it.uniroma3.diadia.Partita;

public class ComandoAiuto implements Comando{
	static final private String[] elencoComandi = {"vai", "aiuto", "fine","prendi","posa","guarda"};
	private IO io;
	public void esegui(Partita partita) {
		for(int i=0; i< elencoComandi.length; i++) 
			io.mostraMessaggio(elencoComandi[i]+" ");
		io.mostraMessaggio(" ");
	}
	
	public void setParametro(String parametro) {
	}
	
	public String getNome() {
		return "aiuto";
	}
	
	public String getParametro() {
		return null;
	}
	
	@Override
	public void setIo(IO io) {
		this.io = io;
	}
}
