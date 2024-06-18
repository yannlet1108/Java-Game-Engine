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
	public PlayerAvatar(View m_view, Entity player) {
		super(m_view, player);
		this.spriteSetNumber = 1;
		m_view.store(this);
	}


	@Override
	void paint(Graphics g) {
		if (ViewCst.DEBUG) {
			m_view.getBank().debugCollisions(g, ViewCst.DEBUG_PLAYER, instanceEntity.getHitbox());
		} else {
			Rectangle2D collisionBox = instanceEntity.getHitbox();
			Point origin = m_view.getViewport().toViewport(collisionBox);
			if (origin == null) {
				return;
			}
			g.drawImage(m_view.getBank().getSprite(spriteSetNumber, currentSpriteNumber), origin.x, origin.y,
					(int) (collisionBox.getWidth() * m_view.getViewport().getScale()),
					(int) (collisionBox.getHeight() * m_view.getViewport().getScale()), null);
		}
	}

}
