package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;




import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;






class FabbricaDiComandiFisarmonicaTest {
	
	private FabbricaDiComandiFisarmonica fabbrica;
	private Comando expected;
	private IO io;

	@BeforeEach
	void setUp() throws Exception {
		io=new IOConsole();
		fabbrica = new FabbricaDiComandiFisarmonica(io);
	}
	
	@Test
	public void testComandoNonValido() {
		expected = new ComandoNonValido();
		assertEquals( expected.getNome(), fabbrica.costruisciComando("pippo").getNome());
	}
	
	@Test
	public void testComandoConParametro() {
		expected = new ComandoVai();
		expected.setParametro("nord");
		Comando current = fabbrica.costruisciComando("vai nord");
		assertEquals( expected.getNome(), current.getNome());
		assertEquals( expected.getParametro(), current.getParametro());
	}
	
	@Test
	public void testComandoSenzaParametro() {
		expected = new ComandoFine();
		assertEquals( expected.getNome(), fabbrica.costruisciComando("fine").getNome());
	}

}
