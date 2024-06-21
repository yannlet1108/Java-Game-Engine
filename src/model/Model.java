package model;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import automaton.AutomatonBank;
import config.Config;
import controller.Controller;
import view.View;

public class Model {
	private Controller m_controller;
	View m_view;

	private double worldHeight;
	private double worldWidth;
	private double density;
	private double viscosity;

	private Collection<Entity> entities;
	private Collection<Player> players;
	Collection<Entity> toRemove;

	private int playerSpawnX;
	private int playerSpawnY;
	private int seed;
	private int safeZone;

	private AutomatonBank automatonBank;

	/**
	 * Initialise la simulation en creant les entites d'origine et en definisant
	 * leur etat d'initialisation.
	 * 
	 * @param m_controller Instance courante du Controller
	 * @param m_view       Instance courante de l'affichage
	 */
	public Model(Controller m_controller, View m_view) {
		this.m_controller = m_controller;
		this.m_view = m_view;
		worldHeight = this.m_controller.getConfig().getIntValue("world", "height");
		worldWidth = this.m_controller.getConfig().getIntValue("world", "width");
		density = this.m_controller.getConfig().getIntValue("world", "density");
		viscosity = this.m_controller.getConfig().getIntValue("world", "viscosity");
		entities = new LinkedList<Entity>();
		players = new LinkedList<Player>();
		toRemove = new LinkedList<Entity>();
		automatonBank = new AutomatonBank();
		mapGenerator();
		playerSpawnX = getConfig().getIntValue("world", "playerSpawnX");
		playerSpawnY = getConfig().getIntValue("world", "playerSpawnY");
		new Player(new Point2D.Double(playerSpawnX,playerSpawnY), Direction.N, this, "Player1");
		m_view.setModel(this);
		seed = getConfig().getIntValue("world", "seed");
		safeZone = getConfig().getIntValue("world", "safeZone");
	}

	/**
	 * Retourne la hauteur de la simulation.
	 * 
	 * @return Hauteur de la matrice de jeu
	 */
	public double getBoardHeight() {
		return worldHeight;
	}

	/**
	 * Retourne la largeur de la simulation.
	 * 
	 * @return Largeur de la matrice de jeu
	 */
	public double getBoardWidth() {
		return worldWidth;
	}

	/**
	 * Actualise l'etat des entites en fonction de leur l'environnement et de leur
	 * automate associe.
	 */
	public void step() {
		for (Entity entity : entities) {
			entity.step();
			if (entity.isPhysicObject()) {
				entity.computeMovement();
			}
		}
		spawnEnemy();
	}

	/**
	 * spawn un poisson d'une probabilitée IN_GENERAL / 1000 dans une poisition
	 * aléatoire , ce poisson peut être de differents types avec une probabilitée
	 * chacun, 10 essaies si il n y a pas de la place l'opération s'arrête.
	 * 
	 * @return le poisson
	 * @author MO ER
	 */
	private Mob spawnEnemy() {
		config.Config cfg = this.m_controller.getConfig();
		Random yesOrNotRand = new Random();
		int yesOrNot = yesOrNotRand.nextInt(1000); // 1000 à modif
		if (yesOrNot < ModelConstants.IN_GENERAL) {
			Random ranWhatFish = new Random();
			int mobProb = ranWhatFish.nextInt(100);
			int mobnum = -1;
			double width = 0, height = 0;
			if (mobProb < cfg.getIntValue("mob0", "spawnProba")) {
				mobnum = 0;
			} else if (mobProb >= ModelConstants.GOLDENFISH_PROBA
					&& mobProb < ModelConstants.GOLDENFISH_PROBA + ModelConstants.SHARK_PROBA) {
				mobnum = 1;
			} else {
				return null;
			}
			width = cfg.getIntValue("mob" + mobnum, "width");
			height = cfg.getIntValue("mob" + mobnum, "height");
			boolean isGood = false;
			int x = 0, y = 0;
			Point2D pts = new Point2D.Double(x, y);
			Rectangle2D candidate;
			int whileCounter = 0;
			while (!isGood) {
				if (whileCounter > 10) {
					System.out.println("Almost Nowhere to spawn, gave Up");
					return null;
				}
				isGood = true;
				Random ranWhereFish = new Random();
				x = ranWhereFish.nextInt((int) this.getBoardWidth());
				y = ranWhereFish.nextInt((int) this.getBoardHeight());
				pts = new Point2D.Double(x, y);
				candidate = new Rectangle2D.Double(x, y, width, height);
				Iterator<Entity> iter = this.entitiesIterator();
				Entity currentEntity;
				while (iter.hasNext()) {
					currentEntity = iter.next();
					if (currentEntity.hitbox.intersects(candidate)) {
						isGood = false;
						break;
					}
				}
				whileCounter++;
			}
			Mob nue = new Mob(pts, Direction.E, this, "mob" + mobnum);
			// Pour test
			// System.out.println("Goldfish added at x = " + pts.getX() + ", y = " +
			// pts.getY() + ".");
			return nue;
		}
		return null;

	}

	/**
	 * 
	 * @return les coordonées du centre de la map
	 */
	Point2D getWorldCenter() {
		return new Point2D.Double(worldWidth / 2, worldHeight / 2);
	}

	public double getViscosity() {
		return viscosity;
	}

	public double getDensity() {
		return density;
	}

	/**
	 * Ajoute une entitée dans la collection des entitées
	 * 
	 * @param enitity e
	 */
	void addEntity(Entity e) {
		entities.add(e);
	}

	/**
	 * Supprime une entitée de la collection des entitées
	 * 
	 * @param e
	 */
	void removeEntity(Entity e) {
		entities.remove(e);
	}

	/**
	 * 
	 * @return un iterateur sur la collection des players
	 */
	public Iterator<Point2D> getPlayersPos() {
		LinkedList<Point2D> thePoints = new LinkedList<Point2D>();
		Iterator<Player> iterPlayer = players.iterator();
		Player play;
		while (iterPlayer.hasNext()) {
			play = iterPlayer.next();
			thePoints.add(play.getCenter());
		}
		return thePoints.iterator();
	}

	/**
	 * Ajoute un player dans la collection des players
	 * 
	 * @param Player P
	 */
	public void addPlayer(Player P) {
		players.add(P);
	}

	/**
	 * Enleve un player de la collection des players
	 * 
	 * @param Player P
	 */
	public void removePlayer(Player P) {
		players.remove(P);
	}

	/**
	 * 
	 * @return Iterateur sur les entitées de model.
	 */
	public Iterator<Entity> entitiesIterator() {
		Iterator<Entity> iter = this.entities.iterator();
		return iter;
	}

	/**
	 * Ajoute une entité dans un tableau d'entité a supprimer
	 * 
	 * @param e
	 */
	void addEntityToRemove(Entity e) {
		toRemove.add(e);
	}

	/**
	 * Supprime une entité des tableaux entities et players
	 */
	void removeEntityToRemove() {
		Iterator<Entity> iter = this.toRemove.iterator();
		while (iter.hasNext()) {
			Entity e = iter.next();
			this.removeEntity(e);
			if (e instanceof Player) {
				this.removePlayer((Player) e);
			}
		}
		toRemove.clear();
	}

	public Collection<Player> getPlayers() {
		return players;
	}

	public void mapGenerator() {
		Random r = new Random(seed);
		int obstacleWidth = getConfig().getIntValue("obstacle", "width");
		int obstacleHeight = getConfig().getIntValue("obstacle", "height");
		float obstacleProbability = getConfig().getFloatValue("obstacle", "probability");
		for (int i = 0; i < this.getBoardWidth(); i = i + obstacleWidth) {
			for (int j = 0; j < this.getBoardHeight(); j = j + obstacleHeight) {
				if (r.nextDouble() < obstacleProbability) {
					new Obstacle(new Point2D.Double(i, j), null, this);
				}
			}
		}
		Rectangle2D safezone = new Rectangle2D.Double(playerSpawnX - safeZone, playerSpawnY, safeZone, safeZone);
		Iterator<Entity> it = this.entitiesIterator();
		while (it.hasNext()) {
			Entity e = it.next();
			if (e instanceof Obstacle) {
				if (e.getHitbox().intersects(safezone)) {
					this.addEntityToRemove(e);
				}
			}
		}
		this.removeEntityToRemove();
	}

	public Collection<Entity> getEntities() {
		return entities;
	}

	public AutomatonBank getAutomatonBank() {
		return automatonBank;
	}

	public Config getConfig() {
		return m_controller.getConfig();
	}

	public Controller getController() {
		return m_controller;
	}

	public int getSafeZone() {
		return safeZone;
	}
}
