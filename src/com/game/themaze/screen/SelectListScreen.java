package com.game.themaze.screen;

import com.game.loblib.entity.GameEntity;
import com.game.loblib.messaging.Message;
import com.game.loblib.messaging.MessageType;
import com.game.loblib.screen.Screen;
import com.game.loblib.screen.ScreenCode;
import com.game.loblib.utility.ButtonControlType;
import com.game.loblib.utility.Direction;
import com.game.loblib.utility.Global;
import com.game.loblib.utility.Logger;
import com.game.loblib.utility.Manager;
import com.game.loblib.utility.MathHelper;
import com.game.loblib.utility.android.FixedSizeArray;
import com.game.loblib.utility.area.AreaType;
import com.game.themaze.behavior.ScreenDragBehavior;
import com.game.themaze.behavior.TMBehaviorType;
import com.game.themaze.entity.EntityHelper;
import com.game.themaze.graphics.TMImage;
import com.game.themaze.graphics.TMSpriteLayer;

public class SelectListScreen extends Screen {

	protected final static float SELECT_POSITION_Y = Global.Renderer.Height / 10f;
	protected final static float SELECT_SIZE_Y = Global.Renderer.Height / 10f;
	protected final static float BUTTON_HEIGHT = Global.Renderer.Height / 20f;
	protected final static float BUTTON_BUFFER = Global.Renderer.Height / 200f;
	
	FixedSizeArray<String> _options;
	protected GameEntity _background;
	protected GameEntity _screenDrag;
	protected FixedSizeArray<GameEntity> _optionButtons;
	protected GameEntity _selectButton;
	
	public SelectListScreen() {
		_type = TMScreenType.SELECT_LIST;
		_backBtnCtl = ButtonControlType.OVERRIDE;
	}
	
	@Override
	public void onBackDown() {
		_screenData.setCode(ScreenCode.POP);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void onInit(Object input) {
		Manager.Message.subscribe(this, MessageType.BUTTON_CLICKED);

		try {
			_options = (FixedSizeArray<String>)input;
		}
		catch (ClassCastException e) {
			Logger.e(_tag, "Input should be of type FixedSizeArray<String>");
		}
		
		_background = EntityHelper.scrollingGraphic(TMImage.SCROLLING_STONE_WALL, TMSpriteLayer.BACKGROUND1, Direction.LEFT, 1f, Global.Data.ScrollingBackgroundPos, Global.Renderer.Width * 1.8f, true);
		_entities.add(_background);
		
		_entities.add(EntityHelper.text("CALIBRI BIG", "TEXT", 0, 0, false, false, Global.Renderer.Width, 1f, 1f, 1f, 1f));
		
		// Setup drag parameters
		_screenDrag = new GameEntity();
		_screenDrag.addBehavior(new ScreenDragBehavior(Global.Renderer.Width / 10f));
		_entities.add(_screenDrag);

		// setup option entities
		_optionButtons = new FixedSizeArray<GameEntity>(_options.getCount()); 
		for (int i = 0; i < _options.getCount(); i++) {
//			GameEntity button = EntityHelper.textButton("CALIBRI BIG", 
//					_options.get(i), 
//					false, 
//					true, 
//					false, 
//					Global.Renderer.Width / 2f, 
//					Global.Renderer.Height - buttonBuffer - (buttonHeight + buttonBuffer) * i, 
//					1f, 
//					1f, 
//					1f, 
//					1f);
			GameEntity button = EntityHelper.button(TMImage.BACK_BUTTON, TMSpriteLayer.UI_LOW, false, BUTTON_HEIGHT * 2f, BUTTON_HEIGHT, true, Global.Renderer.Width / 2f, Global.Renderer.Height - BUTTON_BUFFER - (BUTTON_HEIGHT + BUTTON_BUFFER) * i, AreaType.Rectangle);
			
			_optionButtons.add(button);
		}
		_entities.addAll(_optionButtons);
		
		_selectButton = EntityHelper.textButton("CALIBRI BIG", "Select", false, true, false, Global.Renderer.Width / 2f, BUTTON_BUFFER, 1f, 1f, 1f, 1f);
		_entities.add(_selectButton);		
	}
	
	@Override
	public void onActiveUpdate(float updateRatio) {
		float yChange = ((ScreenDragBehavior)_screenDrag.getBehavior(TMBehaviorType.SCREEN_DRAG)).getCurrentYDragDistance();

		if (yChange != 0 && _optionButtons.getCount() > 0) {
			GameEntity firstButton = _optionButtons.get(0);
			GameEntity lastButton = _optionButtons.get(_optionButtons.getCount() - 1);
			
			// if buttons don't take up whole height don't scroll at all
			float scrollAreaHeight = Global.Renderer.Height - SELECT_POSITION_Y + SELECT_SIZE_Y;
			float buttonAreaHeight = (BUTTON_BUFFER + BUTTON_HEIGHT) * _optionButtons.getCount();
			
			if (buttonAreaHeight > scrollAreaHeight) {
				// clamp change value to ensure buttons don't scroll down past select button or up off screen
				float min = Global.Renderer.Height - firstButton.Attributes.Area.Position.Y - firstButton.Attributes.Area.getHeight();
				float max = SELECT_POSITION_Y + SELECT_SIZE_Y - lastButton.Attributes.Area.Position.Y;
				yChange = MathHelper.clamp(min, max, yChange);
				
				for (int i = 0; i < _optionButtons.getCount(); i++) {
					_optionButtons.get(i).Attributes.Area.Position.Y += yChange;
				}
			}
		}
	}
	
	@Override
	public void onHandleMessage(Message message) {
		if (message.Type == MessageType.BUTTON_CLICKED) {
			//GameEntity entity = message.getData();
		}
	}

	@Override
	protected void onPause() {
		Manager.Message.unsubscribe(this, MessageType.ALL);
	}

	@Override
	protected void onUnpause() {
		Manager.Message.subscribe(this, MessageType.BUTTON_CLICKED);
	}

	@Override
	protected void onClose() {
		Manager.Message.unsubscribe(this, MessageType.ALL);
	}

}
