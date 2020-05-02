package app.model;



public class Account {
    private String firstName;
    private String lastName;
    private String address;
    private String username;
    /**
     * Only stores hashed passwords.
     */
    private String password;
	private String country;
    private String gender;
    private String email;
    private boolean admin;
    private boolean proco;



    public Account(String un, String p, boolean admin, boolean proco) {
        username = un;
        password = p;
        this.admin = admin;
        this.proco = proco;
    }


    public Account(String fn, String ln, String a, String c, String g, String email) {
       this.firstName = fn;
       this.lastName = ln;
       this.address = a;
       this.country = c;
       this.gender = g;
       this.email = email;
       
    }




    public void updateDetails(String fn, String ln, String a, String c, String g, String email) {
        this.firstName = fn;
        this.lastName = ln;
        this.address = a;
        this.country = c;
        this.gender = g;
        this.email = email;
    }



    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
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


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
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

	public boolean getAdmin() { 
		return admin; 
	}

	public void setAdmin() { 
		this.admin = true; 
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("userName: " + this.username);
		sb.append("admin: " + this.admin);
		
		return sb.toString();
	}

}
