package view;

import java.awt.Graphics;

import model.Entity;

public class SharkAvatar extends Avatar {

	protected SharkAvatar(View m_view, Entity e, int entityType) {
		super(m_view, e, entityType);
		this.spriteSetNumber = 6;
		m_view.store(this);
	}

	@Override
	void debugPaint(Graphics g) {
			m_view.getBank().debugCollisions(g, ViewCst.DEBUG_SHARK, instanceEntity.getHitbox());

	}

}
