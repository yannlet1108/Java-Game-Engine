package model;

import java.awt.geom.Point2D;

public abstract class Fish extends Entity {

	public Fish(Point2D position, Direction direction, Model model, int healthPoint) {
		super(position, direction, model, healthPoint);
		this.team = 2;
		this.category = Category.FISH;
		// TODO Auto-generated constructor stub
	}

	@Override
	public abstract Entity egg();
}
