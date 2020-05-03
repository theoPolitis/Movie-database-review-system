package app.model;


import java.util.List;


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
    
    public Show(int showid, String showTitle, double length, boolean isMovie, boolean isSeries,
    		String genre, int year, int proco_id, String status) {
    	this.showid = showid;
    	this.showTitle = showTitle;
    	this.length = length;
    	this.isMovie = isMovie;
    	this.isSeries = isSeries;
    	this.genre = genre;
    	this.year = year;
    	this.proco_id = proco_id;
    	this.status = status;
    }
    
    public String getImage() {
    	return "/img/shows/" + showid + ".jpg";
    }

    public int getProcoId() {
    	return proco_id;
    }
    
    public void setProcoId(int proco_id) {
    	this.proco_id = proco_id;
    }
    
	public int getShowid() {
		return showid;
	}

	public void setShowid(int showid) {
		this.showid = showid;
	}

	public String getShowTitle() {
		return showTitle;
	}

	public void setShowTitle(String showTitle) {
		this.showTitle = showTitle;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public boolean isMovie() {
		return isMovie;
	}

	public void setMovie(boolean isMovie) {
		this.isMovie = isMovie;
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

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public List<UserReview> getUserReviewList() {
		return userReviewList;
	}

	public void setUserReviewList(List<UserReview> userReviewList) {
		this.userReviewList = userReviewList;
	}

	public ProductionCompany getProductionCompany() {
		return productionCompany;
	}
	
	public String getStatus() {
		return status;
	}
	
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
