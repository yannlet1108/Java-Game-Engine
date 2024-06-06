package model;

import java.awt.Point;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import automaton.Automaton;

public class Snake extends Entity {
	List<Point> pointsList;

	public Snake(Point position, Direction direction) {
		super(position, direction);
		pointsList = new LinkedList<Point>();
		Point firstPoint = new Point(position);
		pointsList.add(firstPoint);
		model.setEntityAt(firstPoint, this);
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

	/*
	 * Prends comme paramètre une direction et move le snake dans cette direction.
	 * Author : Moataz ERRAMI
	 */
	@Override
	public void move(Direction newDirect) {
		Point lastPoint = pointsList.remove(this.getLength());
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
		while (iterator.hasNext()) {
			Point currentPoint = iterator.next();
			model.setEntityAt(currentPoint, null);
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

	/*
	 * transforme la direction en un "vecteur de translation" (pas vraiment elle
	 * rend un point qui est utilisé comme vecteur) Cette fonction est privée !
	 * Author : Moataz ERRAMI
	 */
	private Point whereTo(Direction newDirect) {
		Point firstPosition = pointsList.get(0).getLocation();

		int borderPointY = this.model.getBoardHeight();
		int borderPointX = this.model.getBoardWidth();

		Point theMove = new Point(0, 0);
		if (newDirect == Direction.N && firstPosition.y > 0)
			theMove.move(0, -1);
		else if (newDirect == Direction.S && firstPosition.y < borderPointY)
			theMove.move(0, 1);
		else if (newDirect == Direction.E && firstPosition.x < borderPointX)
			theMove.move(1, 0);
		else if (newDirect == Direction.W && firstPosition.x > 0)
			theMove.move(-1, 0);
		else if (newDirect == Direction.NE && firstPosition.y > 0 && firstPosition.x < borderPointX)
			theMove.move(1, -1);
		else if (newDirect == Direction.NW && firstPosition.y > 0 && firstPosition.x > 0)
			theMove.move(-1, -1);
		else if (newDirect == Direction.SW && firstPosition.y < borderPointY && firstPosition.x > 0)
			theMove.move(-1, 1);
		else if (newDirect == Direction.SE && firstPosition.y < borderPointY && firstPosition.x < borderPointX)
			theMove.move(1, 1);
		else
			throw new RuntimeException("Direction Unknown or border reached");

		return theMove;
	}
}
