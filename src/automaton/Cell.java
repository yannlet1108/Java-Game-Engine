package automaton;

import model.Entity;
import model.Direction;
import model.Category;

/**
 * Classe de la condition cell
 */
public class Cell implements Condition {

	Direction dir;
	Category cat;

	/**
	 * Parametre une condition cell.
	 * 
	 * @param dir : Direction a evaluer
	 * @param cat : Categorie de l'entite a evaluer
	 */
	Cell(Direction dir, Category cat) {
		this.dir = dir;
		this.cat = cat;
	}

	/**
	 * Evalue la cellule en fonction des parametres
	 * 
	 * TO DO : Voir aveec le modèle comment faire
	 */
	@Override
	public boolean eval(Entity e) {
		//return e.cell(dir, cat);
		return false;
	}

}
