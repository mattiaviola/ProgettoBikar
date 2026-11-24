package it.unipv.sfw.bikar;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import it.unipv.sfw.bikar.clienti.*;
import it.unipv.sfw.bikar.mezzi.*;

public class Controller {
	private List<Cliente> clienti;
	private List<Mezzo> mezzi;
	private List<Prenotazione> prenotazioni;
	private List<Utilizzo> utilizzi;
	private List<Amministratore> amministratori;
	
	public Controller() {
		this.clienti = new ArrayList<Cliente>();
		this.mezzi = new ArrayList<Mezzo>();
		this.prenotazioni = new ArrayList<Prenotazione>();
		this.utilizzi = new ArrayList<Utilizzo>();
		this.amministratori = new ArrayList<Amministratore>();
	}
	
	public void registraCliente(Cliente c) {
		if(c == null)
			throw new IllegalArgumentException("Il cliente da aggiungere risulta null.");
		for(Cliente cliente: clienti) {
			if(cliente.getId().equalsIgnoreCase(c.getId())) {
				throw new IllegalArgumentException("Il cliente indicato è già registrato.");
			}
		}
		clienti.add(c);
	}
	
	public String consultaParco() {
		StringBuilder sb = new StringBuilder();
		if(mezzi.isEmpty())
			return "Non ci sono mezzi nel parco.";
		else{
			for(Mezzo mezzo: mezzi) {
				sb.append(mezzo.toString()).append("\n");
			}
			return sb.toString();
		}
	}
	
	public String consultaParco(TipoAlimentazione alimentazione) {
		StringBuilder sb = new StringBuilder();
		if(mezzi.isEmpty())
			return "Non ci sono mezzi nel parco.";
		else{
			for(Mezzo mezzo: mezzi) {
				if(mezzo.getAlimentazione() == alimentazione) {
					sb.append(mezzo.toString()).append("\n");
				}
			}
			return sb.toString();
		}
	}
	
	public Prenotazione prenotaMezzo(Cliente c, Mezzo m) {
		if(c == null)
			throw new IllegalArgumentException("Il cliente indicato risulta null.");
		else if(m == null)
			throw new IllegalArgumentException("Il mezzo indicato risulta null.");
		Cliente clienteRegistrato = null;
		for (Cliente cliente : clienti) {
			if (cliente.getId().equalsIgnoreCase(c.getId())) {
				clienteRegistrato = cliente;
				break;
			}
		}
		if (clienteRegistrato == null) {
			throw new IllegalArgumentException("Cliente non registrato nel sistema.");
		}
		
		if (!this.mezzi.contains(m)) {
			throw new IllegalArgumentException("Il mezzo indicato non appartiene al parco Bikar.");
		}
		
		if (!m.isDisponibile()) {
			throw new IllegalStateException("Il mezzo " + m.getTarga() + " non è disponibile.");
		}
		
		Prenotazione prenotazione = new Prenotazione("P-"+(this.prenotazioni.size()+1), clienteRegistrato, m);
		this.prenotazioni.add(prenotazione);
		m.setDisponibile(false); 
		
		return prenotazione;
	}
	
	public Utilizzo avviaUtilizzo(Prenotazione p) {
		if(!prenotazioni.contains(p))
			throw new IllegalArgumentException("Prenotazione non esistente.");
		Utilizzo u = new Utilizzo("U-"+(this.utilizzi.size()+1), p);
		utilizzi.add(u);
		return u;
	}
	
	public double terminaUtilizzo(Utilizzo u, Posizione p) {
		if(u == null)
			throw new IllegalArgumentException("L'utilizzo indicato risulta null.");
		if(p == null)
			throw new IllegalArgumentException("La posizione indicata risulta null.");
		if(!utilizzi.contains(u))
			throw new IllegalArgumentException("L'utilizzo indicato non risulta registrato.");
		u.getPrenotazione().setStato(StatoPrenotazione.CONCLUSA);
		utilizzi.remove(u);
		u.getPrenotazione().getMezzo().setPosizione(p);
		u.setIstanteFinale(LocalDateTime.now());
		return u.calcolaCosto();
	}
	
	public void aggiungiMezzo(Amministratore a, Mezzo m) {
		if(a == null)
			throw new IllegalArgumentException("L'amministratore indicato risulta nullo.");
		if(m == null)
			throw new IllegalArgumentException("Il mezzo indicato risulta nullo.");
		if(!amministratori.contains(a))
			throw new IllegalArgumentException("L'amministratore indicato non è abilitato.");
		if(mezzi.contains(m))
			throw new IllegalArgumentException("Il mezzo indicato appertiene già al parco Bikar.");
		
		mezzi.add(m);
	}
}
