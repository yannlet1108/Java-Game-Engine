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
		if (dir == Direction.UNDERSCORE) {
			Direction randomDirection = dir.getRandomDirection();
			e.doMove(randomDirection);
		} else {
			e.doMove(dir);
		}
	}

	@Override
	public String toString() {
		if (dir == null) {
			return "Move";
		}
		return "Move(" + dir.toString() + ")";
	}

}
