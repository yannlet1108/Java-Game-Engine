package model;

import java.awt.geom.Point2D;

public class Player extends Entity {

	public Player(Point2D position, Direction direction, Model model, int healthPoint) {
		super(position, direction, model, healthPoint);
		category = Category.PLAYER;
		density = PlayerConstants.PLAYER_DENSITY;
		model.addPlayers(this);
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
