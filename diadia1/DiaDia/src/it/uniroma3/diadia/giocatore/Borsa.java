package it.uniroma3.diadia.giocatore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private List<Attrezzo> attrezzi;
	private int pesoMax;
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new ArrayList<>();
	}

	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		this.attrezzi.add(attrezzo);
		return true;
	}


	public int getPesoMax() {
		return pesoMax;
	}

	public Attrezzo getAttrezzo(String wanted) {
		for (Attrezzo a : this.attrezzi)
			if (a.getNome().equals(wanted))
				return a;

		return null;
	}


	public int getPeso() {
		int peso = 0;
		for (Attrezzo a : this.attrezzi)
			peso += a.getPeso();

		return peso;
	}



	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
	}


	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}
	//	Attrezzo a= null;
	//	this.attrezzi.remove(nomeAttrezzo); non funziona! rimuovo una stringa da lista di oggetti! ritorna null


	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		int index = this.attrezzi.indexOf(new Attrezzo(nomeAttrezzo,0));
		if(index!=-1) { //trovato
			return this.attrezzi.remove(index);
		}
		return null;


	}


	public List<Attrezzo> getContenutoOrdinatoPerPeso(){
		ComparatorePesoNome perPeso= new ComparatorePesoNome();
		final List <Attrezzo> inOrdine = new ArrayList<>(this.attrezzi);
		Collections.sort(inOrdine, perPeso); //!!!
		return inOrdine;

	}

	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		return new TreeSet<>(this.attrezzi);
	}

	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso(){
		ComparatorePesoNome perPeso= new ComparatorePesoNome();
		SortedSet <Attrezzo> inOrdine= new TreeSet<>(perPeso); //!!!
		inOrdine.addAll(this.attrezzi);
		return inOrdine;
	}
	
	public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		Map<Integer,Set<Attrezzo>> mappa = new HashMap<>();
		Set<Attrezzo> tmp;
		for(Attrezzo attrezzo:this.attrezzi) {
			tmp=mappa.get(attrezzo.getPeso());

			if(tmp==null) {
				tmp=new HashSet<>();
				mappa.put(attrezzo.getPeso(), tmp);}
			tmp.add(attrezzo);
		}

		return mappa;
	}

	public String guardaOrdinatoPerPeso() {
		StringBuilder s = new StringBuilder();

		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			Iterator<Attrezzo> it = getContenutoOrdinatoPerPeso().iterator();
			while(it.hasNext())
				s.append(it.next()+" ");
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}

	public String guardaOrdinatoPerNome() {
		StringBuilder s = new StringBuilder();

		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			Iterator<Attrezzo> it = getContenutoOrdinatoPerNome().iterator();
			while(it.hasNext())
				s.append(it.next()+" ");
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
	
//	public String guardaOrdinatoPerNome() {
//		StringBuilder s = new StringBuilder();
//
//		if (!this.isEmpty()) {
//			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
//		
//			for(Attrezzo a: getContenutoOrdinatoPerNome()) {
//				s.append(a + " ");
//			}
//		}
//		else
//			s.append("Borsa vuota");
//		return s.toString();
//	}




	public String toString() {
		StringBuilder s = new StringBuilder();

		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			Iterator<Attrezzo> it = this.attrezzi.iterator();
			while(it.hasNext())
				s.append(it.next()+" ");
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
}

