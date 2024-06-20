package model;

import controller.Controller;
import view.View;

public class PhysicsTest {
	public static void main(String[] args) throws InterruptedException {
		Controller controller = Controller.getInstance();
		Model model = new Model(controller, new View(controller));
		Entity playerEntity = new Player(model.getWorldCenter(), Direction.E, model, 1);
		model.addEntity(playerEntity);

		while (true) {
			System.out.println(String.format("Position : (%.2f, %.2f)\tVitesse : (%.2f, %.2f)", playerEntity.getX(),
					playerEntity.getY(), playerEntity.getSpeed().getX(), playerEntity.getSpeed().getY()));
			playerEntity.computeMovement();
			Thread.sleep(ModelConstants.PHYSICS_STEP_DELAY.toMillis());
		}
	}
}
