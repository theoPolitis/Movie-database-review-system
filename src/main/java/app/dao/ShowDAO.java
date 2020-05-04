package app.dao;

import app.dao.utils.DatabaseUtils;
import app.model.Show;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class ShowDAO {

	public final static long MILLIS_PER_DAY = 24 * 60 * 60 * 1000L;

	// collection of all the shows in the database
	private static List<Show> allShows = getAllShows();

	public static Show getShowByTitle(String title) {

		for (Show show : allShows) {
			// checks to see if the show title matches the parameter
			if (title != null) {
				if (show.getShowTitle().toLowerCase().contains(title.toLowerCase())) {
					// checks to see if the date is greater than 24 hours for anything PCO submits
					if (show.getStatus().equals("Submitted")) {
						Date now = new Date();
						Date entryDate = show.getTimeStamp();
						boolean moreThanDay = Math.abs(now.getTime() - entryDate.getTime()) > MILLIS_PER_DAY;

						if (moreThanDay) {
							return show;
						}

					} else {
						return show;
					}
				}
			}
		}

		return null;
	}

	// gets any show from the collection that has a matching showId
	public static Show getShowById(int id) {
		for (Show show : allShows) {
			if (show.getShowid() == id) {
				return show;
			}
		}

		return null;
	}

	// returns a list of shows that have a matching status to the parameter
	public static List<Show> getShowsByStatus(String status) {
		List<Show> showsWithStatus = new ArrayList<Show>();

		for (Show show : allShows) {
			if (show.getStatus().equalsIgnoreCase(status)) {
				showsWithStatus.add(show);
			}
		}

		return showsWithStatus;
	}

	// BELOW ALL OPEN CONNECTIONS TO THE DATABASE FOR INSERTING AND RETRIEVING DATA

	// creates a brand new show and places it into the database
	public static void createShowEntry(String title, String genre, float length, int movie, int series, int proco_id,
			int year, String status) {
		// sql string for inserting show into the database
		String sql = "INSERT INTO imbd.show(show_title, genre, length, "
				+ "movie, series, proco_id, year, status, entryDate)" + "VALUES('" + title + "', '" + genre + "', "
				+ length + ", " + movie + ", " + series + ", " + proco_id + ", " + year + ", '" + status
				+ "', CURRENT_TIMESTAMP);";

		try {
			// opens a connection to the database
			Connection connection = DatabaseUtils.connectToDatabase();
			// create statement object
			Statement statement = connection.createStatement();
			// use .executeUpdate() for inserting items into the database
			statement.executeUpdate(sql);

			// close connection
			DatabaseUtils.closeConnection(connection);
			// update the list
			allShows = getAllShows();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// used for altering data and deleting data for show that is in the database
	// possibility to be used for all future update querrys for the database
	public static void alterShow(String sqlFormat) {
		try {
			// open a connection to the database
			Connection connection = DatabaseUtils.connectToDatabase();
			Statement statement = connection.createStatement();

			statement.executeUpdate(sqlFormat);

			// close connection to the databsse
			DatabaseUtils.closeConnection(connection);
			// update the show list
			allShows = getAllShows();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// returns a list of all the shows in the database
	private static List<Show> getAllShows() {
		// instantiate a list to return
		List<Show> shows = new ArrayList<>();

		try {
			// basic sql querry for retreiveing all shows
			String sqlQuerry = "SELECT * FROM imbd.show";
			// Execute the query
			Connection connection = DatabaseUtils.connectToDatabase();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sqlQuerry);

			// If you have multiple results, you do a while
			while (result.next()) {
				// 2) Add it to the list we have prepared
				shows.add(new Show(result.getInt("showid"), result.getString("show_title"), result.getDouble("length"),
						result.getBoolean("movie"), result.getBoolean("series"), result.getString("genre"),
						result.getInt("year"), result.getInt("proco_id"), result.getString("status"),
						result.getDate("entryDate")));
			}

			// Close it
			DatabaseUtils.closeConnection(connection);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// If there is a result
		if (!shows.isEmpty())
			return shows;
		// If we are here, something bad happened
		return null;
	}

}