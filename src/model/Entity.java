package model;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;

import view.PlayerAvatar;

public abstract class Entity {
	private Rectangle2D hitbox;
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
		hitbox = new Rectangle2D.Double(position.getX(), position.getY(), PlayerConstants.PLAYER_WIDTH,
				PlayerConstants.PLAYER_HEIGHT);
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
		return hitbox.getX();
	}

	/**
	 * Retourne la coordonnee Y de l'entite dans la simulation
	 * 
	 * @return Coordonnee Y
	 */
	public double getY() {
		return hitbox.getY();

	}

	public Point2D getPosition() {
		return new Point2D.Double(hitbox.getCenterX(), hitbox.getCenterY());
	}

	public void setPosition(Point2D position) {
		hitbox.setRect(position.getX(), position.getY(), hitbox.getWidth(), hitbox.getHeight());
	}

	protected void setAvatar() {
		throw new RuntimeException("Not Yet Implemented");
	}

	public Rectangle2D getHitbox() {
		Rectangle2D hitbox = new Rectangle2D.Double();
		hitbox.setRect(this.hitbox);
		return hitbox;
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

		Point2D currentPos = getPosition();
		double x = currentPos.getX();
		double y = currentPos.getY();
		Iterator<Entity> entityIter = this.model.entitiesIterator();

		while (entityIter.hasNext()) {
			entity = entityIter.next();
			if (entity.category == category && entity != this) {
				// Cela test si un des points de rectangle est dans cette direction
				double GBx, GBy;
				for (int i = 0; i < 4; i++) {
					if (i == 0) {
						// Gauche bas point
						GBx = entity.getHitbox().getMinX();
						GBy = entity.getHitbox().getMaxY();
					} else if (i == 1) {
						// Gauche Haut point
						GBx = entity.getHitbox().getMinX();
						GBy = entity.getHitbox().getMinY();
					} else if (i == 2) {
						// Droit Haut point
						GBx = entity.getHitbox().getMaxX();
						GBy = entity.getHitbox().getMinY();
					} else {
						// Droit bas point
						GBx = entity.getHitbox().getMaxX();
						GBy = entity.getHitbox().getMaxY();
					}
					if (direction == Direction.NE && GBx > x && GBx <= x + rayon && GBy < y && GBy >= y - rayon)
						return true;
					if (direction == Direction.NW && GBx < x && GBx >= x - rayon && GBy < y && GBy >= y - rayon)
						return true;
					if (direction == Direction.SW && GBx < x && GBx >= x - rayon && GBy > y && GBy <= y + rayon)
						return true;
					if (direction == Direction.SE && GBx > x && GBx <= x + rayon && GBy > y && GBy <= y + rayon)
						return true;
					if (direction == Direction.E && ((GBx > x && GBx <= x + rayon / 2 && absolute(GBx - y) < GBx - x)
							|| (GBx <= x + rayon && GBx >= x + rayon / 2 && absolute(GBy - y) < x + rayon - GBx)))
						return true;
					if (direction == Direction.W && ((GBx < x && GBx >= x - rayon / 2 && absolute(GBy - y) < x - GBx)
							|| (GBx >= x - rayon && GBx <= x - rayon / 2 && absolute(GBy - y) < GBx - (x - rayon))))
						return true;
					if (direction == Direction.N && ((GBy < y && GBy >= y - rayon / 2 && absolute(GBx - x) < y - GBy)
							|| (GBy >= y - rayon && GBy <= y - rayon / 2 && absolute(GBx - x) < GBy - (y - rayon))))
						return true;
					if (direction == Direction.S && ((GBy > y && GBy <= y + rayon / 2 && absolute(GBx - x) < GBy - y)
							|| (GBy <= y + rayon && GBy >= y + rayon / 2 && absolute(GBx - x) < y + rayon - GBy)))
						return true;
				}
				// Cela test si le rectangle ou le losange de la direction a un point dans le
				// hitbox d'entité
				Rectangle2D hitBox = entity.getHitbox();
				Point2D point1, point2, point3;
				if (direction == Direction.SE) {
					point1 = new Point2D.Double(x + rayon, y);
					point2 = new Point2D.Double(x, y + rayon);
					point3 = new Point2D.Double(x + rayon, y + rayon);
					if (hitBox.contains(point1) || hitBox.contains(point2) || hitBox.contains(point3))
						return true;
				}
				if (direction == Direction.SW) {
					point1 = new Point2D.Double(x - rayon, y);
					point2 = new Point2D.Double(x, y + rayon);
					point3 = new Point2D.Double(x - rayon, y + rayon);
					if (hitBox.contains(point1) || hitBox.contains(point2) || hitBox.contains(point3))
						return true;
				}
				if (direction == Direction.NW) {
					point1 = new Point2D.Double(x - rayon, y);
					point2 = new Point2D.Double(x, y - rayon);
					point3 = new Point2D.Double(x - rayon, y - rayon);
					if (hitBox.contains(point1) || hitBox.contains(point2) || hitBox.contains(point3))
						return true;
				}
				if (direction == Direction.NE) {
					point1 = new Point2D.Double(x + rayon, y);
					point2 = new Point2D.Double(x, y - rayon);
					point3 = new Point2D.Double(x + rayon, y - rayon);
					if (hitBox.contains(point1) || hitBox.contains(point2) || hitBox.contains(point3))
						return true;
				}
				if (direction == Direction.E) {
					point1 = new Point2D.Double(x + rayon, y);
					point2 = new Point2D.Double(x + rayon / 2, y + rayon / 2);
					point3 = new Point2D.Double(x + rayon / 2, y - rayon / 2);
					if (hitBox.contains(point1) || hitBox.contains(point2) || hitBox.contains(point3))
						return true;
				}
				if (direction == Direction.W) {
					point1 = new Point2D.Double(x - rayon, y);
					point2 = new Point2D.Double(x - rayon / 2, y + rayon / 2);
					point3 = new Point2D.Double(x - rayon / 2, y - rayon / 2);
					if (hitBox.contains(point1) || hitBox.contains(point2) || hitBox.contains(point3))
						return true;
				}
				if (direction == Direction.N) {
					point1 = new Point2D.Double(x, y - rayon);
					point2 = new Point2D.Double(x + rayon / 2, y - rayon / 2);
					point3 = new Point2D.Double(x - rayon / 2, y - rayon / 2);
					if (hitBox.contains(point1) || hitBox.contains(point2) || hitBox.contains(point3))
						return true;
				}
				if (direction == Direction.S) {
					point1 = new Point2D.Double(x, y + rayon);
					point2 = new Point2D.Double(x + rayon / 2, y + rayon / 2);
					point3 = new Point2D.Double(x - rayon / 2, y + rayon / 2);
					if (hitBox.contains(point1) || hitBox.contains(point2) || hitBox.contains(point3))
						return true;
				}
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
		setPosition(movement.add(getPosition()));
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
