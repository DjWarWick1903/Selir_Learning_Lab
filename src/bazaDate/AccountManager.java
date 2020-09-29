package bazaDate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class AccountManager {
	
	ConnectionManager connMan = new ConnectionManager();
	
	// method will return account id if it exists, else it will return 0
	public int checkAccountExists(Account acc) {
		Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        int id = 0;

        try {
        	connection = connMan.openConnection();
        	if(connection != null) {
        		String sql = "SELECT id_user "
            			+ "FROM conturi "
            			+ "WHERE user=?";
            	statement = connection.prepareStatement(sql);
                statement.setString(1, acc.getUsername());
                result = statement.executeQuery();
                
                while (result.next()) {
                    id = result.getInt(1);
                }
        	}
        } catch (SQLException e) {
        	e.printStackTrace();
        } finally {
        	connMan.closeConnection(connection, statement, result);
        }
        
        if(id != 0) {
        	acc.setId(id);
        }
        
        return id;
	}
	
	// check if account with inserted username and password exists
	public boolean verifyAccount(Account acc) {
		Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        int id = 0;
        boolean dataIsCorrect = false;

        try {
        	connection = connMan.openConnection();
        	if(connection != null) {
        		String sql = "SELECT id_user "
            			+ "FROM conturi "
            			+ "WHERE user=?";
            	statement = connection.prepareStatement(sql);
                statement.setString(1, acc.getUsername());
                result = statement.executeQuery();
                
                while (result.next()) {
                    id = result.getInt(1);
                }
        	}
        } catch (SQLException e) {
        	e.printStackTrace();
        } finally {
        	connMan.closeConnection(connection, statement, result);
        }
        
        if(id != 0) {
        	acc.setId(id);
        	dataIsCorrect = true;
        }
        
        return dataIsCorrect;
	}
	
	public int createAccount(Account acc, int priv) {
		Connection connection = null;
        PreparedStatement statement = null;
        int newKey = 0;

        //first we check if account already exists (by username) and add it if not
        //also, if priv != 0/1, the account will not be created (developers take care)
        int exists = checkAccountExists(acc);
        
        if(exists == 0 && (priv == 0 || priv == 1)) {
        	try {
        		connection = connMan.openConnection();
        		if(connection != null) {
        			String sql = "INSERT INTO conturi (user, parola, admin) VALUES (?,?,?)";
                    statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    
                    statement.setString(1, acc.getUsername());
                    statement.setString(2, acc.getPassword());
                    statement.setInt(3, priv);
                    
                    int rowsInserted = 0;
                    rowsInserted = statement.executeUpdate();
                    if(rowsInserted != 0) {
                        ResultSet keySet = statement.getGeneratedKeys();
                        if(keySet.next()) {
                            newKey = keySet.getInt(1);
                        }
                    }
        		}
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                connMan.closeConnection(connection, statement);
            }
        }

        return newKey;
	}
	
	// method will return account priviledge(0 - normal user / 1 - admin user), 
	// or it will return 2 if a problem occured
	public int getAccountPriviledge(Account acc) {
		Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        int id = 2;
        
        try {
        	connection = connMan.openConnection();
        	if(connection != null) {
        		String sql = "SELECT admin "
            			+ "FROM conturi "
            			+ "WHERE id_user=?";
            	statement = connection.prepareStatement(sql);
                statement.setInt(1, acc.getId());
                result = statement.executeQuery();
                
                while (result.next()) {
                    id = result.getInt(1);
                }
        	}
        } catch (SQLException e) {
        	e.printStackTrace();
        } finally {
        	connMan.closeConnection(connection, statement, result);
        }
        
        if(id == 0 || id == 1) {
        	acc.setPriv(id);
        }
        
        return id;
	}
}
