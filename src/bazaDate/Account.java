package bazaDate;

//import org.hibernate.annotations.*;
import javax.persistence.*;

@Entity
@Table(name="conturi")
public final class Account {
	private int id;
	private String username;
	private String password;
	private int priv;
	
	public Account(int id, String user, String pass, int priv) {
		this.id = id;
		username = user;
		password = pass;
		this.priv = priv;
	}
	
	public Account(int id, String user, String pass) {
		this.id = id;
		username = user;
		password = pass;
		priv = 0;
	}
	
	public Account() {
		id = 0;
		username = null;
		password = null;
		priv = 0;
	}

	public int getId() {
		return id;
	}

	protected void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	protected void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	protected void setPassword(String password) {
		this.password = password;
	}
	
	public int getPriv() {
		return priv;
	}
	
	protected void setPriv(int priv) {
		this.priv = priv;
	}
	
	
}
