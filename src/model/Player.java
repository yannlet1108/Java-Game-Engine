package model;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import view.PlayerAvatar;

public class Player extends Entity {

	public Player(Point2D position, Direction direction, Model model) {
		super(position, direction, model);
		setAvatar(new PlayerAvatar(model.getView(), this));
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

	/**
	 * Fonction temporaire pour tester la view
	 * 
	 * @return coordonnées d'une hitbox en hard
	 */
	public Rectangle2D getHitbox() {
		return new Rectangle2D.Double(position.getX(), position.getY(), 50, 50);
	}

}
