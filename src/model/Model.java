package model;

import java.awt.geom.Point2D;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

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
		Player P = new Player(getWorldCenter(), Direction.E, this);
		entities.add(P);
		players.add(P);
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
}
