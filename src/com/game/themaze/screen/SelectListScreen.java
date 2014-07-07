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
import com.game.loblib.utility.android.FixedSizeArray;
import com.game.loblib.utility.area.Area;
import com.game.loblib.utility.area.AreaType;
import com.game.loblib.utility.area.Vertex;
import com.game.themaze.behavior.ScreenDragBehavior;
import com.game.themaze.behavior.TMBehaviorType;
import com.game.themaze.entity.EntityHelper;
import com.game.themaze.graphics.TMImage;
import com.game.themaze.graphics.TMSpriteLayer;

public class SelectListScreen extends Screen {

	FixedSizeArray<String> _options;
	protected Vertex _previousLocation;
	protected Vertex _newLocation;
	protected boolean _dragging;
	protected float _dragSpeed;
	
	protected GameEntity _background;
	protected GameEntity _screenDrag;
	protected FixedSizeArray<GameEntity> _optionButtons;
	protected GameEntity _selectButton;
	
	public SelectListScreen() {
		_type = TMScreenType.SELECT_LIST;
		_backBtnCtl = ButtonControlType.OVERRIDE;
		
		_previousLocation = new Vertex();
		_newLocation = new Vertex();
	}
	
	@Override
	public void onBackDown() {
		_screenData.setCode(ScreenCode.POP);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void onInit(Object input) {
		Manager.Message.subscribe(this, MessageType.BUTTON_CLICKED | MessageType.DRAG_START | MessageType.DRAG_STOP);

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
		_dragging = false;
		_dragSpeed = 0f;
		
		// setup option entities
		float buttonHeight = Global.Renderer.Height / 20f;
		float buttonBuffer = Global.Renderer.Height / 200f;
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
			GameEntity button = EntityHelper.button(TMImage.BACK_BUTTON, TMSpriteLayer.UI_LOW, false, buttonHeight * 2f, buttonHeight, true, Global.Renderer.Width / 2f, Global.Renderer.Height - buttonBuffer - (buttonHeight + buttonBuffer) * i, AreaType.Rectangle);
			
			_optionButtons.add(button);
		}
		_entities.addAll(_optionButtons);
		
		_selectButton = EntityHelper.textButton("CALIBRI BIG", "Select", false, true, false, Global.Renderer.Width / 2f, buttonBuffer, 1f, 1f, 1f, 1f);
		_entities.add(_selectButton);		
	}
	
	@Override
	public void onActiveUpdate(float updateRatio) {
		if (_dragging || _dragSpeed != 0) {
			((ScreenDragBehavior)_screenDrag.getBehavior(TMBehaviorType.SCREEN_DRAG)).getCurrentLocation(_newLocation);
			float yChange = _newLocation.Y - _previousLocation.Y;
			for (int i = 0; i < _optionButtons.getCount(); i++) {
				_optionButtons.get(i).Attributes.Area.Position.Y += yChange;
			}
			Area.sync(_previousLocation, _newLocation);
		}
	}
	
	@Override
	public void onHandleMessage(Message message) {
		if (message.Type == MessageType.BUTTON_CLICKED) {
			GameEntity entity = message.getData();
		}
		else if (message.Type == MessageType.DRAG_START) {
			GameEntity entity = message.getData();
			_dragging = true;
			((ScreenDragBehavior)entity.getBehavior(TMBehaviorType.SCREEN_DRAG)).getStartLocation(_previousLocation);
		}
		else if (message.Type == MessageType.DRAG_STOP) {
			GameEntity entity = message.getData();
			float lastSpeed = ((ScreenDragBehavior)entity.getBehavior(TMBehaviorType.SCREEN_DRAG)).getLastYDragSpeed();
			if (Math.abs(lastSpeed) < Global.Renderer.Height / 25f) {
				if (lastSpeed < 0)
					_dragSpeed = -Global.Renderer.Height / 25f;
				else 
					_dragSpeed = Global.Renderer.Height / 25f;
			}
			else 
				_dragSpeed = lastSpeed;
			_dragging = false;
		}
	}

	@Override
	protected void onPause() {
		Manager.Message.unsubscribe(this, MessageType.ALL);
	}

	@Override
	protected void onUnpause() {
		Manager.Message.subscribe(this, MessageType.BUTTON_CLICKED | MessageType.DRAG_START | MessageType.DRAG_STOP);
	}

	@Override
	protected void onClose() {
		Manager.Message.unsubscribe(this, MessageType.ALL);
	}

}
