package app.model;


import java.util.List;
import java.util.Date;


public class Show {
    private int showid;
    private String showTitle;
    private double length;
    private boolean isMovie;
    private boolean isSeries;
    private String genre;
    private int year;
    private int proco_id;
    private List<UserReview> userReviewList;
    private ProductionCompany productionCompany;
    private String status;
    private Date entryDate;

    public Show(int showid, String showTitle, double length, boolean isMovie, boolean isSeries,
    		String genre, int year, int proco_id, String status, Date entryDate) {
    	this.showid = showid;
    	this.showTitle = showTitle;
    	this.length = length;
    	this.isMovie = isMovie;
    	this.isSeries = isSeries;
    	this.genre = genre;
    	this.year = year;
    	this.proco_id = proco_id;
    	this.status = status;
    	this.entryDate = entryDate;
    }
    
    //getters for the show object
    public String getImage() {
    	return "/img/shows/" + showid + ".jpg";
    }

    public int getProcoId() {
    	return proco_id;
    }
   
	public int getShowid() {
		return showid;
	}

	public String getShowTitle() {
		return showTitle;
	}

	public double getLength() {
		return length;
	}

	public boolean isMovie() {
		return isMovie;
	}

	public boolean isSeries() {
		return isSeries;
	}

	public void setSeries(boolean isSeries) {
		this.isSeries = isSeries;
	}

	public String getGenre() {
		return genre;
	}

	public int getYear() {
		return year;
	}
	
	public List<UserReview> getUserReviewList() {
		return userReviewList;
	}

	public ProductionCompany getProductionCompany() {
		return productionCompany;
	}
	
	public String getStatus() {
		return status;
	}

	public Date getTimeStamp() { return entryDate; }
	
	//string representation of show object
	public String toString() {
		StringBuilder sb =  new StringBuilder();
		
		sb.append("showid: " + showid + "\n");
		sb.append("title: " + showTitle + "\n");
		sb.append("movie Length: " + length + "\n");
		sb.append("Genre: " + genre + "\n");
		sb.append("year: " + year + "\n");
		if(isSeries) {
			sb.append("series: Yes" + "\n");
		}else {
			sb.append("showid: No" + "\n");
		}
		
		if(isMovie) {
			sb.append("Movie: Yes" + "\n");
		}else {
			sb.append("Movie: No" + "\n");
		}
		
		return sb.toString();
	}
    
    




}
