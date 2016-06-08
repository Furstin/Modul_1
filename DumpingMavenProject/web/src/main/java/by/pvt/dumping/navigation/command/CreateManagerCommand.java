package by.pvt.dumping.navigation.command;

import by.pvt.dumping.entity.Role;
import by.pvt.dumping.entity.User;
import by.pvt.dumping.managers.MessageManager;
import by.pvt.dumping.managers.PathsManager;
import by.pvt.dumping.service.UserService;

import javax.servlet.http.HttpServletRequest;


public class CreateManagerCommand implements Command{

	private static final String ATTR_MEMAILEXISTS = "memailexists";
	private static final String ATTR_MCREATED = "mcreated";
	private static final String PAGE_ADMIN = "admin.home";
	private static final String ATTR_MNOTCREATED = "mnotcreated";
	public static final String PARAM_EMAIL = "email";
	public static final String PARAM_PASSWORD = "password";
	public static final String PARAM_FIRST_NAME = "firstName";
	public static final String PARAM_LAST_NAME = "lastName";
	public static final String PARAM_PHONE_NUMBER = "phoneNumber";
	public static final String PARAM_ADDRESS = "address";
	private static final String ATTR_LANGUAGE = "language";

	@Override
	public String execute(HttpServletRequest request) {
		String email = request.getParameter(PARAM_EMAIL);
		String firstName = request.getParameter(PARAM_FIRST_NAME);
		String lastName = request.getParameter(PARAM_LAST_NAME);
		String phoneNumber = request.getParameter(PARAM_PHONE_NUMBER);
		String address = request.getParameter(PARAM_ADDRESS);
		String password = request.getParameter(PARAM_PASSWORD);
		String lang = (String) request.getSession().getAttribute(ATTR_LANGUAGE);
		Role role = Role.MANAGER;

		UserService userService = new UserService();
		if (userService.isEmpty(email) || userService.isEmpty(firstName)
				|| userService.isEmpty(lastName)
				|| userService.isEmpty(phoneNumber)
				|| userService.isEmpty(address)
				|| userService.isEmpty(password)) {
			request.setAttribute(ATTR_MNOTCREATED, MessageManager.getProperty(
					"message.manager_not_created", lang));
			return PathsManager.getProperty(PAGE_ADMIN);
		} else {
			if (userService.checkEmail(email)) {
				User user = new User();
				user.setFirstName(firstName);
				user.setLastName(lastName);
				user.setEmail(email);
				user.setAddress(address);
				user.setPhoneNumber(phoneNumber);
				user.setPassword(password);
				user.setRole(role);
				userService.addUser(user);
				request.setAttribute(ATTR_MCREATED, MessageManager.getProperty(
						"message.manager_created", lang));
				return PathsManager.getProperty(PAGE_ADMIN);
			} else {
				request.setAttribute(ATTR_MEMAILEXISTS, MessageManager
						.getProperty("message.email_exists", lang));
				return PathsManager.getProperty(PAGE_ADMIN);
			}
		}

	}
	
}
