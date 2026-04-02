package it.uniroma3.diadia1;


import java.util.Scanner;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

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
	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "posa", "prendi"};

	private Partita partita;
	private Giocatore giocatore;

	public DiaDia() {
		this.partita = new Partita();
		this.giocatore = this.partita.getGiocatore();
	}

	public void gioca() {
		String istruzione; 
		Scanner scannerDiLinee;

		System.out.println(MESSAGGIO_BENVENUTO);
		scannerDiLinee = new Scanner(System.in);		
		do		
			istruzione = scannerDiLinee.nextLine();
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);

		if (comandoDaEseguire.getNome().equals("fine") || partita.isFinita()) {
			this.fine(); 
			return true;
		} else if (comandoDaEseguire.getNome().equals("vai"))
			this.vai(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto();
		else if (comandoDaEseguire.getNome().equals("posa"))
			this.posaAttrezzo(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("prendi"))
			this.prendiAttrezzo(comandoDaEseguire.getParametro());
		else
			System.out.println("Comando sconosciuto");
		if (this.partita.vinta()) {
			System.out.println("Hai vinto!");
			return true;}
		else if (this.partita.isFinita()){
			System.out.println("");
			System.out.println("Hai perso! Hai finito i cfu.");
			System.out.println("Grazie per aver giocato");
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
		for(int i=0; i< elencoComandi.length; i++) 
			System.out.print(elencoComandi[i]+" ");
		System.out.println();
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null) {
			System.out.println("Dove vuoi andare?");
			return;}
		Stanza prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null) {
			System.out.println("Direzione inesistente");
		    return;}
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			this.giocatore.decrementaCFU();
			; //CORREZIONE: in java cfu-- restituisce 20, decrementando la variabile locale
		}                               //settando a 20 i cfu.
		System.out.println(partita.getStanzaCorrente().getDescrizione());
	}
	
	private void posaAttrezzo(String nomeAttrezzo) {
		if (nomeAttrezzo == null) {
			System.out.println("Che attrezzo vuoi posare?");
			return;
		}
		if (this.giocatore.getBorsa().isEmpty()) {
			System.out.println("");
			System.out.println("La borsa è vuota!");
			System.out.println("");
		}
		else if (this.giocatore.getBorsa().hasAttrezzo(nomeAttrezzo)){
			Attrezzo attrezzoPosato = this.giocatore.removeAttrezzo(nomeAttrezzo);
			if (this.partita.getStanzaCorrente().addAttrezzo(attrezzoPosato)) {
				System.out.println("");
				System.out.println("Attrezzo posato!");
				System.out.println(this.giocatore.getBorsa().toString());
			}
			else {
				System.out.println("");
				System.out.println("Non è possibile posare l'attrezzo, la stanza è piena!");
				System.out.println("");
			}
		}
		else {
			System.out.println("");
			System.out.println("Attrezzo non presente in borsa!");
			System.out.println("");
		}
	
	
		}
	
	
	private void prendiAttrezzo(String nomeAttrezzo) {
		if (nomeAttrezzo == null) {
			System.out.println("Che attrezzo vuoi prendere?");
			return;
		}
		Attrezzo attrezzo = this.partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
		if (attrezzo!=null) {
			if (this.giocatore.getBorsa().addAttrezzo(attrezzo)) {
				System.out.println("");
				System.out.println("Attrezzo preso!");
				System.out.println(this.giocatore.getBorsa().toString());
			}
			else {
				this.partita.getStanzaCorrente().addAttrezzo(attrezzo);	//Per riaggiungerlo alla stanza se non è possibile inserirlo in borsa
				System.out.println("");
				System.out.println("Non è possibile recuperare l'attrezzo!");
				System.out.println("");
			}

		}
		else {
			System.out.println("");
			System.out.println("Attrezzo non presente nella stanza");
			System.out.println("");
		}
		
	}


	/**
	 * Comando "Fine".
	 */
	private void fine() {
		System.out.println("Grazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		DiaDia gioco = new DiaDia();
		gioco.gioca();
		
	}
}