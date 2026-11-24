package it.unipv.sfw.bikar.mezzi.motocicli;

import it.unipv.sfw.bikar.Posizione;
import it.unipv.sfw.bikar.TipoAlimentazione;
import it.unipv.sfw.bikar.enums.TipoPatente;

public class Moto extends Motociclo{
	public Moto(String targa, Posizione posizione, TipoAlimentazione alimentazione) {
		super(targa, posizione);
		this.setAlimentazione(alimentazione);
		super.setTariffa(0.80);
	}
	
	@Override
	public void setAlimentazione(TipoAlimentazione alimentazione) {
		if(alimentazione != TipoAlimentazione.BENZINA)
			throw new IllegalArgumentException("Una moto può essere solo a benzina.");
		super.setAlimentazione(alimentazione);
	}
	
	@Override
	public boolean verificaRequisiti(int eta, TipoPatente patente) {
		if(eta >= 18 && patente == TipoPatente.A2)
			return true;
		else
			return false;
	}
}
