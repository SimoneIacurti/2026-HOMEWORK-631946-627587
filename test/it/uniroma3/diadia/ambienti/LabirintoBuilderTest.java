package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilderTest {

	private Labirinto labirintoDefault;
	private Labirinto.LabirintoBuilder builder;

	@BeforeEach
	public void setUp() {
		this.labirintoDefault = Labirinto.creaLabirintoDefault();
		this.builder = Labirinto.newBuilder();
	}

	@Test
	public void testCreaLabirintoDefault_StanzaInizialeIsAtrio() {
		assertNotNull(this.labirintoDefault.getStanzaIniziale());
		assertEquals("Atrio", this.labirintoDefault.getStanzaIniziale().getNome());
	}

	@Test
	public void testCreaLabirintoDefault_StanzaVincenteIsBiblioteca() {
		assertNotNull(this.labirintoDefault.getStanzaVincente());
		assertEquals("Biblioteca", this.labirintoDefault.getStanzaVincente().getNome());
	}

	@Test
	public void testCreaLabirintoDefault_PresenzaAttrezziIniziali() {
		assertTrue(this.labirintoDefault.hasAttrezzo("osso"));
		assertTrue(this.labirintoDefault.hasAttrezzo("lanterna"));
		assertFalse(this.labirintoDefault.hasAttrezzo("scudo"));
	}

	@Test
	public void testAddAttrezzoLabirinto_NuovoAttrezzo() {
		Stanza camera = new Stanza("Camera");
		Attrezzo chiave = new Attrezzo("chiave", 1);
		
		assertTrue(this.labirintoDefault.addAttrezzoLabirinto(camera, chiave));
		assertTrue(this.labirintoDefault.hasAttrezzo("chiave"));
		assertTrue(camera.hasAttrezzo("chiave"));
	}

	@Test
	public void testAddAttrezzoLabirinto_AttrezzoDuplicato() {
		Stanza camera = new Stanza("Camera");
		Attrezzo ossoDuplicato = new Attrezzo("osso", 2);
		
		assertFalse(this.labirintoDefault.addAttrezzoLabirinto(camera, ossoDuplicato));
	}

	@Test
	public void testRemoveAttrezzoLabirinto_Successo() {
		Stanza atrio = this.labirintoDefault.getStanzaIniziale();
		Attrezzo osso = atrio.getAttrezzo("osso");
		
		assertNotNull(osso);
		assertTrue(this.labirintoDefault.removeAttrezzoLabirinto(atrio, osso));
		assertFalse(this.labirintoDefault.hasAttrezzo("osso"));
	}

	@Test
	public void testBuilder_StanzaInizialeEVincente() {
		Labirinto lab = this.builder
				.addStanzaIniziale("Entrata")
				.addStanzaVincente("Uscita")
				.getLabirinto();
		
		assertEquals("Entrata", lab.getStanzaIniziale().getNome());
		assertEquals("Uscita", lab.getStanzaVincente().getNome());
	}

	@Test
	public void testBuilder_AddAdiacenza() {
		Labirinto lab = this.builder
				.addStanzaIniziale("Salotto")
				.addStanza("Cucina")
				.addAdiacenza("Salotto", "Cucina", "nord")
				.getLabirinto();
		
		Stanza salotto = lab.getStanza("Salotto");
		Stanza cucina = lab.getStanza("Cucina");
		
		assertNotNull(salotto);
		assertEquals(cucina, salotto.getStanzaAdiacente(Direzioni.NORD));
	}

	@Test
	public void testBuilder_AddAttrezzoAllUltimaStanza() {
		Labirinto lab = this.builder
				.addStanzaIniziale("Studio")
				.addAttrezzo("penna", 1)
				.getLabirinto();
		
		assertTrue(lab.hasAttrezzo("penna"));
		assertTrue(lab.getStanza("Studio").hasAttrezzo("penna"));
	}

	@Test
	public void testBuilder_AddAttrezzoAStanzaSpecifica() {
		Labirinto lab = this.builder
				.addStanzaIniziale("Atrio")
				.addStanza("Ripostiglio")
				.addAttrezzoAStanza("Ripostiglio", "scopa", 3)
				.getLabirinto();
		
		assertTrue(lab.hasAttrezzo("scopa"));
		assertTrue(lab.getStanza("Ripostiglio").hasAttrezzo("scopa"));
		assertFalse(lab.getStanza("Atrio").hasAttrezzo("scopa"));
	}

	@Test
	public void testBuilder_StanzeSpeciali() {
		Labirinto lab = this.builder
				.addStanzaMagica("StanzaMagica")
				.addStanzaBuia("StanzaBuia", "lanterna")
				.addStanzaBloccata("StanzaBloccata", "nord", "chiave")
				.getLabirinto();
		
		assertNotNull(lab.getStanza("StanzaMagica"));
		assertNotNull(lab.getStanza("StanzaBuia"));
		assertNotNull(lab.getStanza("StanzaBloccata"));
	}
}
