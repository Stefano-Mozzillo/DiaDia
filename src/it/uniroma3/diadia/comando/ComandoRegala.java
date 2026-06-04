package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoRegala extends AbstractComando {

	private static final String MESSAGGIO_A_CHI = "A chi dovrei regalarlo?";
	private static final String MESSAGGIO_COSA = "Cosa dovrei regalare?";
	private String messaggio;
	private String nomeAttrezzo;
	@Override
	public void esegui(Partita partita, IO console) {
		AbstractPersonaggio personaggio;
		Borsa borsa;
		personaggio=partita.getStanzaCorrente().getPersonaggio();
		borsa=partita.getGiocatore().getBorsa();
		if(nomeAttrezzo==null)
			console.mostraMessaggio(MESSAGGIO_COSA);
		else if(personaggio==null)
			console.mostraMessaggio(MESSAGGIO_A_CHI);
		
		else if(borsa.hasAttrezzo(nomeAttrezzo)) {
			messaggio = personaggio.riceviRegalo(partita, nomeAttrezzo);
			console.mostraMessaggio(messaggio);
			}
		else 
			console.mostraMessaggio("\nOggetto non presente nella borsa! ");

	}



@Override
public void setParametro(String parametro) {
	this.nomeAttrezzo=parametro;
}
}
