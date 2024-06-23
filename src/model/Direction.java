package model;

public enum Direction {
	N, S, E, W, NW, NE, SW, SE, HERE, FORWARD, BACKWARD, LEFT, RIGHT, UNDERSCORE, d;

	public static Direction relativeToAbsolute(Direction absoluteDirection, Direction relativeDirection) {
		switch (relativeDirection) {
		case BACKWARD:
			return oppositeDirection(absoluteDirection);
		case LEFT:
			return rotateLeft(absoluteDirection);
		case RIGHT:
			return rotateRight(absoluteDirection);
		case FORWARD:
		case HERE:
			return absoluteDirection;
		default:
			return relativeDirection;
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

	public static Direction rotateSlightlyRight(Direction absoluteDirection) {
		switch (absoluteDirection) {
		case N:
			return NE;
		case E:
			return SE;
		case S:
			return NW;
		case W:
			return NW;
		case NW:
			return N;
		case NE:
			return E;
		case SE:
			return S;
		case SW:
			return W;
		default:
			throw new IllegalArgumentException("Absolute direction expected, got : " + absoluteDirection);
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

	public static boolean isAngleInDirection(double angle, Direction direction) {
		switch (direction) {
		case N:
			return angle < 45 || angle > 315;
		case NE:
			return angle < 90 && angle > 0;
		case NW:
			return angle > 270;
		case S:
			return angle < 225 && angle > 135;
		case SE:
			return angle < 180 && angle > 90;
		case SW:
			return angle < 270 && angle > 180;
		case E:
			return angle < 135 && angle > 45;
		case W:
			return angle < 315 && angle > 225;
		default:
			return false;
		}
	}

	/**
	 * @return a random absolute direction between the 8 possible
	 */
	public Direction getRandomDirection() {
		int randomInt = getRandomNumber(1, 8);
		switch (randomInt) {
		case 1:
			return N;
		case 2:
			return S;
		case 3:
			return E;
		case 4:
			return W;
		case 5:
			return NW;
		case 6:
			return NE;
		case 7:
			return SW;
		case 8:
			return SE;
		default:
			throw new IllegalStateException("This should not happen, random number picked :" + randomInt);
		}
	}

	/**
	 * Function used in getRandomDirection()
	 * 
	 * @param min
	 * @param max
	 * @return a random int between min and max (both included)
	 */
	private int getRandomNumber(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}
}
