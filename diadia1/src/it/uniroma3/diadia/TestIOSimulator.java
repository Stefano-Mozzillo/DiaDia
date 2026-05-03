package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestIOSimulator {
private Partita partita;

	@Test
	void testPartitaBase_VinciAndandoVersoNord() {
		String[] comandi={"vai nord"};
		IOSimulator io= new IOSimulator(comandi);
		DiaDia gioco=new DiaDia(io);
		partita=gioco.getPartita();
		gioco.gioca();
		assertTrue(partita.isFinita());

	}
	
	@Test
	void testPartitaBase_NessunAttrezzoInBorsa() {
		String[] comandi={"vai nord"};
		IOSimulator io= new IOSimulator(comandi);
		DiaDia gioco=new DiaDia(io);
		partita=gioco.getPartita();
		gioco.gioca();
		assertTrue(partita.getGiocatore().getBorsa().isEmpty());
	}
	
	@Test
	void testPartitaBase_PrendoOssoDaAtrio() {
		String[] comandi={"prendi osso", "vai nord"};
		IOSimulator io= new IOSimulator(comandi);
		DiaDia gioco=new DiaDia(io);
		partita=gioco.getPartita();
		gioco.gioca();
		assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo("osso"));
	}
	
	@Test
	void testPartitaBase_PrendoOssoDaAtrio_PosoOsso() {
		String[] comandi={"prendi osso","posa osso", "vai nord"};
		IOSimulator io= new IOSimulator(comandi);
		DiaDia gioco=new DiaDia(io);
		partita=gioco.getPartita();
		gioco.gioca();
		assertTrue(partita.getGiocatore().getBorsa().isEmpty());
	}

}
