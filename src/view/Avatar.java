package view;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

import model.Entity;

/**
 * Classe réunissant les champs et méthodes communes à tout les avatars
 */
public abstract class Avatar {

	protected View m_view;

	protected Entity instanceEntity;
	protected boolean isVisible;

	protected int currentSpriteNumber = 0; // numero de sprite dans un set
	protected int spriteSetNumber; // numero de set de sprite

	/**
	 * Crée et initialise les champs communs des avatars. Doit être appellé par une
	 * sous-classe
	 * charge le set de sprites associé à l'entité
	 * 
	 * @param m_view : instance courante de la view
	 * @param e      : entité associé à l'avatar
	 */
	protected Avatar(View m_view, Entity e, int entityType) {
		this.m_view = m_view;
		instanceEntity = e;

		spriteSetNumber = m_view.getBank().loadSpritesSet(getSpritesFile(entityType), getSpritesNrows(entityType),
				getSpritesNcols(entityType));

		setInvisible();
	}

	/**
	 * Cherche le nom fichier de sprites associé à l'entité dans la config
	 * 
	 * @param entityType : numero de l'entité dans la config
	 * @return nom du fichier de sprites
	 */
	String getSpritesFile(int entityType) {
		return ViewCst.SPRITES_FILES[entityType];
	}

	/**
	 * Cherche le nombre de lignes de sprites associé au fichier dans la config
	 * 
	 * @param entityType : numero de l'entité dans la config
	 * @return nombre de lignes de sprites
	 */
	int getSpritesNrows(int entityType) {
		return ViewCst.SPRITES_NROWS[entityType];
	}

	/**
	 * Cherche le nombre de colonnes de sprites associé au fichier dans la config
	 * 
	 * @param entityType : numero de l'entité dans la config
	 * @return nombre de colonnes de sprites
	 */
	int getSpritesNcols(int entityType) {
		return ViewCst.SPRITES_NROWS[entityType];

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
	 * affichage en hitbox si mode debug activé
	 * en sprite sinon
	 * 
	 * @param g : instance graphique du canvas
	 */
	void paint(Graphics g) {
		if (ViewCst.DEBUG) {
			debugPaint(g);
		} else {
			Rectangle2D collisionBox = instanceEntity.getHitbox();
			Point origin = m_view.getViewport().toViewport(collisionBox);
			if (origin == null) {
				return;
			}
			g.drawImage(m_view.getBank().getSprite(spriteSetNumber, currentSpriteNumber), origin.x, origin.y,
					(int) (collisionBox.getWidth() * m_view.getViewport().getScale()),
					(int) (collisionBox.getHeight() * m_view.getViewport().getScale()), null);
		}
	}
	
	/**
	 * affichage des hitbox de l'avatar en mode debug (differe en fonction de l'avatar)
	 * @param g
	 */
	abstract void debugPaint(Graphics g);

}
