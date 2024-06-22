package model;

import java.awt.geom.Point2D;

import view.Avatar;
import view.PlayerAvatar;

public class Player extends Entity {

	private Vest vest;
	private double oxygen;

	public Player(Point2D position, Direction direction, Model model, String name) {
		super(position, direction, model, name);
		density = model.getConfig().getIntValue(name, "density");
		oxygen = 100;
		vest = new Vest();
		this.team = model.getConfig().getCategory(name, "category");
		new PlayerAvatar(model.m_view, this, 1);
		this.model.addPlayer(this);
	}

	private class Vest {
		public double densityStep;
		public double oxygenStep;
		public double maxDensity;
		public double minDensity;
		public double oxygenBreath;

		Vest() {
			densityStep = model.getConfig().getFloatValue(name, "stepDensity");
			oxygenBreath = model.getConfig().getFloatValue(name, "oxygenBreathe");
			maxDensity = model.getConfig().getFloatValue(name, "maxDensity");
			minDensity = model.getConfig().getFloatValue(name, "minDensity");
			oxygenStep = model.getConfig().getFloatValue(name, "oxygenStep");
		}

	}

	public double getOxygen() {
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
		if (density == vest.minDensity) {
			return;
		}
		if (oxygen > 0) {
			oxygen -= vest.oxygenStep;
			density -= vest.densityStep;
			if (density < vest.minDensity) {
				density = vest.minDensity;
			}
		} else {
			oxygen = 0;
		}
	}

	/**
	 * degonfler le gilet : augmente l'oxygen, augmente la densité, diminuer l'air
	 * dans le gilet
	 */
	private void deflate() {
			density += vest.densityStep;
			if (density > vest.maxDensity) {
				density = vest.maxDensity;
			}
	}
}
