package model;

import java.time.Duration;

public class ModelConstants {
	public static double WORLD_HEIGHT = 1000;
	public static double WORLD_WIDTH = 1000;

	public static Duration PHYSICS_STEP_DELAY = Duration.ofMillis(250);
	public static double GRAVITY = 10;

	public static double WORLD_DENSITY = 1;
	public static double WORLD_VISCOSITY = 0.2;
	
	public static long SEED = 12;
}
