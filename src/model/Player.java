package model;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import view.PlayerAvatar;

public class Player extends Entity {

	public Vest vest;
	private int oxygen;

	public Player(Point2D position, Direction direction, Model model) {
		super(position, direction, model, "player" + number);
		hitbox = new Rectangle2D.Double(position.getX(), position.getY(), PlayerConstants.PLAYER_WIDTH,
				PlayerConstants.PLAYER_HEIGHT);
		category = Category.PLAYER;
		density = PlayerConstants.PLAYER_DENSITY;
		oxygen = 100;
		vest = new Vest();
		this.team = PlayerConstants.PLAYER_TEAM;
		model.m_view.store(new PlayerAvatar(model.m_view, this, 1));
		this.model.addPlayer(this);
		this.meleeRange = PlayerConstants.PLAYER_MELEE_RANGE;
		this.attackDamage = PlayerConstants.PLAYER_ATTACK_DAMAGE;
		this.healthPoint = PlayerConstants.PLAYER_HEALTH_POINT;
	}

	public class Vest {
		public int vestAir; // va de 0 à 100

		Vest() {
			vestAir = PlayerConstants.VEST_MAX_AIR;
		}

		public int getVestAir() {
			return vestAir;
		}
	}

	@Override
	public void step() {
		this.breathe();
	}

	public int getOxygen() {
		return oxygen;
	}

	/**
	 * Diminue l'oxygen dans le gilet chaque step
	 */
	public void breathe() {
		if (oxygen > 0) {
			oxygen -= PlayerConstants.OXYGEN_BREATHE;
		} else {
			oxygen = 0;
			this.getHit(15);
		}
	}

	/**
	 * Si i = 1 il gonfle le gilet par un pas, si i = -1 il le dégonfle Sinon il
	 * rends une exception
	 * 
	 * @param i
	 */
	public void pop(int i) {
		if (i == 1) {
			this.swell();
		} else if (i == -1) {
			this.deflate();
		} else {
			throw new RuntimeException("Argument should be either 1 or -1");
		}
	}

	/**
	 * gonfler le gilet : diminue l'oxygen, diminue la densité, augmenter l'air dans
	 * le gilet
	 */
	private void swell() {
		if (vest.getVestAir() + PlayerConstants.VEST_STEP_AIR < PlayerConstants.VEST_MAX_AIR) {
			if (oxygen > PlayerConstants.OXYGEN_STEP) {
				oxygen -= PlayerConstants.OXYGEN_STEP;
				vest.vestAir += PlayerConstants.VEST_STEP_AIR;
				this.density -= PlayerConstants.DENSITY_STEP;
			} else {
				oxygen = 0;
				this.getHit(15);
			}
		}
	}

	/**
	 * degonfler le gilet : augmente l'oxygen, augmente la densité, diminuer l'air
	 * dans le gilet
	 */
	private void deflate() {
		if (vest.getVestAir() > 0) {
			if (oxygen < 100 - PlayerConstants.OXYGEN_STEP) {
				oxygen += PlayerConstants.OXYGEN_STEP;
			}
			vest.vestAir -= PlayerConstants.VEST_STEP_AIR;
			this.density += PlayerConstants.DENSITY_STEP;
		}
	}

	/**
	 * Methode qui créé un missile devant le joueur
	 */

	public void throwMissile() {
		Direction d = this.getDirection();
		Point2D pos = this.getCenter();
		pos.setLocation(this.getX() + 5, this.getY());
		Missile m = new Missile(pos, d, this.getModel());
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

	@Override
	public void egg() {
		// TODO Auto-generated method stub

	}
}
