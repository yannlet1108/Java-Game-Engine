package view;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import info3.game.graphics.GameCanvas;

/**
 * Classe permettant de calculer plus efficacement l'intégralité des scaling lié
 * à la taille variable du viewport
 */
public class Viewport {

	private GameCanvas m_canvas;

	private Rectangle2D viewbox;
	private float scale;

	/**
	 * Crée le viewport
	 * 
	 * @param m_canvas : instance du canvas de la view
	 */
	Viewport(GameCanvas m_canvas, View view) {
		this.m_canvas = m_canvas;
		viewbox = new Rectangle2D.Double(0, 0, m_canvas.getWidth(), m_canvas.getHeight());
		scale = 1;
	}

	/**
	 * Convertie une coordonnée de simulation en coordonnée sur le viewport
	 * 
	 * @param hitbox : hitbox de l'entité dans la simulation
	 * @return point dans le viewport
	 */
	Point toViewport(Rectangle2D hitbox) {
		double left = hitbox.getX();
		double right = hitbox.getX() + hitbox.getWidth();
		double top = hitbox.getY();
		double bottom = hitbox.getY() + hitbox.getHeight();
		if (right < viewbox.getX() || left > viewbox.getX() + viewbox.getWidth() * reverseScale())
			return null;
		if (bottom < viewbox.getY() || top > viewbox.getY() + viewbox.getHeight() * reverseScale())
			return null;
		return new Point((int) ((left - viewbox.getX()) * getScale()), (int) ((top - viewbox.getY()) * getScale()));
	}

	/**
	 * Appellé a chaque fois que le viewport à change de taille
	 * 
	 * @param playersPos : positions des joueurs
	 */
	void updateViewport(Point2D tops[]) {
		double xMax = tops[0].getX();
		double xMin = tops[1].getX();
		double yMax = tops[0].getY();
		double yMin = tops[1].getY();

		double scaling_x = (xMax - xMin + 2 * ViewCst.MARGIN) / this.getWidth();
		double scaling_y = (yMax - yMin + 2 * ViewCst.MARGIN) / this.getHeight();
		double th_scaling;
		if (scaling_x < scaling_y)
			th_scaling = scaling_y;
		else
			th_scaling = scaling_x;
		if (th_scaling != 0) {
			th_scaling = 1 / th_scaling;
			if (th_scaling > ViewCst.MAX_SCALING) {
				th_scaling = ViewCst.MAX_SCALING;
			} else if (th_scaling < ViewCst.MIN_SCALING)
				th_scaling = ViewCst.MIN_SCALING;
		} else
			th_scaling = ViewCst.MAX_SCALING;
		this.scale = (float) (this.scale - (this.scale - th_scaling) * ViewCst.SCALE_FACTOR);

		Point2D center = new Point2D.Double(xMin + (xMax - xMin) / 2, yMin + (yMax - yMin) / 2);
		double x_theorical = center.getX() - (viewbox.getWidth() / 2) * reverseScale();
		double y_theorical = center.getY() - (viewbox.getHeight() / 2) * reverseScale();
		double length_x = viewbox.getX() - x_theorical;
		double length_y = viewbox.getY() - y_theorical;

		double newX = viewbox.getX() - length_x * ViewCst.MOVE_FACTOR;
		double newY = viewbox.getY() - length_y * ViewCst.MOVE_FACTOR;
		viewbox.setRect(newX, newY, viewbox.getHeight(), viewbox.getHeight());
	}

	/**
	 * Appellé a chaque fois que la fenêtre change de taille
	 */
	void resize() {
		viewbox.setRect(viewbox.getX(), viewbox.getY(), m_canvas.getWidth(), m_canvas.getHeight());
	}

	Point backgroundPos(double simX, double simY, float height, float width) {
		double distX = viewbox.getX() / width * simX;
		double distY = viewbox.getY() / height * simY;
		return new Point((int) (viewbox.getX() - distX), (int) (viewbox.getY() - distY));
	}

	/**
	 * Retourne le scaling du viewport
	 * 
	 * @return scaling de dessin
	 */
	float getScale() {
		return scale;
	}

	/**
	 * Retourne l'inverse du scaling du viewport
	 * 
	 * @return reverse scaling du dessin
	 */
	float reverseScale() {
		return 1 / scale;
	}

	/**
	 * Retourne la largeur du viewport
	 * 
	 * @return largeur
	 */
	int getWidth() {
		return (int) viewbox.getWidth();
	}

	/**
	 * Retourne la hauteur du viewport
	 * 
	 * @return hauteur
	 */
	int getHeight() {
		return (int) viewbox.getHeight();
	}

	/**
	 * retourne la coordonnée X de la viewbox
	 * 
	 * @return coordonnée X du viewport
	 */
	int getX() {
		return (int) viewbox.getX();
	}

	/**
	 * retourne la coordonnée Y de la viewbox
	 * 
	 * @return coordonnée Y du viewport
	 */
	int getY() {
		return (int) viewbox.getY();
	}

	Point2D getCenter() {
		return new Point2D.Double(viewbox.getCenterX(), viewbox.getCenterY());
	}
}
