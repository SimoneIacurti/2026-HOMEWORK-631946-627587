package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

class LabirintoTest {

	@Test
	public void FunzionamentoStanzaInizialeAtrio() {
		Labirinto l=new Labirinto();
		assertEquals(l.getStanzaIniziale().getNome(), "Atrio");
	}
	
	@Test
	public void FunzionamentoStanzaVincenteBiblioteca() {
		Labirinto l=new Labirinto();
		assertEquals(l.getStanzaVincente().getNome(), "Biblioteca");
	}
}
