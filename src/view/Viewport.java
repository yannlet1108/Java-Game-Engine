package view;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;

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
	 * @param hitbox : hitbox de l'entité dans la simulation
	 * @return point dans le viewport
	 */
	Point toViewport(Rectangle2D hitbox) {
		double left = hitbox.getX();
		double right = hitbox.getX() + hitbox.getWidth();
		double top = hitbox.getY();
		double bottom = hitbox.getY() + hitbox.getHeight();
		if (right < viewbox.x || left > viewbox.x + viewbox.width * reverseScale())
			return null;
		if (bottom < viewbox.y || top > viewbox.y + viewbox.height * reverseScale())
			return null;
		return new Point((int) ((left - viewbox.x) * reverseScale()), (int) ((top - viewbox.y) * reverseScale()));
	}

	/**
	 * Appellé a chaque fois que le viewport achange de taille
	 * 
	 * @param center : nouveau centre du viewport
	 * @param scale  : nouveau scaling du viewport
	 */
	void updateViewport(Iterator<Point2D> playersPos, float scale) {
		this.scale = scale;
		Point2D center = playersPos.next();
		viewbox.x = (int) (center.getX() - (viewbox.width / 2) * reverseScale());
		viewbox.y = (int) (center.getY() - (viewbox.height / 2) * reverseScale());
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

	/**
	 * Retourne la largeur du viewport
	 * 
	 * @return largeur
	 */
	int getWidth() {
		return viewbox.width;
	}

	/**
	 * Retourne la hauteur du viewport
	 * 
	 * @return hauteur
	 */
	int getHeight() {
		return viewbox.height;
	}

}
