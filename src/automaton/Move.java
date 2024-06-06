package automaton;

import model.Entity;
import model.Direction;

public class Move implements Action {

	Direction dir;

	Move(Direction dir) {
		this.dir = dir;
	}

	@Override
	public void exec(Entity e) {
		e.move(dir);
	}

}
