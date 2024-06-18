package model;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Shark extends Fish {

	public Shark(Point2D position, Direction direction, Model model) {
		super(position, direction, model, FishConstants.SHARK_HEALTH);
		hitbox = new Rectangle2D.Double(position.getX(), position.getY(), FishConstants.SHARK_WIDTH,
				FishConstants.SHARK_HEIGHT);
		this.attackDamage = 50;
		this.meleeRange = 10;
	}

	@Override
	public Entity egg() {
		throw new RuntimeException("Not Yet Implemented");

	}

	@Override
	public void pick() {
		throw new RuntimeException("Not Yet Implemented");
	}
}
