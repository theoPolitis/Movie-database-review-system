package app.dao;

import app.dao.utils.DatabaseUtils;
import app.model.Person;
import app.model.Show;

import org.eclipse.collections.impl.list.mutable.FastList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



public class PersonDAO {
	
	//selects what information it will search the datyabasde with
	public static Person getActorSelector(String name, int id) {
		//had to sanitise the string as if a name contained ' it would fail but replace ' with ''
		String nameSanitize = name;
		if(name != null) {
			nameSanitize = name.replaceAll("'", "''");
		}
		
		String sqlId = "SELECT * "+ "FROM imbd.person WHERE person_id = " + id;
		String sqlName = "SELECT * "+ "FROM imbd.person WHERE fullname LIKE '%" + nameSanitize + "%'";
		
		if(id > 0) {
			return getActor(sqlId);
		}else if(name != null) {
			return getActor(sqlName);
		}
		
		return null;
	}
	
	//get the person
	private static Person getActor(String sqlFormat) {
        // Fish out the results
        List<Person> persons = new ArrayList<>();

        try {
            Connection connection = DatabaseUtils.connectToDatabase();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sqlFormat);

            // If you have multiple results, you do a while
            while(result.next()) {
                // 2) Add it to the list we have prepared
                persons.add(new Person(result.getInt("person_id"), result.getString("fullname"), result.getString("role"),
                		result.getDate("birthdate"), result.getString("bio")));
            }

            // Close it
            DatabaseUtils.closeConnection(connection);
        }
        catch (Exception e) {
            e.printStackTrace();
        }


        // If there is a result
        if(!persons.isEmpty()) return persons.get(0);
        // If we are here, something bad happened
        return null;
    }
}