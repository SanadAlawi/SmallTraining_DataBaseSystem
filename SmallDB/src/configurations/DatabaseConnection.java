package configurations;

import static configurations.DBConfig.DB_NAME;
import static configurations.DBConfig.DB_PASSWORD;
import static configurations.DBConfig.DB_USERNAME;
import static configurations.DBConfig.PORT;
import static configurations.DBConfig.URL;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnection {
	private static DatabaseConnection databaseConnection;
	static Connection connection;
	DBConn conn;

	private DatabaseConnection() {
		try {
			conn = new DBConn(URL, PORT, DB_NAME, DB_USERNAME, DB_PASSWORD);
			connection = conn.connectDB();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static Connection getConeection() {
		if (databaseConnection == null) {
			databaseConnection = new DatabaseConnection();
		}
		return databaseConnection.connection;
	}
}
