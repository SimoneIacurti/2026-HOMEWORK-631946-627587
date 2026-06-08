package it.uniroma3.diadia.ambienti;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Labirinto {
	
	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;
	private Map<String,Attrezzo> attrezzi;
	private Map<String,Stanza> mappaStanze;
	
    private Labirinto() {
    	this.creaStanze();
    }
    
    public static LabirintoBuilder newBuilder() {
        return new LabirintoBuilder();
    }
    public static Labirinto creaLabirintoDefault() {
        return new Labirinto();
    }
    
    private Labirinto(boolean LabirintoBuilder) {
    	attrezzi=new HashMap<>();
    	mappaStanze=new HashMap<>();
    }
	
	public void creaStanze() {

		/* crea gli attrezzi */
    	Attrezzo lanterna = new Attrezzo("lanterna",3);
		Attrezzo osso = new Attrezzo("osso",1);
		attrezzi=new HashMap<>();
		mappaStanze=new HashMap<>();
    	
		/* crea stanze del labirinto */
		Stanza atrio = new Stanza("Atrio");
		Stanza aulaN11 = new Stanza("Aula N11");
		Stanza aulaN10 = new Stanza("Aula N10");
		Stanza laboratorio = new Stanza("Laboratorio Campus");
		Stanza biblioteca = new Stanza("Biblioteca");
		
		/* collega le stanze */
		atrio.impostaStanzaAdiacente(Direzioni.NORD, biblioteca);
		atrio.impostaStanzaAdiacente(Direzioni.EST, aulaN11);
		atrio.impostaStanzaAdiacente(Direzioni.SUD, aulaN10);
		atrio.impostaStanzaAdiacente(Direzioni.OVEST, laboratorio);
		aulaN11.impostaStanzaAdiacente(Direzioni.EST, laboratorio);
		aulaN11.impostaStanzaAdiacente(Direzioni.OVEST, atrio);
		aulaN10.impostaStanzaAdiacente(Direzioni.NORD, atrio);
		aulaN10.impostaStanzaAdiacente(Direzioni.EST, aulaN11);
		aulaN10.impostaStanzaAdiacente(Direzioni.OVEST, laboratorio);
		laboratorio.impostaStanzaAdiacente(Direzioni.EST, atrio);
		laboratorio.impostaStanzaAdiacente(Direzioni.OVEST, aulaN11);
		biblioteca.impostaStanzaAdiacente(Direzioni.SUD, atrio);

        /* pone gli attrezzi nelle stanze */
		aulaN10.addAttrezzo(lanterna);
		atrio.addAttrezzo(osso);

		// il gioco comincia nell'atrio
        this.stanzaIniziale = atrio;  
		this.stanzaVincente = biblioteca;
    }
	
	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}
	
	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}
	
	public boolean addAttrezzoLabirinto(Stanza stanza,Attrezzo attrezzo) {
		if(this.attrezzi.containsKey(attrezzo.getNome()))
			return false;
		stanza.addAttrezzo(attrezzo);
		this.attrezzi.put(attrezzo.getNome(), attrezzo);
		return true;
	}
	
	public boolean removeAttrezzoLabirinto(Stanza stanza,Attrezzo attrezzo) {
		if(stanza.hasAttrezzo(attrezzo.getNome())) {
			stanza.removeAttrezzo(attrezzo);
			this.attrezzi.remove(attrezzo.getNome());
			return true;
		}
		return false;
	}
	
	public boolean hasAttrezzo(String nome) {
		return this.attrezzi.containsKey(nome);
	}

	public void setStanzaIniziale(Stanza s) {
		this.stanzaIniziale=s;
		mappaStanze.put(s.getNome(),s);
	}
	public void setStanzaVincente(Stanza s) {
		this.stanzaVincente=s;
		mappaStanze.put(s.getNome(),s);
		}

	public void addStanzaLabirinto(Stanza s) {
		mappaStanze.put(s.getNome(), s);
	}
	public Stanza getStanza(String s) {
		return mappaStanze.get(s);
		
	}
	public List<Stanza> getListaDaMappaStanze(){
		List<Stanza> lista=new ArrayList<>(this.mappaStanze.values());
		return lista;
	}
	public Map<String,Stanza> getMappaStanze(){
		return this.mappaStanze;
	}
	
	 public static class LabirintoBuilder {

			private Labirinto labirinto;
			private Stanza ultima;
			public LabirintoBuilder() {
				this.labirinto=new Labirinto(true);	
				
			}
			
			public LabirintoBuilder addStanzaIniziale(String s) {
				if(labirinto.getMappaStanze().containsKey(s)) {
					this.labirinto.setStanzaIniziale(labirinto.getMappaStanze().get(s));
					ultima=labirinto.getMappaStanze().get(s);
					return this;
					
				}
				Stanza stanza=new Stanza(s);
				this.labirinto.setStanzaIniziale(stanza);
				ultima=stanza;
				return this;
				
				
				
			}
			public LabirintoBuilder addStanzaVincente(String s) {
			    if(labirinto.getMappaStanze().containsKey(s)) {
			        this.labirinto.setStanzaVincente(labirinto.getMappaStanze().get(s));
			        ultima=labirinto.getMappaStanze().get(s);
			        return this;
			    }
			    Stanza stanza=new Stanza(s);
			    this.labirinto.setStanzaVincente(stanza);
			    ultima=stanza;
			    return this;
			}
			public LabirintoBuilder addAttrezzo(String oggetto, int peso ) {
				if(ultima!=null) 
					this.labirinto.addAttrezzoLabirinto(ultima,new Attrezzo(oggetto,peso));
					return this;
			}
			public LabirintoBuilder addAttrezzoAStanza(String nomeStanza, String nomeAttrezzo, int peso) {
			    Stanza stanza = this.labirinto.getStanza(nomeStanza);
			    if (stanza != null)
			        this.labirinto.addAttrezzoLabirinto(stanza, new Attrezzo(nomeAttrezzo, peso));
			    return this;
			}
			public LabirintoBuilder addStanza(String s) {
				Stanza stanza=new Stanza(s);
				ultima=stanza;
				this.labirinto.addStanzaLabirinto(stanza);
				return this;
				
			}
			
			
			public LabirintoBuilder addStanzaMagica(String s, int num) {
				Stanza magica=new StanzaMagica(s,num);
				this.labirinto.addStanzaLabirinto(magica);
				ultima=magica;
				return this;
				
			}
			public LabirintoBuilder addStanzaMagica(String s) {
				Stanza magica=new StanzaMagica(s);
				this.labirinto.addStanzaLabirinto(magica);
				ultima=magica;
				return this;
				
			}
			public LabirintoBuilder addStanzaBloccata(String s, String dir, String ogg) {
				Stanza bloccata=new StanzaBloccata(s,Direzioni.valueOf(dir.toUpperCase()), ogg);
				this.labirinto.addStanzaLabirinto(bloccata);
				ultima=bloccata;
				return this;
				
			}
			public LabirintoBuilder addStanzaBuia
			(String s, String ogg) {
				Stanza buia=new StanzaBuia(s, ogg);
				this.labirinto.addStanzaLabirinto(buia);
				ultima=buia;
				return this;
				
			}
				
			
			
			
			public LabirintoBuilder addAdiacenza(String stanza1, String stanza2, String direzione) {
			    if (labirinto.getStanza(stanza1) == null)
			        this.labirinto.addStanzaLabirinto(new Stanza(stanza1));
			    if (labirinto.getStanza(stanza2) == null)
			    	this.labirinto.addStanzaLabirinto(new Stanza(stanza2));
			    try {
			        Direzioni dir = Direzioni.valueOf(direzione.toUpperCase().replace("-", "_"));
			        labirinto.getStanza(stanza1).impostaStanzaAdiacente(dir, labirinto.getStanza(stanza2));
			    } catch (IllegalArgumentException e) {
			        return this;
			    }
			    return this;
			}
			public Labirinto getLabirinto() {
				return this.labirinto;
			}
			public List<Stanza> getListaStanze(){
				return this.labirinto.getListaDaMappaStanze();
			}
			public Map<String,Stanza> getMappaStanze(){
				return this.labirinto.getMappaStanze();
			}
		}

}
