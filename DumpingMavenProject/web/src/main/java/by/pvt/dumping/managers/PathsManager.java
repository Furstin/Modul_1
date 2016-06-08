package by.pvt.dumping.managers;

import java.util.ResourceBundle;

public class PathsManager {
	private final static ResourceBundle resourceBundle = ResourceBundle
			.getBundle("Paths");


	private PathsManager() {}


	public static String getProperty(String key) {
		return resourceBundle.getString(key);
	}
}
