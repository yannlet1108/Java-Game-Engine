package model;

import java.awt.geom.Point2D;

import controller.Controller;
import view.View;

public class TestsModel {
	public static void main(String[] args) {
		TestHP();
	}

	public static void TestHP() {
		Controller controller = Controller.getInstance();
		Model model = new Model(controller, new View(controller));
		Entity p1 = new Player(model.getWorldCenter(), Direction.N, model, 100);
		Point2D pos = model.getWorldCenter();
		pos.setLocation(pos.getX(), pos.getY() + 10);
		Entity p2 = new Player(pos, Direction.N, model, 100);
		p2.team = 2;
		System.out.printf("Hp P1 : %d \n", p1.getHealthPoint());
		System.out.printf("Hp P2 : %d \n", p2.getHealthPoint());
		p1.hit();
		p2.hit();
		System.out.printf("Hp P1 apres 1er coup: %d \n", p1.getHealthPoint());
		System.out.printf("Hp P2 apres 1er coup: %d \n", p2.getHealthPoint());
		p1.hit(Direction.S);
		p1.hit(Direction.N);
		System.out.printf("Hp P1 apres 2eme coup: %d \n", p1.getHealthPoint());
		System.out.printf("Hp P2 apres 2eme coup: %d \n", p2.getHealthPoint());

	}
}
