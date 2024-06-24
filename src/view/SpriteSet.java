package view;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class SpriteSet {
	private BufferedImage[] img;
	private String name;
	private Color debugColor;

	/**
	 * Constructeur de SpriteSet
	 * 
	 * @param img        : Set de sprites
	 * @param name       : nom du fichier de sprites
	 * @param debugColor : couleur de debug
	 */
	public SpriteSet(BufferedImage[] img, String name, Color debugColor) {
		this.img = img;
		this.name = name;
		this.debugColor = debugColor;
	}

	/**
	 * Retourne le sprite à l'indice i
	 * 
	 * @param i : indice du sprite
	 * @return le sprite à l'indice i
	 */
	public BufferedImage getSprite(int i) {
		return img[i];
	}

	/**
	 * Retourne le nom du fichier de sprites
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Retourne la couleur de debug
	 * 
	 * @return
	 */
	public Color getDebugColor() {
		return debugColor;
	}

	/**
	 * Redéfinit un sprite post-création du spriteset (utilisé par le background)
	 * 
	 * @param i   : indice du sprite
	 * @param img : nouvelle buffered image
	 */
	void setSprite(int i, BufferedImage img) {
		this.img[i] = img;
	}

}
