package automaton;

public class Cell implements Condition {

	Direction dir;
	Category cat;

	public Cell(Direction dir, Category cat) {
		this.dir = dir;
		this.cat = cat;
	}

	@Override
	public boolean eval(Entity e) {
		return e.cell(dir, cat);
	}

}
