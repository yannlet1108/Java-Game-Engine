package model;

import java.awt.Point;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import automaton.Automaton;

public class Snake extends Entity {
	List<Point> pointsList;

	public Snake(Point position, Direction direction, Model model) {
		super(position, direction, model);
		this.category = Category.SNAKE;
		pointsList = new LinkedList<Point>();
		automaton = new Automaton(this, Automaton.SNAKE);
	}

	/* retourne la liste des points de snake */
	public List<Point> getPointsList() {
		return pointsList;
	}

	/* retourne la longueur de snake */
	public int getLength() {
		return pointsList.size();
	}

	/* retourne la position de la tête de Snake */
	@Override
	public Point getPosition() {
		return this.pointsList.get(0).getLocation();
	}

	/*
	 * Prends comme paramètre une direction et move le snake dans cette direction.
	 * Author : Moataz ERRAMI
	 */
	@Override
	public void move(Direction newDirect) {
		Point lastPoint = pointsList.remove(this.getLength() - 1);
		model.setEntityAt(lastPoint, null);

		Point firstPoint = pointsList.get(0);

		this.setDirection(newDirect);

		Point theMove = this.whereTo(newDirect);
		if (theMove.x == 0 && theMove.y == 0)
			throw new RuntimeException("Something Went wrong, this should not be reached");

		lastPoint = new Point(firstPoint.x + theMove.x, firstPoint.y + firstPoint.y);
		pointsList.add(0, lastPoint);
		model.setEntityAt(lastPoint, this);

	}

	/*
	 * Detruit cette entité, supprimer de la map de model. Worst Author : Moataz
	 * ERRAMI
	 */
	public void explode() {
		this.model.removeEntity(this);
		Iterator<Point> iterator = pointsList.iterator();
		if (iterator.hasNext()) {
			Point currentPoint = iterator.next(); // On commence par le 2éme point car le 1er est déjà supprimer en
													// removeEntity(this)
			while (iterator.hasNext()) {
				currentPoint = iterator.next();
				model.setEntityAt(currentPoint, null);
			}
		}
	}

	public void egg() {
		throw new RuntimeException("Not Yet Implemented");
	}

	/*
	 * ajoute un point devant la tête de snake. Author : Moataz ERRAMI
	 */
	public void pick() {
		Direction thisDirection = this.getDirection();
		Point firstPoint = pointsList.get(0);
		Point theMove = this.whereTo(thisDirection);
		if (theMove.x == 0 && theMove.y == 0)
			throw new RuntimeException("Something Went wrong, this should not be reached");

		Point nuePoint = new Point(firstPoint.x + theMove.x, firstPoint.y + firstPoint.y);
		pointsList.add(nuePoint);
		model.setEntityAt(nuePoint, this);

	}

}
