package model;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Shark extends Fish {

	public Shark(Point2D position, Direction direction, Model model, int healthPoint) {
		super(position, direction, model, healthPoint);
		hitbox = new Rectangle2D.Double(position.getX(), position.getY(), 20, 8); //Constante de taille a redefinir
		this.attackDamage = 50;
		this.meleeRange = 5;
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
