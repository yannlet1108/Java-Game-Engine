package automaton;

import model.Direction;
import model.Entity;

class Throw implements Action {

	Direction dir;

	Throw(Direction dir) {
		this.dir = dir;
	}

	@Override
	public void exec(Entity e) {
		if (dir == Direction.UNDERSCORE) {
			Direction randomDirection = dir.getRandomDirection();
			e.doThrow(randomDirection);
		} else {
			e.doThrow(dir);
		}
	}
	
	@Override
	public String toString() {
		if (dir == null) {
			return "Throw";
		}
		return "Throw(" + dir.toString() + ")";
	}

}
