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
	
	public double calcolaDistanza(Posizione altra) {
	    if (altra == null) {
	        throw new IllegalArgumentException("La posizione di confronto non può essere null.");
	    }
	    double diffLat = this.latitudine - altra.getLatitudine();
	    double diffLon = this.longitudine - altra.getLongitudine();
	    return 0.9996 * Math.sqrt(Math.pow(diffLat, 2) + Math.pow(diffLon, 2));
	}
}