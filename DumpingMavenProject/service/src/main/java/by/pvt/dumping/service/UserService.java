package by.pvt.dumping.service;

import by.pvt.dumping.database.dao.implementations.UserDAOImpl;
import by.pvt.dumping.database.dao.interfaces.UserDAO;
import by.pvt.dumping.entity.Role;
import by.pvt.dumping.entity.User;

import java.util.List;

public class UserService {
	
	private UserDAO userDAO;

	public UserService() {
		userDAO = new UserDAOImpl();
	}

	public boolean checkPassword(String email, String password) {
		return userDAO.checkPassword(email, password);
	}

	public boolean checkIsBanned(String email) {
		return userDAO.checkIsBanned(email);
	}

	public User getUser(String email) {
		return userDAO.getUser(email);
	}

	public boolean addUser(User user) {
		return userDAO.addUser(user);
	}

	public boolean checkEmail(String email) {
		return userDAO.checkEmail(email);
	}

	public boolean removeUser(String email) {
		return userDAO.removeUser(email);
	}

	public List<User> getUsersByRole(Role role) {
		return userDAO.getUsersByRole(role);
	}

	public boolean banCustomer(String email) {
		return userDAO.banCustomer(email);
	}

	public boolean isEmpty(String parameter) {
		return userDAO.isEmpty(parameter);
	}

	public List<String> getDebtors() {
		return userDAO.getDebtors();
	}
}
