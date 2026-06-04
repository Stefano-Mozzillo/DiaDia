package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

public class Labirinto {


	
	private Stanza stanzaVincente;
	private Stanza stanzaIniziale;
	
	
	public static class LabirintoBuilder   {


		private Map <String, Stanza> nome2Stanza; // = new HashMap<>();
		private Stanza ultimaStanzaAggiunta;
		private Labirinto labirinto;
		


		private LabirintoBuilder() {
			this.nome2Stanza = new HashMap<>();
			this.labirinto= new Labirinto();
		}
		
		

		public LabirintoBuilder addStanzaIniziale(String stanza) {
			Stanza stanzaIniziale = new Stanza(stanza);
			ultimaStanzaAggiunta=stanzaIniziale;
			nome2Stanza.put(stanza, stanzaIniziale);
			this.labirinto.setStanzaIniziale(stanzaIniziale);
			return this;
		}

		public LabirintoBuilder addStanzaVincente(String stanza) {
			Stanza stanzaVincente= new Stanza(stanza);
			ultimaStanzaAggiunta= stanzaVincente;
			nome2Stanza.put(stanza, stanzaVincente);
			this.labirinto.setStanzaVincente(stanzaVincente);
			return this;
		}

		public LabirintoBuilder addStanza(String stanza) {
			Stanza stanzaGenerica= new Stanza(stanza);
			ultimaStanzaAggiunta= stanzaGenerica;
			nome2Stanza.put(stanza, stanzaGenerica);
			return this;
		}
		public LabirintoBuilder addStanzaMagica(String stanza) {
			Stanza stanzaGenerica= new StanzaMagica(stanza);
			ultimaStanzaAggiunta= stanzaGenerica;
			nome2Stanza.put(stanza, stanzaGenerica);
			return this;
		}
		
		public LabirintoBuilder addStanzaBloccata(String stanza, Direzione direzione, String oggettoMagico) {
			Stanza stanzaGenerica= new StanzaBloccata(stanza, direzione, oggettoMagico);
			ultimaStanzaAggiunta= stanzaGenerica;
			nome2Stanza.put(stanza, stanzaGenerica);
			return this;
		}
		
		public LabirintoBuilder addStanzaBuia(String nome) {
			Stanza stanzaGenerica= new StanzaBuia(nome);
			ultimaStanzaAggiunta= stanzaGenerica;
			nome2Stanza.put(nome, stanzaGenerica);
			return this;
		}


		public LabirintoBuilder addAdiacenza(String stanza, String stanzaAdiacente, Direzione direzione) {
			Stanza origine= nome2Stanza.get(stanza);
			Stanza adiacente= nome2Stanza.get(stanzaAdiacente);
			if(origine !=null && adiacente!=null )
				origine.impostaStanzaAdiacente(direzione, adiacente);
			return this;
		}

		public LabirintoBuilder addAttrezzo(String nomeAttrezzo, int peso) {
			if (this.ultimaStanzaAggiunta != null) {
				Attrezzo a = new Attrezzo(nomeAttrezzo, peso); //metodo di Stanza
				this.ultimaStanzaAggiunta.addAttrezzo(a); 
			}
			return this; 
		}
		
		public LabirintoBuilder addMago(String nome, String presentazione) {
			if (this.ultimaStanzaAggiunta != null) {
				AbstractPersonaggio m = new Mago(nome, presentazione);
				this.ultimaStanzaAggiunta.addPersonaggio(m);
			}
			return this; 
		}
		
		public LabirintoBuilder addStrega(String nome, String presentazione) {
			if (this.ultimaStanzaAggiunta != null) {
				AbstractPersonaggio s = new Strega(nome, presentazione);
				this.ultimaStanzaAggiunta.addPersonaggio(s);
			}
			return this; 
		}
		
		public LabirintoBuilder addCane(String nome, String pres) {
			if (this.ultimaStanzaAggiunta != null) {
				AbstractPersonaggio c = new Cane(nome, pres );
				this.ultimaStanzaAggiunta.addPersonaggio(c);
			}
			return this; 
		}
		
		public Labirinto getLabirinto() {
			return this.labirinto; 
		}
		
		
		public  Map <String, Stanza> getListaStanze() {
			return nome2Stanza;
		}

		

	}

	public static LabirintoBuilder newBuilder(){
		return new LabirintoBuilder();
	}

    
	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}
	public Stanza getStanzaIniziale() {
		return stanzaIniziale;
	}
	
	public void setStanzaVincente(Stanza stanzaWin) {
		this.stanzaVincente= stanzaWin;
	}
	public void setStanzaIniziale(Stanza stanzaStart) {
		this.stanzaIniziale= stanzaStart;
	}

	

}

