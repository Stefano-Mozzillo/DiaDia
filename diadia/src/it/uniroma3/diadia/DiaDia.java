package it.uniroma3.diadia;


import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
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
	
	static final private String[] elencoComandi = {"vai (direzione)", "aiuto", "fine", "posa (nome oggetto)", "prendi (nome oggetto)"};

	private Partita partita;
	private Giocatore giocatore;
	private IOConsole console;

	public DiaDia(IOConsole console) {
		this.console = console;
		this.partita = new Partita();
		this.giocatore = this.partita.getGiocatore();
	}

	public void gioca() {
		this.console.mostraMessaggio(MESSAGGIO_BENVENUTO);
		
		try(Scanner scannerDiLinee = new Scanner(System.in)){	
			String istruzione; 
			do		
				istruzione = scannerDiLinee.nextLine();
			while (!processaIstruzione(istruzione));
		}
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);
		
		if(istruzione==null)
			this.console.mostraMessaggio("Comando sconosciuto");
		
		else if (comandoDaEseguire.sconosciuto()) {
			this.console.mostraMessaggio("Cosa vuoi fare?");
			return false;
		}
		
		else if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine(); 
			return true;
		} else if (comandoDaEseguire.getNome().equals("vai"))
			this.vai(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("posa"))
			this.posaAttrezzo(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("prendi"))
			this.prendiAttrezzo(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto();
		else
			this.console.mostraMessaggio("Comando sconosciuto");
		
		if (this.partita.vinta()) {
			this.console.mostraMessaggio("Hai vinto!");
			return true;
		}
		else if (this.partita.isFinita()){
			this.console.mostraMessaggio("\nHai perso! Hai finito i cfu");
			this.console.mostraMessaggio("Grazie per aver giocato");
			return true;
		}
		else
			return false;
	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		System.out.print("Elenco comandi:\n");	//modificato da me
		for(int i=0; i< elencoComandi.length; i++) 
			this.console.mostraMessaggio(elencoComandi[i]+" ");  
		this.console.mostraMessaggio("");
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null) {
			this.console.mostraMessaggio("Dove vuoi andare ?");
			return;
		}
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null) {
			this.console.mostraMessaggio("\nDirezione inesistente\n");
		}
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.giocatore.getCfu();
			this.giocatore.setCfu(--cfu);
		}
		this.console.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}
	
	/**
	 * Prende un attrezzo dalla borsa e lo posa nella stanza corrente
	 * Se la borsa è vuota non fa nulla, se l'attrezzo è presente in borsa invoca la funzione removeAttrezzo
	 * e se è possibile aggiungerlo alla borsa lo aggiunge
	 * @param nomeAttrezzo
	 */
	private void posaAttrezzo(String nomeAttrezzo) {
		if (nomeAttrezzo == null) {
			this.console.mostraMessaggio("Che attrezzo vuoi posare?");
			return;
		}
		if (this.giocatore.getBorsa().isEmpty()) {
			this.console.mostraMessaggio("\nLa borsa è vuota!\n");
		}
		else if (this.giocatore.getBorsa().hasAttrezzo(nomeAttrezzo)){
			Attrezzo attrezzoPosato = this.giocatore.removeAttrezzo(nomeAttrezzo);
			if (this.partita.getStanzaCorrente().addAttrezzo(attrezzoPosato)) {
				this.console.mostraMessaggio("\nAttrezzo posato!");
				this.console.mostraMessaggio(this.giocatore.getBorsa().toString());
			}
			else {
				this.console.mostraMessaggio("\nNon è possibile posare l'attrezzo\n");
			}
		}
		else {
			this.console.mostraMessaggio("\nAttrezzo non presente in borsa\n");
		}
	}
	
	/**
	 * Prende un attrezzo dalla stanza e lo aggiunge alla borsa
	 * Se l'attrezzo è presente nella stanza lo aggiunge alla borsa tramite addAttrezzo
	 * e aumenta il peso della borsa
	 * Se non è possibile aggiungerlo alla borsa lo riposa nella stanza 
	 * @param nomeAttrezzo
	 */
	private void prendiAttrezzo(String nomeAttrezzo) {
		if (nomeAttrezzo == null) {
			this.console.mostraMessaggio("Che attrezzo vuoi prendere?");
			return;
		}
		Attrezzo attrezzo = this.partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
		if (attrezzo!=null) {
			if (this.giocatore.getBorsa().addAttrezzo(attrezzo)) {
				this.giocatore.getBorsa().getPeso();
				this.console.mostraMessaggio("\nAttrezzo preso!");
				this.console.mostraMessaggio(this.giocatore.getBorsa().toString());
			}
			else {
				this.partita.getStanzaCorrente().addAttrezzo(attrezzo);	//Per riaggiungerlo alla stanza se non è possibile inserirlo in borsa
				this.console.mostraMessaggio("\nNon è possibile recuperare l'attrezzo\n");
			}

		}
		else {
			this.console.mostraMessaggio("\nAttrezzo non presente nella stanza\n");		}
		
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		this.console.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		DiaDia gioco = new DiaDia(new IOConsole());
		gioco.gioca();
	}
}
