package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class SpriteBank {

	View m_view;

	SpriteBank(View m_view) {
		this.m_view = m_view;
		m_view.setBank(this);
	}
	
	public void paintShape(Graphics g, int index, Color c, int x, int y) {
		g.setColor(c);
		int x_reel = ViewCst.X_MARGIN + x * ViewCst.CELL_SIZE;
		int y_reel = ViewCst.Y_MARGIN + y * ViewCst.CELL_SIZE;
		switch(index) {
		case 0:
			g.fillRect(x_reel+ViewCst.CELL_MARGIN, y_reel+ViewCst.CELL_MARGIN, ViewCst.CELL_SIZE-2*ViewCst.CELL_MARGIN, ViewCst.CELL_SIZE-2*ViewCst.CELL_MARGIN);
			break;
		case 1:
			g.fillOval(x_reel+ViewCst.CELL_MARGIN, y_reel+ViewCst.CELL_MARGIN, ViewCst.CELL_SIZE-2*ViewCst.CELL_MARGIN, ViewCst.CELL_SIZE-2*ViewCst.CELL_MARGIN);
			break;
		case 2:
			int x_[] = {x_reel+ViewCst.CELL_MARGIN+(ViewCst.CELL_SIZE/2), x_reel+ViewCst.CELL_MARGIN, x_reel+ViewCst.CELL_MARGIN+ViewCst.CELL_SIZE};
			int y_[] = {y_reel+ViewCst.CELL_MARGIN, y_reel+ViewCst.CELL_MARGIN+ViewCst.CELL_SIZE, y_reel+ViewCst.CELL_MARGIN+ViewCst.CELL_SIZE};
			Polygon triangle = new Polygon(x_, y_, 3);
			g.fillPolygon(triangle);
			break;
		default:
			throw new RuntimeException("Not yet implemented");
		}
	}
	
}
