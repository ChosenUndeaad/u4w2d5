package Biblioteca.Elementi;

public abstract class Catalogo {
    protected String isbn;
    protected String titolo;
    protected int annoPubblicazione;
    protected int numeroPagine;

    public Catalogo(String isbn, String titolo, int annoPubblicazione, int numeroPagine) {
        this.isbn = isbn;
        this.titolo = titolo;
        this.annoPubblicazione = annoPubblicazione;
        this.numeroPagine = numeroPagine;
    }

    public String getIsbn() { return isbn; }
    public String getTitolo() { return titolo; }
    public int getAnnoPubblicazione() { return annoPubblicazione; }
    public int getNumeroPagine() { return numeroPagine; }

    public abstract String getTipo();

    @Override
    public String toString() {
        return String.format("[%s] ISBN: %s, Titolo: %s, Anno: %d, Pagine: %d", getTipo(), isbn, titolo, annoPubblicazione, numeroPagine);
    }
}
