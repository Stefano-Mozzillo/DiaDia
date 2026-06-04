package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio {
	private static final String MESSAGGIO_CANE = "WOOF WOOF, perdi un CFU";
	private static final String MESSAGGIO_DONO = "Grazie per l'osso, ecco a te un nuovo oggetto";
	private static final String MESSAGGIO_SCUSE = "Mi spiace, ma non ho piu' nulla...";

	private static final Attrezzo attrezzoMagico = new Attrezzo("tesoro", 1);
	private  Attrezzo attrezzo;
	
	public Cane(String nome, String presentaz) {
		super(nome, presentaz);
		this.attrezzo=attrezzoMagico;
	}

	@Override
	public String agisci(Partita partita) {
		String msg;
		partita.getGiocatore().decrementaCFU();
		msg = MESSAGGIO_CANE;
		return msg;}

	@Override //!
	public String riceviRegalo(Partita partita, String attrezzoRicevuto) {
		String msg;
		if(attrezzoRicevuto.equals("osso") && this.attrezzo!=null) {
			partita.getStanzaCorrente().addAttrezzo(this.attrezzo);
			partita.getGiocatore().getBorsa().removeAttrezzo(attrezzoRicevuto);
			this.attrezzo=null;
			msg= MESSAGGIO_DONO;}
		else if(attrezzoRicevuto.equals("osso") && this.attrezzo==null)
			msg=MESSAGGIO_SCUSE;
		else {
			this.agisci(partita);
			msg=MESSAGGIO_CANE;}

		return msg;
	}


}
