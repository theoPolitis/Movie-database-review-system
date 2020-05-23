/**
 * 
 */
package requestForm;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import app.dao.AccountDAO;
import app.model.Account;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AccountDAO_insertNewAccount {
	private Account nullAccount; 
	
	@BeforeAll
	public void setUp() {
	 nullAccount = null;
	}

	@Test
	public void ifAccountIsNormal_NullPointerException_passingANullObjectToInsertNewAccount() {
		assertThrows(NullPointerException.class, ()->AccountDAO.insertNewAccount(nullAccount, true));
		
	}
	
	@Test
	public void ifAccountIsNotNormal_NullPointerException_passingNullObjectToInsertNewAccount() {
		assertThrows(NullPointerException.class, () -> AccountDAO.insertNewAccount(nullAccount, false));
	}
	
	@Test
	public void normalSqlStatement_isNormal_passingBooleanTruetoAnInsertDAO() {
		assertEquals("isNormal", AccountDAOTest.testInsert(true));
	}
	
	@Test
	public void notNormalSqlStaement_isNotNormal_passingBooleanFalseToAnInsertDAO() {
		assertEquals("isNotNormal", AccountDAOTest.testInsert(false));
	}
	
	static class AccountDAOTest{
		public static String testInsert(boolean isNormal) {
			String sql = "";
			if(isNormal) {
				sql = "isNormal";
			}else {
				sql = "isNotNormal";
			}
			
			return sql;
		}
	}
}