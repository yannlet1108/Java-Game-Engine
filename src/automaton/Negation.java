package automaton;

public class Negation  implements Condition {

	Condition cond;
	
	Negation(Condition cond){
		this.cond = cond;
	}

	@Override
	public boolean eval(Entity e) {
		return !cond.eval(e);
	}
}
