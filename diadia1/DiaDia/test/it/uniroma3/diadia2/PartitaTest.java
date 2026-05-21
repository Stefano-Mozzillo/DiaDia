package it.uniroma3.diadia2;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;



class PartitaTest {
	
	private Partita partita;
	private Stanza aulaN11;
	private Labirinto trilocale;
	
	@BeforeEach
	public void setUp() {
	trilocale = new LabirintoBuilder()
				.addStanzaIniziale("salotto")
				.addStanza("cucina")
				.addAttrezzo("pentola",1)
				.addStanzaVincente("camera")
				.addAdiacenza("salotto", "cucina", "nord")
				.addAdiacenza("cucina", "camera", "est")
				.getLabirinto();
		partita = new Partita(trilocale);
	}

	
//vinta()
	@Test
	void testPartitaVintaInStanzaFinale() {
		partita.setStanzaCorrente(trilocale.getStanzaVincente());
		assertTrue(partita.vinta());
	}
	
	@Test
	void testPartitaVinta_NonInStanzaVincente() {
		assertNotEquals(trilocale.getStanzaIniziale(), trilocale.getStanzaVincente());
		assertFalse(this.partita.vinta());
	}
	
	
//isFinita()
	@Test
	void testPartitaFinitaCFU_0() {
		this.partita.getGiocatore().setCfu(0);
		assertTrue(partita.isFinita());
	}

	@Test
	void testPartitaFinitaVinta() {
		this.partita.setStanzaCorrente(this.partita.getLabirinto().getStanzaVincente());
		assertTrue(partita.isFinita());
	}
//setStanzaCorrente()
	@Test
	void testNuovaStanzaCorrenteInseritaCorrettamente() {
		aulaN11=new Stanza("aulaN11");
		partita.setStanzaCorrente(aulaN11);
		assertEquals(partita.getStanzaCorrente().getNome(),"aulaN11");
	}
	

}


