package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class TestStanzaBloccata {

private Stanza stanzaNordBloccato;
private Stanza nord;
private Attrezzo passepartout;
private String descrizioneSenzaPassepartout;

	@BeforeEach
	void SetUp() {
	stanzaNordBloccato= new StanzaBloccata("iniziale",Direzione.NORD,"passepartout");
	nord= new Stanza ("nord");
	stanzaNordBloccato.impostaStanzaAdiacente(Direzione.NORD, nord);
	passepartout= new Attrezzo("passepartout", 1);
	descrizioneSenzaPassepartout=stanzaNordBloccato.getDescrizione();
	}
	
	
	@Test
	void testStanzaAdiacenteBloccataSenzaPasspartoutInStanzaCorrente_SbloccataConPassepartout() {
		assertFalse(stanzaNordBloccato.hasAttrezzo("passepartout"));
		assertNull(stanzaNordBloccato.getStanzaAdiacente(Direzione.NORD));
		assertTrue(stanzaNordBloccato.addAttrezzo(passepartout));
		assertEquals(stanzaNordBloccato.getStanzaAdiacente(Direzione.NORD), nord);
	
	}
	@Test
	void testDescrizioneStanze() {
		assertFalse(stanzaNordBloccato.hasAttrezzo("passepartout"));
		assertEquals(descrizioneSenzaPassepartout, stanzaNordBloccato.getDescrizione());
		assertTrue(stanzaNordBloccato.addAttrezzo(passepartout));
		assertNotEquals(descrizioneSenzaPassepartout, stanzaNordBloccato.getDescrizione());
	}
	
	@Test
	void testStanzaBloccata_diversaDaStanzaAdiacenteSceltaDaUtente() {
		assertFalse(stanzaNordBloccato.hasAttrezzo("passepartout"));
		Stanza est= new Stanza("est");
		stanzaNordBloccato.impostaStanzaAdiacente(Direzione.EST,est);
		assertEquals(stanzaNordBloccato.getStanzaAdiacente(Direzione.EST), est);}
	
	@Test
	void oggettoDiversoDaPassepartoutNonSbloccaLaStanza(){
		assertFalse(stanzaNordBloccato.hasAttrezzo("passepartout"));
		assertTrue(stanzaNordBloccato.addAttrezzo(new Attrezzo("martello",3)));
		assertNull(stanzaNordBloccato.getStanzaAdiacente(Direzione.NORD));}
	
	

}
