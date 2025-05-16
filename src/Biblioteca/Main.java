package Biblioteca;

import Biblioteca.Elementi.*;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Archivio archivio = new Archivio();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean continua = true;

        while (continua) {
            stampaMenu();
            String scelta = scanner.nextLine();

            try {
                switch (scelta) {
                    case "1" -> aggiungiElemento();
                    case "2" -> cercaPerIsbn();
                    case "3" -> rimuoviElemento();
                    case "4" -> cercaPerAnno();
                    case "5" -> cercaPerAutore();
                    case "6" -> aggiornaElemento();
                    case "7" -> archivio.stampaStatistiche();
                    case "8" -> archivio.stampaTuttiGliElementi();
                    case "0" -> continua = false;
                    default -> System.out.println("Scelta non valida.");
                }
            } catch (Exception e) {
                System.out.println("Errore: " + e.getMessage());
            }

            System.out.println();
        }

        scanner.close();
    }

    private static void stampaMenu() {
        System.out.println("===== Catalogo Bibliotecario =====");
        System.out.println("1. Aggiungi elemento");
        System.out.println("2. Cerca per ISBN");
        System.out.println("3. Rimuovi elemento per ISBN");
        System.out.println("4. Cerca per anno di pubblicazione");
        System.out.println("5. Cerca per autore");
        System.out.println("6. Aggiorna elemento");
        System.out.println("7. Mostra statistiche");
        System.out.println("8. Mostra tutti gli elementi");
        System.out.println("0. Esci");
        System.out.print("Scegli un'opzione: ");
    }

    private static void aggiungiElemento() {
        System.out.print("Libro o Rivista (L/R)? ");
        String tipo = scanner.nextLine().trim().toUpperCase();

        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Titolo: ");
        String titolo = scanner.nextLine();
        System.out.print("Anno pubblicazione: ");
        int anno = Integer.parseInt(scanner.nextLine());
        System.out.print("Numero pagine: ");
        int pagine = Integer.parseInt(scanner.nextLine());

        if (tipo.equals("L")) {
            System.out.print("Autore: ");
            String autore = scanner.nextLine();
            System.out.print("Genere: ");
            String genere = scanner.nextLine();
            archivio.aggiungiElemento(new Libro(isbn, titolo, anno, pagine, autore, genere));
        } else if (tipo.equals("R")) {
            System.out.print("Periodicità (SETTIMANALE/MENSILE/SEMESTRALE): ");
            String perStr = scanner.nextLine().toUpperCase();
            Periodicita periodicita = Periodicita.valueOf(perStr);
            archivio.aggiungiElemento(new Rivista(isbn, titolo, anno, pagine, periodicita));
        } else {
            System.out.println("Tipo non valido.");
        }
    }

    private static void cercaPerIsbn() throws ElementoNonTrovato {
        System.out.print("Inserisci ISBN: ");
        String isbn = scanner.nextLine();
        Catalogo elemento = archivio.cercaPerIsbn(isbn);
        System.out.println("Trovato: " + elemento);
    }

    private static void rimuoviElemento() {
        System.out.print("Inserisci ISBN da rimuovere: ");
        String isbn = scanner.nextLine();
        archivio.rimuoviElemento(isbn);
    }

    private static void cercaPerAnno() {
        System.out.print("Inserisci anno: ");
        int anno = Integer.parseInt(scanner.nextLine());
        List<Catalogo> risultati = archivio.cercaPerAnno(anno);
        risultati.forEach(System.out::println);
    }

    private static void cercaPerAutore() {
        System.out.print("Inserisci autore: ");
        String autore = scanner.nextLine();
        List<Libro> risultati = archivio.cercaPerAutore(autore);
        risultati.forEach(System.out::println);
    }

    private static void aggiornaElemento() throws ElementoNonTrovato {
        System.out.print("Inserisci ISBN da aggiornare: ");
        String isbn = scanner.nextLine();

        System.out.print("Nuovo Titolo: ");
        String titolo = scanner.nextLine();
        System.out.print("Nuovo Anno pubblicazione: ");
        int anno = Integer.parseInt(scanner.nextLine());
        System.out.print("Nuovo Numero pagine: ");
        int pagine = Integer.parseInt(scanner.nextLine());

        Catalogo esistente = archivio.cercaPerIsbn(isbn);

        if (esistente instanceof Libro) {
            System.out.print("Nuovo Autore: ");
            String autore = scanner.nextLine();
            System.out.print("Nuovo Genere: ");
            String genere = scanner.nextLine();
            archivio.aggiornaElemento(isbn, new Libro(isbn, titolo, anno, pagine, autore, genere));
        } else if (esistente instanceof Rivista) {
            System.out.print("Nuova Periodicità (SETTIMANALE/MENSILE/SEMESTRALE): ");
            String perStr = scanner.nextLine().toUpperCase();
            Periodicita periodicita = Periodicita.valueOf(perStr);
            archivio.aggiornaElemento(isbn, new Rivista(isbn, titolo, anno, pagine, periodicita));
        }
    }
}