package it.uniroma3.diadia;




import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.comando.Comando;
import it.uniroma3.diadia.comando.FabbricaDiComandiFisarmonica;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il metodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";


	private Partita partita;
	private IO console;


	public DiaDia(Labirinto labirinto, IO console) {
		this.console = console; //altrimenti nullPointerException
		this.partita = new Partita(labirinto);
	}

	public void gioca() {
		String istruzione; 
		console.mostraMessaggio(MESSAGGIO_BENVENUTO);		

		do	{	
			istruzione = console.leggiRiga();}
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire;
		FabbricaDiComandiFisarmonica factory = new FabbricaDiComandiFisarmonica();
		comandoDaEseguire = factory.costruisciComando(istruzione);
		comandoDaEseguire.esegui(this.partita, console);
		if (this.partita.vinta())
			console.mostraMessaggio("Hai vinto!");
		if (!this.partita.giocatoreIsVivo())
			console.mostraMessaggio("Hai esaurito i CFU...");
		return this.partita.isFinita();
	}
	public Partita getPartita() {
		return this.partita;
	}

	// implementazioni dei comandi dell'utente:
	

	public static void main(String[] argc) {
		IO io = new IOConsole();
		
		Labirinto labirinto = new LabirintoBuilder()
				.addStanzaIniziale("Atrio")
				.addAttrezzo("osso", 1) 
				.addStanzaVincente("Biblioteca")
				.addStanzaMagica("Aula N11")
				.addStanzaBloccata("Aula N10", "nord", "passepartout")
				.addAttrezzo("lanterna", 3)    
				.addAttrezzo("passepartout", 1) 
				.addStanza("Laboratorio Campus")
				.addStanzaBuia("Bar")
				.addStanza("Mensa")
				.addAdiacenza("Atrio", "Biblioteca", "nord")
				.addAdiacenza("Atrio", "Aula N11", "est")
				.addAdiacenza("Atrio", "Aula N10", "sud")
				.addAdiacenza("Atrio", "Laboratorio Campus", "ovest")
				.addAdiacenza("Aula N11", "Laboratorio Campus", "est")
				.addAdiacenza("Aula N11", "Atrio", "ovest")
				.addAdiacenza("Aula N10", "Atrio", "nord")
				.addAdiacenza("Aula N10", "Aula N11", "est")
				.addAdiacenza("Aula N10", "Laboratorio Campus", "ovest")	
				.addAdiacenza("Laboratorio Campus", "Atrio", "est")
				.addAdiacenza("Laboratorio Campus", "Aula N11", "ovest")	
				.addAdiacenza("Biblioteca", "Atrio", "sud")
				.addAdiacenza("Bar", "Mensa", "nord")
				.addAdiacenza("Mensa", "Bar", "sud")
				.getLabirinto();
				
		DiaDia gioco = new DiaDia(labirinto, io);
		gioco.gioca();
	}
}


