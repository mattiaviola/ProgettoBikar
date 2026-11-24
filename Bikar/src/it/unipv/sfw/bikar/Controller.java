package it.unipv.sfw.bikar;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import it.unipv.sfw.bikar.clienti.*;
import it.unipv.sfw.bikar.enums.TipoMezzo;
import it.unipv.sfw.bikar.mezzi.*;

public class Controller {
	private List<Cliente> clienti;
	private List<Mezzo> mezzi;
	private List<Prenotazione> prenotazioni;
	private List<Utilizzo> utilizzi;
	private List<Amministratore> amministratori;
	
	private int contatoreUtilizzi;
	private int contatorePrenotazioni;
	
	public Controller() {
		this.clienti = new ArrayList<Cliente>();
		this.mezzi = new ArrayList<Mezzo>();
		this.prenotazioni = new ArrayList<Prenotazione>();
		this.utilizzi = new ArrayList<Utilizzo>();
		this.amministratori = new ArrayList<Amministratore>();
		
		this.contatorePrenotazioni = 1;
		this.contatoreUtilizzi = 1;
	}
	
	public void registraCliente(Cliente c) {
		if(c == null)
			throw new IllegalArgumentException("Il cliente da aggiungere risulta null.");
		for(Cliente cliente: clienti) {
			if(cliente.getId().equalsIgnoreCase(c.getId())) {
				throw new IllegalArgumentException("Il cliente indicato è già registrato.");
			}
		}
		if (c == null || c.getId() == null || c.getId().trim().isEmpty()) {
		    throw new IllegalArgumentException("Il cliente e il suo ID non possono essere nulli o vuoti.");
		}
		
		clienti.add(c);
	}
	
	public String consultaParco(Cliente c) {
	    if (c == null || c.getPosizione() == null) {
	        throw new IllegalArgumentException("Cliente o posizione non validi.");
	    }
	    Posizione posCliente = c.getPosizione();

	    mezzi.sort(Comparator.comparingDouble(
	        m -> m.getPosizione().calcolaDistanza(posCliente)
	    ));

	    StringBuilder sb = new StringBuilder();
	    for (Mezzo m : mezzi) {
	        double distanza = m.getPosizione().calcolaDistanza(posCliente);
	        sb.append(m.toString())
	          .append(String.format(" - Distanza: %.2f km", distanza))
	          .append("\n");
	    }
	    return sb.toString();
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
	
	public String consultaParco(TipoMezzo tipo) {
	    if (tipo == null) {
	        throw new IllegalArgumentException("La tipologia di mezzo non può essere null.");
	    }
	    StringBuilder sb = new StringBuilder();
	    for (Mezzo m : mezzi) {
	        if (m.getTipo() == tipo) {
	            sb.append(m.toString()).append("\n");
	        }
	    }
	    if (sb.length() == 0) {
	        return "Nessun mezzo trovato per la tipologia: " + tipo;
	    }
	    return sb.toString();
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
		
		Prenotazione prenotazione = null;
		if(m.verificaRequisiti(c.getEta(), c.getPatente())) {
			prenotazione = new Prenotazione("P-"+this.contatorePrenotazioni++, clienteRegistrato, m);
			this.prenotazioni.add(prenotazione);
			m.setDisponibile(false); 
		}
	
		return prenotazione;
	}
	
	public Utilizzo avviaUtilizzo(Prenotazione p) {
		if(!prenotazioni.contains(p))
			throw new IllegalArgumentException("Prenotazione non esistente.");
		Utilizzo u = new Utilizzo("U-"+this.contatoreUtilizzi++, p);
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
		u.getPrenotazione().getMezzo().setDisponibile(true);
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
		
		for (Mezzo mezzo : mezzi) {
		    if (mezzo.getTarga().equalsIgnoreCase(m.getTarga())) {
		        throw new IllegalArgumentException("Esiste già un mezzo con targa: " + m.getTarga());
		    }
		}
		
		mezzi.add(m);
	}
	
	public void registraAmministratore(Amministratore admin) {
	    if (admin == null || admin.getId() == null || admin.getId().trim().isEmpty()) {
	        throw new IllegalArgumentException("Amministratore o ID non validi.");
	    }
	    for (Amministratore esistente : amministratori) {
	        if (esistente.getId().equalsIgnoreCase(admin.getId())) {
	            throw new IllegalArgumentException("Amministratore già registrato con ID: " + admin.getId());
	        }
	    }
	    amministratori.add(admin);
	}
}
