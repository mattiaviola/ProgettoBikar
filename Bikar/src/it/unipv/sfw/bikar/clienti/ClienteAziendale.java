package it.unipv.sfw.bikar.clienti;
import it.unipv.sfw.bikar.Posizione;
import it.unipv.sfw.bikar.enums.*;

public class ClienteAziendale extends Cliente{
	private String ragioneSociale;
	private Dipendente dipendente;
	
	public ClienteAziendale(String id, String ragioneSociale, Dipendente dipendente) {
		super(id);
		this.ragioneSociale = ragioneSociale;
		this.setDipendente(dipendente);
	}

	public String getRagioneSociale() {
		return ragioneSociale;
	}

	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}
	
	@Override
	public int getEta() {
	    return this.dipendente.getEta();
	}

	@Override
	public TipoPatente getPatente() {
	    return this.dipendente.getPatente();
	}

	@Override
	public Posizione getPosizione() {
	    return this.dipendente.getPosizione();
	}
	
	public void setDipendente(Dipendente dipendente) {
	    if (dipendente == null) {
	        throw new IllegalArgumentException("Il dipendente designato non può essere null.");
	    }
	    this.dipendente = dipendente;
	}
}
