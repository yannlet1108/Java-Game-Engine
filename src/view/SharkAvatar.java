package view;

import java.awt.Graphics;

import model.Entity;

public class SharkAvatar extends Avatar {

	protected SharkAvatar(View m_view, Entity e) {
		super(m_view, e);
		this.spriteSetNumber = 6;
		m_view.store(this);
	}

	@Override
	void paint(Graphics g) {
		if (ViewCst.DEBUG) {
			m_view.getBank().debugCollisions(g, ViewCst.DEBUG_SHARK, instanceEntity.getHitbox());
		}

	}

}
