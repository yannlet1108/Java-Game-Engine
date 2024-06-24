package view;

import config.Config;

/**
 * Classe listant les variables global de la view
 */
public class ViewCst {

	public static boolean DEBUG;
	public static boolean UID;

	/* Calcul du scaling */
	public static final float PARALLAX = 0.95f;
	public static float MAX_SCALING;
	public static float MIN_SCALING;
	public static int MARGIN;

	/* Lissage des déplacements */
	public static float MOVE_FACTOR;
	public static float SCALE_FACTOR;

	/**
	 * Initialise les constantes de la view
	 * 
	 * @param conf : configuration du jeu
	 */
	public ViewCst(Config conf) {
		DEBUG = conf.getBooleanValue("View", "debug");
		UID = conf.getBooleanValue("View", "uid");

		MAX_SCALING = conf.getFloatValue("View", "max_scaling");
		MIN_SCALING = conf.getFloatValue("View", "min_scaling");
		MARGIN = conf.getIntValue("View", "margin");

		MOVE_FACTOR = conf.getFloatValue("View", "move_factor");
		SCALE_FACTOR = conf.getFloatValue("View", "scale_factor");

	}

}
