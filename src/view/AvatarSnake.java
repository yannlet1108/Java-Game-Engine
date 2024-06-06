package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Iterator;
import java.util.LinkedList;

public class AvatarSnake extends Avatar {

	AvatarSnake(View m_view/* , Entity e */) {
		super(m_view/* , e */);
	}

	@Override
	public void paint(Graphics g) {
		Iterator<Point> pointsIterator = ((Snake) instanceEntity()).getIterator();
		Point currentPoint = pointsIterator.next();
		m_view.paintShape(g, 3, Color.ORANGE, currentPoint.x, currentPoint.y);
		while (pointsIterator.hasNext()) {
			currentPoint = pointsIterator.next();
			m_view.paintShape(g, 1, Color.ORANGE, currentPoint.x, currentPoint.y);
		}
	}
}
