package view;

import java.awt.Graphics;

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

	/**
	 * Affiche la hitbox de debug de l'avatar player
	 */
	void debugPaint(Graphics g) {
		m_view.getBank().debugCollisions(g, ViewCst.DEBUG_PLAYER, instanceEntity.getHitbox());
	}

}
