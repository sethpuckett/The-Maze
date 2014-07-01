package com.game.themaze.screen;

import com.game.loblib.screen.ScreenManager;

public class TMScreenManager extends ScreenManager {
	protected static final StringBuffer _tag = new StringBuffer("TMScreenManager");
	
	protected SplashScreen _splash = new SplashScreen();
	protected TitleScreen _title = new TitleScreen();
	//protected LevelSelectScreen _levelSelect = new LevelSelectScreen();
	protected CreditsScreen _credits = new CreditsScreen();
	protected JournalScreen _journal = new JournalScreen();
	protected LevelScreen _level = new LevelScreen();
	protected LevelOptionsScreen _levelOptions = new LevelOptionsScreen();
	protected KillMonsterScreen _killMonster = new KillMonsterScreen();
	protected EndLevelScreen _endLevel = new EndLevelScreen();
	protected EndGameScreen _endGame = new EndGameScreen();
	protected PlayerDeathScreen _playerDeath = new PlayerDeathScreen();
	protected LevelSelectionScreen _levelSelection = new LevelSelectionScreen();
	
	public TMScreenManager() {
		_boostrapScreen = TMScreenType.SPLASH_SCREEN;
	}
	
	@Override
	protected void onHandleCode(int screenCode) {
//		switch (_active.getType()) {
//		case TMScreenType.SPLASH_SCREEN:
//			_active.close();
//			if (Manager.Sprite.allocatedSpriteCount() > 0)
//				Logger.e(_tag, "Splash Screen did not free all sprites upon closing");
//			if (screenCode == TMScreenCode.TRANSITION_TITLE)
//				_active = _title;
//			else {
//				Logger.e(_tag, "Invalid transition");
//				return;
//			}
//			_active.init();
//			break;
//		case TMScreenType.TITLE_SCREEN:
//			if (screenCode == TMScreenCode.TRANSITION_CREDITS) {
//				_active.pause();
//				_active = _credits;
//			}
//			else if (screenCode == TMScreenCode.TRANSITION_LEVEL_SELECT) {
//				_active.close();
//				if (Manager.Sprite.allocatedSpriteCount() > 0)
//					Logger.e(_tag, "Title Screen did not free all sprites upon closing");
//				//_active = _levelSelect;
//				_active = _levelSelection;
//			}
//			else if (screenCode == TMScreenCode.TRANSITION_JOURNAL) {
//				_active.close();
//				if (Manager.Sprite.allocatedSpriteCount() > 0)
//					Logger.e(_tag, "Title Screen did not free all sprites upon closing");
//				_active = _journal;
//			}
//			else {
//				Logger.e(_tag, "Invalid transition");
//				return;
//			}
//			_active.init();	
//			break;
//		case TMScreenType.LEVEL_SELECT:
//			_active.close();
//			if (Manager.Sprite.allocatedSpriteCount() > 0)
//				Logger.e(_tag, "Level Select Screen did not free all sprites upon closing");
//			if (screenCode == TMScreenCode.TRANSITION_TITLE)
//				_active = _title;
//			else if (screenCode == TMScreenCode.TRANSITION_JOURNAL)
//				_active = _journal;
//			else {
//				Logger.e(_tag, "Invalid transition");
//				return;
//			}
//			_active.init();	
//			break;
//		case TMScreenType.CREDITS:
//			_active.close();
//			if (screenCode == TMScreenCode.TRANSITION_TITLE)
//				_active = _title;
//			else {
//				Logger.e(_tag, "Invalid transition");
//				return;
//			}
//			_active.unpause();
//			break;
//		case TMScreenType.JOURNAL:
//			_active.close();
//			if (Manager.Sprite.allocatedSpriteCount() > 0)
//				Logger.e(_tag, "Journal Screen did not free all sprites upon closing");
//			if (screenCode == TMScreenCode.TRANSITION_LEVEL)
//				_active = _level;
//			else {
//				Logger.e(_tag, "Invalid transition");
//				return;
//			}
//			_active.init();
//			break;
//		case TMScreenType.LEVEL:
//			if (screenCode == TMScreenCode.TRANSITION_LEVEL_OPTIONS) {
//				_active.pause();
//				_active = _levelOptions;
//			}
//			else if (screenCode == TMScreenCode.TRANSITION_JOURNAL) {
//				_active.close();
//				if (Manager.Sprite.allocatedSpriteCount() > 0)
//					Logger.e(_tag, "Level Screen did not free all sprites upon closing");
//				_active = _journal;
//			}
//			else if (screenCode == TMScreenCode.TRANSITION_KILL) {
//				_active.close();
//				if (Manager.Sprite.allocatedSpriteCount() > 0)
//					Logger.e(_tag, "Level Screen did not free all sprites upon closing");
//				_active = _killMonster;
//			}
//			else if (screenCode == TMScreenCode.TRANSITION_DEATH) {
//				_active.close();
//				_active = _playerDeath;
//			}
//			else {
//				Logger.e(_tag, "Invalid transition");
//				return;
//			}
//			_active.init();
//			break;
//		case TMScreenType.LEVEL_OPTIONS:
//			_active.close();
//			if (screenCode == TMScreenCode.TRANSITION_LEVEL) {
//				_active = _level;
//				_active.unpause();
//			}
//			else if (screenCode == TMScreenCode.TRANSITION_JOURNAL) {
//				_level.close();
//				if (Manager.Sprite.allocatedSpriteCount() > 0)
//					Logger.e(_tag, "Level/Options Screen did not free all sprites upon closing");
//				_active = _journal;
//				_active.init();
//			}
//			else if (screenCode == TMScreenCode.TRANSITION_LEVEL_SELECT) {
//				_level.close();
//				if (Manager.Sprite.allocatedSpriteCount() > 0)
//					Logger.e(_tag, "Level/Options Screen did not free all sprites upon closing");
//				_active = _levelSelection;
//				_active.init();
//			}
//			else {
//				Logger.e(_tag, "Invalid transition");
//				return;
//			}
//			
//			break;
//		case TMScreenType.PLAYER_DEATH:
//			_active.close();
//			if (Manager.Sprite.allocatedSpriteCount() > 0)
//				Logger.e(_tag, "Player Death Screen did not free all sprites upon closing");
//			if (screenCode == TMScreenCode.TRANSITION_LEVEL)
//				_active = _level;
//			else if (screenCode == TMScreenCode.TRANSITION_LEVEL_SELECT) 
//				_active = _levelSelection;
//			else {
//				Logger.e(_tag, "Invalid transition");
//				return;
//			}
//			_active.init();
//			break;
//		case TMScreenType.KILL_MONSTER:
//			_active.close();
//			if (Manager.Sprite.allocatedSpriteCount() > 0)
//				Logger.e(_tag, "Kill Monster Screen did not free all sprites upon closing");
//			if (screenCode == TMScreenCode.TRANSITION_END_LEVEL)
//				_active = _endLevel;
//			else if (screenCode == TMScreenCode.TRANSITION_END_GAME)
//				_active = _endGame;
//			else {
//				Logger.e(_tag, "Invalid transition");
//				return;
//			}
//			_active.init();
//			break;
//		case TMScreenType.END_LEVEL:
//			_active.close();
//			if (Manager.Sprite.allocatedSpriteCount() > 0)
//				Logger.e(_tag, "End Level Screen did not free all sprites upon closing");
//			if (screenCode == TMScreenCode.TRANSITION_JOURNAL)
//				_active = _journal;
//			else if (screenCode == TMScreenCode.TRANSITION_LEVEL_SELECT)
//				_active = _levelSelection;
//			else if (screenCode == TMScreenCode.TRANSITION_TITLE)
//				_active = _title;
//			else {
//				Logger.e(_tag, "Invalid transition");
//				return;
//			}
//			_active.init();
//			break;
//		case TMScreenType.END_GAME:
//			_active.close();
//			if (Manager.Sprite.allocatedSpriteCount() > 0)
//				Logger.e(_tag, "End Game Screen did not free all sprites upon closing");
//			if (screenCode == TMScreenCode.TRANSITION_TITLE)
//				_active = _title;
//			else {
//				Logger.e(_tag, "Invalid transition");
//				return;
//			}
//			_active.init();
//			break;
//		}
	}

}