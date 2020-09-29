package bazaDate;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;

public class ConnectionManager {

    public Connection openConnection() throws SQLException {
        Connection myConn = null;
        String[] array = readFromFile();  
        
        if(array[0] != null && array[1] != null && array[2] != null)
        	myConn = DriverManager.getConnection(array[0], array[1], array[2]);
        
        return myConn;
    }

    public void closeConnection(Connection connection, Statement statement, ResultSet resultset) {
        try {
            if (resultset != null)
                resultset.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (statement != null)
                statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection(Connection connection, PreparedStatement statement) {
        try {
            if (statement != null)
                statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private static String[] readFromFile() {
    	String[] array = new String[3];
    	Path path = FileSystems.getDefault().getPath("connection.dat");
 
//    	"C:\\Users\\Popescu Robert Ionut\\eclipse-workspace\\Selir_Practica_DB_JSP\\connection.dat"
//    	try(DataInputStream locFile = new DataInputStream(new BufferedInputStream(new FileInputStream(path.toAbsolutePath().toString())))) {
    	
    	
    	try(DataInputStream locFile = new DataInputStream(new BufferedInputStream(new FileInputStream("C:\\Users\\Popescu Robert Ionut\\eclipse-workspace\\Selir_Practica_DB_JSP\\connection.dat")))) {
    		array[0] = locFile.readUTF();
    		array[1] = locFile.readUTF();
    		array[2] = locFile.readUTF();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	
    	return array;
    }

}
