package model;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Missile extends Entity {

	public Missile(Point2D position, Direction direction, Model model) {
		super(position, direction, model);
		hitbox = new Rectangle2D.Double(position.getX(), position.getY(), EntityConstants.MISSILE_WIDTH,
				EntityConstants.MISSILE_HEIGHT);
		this.team = EntityConstants.MISSILE_TEAM;
		this.attackDamage = EntityConstants.MISSILE_ATTACK_DAMAGE;
		this.meleeRange = EntityConstants.MISSILE_MELEE_RANGE;
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
