package model;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.LinkedList;
import java.util.List;

import automaton.FSM;
import view.Avatar;

public abstract class Entity {
	protected Rectangle2D hitbox;
	private Direction direction;
	protected Category category;
	protected Model model;

	protected double density;
	private Vector speed;

	private Vector force;
	private boolean isPhysicObject;

	protected int healthPoint;
	protected Category team;
	protected int meleeRange;
	protected int attackDamage;
	protected int number;
	protected String throwEntity;

	protected State state;

	protected String name;

	private FSM myFSM;
	private Direction lastDirectionRequested;

	private boolean automatonAvailable = true;
	private double range;
	private double explodeRange;

	private double moveForce;
	Timer timer;
	TimerTask currenTask;

	int moveDuration;
	int waitDuration;
	int hitDuration;
	int eggDuration;
	int popDuration;
	int explodeDuration;
	int throwDuration;
	private int maxHealthPoint;

	/**
	 * @param position
	 * @param direction
	 * @param model
	 */

	public Entity(Point2D position, Direction direction, Model model, String name) {
		config.Config cfg = model.getConfig();
		hitbox = new Rectangle2D.Double(position.getX(), position.getY(), cfg.getFloatValue(name, "width"),
				cfg.getFloatValue(name, "height"));
		this.direction = direction;
		this.model = model;
		this.model.toAdd.add(this);
		force = new Vector();
		speed = new Vector();
		this.moveForce = cfg.getFloatValue(name, "speed");
		this.category = cfg.getCategory(name, "category");
		this.name = name;
		this.attackDamage = cfg.getIntValue(name, "attackDamage");
		this.maxHealthPoint = cfg.getIntValue(name, "maxHealthPoint");
		this.healthPoint = maxHealthPoint;
		this.meleeRange = cfg.getIntValue(name, "meleeRange");
		this.explodeRange = meleeRange;
		this.throwEntity = cfg.getStringValue(name, "throwBots");
		this.isPhysicObject = cfg.getBooleanValue(name, "isPhysicObject");
		this.range = cfg.getFloatValue(name, "width") * 1;
		this.moveDuration = cfg.getIntValue(name, "moveDuration");
		this.waitDuration = cfg.getIntValue(name, "waitDuration");
		this.hitDuration = cfg.getIntValue(name, "hitDuration");
		this.eggDuration = cfg.getIntValue(name, "eggDuration");
		this.popDuration = cfg.getIntValue(name, "popDuration");
		this.explodeDuration = cfg.getIntValue(name, "explodeDuration");
		this.throwDuration = cfg.getIntValue(name, "throwDuration");

		this.density = model.getDensity();

		new Avatar(model.m_view, this, name);
		timer = new Timer();

		myFSM = new FSM(this,
				model.getAutomatonBank().getAutomaton(model.getConfig().getStringValue(this.name, "automaton")));

		this.state = State.WAITING;
	}

	@Override
	public String toString() {
		return name + " dans l'état " + state;
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

	public Vector getForce() {
		return force;
	}

	public void setSpeed(Vector speed) {
		this.speed = speed;
	}

	public double getHeight() {
		return hitbox.getHeight();
	}

	public double getWidth() {
		return hitbox.getWidth();
	}

	public Point2D getCenter() {
		return new Point2D.Double(hitbox.getCenterX(), hitbox.getCenterY());
	}

	public void setPosition(Point2D position) {
		hitbox.setRect(position.getX(), position.getY(), hitbox.getWidth(), hitbox.getHeight());
	}

	public void translatePosition(Vector v) {
		hitbox.setRect(hitbox.getX() + v.getX(), hitbox.getY() + v.getY(), hitbox.getWidth(), hitbox.getHeight());
	}

	public Model getModel() {
		return this.model;
	}

	protected void setAvatar() {
		throw new RuntimeException("Not Yet Implemented");
	}

	public Rectangle2D getHitbox() {
		return new Rectangle2D.Double(hitbox.getX(), hitbox.getY(), hitbox.getWidth(), hitbox.getHeight());
	}

	public double getDensity() {
		return density;
	}

	public void doMove(Direction direction) {
		blockAutomaton();
		state = State.MOVING;
		if (direction == null) {
			move();
		} else {
			move(getRightDirection(direction));
		}
		ActionTask endMoveTask = new EndMoveTask(this, moveDuration);
		currenTask = endMoveTask;
		timer.schedule(endMoveTask, endMoveTask.getDuration());
	}

	/**
	 * Déplacement par défault d'une entité
	 * 
	 * @param direction
	 */
	public void move(Direction direction) {
		setForce(Vector.getVectorUnitVectorFromDirection(Direction.relativeToAbsolute(this.direction, direction))
				.scalarMultiplication(moveForce));
	}

	public void move() {
		move(Direction.FORWARD);
	}

	public void doWait() {
		blockAutomaton();
		setState(State.WAITING);
		ActionTask endWaitTask = new EndWaitTask(this, waitDuration);
		currenTask = endWaitTask;
		timer.schedule(endWaitTask, endWaitTask.getDuration());
	}

	private void egg() {
		egg(Direction.BACKWARD);
	}

	public void egg(Direction direct) {
		Point2D pts = null;
		int marge = 3;
		config.Config cfg = model.getConfig();
		Direction direction = Direction.relativeToAbsolute(this.direction, direct);
		if (direction == Direction.N)
			pts = new Point2D.Double(this.getX(), this.getY() - cfg.getIntValue(this.name, "height") - marge);
		if (direction == Direction.S)
			pts = new Point2D.Double(this.getX(), this.getY() + cfg.getIntValue(this.name, "height") + marge);
		if (direction == Direction.E)
			pts = new Point2D.Double(this.getX() + cfg.getIntValue(this.name, "width") + marge, this.getY());
		if (direction == Direction.W)
			pts = new Point2D.Double(this.getX() - cfg.getIntValue(this.name, "width") - marge, this.getY());
		if (direction == Direction.SE)
			pts = new Point2D.Double(this.getX() + cfg.getIntValue(this.name, "width") + marge,
					this.getY() + cfg.getIntValue(this.name, "height") + marge);
		if (direction == Direction.SW)
			pts = new Point2D.Double(this.getX() - cfg.getIntValue(this.name, "width") - marge,
					this.getY() + cfg.getIntValue(this.name, "height") + marge);
		if (direction == Direction.NW)
			pts = new Point2D.Double(this.getX() - cfg.getIntValue(this.name, "width") - marge,
					this.getY() - cfg.getIntValue(this.name, "height") - marge);
		if (direction == Direction.NE)
			pts = new Point2D.Double(this.getX() + cfg.getIntValue(this.name, "width") + marge,
					this.getY() - cfg.getIntValue(this.name, "height") - marge);

		Rectangle2D futurHitBox = new Rectangle2D.Double(pts.getX(), pts.getY(), cfg.getFloatValue(name, "width"),
				cfg.getFloatValue(name, "height"));
		Iterator<Entity> iter = this.model.entitiesIterator();
		Entity e;
		while (iter.hasNext()) {
			e = iter.next();
			if (e.hitbox.intersects(futurHitBox))
				return;
		}
		new Mob(pts, this.direction, this.model, name);

	}

	public void doEgg(Direction direction) {
		blockAutomaton();
		if (direction == null) {
			egg();
		} else {
			egg(getRightDirection(direction));
		}
		ActionTask endEggTask = new EndEggTask(this, eggDuration);
		currenTask = endEggTask;
		timer.schedule(endEggTask, endEggTask.getDuration());
	}

	public void doPop(int val) {
		blockAutomaton();
		setState(State.FILLING);
		pop(val);
		ActionTask endPopTask = new EndPopTask(this, popDuration);
		currenTask = endPopTask;
		timer.schedule(endPopTask, endPopTask.getDuration());
	}

	private void pop(int val) {
		((Player) this).pop(val);
	}

	public void doExplode() {
		blockAutomaton();
		setState(State.DYING);
		explode();
		ActionTask endExplodeTask = new EndExplodeTask(this, explodeDuration);
		currenTask = endExplodeTask;
		timer.schedule(endExplodeTask, endExplodeTask.getDuration());
	}

	/**
	 * Supprime l'entite
	 */
	public void explode() {
		Rectangle2D hitRange = new Rectangle2D.Double(this.hitbox.getX() - explodeRange,
				this.hitbox.getY() - explodeRange, this.hitbox.getWidth() + 2 * explodeRange,
				this.hitbox.getHeight() + 2 * explodeRange);
		Iterator<Entity> it = this.model.entitiesIterator();
		while (it.hasNext()) {
			Entity e = it.next();
			if (e.getHitbox().intersects(hitRange)) {
				e.getHit(this);
			}
		}
	}

	public boolean doCell(Direction direction, Category category) {
		// Catégorie par défaut
		Category cat = category;
		if (category == null) {
			cat = Category.VOID;
		}

		// Direction par défaut
		Direction dir = direction;
		if (direction == null) {
			dir = Direction.FORWARD;
		} // Cas particulier de la direction d
		else if (direction == Direction.d) {
			dir = Direction.N; // Première direction qu'on vérifie
			boolean isDirectionFound = cell(dir, cat, range);
			boolean isTourCompleted = false;

			while (!isDirectionFound && !isTourCompleted) {
				dir = Direction.rotateSlightlyRight(dir);
				isDirectionFound = cell(dir, cat, range);

				if (dir == Direction.NW) {
					isTourCompleted = true;
				}
			}
			if (isDirectionFound) {
				lastDirectionRequested = dir;
				return true;
			}
		}

		return cell(dir, cat, range);
	}

	/**
	 * Retourne True Si la case dans la direction donnée en paramètre a une entité
	 * de catégorie category, False sinon
	 * 
	 * @param direction
	 * @param category
	 * @param rayon
	 * @return true or false
	 */
	public boolean cell(Direction direction, Category category, Double rayon) {

		Point2D currentPos = getCenter();
		double x = currentPos.getX();
		double y = currentPos.getY();
		Direction absoluteDirection = Direction.relativeToAbsolute(getDirection(), direction);

		if (category == Category.VOID) {
			for (Iterator<Entity> iterator = model.entitiesIterator(); iterator.hasNext();) {
				Entity entity = (Entity) iterator.next();
				if (!(this == entity)) {
					if (isEntityInZone(rayon, x, y, absoluteDirection, entity.getHitbox())) {
						return false;
					}
				}
				if (isWorldBorderInZone(rayon, x, y, absoluteDirection)) {
					return false;
				}
			}
			return true;
		}

		List<Entity> entitiesOfCategory = getEntitiesOfCategory(category);
		entitiesOfCategory.remove(this);

		for (Entity entity : entitiesOfCategory) {
			if (isEntityInZone(rayon, x, y, absoluteDirection, entity.getHitbox()))
				return true;
		}
		if (category == Category.OBSTACLE) {
			if (isWorldBorderInZone(rayon, x, y, absoluteDirection)) {
				return true;
			}
		}
		return false;
	}

	private boolean isWorldBorderInZone(Double rayon, double x, double y, Direction direction) {
		boolean isLeftBorderInZone = isEntityInZone(rayon, x, y, direction,
				new Rectangle2D.Double(-200, 0, 200, model.getBoardHeight()));
		boolean isRightBorderInZone = isEntityInZone(rayon, x, y, direction,
				new Rectangle2D.Double(model.getBoardWidth(), 0, 200, model.getBoardHeight()));
		boolean isTopBorderInZone = isEntityInZone(rayon, x, y, direction,
				new Rectangle2D.Double(0, -200, model.getBoardWidth(), 200));
		boolean isBottomBorderInZone = isEntityInZone(rayon, x, y, direction,
				new Rectangle2D.Double(0, model.getBoardHeight(), model.getBoardWidth(), 200));
		return isLeftBorderInZone || isRightBorderInZone || isTopBorderInZone || isBottomBorderInZone;
	}

	private boolean isEntityInZone(double rayon, double x, double y, Direction absoluteDirection, Rectangle2D hitbox) {
		// Cela test si un des points de rectangle est dans cette direction
		double GBx, GBy;
		for (int i = 0; i < 4; i++) {
			if (i == 0) {
				// Gauche bas point
				GBx = hitbox.getMinX();
				GBy = hitbox.getMaxY();
			} else if (i == 1) {
				// Gauche Haut point
				GBx = hitbox.getMinX();
				GBy = hitbox.getMinY();
			} else if (i == 2) {
				// Droit Haut point
				GBx = hitbox.getMaxX();
				GBy = hitbox.getMinY();
			} else {
				// Droit bas point
				GBx = hitbox.getMaxX();
				GBy = hitbox.getMaxY();
			}
			if (absoluteDirection == Direction.NE && GBx > x && GBx <= x + rayon && GBy < y && GBy >= y - rayon)
				return true;
			if (absoluteDirection == Direction.NW && GBx < x && GBx >= x - rayon && GBy < y && GBy >= y - rayon)
				return true;
			if (absoluteDirection == Direction.SW && GBx < x && GBx >= x - rayon && GBy > y && GBy <= y + rayon)
				return true;
			if (absoluteDirection == Direction.SE && GBx > x && GBx <= x + rayon && GBy > y && GBy <= y + rayon)
				return true;
			if (absoluteDirection == Direction.E && ((GBx > x && GBx <= x + rayon / 2 && absolute(GBx - y) < GBx - x)
					|| (GBx <= x + rayon && GBx >= x + rayon / 2 && absolute(GBy - y) < x + rayon - GBx)))
				return true;
			if (absoluteDirection == Direction.W && ((GBx < x && GBx >= x - rayon / 2 && absolute(GBy - y) < x - GBx)
					|| (GBx >= x - rayon && GBx <= x - rayon / 2 && absolute(GBy - y) < GBx - (x - rayon))))
				return true;
			if (absoluteDirection == Direction.N && ((GBy < y && GBy >= y - rayon / 2 && absolute(GBx - x) < y - GBy)
					|| (GBy >= y - rayon && GBy <= y - rayon / 2 && absolute(GBx - x) < GBy - (y - rayon))))
				return true;
			if (absoluteDirection == Direction.S && ((GBy > y && GBy <= y + rayon / 2 && absolute(GBx - x) < GBy - y)
					|| (GBy <= y + rayon && GBy >= y + rayon / 2 && absolute(GBx - x) < y + rayon - GBy)))
				return true;
		}
		// Cela test si le rectangle ou le losange de la direction a un point dans le
		// hitbox d'entité
		Rectangle2D hitBox = hitbox;
		Point2D point1, point2, point3;
		if (absoluteDirection == Direction.SE) {
			point1 = new Point2D.Double(x + rayon, y);
			point2 = new Point2D.Double(x, y + rayon);
			point3 = new Point2D.Double(x + rayon, y + rayon);
			if (hitBox.contains(point1) || hitBox.contains(point2) || hitBox.contains(point3))
				return true;
		}
		if (absoluteDirection == Direction.SW) {
			point1 = new Point2D.Double(x - rayon, y);
			point2 = new Point2D.Double(x, y + rayon);
			point3 = new Point2D.Double(x - rayon, y + rayon);
			if (hitBox.contains(point1) || hitBox.contains(point2) || hitBox.contains(point3))
				return true;
		}
		if (absoluteDirection == Direction.NW) {
			point1 = new Point2D.Double(x - rayon, y);
			point2 = new Point2D.Double(x, y - rayon);
			point3 = new Point2D.Double(x - rayon, y - rayon);
			if (hitBox.contains(point1) || hitBox.contains(point2) || hitBox.contains(point3))
				return true;
		}
		if (absoluteDirection == Direction.NE) {
			point1 = new Point2D.Double(x + rayon, y);
			point2 = new Point2D.Double(x, y - rayon);
			point3 = new Point2D.Double(x + rayon, y - rayon);
			if (hitBox.contains(point1) || hitBox.contains(point2) || hitBox.contains(point3))
				return true;
		}
		if (absoluteDirection == Direction.E) {
			point1 = new Point2D.Double(x + rayon, y);
			point2 = new Point2D.Double(x + rayon / 2, y + rayon / 2);
			point3 = new Point2D.Double(x + rayon / 2, y - rayon / 2);
			if (hitBox.contains(point1) || hitBox.contains(point2) || hitBox.contains(point3))
				return true;
		}
		if (absoluteDirection == Direction.W) {
			point1 = new Point2D.Double(x - rayon, y);
			point2 = new Point2D.Double(x - rayon / 2, y + rayon / 2);
			point3 = new Point2D.Double(x - rayon / 2, y - rayon / 2);
			if (hitBox.contains(point1) || hitBox.contains(point2) || hitBox.contains(point3))
				return true;
		}
		if (absoluteDirection == Direction.N) {
			point1 = new Point2D.Double(x, y - rayon);
			point2 = new Point2D.Double(x + rayon / 2, y - rayon / 2);
			point3 = new Point2D.Double(x - rayon / 2, y - rayon / 2);
			if (hitBox.contains(point1) || hitBox.contains(point2) || hitBox.contains(point3))
				return true;
		}
		if (absoluteDirection == Direction.S) {
			point1 = new Point2D.Double(x, y + rayon);
			point2 = new Point2D.Double(x + rayon / 2, y + rayon / 2);
			point3 = new Point2D.Double(x - rayon / 2, y + rayon / 2);
			if (hitBox.contains(point1) || hitBox.contains(point2) || hitBox.contains(point3))
				return true;
		}
		return false;
	}

	public boolean doClosest(Category category, Direction direction) {
		// Catégorie par défaut
		Category cat = category;
		if (category == null) {
			cat = Category.ADVERSARY;
		}
		Entity closestEntity = getClosestEntity(category);
		if (closestEntity == null) {
			return false;
		}

		// Direction par défaut
		Direction dir = direction;
		if (direction == null) {
			dir = Direction.FORWARD;
			return closest(cat, dir, closestEntity);
		} // Cas particulier de la direction d
		else if (direction == Direction.d) {
			dir = Direction.N; // Première direction qu'on vérifie
			boolean isDirectionFound = closest(cat, dir, closestEntity);
			boolean isTourCompleted = false;

			while (!isDirectionFound && !isTourCompleted) {
				dir = Direction.rotateSlightlyRight(dir);
				isDirectionFound = closest(cat, dir, closestEntity);

				if (dir == Direction.NW) {
					isTourCompleted = true;
				}
			}
			if (isDirectionFound) {
				lastDirectionRequested = dir;
				return true;
			}
		}

		return false;
	}

	private boolean closest(Category category, Direction direction, Entity closestEntity) {
		Direction absoluteDirection = Direction.relativeToAbsolute(getDirection(), direction);
		double angle = angleTo(closestEntity);
		return Direction.isAngleInDirection(angle, absoluteDirection);
	}

	private Entity getClosestEntity(Category category) {
		List<Entity> entitiesOfCategory = getEntitiesOfCategory(category);
		entitiesOfCategory.remove(this);
		if (entitiesOfCategory.isEmpty()) {
			return null;
		}
		double minimalDistance = distance(entitiesOfCategory.get(0));
		Entity closestEntity = entitiesOfCategory.get(0);
		for (Entity entity : entitiesOfCategory) {
			double distance = distance(entity);
			if (distance < minimalDistance) {
				minimalDistance = distance;
				closestEntity = entity;
			}
		}
		return closestEntity;
	}

	private List<Entity> getEntitiesOfCategory(Category category) {
		List<Entity> entitiesOfCategory = new LinkedList<Entity>();
		switch (category) {
		case ADVERSARY:
			for (Iterator<Entity> iterator = model.entitiesIterator(); iterator.hasNext();) {
				Entity entity = (Entity) iterator.next();
				if (!entity.getCategory().equals(this.category) && entity.category.isAbsoluteTeam()) {
					entitiesOfCategory.add(entity);
				}
			}
			break;
		case TEAM_MEMBER:
			for (Iterator<Entity> iterator = model.entitiesIterator(); iterator.hasNext();) {
				Entity entity = (Entity) iterator.next();
				if (entity.getCategory().isSameTeam(this.getCategory())) {
					entitiesOfCategory.add(entity);
				}
			}
			break;

		default:
			for (Iterator<Entity> iterator = model.entitiesIterator(); iterator.hasNext();) {
				Entity entity = (Entity) iterator.next();
				if (entity.getCategory().equals(category)) {
					entitiesOfCategory.add(entity);
				}
			}
		}
		return entitiesOfCategory;
	}

	private Category getCategory() {
		return category;
	}

	private double angleTo(Entity entity) {
		double relativeXPosition = entity.getCenter().getX() - getCenter().getX();
		double relativeYPosition = entity.getCenter().getY() - getCenter().getY();

		double angle = Math.toDegrees(Math.atan2(relativeYPosition, relativeXPosition)) + 90;

		if (angle < 0) {
			angle += 360;
		}

		return angle;

		// return Math.toDegrees(Math.atan2(relativeYPosition, relativeXPosition)) %
		// 360;

	}

	private double distance(Entity entity) {
		return getCenter().distance(entity.getCenter());
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
		if (this instanceof Player) {
			Player player = (Player) this;
			player.breathe();
		}
		if (automatonAvailable) {
			myFSM.step();
		}
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
		Vector movementX = new Vector(movement.getX(), 0);
		boolean isMovePossibleX = isMovePossible(movementX);
		if (!isMovePossibleX) {
			speed = new Vector(0, speed.getY());
			movement = new Vector(0, movement.getY());
		}
		Vector movementY = new Vector(0, movement.getY());
		boolean isMovePossibleY = isMovePossible(movementY);
		if (!isMovePossibleY) {
			speed = new Vector(speed.getX(), 0);
			movement = new Vector(movement.getX(), 0);
		}
		if (isMovePossibleX && isMovePossibleY && !isMovePossible(movement)) {
			speed = new Vector(0, 0);
			movement = new Vector(0, 0);
		}

		Direction dir = movement.getVectorDirection();
		if (dir != Direction.HERE) {
			direction = dir;
		}
		translatePosition(movement);
	}

	private Vector computeArchimedes() {
		// return new Vector(0, -density * volume * ModelConstants.GRAVITY);
		return new Vector(0, ModelConstants.DENSITY_CONSTANT * -(model.getDensity() - density));
	}

	private Vector computeWeight() {
		// return new Vector(0, mass * ModelConstants.GRAVITY);
		return new Vector(0, 0);
	}

	private Vector computeFriction() {
		Vector unitVector = speed.unitVector().scalarMultiplication(-1);
		double speedNorm = speed.norm();
		double vs2 = model.getViscosity() * (speedNorm);
		return unitVector.scalarMultiplication(vs2);
	}

	boolean isMovePossible(Vector movement) {
		Rectangle2D movementBox = getHitbox();
		Rectangle2D newHitbox = new Rectangle2D.Double(hitbox.getX() + movement.getX(), hitbox.getY() + movement.getY(),
				hitbox.getWidth(), hitbox.getHeight());

		// check if the movement will bring the entity outside the map
		Rectangle2D map = new Rectangle2D.Double(0, 0, getModel().getBoardWidth(), getModel().getBoardHeight());
		boolean isMoveInMap = map.contains(newHitbox);
		if (!isMoveInMap) {
			return false;
		}

		// check if the movement will overlap some other hitbox
		movementBox.add(newHitbox);
		List<Entity> closeEntities = getEntitiesInRectangle(movementBox);
		closeEntities.remove(this);
		if (closeEntities.isEmpty())
			return true;
		return false;
	}

	List<Entity> getEntitiesInRectangle(Rectangle2D rectangle) {
		List<Entity> list = new LinkedList<Entity>();
		for (Iterator<Entity> iterator = model.entitiesIterator(); iterator.hasNext();) {
			Entity entity = (Entity) iterator.next();
			if (entity.getHitbox().intersects(rectangle)) {
				list.add(entity);
			}
		}
		return list;
	}

	public Point2D.Double getHitboxTopLeft() {
		return new Point2D.Double(hitbox.getX(), hitbox.getY());
	}

	public Point2D.Double getHitboxTopRight() {
		return new Point2D.Double(hitbox.getX() + hitbox.getWidth(), hitbox.getY());
	}

	public Point2D.Double getHitboxBottomLeft() {
		return new Point2D.Double(hitbox.getX(), hitbox.getY() + hitbox.getHeight());
	}

	public Point2D.Double getHitboxBottomRight() {
		return new Point2D.Double(hitbox.getX() + hitbox.getWidth(), hitbox.getY() + hitbox.getHeight());
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

	/**
	 * Modifie la valeur des points de vie de l'entite Si le nombre de point de vie
	 * descend en dessous de 0, l'entite est placée dans un tableau pour etre
	 * supprimé
	 * 
	 * @param val
	 */
	public void modifyHealthPoint(int val, Entity e) {
		this.healthPoint += val;
		if (this.healthPoint <= 0) {
			if (!(model.toRemove.contains(this))) {
				this.model.addEntityToRemove(this);
			}
			if (e != null) {
				e.modifyHealthPoint(this.attackDamage * 2, e);
				if (e.healthPoint > 100) {
					e.setHealthPoint(maxHealthPoint);
				}
			}
		}
	}

	/**
	 * Enleve un nombre de point de vie a une entité
	 * 
	 * @param val
	 */
	public void getHit(Entity e) {
		if (e == null) {
			this.modifyHealthPoint(-1, null);
		} else {
			this.modifyHealthPoint(-(e.attackDamage), e);
		}
	}

	public void doHit(Direction direction) {
		blockAutomaton();
		setState(State.HITTING);
		if (direction != null) {
			this.direction = Direction.relativeToAbsolute(this.direction, getRightDirection(direction));
		}
		ActionTask hitTask = new HitTask(this, hitDuration / 2, getRightDirection(direction));
		currenTask = hitTask;
		timer.schedule(hitTask, hitTask.getDuration());
	}

	/**
	 * Action hit autour du personnage dans sa range
	 */
	public void hit() {
		Rectangle2D hitRange = new Rectangle2D.Double(this.hitbox.getX() - meleeRange, this.hitbox.getY() - meleeRange,
				this.hitbox.getWidth() + 2 * meleeRange, this.hitbox.getHeight() + 2 * meleeRange);
		Iterator<Entity> it = this.model.entitiesIterator();
		while (it.hasNext()) {
			Entity e = it.next();
			if (!(e.getCategory().isSameTeam(this.getCategory()))) {
				if (e.getHitbox().intersects(hitRange)) {
					e.getHit(this);
				}
			}
		}
	}

	/**
	 * Action hit dans une certaine direction
	 * 
	 * @param d
	 */
	public void hit(Direction d) {
		Direction absoluteDirection = Direction.relativeToAbsolute(direction, d);
		Rectangle2D hitRange;
		switch (absoluteDirection) {
		case N:
			hitRange = new Rectangle2D.Double(this.hitbox.getX() - meleeRange, this.hitbox.getY() - meleeRange,
					this.hitbox.getWidth() + 2 * meleeRange, meleeRange);
			break;
		case E:
			hitRange = new Rectangle2D.Double(this.hitbox.getX() + this.hitbox.getWidth(),
					this.hitbox.getY() - meleeRange, meleeRange, this.hitbox.getHeight() + 2 * meleeRange);
			break;
		case S:
			hitRange = new Rectangle2D.Double(this.hitbox.getX() - meleeRange, this.hitbox.getY() + meleeRange,
					this.hitbox.getWidth() + 2 * meleeRange, meleeRange);
			break;
		case W:
			hitRange = new Rectangle2D.Double(this.hitbox.getX() - meleeRange, this.hitbox.getY() - meleeRange,
					meleeRange, this.hitbox.getHeight() + 2 * meleeRange);
			break;
		case NE:
			hitRange = new Rectangle2D.Double(getCenter().getX(), this.hitbox.getY() - meleeRange,
					this.hitbox.getWidth() / 2 + meleeRange, this.hitbox.getHeight() / 2 + meleeRange);
			break;
		case NW:
			hitRange = new Rectangle2D.Double(this.hitbox.getX() - meleeRange, this.hitbox.getY() - meleeRange,
					this.hitbox.getWidth() / 2 + meleeRange, this.hitbox.getHeight() / 2 + meleeRange);
			break;
		case SE:
			hitRange = new Rectangle2D.Double(getCenter().getX(), getCenter().getY(),
					this.hitbox.getWidth() / 2 + meleeRange, this.hitbox.getHeight() / 2 + meleeRange);
			break;
		case SW:
			hitRange = new Rectangle2D.Double(this.hitbox.getX() - meleeRange, getCenter().getY(),
					this.hitbox.getWidth() / 2 + meleeRange, this.hitbox.getHeight() / 2 + meleeRange);
			break;
		default:
			hitRange = null;
		}
		Iterator<Entity> it = this.model.entitiesIterator();
		while (it.hasNext()) {
			Entity e = it.next();
			if (!(e.getCategory().isSameTeam(this.getCategory()))) {
				if (e.getHitbox().intersects(hitRange)) {
					e.getHit(this);
				}
			}
		}
	}

	public Category getTeam() {
		return team;
	}

	public State getState() {
		return state;
	}

	void setState(State newState) {
		state = newState;
	}

	public FSM getFSM() {
		return myFSM;
	}

	public void setForce(Vector newForce) {
		force = newForce;
	}

	public void blockAutomaton() {
		automatonAvailable = false;
	}

	public void freeAutomaton() {
		automatonAvailable = true;
	}

	public void destroy() {
		model.removeEntity(this);
		if (currenTask != null) {
			currenTask.cancel();
		}
		if (timer != null) {
			timer.cancel();
		}
		setState(State.DYING);
		if (this instanceof Player) {
			model.removePlayer((Player) this);
		}
	}

	public boolean doKey(String key) {
		return model.getController().isKeyPressed(key);
	}

	/**
	 * Crée une entité devant une entité
	 * 
	 * @param ekey
	 */
	private void throwEntity(String name, Direction direction) {
		Point2D pos = null;
		int marge = 3;
		config.Config cfg = model.getConfig();
		if (direction == Direction.N)
			pos = new Point2D.Double(this.getX(), this.getY() - cfg.getIntValue(this.name, "height") - marge);
		if (direction == Direction.S)
			pos = new Point2D.Double(this.getX(), this.getY() + cfg.getIntValue(this.name, "height") + marge);
		if (direction == Direction.E)
			pos = new Point2D.Double(this.getX() + cfg.getIntValue(this.name, "width") + marge, this.getY());
		if (direction == Direction.W)
			pos = new Point2D.Double(this.getX() - cfg.getIntValue(this.name, "width") - marge, this.getY());
		if (direction == Direction.SE)
			pos = new Point2D.Double(this.getX() + cfg.getIntValue(this.name, "width") + marge,
					this.getY() + cfg.getIntValue(this.name, "height") + marge);
		if (direction == Direction.SW)
			pos = new Point2D.Double(this.getX() - cfg.getIntValue(this.name, "width") - marge,
					this.getY() + cfg.getIntValue(this.name, "height") + marge);
		if (direction == Direction.NW)
			pos = new Point2D.Double(this.getX() - cfg.getIntValue(this.name, "width") - marge,
					this.getY() - cfg.getIntValue(this.name, "height") - marge);
		if (direction == Direction.NE)
			pos = new Point2D.Double(this.getX() + cfg.getIntValue(this.name, "width") + marge,
					this.getY() - cfg.getIntValue(this.name, "height") - marge);

		Rectangle2D futurHitBox = new Rectangle2D.Double(pos.getX(), pos.getY(), cfg.getFloatValue(name, "width"),
				cfg.getFloatValue(name, "height"));
		Iterator<Entity> iter = this.model.entitiesIterator();
		Entity e;
		boolean isAnEntityInHitbox = false;
		while (iter.hasNext()) {
			e = iter.next();
			if (e.hitbox.intersects(futurHitBox)) {
				isAnEntityInHitbox = true;
				break;
			}
		}
		switch (name) {
		case "Player":
			e = new Player(pos, direction, this.model, name);
			break;
		default:
			e = new Mob(pos, direction, this.model, name);
			break;
		}

		if (isAnEntityInHitbox) {
			e.doExplode();
		}
	}

	private Direction getRightDirection(Direction dir) {
		if (dir == Direction.d) {
			return lastDirectionRequested;
		}
		return dir;
	}

	public boolean isPhysicObject() {
		return isPhysicObject;
	}

	public void doThrow(Direction direction) {
		blockAutomaton();
		setState(State.WAITING);
		if (direction != null) {
			Direction dir = Direction.relativeToAbsolute(this.direction, getRightDirection(direction)).getCardinalDirection();
			this.direction = dir;
			throwEntity(throwEntity, dir);
		}
		else {
			throwEntity(throwEntity, this.direction.getCardinalDirection());
		}

		ActionTask endThrowTask = new EndThrowTask(this, throwDuration);

		currenTask = endThrowTask;
		timer.schedule(endThrowTask, endThrowTask.getDuration());
	}

	public int getMaxHealtPoint() {
		return maxHealthPoint;
	}
}
