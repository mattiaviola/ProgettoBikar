package it.unipv.sfw.bikar.mezzi.autoveicoli;

import it.unipv.sfw.bikar.Posizione;
import it.unipv.sfw.bikar.TipoAlimentazione;
import it.unipv.sfw.bikar.mezzi.Mezzo;

public abstract class Autoveicolo extends Mezzo{
	public Autoveicolo(String targa, Posizione posizione) {
		super(targa, posizione);
	}
}
