package com.game.themaze.screen;

import com.game.loblib.entity.GameEntity;
import com.game.loblib.messaging.Message;
import com.game.loblib.messaging.MessageType;
import com.game.loblib.screen.Screen;
import com.game.loblib.sound.Sound;
import com.game.loblib.utility.FullScreenType;
import com.game.loblib.utility.Global;
import com.game.loblib.utility.Manager;
import com.game.loblib.utility.area.AreaType;
import com.game.themaze.entity.EntityHelper;
import com.game.themaze.graphics.TMImage;
import com.game.themaze.graphics.TMSpriteLayer;
import com.game.themaze.utility.TMManager;

public class JournalScreen extends Screen {

	protected GameEntity _continueButton;
	
	public JournalScreen() {
		_type = TMScreenType.JOURNAL;
		_screenMusic = Sound.CONTINUE_MUSIC;
	}
	
	@Override
	public void onHandleMessage(Message message) {
		if (message.Type == MessageType.BUTTON_CLICKED) {
			GameEntity entity = message.getData();
			if (entity == _continueButton) {
				_screenData.setCode(TMScreenCode.TRANSITION);
				_screenData.setActionScreen(TMScreenType.LEVEL);
			}
		}
	}

	@Override
	public void onInit(Object input) {		
		_continueButton = EntityHelper.button(TMImage.CONTINUE_BUTTON,
				TMSpriteLayer.UI_LOW, 
				false, 
				Global.Renderer.Width / 2.5f, 
				Global.Renderer.Width / 5f, 
				false, 
				Global.Renderer.Width - Global.Renderer.Width / 2.5f - Global.Renderer.Width / 10f, 
				Global.Renderer.Width / 60f, 
				AreaType.Rectangle);
		_entities.add(_continueButton);
		_entities.add(EntityHelper.fullscreenGraphic(TMImage.JOURNAL_PAPER, 
				TMSpriteLayer.BACKGROUND1, 
				FullScreenType.FixedY));
		addText(TMManager.Level.getCurrentLevel());
		
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
	
	protected void addText(int level) {
		int image = TMImage.JOURNAL_TEST;
		
		switch (level) {
		case 01:
			image = TMImage.JOURNAL_LEVEL_01;
			break;
		case 02:
			image = TMImage.JOURNAL_LEVEL_02;
			break;
		case 03:
			image = TMImage.JOURNAL_LEVEL_03;
			break;
		case 04:
			image = TMImage.JOURNAL_LEVEL_04;
			break;
		}
		
		_entities.add(EntityHelper.graphic(image, 
				TMSpriteLayer.UI_LOW, 
				false, 
				Global.Renderer.Width - Global.Renderer.Width / 8f, 
				(Global.Renderer.Width - Global.Renderer.Width / 8f) * 2, 
				false, 
				Global.Renderer.Width / 16f, 
				Global.Renderer.Height - (Global.Renderer.Width - Global.Renderer.Width / 8f) * 2));
		
	}
}
