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

public class LevelOptionsScreen extends Screen {

	GameEntity _resumeButton;
	GameEntity _restartButton;
	GameEntity _levelSelectButton;
	GameEntity _soundButton;
	GameEntity _background;
	
	public LevelOptionsScreen() {
		_type = TMScreenType.LEVEL_OPTIONS;
		_screenMusic = Sound.CONTINUE_MUSIC;
		_backBtnCtl = ButtonControlType.OVERRIDE;
	}
	
	@Override
	public void onBackDown() {
		_screenData.setCode(TMScreenCode.POP);
	}
	
	@Override
	public void onHandleMessage(Message message) {
		if (message.Type == MessageType.BUTTON_CLICKED) {
			GameEntity entity = message.getData();
			if (entity == _resumeButton) 
				_screenData.setCode(TMScreenCode.POP);
			else if (entity == _restartButton) {
				_screenData.setCode(TMScreenCode.TRANSITION_ALL);
				_screenData.setActionScreen(TMScreenType.JOURNAL);
			}
			else if (entity == _levelSelectButton) {
				_screenData.setCode(TMScreenCode.TRANSITION_ALL);
				_screenData.setActionScreen(TMScreenType.LEVEL_SELECT);
			}
			else if (entity == _soundButton)
				toggleSound();
		}
	}

	@Override
	public void onInit(Object input) {
		_background = EntityHelper.scrollingGraphic(TMImage.SCROLLING_STONE_WALL, TMSpriteLayer.BACKGROUND1, Direction.LEFT, 1f, Global.Data.ScrollingBackgroundPos, Global.Renderer.Width * 1.8f, true);
		_entities.add(_background);
		_resumeButton = EntityHelper.button(TMImage.RESUME_BUTTON, TMSpriteLayer.UI_LOW,
				false, Global.Renderer.Width / 2.5f, Global.Renderer.Width / 5f, true, Global.Renderer.Width / 2, Global.Renderer.Height / 2 + Global.Renderer.Width / 4f, AreaType.Rectangle);
		_entities.add(_resumeButton);
		_restartButton = EntityHelper.button(TMImage.RESTART_BUTTON, TMSpriteLayer.UI_LOW,
				false, Global.Renderer.Width / 2.5f, Global.Renderer.Width / 5f, true, Global.Renderer.Width / 2, Global.Renderer.Height / 2 - Global.Renderer.Width / 4f, AreaType.Rectangle);
		_entities.add(_restartButton);
		_levelSelectButton = EntityHelper.button(TMImage.CHOOSE_LEVEL_BUTTON, TMSpriteLayer.UI_LOW,
				false, Global.Renderer.Width / 2.5f, Global.Renderer.Width / 5f, true, Global.Renderer.Width / 2, Global.Renderer.Height / 2 - (Global.Renderer.Width / 4f * 2f), AreaType.Rectangle);
		_entities.add(_levelSelectButton);
		_soundButton = soundButton();
		_entities.add(_soundButton);
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

	protected void toggleSound() {
		if (Manager.Sound.isEnabled()) {
			Manager.Sound.disableSound();
			_soundButton.Attributes.Sprite.setFrame(1);
		}
		else {
			Manager.Sound.enableSound();
			_soundButton.Attributes.Sprite.setFrame(0);
		}
	}
	
	
	/*********************************
	 * Entities
	 ********************************/
	protected GameEntity soundButton() {
		GameEntity button = EntityHelper.button(TMImage.SOUND_BUTTON,
				TMSpriteLayer.UI_LOW, false, Global.Renderer.Width / 6f, Global.Renderer.Width / 6f, false, 
				Global.Renderer.Width - (11f * Global.Renderer.Width / 60f), Global.Renderer.Width / 60f, AreaType.Circle);
		
		if (Manager.Sound.isEnabled())
			button.Attributes.Sprite.setFrame(0);
		else
			button.Attributes.Sprite.setFrame(1);
		
		return button;
	}
}
