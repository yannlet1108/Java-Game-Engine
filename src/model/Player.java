package model;

import java.awt.geom.Point2D;

import view.PlayerAvatar;

public class Player extends Entity {

	public Player(Point2D position, Direction direction, Model model) {
		super(position, direction, model, PlayerConstants.PLAYER_HEALTH);
		category = Category.PLAYER;
		density = PlayerConstants.PLAYER_DENSITY;
		model.addPlayer(this);
		model.m_view.store(new PlayerAvatar(model.m_view, this));
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
		this.getModel().removeEntity(this);
		this.getModel().removePlayer(this);
	}
}
