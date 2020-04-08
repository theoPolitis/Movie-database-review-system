package app.dao;

import app.dao.utils.DatabaseUtils;
import app.model.Person;
import app.model.Show;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class ShowDAO {
	
	public static Show getShowSelector(String showTitle, int id) {
		String sqlId = "SELECT * "+ "FROM imbd.show WHERE showid LIKE '%" + id + "%'";
		String sqlTitle = "SELECT * "+ "FROM imbd.show WHERE show_title LIKE '%" + showTitle + "%'";
		
		if(id > 0) {
			return getShow(null, id, sqlId);
		}else if(showTitle != null) {
			return getShow(showTitle, 0, sqlTitle);
		}
		
		return null;
	}
	
	//get the show by title
    public static Show getShow(String showTitle, int id, String sqlFormat) {
        // Fish out the results
        List<Show> shows = new ArrayList<>();

        try {
            // Execute the query
            Connection connection = DatabaseUtils.connectToDatabase();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sqlFormat);

            // If you have multiple results, you do a while
            while(result.next()) {
                // 2) Add it to the list we have prepared
                shows.add(new Show(result.getInt("showid"), result.getString("show_title"),
                		result.getDouble("length"), result.getBoolean("movie"), result.getBoolean("series"),
                		result.getString("genre"), result.getInt("year"), result.getInt("proco_id")));
            }

            // Close it
            DatabaseUtils.closeConnection(connection);
        }
        catch (Exception e) {
            e.printStackTrace();
        }


        // If there is a result
        if(!shows.isEmpty()) return shows.get(0);
        // If we are here, something bad happened
        return null;
    }
}