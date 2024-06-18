package model;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Missile extends Entity {

	public Missile(Point2D position, Direction direction, Model model, int healthPoint) {
		super(position, direction, model, healthPoint);
		hitbox = new Rectangle2D.Double(position.getX(), position.getY(), 10, 2); //Constante de taille a redefinir
		this.team = 3;
		this.attackDamage = 30;
		this.meleeRange = 2;
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
