package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

/**
 * Classe contenant l'integralite des Sprites utilises dans le rpogramme
 * principal et necessaire a l'affichage.
 * 
 * @implNote TODO Arranger la liste des variables globale afin de
 *           modifier/refaire la methode paintShape plus proprement.
 */
public class SpriteBank {

	View m_view;

	SpriteBank(View m_view) {
		this.m_view = m_view;
		m_view.setBank(this);
	}

	/**
	 * Affiche une forme geometrique en fonction des parametres
	 * 
	 * @param g     : Objet de graphique contenant les parametrages deja effectues
	 *              dans le but de odifier la fenetre graphique
	 * @param index : Index de l'objet a afficher dans la liste des formes |
	 *              1=rectangle, 2=cercle, 3=triangle
	 * @param c     : Couleur de la forme
	 * @param x     : Abscisse de la forme
	 * @param y     : Ordonnee de la forme
	 */
	public void paintShape(Graphics g, int index, Color c, int x, int y) {
		g.setColor(c);
		Point viewpoint= m_view.playGroundtoWindow(m_view.modelToPlayground(new Point(x,y)));
		switch (index) {
		case 0:
			g.fillRect(viewpoint.x, viewpoint.y,m_view.getCellSize(),m_view.getCellSize());
			break;
		case 1:
			g.fillOval(viewpoint.x, viewpoint.y, m_view.getCellSize(), m_view.getCellSize());
		break;
		case 2:
			int x_[] = {viewpoint.x+m_view.getCellSize()/2, viewpoint.x, viewpoint.x+m_view.getCellSize() };
			int y_[] = {viewpoint.y, viewpoint.y+m_view.getCellSize(), m_view.getCellSize() };
			Polygon triangle = new Polygon(x_, y_, 3);
			g.fillPolygon(triangle);
			break;
		default:
			throw new RuntimeException("Not yet implemented");
		}
	}

}
