package automaton;

import model.Entity;
import model.Direction;

public class Move implements Action {

	private Direction dir;
	private boolean isDirectionPrecised;

	Move() {
		isDirectionPrecised = false;
	}

	Move(Direction dir) {
		this.dir = dir;
		isDirectionPrecised = true;
	}

	@Override
	public void exec(Entity e) {
		if (isDirectionPrecised) {
			e.move(dir);
		} else {
			e.move();
		}
	}

}
