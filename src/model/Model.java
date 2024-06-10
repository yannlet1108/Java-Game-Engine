package model;

import java.awt.Point;
import java.util.Collection;
import java.util.LinkedList;

import controller.Controller;
import view.View;

/**
 * Classe gerant l'aspect simulation du jeu. Gere le placement des entites dans
 * la simuation. Gere l'etat des entites dans la simulation. Controle le
 * comportement des entites et definis leurs actions. Applique une physique sur
 * la simulation.
 */
public class Model {
	private Matrix board;
	Controller m_controller;
	View m_view;

	private int boardHeight;
	private int boardWidth;
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
		boardHeight = ModelCst.NB_CELL;
		boardWidth = ModelCst.NB_CELL;
		board = new Matrix(boardHeight, boardWidth);
		entities = new LinkedList<Entity>();
		new Snake(new Point(boardWidth / 2, boardHeight / 2), Direction.N, this);
		new Apple(new Point(0,0), Direction.N, this);
	}

	/**
	 * Retourne la hauteur de la simulation.
	 * 
	 * @return Hauteur de la matrice de jeu
	 */
	public int getBoardHeight() {
		return boardHeight;
	}

	/**
	 * Retourne la largeur de la simulation.
	 * 
	 * @return Largeur de la matrice de jeu
	 */
	public int getBoardWidth() {
		return boardWidth;
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
	 * Retourne l'entité à la position donnée
	 * 
	 * @param position
	 * @return l'entité à la position donnée
	 */
	Entity getEntityAt(Point position) {
		return board.getEntityAt(position.y, position.x);
	}

	Category getCategoryAt(Point position) {
		try {
			Entity e = getEntityAt(position);
			if (e == null)
				return Category.VOID;
			return e.category;
		} catch (IndexOutOfBoundsException e) {
			return Category.OBSTACLE;
		}
	}

	/**
	 * @implNote Met à jour l'entité à la position donnéz
	 * @param position
	 * @param e
	 */
	void setEntityAt(Point position, Entity e) {
		board.setEntityAt(position.y, position.x, e);
	}

	void removeEntity(Entity e) {
		entities.remove(e);
		board.setEntityAt(e.getX(), e.getY(), null);
	}

	void addEntity(Entity e) {
		entities.add(e);
		board.setEntityAt(e.getX(), e.getY(), e);
	}
	
	public boolean outOfBounds(int x, int y) {
		return x < 0 || x > boardWidth || y < 0 || y > boardHeight;
	}
}
