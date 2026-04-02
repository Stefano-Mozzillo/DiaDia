package it.uniroma3.diadia.ambienti;
import it.uniroma3.diadia.attrezzi.Attrezzo;



import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;




class stanzaTest {
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
    void testStanzaConUnAttrezzo_trovato() {
		assertTrue(stanzaConUnMartello.hasAttrezzo("martello"));
    }
    
    @Test
    void testStanzaConUnAttrezzo_introvabile() {
        assertFalse(stanzaConUnMartello.hasAttrezzo("introvabile"));
    }
    
    @Test
    void testMassimo10Attrezzi() {
    	final Stanza piena=new Stanza("piena");
    	for(int i=0; i<10;i++) {
    		assertTrue(piena.addAttrezzo(new Attrezzo("Attrezzo_" +i, i)));
    	}
    	assertFalse(piena.addAttrezzo(new Attrezzo("escluso",1)));
    }
 
//removeAttrezzo	
    @Test
    void testRimuoviAttrezzoDaStanzaConUnAttrezzo_trovato() {
    	assertTrue(stanzaConUnMartello.removeAttrezzo(martello));
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
		stanza.impostaStanzaAdiacente("nord", stanzaAdiacente);
		assertEquals(stanzaAdiacente,stanza.getStanzaAdiacente("nord"), "inserita stanza nord");
	}
	@Test
	void testImpostaStanzaAdiacenteSudCorrettamente() {
		stanza.impostaStanzaAdiacente("sud",stanzaAdiacente);
		assertEquals(stanzaAdiacente,stanza.getStanzaAdiacente("sud"));
	}
	
	@Test
	void testImpostaStanzaAdiacenteEstCorrettamente() {
		stanza.impostaStanzaAdiacente("est",stanzaAdiacente);
		assertEquals(stanzaAdiacente,stanza.getStanzaAdiacente("est"));
	}
	
	@Test
	void testImpostaStanzaAdiacenteOvestCorrettamente() {
		stanza.impostaStanzaAdiacente("ovest",stanzaAdiacente);
		assertEquals(stanzaAdiacente,stanza.getStanzaAdiacente("ovest"));
	}
	
	

}
