package model;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Collection;
import java.util.LinkedList;

import javax.swing.text.Position;

import controller.Controller;
import view.View;

/**
 * Classe principale du model. S'occupe de créer et gérer les entités dans la
 * simulation et de les soumettre à la physique
 */
public class Model {

	private Controller m_controller;
	private View m_view;

	private double worldHeight;
	private double worldWidth;
	private Collection<Entity> entities;

	private Player p;

	/**
	 * Initialise la simulation en créant les entités d'origine et en definisant
	 * leurs état d'initialisation.
	 * 
	 * @param m_controller Instance courante du Controller
	 * @param m_view       Instance courante de l'affichage
	 */
	public Model(Controller m_controller, View m_view) {
		this.m_controller = m_controller;
		this.m_view = m_view;
		worldHeight = ModelCst.WORLD_HEIGHT;
		worldWidth = ModelCst.WORLD_WIDTH;
		entities = new LinkedList<Entity>();

		p = new Player(getWorldCenter(), Direction.E, this);

		m_view.setModel(this);
	}

	public Point2D getSimCenter() {
		return p.getCenter();
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
	 * Actualise l'etat des entités en fonction de leur l'environnement et de leur
	 * automate associe.
	 */
	public void step() {
		for (Entity entity : entities) {
			entity.step();
		}
	}

	/**
	 * Retourne les coordonnées du centre de la simulation
	 * 
	 * @return les coordonées du centre de la map
	 */
	private Point2D getWorldCenter() {
		return new Point2D.Double(worldWidth / 2, worldHeight / 2);
	}

	View getView() {
		return m_view;
	}
}
