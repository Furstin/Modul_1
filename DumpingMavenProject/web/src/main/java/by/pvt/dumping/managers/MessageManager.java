package by.pvt.dumping.managers;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageManager {

	private MessageManager() {
	}

	public static String getProperty(String key, String lang) {
		Locale locale = new Locale(lang);
		ResourceBundle resourceBundle = ResourceBundle.getBundle(
				"Bundle", locale);
		return resourceBundle.getString(key);
	}
	
	
}
