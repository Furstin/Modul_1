package by.pvt.dumping.navigation.command;

import by.pvt.dumping.managers.MessageManager;
import by.pvt.dumping.managers.PathsManager;
import by.pvt.dumping.service.UserService;

import javax.servlet.http.HttpServletRequest;


public class DeleteManagerCommand implements Command {

	private static final String ATTR_MNOSUCH = "mnosuch";
	private static final String PAGE_ADMIN_HOME = "admin.home";
	private static final String ATTR_MDELETED = "mdeleted";
	private static final String PARAM_EMAIL = "email";
	private static final String ATTR_LANGUAGE = "language";

	@Override
	public String execute(HttpServletRequest request) {
		String email = request.getParameter(PARAM_EMAIL);
		String lang = (String) request.getSession().getAttribute(ATTR_LANGUAGE);

		UserService userService = new UserService();
		if (!userService.checkEmail(email)) {
			userService.removeUser(email);
			request.setAttribute(ATTR_MDELETED,
					MessageManager.getProperty("message.manager_deleted", lang));
			return PathsManager.getProperty(PAGE_ADMIN_HOME);
		} else {
			request.setAttribute(ATTR_MNOSUCH,
					MessageManager.getProperty("message.no_such_manager", lang));
			return PathsManager.getProperty(PAGE_ADMIN_HOME);
		}
	}
	
}
