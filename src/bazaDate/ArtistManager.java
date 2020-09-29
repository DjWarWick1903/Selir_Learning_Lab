package bazaDate;

import java.sql.*;
import java.util.LinkedList;

public class ArtistManager {

	ConnectionManager connMan = new ConnectionManager();

	// Get collection of all artist names
	public LinkedList<String> getAllArtistNames() {
		// declare list instance, sql interrogation and open connection
		LinkedList<String> artistList = new LinkedList<>();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;

		// try-catch-finally block that gets data
		try {
			connection = connMan.openConnection();
			System.out.println("aici");
			if (connection != null) {
				statement = connection.createStatement();
				String sql = "SELECT nume FROM artisti";
				result = statement.executeQuery(sql);

				while (result.next()) {
					artistList.add(result.getString(1));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connMan.closeConnection(connection, statement, result);
		}

		return artistList;
	}

	// Get collection of all artists that match the search criteria
	public LinkedList<String> getArtistsLikeName(String nume_search) {
		Connection connection = null;
		LinkedList<String> artistList = new LinkedList<>();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			connection = connMan.openConnection();
			if (connection != null) {
				String sql = "SELECT nume FROM artisti WHERE lower(nume) LIKE ?";
				statement = connection.prepareStatement(sql);
				statement.setString(1, "%" + nume_search.toLowerCase() + "%");
				result = statement.executeQuery();

				while (result.next()) {
					artistList.add(result.getString(1));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connMan.closeConnection(connection, statement, result);
		}

		return artistList;
	}

	// Get specific artist by name
	public Artist getArtistInstanceByName(String nume) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		Artist artist = null;

		try {
			connection = connMan.openConnection();
			if (connection != null) {
				String sql = "SELECT a1.id_artist, a2.nume, a1.an_debut, a1.nationalitate, a1.datan "
						+ "FROM artisti a1 " + "LEFT JOIN artisti a2 " + "ON a1.id_trupa = a2.id_artist "
						+ "WHERE a1.nume = ?";

				statement = connection.prepareStatement(sql);
				statement.setString(1, nume);
				result = statement.executeQuery();

				while (result.next()) {
					int id = result.getInt("id_artist");
					String trupa = result.getString("nume");
					int anDebut = Integer.parseInt(result.getDate("an_debut").toString().split("-")[0]);
					String nationalitate = result.getString("nationalitate");
					Date dataNasterii = result.getDate("datan");

					if (dataNasterii == null && trupa == null) {
						artist = new Artist(id, nume, anDebut, nationalitate);
					} else {
						if (trupa == null) {
							artist = new Artist(id, nume, anDebut, nationalitate, dataNasterii.toLocalDate());
						} else {
							artist = new Artist(id, nume, trupa, anDebut, nationalitate, dataNasterii.toLocalDate());
						}
					}
				}

				if (artist != null) {
					artist.setAbilitati(new InstrumentManager().getInstrumentsForArtist(artist));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connMan.closeConnection(connection, statement, result);
		}

		return artist;
	}

	public Artist getArtistInstanceByID(int id) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		Artist artist = null;

		try {
			connection = connMan.openConnection();
			if (connection != null) {
				String sql = "SELECT a1.nume, a2.nume AS trupa, a1.an_debut, a1.nationalitate, a1.datan "
						+ "FROM artisti a1 " + "LEFT JOIN artisti a2 " + "ON a1.id_trupa = a2.id_artist "
						+ "WHERE a1.id_artist = ?";
				statement = connection.prepareStatement(sql);
				statement.setInt(1, id);
				result = statement.executeQuery();

				while (result.next()) {
					String nume = result.getString("nume");
					String trupa = result.getString("trupa");
					int anDebut = Integer.parseInt(result.getDate("an_debut").toString().split("-")[0]);
					String nationalitate = result.getString("nationalitate");
					Date dataNasterii = result.getDate("datan");

					if (dataNasterii == null && trupa == null) {
						artist = new Artist(id, nume, anDebut, nationalitate);
					} else {
						if (trupa == null) {
							artist = new Artist(id, nume, anDebut, nationalitate, dataNasterii.toLocalDate());
						} else {
							artist = new Artist(id, nume, trupa, anDebut, nationalitate, dataNasterii.toLocalDate());
						}
					}
				}

				if (artist != null) {
					artist.setAbilitati(new InstrumentManager().getInstrumentsForArtist(artist));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connMan.closeConnection(connection, statement, result);
		}

		return artist;
	}

	public boolean updateArtistInDB(Artist artistToUpdate) {
		Connection connection = null;
		PreparedStatement statement = null;
		int rowsUpdated = 0;

		try {
			connection = connMan.openConnection();
			if (connection != null) {
				// se aduce din baza de date id-ul trupei, daca este cazul
				Artist trupa = null;
				if (!artistToUpdate.getTrupa().equals("-Trupa-") && !artistToUpdate.getTrupa().equals("-Solo-")) {
					trupa = new ArtistManager().getArtistInstanceByName(artistToUpdate.getTrupa());
				}

				String sql = "UPDATE artisti SET nume=?, id_trupa=?, an_debut=?, nationalitate=?, datan=? WHERE id_artist=?";
				statement = connection.prepareStatement(sql);

				statement.setString(1, artistToUpdate.getNume());
				if (trupa != null)
					statement.setInt(2, trupa.getId());
				else
					statement.setNull(2, Types.NULL);
				statement.setInt(3, artistToUpdate.getAnDebut());
				statement.setString(4, artistToUpdate.getNationalitate());
				if (artistToUpdate.getDataNasterii() == null)
					statement.setNull(5, Types.NULL);
				else
					statement.setObject(5, artistToUpdate.getDataNasterii());
				statement.setInt(6, artistToUpdate.getId());

				rowsUpdated = statement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connMan.closeConnection(connection, statement);
		}

		return rowsUpdated != 0;
	}

	public int addArtistInDB(Artist artistToAdd) {
		Connection connection = null;
		PreparedStatement statement = null;
		int newKey = 0;

		try {
			connection = connMan.openConnection();
			if (connection != null) {
				// Se aduce din baza de date id-ul trupei, daca este cazul
				Artist trupa = null;
				if (!artistToAdd.getTrupa().equals("-Trupa-") && !artistToAdd.getTrupa().equals("-Solo-")) {
					trupa = new ArtistManager().getArtistInstanceByName(artistToAdd.getTrupa());
				}

				String sql = "INSERT INTO artisti (nume, id_trupa, an_debut, nationalitate, datan) VALUES (?,?,?,?,?)";
				statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

				statement.setString(1, artistToAdd.getNume());
				if (trupa != null)
					statement.setInt(2, trupa.getId());
				else
					statement.setNull(2, Types.NULL);
				statement.setInt(3, artistToAdd.getAnDebut());
				statement.setString(4, artistToAdd.getNationalitate());
				if (artistToAdd.getDataNasterii() == null)
					statement.setNull(5, Types.NULL);
				else
					statement.setObject(5, artistToAdd.getDataNasterii());

				// Se aduce din baza de date cheia cu care a fost asociat artistul nou
				int rowsInserted = 0;
				rowsInserted = statement.executeUpdate();
				if (rowsInserted != 0) {
					ResultSet keySet = statement.getGeneratedKeys();
					if (keySet.next()) {
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

	public boolean deleteByInstance(Artist artist) {
		Connection connection = null;
		PreparedStatement statement = null;
		int rowsDeleted = 0;

		try {
			connection = connMan.openConnection();
			if (connection != null) {
				String sql = "DELETE FROM artisti WHERE id_artist=?";
				statement = connection.prepareStatement(sql);
				statement.setInt(1, artist.getId());

				rowsDeleted = statement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connMan.closeConnection(connection, statement);
		}

		return rowsDeleted != 0;
	}

	public boolean deleteByID(int id) {
		Connection connection = null;
		PreparedStatement statement = null;
		int rowsDeleted = 0;

		try {
			connection = connMan.openConnection();
			if (connection != null) {
				String sql = "DELETE FROM artisti WHERE id_artist=?";
				statement = connection.prepareStatement(sql);
				statement.setInt(1, id);

				rowsDeleted = statement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connMan.closeConnection(connection, statement);
		}

		return rowsDeleted != 0;
	}

}
