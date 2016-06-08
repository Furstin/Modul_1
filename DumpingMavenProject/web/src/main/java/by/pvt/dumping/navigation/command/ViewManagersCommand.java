package by.pvt.dumping.navigation.command;

import by.pvt.dumping.entity.Role;
import by.pvt.dumping.entity.User;
import by.pvt.dumping.managers.PathsManager;
import by.pvt.dumping.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public class ViewManagersCommand implements Command {

	private static final String PAGE_ADMIN_MANAGERS = "admin.managers";
	private static final String ATTR_MANAGERS = "resources";

	@Override
	public String execute(HttpServletRequest request) {
		UserService userService = new UserService();
		List<User> managers = userService.getUsersByRole(Role.MANAGER);
		request.setAttribute(ATTR_MANAGERS, managers);
		return PathsManager.getProperty(PAGE_ADMIN_MANAGERS);
	}
	
}
