package by.pvt.dumping.navigation.command;

import by.pvt.dumping.managers.PathsManager;

import javax.servlet.http.HttpServletRequest;


public class CustomerHomeCommand implements Command {

	private static final String PAGE_CUSTOMER_HOME = "customer.home";
	
	@Override
	public String execute(HttpServletRequest request) {
		return PathsManager.getProperty(PAGE_CUSTOMER_HOME);
	}

}
