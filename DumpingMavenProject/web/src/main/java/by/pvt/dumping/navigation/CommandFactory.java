package by.pvt.dumping.navigation;

import by.pvt.dumping.navigation.command.Command;

public class CommandFactory {
	public static Command getCommand(String page) {
		CommandType currentCommand = CommandType.valueOf(page.toUpperCase());
		return currentCommand.getCommand();
	}
}
