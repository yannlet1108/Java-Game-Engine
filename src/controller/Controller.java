package controller;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import java.util.*;

import info3.game.graphics.GameCanvasListener;
import model.Model;
import view.View;

/**
 * Classe liant l'integralité des packages. Initialise les objets nécessaire au
 * fonctionnement du jeu. Contient les listeners sur la souris et le clavier.
 * Contient les listeners sur le canvas
 */
public class Controller implements GameCanvasListener {

	Controller m_controller;
	View m_view;
	Model m_model;
	private long tick;
	private List<Integer> keyList;

	/**
	 * Crée une instance du controller. Initialise la view et le model
	 */
	private Controller() {
		m_controller = this;
		this.keyList = new LinkedList<Integer>();
		m_view = new View(this);
		m_model = new Model(this, m_view);
		m_view.initBackgroundSprite();
		this.tick = 0;
	}

	/**
	 * Crée une instance du controller et la retourne
	 * 
	 * @return nouvelle instance de controller
	 */
	public static Controller getInstance() {
		return new Controller();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Mouse clicked: (" + e.getX() + "," + e.getY() + ")");
		System.out.println("   modifiers=" + e.getModifiersEx());
		System.out.println("   buttons=" + e.getButton());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("Mouse pressed: (" + e.getX() + "," + e.getY() + ")");
		System.out.println("   modifiers=" + e.getModifiersEx());
		System.out.println("   buttons=" + e.getButton());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("Mouse released: (" + e.getX() + "," + e.getY() + ")");
		System.out.println("   modifiers=" + e.getModifiersEx());
		System.out.println("   buttons=" + e.getButton());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		System.out.println("Mouse entered: (" + e.getX() + "," + e.getY() + ")");
		System.out.println(" modifiers=" + e.getModifiersEx());
		System.out.println(" buttons=" + e.getButton());
	}

	@Override
	public void mouseExited(MouseEvent e) {
		System.out.println("Mouse exited: (" + e.getX() + "," + e.getY() + ")");
		System.out.println(" modifiers=" + e.getModifiersEx());
		System.out.println(" buttons=" + e.getButton());
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		System.out.println("Mouse dragged: (" + e.getX() + "," + e.getY() + ")");
		System.out.println("   modifiers=" + e.getModifiersEx());
		System.out.println("   buttons=" + e.getButton());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		System.out.println("Mouse moved: (" + e.getX() + "," + e.getY() + ")");
		System.out.println("   modifiers=" + e.getModifiersEx());
		System.out.println("   buttons=" + e.getButton());
	}

	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println("Key typed: " + e.getKeyChar() + " code=" + e.getKeyCode());
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("Key pressed: " + e.getKeyChar() + " code=" + e.getKeyCode());
		keyList.add(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println("Key released: " + e.getKeyChar() + " code=" + e.getKeyCode());
		keyList.remove((Object) e.getKeyCode());
	}

	/**
	 * Listener appellé lors de l'ouverture d'une nouvelle fenêtre graphique
	 */
	@Override
	public void windowOpened() {
	}

	/**
	 * Listener appellé à chaque tick de jeu. Exécute les ticks du model et de la
	 * view
	 * 
	 * @param elapsed : Temps écoulé depuis le dernier tick en ms
	 */
	@Override
	public void tick(long elapsed) {
		if (m_view != null)
			m_view.tick(elapsed);
		if (tick >= 100) {
			if (m_model != null)
				m_model.step();
			tick = 0;
		}
		tick += elapsed;
	}

	/**
	 * Listener appellé lors de la mise à jour du contenu de la fenêtre graphique.
	 * Depend du nombre de FPS defini dans canvas
	 */
	@Override
	public void paint(Graphics g) {
		if (m_view != null)
			m_view.paint(g);
	}

	@Override
	public void exit() {
	}

	@Override
	public void endOfPlay(String name) {
	}

	@Override
	public void expired() {
	}

	/**
	 * 
	 * @return le model
	 */
	public Model getM_model() {
		return m_model;
	}

}
