package it.uniroma3.diadia.ambienti;



public class StanzaBuia extends Stanza {
	final static private String ATTREZZO_MAGICO = "lanterna";
	private String attrezzoMagico;
	
	public StanzaBuia(String nome) {
		this(nome ,ATTREZZO_MAGICO);
	}
	public StanzaBuia(String nome, String attrezzo) {
		super(nome);
		this.attrezzoMagico=attrezzo;}
	
	@Override
	public String getDescrizione() {
		if(super.hasAttrezzo(attrezzoMagico)) 
			return super.toString();
		else
			return "qui c'è buio pesto";
	}
}
