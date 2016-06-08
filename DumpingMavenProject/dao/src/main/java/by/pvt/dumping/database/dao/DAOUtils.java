package by.pvt.dumping.database.dao;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.sql.Statement;


public class DAOUtils {

	private static final Logger LOGGER = Logger.getLogger(DAOUtils.class);

	public static void closeStatement(Statement statement) {
		try {
			if (statement != null) {
				statement.close();
			}
		} catch (SQLException e) {
			LOGGER.error("statement was not closed", e);
		}
	}
	
}
