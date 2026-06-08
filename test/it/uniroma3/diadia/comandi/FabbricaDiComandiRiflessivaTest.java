package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FabbricaDiComandiRiflessivaTest {

	private FabbricaDiComandiRiflessiva fabbrica;
	private Comando expected;

	@BeforeEach
	void setUp() throws Exception {
		this.fabbrica = new FabbricaDiComandiRiflessiva();
	}

	@Test
	public void testComandoNonValido() {
		expected = new ComandoNonValido();
		assertEquals(expected.getNome(), fabbrica.costruisciComando("pippo").getNome());
	}

	@Test
	public void testComandoConParametro() {
		expected = new ComandoVai();
		expected.setParametro("nord");
		
		Comando current = fabbrica.costruisciComando("vai nord");
		
		assertEquals(expected.getNome(), current.getNome());
		assertEquals(expected.getParametro(), current.getParametro());
	}

	@Test
	public void testComandoSenzaParametro() {
		expected = new ComandoFine();
		assertEquals(expected.getNome(), fabbrica.costruisciComando("fine").getNome());
	}
}