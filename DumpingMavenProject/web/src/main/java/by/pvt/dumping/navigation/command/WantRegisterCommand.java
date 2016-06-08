package by.pvt.dumping.navigation.command;

import by.pvt.dumping.managers.PathsManager;

import javax.servlet.http.HttpServletRequest;



public class WantRegisterCommand implements Command{
	private static final String PAGE_REGISTER = "common.page-register";
	
	@Override
	public String execute(HttpServletRequest request) {
		return PathsManager.getProperty(PAGE_REGISTER);
	}

}
