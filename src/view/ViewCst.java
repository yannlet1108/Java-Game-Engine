package view;

import java.awt.Color;

/**
 * Classe listant l'integralite des constantes relative a la View.
 * 
 * @implNote TODO Supprimer les constantes inutiles
 */
public class ViewCst {

	public static final int WIN_WIDTH = 1024;
	public static final int WIN_HEIGHT = 768;

	public static final Color BACKGROUND_COLOR = Color.GRAY;
	public static final Color LINE_COLOR = Color.RED;
	public static final Color PLAYGROUND_COLOR = Color.GREEN;

	public static final int PLAYGROUND_WIDTH = 500;
	public static final int PLAYGROUND_HEIGHT = 500;

	public static final int X_MARGIN = (WIN_WIDTH - PLAYGROUND_WIDTH) / 2;
	public static final int Y_MARGIN = (WIN_HEIGHT - PLAYGROUND_HEIGHT) / 2;

	// a supprimer (from model)
	public static final int NB_CELL_HEIGHT = 15;
	public static final int NB_CELL_WIDTH = 15;

	public static final int CELL_HEIGHT = PLAYGROUND_HEIGHT / NB_CELL_HEIGHT;
	public static final int CELL_WIDTH = PLAYGROUND_WIDTH / NB_CELL_WIDTH;

}
