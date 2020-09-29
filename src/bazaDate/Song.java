package bazaDate;
import java.sql.Time;

public class Song {
    private int id;
    private String nume;
    private Time durata;
    private int nr_ordine;

    public Song(int id, String nume, Time durata, int nr_ordine) {
        this.id = id;
        this.nume = nume;
        this.durata = durata;
        this.nr_ordine = nr_ordine;
    }

    public int getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public Time getDurata() {
        return durata;
    }

    public int getNr_ordine() {
        return nr_ordine;
    }
}
