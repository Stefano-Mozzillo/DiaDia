package it.uniroma3.diadia.ambienti;
import it.uniroma3.diadia.attrezzi.Attrezzo;



import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;




class StanzaTest {
private Stanza stanza;
private Stanza stanzaAdiacente;
private Stanza stanzaConUnMartello;
private Attrezzo martello;




	@BeforeEach
	void setUp() {
	stanza=new Stanza("stanza");
	stanzaConUnMartello=new Stanza("stanza con un martello");
	martello= new Attrezzo("martello", 5);
	stanzaConUnMartello.addAttrezzo(martello);
	stanzaAdiacente= new Stanza("stanza adiacente");
	
	
	
	}
	
//hasAttrezzo e addAttrezzo
    @Test
    void testLeStanzeVuoteNonHannoAttrezzi() {
	assertFalse(new Stanza("vuota").hasAttrezzo("martello"));
    }
    
    
    @Test
    void attrezzoAggiuntoCorrettamente() {
		assertTrue(stanza.addAttrezzo(martello));
		assertTrue(stanza.hasAttrezzo("martello"));
    }
    
    @Test
    void testStanzaConUnAttrezzo_trovato() {
		assertTrue(stanzaConUnMartello.hasAttrezzo("martello"));
    }
    
    @Test
    void testStanzaConUnAttrezzo_introvabile() {
        assertFalse(stanzaConUnMartello.hasAttrezzo("introvabile"));
    }
    
   
 
//removeAttrezzo	
    @Test
    void testRimuoviAttrezzoDaStanzaConUnAttrezzo_trovato() {
    	assertTrue(stanzaConUnMartello.removeAttrezzo(martello));
    	assertFalse(stanzaConUnMartello.hasAttrezzo("martello"));
    }
    
    @Test
    void testRimuoviAttrezzo_introvabile() {
    	assertFalse(stanzaConUnMartello.removeAttrezzo(new Attrezzo("spada",4)));
    }
    
    @Test
    void testRimuoviAttrezzoDaStanzaVuota() {
    	assertFalse(new Stanza("vuota").removeAttrezzo(martello));
    }
    
    
//getAttrezzo
   @Test
   void testGetAttrezzo_trovato() {
	   assertEquals(martello,stanzaConUnMartello.getAttrezzo("martello"));
   }
 
   @Test
   void testGetAttrezzo_introvabile() {
	   assertNull(stanzaConUnMartello.getAttrezzo("spada"));
   }
   
//impostaStanzaAdiacente   e getStanzaAdiacente 
    @Test
	void testImpostaStanzaAdiacenteNordCorrettamente() {
		stanza.impostaStanzaAdiacente(Direzione.NORD, stanzaAdiacente);
		assertEquals(stanzaAdiacente,stanza.getStanzaAdiacente(Direzione.NORD), "inserita stanza nord");
	}
	@Test
	void testImpostaStanzaAdiacenteSudCorrettamente() {
		stanza.impostaStanzaAdiacente(Direzione.SUD,stanzaAdiacente);
		assertEquals(stanzaAdiacente,stanza.getStanzaAdiacente(Direzione.SUD));
	}
	
	@Test
	void testImpostaStanzaAdiacenteEstCorrettamente() {
		stanza.impostaStanzaAdiacente(Direzione.EST,stanzaAdiacente);
		assertEquals(stanzaAdiacente,stanza.getStanzaAdiacente(Direzione.EST));
	}
	
	@Test
	void testImpostaStanzaAdiacenteOvestCorrettamente() {
		stanza.impostaStanzaAdiacente(Direzione.OVEST,stanzaAdiacente);
		assertEquals(stanzaAdiacente,stanza.getStanzaAdiacente(Direzione.OVEST));
	}
	
	
	
	@Test
	void aggiornaDirezioneGiàEsistente() {
	    Stanza nuovaStanza = new Stanza("nuova");
	    stanza.impostaStanzaAdiacente(Direzione.NORD, stanzaAdiacente);
	    stanza.impostaStanzaAdiacente(Direzione.NORD, nuovaStanza);  // sovrascrive
	    assertEquals(nuovaStanza, stanza.getStanzaAdiacente(Direzione.NORD));
	}
	
	@Test
	void direzioneInesistente() {
	    assertNull(stanza.getStanzaAdiacente(Direzione.NORD)); //stanza non ha stanze adiacenti
	}

}

