package app.dao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import app.dao.utils.DatabaseUtils;
import app.model.ProductionCompany;

public class ProductionCompanyDAO {

	public static ProductionCompany getProductionCompanyById(int procoId) {
	        // Fish out the results
	        List<ProductionCompany> productionCompany = new ArrayList<>();
	
	        try {
	            // Here you prepare your sql statement
	            String sql = "SELECT * "+ "FROM imbd.production_company WHERE proco_id = " + procoId;
	
	            // Execute the query
	            Connection connection = DatabaseUtils.connectToDatabase();
	            Statement statement = connection.createStatement();
	            ResultSet result = statement.executeQuery(sql);
	
	            // If you have multiple results, you do a while
	            while(result.next()) {
	                // 2) Add it to the list we have prepared
	                productionCompany.add(new ProductionCompany(result.getInt("proco_id"), result.getString("proco_name")));
	            }
	
	            // Close it
	            DatabaseUtils.closeConnection(connection);
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	        }
	
	
	        // If there is a result
	        if(!productionCompany.isEmpty()) return productionCompany.get(0);
	        // If we are here, something bad happened
	        return null;
	    }

	public static List<ProductionCompany> getAllProCo() {

		List<ProductionCompany> proCoList = new ArrayList<>();

		String sql = "SELECT * FROM imbd.production_company";

		try {

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
