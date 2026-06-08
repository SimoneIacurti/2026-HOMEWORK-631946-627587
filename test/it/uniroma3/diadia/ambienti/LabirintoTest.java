package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;

class LabirintoTest {

	private Labirinto labirinto;
	private Partita partita;
	private IO io;
	@BeforeEach
	void setUp() throws Exception {
		labirinto=Labirinto.creaLabirintoDefault();
		io=new IOSimulator(new ArrayList<String>());
		partita=new Partita(io);
	}
	
	@Test
	void testStanzaCorrenteAtrio() {
		assertEquals("Atrio",partita.getStanzaCorrente().getNome());
	}
	
    @Test
    void testCambioStanzaCorrenteNull() {
    	partita.setStanzaCorrente(null);
    	assertNull(partita.getStanzaCorrente());
    }
    
    @Test
    void testVerificoStanzaVincenteBiblioteca() {
    	assertEquals("Biblioteca",labirinto.getStanzaVincente().getNome());
    }
}
