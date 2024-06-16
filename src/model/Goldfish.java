package model;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Goldfish extends Fish {

	public Goldfish(Point2D position, Direction direction, Model model, int healthPoint) {
		super(position, direction, model, healthPoint);
		hitbox = new Rectangle2D.Double(position.getX(), position.getY(), 4, 2); //Constante de taille a redefinir
		this.attackDamage = 5;
		this.meleeRange = 2;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void egg() {
		// TODO Auto-generated method stub
		Point2D pos = this.getCenter();
		pos.setLocation(this.getX() + getRandomNumber(10, 10), this.getY() + getRandomNumber(10, 10));
		Goldfish g = new Goldfish(pos, this.getDirection(), this.getModel(), this.attackDamage);		
	}

	@Override
	public void pick() {
		// TODO Auto-generated method stub
		
	}
	
	public double getRandomNumber(double min, double max) {
	    return (Math.random() * (max - min)) + min;
	}

}
