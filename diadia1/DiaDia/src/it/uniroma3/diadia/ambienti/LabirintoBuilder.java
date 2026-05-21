package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilder extends Labirinto  {


	private Map <String, Stanza> nome2Stanza; // = new HashMap<>();
	private Stanza ultimaStanzaAggiunta;


	public LabirintoBuilder() {
		this.nome2Stanza = new HashMap<>();
	}

	public LabirintoBuilder addStanzaIniziale(String stanza) {
		Stanza stanzaIniziale = new Stanza(stanza);
		ultimaStanzaAggiunta=stanzaIniziale;
		nome2Stanza.put(stanza, stanzaIniziale);
		this.setStanzaIniziale(stanzaIniziale);
		return this;
	}

	public LabirintoBuilder addStanzaVincente(String stanza) {
		Stanza stanzaVincente= new Stanza(stanza);
		ultimaStanzaAggiunta= stanzaVincente;
		nome2Stanza.put(stanza, stanzaVincente);
		this.setStanzaVincente(stanzaVincente);
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
	
	public LabirintoBuilder addStanzaBloccata(String stanza, String direzione, String oggettoMagico) {
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


	public LabirintoBuilder addAdiacenza(String stanza, String stanzaAdiacente, String direzione) {
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
	
	public Labirinto getLabirinto() {
		return this;
	}
	public  Map <String, Stanza> getListaStanze() {
		return nome2Stanza;
	}

	

}













