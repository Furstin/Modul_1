package by.pvt.dumping.database.dao.interfaces;

import by.pvt.dumping.entity.Role;
import by.pvt.dumping.entity.User;

import java.util.List;

public interface UserDAO {
	
	boolean checkPassword(String email, String password);

	boolean checkIsBanned(String email);

	User getUser(String email);

	boolean addUser(User user);

	boolean checkEmail(String email);

	boolean removeUser(String email);

	List<User> getUsersByRole(Role role);

	boolean banCustomer(String email);

	boolean isEmpty(String parameter);

	List<String> getDebtors();
	
	
}
