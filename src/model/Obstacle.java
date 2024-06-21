package model;

import java.awt.geom.Point2D;

public class Obstacle extends Entity {

	public Obstacle(Point2D position, Direction direction, Model model) {
		super(position, direction, model, "obstacle");
		this.healthPoint = model.getConfig().getIntValue("obstacle", "healthPoint");
		// TODO Auto-generated constructor stub
	}

}
