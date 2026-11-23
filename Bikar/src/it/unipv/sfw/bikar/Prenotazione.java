package it.unipv.sfw.bikar;
import it.unipv.sfw.bikar.clienti.*;
import it.unipv.sfw.bikar.mezzi.*;
import it.unipv.sfw.bikar.enums.*;

public class Prenotazione {
	private String id;
	private Cliente cliente;
	private Mezzo mezzo;
	private StatoPrenotazione stato;
	
	public Prenotazione(String id, Cliente cliente, Mezzo mezzo) {
		this.id = id;
		this.cliente = cliente;
		this.mezzo = mezzo;
		this.stato = StatoPrenotazione.ATTIVA;
		this.mezzo.setDisponibile(false);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Mezzo getMezzo() {
		return mezzo;
	}

	public void setMezzo(Mezzo mezzo) {
		this.mezzo = mezzo;
	}

	public StatoPrenotazione getStato() {
		return stato;
	}

	public void setStato(StatoPrenotazione stato) {
		this.stato = stato;
	}
	
	public void annulla() {
        if (this.stato != StatoPrenotazione.ATTIVA) {
            throw new IllegalStateException("Impossibile annullare: la prenotazione non è più attiva.");
        }
        this.stato = StatoPrenotazione.ANNULLATA;
        this.mezzo.setDisponibile(true);
    }
}
