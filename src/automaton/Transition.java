package automaton;

public class Transition {

	State start;
	Condition cond;
	Action action;
	State end;

	Transition(State start, Condition cond, Action action, State end) {
		this.start = start;
		this.cond = cond;
		this.action = action;
		this.end = end;
	}

}
