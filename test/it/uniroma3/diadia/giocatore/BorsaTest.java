package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class BorsaTest {
	
	Borsa b;
	Attrezzo attrezzo1;
	Attrezzo attrezzo2;
	Attrezzo attrezzo3;
	Attrezzo attrezzo4;

	@BeforeEach
	void setUp(){
		b=new Borsa();
		attrezzo1=new Attrezzo("attrezzo1",5);
		attrezzo2=new Attrezzo("attrezzo2",5);
		attrezzo3=new Attrezzo("attrezzo3",10);
		attrezzo4=new Attrezzo("attrezzo4",12);
	}

	@Test
	void BorsaSenzaAttrezzi() {
		assertFalse(this.b.hasAttrezzo("martello"));
	}
	
	@Test
	void BorsaConUnAttrezzoLoTrova() {
		this.b.addAttrezzo(attrezzo1);
		assertTrue(this.b.hasAttrezzo("attrezzo1"));
	}
	
	@Test
	void BorsaPienaTrovaAttrezzo() {
		this.b.addAttrezzo(attrezzo1);
		this.b.addAttrezzo(attrezzo2);
		assertTrue(this.b.hasAttrezzo("attrezzo2"));
	}
	
	@Test
	void AggiuntaDiUnOggettoNullo() {
		assertFalse(this.b.addAttrezzo(null));
	}
	
	@Test
	void AggiuntaDiUnOggettoLeggero() {
		assertTrue(this.b.addAttrezzo(attrezzo1));
	}
	
	@Test
	void AggiuntaOggettoTroppoPesante() {
		assertFalse(this.b.addAttrezzo(attrezzo4));
	}
	
	@Test
	void AggiuntaDiOggettoConBorsaPiena() {
		this.b.addAttrezzo(attrezzo3);
		assertFalse(this.b.addAttrezzo(attrezzo1));
	}
	
	@Test
	void AggiuntaDiOggettoDopoAggiuntaDiAltro() {
		this.b.addAttrezzo(attrezzo1);
		assertTrue(this.b.addAttrezzo(attrezzo2));
	}
	
	@Test
	void BorsaVuotaSenzaAggiunte() {
		assertTrue(this.b.isEmpty());
	}
	
	@Test
	void FunzionamentoPesoMassimo() {
		assertEquals(this.b.getPesoMax(),10);
	}
}
