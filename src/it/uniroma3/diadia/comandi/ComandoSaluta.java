package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoSaluta extends AbstractComando {

	@Override
	public void esegui(Partita partita) {
		// TODO Auto-generated method stub
		if(partita.getStanzaCorrente().getPersonaggio()!=null)
			partita.getIo().mostraMessaggio(partita.getStanzaCorrente().getPersonaggio().saluta());
			partita.getIo().mostraMessaggio("non c'è nessuno");
		}

	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return "saluta";
	}

}
