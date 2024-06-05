package controller;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import info3.game.graphics.GameCanvasListener;
import view.View;

public class Controller implements GameCanvasListener {

	Controller m_controller;
	View m_view;

	public Controller() {
		m_controller = this;
		m_view = new View(this);
	}

	public static Controller getInstance() {
		return new Controller();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened() {
		// TODO Auto-generated method stub

	}

	@Override
	public void tick(long elapsed) {
		// TODO Auto-generated method stub
		m_view.tick(elapsed);
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
