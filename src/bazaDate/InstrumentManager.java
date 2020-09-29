package bazaDate;
import java.sql.*;
import java.util.ArrayList;

public class InstrumentManager {

    ConnectionManager connMan = new ConnectionManager();

    public Instrument getInstrument(String nume) {
        Connection connection = null;
        PreparedStatement statement = null;
        Instrument instrument = null;
        ResultSet result = null;

        try {
        	connection = connMan.openConnection();
        	if(connection != null) {
        		String sql = "SELECT id_instr FROM instrumente WHERE nume=?";
                statement = connection.prepareStatement(sql);
                statement.setString(1, nume);
                result = statement.executeQuery();
                result.next();
                instrument = new Instrument(result.getInt(1), nume);
        	}
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connMan.closeConnection(connection, statement, result);
        }

        return instrument;
    }

    public ArrayList<String> getAllInstrumentNames() {
        Connection connection = null;
        ArrayList<String> instrumentList = new ArrayList<>();
        Statement statement = null;
        ResultSet result = null;

        try {
        	connection = connMan.openConnection();
        	if(connection != null) {
        		statement = connection.createStatement();
                String sql = "SELECT nume FROM instrumente";
                result = statement.executeQuery(sql);
                while(result.next()) {
                    instrumentList.add(result.getString(1));
                }	
        	}
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connMan.closeConnection(connection, statement, result);
        }

        return instrumentList;
    }

    public ArrayList<Instrument> getInstrumentsForArtist(Artist artist) {
        Connection connection = null;
        ArrayList<Instrument> instrumentList = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
        	connection = connMan.openConnection();
        	if(connection != null) {
        		String sql = "SELECT i.id_instr, i.nume " +
                        "FROM abilitati ab " +
                        "JOIN instrumente i " +
                        "USING(id_instr) " +
                        "WHERE ab.id_artist = ?";
                statement = connection.prepareStatement(sql);
                statement.setInt(1, artist.getId());
                result = statement.executeQuery();

                while(result.next()) {
                    int id = result.getInt("id_instr");
                    String nume = result.getString("nume");

                    Instrument instrument = new Instrument(id, nume);
                    instrumentList.add(instrument);
                }
        	}
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connMan.closeConnection(connection, statement, result);
        }

        return instrumentList;
    }

    public boolean addInstrumentToArtist(Instrument instrument, Artist artist) {
        Connection connection = null;
        PreparedStatement statement = null;
        int rowsInserted = 0;

        try {
        	connection = connMan.openConnection();
        	if(connection != null) {
        		String sql = "INSERT INTO abilitati (id_artist, id_instr) VALUES (?, ?)";
                statement = connection.prepareStatement(sql);
                statement.setInt(1, artist.getId());
                statement.setInt(2, instrument.getId());

                rowsInserted = statement.executeUpdate();
        	}
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connMan.closeConnection(connection, statement);
        }

        return rowsInserted != 0;
    }

    public boolean addInstrument(Instrument instrument) {
        Connection connection = null;
        PreparedStatement statement = null;
        int rowsInserted = 0;

        try {
        	connection = connMan.openConnection();
        	if(connection != null) {
        		String sql = "INSERT INTO instrumente (nume) VALUES (?)";
                statement = connection.prepareStatement(sql);
                statement.setString(1, instrument.getNume());

                rowsInserted = statement.executeUpdate();
        	}
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connMan.closeConnection(connection, statement);
        }

        return rowsInserted != 0;
    }
}
