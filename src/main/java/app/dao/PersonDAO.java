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
	//get the show by title
    public static Person getActorByName(String name) {
        // Fish out the results
        List<Person> persons = new ArrayList<>();

        try {
            // Here you prepare your sql statement
            String sql = "SELECT * "+ "FROM imbd.person WHERE fullname LIKE '%" + name + "%'";

            // Execute the query
            Connection connection = DatabaseUtils.connectToDatabase();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

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