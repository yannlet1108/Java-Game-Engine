package automaton;

import model.Entity;
import model.Direction;
import model.Category;

/**
 * Classe de la condition cell
 */
class Cell implements Condition {

	private Direction dir;
	private Category cat;

	/**
	 * Constructeur de condition cell.
	 * 
	 * @param dir : Direction a evaluer
	 * @param cat : Categorie de l'entite a evaluer
	 */
	Cell(Direction dir, Category cat) {
		this.dir = dir;
		this.cat = cat;
	}

	/**
	 * Demande à l'entité d'évaluer la condition
	 */
	@Override
	public boolean eval(Entity e) {
		return e.doCell(dir, cat);
	}

}
