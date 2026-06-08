package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoRegala extends AbstractComando{

	private String parametro;
	
	@Override
	public void esegui(Partita partita) {
		if(partita.getGiocatore().getBorsa().hasAttrezzo(parametro))
			partita.getStanzaCorrente().getPersonaggio().riceviRegalo(partita.getGiocatore().getBorsa().removeAttrezzo(parametro), partita);
		else 
			partita.getIo().mostraMessaggio("non hai questo oggetto");
	}
	
	
	@Override
	public void setParametro(String s) {
		parametro=s;
	}
	@Override
	public String getParametro() {
		return parametro;
	}
	@Override
	public String getNome() {
		return "regala";
	}
	
	@Override
    public void setIo(IO io) {
        this.io = io;
    }
}
