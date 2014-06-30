package com.game.themaze.messaging;

import com.game.loblib.messaging.MessageType;

public class TMMessageType extends MessageType {
	public final static long GOAL_REACHED =			1 << 6;		//data: GameEntity
	public final static long LEVEL_CELL_WIDTH_SET =	1 << 12;	//no data
	public final static long DAMAGE	=				1 << 14;	//data: GameEntity
	public final static long PLAYER_DEATH =			1 << 15;	//no data
	public final static long ITEM_HIT =				1 << 16;	//data: GameEntity
	public final static long ENABLE_ITEM =	 		1 << 17;	//data: int[] (GameItemType); array has 1 element. hack. Java is stupid
	public final static long DISABLE_ITEM = 		1 << 18;	//data: int[] (GameItemType); array has 1 element. hack. Java is stupid
	public final static long DESTINATION_REACHED =	1 << 19;	//data: GameEntity
	public final static long TRIGGER_PATH_FAIL =	1 << 22;	//data: GameEntity
	public final static long FADE_CHAIN_COMPLETE =	1 << 23;	//data: GameEntity
	public final static long PATROL_START =			1 << 24;	//data: GameEntity
}
