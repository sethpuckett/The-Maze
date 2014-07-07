package com.game.themaze.screen;

import com.game.loblib.entity.GameEntity;
import com.game.loblib.messaging.Message;
import com.game.loblib.messaging.MessageType;
import com.game.loblib.screen.Screen;
import com.game.loblib.sound.Sound;
import com.game.loblib.utility.Global;
import com.game.loblib.utility.Manager;
import com.game.loblib.utility.area.AreaType;
import com.game.themaze.entity.EntityHelper;
import com.game.themaze.graphics.TMImage;
import com.game.themaze.graphics.TMSpriteLayer;
import com.game.themaze.utility.TMGlobal;
import com.game.themaze.utility.TMManager;

public class KillMonsterScreen extends Screen {

	protected GameEntity _killButton;
	protected GameEntity _leaveButton;
	
	public KillMonsterScreen() {
		_type = TMScreenType.KILL_MONSTER;
		_screenMusic = Sound.CONTINUE_MUSIC;
	}
	
	@Override
	public void onHandleMessage(Message message) {
		if (message.Type == MessageType.BUTTON_CLICKED) {
			GameEntity entity = message.getData();
			if (entity == _killButton || entity == _leaveButton) {
				if (TMManager.Level.getCurrentLevel() == 30) {
					_screenData.setCode(TMScreenCode.TRANSITION);
					_screenData.setActionScreen(TMScreenType.END_GAME);
				}
				else {
					_screenData.setCode(TMScreenCode.TRANSITION);
					_screenData.setActionScreen(TMScreenType.END_LEVEL);
				}
			}
		}
	}

	@Override
	public void onInit(Object input) {
		int level = TMManager.Level.getCurrentLevel();
		TMGlobal.TMSettings.setLevelComplete(level, true);
		
		_entities.add(EntityHelper.fullscreenGraphic(TMImage.KILLMONSTER_BG, TMSpriteLayer.BACKGROUND1));
		_killButton = EntityHelper.button(TMImage.CONTINUE_BUTTON, TMSpriteLayer.UI_LOW,
				false, Global.Renderer.Width / 2.5f, Global.Renderer.Width / 5f, true, 
				Global.Renderer.Width / 2, Global.Renderer.Height / 4 , AreaType.Rectangle);
		_entities.add(_killButton);
		if(TMManager.Level.getCurrentLevel() == 30) {
			_leaveButton = EntityHelper.button(TMImage.BACK_BUTTON, TMSpriteLayer.UI_LOW,
					false, Global.Renderer.Width / 2.5f, Global.Renderer.Width / 5f, true, 
					Global.Renderer.Width / 2, Global.Renderer.Height / 2 - Global.Renderer.Width / 4f, AreaType.Rectangle);
			_entities.add(_leaveButton);
		}
		
		Manager.Message.subscribe(this, MessageType.BUTTON_CLICKED);
	}

	@Override
	public void onPause() {		
		Manager.Message.unsubscribe(this, MessageType.ALL);
	}

	@Override
	public void onUnpause() {
		Manager.Message.subscribe(this, MessageType.BUTTON_CLICKED);
	}

	@Override
	public void onClose() {	
		Manager.Message.unsubscribe(this, MessageType.ALL);
	}
}
