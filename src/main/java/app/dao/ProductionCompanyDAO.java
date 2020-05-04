package app.dao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import app.dao.utils.DatabaseUtils;
import app.model.ProductionCompany;

public class ProductionCompanyDAO {
	private static List<ProductionCompany> allPCO = getAllProCo();
	
	//returns production company with same procoID
	public static ProductionCompany getProductionCompanyById(int procoId) {	
		for(ProductionCompany PCO : allPCO) {
			if(PCO.getProcoId() == procoId) {
				return PCO;
			}
		}
		
		return null;
	}
	
	public static List<ProductionCompany> getAllPCO() {
		return allPCO;
	}

	//gets all production companys from the database;
	private static List<ProductionCompany> getAllProCo() {

		List<ProductionCompany> proCoList = new ArrayList<>();

		String sql = "SELECT * FROM imbd.production_company";

		try {
			//open a connection to the database
			Connection connection = DatabaseUtils.connectToDatabase();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);

			while (result.next()) {
				proCoList.add(new ProductionCompany(result.getInt("proco_id"), result.getString("proco_name")));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return proCoList;

	}
}
