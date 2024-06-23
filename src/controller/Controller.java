package controller;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import java.util.*;

import config.Config;
import info3.game.graphics.GameCanvasListener;
import model.Model;
import model.ModelConstants;
import view.View;

/**
 * Classe liant l'integralité des packages. Initialise les objets nécessaire au
 * fonctionnement du jeu. Contient les listeners sur la souris et le clavier.
 * Contient les listeners sur le canvas
 */
public class Controller implements GameCanvasListener {
	View m_view;
	Model m_model;
	Config m_config;
	private long tick;
	private List<Integer> keyList;

	/**
	 * Crée une instance du controller. Initialise la view et le model
	 */
	private Controller() {
		m_config = new Config("config.json");
		m_view = new View(this);
		m_model = new Model(this, m_view);
		this.tick = 0;
		this.keyList = new LinkedList<Integer>();
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
		// System.out.println(" modifiers=" + e.getModifiersEx());
		// System.out.println(" buttons=" + e.getButton());
	}

	@Override
	public void mouseExited(MouseEvent e) {
		System.out.println("Mouse exited: (" + e.getX() + "," + e.getY() + ")");
		// System.out.println(" modifiers=" + e.getModifiersEx());
		// System.out.println(" buttons=" + e.getButton());
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		System.out.println("Mouse dragged: (" + e.getX() + "," + e.getY() + ")");
		System.out.println("   modifiers=" + e.getModifiersEx());
		System.out.println("   buttons=" + e.getButton());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		/*
		 * System.out.println("Mouse moved: (" + e.getX() + "," + e.getY() + ")");
		 * System.out.println("   modifiers=" + e.getModifiersEx());
		 * System.out.println("   buttons=" + e.getButton());
		 */
	}

	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println("Key typed: " + e.getKeyChar() + " code=" + e.getKeyCode());
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("Key pressed: " + e.getKeyChar() + " code=" + e.getKeyCode());
		if (!keyList.contains(e.getKeyCode())) {
			keyList.add(e.getKeyCode());
		}
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
		if ((m_view != null) && (m_model != null))
			m_view.tick(elapsed);
		if (tick >= ModelConstants.PHYSICS_STEP_DELAY.toMillis()) {
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
	public Model getmodel() {
		return m_model;
	}

	public boolean isKeyPressed(String keyString) {
		int keyCode = getKeyCodeFromString(keyString);
		for (Integer code : keyList) {
			if (keyCode == code) {
				return true;
			}
		}
		return false;
	}

	public int getKeyCodeFromString(String keyString) {
		switch (keyString.toUpperCase()) {
		case "A":
			return KeyEvent.VK_A;
		case "B":
			return KeyEvent.VK_B;
		case "C":
			return KeyEvent.VK_C;
		case "D":
			return KeyEvent.VK_D;
		case "E":
			return KeyEvent.VK_E;
		case "F":
			return KeyEvent.VK_F;
		case "G":
			return KeyEvent.VK_G;
		case "H":
			return KeyEvent.VK_H;
		case "I":
			return KeyEvent.VK_I;
		case "J":
			return KeyEvent.VK_J;
		case "K":
			return KeyEvent.VK_K;
		case "L":
			return KeyEvent.VK_L;
		case "M":
			return KeyEvent.VK_M;
		case "N":
			return KeyEvent.VK_N;
		case "O":
			return KeyEvent.VK_O;
		case "P":
			return KeyEvent.VK_P;
		case "Q":
			return KeyEvent.VK_Q;
		case "R":
			return KeyEvent.VK_R;
		case "S":
			return KeyEvent.VK_S;
		case "T":
			return KeyEvent.VK_T;
		case "U":
			return KeyEvent.VK_U;
		case "V":
			return KeyEvent.VK_V;
		case "W":
			return KeyEvent.VK_W;
		case "X":
			return KeyEvent.VK_X;
		case "Y":
			return KeyEvent.VK_Y;
		case "Z":
			return KeyEvent.VK_Z;
		case "0":
			return KeyEvent.VK_0;
		case "1":
			return KeyEvent.VK_1;
		case "2":
			return KeyEvent.VK_2;
		case "3":
			return KeyEvent.VK_3;
		case "4":
			return KeyEvent.VK_4;
		case "5":
			return KeyEvent.VK_5;
		case "6":
			return KeyEvent.VK_6;
		case "7":
			return KeyEvent.VK_7;
		case "8":
			return KeyEvent.VK_8;
		case "9":
			return KeyEvent.VK_9;
		case "SPACE":
			return KeyEvent.VK_SPACE;
		case "ENTER":
			return KeyEvent.VK_ENTER;
		case "FU":
			return KeyEvent.VK_UP;
		case "FD":
			return KeyEvent.VK_DOWN;
		case "FR":
			return KeyEvent.VK_RIGHT;
		case "FL":
			return KeyEvent.VK_LEFT;
		default:
			throw new IllegalArgumentException("Unknown key: " + keyString);
		}
	}

	public Config getConfig() {
		return m_config;
	}

}
