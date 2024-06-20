package model;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Missile extends Entity {

	public Missile(Point2D position, Direction direction, Model model) {
		super(position, direction, model);
		this.attackDamage = model.m_controller.getConfig().getIntValue("missile", "attackDamage");
		this.healthPoint = model.m_controller.getConfig().getIntValue("missile", "healthPoint");
		this.meleeRange = model.m_controller.getConfig().getIntValue("missile", "meleeRange");
		this.hitbox = new Rectangle2D.Double(position.getX(), position.getY(),
				model.m_controller.getConfig().getIntValue("missile", "width"),
				model.m_controller.getConfig().getIntValue("missile", "height"));
		// TODO Auto-generated constructor stub
	}

	@Override
	public void egg() {
		// TODO Auto-generated method stub
		throw new RuntimeException("Not Yet Implemented");
	}

	@Override
	public void pick() {
		// TODO Auto-generated method stub
		throw new RuntimeException("Not Yet Implemented");
	}

}
