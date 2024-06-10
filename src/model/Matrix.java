package model;

/**
 * @implNote TODO
 */
public class Matrix {
	private Entity[][] Matrix;

	/**
	 * Crée une matrice à la taille donnée
	 * 
	 * @param numberOfColumns
	 * @param numbrOfRows 
	 * @return la matrice crée
	 * @author Moataz ERRAMI
	 */
	public Matrix(int numberOfColumns, int numberOfRows) {
		Matrix = new Entity[numberOfRows][numberOfColumns];
	}

	/**
	 * Retourne l'entité à la position x,y
	 * @param x
	 * @param y
	 * @return entité à la position x,y
	 * @author Moataz ERRAMI
	 */
	public Entity getEntityAt(int x, int y) {
		return Matrix[x][y];
	}


	/**
	 * Met à jour l'entité à la poisition (x,y)
	 * @param x
	 * @param y
	 * @param e
	 * 
	 * @author : Moataz ERRAMI
	 */
	public void setEntityAt(int x, int y, Entity e) {
		Matrix[x][y] = e;
	}
}
