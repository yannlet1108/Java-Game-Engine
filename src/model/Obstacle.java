package model;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Obstacle extends Entity {

	public Obstacle(Point2D position, Direction direction, Model model) {
		super(position, direction, model);
		hitbox = new Rectangle2D.Double(position.getX(), position.getY(), EntityConstants.OBSTACLE_WIDTH,
				EntityConstants.OBSTACLE_HEIGHT);
		this.healthPoint = EntityConstants.OBSTACLE_HEALTH_POINT;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void egg() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pick() {
		// TODO Auto-generated method stub

	}

}
