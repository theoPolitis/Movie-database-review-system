package app.model;



public class Account {
    private String firstName;
    private String lastName;
    private int organisationPhone;
    private String organisationName;
    private String username;
    private int yearOfBirth;
    private String postCode;
    /**
     * Only stores hashed passwords.
     */
    private String password;
	private String country;
    private String gender;
    private String email;
    private boolean admin;
    private boolean proco;
    private boolean filmCritic;
    private boolean approved;

    public Account(String un, String p, boolean admin, boolean proco) {
        username = un;
        password = p;
        this.admin = admin;
        this.proco = proco;
    }

    //for film critic
    public Account(String fn, String ln, String postCode, String c, String gender, int yearOfBirth, String email, String un,
    		String password, boolean admin, boolean proco, boolean filmCritic, boolean approved) {
       this.firstName = fn;
       this.lastName = ln;
       this.country = c;
       this.gender = gender;
       this.email = email;
       this.username = un;
       this.password = password;
       this.admin = admin;
       this.proco = proco;      
       this.yearOfBirth = yearOfBirth;
       this.postCode = postCode;
       this.filmCritic = filmCritic;
       this.approved = approved;
    }
    
    public Account(String fn, String ln, String postCode, String c, String gender, int yearOfBirth, String email, String un,
    		String password, boolean admin, boolean proco, boolean filmCritic, boolean approved, String organisationName, int organisationPhone) {
       this.firstName = fn;
       this.lastName = ln;
       this.country = c;
       this.gender = gender;
       this.email = email;
       this.username = un;
       this.password = password;
       this.admin = admin;
       this.proco = proco;
       this.approved = approved;
       this.yearOfBirth = yearOfBirth;
       this.postCode = postCode;
       this.filmCritic = filmCritic;
       this.organisationName = organisationName;
       this.organisationPhone = organisationPhone;
    }

    public void updateDetails(String fn, String ln, String address, String c, String g, String email) {
        this.firstName = fn;
        this.lastName = ln;
        this.country = c;
        this.gender = g;
        this.email = email;
    }

	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getOrganisationPhone() {
		return organisationPhone;
	}

	public void setOrganisationPhone(int organisationPhone) {
		this.organisationPhone = organisationPhone;
	}

	public String getOrganisationName() {
		return organisationName;
	}

	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getYearOfBirth() {
		return yearOfBirth;
	}

	public void setYearOfBirth(int yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public boolean isProco() {
		return proco;
	}

	public void setProco(boolean proco) {
		this.proco = proco;
	}
	
	public boolean isFilmCritic() {
		return filmCritic;
	}
	
	public void setFilmCritic(boolean filmCritic) {
		this.filmCritic = filmCritic;
	}
	
	public boolean isApproved() {
		return approved;
	}
	
	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	//string representation of the object
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("userName: " + this.username);
		sb.append("admin: " + this.admin);
		
		return sb.toString();
	}

}
