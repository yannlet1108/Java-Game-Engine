package automaton;

import model.Entity;
import model.Direction;

/**
 * Classe de l'action move
 */
class Move implements Action {

	private Direction dir;

	/**
	 * Parametre une action de mouvement
	 * 
	 * @param dir : Direction cible
	 */
	Move(Direction dir) {
		this.dir = dir;
	}

	/**
	 * Demande à l'entité d'exécuter l'action
	 */
	@Override
	public void exec(Entity e) {
		e.doMove(dir);
		return;
	}

}
