package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;

class TestIOSimulator {
private Partita partita;
private Labirinto labirintoStandard;

@BeforeEach
void setUp() {
	labirintoStandard = new LabirintoBuilder()
			.addStanzaIniziale("Atrio")
			.addAttrezzo("osso", 1) 
			.addStanzaVincente("Biblioteca")
			.addStanzaMagica("Aula N11")
			.addStanzaBloccata("Aula N10", "nord", "passepartout")
			.addAttrezzo("lanterna", 3)    
			.addAttrezzo("passepartout", 1) 
			.addStanza("Laboratorio Campus")
			.addStanzaBuia("Bar")
			.addStanza("Mensa")
			.addAdiacenza("Atrio", "Biblioteca", "nord")
			.addAdiacenza("Atrio", "Aula N11", "est")
			.addAdiacenza("Atrio", "Aula N10", "sud")
			.addAdiacenza("Atrio", "Laboratorio Campus", "ovest")
			.addAdiacenza("Aula N11", "Laboratorio Campus", "est")
			.addAdiacenza("Aula N11", "Atrio", "ovest")
			.addAdiacenza("Aula N10", "Atrio", "nord")
			.addAdiacenza("Aula N10", "Aula N11", "est")
			.addAdiacenza("Aula N10", "Laboratorio Campus", "ovest")	
			.addAdiacenza("Laboratorio Campus", "Atrio", "est")
			.addAdiacenza("Laboratorio Campus", "Aula N11", "ovest")	
			.addAdiacenza("Biblioteca", "Atrio", "sud")
			.addAdiacenza("Bar", "Mensa", "nord")
			.addAdiacenza("Mensa", "Bar", "sud")
			.getLabirinto();
}

	@Test
	void testPartitaBase_VinciAndandoVersoNord() {
		List<String >comandi = new ArrayList<>();
		comandi.add("vai nord");
		IOSimulator io= new IOSimulator(comandi);
		DiaDia gioco=new DiaDia(labirintoStandard, io);
		partita=gioco.getPartita();
		gioco.gioca();
		assertTrue(partita.isFinita());

	}

	
	@Test
	void testPartitaBase_NessunAttrezzoInBorsa() {
		List<String>comandi = new ArrayList<>();
		comandi.add("vai nord");
		IOSimulator io=  new IOSimulator(comandi);
		DiaDia gioco=new DiaDia(labirintoStandard, io);
		partita=gioco.getPartita();
		gioco.gioca();
		assertTrue(partita.getGiocatore().getBorsa().isEmpty());
	}

	
	@Test
	void testPartitaBase_PrendoOssoDaAtrio() {
		List<String >comandi = new ArrayList<>();
		comandi.addAll((Arrays.asList("prendi osso", "vai nord")));
		IOSimulator io= new IOSimulator(comandi);
		DiaDia gioco=new DiaDia(labirintoStandard, io);
		partita=gioco.getPartita();
		gioco.gioca();
		assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo("osso"));
		assertTrue(partita.isFinita());
	}

	
	@Test
	void testPartitaBase_PrendoOssoDaAtrio_PosoOsso() {
		List<String >comandi = new ArrayList<>();
		comandi.addAll((Arrays.asList("prendi osso","posa osso", "vai nord")));
		IOSimulator io= new IOSimulator(comandi);
		DiaDia gioco=new DiaDia(labirintoStandard, io);
		partita=gioco.getPartita();
		gioco.gioca();
		assertTrue(partita.getGiocatore().getBorsa().isEmpty());
		assertTrue(partita.isFinita());
	}
	
	@Test
	void testPartitaBase_ComandoNonValidoNonInfluenzaPartita() {
		List<String >comandi = new ArrayList<>();
		comandi.addAll((Arrays.asList("posa", "prendi", "posa osso", "vai","vai nord" )));
		IOSimulator io= new IOSimulator(comandi);
		DiaDia gioco=new DiaDia(labirintoStandard, io);
		partita=gioco.getPartita();
		gioco.gioca();
		assertTrue(partita.getGiocatore().getBorsa().isEmpty());
		assertEquals(partita.getGiocatore().getCfu(),19);
		assertTrue(partita.isFinita());
		assertEquals(io.getRigheProdotte().get(1), "Che attrezzo vuoi posare?" );
		assertEquals(io.getRigheProdotte().get(2), "Che attrezzo vuoi prendere?");
		assertEquals(io.getRigheProdotte().get(3), "\nLa borsa è vuota!\n" );
		assertEquals(io.getRigheProdotte().get(4), "Dove vuoi andare? Devi specificare una direzione" );
	}

}
