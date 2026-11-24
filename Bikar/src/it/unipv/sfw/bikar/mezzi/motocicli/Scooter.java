package it.unipv.sfw.bikar.mezzi.motocicli;
import it.unipv.sfw.bikar.Posizione;
import it.unipv.sfw.bikar.TipoAlimentazione;
import it.unipv.sfw.bikar.enums.TipoMezzo;
import it.unipv.sfw.bikar.enums.TipoPatente;

public class Scooter extends Motociclo {

	public Scooter(String targa, Posizione posizione, TipoAlimentazione alimentazione) {
		super(targa, posizione);
		this.setAlimentazione(alimentazione);
		super.setTariffa(0.50);
	}
	
	@Override
	public void setAlimentazione(TipoAlimentazione alimentazione) {
		if(alimentazione != TipoAlimentazione.BENZINA && alimentazione != TipoAlimentazione.ELETTRICA)
			throw new IllegalArgumentException("Uno scooter può essere solo a benzina o elettrico.");
		super.setAlimentazione(alimentazione);
	}
	
	@Override
	public boolean verificaRequisiti(int eta, TipoPatente patente) {
		if(eta > 16)
			return true;
		else
			return false;
	}
	
	@Override
	public TipoMezzo getTipo() {
		return TipoMezzo.SCOOTER;
	}

}
