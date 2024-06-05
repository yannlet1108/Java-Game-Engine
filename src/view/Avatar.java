package view;

import java.awt.Graphics;

import org.w3c.dom.Entity;

public abstract class Avatar {

	View m_view;

	protected Entity instanceEntity;

	protected boolean isVisible;

	Avatar(View m_view, Entity e) {
		this.m_view = m_view;
		instanceEntity = e;
		setInvisible();
	}

	public Entity getEntity() {
		return instanceEntity;
	}

	public void setVisible() {
		isVisible = true;
	}

	public void setInvisible() {
		isVisible = false;
	}

	public abstract void paint(Graphics g);

}
