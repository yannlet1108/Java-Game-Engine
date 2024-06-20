package model;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Mob extends Entity {

	public Mob(Point2D position, Direction direction, Model model, int number) {
		super(position, direction, model, "mob" + number);
		this.number = number;
		this.attackDamage = model.m_controller.getConfig().getIntValue("mob" + this.number, "attackDamage");
		this.healthPoint = model.m_controller.getConfig().getIntValue("mob" + this.number, "healthPoint");
		this.meleeRange = model.m_controller.getConfig().getIntValue("mob" + this.number, "meleeRange");
		this.hitbox = new Rectangle2D.Double(position.getX(), position.getY(),
				model.m_controller.getConfig().getIntValue("mob" + this.number, "mobWidth"),
				model.m_controller.getConfig().getIntValue("mob" + this.number, "mobHeight"));
		// TODO Auto-generated constructor stub
	}

	@Override
	public void egg() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void explode() {
		// TODO Auto-generated method stub
		
	}

}
