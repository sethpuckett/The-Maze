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

public class CreditsScreen extends Screen {

	protected GameEntity _background;
	protected GameEntity _backButton;
	
	public CreditsScreen() {
		_type = TMScreenType.CREDITS;
		_screenMusic = Sound.CONTINUE_MUSIC;
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
			if (entity == _backButton) {
				_code = TMScreenCode.TRANSITION_TITLE;
			}
		}
	}

	@Override
	public void onInit() {
		_background = EntityHelper.scrollingGraphic(TMImage.SCROLLING_STONE_WALL, TMSpriteLayer.BACKGROUND1, Direction.LEFT, 1f, Global.Data.ScrollingBackgroundPos, Global.Renderer.Width * 1.8f, true);
		_entities.add(_background);
		
		_entities.add(EntityHelper.graphic(TMImage.CREDITS,
				TMSpriteLayer.FOREGROUND, false, Global.Renderer.Width, Global.Renderer.Width, false, 0, Global.Renderer.Height - Global.Renderer.Width - (Global.Renderer.Width / 10f)));
		
		_backButton = EntityHelper.button(TMImage.BACK_BUTTON,
				TMSpriteLayer.UI_LOW, false, Global.Renderer.Width / 3f, Global.Renderer.Width / 6f, false, Global.Renderer.Width / 60f, Global.Renderer.Width / 60f, AreaType.Rectangle);
		_entities.add(_backButton);
		Manager.Message.subscribe(this, MessageType.BUTTON_CLICKED);
	}

	@Override
	public void onPause() {
		Manager.Message.unsubscribe(this, MessageType.BUTTON_CLICKED);
	}

	@Override
	public void onUnpause() {
		Manager.Message.subscribe(this, MessageType.BUTTON_CLICKED);
	}

	@Override
	public void onClose() {
		Global.Data.ScrollingBackgroundPos = ((ScrollingTileBehavior)_background.getBehavior(TMBehaviorType.SCROLLING_TILE)).getSpritePosition();
		Manager.Message.unsubscribe(this, MessageType.BUTTON_CLICKED);
	}
}
