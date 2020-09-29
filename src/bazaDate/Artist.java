package bazaDate;

import java.time.LocalDate;
import java.util.ArrayList;

public class Artist {
	private int id;
	private String nume;

	private String trupa; //TODO: change String to Artist
	private int anDebut;
	private String nationalitate;
	private LocalDate dataNasterii;
	private ArrayList<Instrument> abilitati;

	public Artist(int id, String nume, String trupa, int anDebut, String nationalitate, LocalDate dataNasterii) {
		this.id = id;
		this.nume = nume;
		this.trupa = trupa;
		this.anDebut = anDebut;
		this.nationalitate = nationalitate;
		this.dataNasterii = dataNasterii;
		this.abilitati = null;
	}

	public Artist(int id, String nume, int anDebut, String nationalitate, LocalDate dataNasterii) {
		this(id, nume, "-Solo-", anDebut, nationalitate, dataNasterii);
	}

	public Artist(int id, String nume, int anDebut, String nationalitate) {
		this(id, nume, "-Trupa-", anDebut, nationalitate, null);
	}

	public int getId() {
		return id;
	}

	public String getNume() {
		return nume;
	}

	public String getTrupa() {
		return trupa;
	}

	public int getAnDebut() {
		return anDebut;
	}

	public String getNationalitate() {
		return nationalitate;
	}

	public LocalDate getDataNasterii() {
		return dataNasterii;
	}

	public ArrayList<Instrument> getAbilitati() {
		return abilitati;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setFormatie(String formatie) {
		this.trupa = formatie;
	}

	public void setDataNasterii(LocalDate dataNasterii) {
		this.dataNasterii = dataNasterii;
	}

	public void setAbilitati(ArrayList<Instrument> abilitati) {
		if (!abilitati.isEmpty()) {
			this.abilitati = abilitati;
		}
	}

	@Override
	public String toString() {
		return "ID:" + id + ", Nume: " + nume + ", Trupa: " + trupa + ", An debut: " + anDebut + ", Nationalitate: "
				+ nationalitate + ", Data nasterii: " + dataNasterii;
	}
}
