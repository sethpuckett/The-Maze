package com.game.themaze.level;

// Left		- 1000
// Up		- 0100
// Right	- 0010
// Down		- 0001


public class WallType {
	public static final int  NONE = -1;
	public static final int  SINGLE = 0;
	public static final int  L = Integer.parseInt("1000", 2);
	public static final int  U = Integer.parseInt("0100", 2);
	public static final int  R = Integer.parseInt("0010", 2);
	public static final int  D = Integer.parseInt("0001", 2);
	public static final int  LU = Integer.parseInt("1100", 2);
	public static final int  LR = Integer.parseInt("1010", 2);
	public static final int  LD = Integer.parseInt("1001", 2);
	public static final int  UR = Integer.parseInt("0110", 2);
	public static final int  UD = Integer.parseInt("0101", 2);
	public static final int  RD = Integer.parseInt("0011", 2);
	public static final int  LUR = Integer.parseInt("1110", 2);
	public static final int  LUD = Integer.parseInt("1101", 2);
	public static final int  LRD = Integer.parseInt("1011", 2);
	public static final int  URD = Integer.parseInt("0111", 2);
	public static final int  LURD = Integer.parseInt("1111", 2);
	
	public static final int SPIKE_MASK = Integer.parseInt("10000", 2);
	public static final int PRIMARY_MASK = Integer.parseInt("100000", 2);
	
}
