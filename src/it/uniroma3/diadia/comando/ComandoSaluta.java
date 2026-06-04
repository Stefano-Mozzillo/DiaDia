package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoSaluta extends AbstractComando {
	private static final String MESSAGGIO_CHI = "Chi dovrei salutare?...";
	private String messaggio;
	@Override
	public void esegui(Partita partita, IO console) {
		String msg;
		AbstractPersonaggio personaggio;
		personaggio=partita.getStanzaCorrente().getPersonaggio();
		if(personaggio==null)
			console.mostraMessaggio(MESSAGGIO_CHI);
		else {
			msg = personaggio.saluta();
			console.mostraMessaggio(msg);
			}
		
		}
	
	public String getMessaggio() {
		return this.messaggio;
	}
}	
	

