package model;

import java.awt.Point;

/**
 * @implNote TODO
 * @implNote TODO Changer les methodes non implementes en methode abstraite -
 *           Changer la nature de la classe
 */
public class Entity {
	Point position;
	Direction direction;
	Model model;

	/**
	 * @implNote TODO
	 * @param position
	 * @param direction
	 */
	public Entity(Point position, Direction direction) {
		this.position = position;
		this.direction = direction;
	}

	/**
	 * Retourne la coordonnee X de l'entite dans la simulation
	 * 
	 * @return Coordonnee X
	 */
	public int getX() {
		throw new RuntimeException("Not Yet Implemented");
	}

	/**
	 * Retourne la coordonnee Y de l'entite dans la simulation
	 * 
	 * @return Coordonnee Y
	 */
	public int getY() {
		throw new RuntimeException("Not Yet Implemented");
	}

	/**
	 * @implNote TODO
	 * @param direction
	 */
	public void move(Direction direction) {
		throw new RuntimeException("Not Yet Implemented");
	}

	/**
	 * @implNote TODO
	 */
	public void egg() {
		throw new RuntimeException("Not Yet Implemented");
	}

	/**
	 * Execute l'action Pick comme definit par l'entite
	 */
	public void pick() {
		throw new RuntimeException("Not Yet Implemented");
	}

	/**
	 * Execute l'action Explode comme definit par l'entite
	 */
	public void explode() {
		throw new RuntimeException("Not Yet Implemented");
	}

	/**
	 * @implNote TODO
	 * @param direction
	 * @param category
	 * @return
	 */
	public boolean cell(Direction direction, Category category) {
		throw new RuntimeException("Not Yet Implemented");
	}

	/**
	 * @implNote TODO
	 * @return
	 */
	public Direction getDirection() {
		throw new RuntimeException("Not Yet Implemented");
	}

	/**
	 * @implNote TODO
	 * @param direction
	 */
	public void setDirection(Direction direction) {
		throw new RuntimeException("Not Yet Implemented");
	}

	/**
	 * Met a jour l'etat de l'entite comme definit par son automate
	 */
	public void step() {
		throw new RuntimeException("Not Yet Implemented");
	}
}
