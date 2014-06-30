package com.game.themaze.utility;

import com.game.loblib.LobLibGame;
import com.game.loblib.collision.CollisionManager;
import com.game.loblib.graphics.Camera;
import com.game.loblib.graphics.SpriteHelper;
import com.game.loblib.screen.ScreenManager;
import com.game.loblib.sound.MusicHelper;
import com.game.loblib.utility.CommonData;
import com.game.loblib.utility.ComponentFactory;
import com.game.loblib.utility.GameSettings;
import com.game.loblib.utility.Global;
import com.game.themaze.TMGame;
import com.game.themaze.collision.TMCollisionManager;
import com.game.themaze.graphics.TMCamera;
import com.game.themaze.graphics.TMSpriteHelper;
import com.game.themaze.level.LevelManager;
import com.game.themaze.screen.TMScreenManager;
import com.game.themaze.sound.TMMusicHelper;

public class TMComponentFactory extends ComponentFactory {
	
	@Override
	public Global CreateGlobal() {
		return new Global();
	}
	
	@Override
	public LobLibGame CreateGame() {
		return new TMGame();
	}
	
	@Override
	public CollisionManager CreateCollisionManager() {
		return new TMCollisionManager();
	}
	
	@Override
	public SpriteHelper CreateSpriteHelper() {
		return new TMSpriteHelper();
	}
	
	@Override
	public ScreenManager CreateScreenManager() {
		return new TMScreenManager();
	}
	
	@Override
	public MusicHelper CreateMusicHelper() {
		return new TMMusicHelper();
	}
	
	@Override
	public CommonData CreateCommonData() {
		return new TMCommonData();
	}
	
	@Override
	public GameSettings CreateGameSettings() {
		return new TMGameSettings();
	}
	
	@Override
	public Camera CreateCamera() {
		return new TMCamera();
	}
	
	public LevelManager CreateLevelManager() {
		return new LevelManager();
	}
}
