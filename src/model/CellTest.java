package model;

import java.awt.geom.Point2D;

import controller.Controller;
import view.View;

public class CellTest {
	public static void main(String args[]) {
		Controller cntrl = Controller.getInstance();
		View v = new View(cntrl);
		Model mod = new Model(cntrl, v);
		int rayon = 8;
		Point2D P = new Point2D.Double(28 , 74);
		Point2D P2 = new Point2D.Double(34 , 70);
		Category categ = Category.PLAYER;
		Player seeker = new Player(P, Direction.N, mod, 100);
		Player hider = new Player(P2, Direction.N, mod, 100);
		hider.category = Category.PLAYER;
		if(seeker.cell(Direction.N, categ, rayon))
			System.out.println("a Player found a " +categ+ " in the direction " + Direction.N);
		if(seeker.cell(Direction.S, categ, rayon))
			System.out.println("a Player found a " +categ+ " in the direction " + Direction.S);
		if(seeker.cell(Direction.E, categ, rayon))
			System.out.println("a Player found a " +categ+ " in the direction " + Direction.E);
		if(seeker.cell(Direction.W, categ, rayon))
			System.out.println("a Player found a " +categ+ " in the direction " + Direction.W);
		if(seeker.cell(Direction.NE, categ, rayon))
			System.out.println("a Player found a " +categ+ " in the direction " + Direction.NE);
		if(seeker.cell(Direction.NW, categ, rayon))
			System.out.println("a Player found a " +categ+ " in the direction " + Direction.NW);
		if(seeker.cell(Direction.SE, categ, rayon))
			System.out.println("a Player found a " +categ+ " in the direction " + Direction.SE);
		if(seeker.cell(Direction.SW, categ, rayon))
			System.out.println("a Player found a " +categ+ " in the direction " + Direction.SW);
	}
}
