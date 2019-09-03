package autonomous.jdbc.ucp;

import oracle.ucp.jdbc.PoolDataSourceFactory;
import oracle.ucp.jdbc.PoolDataSource;

import java.sql.Connection;
import java.util.ResourceBundle;

public class UCPFactory {

	private static UCPFactory ucpFactory = new UCPFactory();
  
	private PoolDataSource pds = PoolDataSourceFactory.getPoolDataSource();

	public static UCPFactory getInstance() throws UCPException{
		if(ucpFactory!=null)return ucpFactory;
		else throw new UCPException("UCP cannot get instance.");
	}

	public UCPFactory(){
		if(UCPFactory.ucpFactory == null) init();
	}

	private void init(){
		try {
			ResourceBundle rb_ucp=ResourceBundle.getBundle("autonomous/jdbc/ucp/ucp");
			// Connection properties
			pds.setConnectionFactoryClassName(rb_ucp.getString("CONN_FACTORY_CLASS_NAME"));
			pds.setURL(rb_ucp.getString("DB_URL"));
			pds.setUser(rb_ucp.getString("DB_USER"));
			pds.setPassword(rb_ucp.getString("DB_PASSWORD"));
			pds.setConnectionPoolName(rb_ucp.getString("CONN_POOL_NAME"));
			// Configuration properties
			if(rb_ucp.containsKey("INIT_POOL_SIZE"))
				pds.setInitialPoolSize(Integer.parseInt(rb_ucp.getString("INIT_POOL_SIZE")));
			if(rb_ucp.containsKey("MIN_POOL_SIZE"))
				pds.setMinPoolSize(Integer.parseInt(rb_ucp.getString("MIN_POOL_SIZE")));
			if(rb_ucp.containsKey("MAX_POOL_SIZE"))
				pds.setMaxPoolSize(Integer.parseInt(rb_ucp.getString("MAX_POOL_SIZE")));
			if(rb_ucp.containsKey("TIMEOUT_CHECK_INTERVAL"))
				pds.setTimeoutCheckInterval(Integer.parseInt(rb_ucp.getString("TIMEOUT_CHECK_INTERVAL")));
			if(rb_ucp.containsKey("INACTIVE_CONN_TIMEOUT"))
				pds.setInactiveConnectionTimeout(Integer.parseInt(rb_ucp.getString("INACTIVE_CONN_TIMEOUT")));
		}catch(Exception e) {
			System.out.println("UCP resource bundle error.");
		}	  
		try (Connection conn = pds.getConnection()) {
			System.out.println("Available connections: " + pds.getAvailableConnectionsCount());
		}catch (Exception e) {
			System.out.println("UCP connection pool initialization error.");
		}
  	}
  
	public Connection getConnection() throws UCPException{
		try {
			return pds.getConnection();
		}catch (Exception e){
			throw new UCPException("UCP connection pool error. Cannot get connection.");
		}
	}
}

  /*
//  final static String DB_URL= "jdbc:oracle:thin:@adb.eu-frankfurt-1.oraclecloud.com:1522/n7pmwsc8te8fjty_dbmamende_tpurgent.atp.oraclecloud.com?TNS_ADMIN=C:/WORK/PROJECT/ATP/wallet_DBMAMENDE";
//  final static String DB_URL= "jdbc:oracle:thin:@dbmamende_tpurgent?TNS_ADMIN=C:/WORK/PROJECT/ATP/wallet_DBMAMENDE";
//  final static String DB_URL= "jdbc:oracle:thin:@dbmamende_low";
//  final static String DB_URL= "jdbc:oracle:thin:@adb.eu-frankfurt-1.oraclecloud.com:1522/n7pmwsc8te8fjty_dbmamende_low.atp.oraclecloud.com";
//  final static String DB_URL= "jdbc:oracle:thin:@dbmamende_low?TNS_ADMIN=C:/WORK/PROJECT/ATP/wallet_DBMAMENDE/";
// final static String DB_URL= "jdbc:oracle:thin:@dbmamende_low?TNS_ADMIN=C:\\WORK\\PROJECT\\ATP\\wallet_DBMAMENDE\\";
  final static String DB_URL= "jdbc:oracle:thin:/@dbmamende_low";

  final static String DB_USER = "Testdaten";
  final static String DB_PASSWORD = "W#lcome123456!";

    public static void main(String[] args){
        System.out.println("Here we go...");

        Properties info = new Properties();
        info.put(OracleConnection.CONNECTION_PROPERTY_USER_NAME, DB_USER);
        info.put(OracleConnection.CONNECTION_PROPERTY_PASSWORD, DB_PASSWORD);
        info.put(OracleConnection.CONNECTION_PROPERTY_DEFAULT_ROW_PREFETCH, "20");

        OracleDataSource ods = null;
        try{
          ods = new OracleDataSource();
          ods.setURL(DB_URL);
          ods.setConnectionProperties(info);
        }catch(Exception ex){
          ex.printStackTrace();
        }

        try (OracleConnection connection = (OracleConnection) ods.getConnection()) {
          DatabaseMetaData dbmd = connection.getMetaData();

          System.out.println("Driver Name: " + dbmd.getDriverName());
          System.out.println("Driver Version: " + dbmd.getDriverVersion());
          System.out.println("Default Row Prefetch Value is: " + connection.getDefaultRowPrefetch());
          System.out.println("Database Username is: " + connection.getUserName());
          System.out.println();

          try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select count(PLZ) from Postleitzahlen");
            System.out.println("PLZ");
            System.out.println("---");
            while (resultSet.next())
              System.out.println(resultSet.getString(1));
            }
          }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
*/
