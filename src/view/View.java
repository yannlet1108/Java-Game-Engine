package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
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

	private Rectangle2D fixedBackgroundArea;

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
		setBank(new SpriteBank(this));
		initAvatar();
		initFixedBackgroundArea();
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
	private void initFixedBackgroundArea() {
		this.fixedBackgroundArea = new Rectangle2D.Double(
				getController().getConfig().getIntValue("World", "fixedBackgroundXOffset"),
				getController().getConfig().getIntValue("World", "fixedBackgroundYOffset"),
				getController().getConfig().getIntValue("World", "fixedBackgroundWidth"),
				getController().getConfig().getIntValue("World", "fixedBackgroundHeight"));
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
		if (m_model != null)
			viewport.updateViewport(m_model.getPlayersPos());
		viewport.resize();
		fillBackground(g);
		drawfixedBackground(g);
		Iterator<Avatar> avatarIterator = getAvatarIterator();
		while (avatarIterator.hasNext()) {
			avatarIterator.next().paint(g);
		}
		destroyToRemoves();
	}

	/**
	 * Remplit le background a chaque repaint
	 * 
	 * @param g : instance graphique du canvas
	 */
	private void fillBackground(Graphics g) {
		g.setColor(getBank().getBackgroundset().getDebugColor());
		g.fillRect(0, 0, viewport.getWidth(), viewport.getHeight());

		// TODO implementer une version fonctionnelle avec une image scalée
	}

	/**
	 * Dessine la zone de refill avec un sprite ou une couleur de debug
	 * 
	 * @param g : instance graphique du canvas
	 */
	private void drawfixedBackground(Graphics g) {
		Point origin = getViewport().toViewport(fixedBackgroundArea);
		if (origin == null)
			return;
		if (ViewCst.DEBUG) {
			g.setColor(getBank().getShipSet().getDebugColor());
			g.fillRect(origin.x, origin.y, (int) (fixedBackgroundArea.getWidth() * getViewport().getScale()),
					(int) (fixedBackgroundArea.getHeight() * getViewport().getScale()));
		} else {
			BufferedImage sprite = getBank().getShipSet().getSprite(0);
			g.drawImage(sprite, origin.x, origin.y, (int) (fixedBackgroundArea.getWidth() * getViewport().getScale()),
					(int) (fixedBackgroundArea.getHeight() * getViewport().getScale()), null);
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
	 * Renvois le controller actuel
	 * 
	 * @return controller
	 */
	Controller getController() {
		return m_controller;
	}

}
