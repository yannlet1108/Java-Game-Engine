package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.util.PriorityQueue;
import java.util.Queue;

import model.Direction;
import model.Entity;

/**
 * Classe réunissant les champs et méthodes communes à tout les avatars
 */
public abstract class Avatar {

	protected View m_view;

	protected Entity instanceEntity;
	protected boolean isVisible;

	protected int spriteSetNumber; // numero de set de sprite

	private model.State lastState;

	private Queue<Integer> animationSprite; // file de sprites pour l'animation

	private Color debugColor;

	/**
	 * Crée et initialise les champs communs des avatars. Doit être appellé par une
	 * sous-classe charge le set de sprites associé à l'entité
	 * 
	 * @param m_view     : instance courante de la view
	 * @param e          : entité associé à l'avatar
	 * @param entityType : numero de l'entité dans la config
	 */
	public Avatar(View m_view, Entity e, String entityConfig) {
		this.m_view = m_view;
		instanceEntity = e;
		spriteSetNumber = m_view.getBank().getSpritesSetNumber(entityConfig);
		debugColor = m_view.getBank().getDebugColor(spriteSetNumber);
		this.animationSprite = new PriorityQueue<Integer>();
		m_view.store(this);
		setInvisible();
	}

	/**
	 * Retourne le prochain sprite à afficher dans la liste d'animation
	 * 
	 * @return numero du sprite
	 */
	int getNextSpriteNumber() {
		if (animationSprite.isEmpty()) {
			return 0;
		}
		return animationSprite.poll();
	}

	/**
	 * Met à jour la file d'animation en fonction de l'état de l'entité Relance
	 * l'animation liée à l'état si necessaire
	 */
	void updateAnimations() {
		model.State newState = instanceEntity.getState();
		if (newState != lastState) {
			lastState = newState;
			if (lastState == model.State.HITTING
					|| lastState == model.State.HITTING_AROUND && lastState != model.State.DYING) {
				animationSprite.clear();
			}
			addAnimation(lastState);
		} else {
			if (animationSprite.isEmpty()) {
				if (lastState != model.State.HITTING && lastState != model.State.HITTING_AROUND
						&& lastState != model.State.DYING) {
					addAnimation(lastState);
				}
			}
		}

	}

	/**
	 * Determine si l'avatar est orienté vers la droite pouur le choix des sprites
	 * 
	 * @return true si l'avatar est orienté vers la droite
	 */
	Boolean isRightSided() {
		Direction dir = instanceEntity.getDirection();
		if (dir == Direction.E || dir == Direction.SE || dir == Direction.NE) {
			return true;
		}
		return false;
	}

	/**
	 * Ajoute les sprites correspondant à l'état de l'entité dans la file
	 * d'animation
	 * 
	 * @param state : état de l'entité
	 */
	void addAnimation(model.State state) {
		int diff = 0;
		if (!isRightSided()) {
			diff = 24;
		}
		switch (state) {
		case WAITING:
			animationSprite.add(0 + diff);
			animationSprite.add(0 + diff);
			animationSprite.add(1 + diff);
			animationSprite.add(1 + diff);
			break;

		case DYING:
			animationSprite.add(diff + 4);
			animationSprite.add(diff + 4);
			animationSprite.add(diff + 5);
			animationSprite.add(diff + 5);
			animationSprite.add(diff + 6);
			animationSprite.add(diff + 6);
			animationSprite.add(diff + 7);
			animationSprite.add(diff + 7);
			break;

		case EMPTYING:
			animationSprite.add(diff + 20);
			animationSprite.add(diff + 20);
			break;

		case FILLING:
			animationSprite.add(diff + 21);
			animationSprite.add(diff + 21);
			break;

		case HITTING:
			animationSprite.add(diff + 12);
			animationSprite.add(diff + 12);
			animationSprite.add(diff + 13);
			animationSprite.add(diff + 14);
			animationSprite.add(diff + 15);
			break;
		case HITTING_AROUND:
			animationSprite.add(diff + 16);
			animationSprite.add(diff + 17);
			animationSprite.add(diff + 18);
			animationSprite.add(diff + 19);
			break;

		case MOVING:
			animationSprite.add(diff + 8);
			animationSprite.add(diff + 9);
			animationSprite.add(diff + 10);
			animationSprite.add(diff + 11);
			break;

		case REFILLING:
			animationSprite.add(diff + 22);
			animationSprite.add(diff + 23);
			break;

		default:
			break;
		}
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
	 * affichage en hitbox si mode debug activé en sprite sinon
	 * 
	 * @param g : instance graphique du canvas
	 */
	void paint(Graphics g) {
		if (ViewCst.DEBUG) {
			debugPaint(g);
		} else {
			updateAnimations();
			Rectangle2D collisionBox = instanceEntity.getHitbox();
			Point origin = m_view.getViewport().toViewport(collisionBox);
			if (origin == null) {
				return;
			}
			g.drawImage(m_view.getBank().getSprite(spriteSetNumber, getNextSpriteNumber()), origin.x, origin.y,
					(int) (collisionBox.getWidth() * m_view.getViewport().getScale()),
					(int) (collisionBox.getHeight() * m_view.getViewport().getScale()), null);
		}
		if (ViewCst.UID) {
			uidPaint(g);
		}
	}
	
	void uidPaint(Graphics g) {
		Rectangle2D collisionBox = instanceEntity.getHitbox();
		Point origin = m_view.getViewport().toViewport(collisionBox);
		if(origin == null) {
			return;
		}
		g.setColor(Color.BLACK);
		g.drawString("Speed: " + instanceEntity.getSpeed(), origin.x, origin.y+ g.getFontMetrics().getHeight());
		g.drawString("Force: " + instanceEntity.getForce(), origin.x, origin.y + g.getFontMetrics().getHeight()*2);
		g.drawString("Position: " + "(" + instanceEntity.getX() + "," + instanceEntity.getY() + ")", origin.x,
				origin.y + g.getFontMetrics().getHeight() * 3);
	}

	/**
	 * affichage des hitbox de l'avatar en mode debug (differe en fonction de
	 * l'avatar)
	 * 
	 * @param g
	 */
	abstract void debugPaint(Graphics g);

}
