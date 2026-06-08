package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzioni;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai extends AbstractComando {
    private Direzioni direzione;
    private IO io;

    @Override
    public void esegui(Partita partita) {
        Stanza stanzaCorrente = partita.getStanzaCorrente();
        Stanza prossimaStanza = null;
        if(direzione == null) {
            io.mostraMessaggio("Dove vuoi andare?, specifica una direzione");
            return;
        }
        prossimaStanza = stanzaCorrente.getStanzaAdiacente(this.direzione);
        if(prossimaStanza == null) {
            io.mostraMessaggio("Direzione inesistente");
            return;
        }
        partita.setStanzaCorrente(prossimaStanza);
        io.mostraMessaggio(partita.getStanzaCorrente().getNome());
        partita.getGiocatore().setCfu(partita.getGiocatore().getCfu() - 1);
    }

    @Override
    public void setParametro(String parametro) {
        try {
            this.direzione = Direzioni.valueOf(parametro.toUpperCase().replace("-", "_"));
        } catch (IllegalArgumentException | NullPointerException e) {
            this.direzione = null;
        }
    }

    @Override
    public String getNome() {
        return "vai";
    }

    @Override
    public String getParametro() {
        return this.direzione != null ? this.direzione.name() : null;
    }

    @Override
    public void setIo(IO io) {
        this.io = io;
    }
   
}
