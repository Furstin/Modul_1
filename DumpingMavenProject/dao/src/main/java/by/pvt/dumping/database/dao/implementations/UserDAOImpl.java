package by.pvt.dumping.database.dao.implementations;

import by.pvt.dumping.database.ConnectionPool;
import by.pvt.dumping.database.dao.DAOUtils;
import by.pvt.dumping.database.dao.interfaces.UserDAO;
import by.pvt.dumping.entity.Role;
import by.pvt.dumping.entity.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserDAOImpl implements UserDAO {

	private static final Logger LOGGER = Logger.getLogger(UserDAO.class);
	private static final String SQL_QUERY_GET_PASS = "SELECT password FROM users WHERE email = ?";
	private static final String SQL_QUERY_GET_BAN = "SELECT is_baned FROM accounts WHERE accounts.users_email=?";
	private static final String SQL_QUERY_GET_ROLE = "SELECT role_type FROM role WHERE id=(SELECT role_id FROM users WHERE email=?)";
	private static final String SQL_QUERY_GET_USER = "SELECT accounts.id, personal_info.first_name, personal_info.last_name, personal_info.phone_number, personal_info.address FROM accounts INNER JOIN personal_info on accounts.id=personal_info.accounts_id WHERE accounts.users_email=?";
	private static final String SQL_QUERY_CREATE_ACCOUNT = "INSERT INTO accounts(users_email) VALUES (?)";
	private static final String SQL_QUERY_ADD_PERSONAL_INFO = "INSERT INTO personal_info VALUES ((SELECT accounts.id FROM accounts WHERE accounts.users_email=?), ?, ?, ?, ?)";
	private static final String SQL_QUERY_CREATE_USER = "INSERT INTO users VALUES (?, ?, (SELECT role.id FROM role WHERE role.role_type=?))";
	private static final String SQL_QUERY_GET_EMAIL = "SELECT email FROM users WHERE password = ?";
	private static final String SQL_QUERY_REMOVE_USER_INFO = "DELETE FROM personal_info WHERE personal_info.accounts_id=(SELECT id FROM accounts WHERE user_email=?)";
	private static final String SQL_QUERY_REMOVE_USER_ACCOUNT = "DELETE FROM accounts WHERE accounts.users_email=?";
	private static final String SQL_QUERY_REMOVE_USER = "DELETE FROM users WHERE users.email=?";
	private static final String SQL_QUERY_GET_USERS_BY_ROLE = "SELECT email, password FROM users WHERE role_id=(SELECT id FROM role WHERE role_type=?)";
	private static final String SQL_QUERY_BAN_USER = "UPDATE accounts SET is_banned = 'Y' WHERE users_email = ?";
	private static final String SQL_QUERY_GET_DEBTORS = "SELECT users_email FROM accounts JOIN orders ON accounts.id=orders.account_id WHERE orders.payment='N'";

	private UserDAO userDAO;

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public boolean addUser(User user) {
		
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			pool = ConnectionPool.getInstance();
			connection = pool.getConnection();
			statement = connection.prepareStatement(SQL_QUERY_CREATE_ACCOUNT);
			statement.setString(1, user.getEmail());
			statement.executeUpdate();
			DAOUtils.closeStatement(statement);

			statement = connection
					.prepareStatement(SQL_QUERY_ADD_PERSONAL_INFO);
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getFirstName());
			statement.setString(3, user.getLastName());
			statement.setString(4, user.getPhoneNumber());
			statement.setString(5, user.getAddress());
			statement.executeUpdate();
			DAOUtils.closeStatement(statement);

			statement = connection.prepareStatement(SQL_QUERY_CREATE_USER);
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getRole().toString().toLowerCase());
			statement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("dao exception", e);
			return false;
		} finally {
			DAOUtils.closeStatement(statement);
			pool.close(connection);
		}
		return true;
	
		
	}

	@Override
	public boolean checkEmail(String email) {
		
		ConnectionPool pool = null;
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement statement = null;
		String dbemail = null;

		try {
			pool = ConnectionPool.getInstance();
			connection = pool.getConnection();
			statement = connection.prepareStatement(SQL_QUERY_GET_EMAIL);
			statement.setString(1, email);
			resultSet = statement.executeQuery();
			dbemail = initEmail(resultSet);
			if (!email.equals(dbemail)) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			LOGGER.error("dao exception", e);
			return false;
		} finally {
			DAOUtils.closeStatement(statement);
			pool.close(connection);
		}
		
	}

	private String initEmail(ResultSet resultSet) throws SQLException {
		String email = null;
		while (resultSet.next()) {
			email = resultSet.getString(1);
		}
		return email;
	}
	
	@Override
	public boolean checkPassword(String email, String password) {
		
		ConnectionPool pool = null;
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement getPassStatement = null;
		String realPassword = null;

		try {
			pool = ConnectionPool.getInstance();
			connection = pool.getConnection();
			getPassStatement = connection.prepareStatement(SQL_QUERY_GET_PASS);
			getPassStatement.setString(1, email);
			resultSet = getPassStatement.executeQuery();
			realPassword = initPass(resultSet);
			if (password.equals(realPassword)) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			LOGGER.error("dao exception", e);
			return false;
		} finally {
			DAOUtils.closeStatement(getPassStatement);
			pool.close(connection);
		}
		
	}
	
	private String initPass(ResultSet resultSet) throws SQLException {
		String pass = null;
		while (resultSet.next()) {
			pass = resultSet.getString(1);
		}
		return pass;
	}
	
	
	@Override
	public boolean checkIsBanned(String email) {
		ConnectionPool pool = null;
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement getBanStatement = null;
		boolean isBan = false;

		try {
			pool = ConnectionPool.getInstance();
			connection = pool.getConnection();
			getBanStatement = connection.prepareStatement(SQL_QUERY_GET_BAN);
			getBanStatement.setString(1, email);
			resultSet = getBanStatement.executeQuery();
			isBan = isBan(resultSet);
			if (!isBan) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException e) {
			LOGGER.error("dao exception", e);
			return false;
		} finally {
			DAOUtils.closeStatement(getBanStatement);
			pool.close(connection);
		}
	}
	
	private boolean isBan(ResultSet resultSet) throws SQLException {
		String ban = null;
		while (resultSet.next()) {
			ban = resultSet.getString(1);
		}
		if ("N".equals(ban)) {
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public User getUser(String email) {
		ConnectionPool pool = null;
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement statement = null;
		User user = new User();

		try {
			pool = ConnectionPool.getInstance();
			connection = pool.getConnection();

			statement = connection.prepareStatement(SQL_QUERY_GET_ROLE);
			statement.setString(1, email);
			resultSet = statement.executeQuery();
			Role role = specifyUserRole(resultSet);
			user.setRole(role);
			DAOUtils.closeStatement(statement);

			statement = connection.prepareStatement(SQL_QUERY_GET_USER);
			statement.setString(1, email);
			resultSet = statement.executeQuery();
			setUserFields(user, resultSet);
		} catch (SQLException e) {
			LOGGER.error("dao exception", e);
		} finally {
			DAOUtils.closeStatement(statement);
			pool.close(connection);
		}
		return user;
	}
	
	private void setUserFields(User user, ResultSet resultSet)
			throws SQLException {
		while (resultSet.next()) {
			user.setId(resultSet.getInt(1));
			user.setFirstName(resultSet.getString(2));
			user.setLastName(resultSet.getString(3));
			user.setPhoneNumber(resultSet.getString(4));
			user.setAddress(resultSet.getString(5));
		}
	}
	
	private Role specifyUserRole(ResultSet resultSet) throws SQLException {
		Role role = null;
		while (resultSet.next()) {
			role = Role.valueOf(resultSet.getString(1).toUpperCase());
		}
		return role;
	}
	
	@Override
	public boolean isEmpty(String parameter) {
		return "".equals(parameter);
	}
	
	@Override
	public boolean banCustomer(String email) {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			pool = ConnectionPool.getInstance();
			connection = pool.getConnection();
			statement = connection.prepareStatement(SQL_QUERY_BAN_USER);
			statement.setString(1, email);
			statement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("dao exception", e);
			return false;
		} finally {
			DAOUtils.closeStatement(statement);
			pool.close(connection);
		}
		return true;
	}
	
	
	@Override
	public List<String> getDebtors() {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<String> list = null;

		try {
			pool = ConnectionPool.getInstance();
			connection = pool.getConnection();
			statement = connection.prepareStatement(SQL_QUERY_GET_DEBTORS);
			resultSet = statement.executeQuery();
			list = initEmails(resultSet);
		} catch (SQLException e) {
			LOGGER.error("dao exception", e);
		} finally {
			DAOUtils.closeStatement(statement);
			pool.close(connection);
		}
		return list;
	}
	
	private List<String> initEmails(ResultSet resultSet) throws SQLException {
		List<String> list = new ArrayList<>();
		while (resultSet.next()) {
			list.add(resultSet.getString(1));
		}
		return list;
	}
	
	@Override
	public List<User> getUsersByRole(Role role) {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<User> list = null;

		try {
			pool = ConnectionPool.getInstance();
			connection = pool.getConnection();
			statement = connection
					.prepareStatement(SQL_QUERY_GET_USERS_BY_ROLE);
			statement.setString(1, role.toString().toLowerCase());
			resultSet = statement.executeQuery();
			list = initList(resultSet);
		} catch (SQLException e) {
			LOGGER.error("dao exception", e);
		} finally {
			DAOUtils.closeStatement(statement);
			pool.close(connection);
		}
		return list;
	}
	
	private List<User> initList(ResultSet resultSet) throws SQLException {
		List<User> arList = new ArrayList<>();
		User user = null;

		while (resultSet.next()) {
			user = new User();
			user.setEmail(resultSet.getString(1));
			user.setPassword(resultSet.getString(2));
			arList.add(user);
		}
		return arList;
	}
	
	
	
	@Override
	public boolean removeUser(String email) {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			pool = ConnectionPool.getInstance();
			connection = pool.getConnection();
			statement = connection.prepareStatement(SQL_QUERY_REMOVE_USER_INFO);
			statement.setString(1, email);
			statement.executeUpdate();
			DAOUtils.closeStatement(statement);

			statement = connection
					.prepareStatement(SQL_QUERY_REMOVE_USER_ACCOUNT);
			statement.setString(1, email);
			statement.executeUpdate();
			DAOUtils.closeStatement(statement);

			statement = connection.prepareStatement(SQL_QUERY_REMOVE_USER);
			statement.setString(1, email);
			statement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("dao exception", e);
			return false;
		} finally {
			DAOUtils.closeStatement(statement);
			pool.close(connection);
		}
		return true;
	}



	

	

	

	
	
	
}
