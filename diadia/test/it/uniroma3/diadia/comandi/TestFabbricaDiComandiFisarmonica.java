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
}