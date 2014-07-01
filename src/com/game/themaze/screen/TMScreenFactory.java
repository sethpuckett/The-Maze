package com.game.themaze.screen;

import com.game.loblib.screen.Screen;
import com.game.loblib.screen.ScreenFactory;

public class TMScreenFactory extends ScreenFactory {
	protected final static StringBuffer _tag = new StringBuffer("ScreenFactory");
	
	@Override
	public Screen create(int screenType) {
		switch (screenType) {
		case TMScreenType.SPLASH_SCREEN:
			return new SplashScreen();
		//TODO: add remaining screens
		default:
			//TODO: error
			return new SplashScreen();
		}
	}
}
