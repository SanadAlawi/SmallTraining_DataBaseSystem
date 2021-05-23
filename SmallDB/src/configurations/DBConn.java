package configurations;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.SQLException;
	import java.util.Properties;
	
	public class DBConn {
			
		public Connection con;
		public String dbURL;
		public String dbUsername;
		public String dbPassword;
		public String URL;
		public String port;
		public String dbName;
	
		public DBConn(String URL, String port, String dbName, String dbUsername, String dbPassword) {
	
			this.URL = URL;
			this.port = port;
			this.dbName = dbName;
			this.dbUsername = dbUsername;
			this.dbPassword = dbPassword;
		}
	
		public Connection connectDB() throws ClassNotFoundException, SQLException {
	
			dbURL = "jdbc:mysql://" + URL + ":" + port + "/" + dbName + "?verifyServerCertificate=false";
			Properties p = new Properties();
			p.setProperty("user", dbUsername);
			p.setProperty("password", dbPassword);
			p.setProperty("useSSL", "false");
			p.setProperty("autoReconnect", "true");
	
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(dbURL, p);// driver 
			// new com.mysql.jdbc.Driver();
			// con = DriverManager.getConnection(dbURL,p);
	
			return con;
		}
		
		
	
	}
