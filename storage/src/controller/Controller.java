package controller;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import java.util.*;

import info3.game.graphics.GameCanvasListener;
import model.Model;
import view.View;

/**
 * Classe liant l'integralite des packages. Initialise les objets necessaire au
 * fonctionnement du jeu. Contient les listeners sur la souris et le clavier.
 * Contient les listeners sur le GameCanvas.
 */
public class Controller implements GameCanvasListener {

	Controller m_controller;
	View m_view;
	Model m_model;
	private long tick;
	List<Integer> keyList;

	/**
	 * Cree une instance du Controller. Initialise la view et le model.
	 */
	public Controller() {
		m_controller = this;
		m_view = new View(this);
		m_model = new Model(this, m_view);
		this.tick = 0;
		this.keyList = new LinkedList<Integer>();
	}

	/**
	 * Cree une instance du Controller et la retourne
	 * 
	 * @return Nouvelle instance de Controller
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
		System.out.println("   modifiers=" + e.getModifiersEx());
		System.out.println("   buttons=" + e.getButton());
	}

	@Override
	public void mouseExited(MouseEvent e) {
		System.out.println("Mouse exited: (" + e.getX() + "," + e.getY() + ")");
		System.out.println("   modifiers=" + e.getModifiersEx());
		System.out.println("   buttons=" + e.getButton());
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
	 * Listener appelle lors de l'ouverture d'une nouvelle fenetre graphique.
	 */
	@Override
	public void windowOpened() {
	}

	/**
	 * Listener appelle a chaque tick de jeu.
	 */
	@Override
	public void tick(long elapsed) {
		if (m_view != null)
			m_view.tick(elapsed);
		if (tick >= 1000) {
			if (m_model != null)
				m_model.step();
			tick = 0;
		}
		tick += elapsed;
	}

	/**
	 * Listener appelle lors de la mise a jour du contenu de la fenetre graphique.
	 * Depend du nombre de FPS defini dans GameCanvas
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

}
