package model;

import java.awt.geom.Point2D;

public class Obstacle extends Entity {

	Obstacle(Point2D position, Direction direction, Model model) {
		super(position, direction, model, "Obstacle");
	}
}
