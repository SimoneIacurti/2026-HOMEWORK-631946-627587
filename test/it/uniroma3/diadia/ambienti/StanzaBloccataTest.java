package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBloccataTest {
	
    private StanzaBloccata stanza;
    private Stanza adiacenteBloccata;
    private Attrezzo grimaldello;
    private Direzioni direzioneBloccata;
    private String unlock;

    @BeforeEach
    void setUp() throws Exception {
        direzioneBloccata = Direzioni.OVEST;
        unlock = "grimaldello";
        
        stanza = new StanzaBloccata("StanzaBloccata", direzioneBloccata, unlock);
        adiacenteBloccata = new Stanza("Stanzetta");
        grimaldello = new Attrezzo(unlock, 1);
        
        stanza.impostaStanzaAdiacente(direzioneBloccata, adiacenteBloccata);
    }

    @Test
    void testGetStanzaAdiacenteDirezioneBloccata() {
        assertEquals(stanza, stanza.getStanzaAdiacente(direzioneBloccata));
    }

    @Test
    void testGetStanzaAdiacenteDirezioneSbloccata() {
        stanza.addAttrezzo(grimaldello);
        assertEquals(adiacenteBloccata, stanza.getStanzaAdiacente(direzioneBloccata));
    }

    @Test
    void testGetDescrizioneDirezioneSbloccata() {
        stanza.addAttrezzo(grimaldello);
        assertEquals(stanza.toString(), stanza.getDescrizione());
    }

    @Test
    void testGetDescrizioneDirezioneBloccata() {
        String avviso = "\nStanza bloccata nella direzione: " + direzioneBloccata + "\nTi serve l'attrezzo: " + unlock;
        assertEquals(stanza.toString() + avviso, stanza.getDescrizione());
    }
}
