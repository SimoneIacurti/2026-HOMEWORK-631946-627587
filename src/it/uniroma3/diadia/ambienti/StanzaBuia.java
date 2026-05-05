package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza {
private String Lanterna;
	
	public StanzaBuia(String nome , String Lanterna) {
		super(nome);
		this.Lanterna = Lanterna;
	}

	@Override
	public String getDescrizione() {
		if(!this.hasAttrezzo(Lanterna)) {
			return "qui c'è un buio pesto";
		}
		return super.getDescrizione();
	}
}
