package it.uniroma3.diadia.ambienti;
import it.uniroma3.diadia.attrezzi.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StanzaMagicaTest {
private StanzaMagica magica;
private Attrezzo attrezzo;
private StanzaMagica magica1;
private Attrezzo bacchetta;
	@BeforeEach
	void setUp() throws Exception {
		magica=new StanzaMagica("magica",0);
		magica1=new StanzaMagica("magica",1);
		attrezzo=new Attrezzo("attrezzo",2);
		bacchetta=new Attrezzo("bacchetta",1);
	}

	@Test
	void testAddAttrezzoPositivo() {
		magica.addAttrezzo(attrezzo);
		assertEquals("ozzertta",magica.getAttrezzo("ozzertta").getNome());
		assertEquals(4,magica.getAttrezzo("ozzertta").getPeso());
		
		
	}
	@Test
void testAddDueAttrezziPositivo() {
	magica1.addAttrezzo(bacchetta);
	magica1.addAttrezzo(attrezzo);
	assertEquals("bacchetta",magica1.getAttrezzo("bacchetta").getNome());
	assertEquals("ozzertta",magica1.getAttrezzo("ozzertta").getNome());
}
}