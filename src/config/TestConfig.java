package config;

public class TestConfig {
	public static void main(String[] args) {
		Config config = new Config("/home/gwendal/Documents/Cours/3A/Projet/config.json");
		System.out.println("World");
		System.out.println("\tbackgroundImage:" + config.getStringValue("world", "backgroundImage"));
		System.out.println("\twidth:" + config.getIntValue("world", "width"));
		System.out.println("\theight:" + config.getIntValue("world", "height"));
		System.out.println("\tdensity:" + config.getFloatValue("world", "density"));

		System.out.println("Mob0");
		System.out.println("\timage:" + config.getStringValue("mob0", "image"));
		System.out.println("\tcategory:" + config.getCategory("mob0", "category"));

		System.out.println("Mob1");
		System.out.println("\timage:" + config.getStringValue("mob1", "image"));
		System.out.println("\tcategory:" + config.getCategory("mob1", "category"));
	}
}
