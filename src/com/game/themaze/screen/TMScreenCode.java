package com.game.themaze.screen;

import com.game.loblib.screen.ScreenCode;

public class TMScreenCode extends ScreenCode {	
	public final static int TRANSITION_TITLE =			1 << 10;
	public final static int TRANSITION_CREDITS =		1 << 11;
	public final static int TRANSITION_LEVEL_SELECT =	1 << 12;
	public final static int TRANSITION_JOURNAL =		1 << 13;
	public final static int TRANSITION_LEVEL =			1 << 14;
	public final static int TRANSITION_LEVEL_OPTIONS =	1 << 15;
	public final static int TRANSITION_DEATH =			1 << 16;
	public final static int TRANSITION_KILL =			1 << 17;
	public final static int TRANSITION_END_LEVEL =		1 << 18;
	public final static int TRANSITION_END_GAME =		1 << 19;
}