package app.dao;

import app.dao.utils.DatabaseUtils;
import app.model.Account;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;



public class AccountDAO {
    public static final String SALT = "$2a$10$h.dl5J86rGH7I8bD9bZeZe";


    /**
     * Method to fetch users from the database.
     * You should use this as an example for future queries, though the sql statement
     * will change -and you are supposed to write them.
     *
     * Current user: caramel 6, password (the password is "password" without quotes)
     * @param username what the user typed in the log in form.
     * @return Some of the user data to check on the password. Null if there
     *         no matching user.
     */
    private static List<Account> accounts = getAllAccounts();;
    
    public static Account getUserByUsername(String username) {
       for(Account account : accounts) {
    	   if(account.getUsername().equals(username)) {
    		   return account;
    	   }
       }
       
       return null;
    }
     
    
    public static void insertNewAccount(Account account, boolean isNormal) {
    	String sql = "";
    	String hashedPassword =  BCrypt.hashpw(account.getPassword(), BCrypt.gensalt());
    	int isAdmin = (account.isAdmin()) ? 1 : 0;
    	int isProco = (account.isProco()) ? 1 : 0;
    	int isFilmCritic = (account.isFilmCritic()) ? 1 : 0;
    	int approved = (account.isApproved()) ? 1 : 0;
    	
    	if(isNormal) {
    		sql = "INSERT INTO imbd.account (username, password, email, country, postCode, gender, year_of_birth, first_name, last_name, admin, proCo, film_critic, approved) "
    			+ "VALUES('" + account.getUsername() + "', '" + hashedPassword + "', '" + account.getEmail() + "', '" + account.getCountry() + "', '" + account.getPostCode() + "', '" + account.getGender() + "', '" + account.getYearOfBirth() + "', '" + account.getFirstName() + "', '" + account.getLastName() + "', '" + isAdmin + "', '" + isProco + "', '" + approved + "')";
    	}else {
    		sql = "INSERT INTO imbd.account (username, password, email, country, postCode, gender, year_of_birth, first_name, last_name, admin, proCo, film_critic, organisation_name, organisation_phone, approved)"
        			+ "VALUES('" + account.getUsername() + "', '" + hashedPassword + "', '" + account.getEmail() + "', '" + account.getCountry() + "', '" + account.getPostCode() + "', '" + account.getGender() + "', '" + account.getYearOfBirth() + "', '" + account.getFirstName() + "', '" + account.getLastName() + "', '" + isAdmin + "', '" + isProco + "', '" + isFilmCritic + "', '" + account.getOrganisationName() + "', '" + account.getOrganisationPhone() + "', '" + approved + "')";
    	}
    	
    	try {
    		System.out.println(account.getPostCode() + " " + account.getLastName());
			// opens a connection to the database
			Connection connection = DatabaseUtils.connectToDatabase();
			// create statement object
			Statement statement = connection.createStatement();
			// use .executeUpdate() for inserting items into the database
			statement.executeUpdate(sql);
			
			accounts = getAllAccounts();

			// close connection
			DatabaseUtils.closeConnection(connection);

		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    private static List<Account> getAllAccounts() {
    	List<Account> tmp = new ArrayList<Account>();
    	String sql = "SELECT * FROM imbd.account";
    	
    	try {
    		Connection connection = DatabaseUtils.connectToDatabase();
    		Statement statement = connection.createStatement();
    		ResultSet result = statement.executeQuery(sql);
    		
    		while(result.next()) {
    			tmp.add(new Account(result.getString("first_name"), result.getString("last_name"), result.getString("post_code"), result.getString("country"), result.getString("gender"), result.getInt("yearOfBirth"), result.getString("email"), result.getString("username"), result.getString("password"), result.getBoolean("admin"), result.getBoolean("proCo"), result.getBoolean("film_critic"), result.getBoolean("approved"), result.getString("organisation_phone"), result.getInt("organisation_phone")));
    		}
    		
    		
    	}catch(Exception e) {
    		
    	}
    	
    	if(!tmp.isEmpty()) {
    		return tmp;
    	}
    	
    	return null;
    }



}
