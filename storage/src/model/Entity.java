package model;

import java.awt.Point;
import automaton.Automaton;

public abstract class Entity {
	Point position;
	Direction direction;
	Category category;
	Model model;
	Automaton automaton;

	/**
	 * @param position
	 * @param direction
	 * @param model
	 */
	public Entity(Point position, Direction direction, Model model) {
		this.position = position;
		this.direction = direction;
		this.model = model;
		model.addEntity(this);
	}

	/**
	 * Retourne la coordonnee X de l'entite dans la simulation
	 * 
	 * @return Coordonnee X
	 */
	public int getX() {
		return position.x;
	}

	/**
	 * Retourne la coordonnee Y de l'entite dans la simulation
	 * 
	 * @return Coordonnee Y
	 */
	public int getY() {
		return position.y;

	}

	public Point getPosition() {
		return position;
	}

	/**
	 * Déplacement par défault d'une entité
	 * 
	 * @param direction
	 * @author Moataz ERRAMI
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

	/**
	 * Execute l'action Pick comme definit par l'entite
	 */
	public abstract void pick();

	/**
	 * Execute l'action Explode comme definit par l'entite
	 */
	public abstract void explode();

	/**
	 * Retourne True Si la case dans la direction donnée en paramètre a une entité
	 * de catégorie category, False sinon
	 * 
	 * @param direction
	 * @param category
	 * @return
	 * @author Moataz ERRAMI
	 */
	public boolean cell(Direction direction, Category category) {
		Point adjaPoint = this.whereTo(direction);
		adjaPoint.move(adjaPoint.x + getX(), adjaPoint.y + getY());
		Category entCategory = model.getCategoryAt(adjaPoint);
		if (entCategory == category)
			return true;
		return false;
	}

	/**
	 * @return la direction de l'entité
	 */
	public Direction getDirection() {
		return direction;
	}

	/**
	 * Set la direction de l'entité
	 * 
	 * @param direction
	 */
	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	/**
	 * Met a jour l'etat de l'entite comme definit par son automate
	 */
	public void step() {
		automaton.step();
	}

	/**
	 * transforme la direction en un "vecteur de translation"
	 * 
	 * @author Moataz ERRAMI
	 */
	public Point whereTo(Direction newDirect) {
		Point firstPosition = this.getPosition();

		int borderPointY = this.model.getBoardHeight();
		int borderPointX = this.model.getBoardWidth();

		Direction absoluteDirection = Direction.relativeToAbsolute(direction, newDirect);

		Point theMove = new Point(0, 0);
		if (absoluteDirection == Direction.N)
			theMove.move(0, -1);
		else if (absoluteDirection == Direction.S)
			theMove.move(0, 1);
		else if (absoluteDirection == Direction.E)
			theMove.move(1, 0);
		else if (absoluteDirection == Direction.W)
			theMove.move(-1, 0);
		else if (absoluteDirection == Direction.NE)
			theMove.move(1, -1);
		else if (absoluteDirection == Direction.NW)
			theMove.move(-1, -1);
		else if (absoluteDirection == Direction.SW)
			theMove.move(-1, 1);
		else if (absoluteDirection == Direction.SE)
			theMove.move(1, 1);
		return theMove;
	}
}
