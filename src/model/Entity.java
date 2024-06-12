package model;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import view.Avatar;

/**
 * Classe regroupant les méthodes communes et champs communs a toutes les
 * entités
 */
public abstract class Entity {

	protected Point2D position;
	protected Direction direction;
	protected Category category;
	protected Model model;

	protected Avatar avatar;

	/**
	 * Crée une entité
	 * 
	 * @param position  : position de l'entité dans la simulation
	 * @param direction : direction de l'ntité dans la simulation
	 * @param model     : instance courante du model
	 */
	public Entity(Point2D position, Direction direction, Model model) {
		this.position = position;
		this.direction = direction;
		this.model = model;
	}

	/**
	 * Atribue un avatar a l'entité
	 * 
	 * @param avatar : avatar atribué
	 */
	protected void setAvatar(Avatar avatar) {
		this.avatar = avatar;
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

	/**
	 * Retourne la position complète de l'entité
	 * 
	 * @return point ou se situe l'entité
	 */
	public Point2D getPosition() {
		return position;
	}
	
	public Point2D getCenter() {
		return new Point2D.Double(position.getX() + 25, position.getY() + 25);
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
	 * @return
	 */
	public boolean cell(Direction direction, Category category) {
		throw new RuntimeException("Not Yet Implemented");
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

	/**
	 * Retourne la hitbox de l'entité
	 * 
	 * @return hitbox sous forme de rectangle
	 */
	public abstract Rectangle2D getHitbox();

}
