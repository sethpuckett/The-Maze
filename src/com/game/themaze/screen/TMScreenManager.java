package com.game.themaze.screen;

import com.game.loblib.screen.ScreenManager;

public class TMScreenManager extends ScreenManager {
	protected static final StringBuffer _tag = new StringBuffer("TMScreenManager");
	
	public TMScreenManager() {
		// game will start with splash screen
		_boostrapScreen = TMScreenType.SPLASH_SCREEN;
	}
}