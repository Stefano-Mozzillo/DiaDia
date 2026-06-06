package it.uniroma3.comando;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comando.AbstractComando;
import it.uniroma3.diadia.comando.Comando;
import it.uniroma3.diadia.comando.FabbricaDiComandiRiflessiva;
import it.uniroma3.diadia.giocatore.Borsa;

public class TestComandoPrendi {
	private Partita partita;
	private IOConsole console;
	private FabbricaDiComandiRiflessiva factory;
	private Attrezzo martello;
	private AbstractComando comandoDaEseguire;
	private Borsa borsa;

	@BeforeEach
	void setUp() {
		Labirinto labirinto = Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.addAttrezzo("osso", 1) 
				.addStanzaVincente("Biblioteca")
				.addStanzaMagica("Aula N11")
				.addStanzaBloccata("Aula N10", Direzione.NORD, "passepartout")
				.addAttrezzo("lanterna", 3)    
				.addAttrezzo("passepartout", 1) 
				.addStanza("Laboratorio Campus")
				.addCane("Rufus,"," sono un cane")
				.addStanzaBuia("Bar")
				.addStrega("Salvini", "sono una strega")
				.addStanza("Mensa")
				.addAdiacenza("Atrio", "Biblioteca", Direzione.NORD)
				.addAdiacenza("Atrio", "Aula N11", Direzione.EST)
				.addAdiacenza("Atrio", "Aula N10", Direzione.SUD)
				.addAdiacenza("Atrio", "Laboratorio Campus", Direzione.OVEST)
				.addAdiacenza("Aula N11", "Laboratorio Campus", Direzione.EST)
				.addAdiacenza("Aula N11", "Atrio", Direzione.OVEST)
				.addAdiacenza("Aula N10", "Atrio", Direzione.NORD)
				.addAdiacenza("Aula N10", "Aula N11", Direzione.EST)
				.addAdiacenza("Aula N10", "Laboratorio Campus", Direzione.OVEST)	
				.addAdiacenza("Laboratorio Campus", "Atrio", Direzione.EST)
				.addAdiacenza("Laboratorio Campus", "Aula N11", Direzione.OVEST)	
				.addAdiacenza("Biblioteca", "Atrio", Direzione.SUD)
				.addAdiacenza("Bar", "Mensa", Direzione.NORD)
				.addAdiacenza("Mensa", "Bar", Direzione.SUD)
				.getLabirinto();
				
		partita=new Partita(labirinto);
		console=new IOConsole(new Scanner(System.in));
		factory = new FabbricaDiComandiRiflessiva();
		martello=new Attrezzo("martello",1);
		try {
			comandoDaEseguire=factory.costruisciComando("prendi martello");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		borsa= partita.getGiocatore().getBorsa();
		}


	

	@Test
	void testAttrezzoPresoDaStanza_Trovato_InseritoBorsa() {
		assertEquals(partita.getStanzaCorrente().getNome(),"Atrio");
		assertTrue(partita.getStanzaCorrente().addAttrezzo(martello));
		comandoDaEseguire.esegui(partita, console);
		assertEquals(borsa.getAttrezzo("martello"),martello);

	}
	@Test
	void testAttrezzoPresoDaStanza_Introvabile() {
		assertNull(partita.getStanzaCorrente().getAttrezzo("martello"));
		comandoDaEseguire.esegui(partita, console);
		assertNull(borsa.getAttrezzo("martello"));
		assertNull(partita.getStanzaCorrente().getAttrezzo("martello"));
	}

	@Test
	void testAttrezzoPresoDaStanza_ParametroNull() {
		assertEquals(partita.getStanzaCorrente().getNome(),"Atrio");
		try {
			comandoDaEseguire=factory.costruisciComando("prendi");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(partita.getStanzaCorrente().addAttrezzo(martello));
		comandoDaEseguire.esegui(partita, console);
		assertNull(borsa.getAttrezzo("martello"));
		assertEquals(partita.getStanzaCorrente().getAttrezzo("martello"),martello);

	}

//	@Test
//	void testAttrezzoPosatoDaBorsa_StanzaPiena() {
//		assertEquals(partita.getStanzaCorrente().getNome(),"Atrio");
//		comandoDaEseguire=factory.costruisciComando("posa martello");
//		assertTrue(borsa.addAttrezzo(martello));
//		piena=partita.getStanzaCorrente();
//		for(int i=0; i<9;i++)  //atrio ha già un oggetto per costruzione
//			assertTrue(piena.addAttrezzo(new Attrezzo("Attrezzo_" +i, i)));
//		comandoDaEseguire.esegui(partita, console);
//		assertNull(piena.getAttrezzo("martello"));
//
//
//	}
}
