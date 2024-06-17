package view;

import java.awt.Color;

/**
 * Classe listant les variables global de la view
 */
public class ViewCst {

	public static final boolean DEBUG = true;

	/* Couleurs par defaut */
	public static final Color BACKGROUND_DEFAULT = Color.GRAY;

	/* Couleurs de debug */
	public static final Color DEBUG_PLAYER = Color.RED;
	public static final Color DEBUG_OBSTACLE = Color.GREEN;
	public static final Color DEBUG_GOLDFISH = Color.ORANGE;
	public static final Color DEBUG_SHARK = Color.CYAN;
	public static final Color DEBUG_BULLET = Color.DARK_GRAY;

	public static final String[] SPRITES_FILES = { "sprites/backgroundsprites", "sprites/playersprites.png",
			"sprites/obstaclesprites", "sprites/bulletsprites", "sprites/goldfishsprites", "sprites/sharksprites" };
	public static final int[] SPRITES_NROWS = { 2, 6, 2 };
	public static final int[] SPRITES_NCOLS = { 2, 6, 2 };

}
