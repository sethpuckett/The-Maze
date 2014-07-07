package com.game.themaze.screen;

import com.game.loblib.screen.Screen;
import com.game.loblib.screen.ScreenFactory;
import com.game.loblib.utility.Logger;

public class TMScreenFactory extends ScreenFactory {
	protected final static StringBuffer _tag = new StringBuffer("ScreenFactory");
	
	@Override
	public Screen create(int screenType) {
		switch (screenType) {
		case TMScreenType.SPLASH_SCREEN:
			return new SplashScreen();
		case TMScreenType.TITLE_SCREEN:
			return new TitleScreen();
		case TMScreenType.LEVEL_SELECT:
			return new LevelSelectionScreen();
		case TMScreenType.CREDITS:
			return new CreditsScreen();
		case TMScreenType.JOURNAL:
			return new JournalScreen();
		case TMScreenType.LEVEL:
			return new LevelScreen();
		case TMScreenType.LEVEL_OPTIONS:
			return new LevelOptionsScreen();
		case TMScreenType.PLAYER_DEATH:
			return new PlayerDeathScreen();
		case TMScreenType.KILL_MONSTER:
			return new KillMonsterScreen();
		case TMScreenType.END_LEVEL:
			return new EndLevelScreen();
		case TMScreenType.END_GAME:
			return new EndGameScreen();
		case TMScreenType.DEBUG:
			return new DebugScreen();
		case TMScreenType.SELECT_LIST:
			return new SelectListScreen();
		default:
			Logger.e(_tag, "Invalid screen type");
			return null;
		}
	}
}
