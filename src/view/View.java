package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.Controller;
import info3.game.graphics.GameCanvas;

public class View {
	Controller m_controller;
	//Model m_model;
	GameCanvas m_canvas;
	JFrame m_frame;
	JLabel m_text;

	private long m_textElapsed;

	//private List<Avatar> avatarStorage;
	//private SpriteBank bank;
	
	
	/**
	 * Initialise la view, la fenetre graphique, la liste d'avatar et la banque
	 * d'avatar.
	 * 
	 * @param m_controller : Instance courante du Controller
	 */
	public View(Controller m_controller) {

		this.m_controller = m_controller;

		m_canvas = new GameCanvas(m_controller);

		System.out.println("  - creating frame...");
		Dimension d = new Dimension(1024,768);
		m_frame = m_canvas.createFrame(d);
		System.out.println("  - setting up the frame...");
		setupFrame();
		//setBank(new SpriteBank(this));
		//initAvatar();
	}
	
	/**
	 * Parametre la fenetre d'affichage. La plupart des parametres sont
	 * modifiabledans la methode directement.
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
	 * Met a jour l'affichage de debug present en haut de la fenetre graphique.
	 * 
	 * @param elapsed : Temps ecoule en ms depuis le debut de la simulation.
	 */
	public void tick(long elapsed) {
		m_textElapsed += elapsed;
		if (m_textElapsed > 1000) {
			m_textElapsed = 0;
			float period = m_canvas.getTickPeriod();
			int fps = m_canvas.getFPS();

			String txt = "Tick=" + period + "ms";
			while (txt.length() < 15) txt += " ";
			txt = txt + fps + " fps   ";
			m_text.setText(txt);
		}
	}
	
	/**
	 * Initialise la liste d'avatar.
	 */
	private void initAvatar() {
		avatarStorage = new LinkedList<Avatar>();
	}

	/**
	 * Enregistre un avatar dans la liste des avatar existante.
	 * 
	 * @param a : Nouvel avatar a sauvegarder
	 */
	public void store(Avatar a) {
		avatarStorage.add(a);
	}

	/**
	 * Detruit l'avatar d'une entite en cours de destruction
	 * 
	 * @param e : Entite en cours de destruction
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
	 * Affiche l'integralite des elements a afficher.
	 * 
	 * @param g : Objet de graphique contenant les parametrages deja effectues dans
	 *          le but de odifier la fenetre graphique
	 */
	public void paint(Graphics g) {
		fillPlayground(g);
		Iterator<Avatar> avatarIterator = getAvatarIterator();
		while (avatarIterator.hasNext()) {
			avatarIterator.next().paint(g);
		}
	}
	
	/**
	 * Affiche l'arriere plan fixe du jeu.
	 * 
	 * @param g : Objet de graphique contenant les parametrages deja effectues dans
	 *          le but de odifier la fenetre graphique
	 */
	private void fillPlayground(Graphics g) {
		// Remplissage de la fenetre
		g.setColor(Color.gray);
		g.fillRect(0, 0, m_canvas.getWidth(), m_canvas.getHeight());

		// Remplissage du playground
		g.setColor(Color.cyan);
		g.fillRect(5,5, m_canvas.getWidth()-10, m_canvas.getHeight()-10);


		g.setColor(Color.RED);
		g.drawString("(" + m_frame.getSize().width + ", " + m_frame.getSize().height + ")", 5, 25);
	}
	
	/**
	 * Stocke la banque des Sprites dans la View
	 * 
	 * @param sb : Banque des Sprites chargee
	 */

	public void setBank(SpriteBank sb) {
		bank = sb;
	}
	
	/**
	 * Cree un iterateur sur les Avatars
	 * 
	 * @return Iterateur sur la liste d'Avatar
	 */
	private Iterator<Avatar> getAvatarIterator() {
		return avatarStorage.iterator();
	}
	
}
