package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi extends AbstractComando {
    private String nomeAttrezzo;
    private IO io;

    @Override
    public void esegui(Partita partita) {
        if(nomeAttrezzo == null) {
            io.mostraMessaggio("Quale oggetto vuoi prendere?");
            return;
        } else {
            Attrezzo attrezzo = partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
            if(attrezzo == null) {
                io.mostraMessaggio("Oggetto inesistente, errore");
                return;
            }
            if(partita.getGiocatore().getBorsa().addAttrezzo(attrezzo) == true) {
                partita.getStanzaCorrente().removeAttrezzo(attrezzo);
                io.mostraMessaggio("Oggetto rimosso e messo in borsa");
            } else {
                io.mostraMessaggio("Impossibile aggiungere l'oggetto in borsa");
            }
        }
    }

    @Override
    public void setParametro(String parametro) {
        this.nomeAttrezzo = parametro;
    }

    @Override
    public String getNome() {
        return "prendi";
    }

    @Override
    public String getParametro() {
        return this.nomeAttrezzo;
    }

    @Override
    public void setIo(IO io) {
        this.io = io;
    }
}
