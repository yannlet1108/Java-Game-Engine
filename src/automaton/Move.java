package automaton;

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
