package automaton;

import model.Entity;
import model.Direction;

/**
 * Classe de l'action move
 */
public class Move implements Action {

	private Direction dir;
	private boolean isDirectionPrecised;

	Move() {
		isDirectionPrecised = false;
	}

	/**
	 * Parametre une action de mouvement
	 * 
	 * @param dir : Direction cible
	 */
	Move(Direction dir) {
		this.dir = dir;
		isDirectionPrecised = true;
	}

	/**
	 * Ne pas exécuter direct
	 */
	@Override
	public void exec(Entity e) {
		/*
		if (isDirectionPrecised) {
			e.move(dir);
		} else {
			e.move();
		}
		*/
		return;
	}

}
