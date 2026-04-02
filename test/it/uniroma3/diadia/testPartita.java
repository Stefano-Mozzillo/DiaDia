package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

class testPartita {
	
	private Partita partita;
	
	@BeforeEach
	public void setUp() {
		this.partita = new Partita();
	}

	/**
	 * Test che verifica se vinco la partita nella stanza finale
	 * @return true
	 */
	@Test
	void testPartitaVintaInStanzaFinale() {
		this.partita.setStanzaCorrente(this.partita.getLabirinto().getStanzaFinale());
		assertTrue(this.partita.vinta());
	}
	
	/**
	 * Test che verifica se vinco la partita in un'altra stanza
	 * @return false
	 */
	@Test
	void testPartitaVintaNonInStanzaFinale() {
		this.partita.setStanzaCorrente(this.partita.getLabirinto().getStanzaCorrente());
		assertFalse(this.partita.vinta());
	}
	
	/**
	 * Test che verifica se non vinco la partita nella stanza finale
	 * @return false
	 */
	@Test
	void testPartitaNonVintaInStanzaFinale() {
		this.partita.setStanzaCorrente(this.partita.getLabirinto().getStanzaFinale());
		assertFalse(this.partita.vinta()==false);
	}
	
	/**
	 * Test che verifica se la partita finisce con 0 cfu rimasti
	 * @return true
	 */
	@Test
	void testPartitaFinitaCFU_0() {
		this.partita.getGiocatore().setCfu(0);
		assertTrue(this.partita.isFinita());
	}
	
	/**
	 * Test che verifica se la non partita finisce con 0 cfu rimasti
	 * @return true
	 */
	@Test
	void testPartitaNonFinitaCFU_0() {
		this.partita.getGiocatore().setCfu(0);
		assertFalse(this.partita.isFinita()==false);
	}
	
	/**
	 * Test che verifica se la partita finisce quando la vinco
	 * @return true
	 */
	@Test
	void testPartitaFinitaVinta() {
		this.partita.setStanzaCorrente(this.partita.getLabirinto().getStanzaFinale());
		assertTrue(this.partita.isFinita());
	}

}
