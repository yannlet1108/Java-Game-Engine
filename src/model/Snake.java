package model;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

public class Snake extends Entity {
	List<Point> pointsList;

	public Snake(Point position, Direction direction, Model model) {
		super(position, direction, model);
		pointsList = new LinkedList<Point>();
	}

	public List<Point> getPointsList() {
		return pointsList;
	}

	public int getLength() {
		return pointsList.size();
	}

	public void move() {
		throw new RuntimeException("Not Yet Implemented");
	}

	public void pick() {
		throw new RuntimeException("Not Yet Implemented");
	}
}
