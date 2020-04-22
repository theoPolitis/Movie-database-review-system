package app.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import app.dao.utils.DatabaseUtils;
import app.model.UserReview;

public class RatingDAO {
	private static List<UserReview> allReviews = getAllReviews();
	
	public static void insertReviewIntoDataBase(UserReview uR) {
		try {
			// Here you prepare your sql statement
			String sql = "INSERT INTO imbd.user_review(reviewId, show_id, user_id, rating, review, date) " + "VALUES("
					+ uR.getReviewId() + ", " + uR.getShowId() + ", '" + uR.getUserName() + "', " + uR.getRating()
					+ ", '" + uR.getReview() + "', CURRENT_TIMESTAMP);";

			// Execute the query
			Connection connection = DatabaseUtils.connectToDatabase();
			Statement statement = connection.createStatement();
			statement.executeUpdate(sql);

			//need to update the reviews array with the latest entry
			allReviews = getAllReviews();
			
			// Close it
			DatabaseUtils.closeConnection(connection);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static List<UserReview> getAllReviews() {
		List<UserReview> uR = new ArrayList<UserReview>();
		
		try {
			// Here you prepare your sql statement
			String sql = "SELECT * FROM imbd.user_review;";
			// Execute the query
			Connection connection = DatabaseUtils.connectToDatabase();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			while (result.next()) {
				// 2) Add it to the list we have prepared
				uR.add(new UserReview(result.getInt("reviewId"), result.getInt("show_id"), result.getString("user_id"),
								result.getInt("rating"), result.getString("review"), result.getDate("date")));
			}
			
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
	
	public static List<UserReview> getShowReviews(int showId){
		List<UserReview> showReviews = new ArrayList<UserReview>();
		
		if(allReviews != null) {
			for(UserReview uR : allReviews) {
				if(uR.getShowId() == showId) {
					showReviews.add(uR);	
				}
			}
		}
		
		return showReviews;
	}
	
	public static UserReview getReview(int showId, String userId) {
		if(allReviews != null) {
			for(int i = 0; i < allReviews.size(); i++) {
				if(allReviews.get(i).getShowId() == showId && allReviews.get(i).getUserName().equals(userId)) {
					return allReviews.get(i);
				}
			}
		}
		return null;
	}
	
	public static List<UserReview> getAllUserReview(){
		return allReviews;
	}
	
	public static int getArraySize() {
		if(allReviews != null) {
			return allReviews.size();
		}
		return 0;
	}
	
	public static double showAverageRating(int showId) {
		int average = 0;
		int count = 0;
		
		if(allReviews != null) {
			for(UserReview review : allReviews) {
				if(review.getShowId() == showId) {
					average += review.getRating();
					count++;
				}
			}
		}
		
		if(average != 0) {
			return average / count;
		}
		return 0;
	}
		
}
