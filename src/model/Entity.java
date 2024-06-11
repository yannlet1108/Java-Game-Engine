package model;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Iterator;

public abstract class Entity {
	Point2D position;
	Direction direction;
	Category category;
	Model model;

	/**
	 * @param position
	 * @param direction
	 * @param model
	 */
	public Entity(Point2D position, Direction direction, Model model) {
		this.position = position;
		this.direction = direction;
		this.model = model;
	}

	/**
	 * Retourne la coordonnee X de l'entite dans la simulation
	 * 
	 * @return Coordonnee X
	 */
	public double getX() {
		return position.getX();
	}

	/**
	 * Retourne la coordonnee Y de l'entite dans la simulation
	 * 
	 * @return Coordonnee Y
	 */
	public double getY() {
		return position.getY();

	}

	public Point2D getPosition() {
		return position;
	}

	/**
	 * Déplacement par défault d'une entité
	 * 
	 * @param direction
	 */
	public void move(Direction direction) {
		throw new RuntimeException("Not Yet Implemented");
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
	 * @param rayon
	 * @return true or false
	 */
	public boolean cell(Direction direction, Category category, int rayon) {
		Entity entity;
		Point2D currentPos = this.position;
		double x = currentPos.getX();
		double y = currentPos.getY();
		Iterator<Entity> entityIter = this.model.entitiesIterator();

		while (entityIter.hasNext()) {
			entity = entityIter.next();
			if (entity.category == category) {
				if (direction == Direction.NE && entity.getX() > x && entity.getX() <= x + rayon && entity.getY() < y
						&& entity.getY() >= y - rayon)
					return true;
				if (direction == Direction.NW && entity.getX() < x && entity.getX() >= x - rayon && entity.getY() < y
						&& entity.getY() >= y - rayon)
					return true;
				if (direction == Direction.SW && entity.getX() < x && entity.getX() >= x - rayon && entity.getY() > y
						&& entity.getY() <= y + rayon)
					return true;
				if (direction == Direction.SE && entity.getX() > x && entity.getX() <= x + rayon && entity.getY() > y
						&& entity.getY() <= y + rayon)
					return true;
				if (direction == Direction.E && ((entity.getX() > x && entity.getX() <= x + rayon / 2
						&& absolute(entity.getY() - y) < entity.getX() - x)
						|| (entity.getX() <= x + rayon && entity.getX() >= x + rayon / 2
								&& absolute(entity.getY() - y) < x + rayon - entity.getX())))
					return true;
				if (direction == Direction.W && ((entity.getX() < x && entity.getX() >= x - rayon / 2
						&& absolute(entity.getY() - y) < x - entity.getX())
						|| (entity.getX() >= x - rayon && entity.getX() <= x - rayon / 2
								&& absolute(entity.getY() - y) < entity.getX() - (x - rayon))))
					return true;
				if (direction == Direction.N && ((entity.getY() < y && entity.getY() >= y - rayon / 2
						&& absolute(entity.getX() - x) < y - entity.getY())
						|| (entity.getY() >= y - rayon && entity.getY() <= y - rayon / 2
								&& absolute(entity.getX() - x) < entity.getY() - (y - rayon))))
					return true;
				if (direction == Direction.S && ((entity.getY() > y && entity.getY() <= y + rayon / 2
						&& absolute(entity.getX() - x) < entity.getY() - y)
						|| (entity.getY() <= y + rayon && entity.getY() >= y + rayon / 2
								&& absolute(entity.getX() - x) < y + rayon - entity.getY())))
					return true;
			}
		}
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
	}

	private double absolute(double abs) {
		if (abs >= 0)
			return abs;
		return -abs;

	}

}
