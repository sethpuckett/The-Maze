package com.game.themaze.screen;

import com.game.loblib.messaging.Message;
import com.game.loblib.screen.Screen;
import com.game.loblib.utility.ButtonControlType;
import com.game.loblib.utility.Global;
import com.game.loblib.utility.Manager;
import com.game.themaze.level.Level;
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
	}
	
	@Override
	public void onBackDown() {
		_code = TMScreenCode.TRANSITION_LEVEL_OPTIONS;
	}
	
	@Override
	public void onMenuDown() {
		_code = TMScreenCode.TRANSITION_LEVEL_OPTIONS;
	}
	
	@Override
	public void onHandleMessage(Message message) {
		if (message.Type == TMMessageType.GOAL_REACHED)
			_code = TMScreenCode.TRANSITION_KILL;
		else if (message.Type == TMMessageType.PLAYER_DEATH)
			_code = TMScreenCode.TRANSITION_DEATH;
	}

	@Override
	public void onInit() {
		TMManager.Level.loadLevel();
		Global.Camera.YOffset = TMManager.Level.getControlBarHeight();
		_entities.addAll(TMManager.Level.getLevelEntities());
		Manager.Message.subscribe(this, TMMessageType.GOAL_REACHED | TMMessageType.PLAYER_DEATH);
		TMManager.Level.enableLevel();
	}

	@Override
	public void enableBehaviors() {
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
	public void update(float updateRatio) {
		TMManager.Level.updateLevel();
	}
}
