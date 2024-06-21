package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.Controller;
import info3.game.graphics.GameCanvas;
import model.Entity;
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
	private SpriteBank bank;
	private Viewport viewport;

	/**
	 * Initialise la view, ouvre la fenêtre graphique, la liste d'avatar et la
	 * banque d'avatar
	 * 
	 * @param m_controller : Instance courante du controller
	 */
	public View(Controller m_controller) {

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
	 * Initialise la liste d'avatar
	 */
	private void initAvatar() {
		avatarStorage = new LinkedList<Avatar>();
	}

	/**
	 * Initialise le viewport
	 */
	private void initViewport() {
		this.viewport = new Viewport(m_canvas);
	}

	/**
	 * Enregistre un avatar dans la liste des avatar existante
	 * 
	 * @param a : nouvel avatar à sauvegarder
	 */
	public void store(Avatar a) {
		avatarStorage.add(a);
	}

	/**
	 * Detruit l'avatar d'une entité en cours de destruction
	 * 
	 * @param e : entité en cours de destruction
	 */
	public void destroy(Entity e) {
		Iterator<Avatar> avatarIterator = getAvatarIterator();
		Avatar current;
		while (avatarIterator.hasNext()) {
			current = avatarIterator.next();
			if (current.getEntity() == e) {
				avatarStorage.remove(current);
				return;
			}
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
		Iterator<Avatar> avatarIterator = getAvatarIterator();
		while (avatarIterator.hasNext()) {
			avatarIterator.next().paint(g);
		}
	}

	/**
	 * Remplit le background a chaque repaint
	 * 
	 * @param g : instance graphique du canvas
	 */
	private void fillBackground(Graphics g) {
		g.setColor(ViewCst.BACKGROUND_DEFAULT);
		g.fillRect(0, 0, viewport.getWidth(), viewport.getHeight());

		// TODO implementer une version fonctionnelle avec une image scalée
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
	 * @return iterateur sur la liste d'avatar
	 */
	private Iterator<Avatar> getAvatarIterator() {
		return avatarStorage.iterator();
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
	
	Controller getController() {
		return m_controller;
	}

}
