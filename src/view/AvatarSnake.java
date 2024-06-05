package view;

import java.awt.Graphics;

public class AvatarSnake extends Avatar {

	AvatarSnake(View m_view/* , Entity e */) {
		super(m_view/* , e */);
	}

	@Override
	public void paint(Graphics g) {
		// LinkedList<int[]> points = ((Snake) instanceEntity).getPointsList();
		// Iterator<int[]> pointsIterator = points.iterator();
		// int currentPoint[] = pointsIterator.next();
		// m_view.paintShape(g, 3, Color.ORANGE, currentPoint[0], currentPoint[1]);
		// while (pointsIterator.hasNext()) {
		// currentPoint = pointsIterator.next();
		// m_view.paintShape(g, 1, Color.ORANGE, currentPoint[0], currentPoint[1]);
		// }
	}
}
