package model;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Goldfish extends Fish {

	public Goldfish(Point2D position, Direction direction, Model model) {
		super(position, direction, model, FishConstants.GOLDFISH_HEALTH);
		hitbox = new Rectangle2D.Double(position.getX(), position.getY(), FishConstants.GOLDFISH_WIDTH,
				FishConstants.GOLDFISH_HEIGHT);
		this.attackDamage = 5;
		this.meleeRange = 2;
	}

	@Override
	public void pick() {
		throw new RuntimeException("Not Yet Implemented");
	}

	public double getRandomNumber(double min, double max) {
		return (Math.random() * (max - min)) + min;
	}

	@Override
	public Entity egg() {
		throw new RuntimeException("Not Yet Implemented");
	}
}
