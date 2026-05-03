package it.uniroma3.comando;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public interface Comando {
	
	public void esegui(Partita partita, IO console);
	
	public void setParametro(String parametro);
}

