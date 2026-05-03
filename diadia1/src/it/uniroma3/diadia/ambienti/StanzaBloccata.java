package it.uniroma3.diadia.ambienti;



public class StanzaBloccata extends Stanza{

	String direzioneBloccata;
	String oggettoMagico;

	public StanzaBloccata(String nome,String direzione, String oggetto) {
		super(nome);
		this.direzioneBloccata=direzione;
		this.oggettoMagico=oggetto;
	}

	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		if (!super.hasAttrezzo(oggettoMagico) && direzione.equals(direzioneBloccata))
			return null;
		else
			return super.getStanzaAdiacente(direzione);

	}
	@Override
	public String getDescrizione() {
		if(super.hasAttrezzo(oggettoMagico))
			return super.getDescrizione();
		else {
			StringBuilder risultato = new StringBuilder(); //se lo avessi scritto nella forma: string risultato = "", ogni modifica porterebbe a creazione di un nuovo oggetto
			risultato.append(super.toString());
			risultato.append("\ndirezione bloccata: " + direzioneBloccata); 
			return risultato.toString();
		}
	}
}

