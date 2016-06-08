package by.pvt.dumping.navigation.command;

import by.pvt.dumping.entity.Role;
import by.pvt.dumping.entity.User;
import by.pvt.dumping.managers.PathsManager;

import javax.servlet.http.HttpServletRequest;



public class SelectLanguageCommand implements Command {
	private static final String USER = "user";
	public static final String LANGUAGE = "language";

	@Override
	public String execute(HttpServletRequest request) {
		String strLanguage = request.getParameter(LANGUAGE);
		User user = (User) request.getSession().getAttribute(USER);
		request.getSession().setAttribute(LANGUAGE, strLanguage);

		if (user == null) {
			return PathsManager.getProperty("common.page-guest-look");
		}
		if (user.getRole() == Role.CUSTOMER) {
			return PathsManager.getProperty("customer.home");
		}
		if (user.getRole() == Role.MANAGER) {
			return PathsManager.getProperty("manager.home");
		}
		if (user.getRole() == Role.ADMIN) {
			return PathsManager.getProperty("admin.home");
		}
		return PathsManager.getProperty("common.page-guest-look");

	}
}
