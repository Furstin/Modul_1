package by.pvt.dumping.navigation.command;

import by.pvt.dumping.managers.PathsManager;

import javax.servlet.http.HttpServletRequest;


public class ManagerHomeCommand implements Command {

	private static final String PAGE_MANAGER_HOME = "manager.home";

	@Override
	public String execute(HttpServletRequest request) {
		return PathsManager.getProperty(PAGE_MANAGER_HOME);
	}
	
}
