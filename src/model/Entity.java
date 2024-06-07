package model;

import java.awt.Point;

import automaton.Automaton;

public abstract class Entity {
	Point position;
	Direction direction;
	Category category;
	Model model;
	Automaton automaton;

	/*
	 * TO DO : initialisation de la catégorie dans le tableau
	 */
	public Entity(Point position, Direction direction, Model model) {
		this.model = model;
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

	/*
	 * Prends comme paramètre une direction et move l'entité dans cette direction.
	 * La classe Snake a sa propre implémentation! Author : Moataz ERRAMI
	 */
	public void move(Direction direction) {
		Point toMovePoint = this.whereTo(direction);
		this.model.setEntityAt(position, null);
		this.position.move(toMovePoint.x + getX(), toMovePoint.y + getY());
		this.model.setEntityAt(position, this);
	}
	
	public void move() {
		move(Direction.FORWARD);
	}

	public abstract void egg();

	public abstract void pick();

	public abstract void explode();

	/*
	 * Retourne True Si la case dans la direction donnée en paramètre a une entité
	 * de catégorie category, False sinon. Author : Moataz
	 */
	public boolean cell(Direction direction, Category category) {
		Point adjaPoint = this.whereTo(direction);
		adjaPoint.move(adjaPoint.x + getX(), adjaPoint.y + getY());
		Category entCategory = model.getCategoryAt(adjaPoint);
		if (entCategory == category)
			return true;
		return false;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public void step() {
		automaton.step();
	}

	/*
	 * transforme la direction en un "vecteur de translation" (pas vraiment elle
	 * rend un point qui est utilisé comme vecteur. Author : Moataz ERRAMI
	 */
	public Point whereTo(Direction newDirect) {
		Point firstPosition = this.getPosition();

		int borderPointY = this.model.getBoardHeight();
		int borderPointX = this.model.getBoardWidth();

		Direction absoluteDirection = Direction.relativeToAbsolute(direction, newDirect);

		Point theMove = new Point(0, 0);
		if (absoluteDirection == Direction.N && firstPosition.y > 0)
			theMove.move(0, -1);
		else if (absoluteDirection == Direction.S && firstPosition.y < borderPointY)
			theMove.move(0, 1);
		else if (absoluteDirection == Direction.E && firstPosition.x < borderPointX)
			theMove.move(1, 0);
		else if (absoluteDirection == Direction.W && firstPosition.x > 0)
			theMove.move(-1, 0);
		else if (absoluteDirection == Direction.NE && firstPosition.y > 0 && firstPosition.x < borderPointX)
			theMove.move(1, -1);
		else if (absoluteDirection == Direction.NW && firstPosition.y > 0 && firstPosition.x > 0)
			theMove.move(-1, -1);
		else if (absoluteDirection == Direction.SW && firstPosition.y < borderPointY && firstPosition.x > 0)
			theMove.move(-1, 1);
		else if (absoluteDirection == Direction.SE && firstPosition.y < borderPointY && firstPosition.x < borderPointX)
			theMove.move(1, 1);
		return theMove;
	}
}
