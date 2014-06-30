package com.game.themaze.screen;


import com.game.loblib.behavior.BehaviorType;
import com.game.loblib.entity.GameEntity;
import com.game.loblib.messaging.Message;
import com.game.loblib.messaging.MessageType;
import com.game.loblib.screen.Screen;
import com.game.loblib.utility.ButtonControlType;
import com.game.loblib.utility.Direction;
import com.game.loblib.utility.Global;
import com.game.loblib.utility.Manager;
import com.game.loblib.utility.TweenType;
import com.game.loblib.utility.area.AreaType;
import com.game.themaze.behavior.ScrollingTileBehavior;
import com.game.themaze.behavior.TMBehaviorType;
import com.game.themaze.behavior.TweenBehavior;
import com.game.themaze.entity.EntityHelper;
import com.game.themaze.graphics.TMImage;
import com.game.themaze.graphics.TMSpriteLayer;
import com.game.themaze.sound.TMSound;
import com.game.themaze.utility.TMGlobal;
import com.game.themaze.utility.TMManager;

public class TitleScreen extends Screen {

	protected GameEntity _title;
	protected GameEntity _scrollingBackground;
	protected GameEntity _newGameButton;
	protected GameEntity _continueButton;
	protected GameEntity _creditsButton;
	protected GameEntity _soundButton;
	
	protected boolean _buttonClicked;
	protected boolean _newGameClicked;
	protected boolean _continueClicked;
	protected boolean _levelSelectClicked;
	protected boolean _creditsClicked;
	
	public TitleScreen() {
		_type = TMScreenType.TITLE_SCREEN;
		_screenMusic = TMSound.BADLOOP;
		_backBtnCtl = ButtonControlType.DEFAULT;
	}
	
	@Override
	public void onHandleMessage(Message message) {
		if (message.Type == MessageType.BUTTON_CLICKED && !_buttonClicked) {
			GameEntity entity = message.getData();
			if (entity == _newGameButton) {
				_buttonClicked = true;
				_newGameClicked = true;
				((TweenBehavior)_newGameButton.getBehavior(TMBehaviorType.TWEEN)).setTweenTime(500);
				TMManager.Level.setCurrentLevel(1);
			}
			else if (entity == _continueButton) {
				_buttonClicked = true;
				_continueClicked = true;
				((TweenBehavior)_continueButton.getBehavior(TMBehaviorType.TWEEN)).setTweenTime(500);
			}
			else if (entity == _creditsButton) {
				_buttonClicked = true;
				_creditsClicked = true;
				((TweenBehavior)_creditsButton.getBehavior(TMBehaviorType.TWEEN)).setTweenTime(500);
			}
			else if (entity == _soundButton)
				toggleSound();
			
			if (_buttonClicked) {
				if (_newGameButton != null)
					_newGameButton.enableBehaviors(TMBehaviorType.TWEEN);
				if (_continueButton != null)
					_continueButton.enableBehaviors(TMBehaviorType.TWEEN);
				_creditsButton.enableBehaviors(TMBehaviorType.TWEEN);
				_soundButton.enableBehaviors(TMBehaviorType.TWEEN);
				
				if (_newGameClicked)
					_code = TMScreenCode.TRANSITION_JOURNAL;
				else if (_continueClicked)
					_code = TMScreenCode.TRANSITION_LEVEL_SELECT;
				else if (_creditsClicked)
					_code = TMScreenCode.TRANSITION_CREDITS;
			}
		}
		else if (message.Type == MessageType.ANIMATION_FINISHED) {
//			Sprite sprite = message.getData();
//			if (sprite == _titleBg.Attributes.Sprite) {
//				if (_newGameClicked || _continueClicked)
//					_code = ScreenCode.TRANSITION_JOURNAL;
//				else if (_levelSelectClicked)
//					_code = ScreenCode.TRANSITION_LEVEL_SELECT;
//				else if (_creditsClicked)
//					_code = ScreenCode.TRANSITION_CREDITS;
//			}
		}
	}

	@Override
	public void onInit() {
		_buttonClicked = false;
		_newGameClicked = false;
		_continueClicked = false;
		_levelSelectClicked = false;
		_creditsClicked = false;
	
		_scrollingBackground = scrollingBackground();
		_entities.add(_scrollingBackground);
		
		_title = EntityHelper.graphic(TMImage.TITLE_LOGO, TMSpriteLayer.UI_LOW, false,
				Global.Renderer.Width, Global.Renderer.Width / 2f, 
				false, 0, Global.Renderer.Height - Global.Renderer.Width / 2f);
		_entities.add(_title);
		
		if (TMGlobal.TMSettings.getCompletedLevelCount() > 0) {
			_continueButton = continueButton();
			_entities.add(_continueButton);
		}
		else {
			_newGameButton = newGameButton();
			_entities.add(_newGameButton);
		}
		
		_creditsButton = creditsButton();
		_entities.add(_creditsButton);
		_soundButton = soundButton();
		_entities.add(_soundButton);
		
		Manager.Message.subscribe(this, MessageType.BUTTON_CLICKED | 
				MessageType.ANIMATION_FINISHED | 
				MessageType.TWEEN_FINISHED);
	}
	
	@Override
	protected void enableBehaviors() {
		_title.enableBehaviors();
		_scrollingBackground.enableBehaviors();
		if (_continueButton != null)
			_continueButton.enableBehaviors(BehaviorType.ALL & ~TMBehaviorType.TWEEN);
		if (_newGameButton != null)
			_newGameButton.enableBehaviors(BehaviorType.ALL & ~TMBehaviorType.TWEEN);
		_creditsButton.enableBehaviors(BehaviorType.ALL & ~TMBehaviorType.TWEEN);
		_soundButton.enableBehaviors(BehaviorType.ALL & ~TMBehaviorType.TWEEN);
	}

	@Override
	public void onPause() {
		Global.Data.ScrollingBackgroundPos = ((ScrollingTileBehavior)_scrollingBackground.getBehavior(TMBehaviorType.SCROLLING_TILE)).getSpritePosition();
		Manager.Message.unsubscribe(this, MessageType.ALL);
	}

	@Override
	public void onUnpause() {
		_buttonClicked = false;
		_newGameClicked = false;
		_continueClicked = false;
		_levelSelectClicked = false;
		_creditsClicked = false;
		
		if (_newGameButton != null) {
			_newGameButton.Attributes.Sprite.Alpha = 1f;
			_newGameButton.disableBehaviors(TMBehaviorType.TWEEN);
		}
		if (_continueButton != null) {
			_continueButton.Attributes.Sprite.Alpha = 1f;
			_continueButton.disableBehaviors(TMBehaviorType.TWEEN);
		}
		
		_creditsButton.Attributes.Sprite.Alpha = 1f;
		_creditsButton.disableBehaviors(TMBehaviorType.TWEEN);
		_soundButton.Attributes.Sprite.Alpha = 1f;
		_soundButton.disableBehaviors(TMBehaviorType.TWEEN);
		
		((ScrollingTileBehavior)_scrollingBackground.getBehavior(TMBehaviorType.SCROLLING_TILE)).setSpritePosition(Global.Data.ScrollingBackgroundPos);
		
		Manager.Message.subscribe(this, MessageType.BUTTON_CLICKED | MessageType.ANIMATION_FINISHED | MessageType.TWEEN_FINISHED);
	}

	@Override
	public void onClose() {
		Global.Data.ScrollingBackgroundPos = ((ScrollingTileBehavior)_scrollingBackground.getBehavior(TMBehaviorType.SCROLLING_TILE)).getSpritePosition();
		Manager.Message.unsubscribe(this, MessageType.ALL);
	}

	@Override
	public void onBackDown() {
		//Global.Activity.finish();
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
	protected GameEntity creditsButton() {
		GameEntity button = EntityHelper.button(TMImage.CREDITS_BUTTON, 
				TMSpriteLayer.UI_LOW, false, Global.Renderer.Width / 6f, Global.Renderer.Width / 6f, 
				false, Global.Renderer.Width / 60f, Global.Renderer.Width / 60f, AreaType.Circle);
		button.addBehavior(new TweenBehavior(TweenType.ALPHA, -1f, 250));
		return button;
	}
	
	protected GameEntity soundButton() {
		GameEntity button = EntityHelper.button(TMImage.SOUND_BUTTON,
				TMSpriteLayer.UI_LOW, false, Global.Renderer.Width / 6f, Global.Renderer.Width / 6f, false, 
				Global.Renderer.Width - (11f * Global.Renderer.Width / 60f), Global.Renderer.Width / 60f, 
				AreaType.Circle);
		button.addBehavior(new TweenBehavior(TweenType.ALPHA, -1f, 250));
		
		if (Manager.Sound.isEnabled())
			button.Attributes.Sprite.setFrame(0);
		else
			button.Attributes.Sprite.setFrame(1);
		
		return button;
	}
	
	protected GameEntity scrollingBackground() {
		return EntityHelper.scrollingGraphic(TMImage.SCROLLING_STONE_WALL, TMSpriteLayer.BACKGROUND1, Direction.LEFT, 1f, Global.Data.ScrollingBackgroundPos, TMGlobal.Renderer.Width * 1.8f, true);
	}
	
	protected GameEntity newGameButton() {
		return titleButton(true, TMImage.NEW_GAME_BUTTON);
	}
	
	protected GameEntity continueButton() {
		return titleButton(true, TMImage.CONTINUE_BUTTON);
	}
	
	protected GameEntity titleButton(boolean top, int image) {
		float centerX = Global.Renderer.Width / 2f;
		float centerY = 0;
		
		if  (top)
			centerY = Global.Renderer.Height / 2f;
		else
			centerY = Global.Renderer.Height / 2f - Global.Renderer.Width / 4f;
		
		GameEntity entity = EntityHelper.button(image, 
				TMSpriteLayer.UI_LOW, 
				false, 
				Global.Renderer.Width / 2.5f, 
				Global.Renderer.Width / 5f,
				true,
				centerX,
				centerY,
				AreaType.Rectangle);
		entity.addBehavior(new TweenBehavior(TweenType.ALPHA, -1f, 250));
		
		return entity;
	}
}
