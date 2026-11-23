package it.unipv.sfw.bikar.clienti;
import it.unipv.sfw.bikar.*;

public class Dipendente {
	private String nome, cognome;
	private int eta;
	private TipoPatente patente;
	private Posizione posizione;
	
	public Dipendente(String nome, String cognome, int eta, TipoPatente patente, Posizione posizione) {
		this.nome = nome;
		this.cognome = cognome;
		this.eta = eta;
		this.patente = patente;
		this.posizione = posizione;
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
	public TipoPatente getPatente() {
		return patente;
	}
	public void setPatente(TipoPatente patente) {
		this.patente = patente;
	}
	public Posizione getPosizione() {
		return posizione;
	}
	public void setPosizione(Posizione posizione) {
		this.posizione = posizione;
	}
}
