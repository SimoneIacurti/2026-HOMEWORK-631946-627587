package it.uniroma3.diadia;

import java.util.ArrayList;

import java.util.List;

public class IOSimulator implements IO {

    private List<String> righeLette;
    private int indiceRigheLette;
    
    private List<String> messaggiProdotti;
    private int indiceMessaggiMostrati;

    public List<String> getMessaggiProdotti() {
        return messaggiProdotti;
    }

    public void setMessaggiProdotti(List<String> messaggiProdotti) {
        this.messaggiProdotti = messaggiProdotti;
    }

    public IOSimulator(List<String> righeDaLeggere) {
        this.righeLette = righeDaLeggere;
        this.indiceRigheLette = 0;
        this.indiceMessaggiMostrati = 0;
        this.messaggiProdotti = new ArrayList<>(); 
    }

    @Override
    public String leggiRiga() {
        String riga = null;
        riga = this.righeLette.get(indiceRigheLette);
        this.indiceRigheLette++;
        return riga;
    }

    @Override
    public void mostraMessaggio(String msg) {
        this.messaggiProdotti.add(msg);
    }

    public String nextMessaggio() {
        String next = this.messaggiProdotti.get(this.indiceMessaggiMostrati);
        this.indiceMessaggiMostrati++;
        return next;
    }

    public boolean hasNextMessaggio() {
        return this.indiceMessaggiMostrati < this.messaggiProdotti.size();
    }
}