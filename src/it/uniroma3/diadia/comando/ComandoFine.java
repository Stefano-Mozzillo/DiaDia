package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoFine extends AbstractComando  {

	
	@Override
	public void esegui(Partita partita, IO console) {
		console.mostraMessaggio("Grazie di aver giocato!");
		
	}


}
