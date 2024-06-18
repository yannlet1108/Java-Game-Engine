package model;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

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
		}
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
			if(e instanceof Obstacle) {
				if(e.getHitbox().intersects(safezone)) {
					this.addEntityToRemove(e);
				}
			}
		}
		this.removeEntityToRemove();
	}

	public Collection<Entity> getEntities() {
		return entities;
	}
}
