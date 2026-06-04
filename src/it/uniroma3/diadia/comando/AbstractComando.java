package it.uniroma3.diadia.comando;



import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public abstract class AbstractComando {
	

	public abstract  void esegui(Partita partita, IO console);

	public void setParametro(String parametro) {}
}
