package automaton;

import java.awt.Point;

import model.Direction;
import model.Entity;
import model.Model;
import model.Snake;

public class Tests {

	public static void main(String[] args) {

		Model model = new Model(10);
		Point pos = new Point(0, 0);
		Entity snake = new Snake(pos, Direction.N, model);

		printStep(snake);

	}

	private static void printPosition(Entity e) {
		System.out.println("L'entité est aux coordonnées suivantes : " + e.getX() + "," + e.getY());
	}

	private static void printStep(Entity e) {
		System.out.println("Avant le pas, le serpent est aux coordonnées suivantes : " + e.getX() + "," + e.getY());
		e.step();
		System.out.println("Après le pas, le serpent est aux coordonnées suivantes : " + e.getX() + "," + e.getY());
	}

}
