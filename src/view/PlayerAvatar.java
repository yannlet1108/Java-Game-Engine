package view;

import java.awt.Graphics;

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
	public PlayerAvatar(View m_view, Entity player) {
		super(m_view, player);
	}

	@Override
	void paint(Graphics g) {
		m_view.getBank().debugCollisions(g, ViewCst.DEBUG_PLAYER, e.get...);
	}

}
