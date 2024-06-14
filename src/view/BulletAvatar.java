package view;

import java.awt.Graphics;

import model.Entity;

public class BulletAvatar extends Avatar{

	protected BulletAvatar(View m_view, Entity e) {
		super(m_view, e);
		m_view.store(this);
	}

	@Override
	void paint(Graphics g) {
		if (ViewCst.DEBUG) {
			m_view.getBank().debugCollisions(g, ViewCst.DEBUG_BULLET, instanceEntity.getHitbox());
		}
	}


}
