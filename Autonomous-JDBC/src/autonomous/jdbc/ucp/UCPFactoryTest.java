package autonomous.jdbc.ucp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class UCPFactoryTest {

	public static void main(String[] args) {
		try {		
			UCPFactory upcFactory=UCPFactory.getInstance();
	        try {
	        	Connection connection=upcFactory.getConnection();
	        	Statement statement = connection.createStatement();
	            ResultSet resultSet = statement.executeQuery("select count(PLZ) from Postleitzahlen");
	            System.out.println("PLZ");
	            System.out.println("---");
	            while (resultSet.next())
	            	System.out.println(resultSet.getString(1));
	        }catch(Exception ex){
	            ex.printStackTrace();
	        }
		} catch (UCPException e) {
			e.printStackTrace();
		}
	}
}
