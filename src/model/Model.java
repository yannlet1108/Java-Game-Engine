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
	Controller m_controller;
	View m_view;

	private double worldHeight;
	private double worldWidth;
	private double density;
	private double viscosity;

	private Collection<Entity> entities;
	private Collection<Player> players;
	Collection<Entity> toRemove;
	
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
		worldHeight = ModelConstants.WORLD_HEIGHT;
		worldWidth = ModelConstants.WORLD_WIDTH;
		density = ModelConstants.WORLD_DENSITY;
		viscosity = ModelConstants.WORLD_VISCOSITY;
		entities = new LinkedList<Entity>();
		players = new LinkedList<Player>();
		toRemove = new LinkedList<Entity>();
		new Player(getWorldCenter(), Direction.N, this);
		m_view.setModel(this);
		automatonBank = new AutomatonBank();
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
	private Fish spawnEnemy() {
		Random yesOrNotRand = new Random();
		int yesOrNot = yesOrNotRand.nextInt(1000); // 1000 à modif
		if (yesOrNot <  ModelConstants.IN_GENERAL) {
			Random ranWhatFish = new Random();
			int fishProb = ranWhatFish.nextInt(100);
			int fish = -1;
			double width = 0, height = 0;
			if (fishProb < ModelConstants.GOLDENFISH_PROBA) {
				fish = 0;
				width = FishConstants.GOLDFISH_WIDTH;
				height = FishConstants.GOLDFISH_HEIGHT;
			} else if (fishProb >= ModelConstants.GOLDENFISH_PROBA
					&& fishProb < ModelConstants.GOLDENFISH_PROBA + ModelConstants.SHARK_PROBA) {
				fish = 1;
				width = FishConstants.SHARK_WIDTH;
				height = FishConstants.SHARK_HEIGHT;
			} else {
				return null;
			}
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
			Fish nue;
			if (fish == 0) {
				nue = new Goldfish(pts, Direction.E, this);
				// Pour test
				// System.out.println("Goldfish added at x = " + pts.getX() + ", y = " +
				// pts.getY() + ".");
				return nue;
			} else if (fish == 1) {
				nue = new Shark(pts, Direction.E, this);
				// Pour test
				// System.out.println("Shark added at x = " + pts.getX() + ", y = " + pts.getY()
				// + ".");
				return nue;
			}
			return null;
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
		Random r = new Random(ModelConstants.SEED);
		for (int i = 0; i < ModelConstants.WORLD_WIDTH; i = i + EntityConstants.OBSTACLE_WIDTH) {
			for (int j = 0; j < ModelConstants.WORLD_HEIGHT; j = j + EntityConstants.OBSTACLE_HEIGHT) {
				if (r.nextDouble() < ModelConstants.OBSTACLE_PROBABILITY) {
					new Obstacle(new Point2D.Double(i, j), null, this);
				}
			}
		}
		Rectangle2D safezone = new Rectangle2D.Double(ModelConstants.PLAYER_SPAWN_X - ModelConstants.SAFE_AREA,
				ModelConstants.PLAYER_SPAWN_Y, ModelConstants.SAFE_AREA, ModelConstants.SAFE_AREA);
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
}
