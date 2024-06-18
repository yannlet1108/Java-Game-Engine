package view;

import java.awt.Graphics;

import model.Entity;

public class GoldfishAvatar extends Avatar {

	protected GoldfishAvatar(View m_view, Entity e, int entityType) {
		super(m_view, e, entityType);
		this.spriteSetNumber = 5;
		m_view.store(this);
	}

	@Override
	void debugPaint(Graphics g) {
			m_view.getBank().debugCollisions(g, ViewCst.DEBUG_GOLDFISH, instanceEntity.getHitbox());
	}

}
