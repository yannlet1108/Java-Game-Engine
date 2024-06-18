package model;

import java.awt.geom.Point2D;

public abstract class Fish extends Entity {

	public Fish(Point2D position, Direction direction, Model model) {
		super(position, direction, model);
		this.team = EntityConstants.FISH_TEAM;
		this.category = Category.FISH;
		// TODO Auto-generated constructor stub
	}

	@Override
	public abstract void egg();
}
