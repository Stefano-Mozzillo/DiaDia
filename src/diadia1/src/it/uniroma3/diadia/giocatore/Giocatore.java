package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Giocatore {
	private int cfu;
	static final private int CFU_INIZIALI = 3;
	private Borsa borsa;
	public Giocatore() {
		this.cfu = CFU_INIZIALI;
		this.borsa=new Borsa();
	}
	
	public int getCfu() {
		return this.cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;		
	}	
	public void decrementaCFU() {
		this.cfu--;
	}
	public boolean addAttrezzo(Attrezzo attrezzo) {
		return this.borsa.addAttrezzo(attrezzo);
	}
	/*public  Attrezzo removeAttrezzo(String attrezzo) {
		return this.borsa.removeAttrezzo(attrezzo);
	}*/
}



