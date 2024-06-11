package model;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.text.Position;

import controller.Controller;

public class Model {
	Controller m_controller;
	View m_view;

	private double worldHeight;
	private double worldWidth;
	private Collection<Entity> entities;

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
		m_view.setModel(this);
		worldHeight = ModelConstants.WORLD_HEIGHT;
		worldWidth = ModelConstants.WORLD_WIDTH;
		entities = new LinkedList<Entity>();
		new Player(getWorldCenter(), Direction.E, this);
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
	private Point2D getWorldCenter() {
		return new Point2D.Double(worldWidth / 2, worldHeight / 2);
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
