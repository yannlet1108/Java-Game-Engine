package view;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;

import info3.game.graphics.GameCanvas;

/**
 * Classe permettant de calculer plus efficacement l'intégralité des scaling lié
 * à la taille variable du viewport
 */
public class Viewport {

	private GameCanvas m_canvas;

	private Rectangle viewbox;
	private float scale;

	/**
	 * Crée le viewport
	 * 
	 * @param m_canvas : instance du canvas de la view
	 */
	Viewport(GameCanvas m_canvas) {
		this.m_canvas = m_canvas;
		viewbox = new Rectangle(0, 0, m_canvas.getWidth(), m_canvas.getHeight());
		scale = 1;
	}

	/**
	 * Convertie une coordonnée de simulation en coordonnée sur le viewport
	 * 
	 * @param p : point sur la simulation
	 * @return point dans le viewport
	 */
	Point toViewport(Point2D p) {
		if (p.getX() < viewbox.x || p.getX() > viewbox.x + viewbox.height * reverseScale())
			return null;
		if (p.getY() < viewbox.y || p.getY() > viewbox.y + viewbox.width * reverseScale())
			return null;
		return new Point((int) ((p.getX() - viewbox.x) * reverseScale()),
				(int) ((p.getY() - viewbox.y) * reverseScale()));
	}

	/**
	 * Appellé a chaque fois que le viewport achange de taille
	 * 
	 * @param center : nouveau centre du viewport
	 * @param scale  : nouveau scaling du viewport
	 */
	void updateViewport(Point2D center, float scale) {
		this.scale = scale;
		viewbox.x = (int) (center.getX() - (viewbox.height / 2) * reverseScale());
		viewbox.y = (int) (center.getY() - (viewbox.width / 2) * reverseScale());
	}

	/**
	 * Appellé a chaque fois que la fenêtre change de taille
	 */
	void resize() {
		viewbox.height = m_canvas.getHeight();
		viewbox.width = m_canvas.getWidth();
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

}
