package view;

import java.awt.Graphics;

import model.Entity;

public class BulletAvatar extends Avatar {

	protected BulletAvatar(View m_view, Entity e, int entityType) {
		super(m_view, e, entityType);
	}

	@Override
	void debugPaint(Graphics g) {
		m_view.getBank().debugCollisions(g, ViewCst.DEBUG_BULLET, instanceEntity.getHitbox());
	}

}
