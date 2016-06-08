package by.pvt.dumping.navigation.command;

import by.pvt.dumping.managers.MessageManager;
import by.pvt.dumping.managers.PathsManager;
import by.pvt.dumping.service.UserService;

import javax.servlet.http.HttpServletRequest;


public class BanCutomerCommand implements Command {

	private static final String PAGE_MANAGER_HOME = "manager.home";
	private static final String ATTR_CUSTOMER_BANNED = "cbanned";
	private static final String ATTR_CUSTOMER_NOT_BANNED = "cnotbanned";
	private static final String PARAM_EMAIL = "email";
	private static final String ATTR_LANGUAGE = "language";

	@Override
	public String execute(HttpServletRequest request) {
		String email = request.getParameter(PARAM_EMAIL);
		String lang = (String) request.getSession().getAttribute(ATTR_LANGUAGE);
		
		UserService userService = new UserService();
		if (userService.isEmpty(email)) {
			request.setAttribute(ATTR_CUSTOMER_NOT_BANNED, MessageManager
					.getProperty("message.customer_not_banned", lang));
			return PathsManager.getProperty(PAGE_MANAGER_HOME);
		} else {
			userService.banCustomer(email);
			request.setAttribute(ATTR_CUSTOMER_BANNED,
					MessageManager.getProperty("message.customer_banned", lang));
			return PathsManager.getProperty(PAGE_MANAGER_HOME);
		}

	}

	
}
