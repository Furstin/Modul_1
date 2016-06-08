package by.pvt.dumping.navigation.command;

import by.pvt.dumping.managers.PathsManager;

import javax.servlet.http.HttpServletRequest;


public class SignOutCommand implements Command {

	private static final String PAGE_GUEST_LOOK = "common.page-guest-look";
	private static final String ATTR_USER = "user";

	@Override
	public String execute(HttpServletRequest request) {
		if (request.getSession().getAttribute(ATTR_USER) != null) {
			request.getSession().removeAttribute(ATTR_USER);
		}
		return PathsManager.getProperty(PAGE_GUEST_LOOK);
	}
	
}
