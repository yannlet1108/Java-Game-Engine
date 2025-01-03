package model;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
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
	private Collection<Entity> toRemove;
	private Collection<Entity> toAdd;
	private int maxEntity;

	private int player1SpawnX;
	private int player1SpawnY;
	private int player2SpawnX;
	private int player2SpawnY;
	private int seed;
	private int safeZone;
	private Double refillArea;

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
		worldHeight = getConfig().getIntValue("World", "height");
		worldWidth = getConfig().getIntValue("World", "width");
		density = getConfig().getFloatValue("World", "density");
		viscosity = getConfig().getFloatValue("World", "viscosity");
		entities = new LinkedList<Entity>();
		players = new LinkedList<Player>();
		toRemove = new LinkedList<Entity>();
		toAdd = new LinkedList<Entity>();
		automatonBank = new AutomatonBank();
		maxEntity = getConfig().getIntValue("World", "maxEntity");

		player1SpawnX = getConfig().getIntValue("World", "player1SpawnX");
		player1SpawnY = getConfig().getIntValue("World", "player1SpawnY");
		player2SpawnX = getConfig().getIntValue("World", "player2SpawnX");
		player2SpawnY = getConfig().getIntValue("World", "player2SpawnY");
		seed = getConfig().getIntValue("World", "seed");
		safeZone = getConfig().getIntValue("World", "safeZone");
		int refillAreaSizeX = getConfig().getIntValue("World", "refillAreaSizeX");
		int refillAreaSizeY = getConfig().getIntValue("World", "refillAreaSizeY");
		refillArea = new Rectangle2D.Double((worldWidth - refillAreaSizeX) / 2, 0, refillAreaSizeX, refillAreaSizeY);
		mapGenerator();
		new Player(new Point2D.Double(player1SpawnX, player1SpawnY), Direction.N, this, "Player1");
		new Player(new Point2D.Double(player2SpawnX, player2SpawnY), Direction.N, this, "Player2");
		m_view.setModel(this);
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
		removeEntityToRemove();
		spawnEnemy();
		addEntityToAdd();
	}

	/**
	 * spawn un poisson d'une probabilitée spawnMobProba% dans une position
	 * aléatoire , ce poisson peut être de differents types avec une probabilitée
	 * chacun, 30 essaies si il n y a pas de la place l'opération s'arrête.
	 * 
	 * @return le poisson
	 * @author MO ER
	 */
	private Mob spawnEnemy() {
		if (entities.size() < maxEntity) {
			Config cfg = this.m_controller.getConfig();
			Random yesOrNotRand = new Random();
			int yesOrNot = yesOrNotRand.nextInt(100); // 100 à modif
			if (yesOrNot < cfg.getIntValue("World", "spawnMobProba")) {
				Random ranWhatFish = new Random();
				int mobProb = ranWhatFish.nextInt(100);
				int mobnum = -1;
				double width = 0, height = 0;
				if (mobProb < cfg.getIntValue("Mob0", "spawnProba")) {
					mobnum = 0;
				} else if (mobProb >= cfg.getIntValue("Mob0", "spawnProba")
						&& mobProb < cfg.getIntValue("Mob1", "spawnProba") + cfg.getIntValue("Mob0", "spawnProba")) {
					mobnum = 1;
				} else {
					return null;
				}
				width = cfg.getIntValue("Mob" + mobnum, "width");
				height = cfg.getIntValue("Mob" + mobnum, "height");
				boolean isGood = false;
				int x = 0, y = 0;
				Point2D pts = new Point2D.Double(x, y);
				Rectangle2D candidate;
				int whileCounter = 0;
				while (!isGood) {
					if (whileCounter > 30) {
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
				Mob nue = new Mob(pts, Direction.E, this, "Mob" + mobnum);
				// Pour test
				// System.out.println("Goldfish added at x = " + pts.getX() + ", y = " +
				// pts.getY() + ".");
				return nue;
			}
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

	double getViscosity() {
		return viscosity;
	}

	double getDensity() {
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
	void addPlayer(Player P) {
		players.add(P);
	}

	/**
	 * Enleve un player de la collection des players
	 * 
	 * @param Player P
	 */
	void removePlayer(Player P) {
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
		getToRemove().add(e);
	}

	/**
	 * Supprime une entité des tableaux entities et players
	 */
	void removeEntityToRemove() {
		Iterator<Entity> iter = this.getToRemove().iterator();
		while (iter.hasNext()) {
			Entity e = iter.next();
			e.destroy();
		}
		getToRemove().clear();
	}

	void addEntityToAdd() {
		Iterator<Entity> iter = this.getToAdd().iterator();
		while (iter.hasNext()) {
			Entity e = iter.next();
			addEntity(e);
		}
		getToAdd().clear();
	}

	public Collection<Player> getPlayers() {
		return players;
	}

	void mapGenerator() {
		Random r = new Random(seed);
		int obstacleWidth = getConfig().getIntValue("Obstacle", "width");
		int obstacleHeight = getConfig().getIntValue("Obstacle", "height");
		int playerWidth = getConfig().getIntValue("Player1", "width");
		float obstacleProbability = getConfig().getFloatValue("Obstacle", "probability");
		Rectangle2D safezone = new Rectangle2D.Double(player1SpawnX - safeZone, player1SpawnY,
				(player2SpawnX - player1SpawnX) + 2 * safeZone + 2 * playerWidth, 2 * safeZone);
		for (int i = 0; i < this.getBoardWidth(); i = i + obstacleWidth) {
			for (int j = 0; j < this.getBoardHeight(); j = j + obstacleHeight) {
				Rectangle2D block = new Rectangle2D.Double(i, j, obstacleWidth, obstacleHeight);
				if (!(safezone.intersects(block))) {
					if (r.nextDouble() < obstacleProbability) {
						new Obstacle(new Point2D.Double(i, j), Direction.E, this);
					}
				}
			}
		}
	}

	Collection<Entity> getEntities() {
		return entities;
	}

	AutomatonBank getAutomatonBank() {
		return automatonBank;
	}

	Config getConfig() {
		return m_controller.getConfig();
	}

	Controller getController() {
		return m_controller;
	}

	int getSafeZone() {
		return safeZone;
	}

	Double getRefillArea() {
		return refillArea;
	}

	public Player getPlayer1() {
		for (Player player : players) {
			if (player.name == "Player1") {
				return player;
			}
		}
		return null;
	}

	public Player getPlayer2() {
		for (Player player : players) {
			if (player.name == "Player2") {
				return player;
			}
		}
		return null;
	}

	public Collection<Entity> getToRemove() {
		return toRemove;
	}

	public Collection<Entity> getToAdd() {
		return toAdd;
	}
}
