package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.PriorityQueue;
import java.util.Queue;

import model.Direction;
import model.Entity;
import model.State;

/**
 * Classe réunissant les champs et méthodes des avatars d'entités
 */
public class Avatar {

	private View m_view;

	private Entity instanceEntity;
	private boolean isVisible;

	private int spriteSetNumber; // numero de set de sprite

	private model.State lastState;

	private Queue<Integer> animationSprite; // file de sprites pour l'animation

	private Color debugColor;

	/**
	 * Crée et initialise les champs communs des avatars. Doit être appellé par une
	 * sous-classe charge le set de sprites associé à l'entité
	 * 
	 * @param m_view       : instance courante de la view
	 * @param e            : entité associé à l'avatar
	 * @param entityConfig : nom du type entité dans la config
	 */
	public Avatar(View m_view, Entity e, String entityConfig) {
		this.m_view = m_view;
		instanceEntity = e;
		spriteSetNumber = m_view.getBank().getSpritesSetNumber(entityConfig);
		debugColor = m_view.getBank().getDebugColor(spriteSetNumber);
		this.animationSprite = new PriorityQueue<Integer>();
		m_view.storeAvatar(this);
		lastState = State.WAITING;
		setVisible();
	}

	/**
	 * Retourne le prochain sprite à afficher dans la liste d'animation Si la liste
	 * est vide, on la remplit avec les sprites d'état de base
	 * 
	 * @return numero du sprite
	 */
	int getNextSpriteNumber() {
		if (animationSprite.isEmpty()) {
			addAnimation(State.WAITING);
		}
		return animationSprite.poll();
	}

	/**
	 * Met à jour la file d'animation en fonction de l'état de l'entité Relance
	 * l'animation liée à l'état si necessaire
	 * 
	 */
	private void updateAnimations() {
		model.State newState = instanceEntity.getState();
		if (newState != lastState) {
			lastState = newState;
			if (lastState == model.State.HITTING || lastState == model.State.HITTING_AROUND
					|| lastState == model.State.DYING) {
				animationSprite.clear();
			}
			addAnimation(lastState);
		} else {
			if (animationSprite.isEmpty()) {
				if (lastState == model.State.DYING) {
					m_view.addToRemove(this);
					setInvisible();
				}
				if (lastState != model.State.HITTING && lastState != model.State.HITTING_AROUND) {
					addAnimation(lastState);
				}
			}
		}

	}

	/**
	 * Determine si l'avatar est orienté vers la droite pour le choix des sprites
	 * 
	 * @return true si l'avatar est orienté vers l'est relatif, false sinon
	 */
	private boolean isRightSided() {
		Direction dir = instanceEntity.getDirection();
		if (dir == Direction.E || dir == Direction.SE || dir == Direction.NE) {
			return true;
		}
		return false;
	}

	/**
	 * Ajoute les sprites correspondant à l'état de l'entité dans la file
	 * d'animation Les numéros de sprites sont déterminés en fonction de la table
	 * d'animation de spriteset
	 * 
	 * @param state : état de l'entité
	 */
	private void addAnimation(model.State state) {
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
	 * Rend l'avatar visible sur la fenêtre
	 */
	void setVisible() {
		isVisible = true;
	}

	/**
	 * Rend l'avatar invisible sur la fenêtre
	 */
	void setInvisible() {
		isVisible = false;
	}

	/**
	 * Affiche l'avatar en fonction du viewport et des coordonnées de l'entité
	 * affichage en hitbox si mode debug activé en sprite sinon affichage des
	 * informations de l'entité si mode uid activé
	 * 
	 * @param g : instance graphique du canvas
	 */
	void paint(Graphics g) {
		if (isVisible) {
			if (ViewCst.DEBUG) {
				debugPaint(g);
			} else {
				updateAnimations();
				Rectangle2D collisionBox = instanceEntity.getHitbox();
				Point origin = m_view.getViewport().toViewport(collisionBox);
				if (origin == null) {
					return;
				}
				BufferedImage sprite = m_view.getBank().getSprite(spriteSetNumber, getNextSpriteNumber());
				g.drawImage(sprite, origin.x, origin.y,
						(int) (collisionBox.getWidth() * m_view.getViewport().getScale()),
						(int) (collisionBox.getHeight() * m_view.getViewport().getScale()), null);
			}
			if (ViewCst.UID) {
				uidPaint(g);
			}
		}
	}

	/**
	 * Arrondi la valeur à un nombre de décimales donné
	 * 
	 * @param value            : valeur à arrondir
	 * @param numberOfDecimals : nombre de décimales
	 * @return valeur arrondie
	 */
	private double roundValue(double value, int numberOfDecimals) {
		return Math.round(value * Math.pow(10, numberOfDecimals)) / Math.pow(10, numberOfDecimals);
	}

	/**
	 * Affiche les informations de l'entité en mode uid
	 * 
	 * @param g : instance graphique du canvas
	 */
	private void uidPaint(Graphics g) {
		Rectangle2D collisionBox = instanceEntity.getHitbox();
		Point origin = m_view.getViewport().toViewport(collisionBox);
		if (origin == null) {
			return;
		}
		int numberOfDecimals = 2;
		g.setColor(Color.BLACK);

		g.setFont(new Font("SansSerif", Font.PLAIN, 11));
		// affichage du nom de l'entité
		g.drawString("Name: " + instanceEntity.toString(), origin.x, origin.y + g.getFontMetrics().getHeight());
		// affichage de la position
		g.drawString(
				"Position: " + "(" + roundValue(instanceEntity.getX(), numberOfDecimals) + ","
						+ roundValue(instanceEntity.getY(), numberOfDecimals) + ")",
				origin.x, origin.y + g.getFontMetrics().getHeight() * 2);
		// affichage de la vitesse
		g.drawString("Speed: " + instanceEntity.getSpeed(), origin.x, origin.y + g.getFontMetrics().getHeight() * 3);
		// affichage de la force
		g.drawString("Force: " + instanceEntity.getForce(), origin.x, origin.y + g.getFontMetrics().getHeight() * 4);
		// affichage de la densité
		g.drawString("Density: " + roundValue(instanceEntity.getDensity(), numberOfDecimals), origin.x,
				origin.y + g.getFontMetrics().getHeight() * 5);
		// affichage des points de vie
		g.drawString("Hp: " + instanceEntity.getHealthPoint(), origin.x, origin.y + g.getFontMetrics().getHeight() * 6);
		// affichage du niveau d'oxygen des joueurs
		if (instanceEntity instanceof model.Player) {
			g.drawString("Oxygen: " + roundValue(((model.Player) instanceEntity).getOxygen(), numberOfDecimals),
					origin.x, origin.y + g.getFontMetrics().getHeight() * 7);
		}
		// affichage de la direction de l'entité
		g.drawString("Direction: " + instanceEntity.getDirection(), origin.x,
				origin.y + g.getFontMetrics().getHeight() * 8);
		// affichage de l'état de l'entité
		g.drawString("State: " + instanceEntity.getState(), origin.x, origin.y + g.getFontMetrics().getHeight() * 9);
	}

	/**
	 * affichage des hitbox de l'avatar en mode debug avec sa couleur de debug
	 * associée
	 * 
	 * @param g
	 */
	private void debugPaint(Graphics g) {
		Rectangle2D collisionBox = instanceEntity.getHitbox();
		g.setColor(debugColor);
		Point origin = m_view.getViewport().toViewport(collisionBox);
		if (origin == null)
			return;
		g.fillRect(origin.x, origin.y, (int) (collisionBox.getWidth() * m_view.getViewport().getScale()),
				(int) (collisionBox.getHeight() * m_view.getViewport().getScale()));
	};

}
