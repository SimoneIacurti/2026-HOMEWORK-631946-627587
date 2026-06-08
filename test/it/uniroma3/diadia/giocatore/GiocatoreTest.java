package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

class GiocatoreTest {
	
	Partita p;
	Giocatore g;
	private IO io;
	
	@Test
	public void PresiCfuIniziali() {
		this.p=new Partita(io);
		this.g=this.p.getGiocatore();
		assertEquals(this.g.getCfu(),20);
	}
	
	@Test
	public void PresiCfuGenerici() {
		this.g=new Giocatore();
		this.g.setCfu(3);
		assertEquals(this.g.getCfu(),3);
	}
	
	@Test
	public void GenerataBorsaGenerica() {
		this.g=new Giocatore();
		assertNotNull(this.g.getBorsa());
	}
	
	@Test
	public void OttenutaBorsaGenerica() {
		this.g=new Giocatore();
		Borsa b=new Borsa();
		this.g.setBorsa(b);
		assertNotNull(this.g.getBorsa());
	}
}
