package model;

import java.awt.geom.Point2D;
import java.util.Iterator;

import controller.Controller;
import view.View;

public class TestsModel {
	public static void main(String[] args) {
		Controller controller = Controller.getInstance();
		Model model = controller.getM_model();
		TestHP(controller, model);
	}

	public static void TestHP(Controller controller, Model model) {
		Entity p1 = new Player(model.getWorldCenter(), Direction.N, model);
		Point2D pos = model.getWorldCenter();
		pos.setLocation(pos.getX(), pos.getY() + 10);
		Entity p2 = new Player(pos, Direction.N, model);
		p2.team = 2;
		System.out.printf("Hp P1 : %d \n", p1.getHealthPoint());
		System.out.printf("Hp P2 : %d \n", p2.getHealthPoint());
		System.out.println("------------------------------------");
		p1.hit();
		p2.hit();
		System.out.printf("Hp P1 apres 1er coup: %d \n", p1.getHealthPoint());
		System.out.printf("Hp P2 apres 1er coup: %d \n", p2.getHealthPoint());
		System.out.println("------------------------------------");
		p1.hit(Direction.S);
		p1.hit(Direction.N);
		System.out.printf("Hp P1 apres 2eme coup: %d \n", p1.getHealthPoint());
		System.out.printf("Hp P2 apres 2eme coup: %d \n", p2.getHealthPoint());
		Entity g1 = new Goldfish(model.getWorldCenter(), Direction.N, model);
		System.out.println("------------------------------------");
		g1.hit();
		System.out.printf("Hp P1 apres 3eme coup: %d \n", p1.getHealthPoint());
		System.out.printf("Hp P2 apres 3eme coup: %d \n", p2.getHealthPoint());
		System.out.printf("Hp G1 apres 3eme coup: %d \n", g1.getHealthPoint());
		System.out.println("------------------------------------");
		pos.setLocation(pos.getX()+2, pos.getY());
		Entity s1 = new Shark(pos, Direction.N, model);
		s1.hit();
		System.out.printf("Hp P1 apres 4eme coup: %d \n", p1.getHealthPoint());
		System.out.printf("Hp P2 apres 4eme coup: %d \n", p2.getHealthPoint());
		System.out.printf("Hp S1 apres 4eme coup: %d \n", s1.getHealthPoint());
		System.out.printf("Hp G1 apres 4eme coup: %d \n", g1.getHealthPoint());
		System.out.printf("Nombre de joueur en vie : %d \n", model.getPlayers().size());
		System.out.println("------------------------------------");
		s1.hit();
		System.out.printf("Hp P1 apres 5eme coup: %d \n", p1.getHealthPoint());
		System.out.printf("Hp P2 apres 5eme coup: %d \n", p2.getHealthPoint());
		System.out.printf("Hp S1 apres 5eme coup: %d \n", s1.getHealthPoint());
		System.out.printf("Hp G1 apres 5eme coup: %d \n", g1.getHealthPoint());
		System.out.printf("Nombre de joueur en vie : %d \n", model.getPlayers().size());
		System.out.println("------------------------------------");
	}
}
