package view;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

import model.Entity;

/**
 * Classe d'avatar des players
 */
public class PlayerAvatar extends Avatar {

	/**
	 * Crée un avatar de player
	 * 
	 * @param m_view : instance courante de la view
	 * @param player : instance du player
	 */
	public PlayerAvatar(View m_view, Entity player, int entityType) {
		super(m_view, player, entityType);
		m_view.store(this);
	}


	void debugPaint(Graphics g) {
		m_view.getBank().debugCollisions(g, ViewCst.DEBUG_PLAYER, instanceEntity.getHitbox());
	}

}
