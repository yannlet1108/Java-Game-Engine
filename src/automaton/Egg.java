package automaton;

import model.Direction;
import model.Entity;

class Egg implements Action {

	private Direction dir;

	Egg(Direction dir) {
		this.dir = dir;
	}

	@Override
	public void exec(Entity e) {
		if (dir == Direction.UNDERSCORE) {
			Direction randomDirection = dir.getRandomDirection();
			e.doEgg(randomDirection);
		} else {
			e.doEgg(dir);
		}
	}

	@Override
	public String toString() {
		if (dir == null) {
			return "Egg";
		}
		return "Egg(" + dir.toString() + ")";
	}

}
