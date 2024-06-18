package view;

import java.awt.Graphics;

import model.Entity;

/**
 * Classe réunissant les champs et méthodes communes à tout les avatars
 */
public abstract class Avatar {

	protected View m_view;

	protected Entity instanceEntity;
	protected boolean isVisible;

	protected int currentSpriteNumber = 0; // numero de sprite dans un set
	protected int spriteSetNumber = 1; // numero de set de sprite

	/**
	 * Crée et initialise les champs communs des avatars. Doit être appellé par une
	 * sous-classe
	 * 
	 * @param m_view : instance courante de la view
	 * @param e      : entité associé à l'avatar
	 */
	protected Avatar(View m_view, Entity e) {
		this.m_view = m_view;
		instanceEntity = e;
		setInvisible();
	}

	/**
	 * Retourne l'entité associé a l'avatar
	 * 
	 * @return instance de l'entité associé
	 */
	Entity getEntity() {
		return instanceEntity;
	}

	/**
	 * Rend l'avatar visible sur la fenêtre
	 */
	public void setVisible() {
		isVisible = true;
	}

	/**
	 * Rend l'avatar invisible sur la fenêtre
	 */
	public void setInvisible() {
		isVisible = false;
	}

	/**
	 * Affiche l'avatar en fonction du viewport et des coordonnées de l'entité
	 * 
	 * @param g : inctance graphique du canvas
	 */
	abstract void paint(Graphics g);

}
