package it.unipv.sfw.bikar;

import java.time.LocalDateTime;
import it.unipv.sfw.bikar.clienti.*;
import it.unipv.sfw.bikar.enums.TipoMezzo;
import it.unipv.sfw.bikar.enums.TipoPatente;
import it.unipv.sfw.bikar.mezzi.autoveicoli.*;
import it.unipv.sfw.bikar.mezzi.motocicli.*;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();

        Amministratore admin = new Amministratore();
        admin.setId("A01");
        admin.setNome("Luca");
        admin.setCognome("Verdi");
        controller.registraAmministratore(admin);

        ClientePrivato mario = new ClientePrivato("C01", TipoPatente.B,
                "Mario", "Rossi", 25, new Posizione(0, 0));
        ClientePrivato giovane = new ClientePrivato("C02", TipoPatente.NESSUNA,
                "Paolo", "Bianchi", 17, new Posizione(0, 0));
        Dipendente d = new Dipendente("Anna", "Neri", 24,
                TipoPatente.A2, new Posizione(1, 1));
        ClienteAziendale azienda = new ClienteAziendale("C03", "ABC Srl", d);
        controller.registraCliente(mario);
        controller.registraCliente(giovane);
        controller.registraCliente(azienda);

        Auto auto = new Auto("AA001", new Posizione(3, 4), TipoAlimentazione.BENZINA);
        Scooter scooter = new Scooter("SC001", new Posizione(1, 0), TipoAlimentazione.ELETTRICA);
        Moto moto = new Moto("MO001", new Posizione(2, 0), TipoAlimentazione.BENZINA);
        controller.aggiungiMezzo(admin, auto);
        controller.aggiungiMezzo(admin, scooter);
        controller.aggiungiMezzo(admin, moto);

        System.out.println("\n--- 1. CONSULTAZIONE PARCO ---");
        System.out.println(controller.consultaParco(mario));
        System.out.println("Solo scooter:");
        System.out.println(controller.consultaParco(mario, TipoMezzo.SCOOTER));

        System.out.println("\n--- 2. PRENOTAZIONE NON CONSENTITA ---");
        try {
            controller.prenotaMezzo(giovane, auto);
            System.out.println("ERRORE: prenotazione accettata!");
        } catch (IllegalArgumentException e) {
            System.out.println("OK - " + e.getMessage());
        }

        System.out.println("\n--- 3. PRENOTAZIONE VALIDA ---");
        Prenotazione p = controller.prenotaMezzo(mario, auto);
        System.out.println("Prenotazione: " + p.getId() + " - " + p.getStato());
        System.out.println("Auto disponibile: " + auto.isDisponibile());
        try {
            controller.prenotaMezzo(mario, auto);
            System.out.println("ERRORE: doppia prenotazione accettata!");
        } catch (IllegalStateException e) {
            System.out.println("OK - " + e.getMessage());
        }

        System.out.println("\n--- 4. UTILIZZO E RICONSEGNA ---");
        Utilizzo u = controller.avviaUtilizzo(p);
        System.out.println("Stato: " + p.getStato());

        u.setIstanteIniziale(LocalDateTime.now().minusMinutes(5));
        double costo = controller.terminaUtilizzo(u, new Posizione(5, 6));
        System.out.printf("Costo: %.2f euro (atteso: 5,00 euro)%n", costo);
        System.out.println("Stato: " + p.getStato());
        System.out.println("Auto disponibile: " + auto.isDisponibile());
        System.out.println("Nuova posizione: " + auto.getPosizione().getLatitudine()
                + ", " + auto.getPosizione().getLongitudine());

        System.out.println("\n--- 5. PRENOTAZIONE AZIENDALE ---");
        Prenotazione pAzienda = controller.prenotaMezzo(azienda, moto);
        System.out.println("Prenotazione: " + pAzienda.getId() + " - " + pAzienda.getStato());
        System.out.println("Moto disponibile: " + moto.isDisponibile());
    }
}
