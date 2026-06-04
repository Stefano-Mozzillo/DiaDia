package it.uniroma3.comando;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comando.Comando;
import it.uniroma3.diadia.comando.FabbricaDiComandiFisarmonica;
import it.uniroma3.diadia.giocatore.Borsa;

public class TestComandoPrendi {
	private Partita partita;
	private IOConsole console;
	private FabbricaDiComandiFisarmonica factory;
	private Attrezzo martello;
	private Comando comandoDaEseguire;
	private Borsa borsa;

	@BeforeEach
	void setUp() {
		partita=new Partita();
		console=new IOConsole();
		factory = new FabbricaDiComandiFisarmonica();
		martello=new Attrezzo("martello",1);
		comandoDaEseguire=factory.costruisciComando("prendi martello");
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
		comandoDaEseguire=factory.costruisciComando("prendi");
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
