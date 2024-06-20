package model;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Missile extends Entity {

	public Missile(Point2D position, Direction direction, Model model) {
		super(position, direction, model, "missile");
		this.attackDamage = model.m_controller.getConfig().getIntValue("missile", "attackDamage");
		this.healthPoint = model.m_controller.getConfig().getIntValue("missile", "healthPoint");
		this.meleeRange = model.m_controller.getConfig().getIntValue("missile", "meleeRange");
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

	@Override
	public void explode() {
		// TODO Auto-generated method stub
		
	}

}
