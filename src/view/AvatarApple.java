package view;

import java.awt.Color;
import java.awt.Graphics;

import model.Entity;

public class AvatarApple extends Avatar {

	AvatarApple(View m_view, Entity e) {
		super(m_view, e);
	}

	@Override
	public void paint(Graphics g) {
		m_view.paintShape(g, 1, Color.RED, instanceEntity.getX(), instanceEntity.getY());
	}
}
