package by.pvt.dumping.navigation.command;

import by.pvt.dumping.managers.PathsManager;
import by.pvt.dumping.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public class ViewDebtorsCommand implements Command {

	private static final String PAGE_MANAGER_DEBTORS = "manager.debtors";
	private static final String ATTR_DEBTORS = "debtors";

	@Override
	public String execute(HttpServletRequest request) {
		UserService userService = new UserService();
		List<String> list = userService.getDebtors();
		request.setAttribute(ATTR_DEBTORS, list);
		return PathsManager.getProperty(PAGE_MANAGER_DEBTORS);
	}
	
}
