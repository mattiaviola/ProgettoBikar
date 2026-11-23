package it.unipv.sfw.bikar.clienti;

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
	
}
