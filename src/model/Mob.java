package model;

import java.awt.geom.Point2D;

public class Mob extends Entity {

	public Mob(Point2D position, Direction direction, Model model, int number) {
		super(position, direction, model, "mob" + number);
		this.number = number;
		this.attackDamage = model.getConfig().getIntValue("mob" + this.number, "attackDamage");
		this.healthPoint = model.getConfig().getIntValue("mob" + this.number, "healthPoint");
		this.meleeRange = model.getConfig().getIntValue("mob" + this.number, "meleeRange");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void egg() {
		// TODO Auto-generated method stub

	}
}
