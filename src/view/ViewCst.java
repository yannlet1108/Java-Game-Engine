package view;

import java.awt.Color;

/**
 * Classe listant les variables global de la view
 */
public class ViewCst {

	public static final boolean DEBUG = false;

	/* Calcul du scaling */
	public static final float MAX_SCALING = 16f;
	public static final float MIN_SCALING = 0.1f;
	public static final int MARGIN = 20;
	public static final float PARALLAX = 1.5f;

	/* Lissage des déplacements */
	public static final float MOVE_FACTOR = 0.06f;
	public static final float SCALE_FACTOR = 0.06f;

	/* Couleurs par defaut */
	public static final Color BACKGROUND_DEFAULT = Color.BLUE;

	/* Couleurs de debug */
	public static final Color DEBUG_PLAYER = Color.RED;
	public static final Color DEBUG_OBSTACLE = Color.GREEN;
	public static final Color DEBUG_GOLDFISH = Color.ORANGE;
	public static final Color DEBUG_SHARK = Color.CYAN;
	public static final Color DEBUG_BULLET = Color.DARK_GRAY;

	/* Chargement des sprites */
	// A lister dans l'ordre {background, joueur1, joueur2, obstacles, bullet, mob1,
	// mob2}
	public static final String[] SPRITES_FILES = { "sprites/backgroundsprites.jpeg", "sprites/playersprites.png",
			"sprites/playersprites.png", "sprites/obstaclesprites.png", "sprites/bulletsprites.png",
			"sprites/goldfishsprites.png", "sprites/sharksprites.png" };
	public static final int[] SPRITES_NROWS = { 1, 3 };
	public static final int[] SPRITES_NCOLS = { 1, 4 };

}
