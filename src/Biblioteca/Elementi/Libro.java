package Biblioteca.Elementi;

public class Libro extends Catalogo{
    private String autore;
    private String genere;

    public Libro (String isbn, String titolo, int annoPubblicazione, int numeroPagine, String autore, String genere) {
        super(isbn, titolo, annoPubblicazione, numeroPagine);
        this.autore = autore;
        this.genere = genere;
    }

    public String getAutore() {return autore; }
    public String getGenere() {return genere; }

    @Override
    public String getTipo() {
        return "Libro";
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", Autore: %s, Genere: %s", autore, genere);
    }
}
