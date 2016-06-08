package by.pvt.dumping.navigation.command;

import by.pvt.dumping.entity.Role;
import by.pvt.dumping.entity.User;
import by.pvt.dumping.managers.MessageManager;
import by.pvt.dumping.managers.PathsManager;
import by.pvt.dumping.service.UserService;

import javax.servlet.http.HttpServletRequest;


public class CustomerRegisterCommand implements Command {

	private static final String ATTR_EMAILEXISTS = "emailexists";
	private static final String PAGE_CUSTOMER_HOME = "customer.home";
	private static final String ATTR_USER = "user";
	private static final String PAGE_REGISTER_COMMON = "common.page-register";
	private static final String ATTR_FILLALL = "fillall";
	private static final String ATTR_LANGUAGE = "language";
	private static final String PARAM_EMAIL = "email";
	private static final String PARAM_PASSWORD = "password";
	private static final String PARAM_FIRST_NAME = "firstName";
	private static final String PARAM_LAST_NAME = "lastName";
	private static final String PARAM_PHONE_NUMBER = "phoneNumber";
	private static final String PARAM_ADDRESS = "address";
	
	
	
	@Override
	public String execute(HttpServletRequest request) {
		String email = request.getParameter(PARAM_EMAIL);
		String firstName = request.getParameter(PARAM_FIRST_NAME);
		String lastName = request.getParameter(PARAM_LAST_NAME);
		String phoneNumber = request.getParameter(PARAM_PHONE_NUMBER);
		String address = request.getParameter(PARAM_ADDRESS);
		String password = request.getParameter(PARAM_PASSWORD);
		String lang = (String) request.getSession().getAttribute(ATTR_LANGUAGE);
		Role role = Role.CUSTOMER;

		UserService userService = new UserService();
		if (userService.isEmpty(email) || userService.isEmpty(firstName)
				|| userService.isEmpty(lastName)
				|| userService.isEmpty(phoneNumber)
				|| userService.isEmpty(address)
				|| userService.isEmpty(password)) {
			request.setAttribute(ATTR_FILLALL,
					MessageManager.getProperty("message.fill_all", lang));
			return PathsManager.getProperty(PAGE_REGISTER_COMMON);
		} else {
			if (userService.checkEmail(email)) {
				User user = new User();
				user.setFirstName(firstName);
				user.setLastName(lastName);
				user.setAddress(address);
				user.setEmail(email);
				user.setPhoneNumber(phoneNumber);
				user.setRole(role);
				user.setPassword(password);
				userService.addUser(user);
				User currUser = userService.getUser(request
						.getParameter(PARAM_EMAIL));
				request.getSession().setAttribute(ATTR_USER, currUser);
				return PathsManager.getProperty(PAGE_CUSTOMER_HOME);
			} else {
				request.setAttribute(ATTR_EMAILEXISTS, MessageManager
						.getProperty("message.email_exists", lang));
				return PathsManager.getProperty(PAGE_REGISTER_COMMON);
			}
		}
	
	}

}
