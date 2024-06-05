package view;

import java.awt.Color;

public class ViewCst {

	public static final int WIN_WIDTH = 1024;
	public static final int WIN_HEIGHT = 768;

	public static final Color BACKGROUND_COLOR = Color.GRAY;
	public static final Color PLAYGROUND_COLOR = Color.GREEN;

	public static final int GAME_SQUARE = 15;
	public static final int X_MARGIN = WIN_WIDTH - WIN_HEIGHT + GAME_SQUARE;
	public static final int Y_MARGIN = GAME_SQUARE;

	public static final int CELL_SIZE = (WIN_WIDTH - 2 * X_MARGIN) / ModelCst.NB_CELL;
	public static final int CELL_MARGIN = 5;
}
