package automaton;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import model.Entity;
import model.Direction;
import model.Category;

/**
 * Classe de generation et de gestion des automates de controle des entite.
 * 
 * @implNote TODO Penser a faire un fichier a part pour les variables globale
 */
public class Automaton {

	private State currentState;
	private List<Transition> transitions;

	private Entity e;

	public static final int SNAKE = 0;
	public static final int APPLE = 1;
	public static final int BLOCK = 2;

	/**
	 * Cree l'automate en fonction du type d'entite.
	 * 
	 * @param e              : Entite proprietaire de l'automate
	 * @param whichAutomaton : Numero de l'automate defini
	 */
	public Automaton(Entity e, int whichAutomaton) {
		this.e = e;
		load(whichAutomaton);
	}

	/**
	 * Charge les transitions de l'automate selectionne au chargement et
	 * l'initialise.
	 * 
	 * @param whichAutomaton : Numero de l'automate defini
	 */
	private void load(int whichAutomaton) {
		switch (whichAutomaton) {
		case SNAKE:
			currentState = State.FORWARD;
			transitions = snakeTransitions();
			break;
		case APPLE:
			currentState = State.WAIT;
			transitions = appleTransitions();
			break;
		case BLOCK:
			currentState = State.WAIT;
			transitions = blockTransitions();
		default:
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Version de l'automate choisi pour chaque type d'entités
	 */
	private List<Transition> snakeTransitions() {
		return snakeTransitionsAroundTheMap();
	}

	/**
	 * Charge les transitions liee aux entites de type apple
	 * 
	 * @return : Liste des transitions
	 */
	private List<Transition> appleTransitions() {
		return stayWaiting();
	}

	/**
	 * Charge les transitions liee aux entites de type block
	 * 
	 * @return : Liste des transitions
	 */
	private List<Transition> blockTransitions() {
		return stayWaiting();
	}

	/**
	 * Fait un pas dans l'automate
	 */
	public void step() {
		for (Iterator<Transition> iterator = transitions.iterator(); iterator.hasNext();) {
			Transition transition = (Transition) iterator.next();
			if (transition.start == currentState) {
				if (transition.cond.eval(e)) {
					currentState = transition.end;
					// si pas d'action, on exécute rien
					if (transition.action != null) {
						transition.action.exec(e);
					}
					System.out.println("Après le pas, le serpent est aux coordonnées suivantes : " + e.getX() + ","
							+ e.getY() + "\n");
					return;
				}
			}
		}
		throw new IllegalStateException("Aucune transition de l'état courant n'est valide");
	}

	// * * * * * * * * * Différentes versions d'automates de Snake * * * * * * * * *

	/**
	 * Premier automate basique de snake avec des directions relatives
	 */
	private List<Transition> snakeTransitionsRelatives() {
		// Création de la liste des transitions
		List<Transition> transitions = new LinkedList<Transition>();

		// Définition de variables pour les États
		State waitState = State.WAIT;
		State forwardState = State.FORWARD;
		// State deadState = State.DEAD;

		// Définition de variables pour les Directions
		Direction forward = Direction.FORWARD;

		// Définition de variables pour les Catégories
		Category voidCategory = Category.VOID;
		Category obstacle = Category.OBSTACLE;

		transitions.add(new Transition(forwardState, new Cell(forward, voidCategory), new Move(forward), forwardState));
		transitions.add(new Transition(forwardState, new Cell(forward, obstacle), null, waitState));
		transitions.add(new Transition(waitState, new TrueCondition(), null, waitState));

		return transitions;
	}

	/**
	 * Premier automate basique de snake avec des directions absolues
	 */
	private List<Transition> snakeTransitionsAbsolues() {
		// Création de la liste des transitions
		List<Transition> transitions = new LinkedList<Transition>();

		// Définition de variables pour les États
		State waitState = State.WAIT;
		State forwardState = State.FORWARD;
		// State deadState = State.DEAD;

		// Définition de variables pour les Directions
		Direction north = Direction.N;
		// Direction east = Direction.E;

		// Définition de variables pour les Catégories
		Category voidCategory = Category.VOID;
		Category obstacle = Category.OBSTACLE;

		transitions.add(new Transition(forwardState, new Cell(north, voidCategory), new Move(north), forwardState));
		// transitions.add(new Transition(forwardState, new Cell(north, obstacle), null,
		// waitState));
		transitions.add(new Transition(forwardState, new Negation(new Cell(north, voidCategory)), null, waitState));
		transitions.add(new Transition(waitState, new TrueCondition(), null, waitState));

		return transitions;
	}

	/**
	 * Automate de snake qui fait le tour du terrain
	 */
	private List<Transition> snakeTransitionsAroundTheMap() {
		// Création de la liste des transitions
		List<Transition> transitions = new LinkedList<Transition>();

		// Définition de variables pour les États
		State waitState = State.WAIT;
		State forwardState = State.FORWARD;
		// State deadState = State.DEAD;

		// Définition de variables pour les Directions
		Direction forward = Direction.FORWARD;
		Direction right = Direction.RIGHT;
		// Direction left = Direction.LEFT;

		// Définition de variables pour les Catégories
		Category voidCategory = Category.VOID;
		Category obstableCategory = Category.OBSTACLE;

		// Condition
		Condition mustTurnRightCondition = new Conjonction(new Cell(forward, obstableCategory),
				new Cell(right, voidCategory));

		transitions.add(new Transition(forwardState, new Negation( new Cell(forward, obstableCategory)), new Move(forward), forwardState));
		transitions.add(new Transition(forwardState, mustTurnRightCondition, new Move(right), forwardState));
		// transitions.add(new Transition(forwardState, new TrueCondition(), null,
		// waitState));
		// transitions.add(new Transition(waitState, new TrueCondition(), null,
		// waitState));

		return transitions;
	}

	/**
	 * Automate de snake plus complet qui évite les obstacles et meurt si il ne peut
	 * aller nulle part
	 */
	private List<Transition> snakeTransitionsComplet() {
		// Création de la liste des transitions
		List<Transition> transitions = new LinkedList<Transition>();

		// Définition de variables pour les États
		State waitState = State.WAIT;
		State forwardState = State.FORWARD;
		State deadState = State.DEAD;

		// Définition de variables pour les Directions
		Direction forward = Direction.FORWARD;
		Direction right = Direction.RIGHT;
		Direction left = Direction.LEFT;

		// Définition de variables pour les Catégories
		Category voidCategory = Category.VOID;
		Category obstacle = Category.OBSTACLE;

		/*
		 * * * * * * * * * Ajout des différentes transitions de l'automate * * * * * * *
		 */
		// si libre en face, on avance
		transitions.add(new Transition(waitState, new Cell(forward, voidCategory), new Move(forward), forwardState));
		transitions.add(new Transition(forwardState, new Cell(forward, voidCategory), new Move(forward), forwardState));

		// si occupé en face et libre à droite, on tourne à droite
		transitions.add(new Transition(forwardState,
				new Conjonction(new Cell(forward, obstacle), new Cell(right, voidCategory)), new Move(forward),
				forwardState));

		// si occupé en face et libre à gauche, on tourne à gauche
		transitions.add(
				new Transition(forwardState, new Conjonction(new Cell(forward, obstacle), new Cell(left, voidCategory)),
						new Move(forward), forwardState));

		// si occupé en face, à droite et à gauche, on meurt
		transitions.add(new Transition(forwardState,
				new Conjonction(new Conjonction(new Cell(forward, obstacle), new Cell(right, obstacle)),
						new Cell(left, obstacle)),
				new Explode(), deadState));

		return transitions;
	}

	// * * * * * * * * * Différentes versions d'automates de Apple * * * * * * * * *
	// rien de spécial pour l'instant

	// * * * * * * * * * Différentes versions d'automates de Block * * * * * * * * *

	/**
	 * Si les 4 blocks autour sont vides, le block descend d'une case ("tombe")
	 */
	private List<Transition> blockTransitionsFall() {
		// Création de la liste des transitions
		List<Transition> transitions = new LinkedList<Transition>();

		// Définition de variables pour les États
		State waitState = State.WAIT;

		// Définition de variables pour les Directions
		Direction south = Direction.S;

		// Définition de variables pour les Catégories

		// Si les 4 blocks autour sont vides, le block descend d'une case ("tombe")
		transitions.add(new Transition(waitState, areFourDirectionsFree(), new Move(south), waitState));

		return transitions;
	}

	// * * * * * * * * * * * * Automates généraux * * * * * * * * * * * *
	/**
	 * Garde l'automate en état WAIT, sans rien faire
	 */
	private List<Transition> stayWaiting() {
		List<Transition> transitions = new LinkedList<Transition>();
		transitions.add(new Transition(State.WAIT, new TrueCondition(), null, State.WAIT));
		return transitions;
	}

	// * * * * * * * * * * * * Fonctions Utiles * * * * * * * * * * * *

	/**
	 * Return False si on ne peut ni aller tout droit, ni à droite, ni à gaauche
	 */
	private Condition isAMovePossible() {
		Condition isFrontFreeCondition = new Cell(Direction.FORWARD, Category.VOID);
		Condition isRightFreeCondition = new Cell(Direction.RIGHT, Category.VOID);
		Condition isLeftFreeCondition = new Cell(Direction.LEFT, Category.VOID);

		return new Disjonction(new Disjonction(isFrontFreeCondition, isRightFreeCondition), isLeftFreeCondition);
	}

	/**
	 * Retourne la conditions résultante de la Conjonction de Cell(Direction,Void)
	 * sur les 4 points cardinaux
	 */
	private Condition areFourDirectionsFree() {
		Condition isNorthFreeCondition = new Cell(Direction.N, Category.VOID);
		Condition isSouthFreeCondition = new Cell(Direction.S, Category.VOID);
		Condition isEastFreeCondition = new Cell(Direction.E, Category.VOID);
		Condition isWestFreeCondition = new Cell(Direction.W, Category.VOID);

		return new Conjonction(new Conjonction(isNorthFreeCondition, isSouthFreeCondition),
				new Conjonction(isEastFreeCondition, isWestFreeCondition));
	}

}
