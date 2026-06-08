package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends AbstractComando {

    @Override
    public void esegui(Partita partita) {
    	for(String elencoComandi : AbstractComando.comandi) 
    		partita.getIo().mostraMessaggio(elencoComandi+" ");
    	}

    @Override
    public String getNome() {
        return "aiuto";
    }

    public void setIo(IO io) {
        this.io = io;
    }
}