package model;

import java.awt.Point;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @implNote TODO
 */
public class Snake extends Entity {
	List<Point> pointsList;

	/**
	 * @implNote TODO
	 * @param position
	 * @param direction
	 */
	public Snake(Point position, Direction direction) {
		super(position, direction);
		pointsList = new LinkedList<Point>();
	}

	/**
	 * @implNote TODO
	 * @return
	 */
	public Iterator<Point> getPointsIterator() {
		return pointsList.iterator();
	}

	/**
	 * @implNote TODO
	 * @return
	 */
	public int getLength() {
		return pointsList.size();
	}

	/**
	 * @implNote TODO
	 */
	public void move() {
		throw new RuntimeException("Not Yet Implemented");
	}

	/**
	 * @implNote TODO
	 */
	public void pick() {
		throw new RuntimeException("Not Yet Implemented");
	}
}
