package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

class PartitaTest {
	
	private Partita p;
	private Labirinto l;
	private Giocatore g;
	private IO io;

	@BeforeEach
	void setUp() {
		p=new Partita(io);
		l=p.getLabirinto();
		g=p.getGiocatore();
	}

	@Test
	void NonFinitaAllInizio() {
		assertFalse(this.p.isFinita());
	}
	
	@Test
	
	void FinitaQuandoCfuZero() {
		g.setCfu(0);
		assertTrue(this.p.isFinita());
	}
	
	@Test
	void FinitaQuandoPartitaVinta() {
		Stanza stanzavincente=l.getStanzaVincente();
		p.setStanzaCorrente(stanzavincente);
		assertTrue(this.p.isFinita());
	}
	
	@Test
	void getStanzaCorrenteNulla() {
		this.p.setStanzaCorrente(null);
		assertNull(this.p.getStanzaCorrente());
	}
	
	@Test
	void getStanzaCorrenteInizialeCheSiaStessaInizialeLabirinto() {
		assertTrue(this.p.getStanzaCorrente().equals(this.l.getStanzaIniziale()));
	}
	
	@Test
	void getStanzaCorrenteDopoAverCambiatoStanza() {
		Stanza stanza1=new Stanza("stanza1");
		this.p.setStanzaCorrente(stanza1);
		assertTrue(this.p.getStanzaCorrente().equals(stanza1));
	}
	
	@Test
	void funzionamentoGetGiocatore() {
		assertEquals(this.p.getGiocatore(), g);
	}
	
	@Test
	void funzionamentoGetLabirinto() {
		assertEquals(this.p.getLabirinto(),l);
	}
	
	@Test
	void VintaNonDopoInizio() {
		assertFalse(this.p.vinta());
	}
	
	@Test
	void VintaSeInStanzaVincente() {
		Stanza stanza1=this.l.getStanzaVincente();
		this.p.setStanzaCorrente(stanza1);
		assertTrue(this.p.vinta());
	}
	
	@Test
	void VintaSeNonInStanzaVincente() {
		Stanza stanza1=new Stanza("stanza1");
		this.p.setStanzaCorrente(stanza1);
		assertFalse(this.p.vinta());
	}
}
