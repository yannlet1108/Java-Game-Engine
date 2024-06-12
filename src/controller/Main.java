package controller;

public class Main {
	/**
	 * Crée et lance l'instance du Controller. L'affichage garantie le bon
	 * déroulement de la création et de l'initialisation de l'instance
	 * 
	 * @param args : Paramètres du programme principal
	 */
	public static void main(String args[]) {
		try {
			System.out.println("Game starting...");
			Controller.getInstance();
			System.out.println("Game started.");
		} catch (Throwable th) {
			th.printStackTrace(System.err);
		}
	}
}
