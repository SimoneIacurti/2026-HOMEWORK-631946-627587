package it.uniroma3.diadia.ambienti;

public enum Direzioni {
	NORD() {
		@Override public Direzioni opposta() {
		return SUD;
		}
	},
	SUD(){
		@Override public Direzioni opposta() {
			return NORD;
			}
	},
	OVEST(){	
		@Override public Direzioni opposta() {
			return EST;
			}
	},
	EST(){
		@Override public Direzioni opposta() {
			return OVEST;
			}
	},
	NORD_EST(){
		
		@Override public Direzioni opposta() {
			return null;
			}
	};
	public abstract Direzioni opposta();
}
