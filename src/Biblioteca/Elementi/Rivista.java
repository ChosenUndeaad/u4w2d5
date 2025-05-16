package Biblioteca.Elementi;

import Biblioteca.Periodicita;

public class Rivista extends Catalogo {
    private Periodicita periodicita;

    public Rivista(String isbn, String titolo, int annoPubblicazione, int numeroPagine, Periodicita periodicita) {
        super(isbn, titolo, annoPubblicazione, numeroPagine);
        this.periodicita = periodicita;
    }

    public Periodicita getPeriodicita() {return periodicita; }

    @Override
    public String getTipo() {
        return "Rivista";
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", Periodicità: %s", periodicita);
    }
}

