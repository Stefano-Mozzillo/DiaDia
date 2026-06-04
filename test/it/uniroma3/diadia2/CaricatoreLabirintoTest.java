package it.uniroma3.diadia2;

import static org.junit.Assert.assertEquals;

import java.io.StringReader;


import org.junit.Test;

import it.uniroma3.diadia.CaricatoreLabirinto;
import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class CaricatoreLabirintoTest {
	
	
	
	
	public CaricatoreLabirinto caricaLabirinto(String labirinto) {
		StringReader in = new StringReader(labirinto);
		CaricatoreLabirinto c = new CaricatoreLabirinto(in);
		try {
			c.carica();
		} catch (FormatoFileNonValidoException e) {
			e.printStackTrace();
		}
		return c;
	}
	
	@Test
	public void TestMonolocaleSenzaAttrezzi() {
		String labirinto= "Stanze:N10,N11\n"
				+ "Stanze buie:\n"
				+ "Stanze magiche:\n"
				+ "Stanze bloccate:\n"
				+ "Inizio:N10\n"
				+ "Vincente:N10\n"
				+ "Attrezzi:\n"
				+ "Uscite:\n"
				+ "Personaggi: ";
		
		CaricatoreLabirinto c = caricaLabirinto(labirinto);
		assertEquals(c.getStanzaIniziale().getNome(), "N10");
		assertEquals(c.getStanzaVincente().getNome(), "N10");
	}
	
	@Test
	public void TestBilocaleSenzaAttrezzi() {
		String labirinto= "Stanze:N10,N11\n"
				+ "Stanze buie:\n"
				+ "Stanze magiche:\n"
				+ "Stanze bloccate:\n"
				+ "Inizio:N10\n"
				+ "Vincente:N10\n"
				+ "Attrezzi:\n"
				+ "Uscite:\n"
				+ "Personaggi: ";
		CaricatoreLabirinto c = caricaLabirinto(labirinto);
		assertEquals(c.getStanzaIniziale().getNome(), "N10");
		assertEquals(c.getStanzaVincente().getNome(), "N10");
	}
	
	@Test
	public void TestMonolocaleConAttrezzo() {
		String labirinto= "Stanze:N10,N11\n"
				+ "Stanze buie:\n"
				+ "Stanze magiche:\n"
				+ "Stanze bloccate:\n"
				+ "Inizio:N10\n"
				+ "Vincente:N10\n"
				+ "Attrezzi: pinza 2 N10\n"
				+ "Uscite:\n"
				+ "Personaggi: ";
				
		CaricatoreLabirinto c = caricaLabirinto(labirinto);
		assertEquals(c.getStanzaIniziale().getNome(), "N10");
		assertEquals(c.getStanzaVincente().getNome(), "N10");
		assertEquals(c.getStanzaIniziale().getAttrezzo("pinza"),new Attrezzo("pinza", 2));
	}
	@Test
	public void TestBilocaleConAttrezzi() {
		String labirinto= "Stanze:N10,N11\n"
				+ "Stanze buie:\n"
				+ "Stanze magiche:\n"
				+ "Stanze bloccate:\n"
				+ "Inizio:N10\n"
				+ "Vincente:N11\n"
				+ "Attrezzi: pinza 2 N10, martello 4 N11\n"
				+ "Uscite:\n"
				+ "Personaggi: ";
				
		CaricatoreLabirinto c = caricaLabirinto(labirinto);
		assertEquals(c.getStanzaIniziale().getNome(), "N10");
		assertEquals(c.getStanzaVincente().getNome(), "N11");
		assertEquals(c.getStanzaIniziale().getAttrezzo("pinza"), new Attrezzo("pinza",2));
		assertEquals(c.getStanzaVincente().getAttrezzo("martello"), new Attrezzo("martello",2));
		
		
		
	}
	
	@Test
	public void TestBilocaleConAttrezziEAdiacenza() {
		String labirinto= "Stanze:N10,N11\n"
				+ "Stanze buie:\n"
				+ "Stanze magiche:\n"
				+ "Stanze bloccate:\n"
				+ "Inizio:N10\n"
				+ "Vincente:N11\n"
				+ "Attrezzi: pinza 2 N10, martello 4 N11\n"
				+ "Uscite: N10 nord N11\n"
				+ "Personaggi: ";
		CaricatoreLabirinto c = caricaLabirinto(labirinto);
		assertEquals(c.getStanzaIniziale().getStanzaAdiacente(Direzione.NORD).getNome(), "N11");
		
		
	}
	

}
