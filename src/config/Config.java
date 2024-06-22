package config;

import java.io.FileInputStream;
import java.io.IOException;
import org.json.*;

import model.Category;

public class Config {
	JSONObject config;

	public Config(String configPath) {
		try {
			FileInputStream configInputStream = new FileInputStream(configPath);
			JSONTokener tokener = new JSONTokener(configInputStream);
			config = new JSONObject(tokener);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getIntValue(String element, String parameter) {
		return getJSONElement(element).getInt(parameter);
	}

	public String getStringValue(String element, String parameter) {
		return getJSONElement(element).getString(parameter);
	}
	
	public boolean getBooleanValue(String element, String parameter) {
		return getJSONElement(element).getBoolean(parameter);
	}

	public float getFloatValue(String element, String parameter) {
		return getJSONElement(element).getFloat(parameter);
	}

	public Category getCategory(String element, String parameter) {
		return getJSONElement(element).getEnum(Category.class, parameter);
	}

	private JSONObject getJSONElement(String element) {
		if (element.matches("mob[0-9]")) {
			return config.getJSONArray("mobs").getJSONObject(element.charAt(3) - 48);
		}
		return config.getJSONObject(element);
	}
}
