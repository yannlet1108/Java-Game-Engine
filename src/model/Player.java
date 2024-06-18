package model;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import view.PlayerAvatar;

public class Player extends Entity {

	public Player(Point2D position, Direction direction, Model model) {
		super(position, direction, model, PlayerConstants.PLAYER_HEALTH_POINT);
		hitbox = new Rectangle2D.Double(position.getX(), position.getY(), PlayerConstants.PLAYER_WIDTH,
				PlayerConstants.PLAYER_HEIGHT);
		category = Category.PLAYER;
		density = PlayerConstants.PLAYER_DENSITY;
		this.team = PlayerConstants.PLAYER_TEAM;
		model.m_view.store(new PlayerAvatar(model.m_view, this));
		this.model.addPlayer(this);
		this.meleeRange = PlayerConstants.PLAYER_MELEE_RANGE;
		this.attackDamage = PlayerConstants.PLAYER_ATTACK_DAMAGE;
	}

	/**
	 * Methode qui créé un missile devant le joueur
	 */
	@Override
	public void egg() {
		Direction d = this.getDirection();
		Point2D pos = this.getCenter();
		pos.setLocation(this.getX()+5, this.getY());
		Missile m = new Missile(pos, d, this.getModel(), 1);
	}

	@Override
	public void pick() {
		throw new RuntimeException("Not Yet Implemented");
	}

	@Override
	public Rectangle2D getHitbox() {
		Rectangle2D hitbox = new Rectangle2D.Double(this.getX(), this.getY(), PlayerConstants.PLAYER_WIDTH,
				PlayerConstants.PLAYER_HEIGHT);
		return hitbox;
	}
	
	public void explode() {
		this.getModel().removeEntity(this);
		this.getModel().removePlayer(this);
	}
}
