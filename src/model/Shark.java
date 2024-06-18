package model;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Shark extends Fish {

	public Shark(Point2D position, Direction direction, Model model) {
		super(position, direction, model);
		hitbox = new Rectangle2D.Double(position.getX(), position.getY(), EntityConstants.SHARK_WIDTH,
				EntityConstants.SHARK_HEIGHT);
		this.attackDamage = EntityConstants.SHARK_ATTACK_DAMAGE;
		this.meleeRange = EntityConstants.SHARK_MELEE_RANGE;
		this.healthPoint = EntityConstants.SHARK_HEALTH_POINT;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void egg() {
		// TODO Auto-generated method stub
	}

	@Override
	public void pick() {
		throw new RuntimeException("Not Yet Implemented");
	}
}
