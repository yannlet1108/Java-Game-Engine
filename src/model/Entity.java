package model;

import java.awt.Point;

import automaton.Automaton;

public abstract class Entity {
	Point position;
	Direction direction;
	Model model;
	Automaton automaton;

	public Entity(Point position, Direction direction) {
		this.position = position;
		this.direction = direction;
		model.addEntity(this);
	}

	public int getX() {
		return position.x;
	}

	public int getY() {
		return position.y;

	}

	public Point getPosition() {
		return position;
	}

	public void move(Direction direction) {
		throw new RuntimeException("Not Yet Implemented");
	}

	public abstract void egg();

	public abstract void pick();

	public abstract void explode();

	public boolean cell(Direction direction, Category category) {
		throw new RuntimeException("Not Yet Implemented");
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public void step() {
		throw new RuntimeException("Not Yet Implemented");
	}

	/*
	 * transforme la direction en un "vecteur de translation" (pas vraiment elle
	 * rend un point qui est utilisé comme vecteur. Author : Moataz ERRAMI
	 */
	public Point whereTo(Direction newDirect) {
		Point firstPosition = this.getPosition();

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
