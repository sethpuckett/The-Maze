package com.game.themaze.screen;

import com.game.loblib.entity.GameEntity;
import com.game.loblib.messaging.Message;
import com.game.loblib.messaging.MessageType;
import com.game.loblib.screen.Screen;
import com.game.loblib.utility.ButtonControlType;
import com.game.loblib.utility.Direction;
import com.game.loblib.utility.Global;
import com.game.loblib.utility.Manager;
import com.game.loblib.utility.android.FixedSizeArray;
import com.game.loblib.utility.area.AreaType;
import com.game.themaze.behavior.ScrollingTileBehavior;
import com.game.themaze.behavior.TMBehaviorType;
import com.game.themaze.entity.EntityHelper;
import com.game.themaze.graphics.TMImage;
import com.game.themaze.graphics.TMSpriteLayer;
import com.game.themaze.sound.TMSound;
import com.game.themaze.utility.TMManager;

public class LevelSelectScreen extends Screen {

	protected FixedSizeArray<GameEntity> _levelButtons;
	protected GameEntity _titleScreenButton;
	protected GameEntity _background;
	
	public LevelSelectScreen() {
		_type = TMScreenType.LEVEL_SELECT;
		_screenMusic = TMSound.BADLOOP;
		_backBtnCtl = ButtonControlType.OVERRIDE;
	}
	
	@Override
	public void onBackDown() {
		_code = TMScreenCode.TRANSITION_TITLE;
	}
	
	@Override
	public void onHandleMessage(Message message) {
		if (message.Type == MessageType.BUTTON_CLICKED) {
			GameEntity entity = message.getData();
			int levIndex;
			if (entity == _titleScreenButton)
				_code = TMScreenCode.TRANSITION_TITLE;
			else if ((levIndex = _levelButtons.find(entity, false)) != -1) {
				TMManager.Level.setCurrentLevel(levIndex + 1);
				_code = TMScreenCode.TRANSITION_JOURNAL;
			}
		}
	}

	@Override
	public void onInit() {
		_background = EntityHelper.scrollingGraphic(TMImage.SCROLLING_STONE_WALL, TMSpriteLayer.BACKGROUND1, Direction.LEFT, 1f, Global.Data.ScrollingBackgroundPos, Global.Renderer.Width * 1.8f, true);
		_entities.add(_background);
		_titleScreenButton = levelSelectBackButton();
		_entities.add(_titleScreenButton);
		_levelButtons = new FixedSizeArray<GameEntity>(31);
//		int maxLevel = Global.Settings.getMaxLevel();
//		for (int i = 1; i < 31; i++) {
//			GameEntity entity = levelSelectButton(i, i <= maxLevel);
//			_levelButtons.add(entity);
//			_entities.add(entity);
//		}

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
		Global.Data.ScrollingBackgroundPos = ((ScrollingTileBehavior)_background.getBehavior(TMBehaviorType.SCROLLING_TILE)).getSpritePosition();
		_levelButtons = null;
		Manager.Message.unsubscribe(this, MessageType.ALL);
	}

	protected int getButtonImage(int level) {
		//TODO: add remaining images
		switch (level) {
		case 1: return TMImage.LEVEL_1_BUTTON;
		case 2: return TMImage.LEVEL_2_BUTTON;
		case 3: return TMImage.LEVEL_3_BUTTON;
		case 4: return TMImage.LEVEL_4_BUTTON;
		case 5: return TMImage.LEVEL_5_BUTTON;
		case 6: return TMImage.LEVEL_6_BUTTON;
		case 7: return TMImage.LEVEL_7_BUTTON;
		case 8: return TMImage.LEVEL_8_BUTTON;
		case 9: return TMImage.LEVEL_9_BUTTON;
		case 10: return TMImage.LEVEL_10_BUTTON;
		case 11: return TMImage.LEVEL_11_BUTTON;
		case 12: return TMImage.LEVEL_12_BUTTON;
		case 13: return TMImage.LEVEL_13_BUTTON;
		case 14: return TMImage.LEVEL_14_BUTTON;
		case 15: return TMImage.LEVEL_15_BUTTON;
		case 16: return TMImage.LEVEL_16_BUTTON;
		case 17: return TMImage.LEVEL_17_BUTTON;
		case 18: return TMImage.LEVEL_18_BUTTON;
		case 19: return TMImage.LEVEL_19_BUTTON;
		case 20: return TMImage.LEVEL_20_BUTTON;
		case 21: return TMImage.LEVEL_21_BUTTON;
		case 22: return TMImage.LEVEL_22_BUTTON;
		case 23: return TMImage.LEVEL_23_BUTTON;
		case 24: return TMImage.LEVEL_24_BUTTON;
		case 25: return TMImage.LEVEL_25_BUTTON;
		case 26: return TMImage.LEVEL_26_BUTTON;
		case 27: return TMImage.LEVEL_27_BUTTON;
		case 28: return TMImage.LEVEL_28_BUTTON;
		case 29: return TMImage.LEVEL_29_BUTTON;
		case 30: return TMImage.LEVEL_30_BUTTON;
		default: return TMImage.LEVEL_1_BUTTON;
		}
	}
	
	/*********************************
	 * Entities
	 ********************************/
	protected GameEntity levelSelectButton(int level, boolean enabled) {
		float screenX = Global.Renderer.Width;
		float buffer = screenX / 60f;
		float gridSize = screenX / 5f;
		float topBuffer = screenX / 5f;
		float posX = 0;
		float posY = 0;
		float sizeX = gridSize - (buffer * 2f);
		float sizeY = sizeX;
		
		int gridX = 0;
		int gridY = 0;
		
		switch (level) {
		case 1: case 6: case 11: case 16: case 21: case 26:
			gridX = 1;
			break;
		case 2: case 7: case 12: case 17: case 22: case 27:
			gridX = 2;
			break;
		case 3: case 8: case 13: case 18: case 23: case 28:
			gridX = 3;
			break;
		case 4: case 9: case 14: case 19: case 24: case 29:
			gridX = 4;
			break;
		case 5: case 10: case 15: case 20: case 25: case 30:
			gridX = 5;
			break;
		}
		
		switch (level) {
		case 1:case 2:case 3:case 4:case 5:
			gridY = 1;
			break;
		case 6:case 7:case 8:case 9:case 10:
			gridY = 2;
			break;
		case 11:case 12:case 13:case 14:case 15:
			gridY = 3;
			break;
		case 16:case 17:case 18:case 19:case 20:
			gridY = 4;
			break;
		case 21:case 22:case 23:case 24:case 25:
			gridY = 5;
			break;
		case 26:case 27:case 28:case 29:case 30:
			gridY = 6;
			break;
		}
		
		switch (gridX) {
		case 1:
			posX = buffer;
			break;
		case 2:
			posX = gridSize + buffer;
			break;
		case 3:
			posX = (gridSize * 2) + buffer;
			break;
		case 4:
			posX = (gridSize * 3) + buffer;
			break;
		case 5:
			posX = (gridSize * 4) + buffer;
			break;
		}
		
		switch (gridY) {
		case 7:
			posY = topBuffer + buffer;
			break;
		case 6:
			posY = topBuffer + gridSize + buffer;
			break;
		case 5:
			posY = topBuffer + (gridSize * 2) + buffer;
			break;
		case 4:
			posY = topBuffer + (gridSize * 3) + buffer;
			break;
		case 3:
			posY = topBuffer + (gridSize * 4) + buffer;
			break;
		case 2:
			posY = topBuffer + (gridSize * 5) + buffer;
			break;
		case 1:
			posY = topBuffer + (gridSize * 6) + buffer;
			break;
		}
		
		if (enabled) {
			return EntityHelper.button(getButtonImage(level), 
					TMSpriteLayer.UI_LOW, false, sizeX, sizeY, false, posX, posY, AreaType.Rectangle, true);
		}
		else {
			return EntityHelper.graphic(TMImage.LEVEL_LOCK_BUTTON, TMSpriteLayer.UI_LOW,
					false, sizeX, sizeY, false, posX, posY);
		}
	}

	public static GameEntity levelSelectBackButton() {
		return EntityHelper.button(TMImage.BACK_BUTTON,
				TMSpriteLayer.UI_LOW, false, Global.Renderer.Width / 2.5f, Global.Renderer.Width / 5f, false, Global.Renderer.Width / 60f, Global.Renderer.Width / 60f, AreaType.Rectangle);
	}
}
