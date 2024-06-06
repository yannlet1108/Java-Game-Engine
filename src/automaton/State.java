package automaton;

/**
 * Classe caracterisant l'etat d'une entite
 */
public class State {

	public static final int WAIT = 0;
	public static final int FORWARD = 1;
	public static final int DEAD = 2;

	int state;

	/**
	 * Cree un etat
	 * 
	 * @param state : valeur equivalente de l'etat
	 */
	State(int state) {
		this.state = state;
	}
}
