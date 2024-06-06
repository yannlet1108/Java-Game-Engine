package automaton;

public class State {

	public static final int WAIT = 0;
	public static final int FORWARD = 1;
	public static final int DEAD = 2;

	int state;

	State(int state) {
		this.state = state;
	}
}
