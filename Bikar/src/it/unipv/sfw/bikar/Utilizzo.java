package it.unipv.sfw.bikar;
import java.time.LocalDateTime;
import it.unipv.sfw.bikar.mezzi.*;

public class Utilizzo {
	private String id;
	private Prenotazione prenotazione;
	private LocalDateTime istanteIniziale;
	private LocalDateTime istanteFinale;
	
	public Utilizzo(String id, Prenotazione prenotazione) {
		if (prenotazione == null || prenotazione.getStato() != StatoPrenotazione.ATTIVA) {
            throw new IllegalStateException("La prenotazione non è valida o non è attiva.");
        }
		this.id = id;
		this.prenotazione = prenotazione;
		this.istanteIniziale = LocalDateTime.now();
		this.prenotazione.setStato(StatoPrenotazione.IN_UTILIZZO);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Prenotazione getPrenotazione() {
		return prenotazione;
	}

	public void setPrenotazione(Prenotazione prenotazione) {
		this.prenotazione = prenotazione;
	}

	public LocalDateTime getIstanteIniziale() {
		return istanteIniziale;
	}

	public void setIstanteIniziale(LocalDateTime istanteIniziale) {
		this.istanteIniziale = istanteIniziale;
	}

	public LocalDateTime getIstanteFinale() {
		return istanteFinale;
	}

	public void setIstanteFinale(LocalDateTime istanteFinale) {
		this.istanteFinale = istanteFinale;
	}
	
	public double termina(Posizione posFinale) {
		if(this.istanteFinale != null) {
			throw new IllegalStateException("L'utilizzo è già stato terminato!");
		}
		if(posFinale == null) {
			throw new IllegalArgumentException("La posizione di riconsegna non è valida.");
		}
		
		this.istanteFinale = LocalDateTime.now();
		
		Mezzo mezzo = this.prenotazione.getMezzo();
		mezzo.setPosizione(posFinale);
		mezzo.setDisponibile(true);
		
		this.prenotazione.setStato(StatoPrenotazione.CONCLUSA);
		
		return calcolaCosto();
	}
	
	public double calcolaCosto() {
		if (this.istanteFinale == null) {
	        throw new IllegalStateException("Impossibile calcolare il costo: l'utilizzo non è ancora terminato.");
	    }
		
		long minuti = java.time.Duration.between(this.istanteIniziale, this.istanteFinale).toMinutes();
		
		long secondiTotali = java.time.Duration.between(this.istanteIniziale, this.istanteFinale).getSeconds();
	    if (secondiTotali > 0 && minuti == 0) {
	        minuti = 1;
	    }
	    
	    double tariffa = this.prenotazione.getMezzo().getTariffaAMinuto();
	    return minuti * tariffa;
	}
}
