package it.unipv.sfw.bikar.mezzi.autoveicoli;
import it.unipv.sfw.bikar.*;
import it.unipv.sfw.bikar.enums.TipoPatente;


public class Furgone extends Autoveicolo{
	
	public Furgone(String targa, Posizione posizione, TipoAlimentazione alimentazione) {
		super(targa, posizione);
		this.setAlimentazione(alimentazione);
		super.setTariffa(2);
	}
	
	@Override
	public void setAlimentazione(TipoAlimentazione alimentazione) {
		if(alimentazione != TipoAlimentazione.BENZINA)
			throw new IllegalArgumentException("Un furgone può essere solo a benzina.");
		super.setAlimentazione(alimentazione);
	}
	
	@Override
	public boolean verificaRequisiti(int eta, TipoPatente patente) {
		if(eta >= 18 && eta <= 70 && patente == TipoPatente.B)
			return true;
		else
			return false;
	}
}
