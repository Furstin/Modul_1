package by.pvt.dumping.navigation.command;

import javax.servlet.http.HttpServletRequest;

public interface Command {
	String execute(HttpServletRequest request);
}