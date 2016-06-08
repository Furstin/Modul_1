package by.pvt.dumping.database;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;


public class DBConnector {

	private static final ResourceBundle CONFIG_BUNDLE = ResourceBundle
			.getBundle("Configuration");
	private static final Logger LOGGER = Logger.getLogger(DBConnector.class);
	private static final String URL = CONFIG_BUNDLE.getString("database-url");
	private static Properties PROPERTIES = new Properties();
	static {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			LOGGER.fatal("exception in initializing driver", e);
			throw new ExceptionInInitializerError();
		}
		PROPERTIES
				.setProperty("user", CONFIG_BUNDLE.getString("database-user"));
		PROPERTIES.setProperty("password",
				CONFIG_BUNDLE.getString("database-password"));
		PROPERTIES.setProperty("useUnicode",
				CONFIG_BUNDLE.getString("database-unicode"));
		PROPERTIES.setProperty("characterEncoding",
				CONFIG_BUNDLE.getString("database-encoding"));
	}

	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 * @throws SQLException the SQL exception
	 */
	public Connection getConnection() throws SQLException {
		Connection connection = DriverManager.getConnection(URL, PROPERTIES);
		return connection;
	}
}
