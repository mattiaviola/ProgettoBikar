package it.unipv.sfw.bikar.clienti;

import it.unipv.sfw.bikar.Posizione;
import it.unipv.sfw.bikar.enums.TipoPatente;

public abstract class Cliente {
	private String id;
	
	public Cliente(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public abstract int getEta();
	public abstract TipoPatente getPatente();
	public abstract Posizione getPosizione();
}
