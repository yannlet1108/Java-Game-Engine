package model;

import java.awt.geom.Point2D;

public abstract class Entity {
	private Point2D position;
	private Direction direction;
	protected Category category;
	private Model model;

	protected double density;
	private Vector speed;
	private Vector force;
	private double mass;
	private double volume;

	/**
	 * @param position
	 * @param direction
	 * @param model
	 */
	public Entity(Point2D position, Direction direction, Model model) {
		this.position = position;
		this.direction = direction;
		this.model = model;
		force = new Vector();
		speed = new Vector();
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

	public void computeMovement() {
		Vector weight = computeWeight();
		Vector archimede = computeArchimedes();
		Vector friction = computeFriction();
		Vector forcesSum = archimede.add(weight.add(force.add(friction)));

		// Vector acceleration = forcesSum.scalarMultiplication(1 / mass);
		Vector acceleration = forcesSum;

		double timeSeconds = (double) ModelConstants.PHYSICS_STEP_DELAY.toMillis() / (double) 1000;
		speed = speed.add(acceleration.scalarMultiplication(timeSeconds));

		Vector movement = speed.scalarMultiplication(timeSeconds);
		position = (movement.add(position));
	}

	private Vector computeArchimedes() {
		// return new Vector(0, -density * volume * ModelConstants.GRAVITY);
		return new Vector(0, -(model.getDensity() - density));
	}

	private Vector computeWeight() {
		// return new Vector(0, mass * ModelConstants.GRAVITY);
		return new Vector(0, 0);
	}

	private Vector computeFriction() {
		Vector unitVector = speed.unitVector().scalarMultiplication(-1);
		double speedNorm = speed.norm();
		double vs2 = model.getViscosity() * (Math.pow(speedNorm, 2));
		return unitVector.scalarMultiplication(vs2);
	}

	public Vector getSpeed() {
		return speed;
	}
}
