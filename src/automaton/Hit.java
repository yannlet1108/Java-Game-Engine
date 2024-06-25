package automaton;

import model.Direction;
import model.Entity;

class Hit implements Action {

	Direction dir;

	Hit(Direction dir) {
		this.dir = dir;
	}

	@Override
	public void exec(Entity e) {
		if (dir == Direction.UNDERSCORE) {
			Direction randomDirection = dir.getRandomDirection();
			e.doHit(randomDirection);
		} else {
			e.doHit(dir);
		}
	}

	@Override
	public String toString() {
		if (dir == null) {
			return "Hit";
		}
		return "Hit(" + dir.toString() + ")";
	}

}
