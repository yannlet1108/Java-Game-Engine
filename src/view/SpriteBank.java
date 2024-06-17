package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.management.InstanceAlreadyExistsException;

/**
 * Classe utilisé comme banque de sprite pour les avatars
 */
public class SpriteBank {

	private View m_view;
	
	private BufferedImage[][] spritebank;
	
	/**
	 * Initialise la banque de sprite
	 * 
	 * @param m_view : instance courante de la view
	 */
	SpriteBank(View m_view) {
		this.m_view = m_view;
		m_view.setBank(this);
		try {
			loadsprites();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Charge tous les sprites de la liste depuis les fichiers
	 * @throws IOException
	 */
	
	void loadsprites() throws IOException {
		for(int i = 0; i<ViewCst.SPRITES_FILES.length; i++) {
			spritebank[i]= loadSprite(ViewCst.SPRITES_FILES[i],ViewCst.SPRITES_NROWS[i],ViewCst.SPRITES_NCOLS[i]);
		}
	}
	
	/**
	 * Charge les sprites d'un fichier sous forme d'un tableau de sprites
	 * @param filename : nom du fichier contenant les sprites
	 * @param nrows	: nombre de lignes de sprites dans le fichier
	 * @param ncols : nombre de colonnes de sprites dans le fichier
	 * @return : le tableau des sprites du fichier
	 * @throws IOException
	 */
	public static BufferedImage[] loadSprite(String filename, int nrows, int ncols) throws IOException {
	    File imageFile = new File(filename);
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
	      return images;
	    }
	    return null;
	  }
	
	/**
	 * Cherche le sprite d'un avatar dans la banque de sprite
	 * @param avatar : avatar dont on veut le sprite
	 * @param numSprite : numéro du sprite souhaité parmi les sprites de l'avatar
	 * @return	: le sprite de l'avatar
	 */
	BufferedImage getSprite(Avatar avatar, int numSprite) {
			return spritebank[findAvatarType(avatar)][numSprite];
			
	}
	
	/**
	 * Retourne le sprite de l'arrière plan
	 */
	BufferedImage getBackground() {
		return spritebank[0][0];
	}
	
	
	/**
	 * Fait correspondre un avatar à son numéro de sprites dans la banque de sprites
	 * 0 : avatar inconnu (renvoie le numéro de l'arrirère plan)
	 * 1 : avatar joueur
	 * 2 : avatar obstacle
	 * 3 : avatar bullet
	 * 4 : avatar goldfish
	 * 5 : avatar shark
	 * 
	 * @param a : avatar dont on veut le numéro de sprites
	 * @return
	 */
	int findAvatarType(Avatar a) {
		if(a instanceof PlayerAvatar) {
			return 1;
		}
		if (a instanceof ObstacleAvatar) {
			return 2;
		}
		if (a instanceof BulletAvatar) {
			return 3;
		}
		if ( a instanceof GoldfishAvatar) {
			return 4;
		}
		if( a instanceof SharkAvatar) {
			return 5;
		}
		return 0;
	}
	

	/**
	 * Affiche la boite de collision de l'entité
	 * 
	 * @param g            : inctance graphique du canvas
	 * @param c            : couleur de la boite de collision
	 * @param collisionBox : coordonnées et taille de la boite de collision
	 */
	void debugCollisions(Graphics g, Color c, Rectangle2D collisionBox) {
		g.setColor(c);
		Point origin = m_view.getViewport().toViewport(new Point2D.Double(collisionBox.getX(), collisionBox.getY()));
		if(origin == null)
			return;
		g.drawRect(origin.x, origin.y, (int) (collisionBox.getWidth() * m_view.getViewport().getScale()),
				(int) (collisionBox.getHeight() * m_view.getViewport().getScale()));
	}
	
}
