package controller;

public class Main {
	/**
	 * Cree et lance l'instance du Controller. L'affichage garantie le bon
	 * deroulement de la creation et de l'initialisation de l'instance.
	 * 
	 * @param args : Parametres du programme principal
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
