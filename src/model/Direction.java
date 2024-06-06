package model;
/**
 * @implNote TODO
 * @implNote TODO Choisir le package de la classe (model vs automaton)
 */
public class Direction {
	int direction;
	
	public Direction(int d) {
		direction = d;
	}
	
	public static final int N = 0 ;
	
	public static final int S = 1 ;
	
	public static final int W = 2 ;
	
	public static final int E = 3 ;
	
	public static final int NW = 4 ;

	public static final int NE = 5 ;

	public static final int SW = 6 ;
	
	public static final int SE = 7 ;
	
	public static final int H = 8 ; //HERE
	
	public static final int F = 9 ; //FRONT
	
	public static final int B = 10 ; //BACK
	
	public static final int L = 11 ; //ON MY LEFT
	
	public static final int R = 12 ; //ON MY RIGHT


}
