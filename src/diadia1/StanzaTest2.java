package diadia1;

public class StanzaTest2 {
	public static void main(String[] argc) {
		Stanza bar = new Stanza("Bar");
		Stanza mensa = new Stanza("Mensa");
		Attrezzo tazzina= new Attrezzo("tazzina",1);
		Attrezzo piatto= new Attrezzo("piatto",2);
		bar.impostaStanzaAdiacente("nord", mensa);
		mensa.impostaStanzaAdiacente("sud", bar);
		bar.addAttrezzo(tazzina);
		mensa.addAttrezzo(piatto);
		System.out.println(bar.getStanzaAdiacente("nord").getAttrezzo("piatto").getNome());
		System.out.println(bar.getStanzaAdiacente("nord").getAttrezzo("piatto").getPeso());
		System.out.println(mensa.getStanzaAdiacente("sud").getAttrezzo("tazzina").getNome());
		System.out.println(mensa.getStanzaAdiacente("sud").getAttrezzo("tazzina").getPeso());
	}
}
