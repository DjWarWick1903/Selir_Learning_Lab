package bazaDate;
public class Instrument {
    private int id;
    private String nume;

    public Instrument(int id, String nume) {
        this.id = id;
        this.nume = nume;
    }

    public int getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }
}
