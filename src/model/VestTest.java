package model;

import controller.Controller;
import view.View;
import java.awt.geom.Point2D;

public class VestTest {
	public static void main(String[] args) {
		Controller cntrl = Controller.getInstance();
		View v = new View(cntrl);
		Model mod = new Model(cntrl, v);
		Point2D pts = new Point2D.Double(100, 100);
		Player P1 = new Player(pts, Direction.N, mod, 1);
		int x = 100;
		while (x > 0) {
			System.out.println("Density = " + P1.density + " ,Oxygen = " + P1.getOxygen() + " ,Air in vest = "
					+ P1.vest.getVestAir() + " Health = " + P1.getHealthPoint());
			P1.step();
			P1.pop(-1);
			x -= 10;
		}
		x = 100;
		System.out.println("------------------------------------------------");
		while (x > 0) {
			System.out.println("Density = " + P1.density + " ,Oxygen = " + P1.getOxygen() + " ,Air in vest = "
					+ P1.vest.getVestAir() + " ,Health = " + P1.getHealthPoint());
			P1.step();
			P1.pop(1);
			x -= 10;
		}
	}
}
