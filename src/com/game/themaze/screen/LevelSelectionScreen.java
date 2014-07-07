package com.game.themaze.screen;

import com.game.loblib.entity.GameEntity;
import com.game.loblib.messaging.Message;
import com.game.loblib.messaging.MessageType;
import com.game.loblib.screen.Screen;
import com.game.loblib.utility.ButtonControlType;
import com.game.loblib.utility.Direction;
import com.game.loblib.utility.Global;
import com.game.loblib.utility.Manager;
import com.game.loblib.utility.MathHelper;
import com.game.loblib.utility.android.FixedSizeArray;
import com.game.loblib.utility.area.Area;
import com.game.loblib.utility.area.AreaType;
import com.game.loblib.utility.area.Vertex;
import com.game.themaze.behavior.ScreenDragBehavior;
import com.game.themaze.behavior.ScrollingTileBehavior;
import com.game.themaze.behavior.TMBehaviorType;
import com.game.themaze.entity.EntityHelper;
import com.game.themaze.graphics.TMImage;
import com.game.themaze.graphics.TMSpriteLayer;
import com.game.themaze.sound.TMSound;
import com.game.themaze.utility.TMGlobal;
import com.game.themaze.utility.TMManager;

public class LevelSelectionScreen extends Screen {

	protected int _currentChapter;
	protected boolean _buttonDown;
	protected boolean _dragging;
	protected float _dragSpeed;
	protected Vertex _previousLocation;
	protected Vertex _newLocation;
	protected int _maxChapter;
	
	protected GameEntity _cameraRail;
	protected GameEntity _background;
	protected GameEntity _titleScreenButton;
	protected GameEntity _screenDrag;
	protected FixedSizeArray<GameEntity> _levelButtons;
	
	public LevelSelectionScreen() {
		_type = TMScreenType.LEVEL_SELECT;
		_screenMusic = TMSound.BADLOOP;
		_backBtnCtl = ButtonControlType.OVERRIDE;
		
		_previousLocation = new Vertex();
		_newLocation = new Vertex();
	}
	
	@Override
	protected void onInit(Object input) {
		_background = EntityHelper.scrollingGraphic(TMImage.SCROLLING_STONE_WALL, TMSpriteLayer.BACKGROUND1, Direction.LEFT, 1f, Global.Data.ScrollingBackgroundPos, Global.Renderer.Width * 1.8f, true);
		_entities.add(_background);
		_titleScreenButton = EntityHelper.button(TMImage.BACK_BUTTON, TMSpriteLayer.UI_LOW, false, Global.Renderer.Width / 2.5f, Global.Renderer.Width / 5f, false, Global.Renderer.Width / 60f, Global.Renderer.Width / 60f, AreaType.Rectangle);
		_entities.add(_titleScreenButton);
		
		_screenDrag = new GameEntity();
		_screenDrag.addBehavior(new ScreenDragBehavior(Global.Renderer.Width / 10f));
		_entities.add(_screenDrag);
		
		_dragging = false;
		_dragSpeed = 0f;
		
		float screenX = Global.Renderer.Width;
		float screenY = Global.Renderer.Height;
		float buffer = screenX / 60f;
		float gridSize = screenX / 5f;
		float topBuffer = screenX / 5f;
		float sizeX = gridSize - (buffer * 2f);
		float sizeY = sizeX;
		_levelButtons = new FixedSizeArray<GameEntity>(34);
		_levelButtons.add(EntityHelper.button(getButtonImage(1), TMSpriteLayer.UI_LOW, true, sizeX, sizeY, false, buffer, topBuffer + (gridSize * 4) + buffer, AreaType.Rectangle, true));
		_levelButtons.add(EntityHelper.button(getButtonImage(2), TMSpriteLayer.UI_LOW, true, sizeX, sizeY, false, gridSize + buffer, topBuffer + (gridSize * 4) + buffer, AreaType.Rectangle, true));
		_levelButtons.add(EntityHelper.button(getButtonImage(3), TMSpriteLayer.UI_LOW, true, sizeX, sizeY, false, (gridSize * 2) + buffer, topBuffer + (gridSize * 4) + buffer, AreaType.Rectangle, true));
		_levelButtons.add(EntityHelper.button(getButtonImage(4), TMSpriteLayer.UI_LOW, true, sizeX, sizeY, false, (gridSize * 3) + buffer, topBuffer + (gridSize * 4) + buffer, AreaType.Rectangle, true));
		
		_levelButtons.add(EntityHelper.button(getButtonImage(5), TMSpriteLayer.UI_LOW, true, sizeX, sizeY, false, screenX + buffer, topBuffer + (gridSize * 4) + buffer, AreaType.Rectangle, true));
		_levelButtons.add(EntityHelper.button(getButtonImage(6), TMSpriteLayer.UI_LOW, true, sizeX, sizeY, false, screenX + gridSize + buffer, topBuffer + (gridSize * 4) + buffer, AreaType.Rectangle, true));
		_levelButtons.add(EntityHelper.button(getButtonImage(7), TMSpriteLayer.UI_LOW, true, sizeX, sizeY, false, screenX + (gridSize * 2) + buffer, topBuffer + (gridSize * 4) + buffer, AreaType.Rectangle, true));
		_levelButtons.add(EntityHelper.button(getButtonImage(8), TMSpriteLayer.UI_LOW, true, sizeX, sizeY, false, screenX + (gridSize * 3) + buffer, topBuffer + (gridSize * 4) + buffer, AreaType.Rectangle, true));
		_levelButtons.add(EntityHelper.button(getButtonImage(9), TMSpriteLayer.UI_LOW, true, sizeX, sizeY, false, screenX + (gridSize * 4) + buffer, topBuffer + (gridSize * 4) + buffer, AreaType.Rectangle, true));

		_levelButtons.add(EntityHelper.button(getButtonImage(10), TMSpriteLayer.UI_LOW, true, sizeX, sizeY, false, (screenX * 2) + buffer, topBuffer + (gridSize * 4) + buffer, AreaType.Rectangle, true));
		_levelButtons.add(EntityHelper.button(getButtonImage(11), TMSpriteLayer.UI_LOW, true, sizeX, sizeY, false, (screenX * 2) + gridSize + buffer, topBuffer + (gridSize * 4) + buffer, AreaType.Rectangle, true));
		_levelButtons.add(EntityHelper.button(getButtonImage(12), TMSpriteLayer.UI_LOW, true, sizeX, sizeY, false, (screenX * 2) + (gridSize * 2) + buffer, topBuffer + (gridSize * 4) + buffer, AreaType.Rectangle, true));
		_levelButtons.add(EntityHelper.button(getButtonImage(13), TMSpriteLayer.UI_LOW, true, sizeX, sizeY, false, (screenX * 2) + (gridSize * 3) + buffer, topBuffer + (gridSize * 4) + buffer, AreaType.Rectangle, true));
		_levelButtons.add(EntityHelper.button(getButtonImage(14), TMSpriteLayer.UI_LOW, true, sizeX, sizeY, false, (screenX * 2) + (gridSize * 4) + buffer, topBuffer + (gridSize * 4) + buffer, AreaType.Rectangle, true));

		_levelButtons.add(EntityHelper.button(getButtonImage(15), TMSpriteLayer.UI_LOW, true, sizeX, sizeY, false, (screenX * 3) + buffer, topBuffer + (gridSize * 4) + buffer, AreaType.Rectangle, true));
		_levelButtons.add(EntityHelper.button(getButtonImage(16), TMSpriteLayer.UI_LOW, true, sizeX, sizeY, false, (screenX * 3) + gridSize + buffer, topBuffer + (gridSize * 4) + buffer, AreaType.Rectangle, true));
		_levelButtons.add(EntityHelper.button(getButtonImage(17), TMSpriteLayer.UI_LOW, true, sizeX, sizeY, false, (screenX * 3) + (gridSize * 2) + buffer, topBuffer + (gridSize * 4) + buffer, AreaType.Rectangle, true));
		_levelButtons.add(EntityHelper.button(getButtonImage(18), TMSpriteLayer.UI_LOW, true, sizeX, sizeY, false, (screenX * 3) + (gridSize * 3) + buffer, topBuffer + (gridSize * 4) + buffer, AreaType.Rectangle, true));
		_levelButtons.add(EntityHelper.button(getButtonImage(19), TMSpriteLayer.UI_LOW, true, sizeX, sizeY, false, (screenX * 3) + (gridSize * 4) + buffer, topBuffer + (gridSize * 4) + buffer, AreaType.Rectangle, true));

		_levelButtons.add(EntityHelper.button(getButtonImage(20), TMSpriteLayer.UI_LOW, true, sizeX, sizeY, false, (screenX * 4) + buffer, topBuffer + (gridSize * 4) + buffer, AreaType.Rectangle, true));
		_levelButtons.add(EntityHelper.button(getButtonImage(21), TMSpriteLayer.UI_LOW, true, sizeX, sizeY, false, (screenX * 4) + gridSize + buffer, topBuffer + (gridSize * 4) + buffer, AreaType.Rectangle, true));
		_levelButtons.add(EntityHelper.button(getButtonImage(22), TMSpriteLayer.UI_LOW, true, sizeX, sizeY, false, (screenX * 4) + (gridSize * 2) + buffer, topBuffer + (gridSize * 4) + buffer, AreaType.Rectangle, true));
		_levelButtons.add(EntityHelper.button(getButtonImage(23), TMSpriteLayer.UI_LOW, true, sizeX, sizeY, false, (screenX * 4) + (gridSize * 3) + buffer, topBuffer + (gridSize * 4) + buffer, AreaType.Rectangle, true));
		_levelButtons.add(EntityHelper.button(getButtonImage(24), TMSpriteLayer.UI_LOW, true, sizeX, sizeY, false, (screenX * 4) + (gridSize * 4) + buffer, topBuffer + (gridSize * 4) + buffer, AreaType.Rectangle, true));

		_levelButtons.add(EntityHelper.button(getButtonImage(25), TMSpriteLayer.UI_LOW, true, sizeX, sizeY, false, (screenX * 5) + buffer, topBuffer + (gridSize * 4) + buffer, AreaType.Rectangle, true));
		_levelButtons.add(EntityHelper.button(getButtonImage(26), TMSpriteLayer.UI_LOW, true, sizeX, sizeY, false, (screenX * 5) + gridSize + buffer, topBuffer + (gridSize * 4) + buffer, AreaType.Rectangle, true));
		_levelButtons.add(EntityHelper.button(getButtonImage(27), TMSpriteLayer.UI_LOW, true, sizeX, sizeY, false, (screenX * 5) + (gridSize * 2) + buffer, topBuffer + (gridSize * 4) + buffer, AreaType.Rectangle, true));
		_levelButtons.add(EntityHelper.button(getButtonImage(28), TMSpriteLayer.UI_LOW, true, sizeX, sizeY, false, (screenX * 5) + (gridSize * 3) + buffer, topBuffer + (gridSize * 4) + buffer, AreaType.Rectangle, true));
		_levelButtons.add(EntityHelper.button(getButtonImage(29), TMSpriteLayer.UI_LOW, true, sizeX, sizeY, false, (screenX * 5) + (gridSize * 4) + buffer, topBuffer + (gridSize * 4) + buffer, AreaType.Rectangle, true));

		_levelButtons.add(EntityHelper.button(getButtonImage(15), TMSpriteLayer.UI_LOW, true, sizeX, sizeY, false, (screenX * 6) + buffer, topBuffer + (gridSize * 4) + buffer, AreaType.Rectangle, true));
		_levelButtons.add(EntityHelper.button(getButtonImage(16), TMSpriteLayer.UI_LOW, true, sizeX, sizeY, false, (screenX * 6) + gridSize + buffer, topBuffer + (gridSize * 4) + buffer, AreaType.Rectangle, true));
		_levelButtons.add(EntityHelper.button(getButtonImage(17), TMSpriteLayer.UI_LOW, true, sizeX, sizeY, false, (screenX * 6) + (gridSize * 2) + buffer, topBuffer + (gridSize * 4) + buffer, AreaType.Rectangle, true));
		_levelButtons.add(EntityHelper.button(getButtonImage(18), TMSpriteLayer.UI_LOW, true, sizeX, sizeY, false, (screenX * 6) + (gridSize * 3) + buffer, topBuffer + (gridSize * 4) + buffer, AreaType.Rectangle, true));
		_levelButtons.add(EntityHelper.button(getButtonImage(19), TMSpriteLayer.UI_LOW, true, sizeX, sizeY, false, (screenX * 6) + (gridSize * 4) + buffer, topBuffer + (gridSize * 4) + buffer, AreaType.Rectangle, true));
		
		_entities.addAll(_levelButtons);
		
		_entities.add(EntityHelper.graphic(TMImage.CHAPTER_0_TITLE, TMSpriteLayer.UI_LOW, true, screenX, screenX / 2f, false, 0, screenY - screenX / 2f));
		_entities.add(EntityHelper.graphic(TMImage.CHAPTER_1_TITLE, TMSpriteLayer.UI_LOW, true, screenX, screenX / 2f, false, screenX, screenY - screenX / 2f));
		_entities.add(EntityHelper.graphic(TMImage.CHAPTER_2_TITLE, TMSpriteLayer.UI_LOW, true, screenX, screenX / 2f, false, screenX * 2, screenY - screenX / 2f));
		_entities.add(EntityHelper.graphic(TMImage.CHAPTER_3_TITLE, TMSpriteLayer.UI_LOW, true, screenX, screenX / 2f, false, screenX * 3, screenY - screenX / 2f));
		_entities.add(EntityHelper.graphic(TMImage.CHAPTER_4_TITLE, TMSpriteLayer.UI_LOW, true, screenX, screenX / 2f, false, screenX * 4, screenY - screenX / 2f));
		_entities.add(EntityHelper.graphic(TMImage.CHAPTER_5_TITLE, TMSpriteLayer.UI_LOW, true, screenX, screenX / 2f, false, screenX * 5, screenY - screenX / 2f));
		_entities.add(EntityHelper.graphic(TMImage.CHAPTER_6_TITLE, TMSpriteLayer.UI_LOW, true, screenX, screenX / 2f, false, screenX * 6, screenY - screenX / 2f));
		
		Global.Camera.YOffset = 0f;
		Global.Camera.CoveredArea.setPosition(0, 0);
		Global.Camera.CoveredArea.setSize(screenX * 6f, screenY);
		
		_cameraRail = new GameEntity();
		_currentChapter = TMGlobal.TMSettings.getCurrentChapter();
		switch (_currentChapter) {
		case 0:
			_cameraRail.Attributes.Area.Position.X = screenX / 2f;
			break;
		case 1:
			_cameraRail.Attributes.Area.Position.X = screenX + screenX / 2f;
			break;
		case 2:
			_cameraRail.Attributes.Area.Position.X = (screenX * 2) + screenX / 2f;
			break;
		case 3:
			_cameraRail.Attributes.Area.Position.X = (screenX * 3) + screenX / 2f;
			break;
		case 4:
			_cameraRail.Attributes.Area.Position.X = (screenX * 4) + screenX / 2f;
			break;
		case 5:
			_cameraRail.Attributes.Area.Position.X = (screenX * 5) + screenX / 2f;
			break;
		case 6:
			_cameraRail.Attributes.Area.Position.X = (screenX * 6) + screenX / 2f;
			break;
		}
		Global.Camera.Anchor = _cameraRail;
		Global.Camera.Threshold.setPosition(0f, 0f);
		
		int completedLevels = TMGlobal.TMSettings.getCompletedLevelCount();
		if (completedLevels < 4)
			_maxChapter = 0;
		else if (completedLevels < 8)
			_maxChapter = 1;
		else if (completedLevels < 12)
			_maxChapter = 2;
		else if (completedLevels < 16)
			_maxChapter = 3;
		else if (completedLevels < 20)
			_maxChapter = 4;
		else if (completedLevels < 24)
			_maxChapter = 5;
		else
			_maxChapter = 6;
		
		Manager.Message.subscribe(this, MessageType.BUTTON_CLICKED | MessageType.DRAG_START | MessageType.DRAG_STOP);
	}

	@Override
	public void onActiveUpdate(float updateRatio) {
		if (_dragging) {
			((ScreenDragBehavior)_screenDrag.getBehavior(TMBehaviorType.SCREEN_DRAG)).getCurrentLocation(_newLocation);
			float xChange = _newLocation.X - _previousLocation.X;
			_cameraRail.Attributes.Area.Position.X -= xChange;
			_cameraRail.Attributes.Area.Position.X = MathHelper.clamp(Global.Renderer.Width / 2f, getMaxScrollForChapter(_maxChapter), _cameraRail.Attributes.Area.Position.X);
			Area.sync(_previousLocation, _newLocation);
			if (xChange < 0 && _cameraRail.Attributes.Area.Position.X >= getCameraStopUp()) {
				_currentChapter++;
				TMGlobal.TMSettings.setCurrentChapter(_currentChapter);
			}
			else if (xChange > 0 && _cameraRail.Attributes.Area.Position.X <= getCameraStopDown()) {
				_currentChapter--;
				TMGlobal.TMSettings.setCurrentChapter(_currentChapter);
			}
		}
		else if (_dragSpeed != 0) {
			float oldRailX = _cameraRail.Attributes.Area.Position.X;
			_cameraRail.Attributes.Area.Position.X -= _dragSpeed * updateRatio / 1.5f;
			_cameraRail.Attributes.Area.Position.X = MathHelper.clamp(Global.Renderer.Width / 2f, getMaxScrollForChapter(_maxChapter), _cameraRail.Attributes.Area.Position.X);
			
			float lockX = getLockPosition(oldRailX, _cameraRail.Attributes.Area.Position.X);
			if (lockX > 0) {
				_dragSpeed = 0f;
				_cameraRail.Attributes.Area.Position.X = lockX;
				setChapter(lockX);
			}
			
			Area.sync(_previousLocation, _newLocation);
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
		Global.Data.ScrollingBackgroundPos = ((ScrollingTileBehavior)_background.getBehavior(TMBehaviorType.SCROLLING_TILE)).getSpritePosition();
		Manager.Message.unsubscribe(this, MessageType.ALL);
	}
	
	@Override
	public void onBackDown() {
		_screenData.setCode(TMScreenCode.TRANSITION);
		_screenData.setActionScreen(TMScreenType.TITLE_SCREEN);
	}
	
	@Override
	public void onHandleMessage(Message message) {
		if (message.Type == MessageType.BUTTON_CLICKED) {
			GameEntity entity = message.getData();
			int levIndex;
			if (entity == _titleScreenButton) {
				_screenData.setCode(TMScreenCode.TRANSITION);
				_screenData.setActionScreen(TMScreenType.TITLE_SCREEN);
			}
			else if ((levIndex = _levelButtons.find(entity, false)) != -1) {
				TMManager.Level.setCurrentLevel(levIndex + 1);
				_screenData.setCode(TMScreenCode.TRANSITION);
				_screenData.setActionScreen(TMScreenType.JOURNAL);
			}
		}
		else if (message.Type == MessageType.DRAG_START) {
			GameEntity entity = message.getData();
			_dragging = true;
			((ScreenDragBehavior)entity.getBehavior(TMBehaviorType.SCREEN_DRAG)).getStartLocation(_previousLocation);
		}
		else if (message.Type == MessageType.DRAG_STOP) {
			GameEntity entity = message.getData();
			float lastSpeed = ((ScreenDragBehavior)entity.getBehavior(TMBehaviorType.SCREEN_DRAG)).getLastXDragSpeed();
			if (Math.abs(lastSpeed) < Global.Renderer.Width / 25f) {
				if (lastSpeed < 0)
					_dragSpeed = -Global.Renderer.Width / 25f;
				else 
					_dragSpeed = Global.Renderer.Width / 25f;
			}
			else 
				_dragSpeed = lastSpeed;
			_dragging = false;
		}
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

	protected float getCameraStopDown() {
		float stop = 0f;
		switch (_currentChapter) {
		case 0:
			stop = Global.Renderer.Width / 2f;
			break;
		case 1:
			stop = Global.Renderer.Width / 2f;
			break;
		case 2:
			stop = Global.Renderer.Width + Global.Renderer.Width / 2f;
			break;
		case 3:
			stop = (Global.Renderer.Width * 2f) + Global.Renderer.Width / 2f;
			break;
		case 4:
			stop = (Global.Renderer.Width * 3f) + Global.Renderer.Width / 2f;
			break;
		case 5:
			stop = (Global.Renderer.Width * 4f) + Global.Renderer.Width / 2f;
			break;
		case 6:
			stop = (Global.Renderer.Width * 5f) + Global.Renderer.Width / 2f;
			break;
		}
		return stop;
	}
	
	protected float getCameraStopUp() {
		float stop = 0f;
		switch (_currentChapter) {
		case 0:
			stop = Global.Renderer.Width + Global.Renderer.Width / 2f;
			break;
		case 1:
			stop = (Global.Renderer.Width * 2f) + Global.Renderer.Width / 2f;
			break;
		case 2:
			stop = (Global.Renderer.Width * 3f) + Global.Renderer.Width / 2f;
			break;
		case 3:
			stop = (Global.Renderer.Width * 4f) + Global.Renderer.Width / 2f;
			break;
		case 4:
			stop = (Global.Renderer.Width * 5f) + Global.Renderer.Width / 2f;
			break;
		case 5:
			stop = (Global.Renderer.Width * 6f) + Global.Renderer.Width / 2f;
			break;
		case 6:
			stop = (Global.Renderer.Width * 6f) + Global.Renderer.Width / 2f;
			break;
		}
		return stop;
	}

	protected float getLockPosition(float oldPos, float newPos) {
		float stop = 0f;
		float currentStop = 0f;
		for (int i = 0; i < 6; i++) {
			switch (i) {
			case 0:
				currentStop =Global.Renderer.Width / 2f;
				break;
			case 1:
				currentStop = Global.Renderer.Width + Global.Renderer.Width / 2f;
				break;
			case 2:
				currentStop = (Global.Renderer.Width * 2f) + Global.Renderer.Width / 2f;
				break;
			case 3:
				currentStop = (Global.Renderer.Width * 3f) + Global.Renderer.Width / 2f;
				break;
			case 4:
				currentStop = (Global.Renderer.Width * 4f) + Global.Renderer.Width / 2f;
				break;
			case 5:
				currentStop = (Global.Renderer.Width * 5f) + Global.Renderer.Width / 2f;
				break;
			case 6:
				currentStop = (Global.Renderer.Width * 6f) + Global.Renderer.Width / 2f;
				break;
			}
			
			if ((oldPos < currentStop && newPos >= currentStop) ||
				(oldPos > currentStop && newPos <= currentStop)) {
				stop = currentStop;
				break;
			}
		}
		return stop;
	}
	
	protected void setChapter(float xPos) {
		if (xPos == Global.Renderer.Width / 2f)
			_currentChapter = 0;
		else if (xPos == Global.Renderer.Width + Global.Renderer.Width / 2f)
			_currentChapter = 1;
		else if (xPos == (Global.Renderer.Width * 2f) + Global.Renderer.Width / 2f)
			_currentChapter = 2;
		else if (xPos == (Global.Renderer.Width * 3f) + Global.Renderer.Width / 2f)
			_currentChapter = 3;
		else if (xPos == (Global.Renderer.Width * 4f) + Global.Renderer.Width / 2f)
			_currentChapter = 4;
		else if (xPos == (Global.Renderer.Width * 5f) + Global.Renderer.Width / 2f)
			_currentChapter = 5;
		
		TMGlobal.TMSettings.setCurrentChapter(_currentChapter);
	}
	
	protected float getMaxScrollForChapter(int chapter) {
		float max = 0f;
		switch (chapter) {
		case 0:
			max = Global.Renderer.Width / 2f;
			break;
		case 1:
			max = Global.Renderer.Width + Global.Renderer.Width / 2f;
			break;
		case 2:
			max = (Global.Renderer.Width * 2f) + Global.Renderer.Width / 2f;
			break;
		case 3:
			max = (Global.Renderer.Width * 3f) + Global.Renderer.Width / 2f;
			break;
		case 4:
			max = (Global.Renderer.Width * 4f) + Global.Renderer.Width / 2f;
			break;
		case 5:
			max = (Global.Renderer.Width * 5f) + Global.Renderer.Width / 2f;
			break;
		case 6:
			max = (Global.Renderer.Width * 6f) + Global.Renderer.Width / 2f;
			break;
		}
		return max;
	}
	
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

}
