package model;

import java.awt.geom.Point2D;
import java.util.Iterator;

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

	private int healthPoint;

	/**
	 * @param position
	 * @param direction
	 * @param model
	 */
	public Entity(Point2D position, Direction direction, Model model, int healthPoint) {
		this.position = position;
		this.direction = direction;
		this.model = model;
		this.model.addEntity(this);
		this.healthPoint = healthPoint;
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
	
	protected void setAvatar() {
		throw new RuntimeException("Not Yet Implemented");
	}
	
	abstract void getHitbox();

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
		Point2D currentPos = this.getPosition();
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

	private double absolute(double abs) {
		if (abs >= 0)
			return abs;
		return -abs;

	}

	public int getHealthPoint() {
		return healthPoint;
	}

	public void setHealthPoint(int healthPoint) {
		this.healthPoint = healthPoint;
	}

	public void modifyHealthPoint(int val) {
		this.healthPoint += val;
	}
}
