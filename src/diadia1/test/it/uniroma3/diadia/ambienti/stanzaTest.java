package it.uniroma3.diadia.ambienti;
import it.uniroma3.diadia.attrezzi.Attrezzo;



import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;




class stanzaTest {
private Stanza stanza;
private Stanza cucina;
private Attrezzo spada;
private Attrezzo elmo;


	@BeforeEach
	void setUp() {
	stanza=new Stanza("stanza");
	cucina=new Stanza("cucina");
	spada= new Attrezzo("spada", 5);
	elmo= new Attrezzo("elmo",2);
	
	
	}
	

    @Test
    void testLeStanzeVuoteNonHannoAttrezzi() {
	
	assertFalse(new Stanza("vuota").hasAttrezzo("martello"));
    }
    @Test
    void testStanzaConUnAttrezzo_trovato() {
    	final Stanza piena=new Stanza("piena");
        assertFalse(piena.hasAttrezzo("martello"));
        piena.addAttrezzo(new Attrezzo("martello",10));
        assertTrue(piena.hasAttrezzo("martello"));
    }
    
    @Test
    void testStanzaConUnAttrezzo_introvabile() {
    	final Stanza piena=new Stanza("piena");
        piena.addAttrezzo(new Attrezzo("martello",10));
        assertFalse(piena.hasAttrezzo("introvabile"));
    }
    @Test
    void testMassimo10Attrezzi() {
    	final Stanza piena=new Stanza("piena");	
    	for(int i=0; i<10;i++) {
    		assertTrue(piena.addAttrezzo(new Attrezzo("Attrezzo_" +i, i)));
    	}
    	assertFalse(piena.addAttrezzo(new Attrezzo("escluso",1)));
    }
	
    @Test
    void testRimuoviAttrezzoDaStanzaConUnAttrezzo_trovato() {
    	final Stanza piena=new Stanza("piena");
    	piena.addAttrezzo(elmo);
    	assertTrue(piena.removeAttrezzo(elmo));
    }
    
    @Test
    void testRimuoviAttrezzo_introvabile() {
    	final Stanza vuota=new Stanza("vuota");
    	assertFalse(vuota.removeAttrezzo(elmo));
    }
    
    
    
    @Test
	public void testImpostaStanzaAdiacenteNordCorrettamente() {
		stanza.impostaStanzaAdiacente("nord", cucina);
		assertEquals(cucina,stanza.getStanzaAdiacente("nord"), "inserita stanza nord");
	}
	@Test
	public void testImpostaStanzaAdiacenteSudCorrettamente() {
		stanza.impostaStanzaAdiacente("sud",cucina);
		assertEquals(cucina,stanza.getStanzaAdiacente("sud"));
	}
	
	@Test
	public void testImpostaStanzaAdiacenteEstCorrettamente() {
		stanza.impostaStanzaAdiacente("est",cucina);
		assertEquals(cucina,stanza.getStanzaAdiacente("est"));
	}
	
	@Test
	public void testImpostaStanzaAdiacenteOvestCorrettamente() {
		stanza.impostaStanzaAdiacente("ovest",cucina);
		assertEquals(cucina,stanza.getStanzaAdiacente("ovest"));
	}
	
	

}
