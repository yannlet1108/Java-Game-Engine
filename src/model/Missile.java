package model;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Missile extends Entity {

	public Missile(Point2D position, Direction direction, Model model, int healthPoint) {
		super(position, direction, model, healthPoint);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Rectangle2D getHitbox() {
		// TODO Auto-generated method stub
		throw new RuntimeException("Not Yet Implemented");
	}

	@Override
	public void egg() {
		// TODO Auto-generated method stub
		throw new RuntimeException("Not Yet Implemented");
	}

	@Override
	public void pick() {
		// TODO Auto-generated method stub
		throw new RuntimeException("Not Yet Implemented");
	}

}
