package diadia1;

public class StanzaTest1 {
	


public static void main(String[] argc) {
	Stanza bar = new Stanza("Bar");
	Stanza mensa = new Stanza("Mensa");
	bar.impostaStanzaAdiacente("nord", mensa);
	mensa.impostaStanzaAdiacente("sud", bar);
	System.out.println(bar.getStanzaAdiacente("nord"));
	System.out.println(mensa.getStanzaAdiacente("sud"));
}}
