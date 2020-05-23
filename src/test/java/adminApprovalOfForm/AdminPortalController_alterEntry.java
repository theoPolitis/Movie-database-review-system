package adminApprovalOfForm;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.controller.paths.Template;
import app.controller.utils.ViewUtil;
import app.dao.AccountDAO;
import app.dao.ProductionCompanyDAO;
import app.dao.ShowDAO;

class AdminPortalController_alterEntry {
	@Test
	public void passedOverNullValues_NullPointerException_alterShowGivenANullValue() {
		assertThrows(NullPointerException.class, () -> AccountControllerTest.alterShow(null));
	}
	
	@Test
	public void adminRemovesAShow_showremove_passingFormData() {
		String formData = "1, Remove";
		assertEquals("showRemove", AccountControllerTest.alterShow(formData));
	}
	
	@Test
	public void adminDeletesAccount_adminDeletedAccount_passingFormData() {
		String formData = "username, Delete";
		assertEquals("adminDeletedAccount", AccountControllerTest.alterShow(formData));
	}
	
	@Test
	public void adminApprovesAccount_adminApproved_passingFormData() {
		String formData = "username, Approve";
		assertEquals("adminApproved", AccountControllerTest.alterShow(formData));
	}
	
	@Test
	public void productionCompanyAdded_productionCompanyAdded_passingFormData() {
		String formData = "pro, comp, added";
		assertEquals("productionCompanyAdded", AccountControllerTest.alterShow(formData));
	}
	
	@Test
	public void productionCompanyRemoved_productionCompanyDeleted_passingFormData() {
		String formData = "pro, comp, check, remove";
		assertEquals("productionCompanyDeleted", AccountControllerTest.alterShow(formData));
	}
	
	
	static class AccountControllerTest{
		
		public static String alterShow(String formData) {
			
	        String alterCommands[] = formData.split(", ");
	        String sqlCommand;
	        
	        //dependant on what button is pressed then we update database accordingly
	        if (alterCommands[1].equals("Remove")) {
	            return "showRemove";
	        } else if(alterCommands[1].equals("Delete")){
	        	return "adminDeletedAccount";
	        }else if(alterCommands[1].equals("Approve")){
	        		return "adminApproved";
	        }else {
	            sqlCommand = "UPDATE imbd.show SET status='" + alterCommands[1] + "' WHERE showid=" + alterCommands[0];
	          
	        }
	        
	        
	        if(alterCommands.length == 3) {
				return "productionCompanyAdded";
			}
	        
	        if(alterCommands.length == 4) {
	        	return "productionCompanyDeleted";
	        }
	        
	        return null;
		
		
		}
		
		
	}

}
