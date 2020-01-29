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