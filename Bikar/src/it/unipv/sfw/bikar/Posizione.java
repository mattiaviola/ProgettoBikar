package it.unipv.sfw.bikar;

public class Posizione {
	private double latitudine, longitudine;
	
	public Posizione(double lat, double lon) {
		this.latitudine = lat;
		this.longitudine = lon;
	}

	public double getLatitudine() {
		return latitudine;
	}

	public void setLatitudine(double latitudine) {
		this.latitudine = latitudine;
	}

	public double getLongitudine() {
		return longitudine;
	}

	public void setLongitudine(double longitudine) {
		this.longitudine = longitudine;
	}
}