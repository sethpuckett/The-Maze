package com.game.themaze.screen;

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
import com.game.themaze.behavior.ScrollingTileBehavior;
import com.game.themaze.behavior.TMBehaviorType;
import com.game.themaze.entity.EntityHelper;
import com.game.themaze.graphics.TMImage;
import com.game.themaze.graphics.TMSpriteLayer;
import com.game.themaze.utility.TMManager;

public class EndLevelScreen extends Screen {

	protected GameEntity _background;
	protected GameEntity _nextButton;
	protected GameEntity _levelSelectButton;
	
	public EndLevelScreen() {
		_type = TMScreenType.END_LEVEL;
		_screenMusic = Sound.CONTINUE_MUSIC;
		_backBtnCtl = ButtonControlType.OVERRIDE;
	}
	
	@Override
	public void onBackDown() {
		_screenData.setCode(TMScreenCode.TRANSITION);
		_screenData.setActionScreen(TMScreenType.LEVEL_SELECT);
	}
	
	@Override
	public void onHandleMessage(Message message) {
		if (message.Type == MessageType.BUTTON_CLICKED) {
			GameEntity entity = message.getData();
			if (entity == _nextButton) {
				int level = TMManager.Level.getCurrentLevel();
				TMManager.Level.setCurrentLevel(level + 1);
				_screenData.setCode(TMScreenCode.TRANSITION);
				_screenData.setActionScreen(TMScreenType.JOURNAL);
			}
			else if (entity == _levelSelectButton) {
				_screenData.setCode(TMScreenCode.TRANSITION);
				_screenData.setActionScreen(TMScreenType.LEVEL_SELECT);
			}
		}
	}

	@Override
	public void onInit(Object input) {
		_background = EntityHelper.scrollingGraphic(TMImage.SCROLLING_STONE_WALL, TMSpriteLayer.BACKGROUND1, Direction.LEFT, 1f, Global.Data.ScrollingBackgroundPos, Global.Renderer.Width * 1.8f, true);
		_entities.add(_background);
		_nextButton = EntityHelper.button(TMImage.CONTINUE_BUTTON, TMSpriteLayer.UI_LOW,
				false, Global.Renderer.Width / 2.5f,Global.Renderer.Width / 5f, true, Global.Renderer.Width / 2, Global.Renderer.Height / 2, AreaType.Rectangle);
		_entities.add(_nextButton);
		_levelSelectButton = EntityHelper.button(TMImage.CHOOSE_LEVEL_BUTTON, TMSpriteLayer.UI_LOW,
				false, Global.Renderer.Width / 2.5f, Global.Renderer.Width / 5f, true, Global.Renderer.Width / 2, Global.Renderer.Height / 2 - Global.Renderer.Width / 4f, AreaType.Rectangle);
		_entities.add(_levelSelectButton);
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
		Manager.Message.unsubscribe(this, MessageType.ALL);
	}
}
