package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

import org.junit.jupiter.api.BeforeEach;
class StanzaTest {
	
	private Stanza stanza;
	private Attrezzo attrezzo1;
	private Attrezzo attrezzo2;
	private Attrezzo attrezzo3;
	
	@BeforeEach
	public void setUp() {
		 stanza = new Stanza("stanza"); 
	     attrezzo1 = new Attrezzo("attrezzo1", 1);
	     attrezzo2 = new Attrezzo("attrezzo2", 2);
	     attrezzo3 = new Attrezzo("attrezzo3", 3);
	}

	@Test
	void testStanzeVuoteNoAttrezzi() {
		assertFalse(this.stanza.hasAttrezzo("martello"));
	}
	
	@Test
	void testStanzaUnSoloAttrezzo() {
		assertFalse(this.stanza.hasAttrezzo("attrezzo1"));
		this.stanza.addAttrezzo(attrezzo1);
		assertTrue(this.stanza.hasAttrezzo("attrezzo1"));
	}
	
	@Test
	void testOggettoEsisteInStanzaPiena() {
		for(int i=0; i<10; i++) {
			stanza.addAttrezzo(new Attrezzo("attrezzo" + i, i));
		}
		assertTrue(this.stanza.hasAttrezzo("attrezzo1"));
	}
	
	@Test
	void aggiungiAttrezzoNull() {
		assertFalse(stanza.addAttrezzo(null));
	}
	
	@Test
	void aggiungiUnSoloAttrezzo() {
		assertTrue(stanza.addAttrezzo(attrezzo1));
	}
	
	@Test
	void aggiungiOggettoConStanzaPiena() {
		for(int i=0; i<10; i++) {
			stanza.addAttrezzo(new Attrezzo("attrezzo" + i, i));
		}
		assertFalse(this.stanza.addAttrezzo(attrezzo2));
    }
	
	@Test
	void ottieniOggettoNonEsistente() {
		assertNull(this.stanza.getAttrezzo("martello"));
	}
	
	@Test
	void ottieniOggettoDaStanzaConUnSoloOggetto() {
		this.stanza.addAttrezzo(attrezzo3);
		assertNotNull(this.stanza.getAttrezzo("attrezzo3"));
	}
	
	@Test
	void ottieniOggettoDaStanzaPiena() {
		for(int i=0; i<10; i++) {
			stanza.addAttrezzo(new Attrezzo("attrezzo" + i, i));
		}
		assertNotNull(this.stanza.getAttrezzo("attrezzo1"));
	}
	
	@Test
	void ottieniStanzaAdiacenteNulla() {
		assertNull(this.stanza.getStanzaAdiacente(null));
	}
	
	@Test
	void ottieniStanzaAdiacenteNonEsistente() {
		Stanza Stanza1=new Stanza("Stanza1");
		this.stanza.impostaStanzaAdiacente("sud", Stanza1);
		assertNull(this.stanza.getStanzaAdiacente("nord"));
	}
	
	@Test
	void ottieniUnaStanzaAdiacente() {
		Stanza Stanza1=new Stanza("Stanza1");
		this.stanza.impostaStanzaAdiacente("nord", Stanza1);
		assertNotNull(this.stanza.getStanzaAdiacente("nord"));
	}
}
