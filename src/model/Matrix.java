package model;

public class Matrix {
	Entity[][] prvMatrix;

	/*
	 * Crée une matrice Paramétres : numberOfColumns, numberOfRows. Retourne une
	 * matrice de numberOfRows lignes et numberOfColumns colonnes Author : Moataz
	 * ERRAMI :)
	 */
	public Matrix(int numberOfColumns, int numberOfRows) {
		prvMatrix = new Entity[numberOfRows][numberOfColumns];
	}

	/*
	 * Paramétres : position x, position y, retourne l'entité à (x,y) Author :
	 * Moataz ERRAMI :)
	 */
	public Entity getEntityAt(int x, int y) {
		return prvMatrix[x][y];
	}

	/*
	 * Paramétres : position x, position y, entité e, fixer l'entité e à (x,y)
	 * Author : Moataz ERRAMI :)
	 */
	public void setEntityAt(int x, int y, Entity e) {
		prvMatrix[x][y] = e;
	}
}
