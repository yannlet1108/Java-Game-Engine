package model;

public enum Direction {
	N, S, E, W, NW, NE, SW, SE, HERE, FORWARD, BACKWARD, LEFT, RIGHT;

	public static Direction relativeToAbsolute(Direction absoluteDirection, Direction relativeDirection) {
		switch (relativeDirection) {
		case FORWARD:
			return absoluteDirection;
		case BACKWARD:
			return oppositeDirection(absoluteDirection);
		case LEFT:
			return rotateLeft(absoluteDirection);
		case RIGHT:
			return rotateRight(absoluteDirection);
		default:
			return absoluteDirection;
		}
	}

	public static Direction oppositeDirection(Direction direction) {
		switch (direction) {
		case N:
			return S;
		case S:
			return N;
		case E:
			return W;
		case W:
			return E;
		case NW:
			return SE;
		case NE:
			return SW;
		case SW:
			return NE;
		case SE:
			return NW;
		case HERE:
			return HERE;
		case FORWARD:
			return BACKWARD;
		case BACKWARD:
			return FORWARD;
		case LEFT:
			return RIGHT;
		case RIGHT:
			return LEFT;
		default:
			throw new IllegalArgumentException("Unknown direction: " + direction);
		}
	}

	public static Direction rotateRight(Direction direction) {
		switch (direction) {
		case N:
			return E;
		case E:
			return S;
		case S:
			return W;
		case W:
			return N;
		case NW:
			return NE;
		case NE:
			return SE;
		case SE:
			return SW;
		case SW:
			return NW;
		case HERE:
			return HERE;
		case FORWARD:
			return RIGHT;
		case BACKWARD:
			return LEFT;
		case LEFT:
			return FORWARD;
		case RIGHT:
			return BACKWARD;
		default:
			throw new IllegalArgumentException("Unknown direction: " + direction);
		}
	}

	public static Direction rotateLeft(Direction direction) {
		switch (direction) {
		case N:
			return W;
		case W:
			return S;
		case S:
			return E;
		case E:
			return N;
		case NW:
			return SW;
		case SW:
			return SE;
		case SE:
			return NE;
		case NE:
			return NW;
		case HERE:
			return HERE;
		case FORWARD:
			return LEFT;
		case BACKWARD:
			return RIGHT;
		case LEFT:
			return BACKWARD;
		case RIGHT:
			return FORWARD;
		default:
			throw new IllegalArgumentException("Unknown direction: " + direction);
		}
	}
}
