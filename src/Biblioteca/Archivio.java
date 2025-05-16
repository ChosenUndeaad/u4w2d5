package Biblioteca;

import Biblioteca.Elementi.Catalogo;
import Biblioteca.Elementi.Libro;
import Biblioteca.Elementi.Rivista;

import java.util.*;
import java.util.stream.Collectors;

public class Archivio {

    private final Map<String, Catalogo> elementi = new HashMap<>();

    //Aggiunta elemento
    public void aggiungiElemento(Catalogo elemento) {
        if (elementi.containsKey(elemento.getIsbn())) {
            System.out.println("Elemento con ISBN già presente");
        } else {
            elementi.put(elemento.getIsbn(), elemento);
            System.out.println("Elemento aggiunto con successo");
        }
    }

    //Ricerca
    public Catalogo cercaPerIsbn(String isbn) throws ElementoNonTrovato {
        Catalogo elemento = elementi.get(isbn);
        if (elemento == null) {
            throw new ElementoNonTrovato("Elemento non trovato");
        }
        return elemento;
    }

    //Rimozione
    public void rimuoviElemento(String isbn) {
        if (elementi.remove(isbn) != null) {
            System.out.println("Elemento rimosso");
        } else {
            System.out.println("Elemento non trovato");
        }
    }

    //Ricerca per anno
    public List<Catalogo> cercaPerAnno(int anno) {
        return elementi.values().stream()
                .filter(e -> e.getAnnoPubblicazione() == anno)
                .collect(Collectors.toList());
    }

    //Ricerca per autore (libri)
    public List<Libro> cercaPerAutore(String autore) {
        return elementi.values().stream()
                .filter(e -> e instanceof Libro)
                .map(e -> (Libro) e)
                .filter(libro -> libro.getAutore().equalsIgnoreCase(autore))
                .collect(Collectors.toList());
    }

    //Aggiorna elemento
    public void aggiornaElemento(String isbn, Catalogo nuovoElemento) throws ElementoNonTrovato {
        if (!elementi.containsKey(isbn)) {
            throw new ElementoNonTrovato("Impossibile aggiornare. Nessun elemento con ISBN " + isbn);
        }
        elementi.put(isbn, nuovoElemento);
        System.out.println("Elemento aggiornato.");
    }

    //Stat catalogo
    public void stampaStatistiche() {
        long totaleLibri = elementi.values().stream().filter(e -> e instanceof Libro).count();
        long totaleRiviste = elementi.values().stream().filter(e -> e instanceof Rivista).count();

        Optional<Catalogo> maxPagine = elementi.values().stream()
                .max(Comparator.comparingInt(Catalogo::getNumeroPagine));

        double mediaPagine = elementi.values().stream()
                .mapToInt(Catalogo::getNumeroPagine)
                .average()
                .orElse(0.0);

        System.out.println("Statistiche del catalogo:");
        System.out.println("- Totale Libri: " + totaleLibri);
        System.out.println("- Totale Riviste: " + totaleRiviste);
        maxPagine.ifPresent(e -> System.out.println("- Elemento con più pagine: " + e));
        System.out.println("- Media numero pagine: " + mediaPagine);
    }

    //Metodo per debugging
    public void stampaTuttiGliElementi() {
        if (elementi.isEmpty()) {
            System.out.println("Nessun elemento nel catalogo");
        } else {
            elementi.values().forEach(System.out::println);
        }
    }
}
