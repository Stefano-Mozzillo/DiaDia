package it.uniroma3.diadia.comando;

public interface FabbricaDiComandi  {
	public AbstractComando costruisciComando(String istruzione)
		throws Exception;
	
}
