package view;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

/**
 * Classe utilisé comme banque de sprite pour les avatars
 */
public class SpriteBank {

	private View m_view;

	private LinkedList<SpriteSet> spritesBank;

	/**
	 * Initialise la banque de sprite
	 * 
	 * @param m_view : instance courante de la view
	 */
	SpriteBank(View m_view) {
		this.m_view = m_view;
		m_view.setBank(this);
		this.spritesBank = new LinkedList<SpriteSet>();
		loadSpritesSets();
		resizeBackground();
	}

	/**
	 * Met a jour la taille du background en fonction de la taille de l'écran
	 */
	private void resizeBackground() {
		SpriteSet background = getBackgroundset();
		BufferedImage sprite = background.getSprite(0);
		double xRatio = (double) sprite.getWidth() / (double)m_view.getScreenWidth();
		double yRatio = (double) sprite.getHeight() / (double) m_view.getScreenHeight();
		double ratio = Math.min(xRatio, yRatio);
		double newWidth = sprite.getWidth();
		double newHeight = sprite.getHeight();
		int offset = sprite.getMinX();
		if (xRatio > ratio) {
			newWidth = (int) ((ratio * sprite.getWidth()) / xRatio);
			offset = (int) ((sprite.getWidth() - newWidth) / 2);
		}
		if (yRatio > ratio)
			newHeight = (int) ((ratio * sprite.getHeight()) / yRatio);
		BufferedImage newSprite = sprite.getSubimage(offset, sprite.getMinY(), (int) newWidth, (int) newHeight);
		background.setSprite(0, newSprite);
	}

	/**
	 * Renvoie l'emplacement du spriteset dans la banque de sprite en fonction de
	 * son type dans la config
	 * 
	 * @param fileName
	 * @return
	 */
	int getSpritesSetNumber(String entityType) {
		String fileName = getconfStr(entityType, "spriteFile");
		for (int i = 0; i < spritesBank.size(); i++) {
			if (spritesBank.get(i).getName().equals(fileName)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Charge les spriteset de la config dans la banque de sprite
	 */
	void loadSpritesSets() {

		String currentFile = "";
		try {
			/* background */
			currentFile = getconfStr("World", "spriteFile");
			spritesBank.add(0, loadSprite(currentFile, 1, 1, new Color(getconfInt("World", "debugColor"))));

			/* fixedBackground */
			currentFile = getconfStr("World", "fixedBackgroundSprite");

			spritesBank.add(1,
					loadSprite(currentFile, 1, 1, new Color(getconfInt("World", "fixedBackgroundDebugColor"))));

			/* fixedBackground */
			currentFile = getconfStr("World", "backgroundOverlaySprite");

			spritesBank.add(2,
					loadSprite(currentFile, 1, 1, new Color(getconfInt("World", "backgroundOverlayDebugColor"))));

			/* player1 */
			currentFile = getconfStr("Player1", "spriteFile");
			spritesBank.add(loadSprite(currentFile, getconfInt("World", "spriteNrows"),
					getconfInt("World", "spriteNcols"), new Color(getconfInt("Player1", "debugColor"))));
			/* player2 */
			currentFile = getconfStr("Player2", "spriteFile");
			spritesBank.add(loadSprite(currentFile, getconfInt("World", "spriteNrows"),
					getconfInt("World", "spriteNcols"), new Color(getconfInt("Player2", "debugColor"))));
			/* block */
			currentFile = getconfStr("Obstacle", "spriteFile");
			spritesBank.add(loadSprite(currentFile, getconfInt("World", "spriteNrows"),
					getconfInt("World", "spriteNcols"), new Color(getconfInt("Obstacle", "debugColor"))));
			/* Mobs */
			for (int i = 0; i < getconfInt("World", "nbBots"); i++) {
				currentFile = getconfStr("Mob" + i, "spriteFile");
				spritesBank.add(loadSprite(currentFile, getconfInt("World", "spriteNrows"),
						getconfInt("World", "spriteNcols"), new Color(getconfInt("Mob" + i, "debugColor"))));
			}
		} catch (IOException e) {
			System.err.println("Erreur de chargement pour " + currentFile);
		}
	}

	/**
	 * Charge un spriteset à partir d'un fichier
	 * 
	 * @param filename   : nom du fichier
	 * @param nrows      : nombre de lignes de sprites
	 * @param ncols      : nombre de colonnes de sprites
	 * @param debugColor : couleur de debug
	 * @return : le spriteset
	 * @throws IOException
	 */
	public static SpriteSet loadSprite(String filename, int nrows, int ncols, Color debugColor) throws IOException {
		File imageFile = new File("sprites/" + filename);
		if (imageFile.exists()) {
			BufferedImage image = ImageIO.read(imageFile);
			int width = image.getWidth(null) / ncols;
			int height = image.getHeight(null) / nrows;

			BufferedImage[] images = new BufferedImage[nrows * ncols];
			for (int i = 0; i < nrows; i++) {
				for (int j = 0; j < ncols; j++) {
					int x = j * width;
					int y = i * height;
					images[(i * ncols) + j] = image.getSubimage(x, y, width, height);
				}
			}

			return new SpriteSet(images, filename, debugColor);
		}
		return new SpriteSet(null, filename, debugColor);
	}

	/**
	 * Cherche le sprite d'un avatar dans la banque de sprite
	 * 
	 * @param avatar    : avatar dont on veut le sprite
	 * @param numSprite : numéro du sprite souhaité parmi le spriteset
	 * @return : le sprite de l'avatar
	 */
	BufferedImage getSprite(int numSetSprite, int numSprite) {
		return spritesBank.get(numSetSprite).getSprite(numSprite);

	}

	/**
	 * Renvoie la couleur de debug du spriteset
	 * 
	 * @param numSetSprite
	 * @return
	 */
	Color getDebugColor(int numSetSprite) {
		return spritesBank.get(numSetSprite).getDebugColor();
	}

	/**
	 * Retourne le spriteset de l'arrière plan
	 * 
	 * @return
	 */
	SpriteSet getBackgroundset() {
		return spritesBank.get(0);
	}

	/**
	 * Retourne le spriteset du background fixe
	 * 
	 * @return
	 */
	SpriteSet getFixedBackgroundSet() {
		return spritesBank.get(1);
	}

	/**
	 * Retourne le spriteset de l'overlay du background
	 * 
	 * @return
	 */
	SpriteSet getBackgroundOverlaySet() {
		return spritesBank.get(2);
	}

	/**
	 * Raccourci pour trouver des elements string dans la config
	 * 
	 * @param elem
	 * @param param
	 * @return
	 */
	private String getconfStr(String elem, String param) {
		return m_view.getController().getConfig().getStringValue(elem, param);
	}

	/**
	 * Raccourci pour trouver des element int dans la config
	 * 
	 * @param elem
	 * @param param
	 * @return
	 */
	private int getconfInt(String elem, String param) {
		return m_view.getController().getConfig().getIntValue(elem, param);
	}

}
