package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoGuarda extends AbstractComando{

	@Override
	public void esegui(Partita partita, IO console) {
		console.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		console.mostraMessaggio(partita.getGiocatore().getBorsa().guardaOrdinatoPerPeso());
		console.mostraMessaggio("CFU:" + partita.getGiocatore().getCfu());
		
	}


}
