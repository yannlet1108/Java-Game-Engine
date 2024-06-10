package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Iterator;

import model.Entity;
import model.Snake;

public class AvatarSnake extends Avatar {

	public AvatarSnake(View m_view, Entity e) {
		super(m_view, e);
	}

	@Override
	public void paint(Graphics g) {
		Iterator<Point> pointsIterator = ((Snake) instanceEntity).getPointsIterator();
		Point currentPoint = pointsIterator.next();
		m_view.paintShape(g, 2, Color.ORANGE, currentPoint.x, currentPoint.y, instanceEntity.getDirection());
		while (pointsIterator.hasNext()) {
			currentPoint = pointsIterator.next();
			m_view.paintShape(g, 0, Color.ORANGE, currentPoint.x, currentPoint.y);
		}
	}
}
