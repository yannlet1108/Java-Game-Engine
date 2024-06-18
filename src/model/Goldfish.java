package model;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Goldfish extends Fish {

	public Goldfish(Point2D position, Direction direction, Model model) {
		super(position, direction, model);
		hitbox = new Rectangle2D.Double(position.getX(), position.getY(), EntityConstants.GOLDFISH_WIDTH,
				EntityConstants.GOLDFISH_HEIGHT);
		this.attackDamage = EntityConstants.GOLDFISH_ATTACK_DAMAGE;
		this.meleeRange = EntityConstants.GOLDFISH_MELEE_RANGE;
		this.healthPoint = EntityConstants.GOLDFISH_HEALTH_POINT;
		// TODO Auto-generated constructor stub

	}

	@Override
	public void pick() {
		throw new RuntimeException("Not Yet Implemented");
	}

	public double getRandomNumber(double min, double max) {
		return (Math.random() * (max - min)) + min;
	}

	@Override
	public void egg() {
		// TODO Auto-generated method stub

	}
}
