package controller;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import java.util.*;

import info3.game.graphics.GameCanvasListener;
import view.View;

public class Controller implements GameCanvasListener {

	Controller m_controller;
	View m_view;
	Model m_model;
	private long tick;
	List keyList;

	public Controller() {
		m_controller = this;
		m_view = new View(this);
		m_model = new Model(this, m_view);
		this.tick = 0;
		this.keyList = new List<int>;
	}

	public static Controller getInstance() {
		return new Controller();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Mouse clicked: (" + e.getX() + "," + e.getY() + ")");
		System.out.println("   modifiers=" + e.getModifiersEx());
		System.out.println("   buttons=" + e.getButton());

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Mouse pressed: (" + e.getX() + "," + e.getY() + ")");
		System.out.println("   modifiers=" + e.getModifiersEx());
		System.out.println("   buttons=" + e.getButton());

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Mouse released: (" + e.getX() + "," + e.getY() + ")");
		System.out.println("   modifiers=" + e.getModifiersEx());
		System.out.println("   buttons=" + e.getButton());

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
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
		keyList.remove(e.getKeyCode());

	}

	@Override
	public void windowOpened() {
		// TODO Auto-generated method stub

	}

	@Override
	public void tick(long elapsed) {
		// TODO Auto-generated method stub
		m_view.tick(elapsed);
		if ((elapsed - tick) >= 1000) {
			m_model.tick();
			tick = elapsed;
		}
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		m_view.paint(g);
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void endOfPlay(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void expired() {
		// TODO Auto-generated method stub

	}

}
