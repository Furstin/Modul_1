package by.pvt.dumping.navigation.command;

import by.pvt.dumping.managers.PathsManager;

import javax.servlet.http.HttpServletRequest;

public class WantLoginCommand implements Command {
	private static final String PAGE_LOGIN = "common.page-login";

	@Override
	public String execute(HttpServletRequest request) {return PathsManager.getProperty(PAGE_LOGIN);
	}
}
