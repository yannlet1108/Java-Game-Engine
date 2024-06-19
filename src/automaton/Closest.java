package automaton;

import model.Category;
import model.Direction;
import model.Entity;

class Closest implements Condition {
	
	private Category cat;
	private Direction dir;
	
	Closest(Category cat, Direction dir) {
		this.cat = cat;
		this.dir = dir;
	}

	@Override
	public boolean eval(Entity e) {
		return e.doClosest(cat, dir);
	}
	
	@Override
	public String toString() {
		return "Closest(" + cat.toString() + "," + dir.toString() + ")";
	}

}
