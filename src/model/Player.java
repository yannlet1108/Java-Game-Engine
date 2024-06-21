package model;

import java.awt.geom.Point2D;

import view.PlayerAvatar;

public class Player extends Entity {

	public Vest vest;
	private int oxygen;

	public Player(Point2D position, Direction direction, Model model, int number) {
		super(position, direction, model, "Player" + number);
		density = model.getConfig().getIntValue("Player" + number, "density");
		oxygen = 100;
		vest = new Vest();
		this.number = number;
		this.team = model.getConfig().getCategory("Player" + number, "category");
		model.m_view.store(new PlayerAvatar(model.m_view, this, 1));
		this.model.addPlayer(this);
		this.attackDamage = model.getConfig().getIntValue("Player" + number, "attackDamage");
		this.healthPoint = model.getConfig().getIntValue("Player" + number, "healthPoint");
		this.meleeRange = model.getConfig().getIntValue("Player" + number, "meleeRange");
	}

	public class Vest {
		public int vestAir; // va de 0 à 100

		Vest() {
			vestAir = model.getConfig().getIntValue("Player" + number, "vestMaxAir");
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
			oxygen -= model.getConfig().getIntValue("Player" + number, "oxygenBreathe");
			;
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
		if (vest.getVestAir() + model.getConfig().getIntValue("Player" + number, "oxygenStep") < model.getConfig()
				.getIntValue("Player" + number, "vestMaxAir")) {
			if (oxygen > model.getConfig().getIntValue("Player" + number, "oxygenStep")) {
				oxygen -= model.getConfig().getIntValue("Player" + number, "oxygenStep");
				vest.vestAir += model.getConfig().getIntValue("Player" + number, "oxygenStep");
				this.density -= model.getConfig().getIntValue("Player" + number, "densityStep");
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
			if (oxygen < 100 - model.getConfig().getIntValue("Player" + number, "oxygenStep")) {
				oxygen += model.getConfig().getIntValue("Player" + number, "oxygenStep");
			}
			vest.vestAir -= model.getConfig().getIntValue("Player" + number, "oxygenStep");
			this.density += model.getConfig().getIntValue("Player" + number, "stepDensity");
		}
	}
}
