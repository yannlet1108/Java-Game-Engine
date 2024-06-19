package automaton;

/**
 * Classe caracterisant l'etat d'une entite
 */
public class State {

	String name;

	State(String name) {
		this.name = name;
	}
	
	String getName() {
		return name;
	}

	boolean equals(State state) {
		return state.getName().equals(name);
	}
}
