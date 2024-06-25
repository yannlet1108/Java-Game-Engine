package view;

import config.Config;

/**
 * Classe listant les variables global de la view
 */
public class ViewCst {

	public static boolean DEBUG;
	public static boolean UID;

	/* Calcul du scaling */
	public static float PARALLAX;
	public static float MAX_SCALING;
	public static float MIN_SCALING;
	public static int MARGIN;
	public static int TOP_MARGIN;
	public static int BOTTOM_MARGIN;
	public static int LEFT_MARGIN;
	public static int RIGHT_MARGIN;

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

		PARALLAX = conf.getFloatValue("View", "parallax");
		MAX_SCALING = conf.getFloatValue("View", "max_scaling");
		MIN_SCALING = conf.getFloatValue("View", "min_scaling");
		MARGIN = conf.getIntValue("View", "margin");

		MOVE_FACTOR = conf.getFloatValue("View", "move_factor");
		SCALE_FACTOR = conf.getFloatValue("View", "scale_factor");

		TOP_MARGIN = conf.getIntValue("View", "top_margin");
		BOTTOM_MARGIN = conf.getIntValue("View", "bottom_margin");
		LEFT_MARGIN = conf.getIntValue("View", "left_margin");
		RIGHT_MARGIN = conf.getIntValue("View", "right_margin");

	}

}
