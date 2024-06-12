package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

/**
 * Classe utilisé comme banque de sprite pour les avatars
 */
public class SpriteBank {

	private View m_view;

	/**
	 * Initialise la banque de sprite
	 * 
	 * @param m_view : instance courante de la view
	 */
	SpriteBank(View m_view) {
		this.m_view = m_view;
		m_view.setBank(this);
	}

	/**
	 * Affiche la boite de collision de l'entité
	 * 
	 * @param g            : inctance graphique du canvas
	 * @param c            : couleur de la boite de collision
	 * @param collisionBox : coordonnées et taille de la boite de collision
	 */
	void debugCollisions(Graphics g, Color c, Rectangle2D collisionBox) {
		g.setColor(c);
		Point origin = m_view.getViewport().toViewport(collisionBox);
		if (origin == null)
			return;
		g.drawRect(origin.x, origin.y, (int) (collisionBox.getWidth() * m_view.getViewport().getScale()),
				(int) (collisionBox.getHeight() * m_view.getViewport().getScale()));
	}
}
