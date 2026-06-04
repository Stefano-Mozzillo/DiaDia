package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends AbstractComando {
	/**
	 * Stampa informazioni di aiuto.
	 */
	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "posa", "prendi", "regala", "saluta", "interagisci"};

	@Override
	public void esegui(Partita partita, IO console) {
		for(String s: elencoComandi) 
			console.mostraMessaggio(s+" ");

	}

	@Override
	public void setParametro(String parametro) {

	}

	

}