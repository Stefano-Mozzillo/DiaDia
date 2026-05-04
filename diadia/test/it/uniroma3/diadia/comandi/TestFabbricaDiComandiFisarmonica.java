package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestFabbricaDiComandiFisarmonica {

	@Test
	void testRiconoscimentoComandoVai() {
		FabbricaDiComandiFisarmonica factory=new FabbricaDiComandiFisarmonica();
		factory.costruisciComando("vai nord");
		assertEquals(factory.getNome(), "vai");
		assertEquals(factory.getParametro(), "nord");
	}
	@Test
	void testRiconoscimentoComandoPrendi() {
		FabbricaDiComandiFisarmonica factory=new FabbricaDiComandiFisarmonica();
		factory.costruisciComando("prendi attrezzo");
		assertEquals(factory.getNome(), "prendi");
		assertEquals(factory.getParametro(), "attrezzo");
	}
	@Test
	void testRiconoscimentoComandoAiuto() {
		FabbricaDiComandiFisarmonica factory=new FabbricaDiComandiFisarmonica();
		factory.costruisciComando("aiuto");
		assertEquals(factory.getNome(), "aiuto");
		assertNull(factory.getParametro());
	}
	@Test
	void testRiconoscimentoComandoPosa() {
		factory.costruisciComando("posa attrezzo");
		assertEquals(factory.getNome(), "posa");
		assertEquals(factory.getParametro(), "attrezzo");
	}
	@Test
	void testRiconoscimentoComandoFine() {
		factory.costruisciComando("fine");
		assertEquals(factory.getNome(), "fine");
		assertNull(factory.getParametro());
	}
	@Test
	void testRiconoscimentoComandoGuarda() {
		factory.costruisciComando("guarda");
		assertEquals(factory.getNome(), "guarda");
		assertNull(factory.getParametro());
	}
	@Test
	void testRiconoscimentoComandoNonValido() {
		factory.costruisciComando("...");
		assertEquals(factory.getNome(), "...");
		assertNull(factory.getParametro());
	}
	@Test
	void testRiconoscimentoComandoVuoto() {
		factory.costruisciComando("");
		assertNull(factory.getNome());
		assertNull(factory.getParametro());
	}
}
