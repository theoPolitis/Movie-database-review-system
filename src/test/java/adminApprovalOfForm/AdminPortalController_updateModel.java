package adminApprovalOfForm;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.model.Account;

class AdminPortalController_updateModel {
	static List<Account> unApprovedAccounts = null;
	static List<Account> approvedAccounts = null;
	static boolean filmCriticApprovedExists = false;
	static boolean procoApprovedExists = false;
	static boolean procoUnApprovedExists = false;
	static boolean filmCriticUnApprovedExists = false;
	
	@BeforeEach
	void setUp() {
		unApprovedAccounts = new ArrayList<Account>();
		approvedAccounts = new ArrayList<Account>();
		
		int i = 0;
		
		
		while(i < 4) {
			unApprovedAccounts.add(new Account("firstName", "lastName", "postCode", "country", "gender", i, "email", "userName", "password", false, true, false, false));
			
			approvedAccounts.add(new Account("firstName", "lastName", "postCode", "country", "gender", i, "email", "userName", "password", false, true, false, true));
			
			i++;
		}
		
		int x = 10;
		while(x < 14) {
			unApprovedAccounts.add(new Account("firstName", "lastName", "postCode", "country", "gender", i, "email", "userName", "password", false, false, true, false));
			
			approvedAccounts.add(new Account("firstName", "lastName", "postCode", "country", "gender", i, "email", "userName", "password", false, false, true, true));
			
			x++;
		}
		
		AdminPortalControllerTest.updateModelReturn();
	}


	@Test
	void aNullModel_NullPointerException_passingANullModelToUpdateModel() {
		assertThrows(NullPointerException.class, () -> AdminPortalControllerTest.updateModel(null));
	}
	
	@Test
	void aNullApprovedAccountsList_NullPointerException_makingApprovedAccountsObjectNull() {
		approvedAccounts = null;
		assertThrows(NullPointerException.class, () -> AdminPortalControllerTest.updateModel(new HashMap<>()));
	}
	
	@Test
	void aNullUnapprovedAccountsList_NullPointerException_makingUnApprovedAccountsNull(){
		unApprovedAccounts = null;
		assertThrows(NullPointerException.class, () -> AdminPortalControllerTest.updateModel(new HashMap<>()));
	}
	
	@Test
	void filmCiricUnApprovedIsSplit_true_TestingObjectsInModel() {
		assertTrue(filmCriticUnApprovedExists);
	}
	
	@Test
	void filmCriticApprovedIsSplit_true_TestingObjectInModel() {
		assertTrue(filmCriticApprovedExists);
	}
	
	@Test
	void procoUnapprovedIsSplit_true_TestingObjectModel() {
		assertTrue(procoUnApprovedExists);
	}
	
	@Test 
	void procoApprovedIsSplit_true_TestingObjectModel(){
		assertTrue(procoApprovedExists);
	}
	
	static class AdminPortalControllerTest{
		static Map<String, Object> updateModel(Map<String, Object> model){
			
	        
	        //created differnet model keys for the PCO and FC
	        //made it easy to differentiate in the html page
	        List<Account> PCO = new ArrayList<Account>();
	        List<Account> FC = new ArrayList<Account>();
	        
	        //displays unapproved accounts
	        for(Account a : unApprovedAccounts) {
	        	if(a.isProco()) {
	        		PCO.add(a);
	        	}
	        	
	        	if(a.isFilmCritic()) {
	        		FC.add(a);
	        	}
	        }
	        
	        List<Account> PCOApproved = new ArrayList<Account>();
	        List<Account> FCApproved = new ArrayList<Account>();
	        
	        //displays the approved accounts for deleting
	        for(Account a : approvedAccounts) {
	        	if(a.isFilmCritic()) {
	        		FCApproved.add(a);
	        	}
	        	
	        	if(a.isProco()) {
	        		PCOApproved.add(a);
	        	}
	        }
	        
	        //add lists to model with approriate key
	        model.put("PCOApproved", PCOApproved);
	        model.put("FCApproved", FCApproved);
	        model.put("PCO", PCO);
	        model.put("FC", FC);
			return model;
		}
		
		static void updateModelReturn(){
			
	        
	        //displays unapproved accounts
	        for(Account a : unApprovedAccounts) {
	        	if(a.isProco()) {
	        		procoUnApprovedExists = true;
	        	}
	        	
	        	if(a.isFilmCritic()) {
	        		filmCriticUnApprovedExists = true;
	        	}
	        }
	        
	        
	        //displays the approved accounts for deleting
	        for(Account a : approvedAccounts) {
	        	if(a.isFilmCritic()) {
	        		filmCriticApprovedExists = true;
	        	}
	        	
	        	if(a.isProco()) {
	        		procoApprovedExists = true;
	        	}
	        }
	        
	        //add lists to model with approriate key
	 
	        
		} 
	}

}
