package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;

class AbstractComandoTest {

	private AbstractComando comando;
	private Partita partita;
	private IOSimulator io;

	@BeforeEach
	void setUp() {
		List<String> comandiDaLeggere = new ArrayList<>();
		io = new IOSimulator(comandiDaLeggere);
		partita = new Partita(io);
		
		comando = new AbstractComando() {
			@Override
			public void esegui(Partita partita) {
			}
			
			@Override 
			public String getNome() {
				return "abstract comando";
			}
			
			@Override
			public void setParametro(String parametro) {
			}
		};
	}

	@Test
	void creaComandoVaiTest() {
		comando = new ComandoVai();
		comando.setIo(io);
		comando.setParametro("nord");
		comando.esegui(partita);
		
		assertTrue(partita.vinta());
		assertEquals("vai", comando.getNome());
	}

	@Test
	void creaComandoPrendiTest() {
		comando = new ComandoPrendi();
		comando.setIo(io);
		comando.setParametro("osso");
		comando.esegui(partita);
		
		assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo("osso"));
		assertEquals("prendi", comando.getNome());
	}

	@Test
	void creaComandoGuardaTest() {
		comando = new ComandoGuarda();
		comando.setIo(io);
		comando.setParametro("osso");
		comando.esegui(partita);
		
		assertEquals("guarda", comando.getNome());
		assertNull(comando.getParametro());
	}

	@Test
	void abstractComandoBaseTest() {
		assertEquals("abstract comando", comando.getNome());
		assertNull(comando.getParametro());	
	}
}
