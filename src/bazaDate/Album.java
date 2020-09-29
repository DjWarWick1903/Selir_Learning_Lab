package bazaDate;
import java.util.ArrayList;
import javax.persistence.*;

@Entity
@Table(name="albume")
public class Album {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_album")
    private int id;
	
	@Column(name="titlu")
    private String titlu;
	
	@Column(name="an")
    private String anAparitie;
	
	@Column(name="id_artist")
    private String artist;
    private ArrayList<Song> melodii;
    
    public Album() {
    	
    }

    public Album(int id, String titlu, String anAparitie, String artist) {
        this.id = id;
        this.titlu = titlu;
        this.anAparitie = anAparitie;
        this.artist = artist;
        this.melodii = null;
    }

    public Album(int id, String titlu, String anAparitie) {
        this(id, titlu, anAparitie, "Necunoscut");
    }

    public int getId() {
        return id;
    }

    public String getTitlu() {
        return titlu;
    }

    public String getAnAparitie() {
        return anAparitie;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void addMelodie(Song melodie) {
        if(melodii == null) {
            melodii = new ArrayList<>();
            melodii.add(melodie);
        } else {
            melodii.add(melodie);
        }
    }

    public ArrayList<Song> getMelodii() {
        return melodii;
    }
}
