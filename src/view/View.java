package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.Controller;
import info3.game.graphics.GameCanvas;

public class View {

	Controller m_controller;
	GameCanvas m_canvas;
	JFrame m_frame;
	JLabel m_text;

	private long m_textElapsed;
	
	private LinkedList<Avatar> avatarStorage;
	private SpriteBank bank;


	public View(Controller m_controller) {

		this.m_controller = m_controller;

		m_canvas = new GameCanvas(m_controller);

		System.out.println("  - creating frame...");
		Dimension d = new Dimension(ViewCst.WIN_WIDTH, ViewCst.WIN_HEIGHT);
		m_frame = m_canvas.createFrame(d);
		System.out.println("  - setting up the frame...");
		setupFrame();
		initAvatar();
		setBank(new SpriteBank(this));
	}

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

	

	private void initAvatar() {
		avatarStorage = new LinkedList<Avatar>();
	}

	public void store(Avatar a) {
		avatarStorage.add(a);
	}

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

	public void paint(Graphics g) {
		fillPlayground(g);
		Iterator<Avatar> avatarIterator = getAvatarIterator();
		while (avatarIterator.hasNext()) {
			avatarIterator.next().paint(g);
		}
	}

	private void fillPlayground(Graphics g) {
		g.setColor(ViewCst.BACKGROUND_COLOR);
		g.fillRect(0, 0, ViewCst.WIN_WIDTH, ViewCst.WIN_HEIGHT);
		g.setColor(ViewCst.PLAYGROUND_COLOR);
		g.fillRect(ViewCst.X_MARGIN, ViewCst.Y_MARGIN, ViewCst.PLAYGROUND_WIDTH, ViewCst.PLAYGROUND_HEIGHT);
		
		//affichage du quadrillage
		g.setColor(ViewCst.LINE_COLOR);
		for(int i = 0;i < ViewCst.NB_CELL_HEIGHT; i++ ) {
			g.drawLine(0+ViewCst.X_MARGIN,i*ViewCst.CELL_HEIGHT+ViewCst.Y_MARGIN, ViewCst.X_MARGIN+ViewCst.PLAYGROUND_WIDTH, i*ViewCst.CELL_HEIGHT+ViewCst.Y_MARGIN);
			for(int j = 0; j < ViewCst.NB_CELL_WIDTH; j++) {
				g.drawLine(j*ViewCst.CELL_WIDTH+ViewCst.X_MARGIN,0+ViewCst.Y_MARGIN, j*ViewCst.CELL_WIDTH+ViewCst.X_MARGIN, ViewCst.PLAYGROUND_HEIGHT+ViewCst.Y_MARGIN);
			}
		}
	}

	
	public void setBank(SpriteBank sb) {
		bank = sb;
	}

	public void paintShape(Graphics g, int index, Color c, int x, int y) {
		bank.paintShape(g, index, c, x, y);
	}

	private Iterator<Avatar> getAvatarIterator() {
		return avatarStorage.iterator();
	}
	
	private Point playGroundtoWindow(Point p) {
		Point np = new Point(p);
		np.translate(ViewCst.X_MARGIN, ViewCst.Y_MARGIN);
		return np;
	}

}
