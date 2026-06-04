package it.uniroma3.diadia.personaggi;

import java.util.Map;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;

public class Strega extends AbstractPersonaggio {


private static final String MESSAGGIO_BUONO = "Sei nella stanza con più attrezzi tra quelle adiacenti, grazie per il saluto!";
private static final String MESSAGGIO_CATTIVO= "Sei nella stanza con meno attrezzi tra quelle adiacenti, non hai nemmeno salutato";
private static final String MESSAGGIO_RISATA= "HAHAHAH, il tuo oggetto ora è mio";


	public Strega(String nome, String presentaz) {
		super(nome,  presentaz);
	}

	@Override
	public String agisci(Partita partita) {
		String msg;
		Map<Direzione, Stanza> stanzeVicine=partita.getStanzaCorrente().getMapStanzeAdiacenti();
		Stanza stanzaConMenoAttrezzi=null;
		Stanza stanzaConPiuAttrezzi=null;
		for(Stanza s: stanzeVicine.values()) {
			if(stanzaConPiuAttrezzi==null || s.getNumeroAttrezzi()>stanzaConPiuAttrezzi.getNumeroAttrezzi())
				stanzaConPiuAttrezzi=s;
			if(stanzaConMenoAttrezzi==null || s.getNumeroAttrezzi()<stanzaConMenoAttrezzi.getNumeroAttrezzi())
				stanzaConMenoAttrezzi=s;}
		if (!this.haSalutato()) {
			partita.setStanzaCorrente(stanzaConMenoAttrezzi);
			msg = MESSAGGIO_CATTIVO;
		}
		else {
			partita.setStanzaCorrente(stanzaConPiuAttrezzi);
			msg = MESSAGGIO_BUONO;
		}
		return msg;
	}

	@Override
	public String riceviRegalo(Partita partita, String nomeAttrezzo) {
		partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
		return MESSAGGIO_RISATA;
	}
	}


