package app.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import app.dao.utils.DatabaseUtils;
import app.model.UserReview;

public class RatingDAO {
	//a varaiable accesable by all methods that contains information about the user reviews in the database
	private static List<UserReview> allReviews = getAllReviews();
	
	//method to insert into the database
	public static void insertReviewIntoDataBase(UserReview uR) {
		try {
			//prepare sql statement for inserting into the database
			String sql = "INSERT INTO imbd.user_review(reviewId, show_id, user_id, rating, review, date) " + "VALUES("
					+ uR.getReviewId() + ", " + uR.getShowId() + ", '" + uR.getUserName() + "', " + uR.getRating()
					+ ", '" + uR.getReview() + "', CURRENT_TIMESTAMP);";

			//open up the connection to the database
			Connection connection = DatabaseUtils.connectToDatabase();
			Statement statement = connection.createStatement();
			//use execute update to insert into the database
			statement.executeUpdate(sql);

			//need to update the reviews array with the latest entry
			allReviews = getAllReviews();
			
			// Close the connection
			DatabaseUtils.closeConnection(connection);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//method to get alll the reviews located in the database
	private static List<UserReview> getAllReviews() {
		List<UserReview> uR = new ArrayList<UserReview>();
		
		try {
			//sql statement to get all the data
			String sql = "SELECT * FROM imbd.user_review;";
			
			//open up a connection to the database
			Connection connection = DatabaseUtils.connectToDatabase();
			Statement statement = connection.createStatement();
			//puts all the data from the database into the resultSet
			ResultSet result = statement.executeQuery(sql);
			
			//adds all the data as objects in java so that we can do something with the information.
			while (result.next()) { 
				// 2) Add it to the list we have prepared
				uR.add(new UserReview(result.getInt("reviewId"), result.getInt("show_id"), result.getString("user_id"),
								result.getInt("rating"), result.getString("review"), result.getDate("date")));
			}
			
			//close the connection
			DatabaseUtils.closeConnection(connection);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (!uR.isEmpty()) {
			return uR;
		}
		// If we are here, something bad happened
		return null;
	}
	
	
	
	//gets the reviews for a particular show that we want from the List
	public static List<UserReview> getShowReviews(int showId){
		List<UserReview> showReviews = new ArrayList<UserReview>();
		
		if(allReviews != null) {
			//iterates through the array untill we find something
			for(UserReview uR : allReviews) {
				if(uR.getShowId() == showId) {
					showReviews.add(uR);	
				}
			}
		}
		
		return showReviews;
	}
	
	//returns all the reviews
	public static List<UserReview> getAllUserReview(){
		return allReviews;
	}
	
	//returns the size of the list
	public static int getArraySize() {
		if(allReviews != null) {
			return allReviews.size();
		}
		return 0;
	}
	
	//returns the average amount of ratings for a particular show
	public static int showAverageRating(int showId) {
		int average = 0;
		int count = 0;
		
		//counts the ratings
		if(allReviews != null) {
			for(UserReview review : allReviews) {
				if(review.getShowId() == showId) {
					average += review.getRating();
					count++;
				}
			}
		}
		
		//returns the average 
		if(average != 0) {
			return average / count;
		}
		return 0;
	}
		
}
