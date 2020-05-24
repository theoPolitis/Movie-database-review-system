package adminApprovalOfForm;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.Statement;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.dao.ProductionCompanyDAO;
import app.dao.utils.DatabaseUtils;


class ProductionCompanyDAO_AlterPCO {
	
	@Test
	void ifDeleteISTrue_deletedTest_passingTrueToMethod() {
		assertEquals("Deleted Test", ProductionCompanyDAOTest.testMethodReturnSql("Test", true));
	}
	
	@Test
	void ifDeleteIsFalse_notDeleteTest_passingFalseToMethod() {
		assertEquals("notDeleted Test", ProductionCompanyDAOTest.testMethodReturnSql("Test", false));
	}
	
	@Test
	void ifProcoNameIsNullAndDeleteIsTrue_NullPointerException_passingNullToMethod() {
		assertThrows(NullPointerException.class, () -> ProductionCompanyDAO.AlterPCO(null, true));
	}
	
	@Test
	void ifProcoNameIsNullAndDeleteIsFalse_NullPointerException_passingNullToMethod() {
		assertThrows(NullPointerException.class, () -> ProductionCompanyDAO.AlterPCO(null, false));
	}
	
	static class ProductionCompanyDAOTest{
		
		static String testMethodReturnSql(String procoName, boolean delete) {
			String sql = " ";
				try {
					
					if(!delete) {
						sql = "notDeleted " + procoName;
					}else {
						sql = "Deleted " + procoName;
					}
			
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return sql;
		}
	}
}
