package com.game.themaze.screen;

import com.game.loblib.entity.GameEntity;
import com.game.loblib.messaging.Message;
import com.game.loblib.messaging.MessageType;
import com.game.loblib.screen.Screen;
import com.game.loblib.sound.Sound;
import com.game.loblib.utility.Direction;
import com.game.loblib.utility.Global;
import com.game.loblib.utility.Manager;
import com.game.loblib.utility.area.AreaType;
import com.game.themaze.entity.EntityHelper;
import com.game.themaze.graphics.TMImage;
import com.game.themaze.graphics.TMSpriteLayer;
import com.game.themaze.utility.TMGlobal;

public class DebugScreen extends Screen {

	protected GameEntity _startButton;
	
	public DebugScreen() {
		_type = TMScreenType.DEBUG;
		_screenMusic = Sound.CONTINUE_MUSIC;
	}
	
	@Override
	public void onHandleMessage(Message message) {
		if (message.Type == MessageType.BUTTON_CLICKED) {
			GameEntity entity = message.getData();
			if (entity == _startButton) {
				_code = TMScreenCode.TRANSITION_LEVEL;
			}
		}
	}

	@Override
	public void onInit() {		
		_startButton = EntityHelper.button(TMImage.CONTINUE_BUTTON,
				TMSpriteLayer.UI_LOW, 
				false, 
				Global.Renderer.Width / 2.5f, 
				Global.Renderer.Width / 5f, 
				false, 
				Global.Renderer.Width - Global.Renderer.Width / 2.5f - Global.Renderer.Width / 10f, 
				Global.Renderer.Width / 60f, 
				AreaType.Rectangle);
		_entities.add(_startButton);

		_entities.add(EntityHelper.scrollingGraphic(TMImage.SCROLLING_STONE_WALL, TMSpriteLayer.BACKGROUND1, Direction.LEFT, 1f, Global.Data.ScrollingBackgroundPos, TMGlobal.Renderer.Width * 1.8f, true));
		
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
