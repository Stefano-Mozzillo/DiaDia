package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class BorsaTest {

	private Borsa vuota;
	private Borsa singoletto;
	private Attrezzo martello;
	private Attrezzo martello2;
	private Borsa coppiaStessoPeso;
//SetUp	
	@BeforeEach
	void setUp() {
		this.vuota = new Borsa();
		this.singoletto=new Borsa();
		this.coppiaStessoPeso = new Borsa();
		this.martello = new Attrezzo("martello",4);
		this.singoletto.addAttrezzo(martello);
		this.martello2=new Attrezzo("martello2", 4);
		this.coppiaStessoPeso.addAttrezzo(martello);
		this.coppiaStessoPeso.addAttrezzo(martello2);	

	}
//getPesoMax()	
	@Test
	void testGetPesoMax() {
		assertEquals(vuota.getPesoMax(),10);
	}
//addAttrezzo()
	@Test
	void testAddAttrezzoCorrettamente() {
		assertTrue(vuota.addAttrezzo(martello));
		assertTrue(vuota.hasAttrezzo("martello"));
	}
	
	@Test
	void testAddAttrezzoTroppoPesante() {
		assertFalse(vuota.addAttrezzo(new Attrezzo("spada", 11)));
		assertFalse(vuota.hasAttrezzo("spada"));
	}
	
//	@Test
//	void testAddAttrezzoConBorsaPiena() {
//		for (int i=0;i<10;i++) {
//		borsa.addAttrezzo(new Attrezzo("Attrezzo_"+i,0));
//		}
//		assertFalse(borsa.addAttrezzo(new Attrezzo("piuma", 0)));
//		assertFalse(borsa.hasAttrezzo("piuma"));
//	} test non più utile in quanto arrayList non ha limiti di capienza!!!
  

	
	//getAttrezzo()
	
	@Test
	void getAttrezzo_trovato() {
		assertEquals(singoletto.getAttrezzo("martello"), martello);
	}
	
	@Test
	void getAttrezzo_introvabile() {
		assertNull(vuota.getAttrezzo("introvabile"));
	}

//hasAttrezzo
	@Test
    void testBorsaConUnAttrezzo_trovato() {
		assertTrue(singoletto.hasAttrezzo("martello"));
    }
    
	@Test
    void testBorsaConUnAttrezzo_introvabile() {
		assertFalse(vuota.hasAttrezzo("martello"));
    }
//removeAttrezzo()	
	
	@Test
    void testRimuoviAttrezzoDaBorsaConUnAttrezzo_trovato() {
	    assertEquals(singoletto.removeAttrezzo("martello"),martello);
	    assertFalse(singoletto.hasAttrezzo("martello"));
	    }
	@Test
	void testRimuoviAttrezzoDaBorsaConUnAttrezzo_introvabile() {
	    assertNull(vuota.removeAttrezzo("martello"));
	    }
	
	
	//ContenutoOrdinatoPerPeso
	@Test
	void testGetContenutoOrdinatoPerPeso_Vuota() {
		assertEquals(Collections.emptyList(), this.vuota.getContenutoOrdinatoPerPeso());
	}
	
	@Test
	void testGetContenutoOrdinatoPerPeso_Singoletto() {
		assertEquals(Collections.singletonList(this.martello), this.singoletto.getContenutoOrdinatoPerPeso());
	}
	
	//ContenutoOrdinatoPerNome
	@Test
	void testGetContenutoOrdinatoPerNome_Vuota() {
		assertEquals(Collections.emptySortedSet(), this.vuota.getContenutoOrdinatoPerNome());
	}
	@Test
	void getSortedSetOrdinatoPerPeso_AttrezziConStessoPesoRimangonoDistinti() {
		assertEquals(this.coppiaStessoPeso.getSortedSetOrdinatoPerPeso().size(),2);
}
}


//public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso(){
//	ComparatorePesoNome perPeso= new ComparatorePesoNome();
//	SortedSet <Attrezzo> inOrdine= new TreeSet<>(perPeso);
//	inOrdine.addAll(this.attrezzi);
//	return inOrdine;
//}
