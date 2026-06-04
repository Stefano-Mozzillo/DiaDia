package it.uniroma3.diadia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IOSimulator implements IO {

	private List<String> righeDaInserire;
	private Iterator<String> iteratoreLeggi;
	private List<String> righeProdotte;
	
	
	public IOSimulator(List<String> comandi) {
		this.righeDaInserire=comandi;	
		iteratoreLeggi= righeDaInserire.iterator();
		righeProdotte= new ArrayList<>();
		
	}
	
	@Override
	public void mostraMessaggio(String messaggio) {
		System.out.println("\n" + messaggio);
		righeProdotte.add(messaggio);
	}

	@Override
	public String leggiRiga() {
		String comando=null;
		if(iteratoreLeggi.hasNext())
			comando=iteratoreLeggi.next();
		System.out.println("\n>>> comando inserito: " + comando);
		return comando;
		
	}
	
	public List<String> getRigheProdotte() {
	    return this.righeProdotte;
	}

	public List<String> getRigheDaInserire() {
	    return this.righeDaInserire;
	}
}
