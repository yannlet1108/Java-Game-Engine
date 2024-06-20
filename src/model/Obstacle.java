package model;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Obstacle extends Entity {

	public Obstacle(Point2D position, Direction direction, Model model) {
		super(position, direction, model);
		this.hitbox = new Rectangle2D.Double(position.getX(), position.getY(),
				model.m_controller.getConfig().getIntValue("obstacle", "width"),
				model.m_controller.getConfig().getIntValue("obstacle", "height"));
		this.healthPoint = model.m_controller.getConfig().getIntValue("obstacle", "healthPoint");
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

}
