package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;





import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;

class ComandoVaiTest {
	
	private ComandoVai vai;
	private Partita p;
	private IO io;
	private Labirinto labirinto;

	@BeforeEach
	void setUp() throws Exception {
		vai = new ComandoVai();
		p = new Partita(io);
		vai.setIo(new IOConsole());
	}

	@Test
	void testSetParametro() {
		vai.setParametro("nord");
		vai.esegui(p);
		assertEquals("Biblioteca",this.p.getStanzaCorrente().getNome());
		assertEquals(19,this.p.getGiocatore().getCfu());	
	}
	@Test
	void testDirezioneInesistente() {
		vai.setParametro("sest");
		vai.esegui(p);
		assertEquals("Atrio",this.p.getStanzaCorrente().getNome());
		assertEquals(20,this.p.getGiocatore().getCfu());	
	}
	@Test
	void testSetParametroNull() {
		vai.setParametro(null);
		vai.esegui(p);
		assertEquals("Atrio",this.p.getStanzaCorrente().getNome());
		assertEquals(20,this.p.getGiocatore().getCfu());	
	}
	
	@Test
	void testBilocale() {
		labirinto=Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.addAdiacenza("Atrio", "Bagno", "est")
				.getLabirinto();
		p=new Partita(labirinto,io);
		vai.setParametro("nord");
		vai.esegui(p);
		vai.setParametro("ovest");
		vai.esegui(p);
		vai.setParametro("sud");
		vai.esegui(p);
		vai.setParametro("est");
		vai.esegui(p);
		assertEquals(p.getStanzaCorrente().getNome(),"Bagno");
		
	}

}
