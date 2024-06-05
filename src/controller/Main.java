package controller;

public class Main {

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
