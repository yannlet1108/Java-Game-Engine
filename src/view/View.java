package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.Controller;
import info3.game.graphics.GameCanvas;
import model.Model;

/**
 * Classe principale de la view. S'occupe de relier la liste des avatars à la
 * banque d'avatar, ainsi de de maintenir a jour la fenêtre d'afiichage
 */
public class View {

	private Model m_model;
	private Controller m_controller;
	private GameCanvas m_canvas;
	private JFrame m_frame;
	private JLabel m_text;

	private long m_textElapsed;

	private List<Avatar> avatarStorage;
	private List<Avatar> toRemoveList;
	private SpriteBank bank;
	private Viewport viewport;

	private Rectangle2D refillArea;

	/**
	 * Initialise la view, ouvre la fenêtre graphique, la liste d'avatar et la
	 * banque d'avatar
	 * 
	 * @param m_controller : Instance courante du controller
	 */
	public View(Controller m_controller) {
		new ViewCst(m_controller.getConfig());
		m_canvas = new GameCanvas(m_controller);
		this.m_controller = m_controller;

		System.out.println("  - creating frame...");
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		m_frame = m_canvas.createFrame(d);
		System.out.println("  - setting up the frame...");
		setupFrame();
		initViewport();
		new SpriteBank(this);
		initAvatar();
		initRefillArea();
	}

	/**
	 * Paramètre la fenêtre d'affichage. La plupart des paramètres sont modifiable
	 * dans la méthode directement
	 */
	private void setupFrame() {
		m_frame.setTitle("Game");
		m_frame.setLayout(new BorderLayout());

		m_frame.add(m_canvas, BorderLayout.CENTER);

		m_text = new JLabel();
		m_textElapsed = 0;
		m_text.setText("Tick: 0ms FPS=0");
		m_frame.add(m_text, BorderLayout.NORTH);

		m_frame.setLocationRelativeTo(null);

		m_frame.setVisible(true);
	}

	/**
	 * Met à jour l'affichage de debug present en haut de la fenêtre graphique
	 * 
	 * @param elapsed : Temps écoule en ms depuis le dernier tick.
	 */
	public void tick(long elapsed) {
		m_textElapsed += elapsed;
		if (m_textElapsed > 1000) {
			m_textElapsed = 0;
			float period = m_canvas.getTickPeriod();
			int fps = m_canvas.getFPS();

			String txt = "Tick=" + period + "ms";
			while (txt.length() < 15)
				txt += " ";
			txt = txt + fps + " fps   ";
			m_text.setText(txt);
		}
	}

	/**
	 * Initialise le viewport
	 */
	private void initViewport() {
		this.viewport = new Viewport(m_canvas);
	}

	/**
	 * Initialise la liste d'avatar et sa liste de suppression
	 */
	private void initAvatar() {
		avatarStorage = new LinkedList<Avatar>();
		toRemoveList = new LinkedList<Avatar>();
	}

	/**
	 * Initialise la zone de refill du monde
	 */
	private void initRefillArea() {
		this.refillArea = new Rectangle2D.Double(m_controller.getConfig().getIntValue("World", "width") / 2, 0,
				m_controller.getConfig().getIntValue("World", "shipSize"),
				m_controller.getConfig().getIntValue("World", "shipSize"));
	}

	/**
	 * Enregistre un avatar dans la liste des avatar existante
	 * 
	 * @param a : nouvel avatar à sauvegarder
	 */
	public void storeAvatar(Avatar a) {
		avatarStorage.add(a);
	}

	public void addToRemove(Avatar a) {
		toRemoveList.add(a);
	}

	/**
	 * Retire de la liste d'avatar les avatars de ToRemoveList
	 */
	public void destroyToRemoves() {
		Iterator<Avatar> it = toRemoveList.iterator();
		while (it.hasNext()) {
			avatarStorage.remove(it.next());

		}
	}

	/**
	 * Affiche l'integralité des éléments à afficher
	 * 
	 * @param g : instance graphique du canvas
	 */
	public void paint(Graphics g) {
		if (m_model != null && m_model.getPlayers().size() > 0) {

			viewport.updateViewport(getFarthestPlayers(m_model.getPlayersPos()));
			viewport.resize();
			fillBackground(g);
			drawRefillZone(g);
			Iterator<Avatar> avatarIterator = getAvatarIterator();
			while (avatarIterator.hasNext()) {
				avatarIterator.next().paint(g);
			}
		}
		destroyToRemoves();
	}

	/**
	 * Remplit le background a chaque repaint
	 * 
	 * @param g : instance graphique du canvas
	 */
	private void fillBackground(Graphics g) {
		BufferedImage background = bank.getBackgroundset().getSprite(0);
		float scale = getBackgroundScale();
		Point origin = getBackgroundPos(scale);
		System.out.println("x : " + origin.x + ", y : " + origin.y + ", scale : " + scale);
		g.drawImage(background, origin.x, origin.y, (int) (background.getWidth() * scale),
				(int) (background.getHeight() * scale), null);
	}

	/**
	 * Calcule le coeficient allant de 0 à 1 permettant de calculer la taille du
	 * background
	 * 
	 * @return coeficient permettant le scaling du background
	 */
	private double getScaleRatio() {
		Point2D tops[] = getFarthestPlayers(m_model.getPlayersPos());
		double xRatio = (tops[0].getX() - tops[1].getX()) / getSimWidth();
		double yRatio = (tops[0].getY() - tops[1].getY()) / getSimHeight();
		return Math.max(xRatio, yRatio);
	}

	/**
	 * Adapte le ratio en fonction du coeficient parallaxale
	 * 
	 * @param ratio : ratio entre 0 et 1 résultat de getScaleRatio()
	 * @return convertie en parallaxe
	 */
	private float ratioToParallax(double ratio) {
		return ((1 - (float) (ratio)) * ViewCst.PARALLAX);
	}

	/**
	 * Calcule le scale du background basé sur son raw scale et sur le coeficient de
	 * remplisage du viewport adapté au parallaxe courant
	 * 
	 * @return scale exacte du background
	 */
	private float getBackgroundScale() {
		float rawScale = getScreenHeight() / bank.getBackgroundset().getSprite(0).getHeight();
		return rawScale + rawScale * ratioToParallax(getScaleRatio());
	}

	/**
	 * Calcule la position du background en fonction du scaling du background
	 * 
	 * @param scaleb : scaling du background
	 * @return point origine de l'affichage du background
	 */
	private Point getBackgroundPos(float scale) {
		BufferedImage background = bank.getBackgroundset().getSprite(0);
		Point2D tops[] = getFarthestPlayers(m_model.getPlayersPos());
		double xRatio = tops[1].getX() / getSimWidth();
		double yRatio = tops[1].getY() / getSimHeight();

		double diffWidth = (background.getWidth() * scale - viewport.getWidth()) * xRatio;
		double diffHeight = (background.getHeight() * scale - viewport.getHeight()) * yRatio;
		double x = -diffWidth;
		double y = -diffHeight;
		return new Point((int) x, (int) y);
	}

	/**
	 * Retourne les extréminés du rectangle contenant tout les joueurs
	 * 
	 * @param playersPos : positions des joueurs
	 * @return deux points, un étant le point bas droit et l'autre étant haut gauche
	 *         du rectangle englobant tout les joueurs
	 */
	private Point2D[] getFarthestPlayers(Iterator<Point2D> playersPos) {
		Point2D tops[] = new Point2D.Double[2];
		Point2D current = playersPos.next();
		double xMax = current.getX();
		double xMin = current.getX();
		double yMax = current.getY();
		double yMin = current.getY();
		while (playersPos.hasNext()) {
			current = playersPos.next();
			if (current.getX() > xMax)
				xMax = current.getX();
			if (current.getX() < xMin)
				xMin = current.getX();
			if (current.getY() > yMax)
				yMax = current.getY();
			if (current.getY() < yMin)
				yMin = current.getY();
		}
		if (xMax > getSimWidth() - ViewCst.MARGIN)
			xMax = getSimWidth() - ViewCst.MARGIN;
		if (xMin < ViewCst.MARGIN)
			xMin = ViewCst.MARGIN;
		if (yMax > getSimHeight() - ViewCst.MARGIN)
			yMax = getSimHeight() - ViewCst.MARGIN;
		if (yMin < ViewCst.MARGIN)
			yMin = ViewCst.MARGIN;
		Point2D max = new Point2D.Double(xMax, yMax);
		Point2D min = new Point2D.Double(xMin, yMin);
		tops[0] = max;
		tops[1] = min;
		return tops;
	}

	/**
	 * Dessine la zone de refill avec un sprite ou une couleur de debug
	 * 
	 * @param g : instance graphique du canvas
	 */
	private void drawRefillZone(Graphics g) {
		Point origin = getViewport().toViewport(refillArea);
		if (origin == null)
			return;
		if (ViewCst.DEBUG) {
			g.setColor(getBank().getShipSet().getDebugColor());
			g.fillRect(origin.x, origin.y, (int) (refillArea.getWidth() * getViewport().getScale()),
					(int) (refillArea.getHeight() * getViewport().getScale()));
		} else {
			BufferedImage sprite = getBank().getShipSet().getSprite(0);
			g.drawImage(sprite, origin.x, origin.y, (int) (refillArea.getWidth() * getViewport().getScale()),
					(int) (refillArea.getHeight() * getViewport().getScale()), null);
		}

	}

	/**
	 * Enregistre l'instance courante du model
	 * 
	 * @param m_model : instance courante du model
	 */
	public void setModel(Model m_model) {
		this.m_model = m_model;
	}

	/**
	 * Stocke la banque des sprites dans la view
	 * 
	 * @param sb : banque des sprites chargée
	 */
	public void setBank(SpriteBank sb) {
		bank = sb;
	}

	/**
	 * Crée un iterateur sur les avatars
	 * 
	 * @return iterateur sur une copie de la liste d'avatar
	 */
	private Iterator<Avatar> getAvatarIterator() {
		LinkedList<Avatar> avatars = new LinkedList<Avatar>(avatarStorage);
		return avatars.iterator();
	}

	/**
	 * Retourne l'instance courante du controller
	 * 
	 * @return instance du controller
	 */
	Controller getController() {
		return m_controller;
	}

	/**
	 * Renvois la banque d'avatar
	 * 
	 * @return banque d'avatar
	 */
	SpriteBank getBank() {
		return bank;
	}

	/**
	 * Renvois le viewport actuel
	 * 
	 * @return viewport
	 */
	Viewport getViewport() {
		return viewport;
	}

	/**
	 * Retourne la hauteur de la simulation
	 * 
	 * @return taille de la simulation sur les abcisses
	 */
	double getSimHeight() {
		return m_model.getBoardHeight();
	}

	/**
	 * Retourne la largeur de la simulation
	 * 
	 * @return taille de la simulation sur les ordonnées
	 */
	double getSimWidth() {
		return m_model.getBoardWidth();
	}

	int getScreenWidth() {
		return Toolkit.getDefaultToolkit().getScreenSize().width;
	}

	int getScreenHeight() {
		return Toolkit.getDefaultToolkit().getScreenSize().height;
	}
}
