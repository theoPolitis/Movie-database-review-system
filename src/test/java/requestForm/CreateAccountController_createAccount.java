package requestForm;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;

import app.dao.AccountDAO;
import app.model.Account;
import io.javalin.http.Context;

class CreateAccountController_createAccount {

	@Test
	public void noAccountTypeExist_NullPointerException_passedANullAccountType() {
		assertThrows(NullPointerException.class, () -> CreateAccountTest.accountTypetest(null));
	}
	
	@Test
	public void standardAccount_isApproved_passingStandardToMethod(){
		assertTrue(CreateAccountTest.accountTypetest("standard").isApproved());
	}
	
	@Test
	public void productionCompanyAccount_isProco_passingPCOToMethod(){
		assertTrue(CreateAccountTest.accountTypetest("PCO").isProco());
	}
	
	@Test
	public void filmCriticAccount_isFimCritic_passingFCoMethod(){
		assertTrue(CreateAccountTest.accountTypetest("FC").isFilmCritic());
	}
	
	@Test
	public void standardAccountOrganisation_null_passingStandardToMethod(){
		assertEquals(null, CreateAccountTest.accountTypetest("standard").getOrganisationName());
	}
	
	@Test
	public void PCOAccountOrganisation_name_passingPCOToMethod(){
		assertEquals("name", CreateAccountTest.accountTypetest("PCO").getOrganisationName());
	}
	
	@Test
	public void productionCompanyAccount_isNotApproved_passingPCOToMethod(){
		assertFalse(CreateAccountTest.accountTypetest("PCO").isApproved());
	}
	
	@Test
	public void filmCriticAccount_isNotApproved_passingPCOToMethod(){
		assertFalse(CreateAccountTest.accountTypetest("FC").isApproved());
	}
	
	@Test
	public void passwordIsEncrypted_isNotApproved_pasingPasswordToSaltPassword(){
		assertEquals("$2a$10$h.dl5J86rGH7I8bD9bZeZeci0pDt0.VwFTGujlnEaZXPf/q7vM5wO", CreateAccountTest.saltPassword("password"));
	}
	
	
	static class CreateAccountTest{
		public static final String SALT = "$2a$10$h.dl5J86rGH7I8bD9bZeZe";
		public static Account accountTypetest(String accountType) {
		   	 String firstName = "firstname";
		   	 String lastName = "lastname";
		   	 String gender = "gender";
		   	 int yearOfBirth = 2020;
		   	 String country = "country";
		   	 String postCode = "postCode";
		   	 String email = "email";
		   	 String username = "username";
		   	 String password = "password";
			
			Account account = null;
			if(accountType.equals("standard")) {
		   		 account = new Account(firstName, lastName, postCode, country, gender, yearOfBirth, email, username, password, false, false, false, true); 
		   	 }else if(accountType.equals("PCO")) {
		   		account = new Account(firstName, lastName, postCode, country, gender, yearOfBirth, email, username, password, false, true, false, false);
		   		setOrganisation(account, "phone", "name");
		   	 }else if(accountType.equals("FC")) {
		   		account = new Account(firstName, lastName, postCode, country, gender, yearOfBirth, email, username, password, false, false, true, false);
		   		setOrganisation(account, "phone", "name");
		   	 }
			return account;
		}
		
		public static String saltPassword(String password) {
			return BCrypt.hashpw(password, SALT);
		}
		
		private static void setOrganisation(Account account, String phone, String name) {
			   account.setOrganisationName(name);
			   account.setOrganisationPhone(phone);
		   }
	}

}
