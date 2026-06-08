package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaTest {
	
private  Stanza biblioteca;
private Stanza laboratorio;
private Stanza atrio;
private Stanza ovest;
private Attrezzo martello;
private Attrezzo osso;
	@BeforeEach
	void setUp() throws Exception {
		biblioteca= new Stanza("biblioteca");
		laboratorio= new Stanza("laboratorio");
		atrio=new Stanza("atrio");
		ovest=new Stanza("ovest");
		martello=new Attrezzo("martello",6);
		osso=new Attrezzo("osso",2);
	}

	@Test
	void testImpostaStanzaAdiacente() {
	    atrio.impostaStanzaAdiacente(Direzioni.NORD, biblioteca);
	    assertEquals(biblioteca, atrio.getStanzaAdiacente(Direzioni.NORD));
	}

	@Test
	void testImpostaStanzaAdiacenteNull() {
	    assertNull(atrio.getStanzaAdiacente(Direzioni.NORD));
	}

	@Test
	void testAggiornaStanzaAdiacente() {
	    atrio.impostaStanzaAdiacente(Direzioni.NORD, biblioteca);
	    atrio.impostaStanzaAdiacente(Direzioni.NORD, laboratorio);
	    assertEquals(laboratorio, atrio.getStanzaAdiacente(Direzioni.NORD));
	}

	@Test
	void testAddAttrezzoAggiungoUnAttrezzo() {
	    assertTrue(atrio.addAttrezzo(martello));
	}

	@Test
	void testAddAttrezzoAggiungoDueAttrezzi() {
	    atrio.addAttrezzo(martello);
	    atrio.addAttrezzo(osso);
	    assertTrue(atrio.hasAttrezzo("martello"));
	    assertTrue(atrio.hasAttrezzo("osso"));
	}

	@Test
	void testGetAttrezziStanzaVuota() {
	    assertTrue(atrio.getAttrezzi().isEmpty());
	}

	@Test
	void testGetAttrezziStanzaConUnAttrezzo() {
	    atrio.addAttrezzo(martello);
	    assertEquals(martello, atrio.getAttrezzi().get(0));
	}

	@Test
	void testHasAttrezzoStanzaVuota() {
	    assertFalse(atrio.hasAttrezzo("martello"));
	}

	@Test
	void testStanzaHasUnAttrezzo() {
	    atrio.addAttrezzo(martello);
	    assertTrue(atrio.hasAttrezzo("martello"));
	}

	@Test
	void testGetAttrezzoStanzaVuota() {
	    assertNull(atrio.getAttrezzo("martello"));
	}

	@Test
	void testGetAttrezzoStanzaConOggetto() {
	    atrio.addAttrezzo(martello);
	    assertEquals(martello, atrio.getAttrezzo("martello"));
	}

	@Test
	void testGetAttrezzoStanzaSenzaOggetto() {
	    atrio.addAttrezzo(martello);
	    assertNull(atrio.getAttrezzo("osso"));
	}

	@Test
	void testRemoveOggettoDaStanzaVuota() {
	    assertFalse(atrio.removeAttrezzo(martello));
	}

	@Test
	void testRemoveAttrezzoDaStanzaConUnOggetto() {
	    atrio.addAttrezzo(martello);
	    assertTrue(atrio.removeAttrezzo(martello));
	}

	@Test
	void testRemoveAttrezzoStanzaSenzaOggetto() {
	    atrio.addAttrezzo(martello);
	    assertFalse(atrio.removeAttrezzo(osso));
	}

	@Test
	void testSenzaDirezioni() {
	    assertEquals(0, atrio.getDirezioni().size());
	}

	@Test
	void testUnaDirezione() {
	    atrio.impostaStanzaAdiacente(Direzioni.SUD, laboratorio);
	    assertTrue(atrio.getDirezioni().contains(Direzioni.SUD));
	}

	@Test
	void testTuttaTranneUnaDirezione() {
	    atrio.impostaStanzaAdiacente(Direzioni.SUD, laboratorio);
	    atrio.impostaStanzaAdiacente(Direzioni.EST, biblioteca);
	    atrio.impostaStanzaAdiacente(Direzioni.OVEST, ovest);
	    assertFalse(atrio.getDirezioni().contains(Direzioni.NORD)); 
	    
	}
}
