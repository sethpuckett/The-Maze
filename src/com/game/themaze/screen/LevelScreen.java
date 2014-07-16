package com.game.themaze.screen;

import com.game.loblib.messaging.Message;
import com.game.loblib.screen.Screen;
import com.game.loblib.utility.ButtonControlType;
import com.game.loblib.utility.Global;
import com.game.loblib.utility.Logger;
import com.game.loblib.utility.Manager;
import com.game.loblib.utility.android.FixedSizeArray;
import com.game.themaze.behavior.TMBehaviorType;
import com.game.themaze.level.Level;
import com.game.themaze.level.LevelSettings;
import com.game.themaze.messaging.TMMessageType;
import com.game.themaze.sound.TMSound;
import com.game.themaze.utility.TMManager;

public class LevelScreen extends Screen {
	
	protected Level _level;
	
	public LevelScreen() {
		_type = TMScreenType.LEVEL;
		_screenMusic = TMSound.THE_ABYSS;
		_backBtnCtl = ButtonControlType.OVERRIDE;
		_menuBtnCtl = ButtonControlType.OVERRIDE;
		_noPauseBehaviorTypes = TMBehaviorType.RENDER | TMBehaviorType.PLAYER_RENDER | TMBehaviorType.WALL_RENDER | TMBehaviorType.MONSTER_RENDER | TMBehaviorType.TEXT_RENDER | TMBehaviorType.TILE_RENDER;
	}
	
	@Override
	public void onBackDown() {
		_screenData.setCode(TMScreenCode.PUSH);
		//_screenData.setActionScreen(TMScreenType.LEVEL_OPTIONS);
		FixedSizeArray<String> testOptions = new FixedSizeArray<String>(128);
		testOptions.add("Option 1");
		testOptions.add("Option 2");
		testOptions.add("Option 3");
		testOptions.add("Option 4");
		testOptions.add("Option 5");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 1");
		testOptions.add("Option 2");
		testOptions.add("Option 3");
		testOptions.add("Option 4");
		testOptions.add("Option 5");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		testOptions.add("Option 6");
		_screenData.setInput(testOptions);
		_screenData.setActionScreen(TMScreenType.SELECT_LIST);
	}
	
	@Override
	public void onMenuDown() {
		_screenData.setCode(TMScreenCode.PUSH);
		_screenData.setActionScreen(TMScreenType.LEVEL_OPTIONS);
	}
	
	@Override
	public void onHandleMessage(Message message) {
		if (message.Type == TMMessageType.GOAL_REACHED) {
			_screenData.setCode(TMScreenCode.TRANSITION);
			_screenData.setActionScreen(TMScreenType.KILL_MONSTER);
		}
		else if (message.Type == TMMessageType.PLAYER_DEATH) {
			_screenData.setCode(TMScreenCode.TRANSITION);
			_screenData.setActionScreen(TMScreenType.PLAYER_DEATH);
		}
	}

	@Override
	public void onInit(Object input) {
		LevelSettings settings = null;
		try {
			if (input != null)
				settings = (LevelSettings)input;
		}
		catch (ClassCastException e) {
			Logger.e(_tag, "Input should be of type LevelSettings");
		}
		
		TMManager.Level.loadLevel(settings);
		Global.Camera.YOffset = TMManager.Level.getControlBarHeight();
		_entities.addAll(TMManager.Level.getLevelEntities());
		Manager.Message.subscribe(this, TMMessageType.GOAL_REACHED | TMMessageType.PLAYER_DEATH);
		TMManager.Level.enableLevel();
	}

	@Override
	protected void enableBehaviors() {
		TMManager.Level.enableBehaviors();
	}
	
	@Override
	public void onPause() {
		TMManager.Level.disableLevel();
		Manager.Message.unsubscribe(this, TMMessageType.GOAL_REACHED | TMMessageType.PLAYER_DEATH);
	}

	@Override
	public void onUnpause() {
		TMManager.Level.enableLevel();
		Manager.Message.subscribe(this, TMMessageType.GOAL_REACHED | TMMessageType.PLAYER_DEATH);
	}

	@Override
	public void onClose() {
		TMManager.Level.stop();
		Manager.Message.unsubscribe(this, TMMessageType.GOAL_REACHED | TMMessageType.PLAYER_DEATH);
	}

	@Override
	public void onActiveUpdate(float updateRatio) {
		TMManager.Level.updateLevel();
	}
}
