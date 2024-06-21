package model;

import java.awt.geom.Point2D;

import view.PlayerAvatar;

public class Player extends Entity {

	public Vest vest;
	private int oxygen;

	public Player(Point2D position, Direction direction, Model model, String name) {
		super(position, direction, model, name);
		density = model.getConfig().getIntValue(name, "density");
		oxygen = 100;
		vest = new Vest();
		this.team = model.getConfig().getCategory(name, "category");
		model.m_view.store(new PlayerAvatar(model.m_view, this, 1));
		this.model.addPlayer(this);
	}

	public class Vest {
		public int vestAir; // va de 0 à 100
		public int densityStep;
		public int oxygenBreath;
		public int oxygenStep;

		Vest() {
			vestAir = model.getConfig().getIntValue(name, "vestMaxAir");
			densityStep =model.getConfig().getIntValue(name, "stepDensity");
			oxygenBreath = model.getConfig().getIntValue(name, "oxygenBreathe");
			oxygenStep = model.getConfig().getIntValue(name, "oxygenStep");
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
			oxygen -= vest.oxygenBreath;
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
		if (vest.getVestAir() + vest.oxygenStep < vest.getVestAir()) {
			if (oxygen > vest.oxygenStep) {
				oxygen -= vest.oxygenStep;
				vest.vestAir += vest.oxygenStep;
				this.density -=  vest.densityStep;
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
			if (oxygen < 100 - vest.oxygenStep) {
				oxygen += vest.oxygenStep;
				;
			}
			vest.vestAir -= vest.oxygenStep;
			;
			this.density += vest.densityStep;
			;
		}
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
