package model;

import java.awt.geom.Point2D;

public class Player extends Entity {

	public Player(Point2D position, Direction direction, Model model) {
		super(position, direction, model);
	}

	@Override
	public void egg() {
		throw new RuntimeException("Not Yet Implemented");
	}

	@Override
	public void pick() {
		throw new RuntimeException("Not Yet Implemented");
	}

	@Override
	public void explode() {
		throw new RuntimeException("Not Yet Implemented");
	}

}
