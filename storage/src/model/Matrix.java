package model;

/**
 * @implNote TODO
 */
public class Matrix {
	private Entity[][] Matrix;
	private int numberOfColumns;
	private int numberOfRows;

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
		this.numberOfColumns = numberOfColumns;
		this.numberOfRows = numberOfRows;
	}

	/**
	 * Retourne l'entité à la position x,y
	 * 
	 * @param x
	 * @param y
	 * @return entité à la position x,y
	 * @author Moataz ERRAMI
	 */
	public Entity getEntityAt(int x, int y) {
		if (outOfBounds(x, y))
			throw new IndexOutOfBoundsException();
		return Matrix[x][y];
	}

	/**
	 * Met à jour l'entité à la poisition (x,y)
	 * 
	 * @param x
	 * @param y
	 * @param e
	 * 
	 * @author : Moataz ERRAMI
	 */
	public void setEntityAt(int x, int y, Entity e) {
		Matrix[x][y] = e;
	}

	public boolean outOfBounds(int x, int y) {
		return x < 0 || x > numberOfColumns || y < 0 || y > numberOfRows;
	}
}
