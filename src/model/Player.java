package model;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import view.PlayerAvatar;

public class Player extends Entity {

	public Player(Point2D position, Direction direction, Model model, int healthPoint) {
		super(position, direction, model, healthPoint);
		category = Category.PLAYER;
		density = PlayerConstants.PLAYER_DENSITY;
		this.team = 1;
		model.addPlayers(this);
		model.m_view.store(new PlayerAvatar(model.m_view, this));
	}

	@Override
	public void egg() {
		Direction d = this.getDirection();
		Point2D pos = this.getCenter();
		pos.setLocation(this.getX()+5, this.getY());
		Missile m = new Missile(pos, d, this.getModel(), 1);
		this.getModel().addEntity(m);
	}

	@Override
	public void pick() {
		throw new RuntimeException("Not Yet Implemented");
	}

	@Override
	public void explode() {
		throw new RuntimeException("Not Yet Implemented");
	}

	@Override
	public Rectangle2D getHitbox() {
		throw new RuntimeException("Not Yet Implemented");

	}
}
