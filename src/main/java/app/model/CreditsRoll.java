package app.model;


public class CreditsRoll {
    private Person person;
    private String role;
    private String character;
    private int startYear;
    private int endYear;
    private int showId;




    public CreditsRoll(Person person, String character, String role, int startYear, int showId) {
        this.person = person;
        this.role = role;
        this.startYear = startYear;
        this.character = character;
        this.showId = showId;
    }
    
    public String toString() {
    	return person.toString();
    }
    
    public int getShowId() {
    	return showId;
    }
    
    public void setShowId(int showId) {
    	this.showId = showId;
    }


    public Person getPerson() {
        return person;
    }

    public String getRole() {
        return role;
    }

    public int getStartYear() {
        return startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }
}
