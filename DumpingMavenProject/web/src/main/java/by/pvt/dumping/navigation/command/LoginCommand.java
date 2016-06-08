package by.pvt.dumping.navigation.command;

import by.pvt.dumping.entity.Role;
import by.pvt.dumping.entity.User;
import by.pvt.dumping.managers.MessageManager;
import by.pvt.dumping.managers.PathsManager;
import by.pvt.dumping.service.UserService;

import javax.servlet.http.HttpServletRequest;


public class LoginCommand implements Command {
	private static final String PAGE_GUEST_LOOK = "common.page-guest-look";
	private static final String PAGE_ADMIN_HOME = "admin.home";
	private static final String PAGE_MANAGER_HOME = "manager.home";
	private static final String PAGE_CUSTOMER_HOME = "customer.home";
	private static final String ATTR_CHECKLOGIN = "checklogin";
	private static final String ATTR_BANNED = "banned";
	private static final String ATTR_USER = "user";
	private static final String PAGE_LOGIN = "common.page-login";
	private static final String ATTR_FILLALL = "fillall";
	private static final String LANGUAGE = "language";
	private static final String PARAM_EMAIL = "email";
	private static final String PARAM_PASSWORD = "password";

	@Override
	public String execute(HttpServletRequest request) {
		String lang = (String) request.getSession().getAttribute(LANGUAGE);
		String email = request.getParameter(PARAM_EMAIL);
		String password = request.getParameter(PARAM_PASSWORD);
		User user = null;

		UserService userService = new UserService();
		if (userService.isEmpty(email) || userService.isEmpty(password)) {
			request.setAttribute(ATTR_FILLALL,
					MessageManager.getProperty("message.fill_all", lang));
			return PathsManager.getProperty(PAGE_LOGIN);
		} else {
			if (userService.checkPassword(email, password)) {
				if (!userService.checkIsBanned(email)) {
					user = userService.getUser(email);
					request.getSession().setAttribute(ATTR_USER, user);
				} else {
					request.setAttribute(ATTR_BANNED,
							MessageManager.getProperty("message.banned", lang));
					return PathsManager.getProperty(PAGE_LOGIN);
				}
			} else {
				request.setAttribute(ATTR_CHECKLOGIN,
						MessageManager.getProperty("message.check_login", lang));
				return PathsManager.getProperty(PAGE_LOGIN);
			}

			if (user.getRole() == Role.CUSTOMER) {
				return PathsManager.getProperty(PAGE_CUSTOMER_HOME);
			} else if (user.getRole() == Role.MANAGER) {
				return PathsManager.getProperty(PAGE_MANAGER_HOME);
			} else if (user.getRole() == Role.ADMIN) {
				return PathsManager.getProperty(PAGE_ADMIN_HOME);
			} else {
				return PathsManager.getProperty(PAGE_GUEST_LOOK);
			}

		}

	}
}