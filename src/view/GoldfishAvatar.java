package view;

import java.awt.Graphics;

import model.Entity;

public class GoldfishAvatar extends Avatar {

	protected GoldfishAvatar(View m_view, Entity e) {
		super(m_view, e);
		this.spriteSetNumber = 5;
		m_view.store(this);
	}

	@Override
	void paint(Graphics g) {
		if (ViewCst.DEBUG) {
			m_view.getBank().debugCollisions(g, ViewCst.DEBUG_GOLDFISH, instanceEntity.getHitbox());
		}
	}

}
