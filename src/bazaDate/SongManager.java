package bazaDate;
import java.sql.*;
import java.util.LinkedList;

public class SongManager {

     ConnectionManager connMan = new ConnectionManager();

    public LinkedList<Song> getAllSongs() {
    	//declare list instance, sql interrogation and open connection
        LinkedList<Song> songList = new LinkedList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;
        
        //try-catch-finally block that gets data
        try {
        	connection = connMan.openConnection();
        	if(connection != null) {
        		statement = connection.createStatement();
                String sql = "SELECT nume, durata, nr_ordine, id_mel FROM melodii";
                result = statement.executeQuery(sql);

                while (result.next()) {
                	int id = result.getInt("id_mel");
                    String nume = result.getString("nume");
                    Time durata = result.getTime("durata");
                    int ordine = result.getInt("nr_ordine");
                    
                    songList.add(new Song(id, nume, durata, ordine));
                }
        	}
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connMan.closeConnection(connection, statement, result);
        }

        return songList;
    }
    
    public LinkedList<Song> getSongsLikeName(String nume_search) {
    	Connection connection = null;
        LinkedList<Song> songList = new LinkedList<>();
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
        	connection = connMan.openConnection();
        	if(connection != null) {
        		String sql = "SELECT id_mel, nume, durata, nr_ordine FROM melodii WHERE lower(nume) LIKE ?";
                statement = connection.prepareStatement(sql);
                statement.setString(1, "%" + nume_search.toLowerCase() + "%");
                result = statement.executeQuery();

                while (result.next()) {
                	
                	int id = result.getInt("id_mel");
                    String nume = result.getString("nume");
                    Time durata = result.getTime("durata");
                    int ordine = result.getInt("nr_ordine");
                    
                    songList.add(new Song(id, nume, durata, ordine));
                }
        	}
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connMan.closeConnection(connection, statement, result);
        }

        return songList;
    }
    
    public boolean updateSongInDB(Song melodie) {
        Connection connection = null;
        PreparedStatement statement = null;
        int rowsUpdated = 0;

        try {
        	connection = connMan.openConnection();
        	if(connection != null) {
        		String sql = "UPDATE melodii SET nume=?, durata=?, nr_ordine=? WHERE id_mel=?";
                statement = connection.prepareStatement(sql);
                statement.setString(1, melodie.getNume());
                statement.setTime(2, melodie.getDurata());
                statement.setInt(3, melodie.getNr_ordine());
                statement.setInt(4, melodie.getId());

                rowsUpdated = statement.executeUpdate();
        	}
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connMan.closeConnection(connection, statement);
        }

        return rowsUpdated != 0;
    }

    public boolean deleteSongFromDB(Song melodie) {
        Connection connection = null;
        PreparedStatement statement = null;
        int rowsDeleted = 0;

        try {
        	connection = connMan.openConnection();
        	if(connection != null) {
        		String sql = "DELETE FROM melodii WHERE id_mel=?";
                statement = connection.prepareStatement(sql);
                statement.setInt(1, melodie.getId());

                rowsDeleted = statement.executeUpdate();
        	}
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connMan.closeConnection(connection, statement);
        }

        return rowsDeleted != 0;
    }

    public int addSongInDB(Song melodie, Album album) {
        Connection connection = null;
        PreparedStatement statement = null;
        int newKey = 0;

        try {
        	connection = connMan.openConnection();
        	if(connection != null) {
        		String sql = "INSERT INTO melodii (nume, durata, nr_ordine, id_album) VALUES (?, ?, ?, ?)";
                statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, melodie.getNume());
                statement.setTime(2, melodie.getDurata());
                statement.setInt(3, melodie.getNr_ordine());
                statement.setInt(4, album.getId());

                //Se aduce din baza de date cheia cu care a fost asociata melodia noua
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

        return newKey;
    }
}
