package model;

import java.util.Random;

import controller.Controller;
import view.View;

public class SpawnTest {
	public static void main(String[] args) {
		Controller cntrl = Controller.getInstance();
		View v = new View(cntrl);
		Model mod = new Model(cntrl, v);
		while (true) {
			Random yesOrNotRand = new Random();
			int yesOrNot = yesOrNotRand.nextInt(100000);
			if (yesOrNot < 3) {
				mod.step();
			}
		}
	}
}
