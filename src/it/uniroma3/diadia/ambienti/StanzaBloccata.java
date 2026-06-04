package it.uniroma3.diadia.ambienti;



public class StanzaBloccata extends Stanza{

	Direzione direzioneBloccata;
	String oggettoMagico;

	public StanzaBloccata(String nome,Direzione direzione, String oggetto) {
		super(nome);
		this.direzioneBloccata=direzione;
		this.oggettoMagico=oggetto;
	}

	@Override
	public Stanza getStanzaAdiacente(Direzione direzione) {
		if (!this.hasAttrezzo(oggettoMagico) && direzione.equals(direzioneBloccata))
			return null;
		else
			return super.getStanzaAdiacente(direzione);

	}
	@Override
	public String getDescrizione() {
		if(this.hasAttrezzo(oggettoMagico)) 
			return super.getDescrizione();
		else {
			StringBuilder risultato = new StringBuilder(); //se lo avessi scritto nella forma: string risultato = "", ogni modifica porterebbe a creazione di un nuovo oggetto
			risultato.append(super.toString());
			risultato.append("\ndirezione bloccata: " + direzioneBloccata); 
			return risultato.toString();
		}
	}
}

