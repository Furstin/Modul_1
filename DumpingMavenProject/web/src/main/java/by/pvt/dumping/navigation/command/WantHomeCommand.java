package by.pvt.dumping.navigation.command;

import by.pvt.dumping.managers.PathsManager;

import javax.servlet.http.HttpServletRequest;


public class WantHomeCommand implements Command {

	private static final String PAGE_GUEST_LOOK = "common.page-guest-look";
	
	@Override
	public String execute(HttpServletRequest request) {
		return PathsManager.getProperty(PAGE_GUEST_LOOK);
	}

}
