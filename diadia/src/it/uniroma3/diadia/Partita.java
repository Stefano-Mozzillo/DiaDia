package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {

	//static final private int CFU_INIZIALI = 20;		//inserito in giocatore

	private Labirinto labirinto;	//modificato
	private Giocatore giocatore;
	private boolean finita;
	//private int cfu;		//inserito in giocatore
	
	public Partita(){
		this.labirinto = new Labirinto();	//modificato
		this.giocatore = new Giocatore();
		this.finita = false;
		//this.cfu = CFU_INIZIALI;		//inserito in giocatore
	}
	
	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		//System.out.println("Stanza corrente: " + this.labirinto.getStanzaCorrente().getNome());
	    //System.out.println("Stanza finale: " + this.labirinto.getStanzaFinale().getNome());
		return this.labirinto.getStanzaCorrente()== this.labirinto.getStanzaFinale();		//modificato
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || (this.giocatore.getCfu() == 0);
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}
	
	/**
	 * Restituisce il labirinto della classe partita alla classe DiaDia
	 * Per avere un solo labirinto
	 * @return riferimento a questo labirinto
	 */
	public Labirinto getLabirinto() {	//Aggiunto
		return this.labirinto;
	}
	
	/**
	 * Restituisce il giocatore della classe partita alla classe DiaDia
	 * Per avere un solo giocatore
	 * @return riferimento a questo giocatore
	 */
	public Giocatore getGiocatore() {	//aggiunto
		return this.giocatore;
	}
	
	//Inserite in Giocatore
	/*public int getCfu() {
		return this.cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;		
	}	*/
}
