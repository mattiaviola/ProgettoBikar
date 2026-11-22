package it.unipv.sfw.bikar.clienti;
import it.unipv.sfw.bikar.*;

public class ClientePrivato extends Cliente{
	private TipoPatente patente;
	private String nome, cognome;
	private int eta;
	private Posizione posizione;
	
	public ClientePrivato(String id, TipoPatente patente, String nome, String cognome, int eta, Posizione posizione) {
		super(id);
		this.patente = patente;
		this.nome = nome;
		this.cognome = cognome;
		this.eta = eta;
		this.posizione = posizione;
	}

	public TipoPatente getPatente() {
		return patente;
	}

	public void setPatente(TipoPatente patente) {
		this.patente = patente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public int getEta() {
		return eta;
	}

	public void setEta(int eta) {
		this.eta = eta;
	}

	public Posizione getPosizione() {
		return posizione;
	}

	public void setPosizione(Posizione posizione) {
		this.posizione = posizione;
	}
	
	
}
