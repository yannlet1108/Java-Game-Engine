package automaton;

import model.Entity;

public class TrueCondition implements Condition{

	public TrueCondition() {
		
	}
	
	@Override
	public boolean eval(Entity e) {
		return true;
	}

}
