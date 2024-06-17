package view;

import java.awt.Color;

/**
 * Classe listant les variables global de la view
 */
public class ViewCst {

	public static final boolean DEBUG = true;

	/* Calcul du scaling */
	public static final float MAX_SCALING = 16f;
	public static final float MIN_SCALING = 0.1f;
	public static final int MARGIN = 20;

	/* Lissage des déplacement */
	public static final float MOVE_FACTOR = 0.06f;

	/* Couleurs par defaut */
	public static final Color BACKGROUND_DEFAULT = Color.GRAY;

	/* Couleurs de debug */
	public static final Color DEBUG_PLAYER = Color.RED;
	public static final Color DEBUG_OBSTACLE = Color.GREEN;
	public static final Color DEBUG_GOLDFISH = Color.ORANGE;
	public static final Color DEBUG_SHARK = Color.CYAN;
	public static final Color DEBUG_BULLET = Color.DARK_GRAY;

}
