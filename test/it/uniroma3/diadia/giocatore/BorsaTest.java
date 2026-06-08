package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import java.util.SortedSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class BorsaTest {
	
	Borsa b;
	Attrezzo attrezzo1;
	Attrezzo attrezzo2;
	Attrezzo attrezzo3;
	Attrezzo attrezzo4;
	Attrezzo bottiglia;
	Attrezzo dado;

	@BeforeEach
	void setUp(){
		b=new Borsa();
		attrezzo1=new Attrezzo("attrezzo1",5);
		attrezzo2=new Attrezzo("attrezzo2",5);
		attrezzo3=new Attrezzo("attrezzo3",10);
		attrezzo4=new Attrezzo("attrezzo4",12);
		bottiglia=new Attrezzo("bottiglia",3);
		dado=new Attrezzo("dado",4);
		
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
	
	@Test
	void testDueAttrezziConPesoUgualeMaNomeDiverso() {
		b.addAttrezzo(attrezzo1);
		b.addAttrezzo(attrezzo2);
		SortedSet<Attrezzo> nuovoset=b.getSortedSetOrdinatoPerPeso();
		assertTrue(nuovoset.contains(attrezzo1));
		assertTrue(nuovoset.contains(attrezzo2));
		
	}
	
	@Test
	void OridinaDuePerPeso() {
		b.addAttrezzo(attrezzo1);
		b.addAttrezzo(attrezzo3);
		List<Attrezzo> lista=b.getContenutoOrdinatoPerPeso();
		assertEquals(lista.getLast(),attrezzo3);
		assertEquals(lista.getFirst(),attrezzo1);
	}
	
	@Test
	void OrdinaDuePerNome() {

		b.addAttrezzo(dado);
		b.addAttrezzo(bottiglia);
		SortedSet<Attrezzo> set=b.getContenutoOrdinatoPerNome();
		assertEquals(set.getFirst(),bottiglia);
		assertEquals(set.getLast(),dado);		
	}
	
	@Test
	void MappaOrdinataConDueUgualiInPeso() {
		b.addAttrezzo(attrezzo1);
		b.addAttrezzo(attrezzo2);
		Map<Integer,Set<Attrezzo>> mappa= b.getContenutoRaggruppatoPerPeso();
		
		assertTrue(mappa.get(2).contains(attrezzo1));
		assertTrue(mappa.get(2).contains(attrezzo2));
		
		
	}
}
