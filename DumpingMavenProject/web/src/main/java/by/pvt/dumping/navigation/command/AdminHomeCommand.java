package by.pvt.dumping.navigation.command;

import by.pvt.dumping.managers.PathsManager;

import javax.servlet.http.HttpServletRequest;


public class AdminHomeCommand implements Command {

	private static final String PAGE_ADMIN_HOME = "admin.home";

	@Override
	public String execute(HttpServletRequest request) {
		return PathsManager.getProperty(PAGE_ADMIN_HOME);
	}
	
}
