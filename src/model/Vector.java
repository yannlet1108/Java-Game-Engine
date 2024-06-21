package model;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class Vector {
	private Point2D endPoint;

	public Vector(double x, double y) {
		endPoint = new Point2D.Double(x, y);
	}

	public Vector() {
		endPoint = new Point2D.Double(0, 0);
	}

	public double getX() {
		return endPoint.getX();
	}

	public double getY() {
		return endPoint.getY();
	}

	public Vector scalarMultiplication(double a) {
		return new Vector(getX() * a, getY() * a);
	}

	public Vector add(Vector v) {
		return new Vector(getX() + v.getX(), getY() + v.getY());
	}

	public Point2D add(Point2D position) {
		return new Point2D.Double(getX() + position.getX(), getY() + position.getY());
	}

	public double norm() {
		return Math.sqrt(Math.pow(getX(), 2) + Math.pow(getY(), 2));
	}

	public Vector unitVector() {
		double norm = norm();
		if (norm == 0)
			return new Vector();
		return new Vector(getX() / norm, getY() / norm);
	}

	@Override
	public String toString() {
		return "(" + getX() + "," + getY() + ")";
	}

	Direction getVectorDirection() {
		double x = getX();
		double y = getY();

		if (x == 0 && y == 0) {
			return Direction.HERE;
		}

		if (x == 0) {
			if (y > 0) {
				return Direction.N;
			} else {
				return Direction.S;
			}
		}

		if (y == 0) {
			if (x > 0) {
				return Direction.E;
			} else {
				return Direction.W;
			}
		}

		if (x > 0 && y > 0) {
			return Direction.NE;
		}

		if (x > 0 && y < 0) {
			return Direction.SE;
		}

		if (x < 0 && y > 0) {
			return Direction.NW;
		}

		if (x < 0 && y < 0) {
			return Direction.SW;
		}

		return Direction.HERE;
	}

	Line2D getLineFrom(Point2D p) {
		return new Line2D.Double(p, add(p));
	}

	public static Vector getVectorUnitVectorFromDirection(Direction direction) {
		Vector vector;
		switch (direction) {
		case N:
			vector = new Vector(0, -1);
		case S:
			vector = new Vector(0, 1);
		case E:
			vector = new Vector(1, 0);
		case W:
			vector = new Vector(-1, 0);
		case NW:
			vector = new Vector(-1, -1);
		case NE:
			vector = new Vector(1, -1);
		case SW:
			vector = new Vector(-1, 1);
		case SE:
			vector = new Vector(1, 1);
		}
		return vector.unitVector();
	}
}
