package automaton;

import model.Entity;
import model.Direction;

/**
 * Classe de l'action move
 */
public class Move implements Action {

	Direction dir;

	/**
	 * Parametre une action de mouvement
	 * 
	 * @param dir : Direction cible
	 */
	Move(Direction dir) {
		this.dir = dir;
	}

	/**
	 * Execute le deplacement
	 */
	@Override
	public void exec(Entity e) {
		e.move(dir);
	}

}
