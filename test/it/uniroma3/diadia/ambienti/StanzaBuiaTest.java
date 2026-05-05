package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBuiaTest {
	
	private StanzaBuia stanza;
	private Attrezzo Lanterna;
	@BeforeEach
	public void setUp(){
		stanza = new StanzaBuia("StanzaBuia", "Lanterna");
		Lanterna = new Attrezzo("Lanterna", 1);
	}

	@Test
	public void testGetDescrizioneConLanterna() {
		stanza.addAttrezzo(Lanterna);
		assertEquals(stanza.toString(), stanza.getDescrizione());
	}
	
	@Test
	public void testGetDescrizioneSenzaLanterna() {
		String e = "qui c'è un buio pesto";
		assertEquals(e, stanza.getDescrizione());
	}

}
