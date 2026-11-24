package it.unipv.sfw.bikar.mezzi.autoveicoli;
import it.unipv.sfw.bikar.*;
import it.unipv.sfw.bikar.enums.TipoPatente;

public class Auto extends Autoveicolo{
	
	public Auto(String targa, Posizione posizione, TipoAlimentazione alimentazione) {
		super(targa, posizione);
		this.setAlimentazione(alimentazione);
		super.setTariffa(1);
	}
	
	@Override
	public void setAlimentazione(TipoAlimentazione alimentazione) {
		if(alimentazione != TipoAlimentazione.BENZINA && alimentazione != TipoAlimentazione.ELETTRICA)
			throw new IllegalArgumentException("Un'auto può essere solo a benzina o elettrica.");
		super.setAlimentazione(alimentazione);
	}
	
	@Override
	public boolean verificaRequisiti(int eta, TipoPatente patente) {
		if(eta >= 18 && patente == TipoPatente.B)
			return true;
		else
			return false;
	}
}
