package app.dao;

import app.dao.utils.DatabaseUtils;
import app.model.Show;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class ShowDAO {
	
	private static List<Show> allShows = getAllShows();
	
	public static Show getShowByTitle(String title) {
		for(Show show : allShows) {
			if(show.getShowTitle().toLowerCase().contains(title.toLowerCase())) {
				return show;
			}
		}
		
		return null;
	}
	
	public static Show getShowById(int id) {
		for(Show show : allShows) {
			if(show.getShowid() == id) {
				return show;
			}
		}
		
		return null;
	}
	
	public static List<Show> getShowsByStatus(String status){
		List<Show> showsWithStatus = new ArrayList<Show>();
		
		for(Show show : allShows) {
			if(show.getStatus().equalsIgnoreCase(status)) {
				showsWithStatus.add(show);
			}
		}
		
		return showsWithStatus;
	}
	
    public static void createShowEntry(String title, String genre, float length, int movie, int series,  int proco_id, int year, String status) {

        String sql = "INSERT INTO imbd.show(show_title, genre, length, movie, series, proco_id, year, status, entryDate)" +
                "VALUES('" + title + "', '" + genre + "', " + length + ", " + movie + ", " + series + ", " + proco_id + ", " + year + ", '" + status + "', CURRENT_TIMESTAMP);";

        try {

            Connection connection = DatabaseUtils.connectToDatabase();
            Statement statement = connection.createStatement();

            statement.executeUpdate(sql);


            DatabaseUtils.closeConnection(connection);
            //update the list
            allShows = getAllShows();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void alterShow(String sqlFormat) {

	    String sql = sqlFormat;

        try {

            Connection connection = DatabaseUtils.connectToDatabase();
            Statement statement = connection.createStatement();

            statement.executeUpdate(sql);

            DatabaseUtils.closeConnection(connection);
            //update the show list
            allShows = getAllShows();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    private static List<Show> getAllShows(){
		List<Show> shows = new ArrayList<>();

		try {
			String sqlQuerry = "SELECT * FROM imbd.show";
			// Execute the query
			Connection connection = DatabaseUtils.connectToDatabase();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sqlQuerry);

			// If you have multiple results, you do a while
			while(result.next()) {
				// 2) Add it to the list we have prepared
				shows.add(new Show(result.getInt("showid"), result.getString("show_title"),
            		result.getDouble("length"), result.getBoolean("movie"), result.getBoolean("series"),
            		result.getString("genre"), result.getInt("year"), result.getInt("proco_id"), result.getString("status")));
        }

			// Close it
			DatabaseUtils.closeConnection(connection);
		}
		catch (Exception e) {
			e.printStackTrace();
		}


		// If there is a result
		if(!shows.isEmpty()) return shows;
		// If we are here, something bad happened
		return null;
	}

}