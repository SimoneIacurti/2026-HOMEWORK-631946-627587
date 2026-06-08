package it.uniroma3.diadia.giocatore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import it.uniroma3.diadia.Proprieta;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.attrezzi.ComparatoreNome;
import it.uniroma3.diadia.attrezzi.ComparatorePeso;

public class Borsa {
	private ArrayList<Attrezzo> attrezzi;
	private int pesoMax;
	public Borsa() {
		this.pesoMax=Proprieta.getPesoMaxBorsa();
		this.attrezzi = new ArrayList<>();
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
	
	
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		for (Attrezzo attrezzo : this.attrezzi) {
		if (attrezzo.getNome().equals(nomeAttrezzo)) {
			return attrezzo;
		}
		}
		return null;
	}
	
	public int getPeso() {
		int peso = 0;
		for (Attrezzo attrezzo : attrezzi) {
		    peso += attrezzo.getPeso();
		}
		return peso;
	}
	
	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
	}
	
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}
	
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		 Attrezzo trovato = this.getAttrezzo(nomeAttrezzo);
		    if (trovato != null)
		        this.attrezzi.remove(trovato);
		    return trovato;
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
		s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
		for (Attrezzo attrezzo : this.attrezzi)
		s.append(attrezzo.toString()+" ");
		}
		else
		s.append("Borsa vuota");
		return s.toString();
	}
	
	public List<Attrezzo> getContenutoOrdinatoPerPeso(){
		List<Attrezzo> lista = new ArrayList<>(this.attrezzi);
		Collections.sort(lista, new ComparatorePeso());
		return lista;
	}
	
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		SortedSet<Attrezzo> nuovoset = new TreeSet<>(new ComparatoreNome());
		nuovoset.addAll(attrezzi);
		return nuovoset;
	}
	
	public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		Map<Integer, Set<Attrezzo>> raggruppa= new HashMap<>();
		for(Attrezzo attrezzo : attrezzi) {
			if(!raggruppa.containsKey(attrezzo.getPeso())) {
				Set<Attrezzo> set=new HashSet<>();
				set.add(attrezzo);
				raggruppa.put(attrezzo.getPeso(),set);
			}
			else {
				raggruppa.get(attrezzo.getPeso()).add(attrezzo);
			}
		}
		return raggruppa;
	}
	
	SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso(){
		SortedSet<Attrezzo> nuovoset = new TreeSet<>(new ComparatorePeso());
		nuovoset.addAll(attrezzi);
		return nuovoset;
	}
}
