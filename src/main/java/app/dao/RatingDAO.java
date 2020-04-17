package app.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import app.dao.utils.DatabaseUtils;
import app.model.ProductionCompany;
import app.model.UserReview;

public class RatingDAO {
	
	public static UserReview getUserReview(int showId, String userId) {
        // Fish out the results
        List<UserReview> userReview = new ArrayList<>();

        try {
            // Here you prepare your sql statement
            String sql = "SELECT * "+ "FROM imbd.user_review WHERE show_id =" + showId +  " AND user_id = '" + userId + "'";

            // Execute the query
            Connection connection = DatabaseUtils.connectToDatabase();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            // If you have multiple results, you do a while
            while(result.next()) {
                // 2) Add it to the list we have prepared
                userReview.add(new UserReview(result.getInt("reviewId"), result.getInt("show_id"), result.getString("user_id"), result.getInt("rating"),
                		result.getString("review"), result.getDate("Date")));
            }

            // Close it
            DatabaseUtils.closeConnection(connection);
        }
        catch (Exception e) {
            e.printStackTrace();
        }


        // If there is a result
        if(!userReview.isEmpty()) return userReview.get(0);
        // If we are here, something bad happened
        return null;
    }
	
	public static void insertReviewIntoDataBase(UserReview uR) {
        try {
            // Here you prepare your sql statement
            String sql = "INSERT INTO imbd.user_review(reviewId, show_id, user_id, rating, review, date)\n" + 
            		"VALUES(" + uR.getReviewId() + ", " + uR.getShowId() + ", '" + uR.getUserName() + "', " +
            		uR.getRating() + ", '" + uR.getReview() + "', '" + uR.getDate() + ");";

            // Execute the query
            Connection connection = DatabaseUtils.connectToDatabase();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            // If you have multiple results, you do a while
           

            // Close it
            DatabaseUtils.closeConnection(connection);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	


}
