package app.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import app.dao.utils.DatabaseUtils;
import app.model.CreditsRoll;

public class CreditsRollDAO {
	public static List<CreditsRoll> getCreditsRollByMovieId(int showId) {
        // Fish out the results
        List<CreditsRoll> creditsRoll = new ArrayList<>();

        try {
            // Here you prepare your sql statement
            String sql = "SELECT * "+ "FROM imbd.credits_roll WHERE show_id = " + showId;

            // Execute the query
            Connection connection = DatabaseUtils.connectToDatabase();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            // If you have multiple results, you do a while
            while(result.next()) {
                // 2) Add it to the list we have prepared
            	creditsRoll.add(new CreditsRoll(PersonDAO.getActorSelector(null, result.getInt("person_id")), result.getString("character_name"),
            			result.getString("role"), result.getInt("start_year"), result.getInt("show_id")));	
            }
            // Close it
            DatabaseUtils.closeConnection(connection);
        }
        catch (Exception e) {
            e.printStackTrace();
        }


        // If there is a result
        if(!creditsRoll.isEmpty()) return creditsRoll;
        // If we are here, something bad happened
        return null;
    }
	
	
	//essentially the same method as above but takes the person id instead
	public static List<CreditsRoll> getMoviesByActor(int personId){
		 // Fish out the results
        List<CreditsRoll> creditsRoll = new ArrayList<>();

        try {
            // Here you prepare your sql statement
            String sql = "SELECT * "+ "FROM imbd.credits_roll WHERE person_id = " + personId;

            // Execute the query
            Connection connection = DatabaseUtils.connectToDatabase();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            // If you have multiple results, you do a while
            while(result.next()) {
                // 2) Add it to the list we have prepared
            	creditsRoll.add(new CreditsRoll(PersonDAO.getActorSelector(null, result.getInt("person_id")), result.getString("character_name"),
            			result.getString("role"), result.getInt("start_year"), result.getInt("show_id")));
            }

            // Close it
            DatabaseUtils.closeConnection(connection);
        }
        catch (Exception e) {
            e.printStackTrace();
        }


        // If there is a result
        if(!creditsRoll.isEmpty()) return creditsRoll;
        // If we are here, something bad happened
        return null;
    }

    public static void alterCredits(String sqlFormat) {
        try {
            // open a connection to the database
            Connection connection = DatabaseUtils.connectToDatabase();
            Statement statement = connection.createStatement();

            statement.executeUpdate(sqlFormat);

            // close connection to the database
            DatabaseUtils.closeConnection(connection);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
