package com.game.themaze.screen;

import com.game.loblib.screen.ScreenType;

public class TMScreenType extends ScreenType {
	public final static int SPLASH_SCREEN =		1 << 10;
	public final static int TITLE_SCREEN =		1 << 11;
	public final static int LEVEL_SELECT =		1 << 12;
	public final static int CREDITS =			1 << 13;
	public final static int JOURNAL =			1 << 14;
	public final static int LEVEL =				1 << 15;
	public final static int LEVEL_OPTIONS =		1 << 16;
	public final static int PLAYER_DEATH =		1 << 17;
	public final static int KILL_MONSTER =		1 << 18;
	public final static int END_LEVEL =			1 << 19;
	public final static int END_GAME =			1 << 20;
}
