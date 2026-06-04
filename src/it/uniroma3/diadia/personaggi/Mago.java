package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class Mago extends AbstractPersonaggio {
	private static final String MESSAGGIO_DONO = "Sei un vero simpaticone, " +
			"con una mia magica azione, troverai un nuovo oggetto " +
			"per il tuo borsone!";
	private static final String MESSAGGIO_SCUSE = "Mi spiace, ma non ho piu' nulla...";
	private static final String MESSAGGIO_ATTREZZO_ALLEGGERITO = "il peso del tuo oggetto è magicamente dimezzato! ";
	
	private Attrezzo attrezzo = new Attrezzo("spada", 2);
	public Mago(String nome, String presentaz) {
		super(nome, presentaz);
	}
	@Override
	public String agisci(Partita partita) {
		String msg;
		if (this.attrezzo!=null) {
			partita.getStanzaCorrente().addAttrezzo(this.attrezzo);
			this.attrezzo = null;
			msg = MESSAGGIO_DONO;
		}
		else {
			msg = MESSAGGIO_SCUSE;
		}
		return msg;
	}
	@Override
	public String riceviRegalo(Partita partita, String nomeAttrezzo) {
		Attrezzo attrezzoMagico;
		Borsa borsa = partita.getGiocatore().getBorsa();
		attrezzoMagico = borsa.removeAttrezzo(nomeAttrezzo);
		int pesoDiviso2= attrezzoMagico.getPeso() / 2;
		Attrezzo nuovoAttrezzo= new Attrezzo(nomeAttrezzo, pesoDiviso2);
		partita.getStanzaCorrente().addAttrezzo(nuovoAttrezzo);
		return MESSAGGIO_ATTREZZO_ALLEGGERITO;
		

	}
	}

	
