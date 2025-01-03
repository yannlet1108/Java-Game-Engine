package automaton;

/**
 * Classe caracterisant l'etat d'une entite
 */
public class State {

	private String name;

	State(String name) {
		this.name = name;
	}

	String getName() {
		return name;
	}

	boolean equals(State state) {
		return state.getName().equals(name);
	}

	@Override
	public String toString() {
		return name;
	}
}
