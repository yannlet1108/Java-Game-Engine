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
		e.doHit(dir);
	}

	@Override
	public String toString() {
		if (dir == null) {
			return "Hit";
		}
		return "Hit(" + dir.toString() + ")";
	}

}
