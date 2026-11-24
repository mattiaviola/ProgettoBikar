package it.unipv.sfw.bikar.clienti;
import it.unipv.sfw.bikar.*;
import it.unipv.sfw.bikar.enums.TipoPatente;

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

	public void setEta(int eta) {
		this.eta = eta;
	}

	public void setPosizione(Posizione posizione) {
		this.posizione = posizione;
	}
	
	@Override
	public int getEta() {
	    return this.eta;
	}

	@Override
	public TipoPatente getPatente() {
	    return this.patente;
	}

	@Override
	public Posizione getPosizione() {
	    return this.posizione;
	}
	
	
}
