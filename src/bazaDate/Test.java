package bazaDate;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Projections;
import org.hibernate.cfg.Configuration;

public class Test {
    public static final String BLUE = "\u001B[34m";
    public static final String YELLOW = "\u001B[33m";

    public static void main(String[] args) {
    	
//    	String string = "<a>Department, R&D</a>";
//    	System.out.println(isValidHtmlEscapeCode(string));
    	
    	SessionFactory factory = new Configuration()
    			.configure("hibernate.cfg.xml")
    			.addAnnotatedClass(Album.class)
    			.buildSessionFactory();
    	
    	Session session = factory.getCurrentSession();
    	
    	try {
    		Album album = session.get(Album.class, 1);
    		System.out.println(album.getTitlu());
    	} catch(HibernateException e) {
    		e.printStackTrace();
    	} finally {
    		factory.close();
    	}
    	
    }
    
    private static void writeToFile() throws IOException{
    	String url = "jdbc:mysql://localhost:3306/selir_practica_db";
        String user = "root";
        String password = "robertmaster1";
        ArrayList<String> list = new ArrayList<String>();
        
        try(DataOutputStream locFile = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("connection.dat")))) {
        	locFile.writeUTF(url);
        	locFile.writeUTF(user);
        	locFile.writeUTF(password);
        }
    }
    
    public static boolean isValidHtmlEscapeCode(String string) {
    	String temp = "";
    	try {
    	    //temp = StringEscapeUtils.unescapeHtml4(string);
    	} catch (IllegalArgumentException e) {
    	    return false;
    	}
    	return !string.equals(temp);
    }
}
