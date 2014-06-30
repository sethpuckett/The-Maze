package com.game.themaze.collision;

import com.game.loblib.collision.CollisionLayer;

public class TMCollisionLayer extends CollisionLayer {
	public final static long MAIN_BLOCK = 	1 << 10;
	public final static long PLAYER =		1 << 11;
	public final static long DAMAGE =		1 << 12;
	public final static long GOAL =			1 << 13;
	public final static long GAME_ITEM =	1 << 14;
}
