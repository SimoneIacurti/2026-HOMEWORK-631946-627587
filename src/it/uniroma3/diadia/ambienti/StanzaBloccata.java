package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza {
	private Direzioni direzioneBloccata;
	private String attrezzoSbloccante;

	public StanzaBloccata(String nome, Direzioni direzioneBloccata, String attrezzoSbloccante) {
		super(nome);
		this.direzioneBloccata = direzioneBloccata;
		this.attrezzoSbloccante = attrezzoSbloccante;
	}

	@Override
	public Stanza getStanzaAdiacente(Direzioni direzione) {
		if(this.direzioneBloccata.equals(direzione) && !this.hasAttrezzo(attrezzoSbloccante)) {
			return this;
		}
		return super.getStanzaAdiacente(direzione);
	}

	@Override
	public String getDescrizione() {
		if(!this.hasAttrezzo(attrezzoSbloccante)) {
			String avviso = "\nStanza bloccata nella direzione: " + direzioneBloccata + "\nTi serve l'attrezzo: " + attrezzoSbloccante;
			return super.getDescrizione() + avviso;
		}
		return super.getDescrizione();
	}
}
