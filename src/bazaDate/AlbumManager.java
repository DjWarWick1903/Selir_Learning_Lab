package bazaDate;
import java.sql.*;
import java.util.LinkedList;

public class AlbumManager {

    ConnectionManager connMan = new ConnectionManager();

    public LinkedList<String> getAllAlbumNames() {
        LinkedList<String> albumList = new LinkedList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;

        try {
        	connection = connMan.openConnection();
        	if(connection != null ) {
        		statement = connection.createStatement();
                String sql = "SELECT titlu FROM albume";
                result = statement.executeQuery(sql);

                while(result.next()) {
                    albumList.add(result.getString(1));
                }
        	}
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connMan.closeConnection(connection, statement, result);
        }

        return albumList;
    }

    public Album getAlbumInstance(String nume) {
    	Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        Album album = null;
        
        try {
        	connection = connMan.openConnection();
        	if(connection != null) {
        		String sql = "SELECT al.id_album, al.titlu, al.an, ar.nume "
                		+ "FROM albume al "
                		+ "JOIN artisti ar "
                		+ "USING(id_artist) "
                		+ "WHERE al.titlu=?";
                statement = connection.prepareStatement(sql);
                statement.setString(1, nume);
                result = statement.executeQuery();

                while(result.next()) {
                    String titlu = result.getString("titlu");
                    int id = result.getInt("id_album");
                    String an = result.getDate("an").toString().split("-")[0];
                    String artist = result.getString("nume");
                    
                    album = new Album(id, titlu, an, artist);
                }
        	}
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connMan.closeConnection(connection, statement, result);
        }
        
        return album;
    }
    
    public LinkedList<String> getAlbumsLikeName(String nume_search) {
        Connection connection = null;
        LinkedList<String> albumList = new LinkedList<>();
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
        	connection = connMan.openConnection();
        	if(connection != null) {
        		String sql = "SELECT titlu FROM albume WHERE lower(titlu) LIKE ?";
                statement = connection.prepareStatement(sql);
                statement.setString(1, "%" + nume_search.toLowerCase() + "%");
                result = statement.executeQuery();

                while (result.next()) {
                    albumList.add(result.getString(1));
                }
        	}
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connMan.closeConnection(connection, statement, result);
        }

        return albumList;
    }
    
    public boolean updateAlbum(Album album) {
        Connection connection = null;
        PreparedStatement statement = null;
        int rowsUpdated = 0;

        try {
        	connection = connMan.openConnection();
        	if(connection != null) {
        		//se aduce id-ul artistului in eventualitatea in care este schimbat artistul albumului
                Artist artist = new ArtistManager().getArtistInstanceByName(album.getArtist());

                //se creaza si executa comanda UPDATE in baza de date
                String sql = "UPDATE albume SET titlu=?, an=?, id_artist=? WHERE id_album=?";
                statement = connection.prepareStatement(sql);
                statement.setString(1, album.getTitlu());
                statement.setString(2, album.getAnAparitie());
                statement.setInt(3, artist.getId());
                statement.setInt(4, album.getId());
                rowsUpdated = statement.executeUpdate();
        	}
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connMan.closeConnection(connection, statement);
        }

        return rowsUpdated != 0;
    }

    public boolean deleteAlbum(Album album) {
        Connection connection = null;
        PreparedStatement statement = null;
        int rowsDeleted = 0;

        try {
        	connection = connMan.openConnection();
        	if(connection != null) {
        		String sql = "DELETE FROM albume WHERE id_album=?";
                statement = connection.prepareStatement(sql);
                statement.setInt(1, album.getId());

                rowsDeleted = statement.executeUpdate();
        	}
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connMan.closeConnection(connection, statement);
        }

        return rowsDeleted != 0;
    }

    public int addAlbum(Album album) {
        Connection connection = null;
        PreparedStatement statement = null;
        int newKey = 0;

        try {
        	connection = connMan.openConnection();
        	if(connection != null) {
        		//se aduce id-ul artistului
                Artist artist = new ArtistManager().getArtistInstanceByName(album.getArtist());

                //se creaza si executa comanda INSERT in baza de date
                String sql = "INSERT INTO albume (titlu, an, id_artist) VALUES (?, ?, ?)";
                statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, album.getTitlu());
                statement.setString(2, album.getAnAparitie());
                statement.setInt(3, artist.getId());

                //Se aduce din baza de date cheia cu care a fost asociat albumul nou
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
