package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import java.io.StringReader;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Direzioni;
import it.uniroma3.diadia.ambienti.Labirinto;

class CaricatoreLabirintoTest {


	@Test
	void testCaricatoreLabirinto_Monolocale() throws Exception {
		String monolocale = 
				"Stanze:\n" +
				"Sgabuzzino\n" +
				"Stanze magiche:\n" +
				"Stanze chiuse:\n" +
				"Stanze buie:\n" +
				"Estremi:\n" +
				"Sgabuzzino\n" +
				"Sgabuzzino\n" +
				"Attrezzi:\n" +
				"Personaggi:\n" +
				"Uscite:\n";

		CaricatoreLabirinto caricatore = new CaricatoreLabirinto(new StringReader(monolocale));
		caricatore.carica();
		Labirinto lab = caricatore.getLabirinto();

		assertNotNull(lab);
		assertEquals("Sgabuzzino", lab.getStanzaIniziale().getNome());
		assertEquals("Sgabuzzino", lab.getStanzaVincente().getNome());
	}
	@Test
	void testCaricatoreLabirinto_Bilocale() throws Exception {
		String bilocale = 
				"Stanze:\n" +
				"N10\n" +
				"Biblioteca\n" +
				"Stanze magiche:\n" +
				"Stanze chiuse:\n" +
				"Stanze buie:\n" +
				"Estremi:\n" +
				"N10\n" +
				"Biblioteca\n" +
				"Attrezzi:\n" +
				"Personaggi:\n" +
				"Uscite:\n" +
				"N10 nord Biblioteca\n" +
				"Biblioteca sud N10\n";

		CaricatoreLabirinto caricatore = new CaricatoreLabirinto(new StringReader(bilocale));
		caricatore.carica();
		Labirinto lab = caricatore.getLabirinto();

		assertEquals("N10", lab.getStanzaIniziale().getNome());
		assertEquals("Biblioteca", lab.getStanzaVincente().getNome());
		
		assertEquals("Biblioteca", lab.getStanza("N10").getStanzaAdiacente(Direzioni.NORD).getNome());
		assertEquals("N10", lab.getStanza("Biblioteca").getStanzaAdiacente(Direzioni.SUD).getNome());
	}

	@Test
	void testCaricatoreLabirinto_TrilocaleConAttrezzi() throws Exception {
		String trilocale = 
				"Stanze:\n" +
				"Atrio\n" +
				"N10\n" +
				"Biblioteca\n" +
				"Stanze magiche:\n" +
				"Stanze chiuse:\n" +
				"Stanze buie:\n" +
				"Estremi:\n" +
				"Atrio\n" +
				"Biblioteca\n" +
				"Attrezzi:\n" +
				"Osso 5 N10\n" +    
				"Personaggi:\n" +
				"Uscite:\n" +
				"Atrio nord Biblioteca\n" +
				"Atrio est N10\n";

		CaricatoreLabirinto caricatore = new CaricatoreLabirinto(new StringReader(trilocale));
		caricatore.carica();
		Labirinto lab = caricatore.getLabirinto();

		assertNotNull(lab.getStanza("Atrio"));
		assertNotNull(lab.getStanza("N10"));
		
		assertTrue(lab.getStanza("N10").hasAttrezzo("Osso"), "L'osso dovrebbe essere nella stanza N10");
		assertEquals(5, lab.getStanza("N10").getAttrezzo("Osso").getPeso(), "L'osso dovrebbe pesare 5");
		
		assertTrue(lab.hasAttrezzo("Osso"));
	}

	@Test
	void testCaricatoreLabirinto_EccezioneFormatoNonValido() {
		String errato = 
				"Stanze:\n" +
				"Atrio\n" +
				"Stanze magiche:\n" +
				"Stanze chiuse:\n" +
				"Stanze buie:\n" +
				"Attrezzi:\n" + 
				"Personaggi:\n" +
				"Uscite:\n";

		CaricatoreLabirinto caricatore = new CaricatoreLabirinto(new StringReader(errato));
		
		assertThrows(FormatoFileNonValidoException.class, () -> {
			caricatore.carica();
		});
	}
}