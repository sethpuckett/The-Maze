package com.game.themaze.screen;

import java.util.Random;

import com.game.loblib.entity.GameEntity;
import com.game.loblib.messaging.Message;
import com.game.loblib.messaging.MessageType;
import com.game.loblib.screen.Screen;
import com.game.loblib.sound.Sound;
import com.game.loblib.utility.ButtonControlType;
import com.game.loblib.utility.Direction;
import com.game.loblib.utility.Global;
import com.game.loblib.utility.Manager;
import com.game.loblib.utility.area.AreaType;
import com.game.themaze.entity.EntityHelper;
import com.game.themaze.graphics.TMImage;
import com.game.themaze.graphics.TMSpriteLayer;
import com.game.themaze.utility.TMGlobal;

public class PlayerDeathScreen extends Screen {
	
	protected GameEntity _background;
	protected GameEntity _restartButton;
	protected GameEntity _levelSelectButton;
	
	public PlayerDeathScreen() {
		_type = TMScreenType.PLAYER_DEATH;
		_screenMusic = Sound.CONTINUE_MUSIC;
		_backBtnCtl = ButtonControlType.OVERRIDE;
	}
	
	@Override
	public void onBackDown() {
		_screenData.setCode(TMScreenCode.TRANSITION);
		_screenData.setActionScreen(TMScreenType.LEVEL_SELECT);
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

	@Override
	public void onInit(Object input) {
		int subHeadImage = 0;
		Random imageCheck = new Random();
		Random rareCheck = new Random();
		if (TMGlobal.TMSettings.getCompletedLevelCount() > 10 && rareCheck.nextInt(25) < 1) {
			switch (imageCheck.nextInt(4)) {
			case 0:
				subHeadImage = TMImage.DEATH_MESSAGE_RARE_01;
				break;
			case 1:
				subHeadImage = TMImage.DEATH_MESSAGE_RARE_02;
				break;
			case 2:
				subHeadImage = TMImage.DEATH_MESSAGE_RARE_03;
				break;
			case 3:
				subHeadImage = TMImage.DEATH_MESSAGE_RARE_04;
				break;
			}
		}
		else {
			switch (imageCheck.nextInt(5)) {
			case 0:
				subHeadImage = TMImage.DEATH_MESSAGE01;
				break;
			case 1:
				subHeadImage = TMImage.DEATH_MESSAGE02;
				break;
			case 2:
				subHeadImage = TMImage.DEATH_MESSAGE03;
				break;
			case 3:
				subHeadImage = TMImage.DEATH_MESSAGE04;
				break;
			case 4:
				subHeadImage = TMImage.DEATH_MESSAGE05;
				break;
			}
		}
		//_entities.add(EntityHelper.graphic(subHeadImage, TMSpriteLayer.UI_LOW, false, Global.Renderer.Width, Global.Renderer.Width / 4f, false, 0, Global.Renderer.Height - (Global.Renderer.Width * 13f / 30f) ));
		
		_entities.add(EntityHelper.graphic(TMImage.DEATH_TEXT, TMSpriteLayer.UI_LOW, false, Global.Renderer.Width, Global.Renderer.Width / 4f, false, 0, Global.Renderer.Height - (Global.Renderer.Width / 4f)));
		_background = EntityHelper.scrollingGraphic(TMImage.DEATH_BONES, TMSpriteLayer.BACKGROUND1, Direction.LEFT, .3f, 0f, Global.Renderer.Height, true);
		_entities.add(_background);
		_restartButton = EntityHelper.button(TMImage.RESTART_BUTTON, TMSpriteLayer.UI_LOW,
				false, Global.Renderer.Width / 2.5f, Global.Renderer.Width / 5f, true, Global.Renderer.Width / 2, Global.Renderer.Height / 2, AreaType.Rectangle);
		_entities.add(_restartButton);
		_levelSelectButton = EntityHelper.button(TMImage.CHOOSE_LEVEL_BUTTON, TMSpriteLayer.UI_LOW,
				false, Global.Renderer.Width / 2.5f, Global.Renderer.Width / 5f, true, Global.Renderer.Width / 2, Global.Renderer.Height / 2 - Global.Renderer.Width / 2f, AreaType.Rectangle);
		_entities.add(_levelSelectButton);
		Manager.Message.subscribe(this, MessageType.BUTTON_CLICKED);
	}
	
	@Override
	public void onHandleMessage(Message message) {
		if (message.Type == MessageType.BUTTON_CLICKED) {
			GameEntity entity = message.getData();
			if (entity == _restartButton) {
				_screenData.setCode(TMScreenCode.TRANSITION);
				_screenData.setActionScreen(TMScreenType.LEVEL);
			}
			else if (entity == _levelSelectButton) {
				_screenData.setCode(TMScreenCode.TRANSITION);
				_screenData.setActionScreen(TMScreenType.LEVEL_SELECT);
			}
		}
	}

}
