package model;

import java.awt.geom.Point2D;

import view.Avatar;

public class Player extends Entity {

	private Vest vest;
	private double oxygen;
	private double maxOxygen;

	public Player(Point2D position, Direction direction, Model model, String name) {
		super(position, direction, model, name);
		density = model.getConfig().getIntValue(name, "density");
		maxOxygen = model.getConfig().getFloatValue(name, "maxOxygen");
		oxygen = maxOxygen;
		vest = new Vest();
		this.team = model.getConfig().getCategory(name, "category");
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

	public void setOxygen(double oxygen) {
		this.oxygen = oxygen;
	}
	/**
	 * Diminue l'oxygen dans le gilet chaque step
	 */
	public void breathe() {
		if (oxygen > 0) {
			oxygen -= vest.oxygenBreath;
		} else {
			oxygen = 0;
			this.getHit(15);
		}
		if (this.hitbox.intersects(model.getShipArea())) {
			setOxygen(maxOxygen);
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

	public Vest getVest() {
		return vest;
	}
}
