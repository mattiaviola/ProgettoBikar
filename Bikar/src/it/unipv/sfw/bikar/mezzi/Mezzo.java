package it.unipv.sfw.bikar.mezzi;
import it.unipv.sfw.bikar.*;
import it.unipv.sfw.bikar.enums.TipoMezzo;
import it.unipv.sfw.bikar.enums.TipoPatente;

public abstract class Mezzo {
	private String targa;
	private Posizione posizione;
	private TipoAlimentazione alimentazione;
	private boolean disponibile;
	private double tariffa;
	
	public Mezzo(String targa, Posizione posizione) {
		this.targa = targa;
		this.posizione = posizione;
		this.disponibile = true;
	}
	
	@Override
	public String toString() {
		return "Targa: " + this.targa + " - Posizione: " + this.posizione.getLatitudine() + "; " + this.posizione.getLongitudine()
        + " - Alimentazione: " + this.alimentazione
        + " - Tariffa: " + this.tariffa + " €/min"
        + " - Disponibile: " + (this.disponibile ? "Sì" : "No");
	}

	public String getTarga() {
		return targa;
	}

	public void setTarga(String targa) {
		this.targa = targa;
	}

	public Posizione getPosizione() {
		return posizione;
	}

	public void setPosizione(Posizione posizione) {
		this.posizione = posizione;
	}

	public TipoAlimentazione getAlimentazione() {
		return alimentazione;
	}

	public void setAlimentazione(TipoAlimentazione alimentazione) {
		this.alimentazione = alimentazione;
	}

	public boolean isDisponibile() {
		return disponibile;
	}

	public void setDisponibile(boolean disponibile) {
		this.disponibile = disponibile;
	}

	public double getTariffaAMinuto() {
		return tariffa;
	}

	public void setTariffa(double tariffa) {
		this.tariffa = tariffa;
	}
	
	public abstract boolean verificaRequisiti(int eta, TipoPatente patente);
	
	public abstract TipoMezzo getTipo();
	
}
