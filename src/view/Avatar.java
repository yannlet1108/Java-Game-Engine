package view;

import java.awt.Graphics;

import model.Entity;

/**
 * Classe contenant les operations communes a tout les Avatars
 */
public abstract class Avatar {

	View m_view;

	protected Entity instanceEntity;

	protected boolean isVisible;

	/**
	 * Cree un Avatar | ne peut pas etre instancie
	 * 
	 * @param m_view : Instance de la vew
	 * @param e      : Entite liee a l'Avatar courant
	 */
	Avatar(View m_view, Entity e) {
		this.m_view = m_view;
		instanceEntity = e;
		setInvisible();
	}

	/**
	 * Renvois l'Entite liee a l'Avatar courant
	 * 
	 * @return Entite liee
	 */
	public Entity getEntity() {
		return instanceEntity;
	}

	/**
	 * Parametre l'Avatar comme etant visible
	 */
	public void setVisible() {
		isVisible = true;
	}

	/**
	 * Parametre l'Avatar comme etant invisible
	 */
	public void setInvisible() {
		isVisible = false;
	}

	/**
	 * Affiche l'Avatar courant selon sont Entite liee
	 * 
	 * @param g : Objet de graphique contenant les parametrages deja effectues dans
	 *          le but de odifier la fenetre graphique
	 */
	public abstract void paint(Graphics g);

}
