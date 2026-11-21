package it.unipv.sfw.bikar.mezzi;
import it.unipv.sfw.bikar.*;

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
	
	
}
