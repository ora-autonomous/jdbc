package autonomous.jdbc;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import oracle.jdbc.pool.OracleDataSource;
import oracle.jdbc.OracleConnection;
import java.sql.DatabaseMetaData;

public class SimpleJDBCTest {

  final static String DB_URL= "jdbc:oracle:thin:/@dbmamende_low";
  final static String DB_USER = "Testdaten";
  final static String DB_PASSWORD = "<Your_PASSW0RD_Here>";

    public static void main(String[] args){
        System.out.println("Starting Autonomous DB Connection Test...\n");

        Properties info = new Properties();
        info.put(OracleConnection.CONNECTION_PROPERTY_USER_NAME, DB_USER);
        info.put(OracleConnection.CONNECTION_PROPERTY_PASSWORD, DB_PASSWORD);
        info.put(OracleConnection.CONNECTION_PROPERTY_DEFAULT_ROW_PREFETCH, "20");

        OracleDataSource ods = null;

        try{
        	ods = new OracleDataSource();
        	ods.setURL(DB_URL);
        	ods.setConnectionProperties(info);

        	OracleConnection connection = (OracleConnection) ods.getConnection();
        	DatabaseMetaData dbmd = connection.getMetaData();
        	System.out.println("Driver Name: " + dbmd.getDriverName());
        	System.out.println("Driver Version: " + dbmd.getDriverVersion());
        	System.out.println("Default Row Prefetch Value is: " + connection.getDefaultRowPrefetch());
        	System.out.println("Database Username is: " + connection.getUserName() +"\n");

        	Statement statement = connection.createStatement();
        	ResultSet resultSet = statement.executeQuery("select count(PLZ) from Postleitzahlen");
        	System.out.println("PLZ");
        	System.out.println("---");
        	while (resultSet.next())
        		System.out.println(resultSet.getString(1));
        }catch(Exception ex){
        	ex.printStackTrace();
        }
    }
}
