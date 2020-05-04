package app.model;

import java.util.Date;
public class UserReview {
    private int reviewId;
    private int showId;
    private String userName;
    private String review;
    private int rating;
    private Date date;

    public UserReview(int reviewId, int showId, String userName, int rating, String review, Date date) {
    	this.reviewId = reviewId;
        this.review = review;
        this.showId = showId;
        this.userName = userName;
        this.rating = rating;
        this.date = date;
    }
    
    public int getReviewId() {
    	return reviewId;
    }
    
    public int getShowId() {
    	return showId;
    }
    
    public String getUserName() {
    	return userName;
    }
    
    public String getReview() {
        return review;
    }

    public int getRating() {
        return rating;
    }


    public Date getDate() {
        return date;
    }
    
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	
    	sb.append("reviewid: " + reviewId);
    	sb.append("showId: " + showId);
    	sb.append("userName: " + userName);
    	sb.append("Rating: " + rating);
    	sb.append("Review: " + review);
    	sb.append("Date: " + date);
    	
    	return sb.toString();
    }
}
