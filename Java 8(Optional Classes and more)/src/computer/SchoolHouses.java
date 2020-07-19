//Create an app for school , which has 4 set of groups like red, green ,blue and yellow.. should allocate the students randomly to each group and finally all group size should be equal(in Java)
package computer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class SchoolHouses {
	public static void main(String[] args) {
		final String getStudents = "select * from students;";
		final String addHouseColumn = "ALTER TABLE students ADD House VARCHAR(10);";
		final String assignHouse = "insert into students ('House') values (?) where Name=?;";
		List<String> houseNames = Arrays.asList("Red", "Green", "Blue", "Yellow");
		HashMap<String, Integer> houses = new HashMap<String, Integer>();
		houses.put("Red", 0);
		houses.put("Blue", 0);
		houses.put("Green", 0);
		houses.put("Yellow", 0);
		
		try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels?useSSL=false", "root" , "");){
			PreparedStatement addCol = con.prepareStatement(addHouseColumn);
			PreparedStatement getData = con.prepareStatement(getStudents);
			PreparedStatement assign = con.prepareStatement(assignHouse);
			int colResult = addCol.executeUpdate();
			ResultSet result = getData.executeQuery();
			int numOfStudents = result.getFetchSize();
			int houseSize = numOfStudents/houses.size();
			Random rand = new Random();
		    String randomHouse;
			while(result.next()) {
			    randomHouse = houseNames.get(rand.nextInt(houseNames.size()));
				if(houses.get(randomHouse) <= houseSize) {
					assign.setString(1, randomHouse);
					assign.setString(2, result.getString("Name"));
					int assignResult = assign.executeUpdate();
					houses.replace(randomHouse, houses.get(randomHouse) + 1);
				}
				else {
					String newRandHouse = "Red";
					while(newRandHouse == houseNames.get(rand.nextInt(houseNames.size()))){
						if((houses.get(newRandHouse) <= houseSize))
							continue;
					}
					assign.setString(1, newRandHouse);
					assign.setString(2, result.getString("Name"));
					int assignResult = assign.executeUpdate();
					houses.replace(randomHouse, houses.get(randomHouse) + 1);
				}
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("done");
	}

}