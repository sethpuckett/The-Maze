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
import com.game.themaze.level.LevelSettings;
import com.game.themaze.level.LevelSettings.LevelSettingInvisibleWallType;
import com.game.themaze.level.LevelSettings.LevelSettingPeekType;
import com.game.themaze.level.WallSpriteSet;
import com.game.themaze.utility.TMGameSettings;
import com.game.themaze.utility.TMGlobal;
import com.game.themaze.utility.TMManager;

public class DebugScreen extends Screen {

	public static class MazeSize {
		public static final int Unknown = 0;
		public static final int Small = 1;
		public static final int Medium = 2;
		public static final int Large = 3;
	}
	
	public static class Zoom {
		public static final int Unknown = 0;
		public static final int Minimum = 1;
		public static final int Medium = 2;
		public static final int Maximum = 3;
	}
	
	public static class GoalIndicator {
		public static final int Unknown = 0;
		public static final int On = 1;
		public static final int Off = 2;
	}
	
	protected GameEntity _startButton;
	protected GameEntity _mazeSizeLabel;
	protected GameEntity _mazeSizeValue;
	protected GameEntity _mazeSizeButton;
	protected int _selectedMazeSize;
	protected GameEntity _zoomLabel;
	protected GameEntity _zoomValue;
	protected GameEntity _zoomButton;
	protected int _selectedZoom;
	protected GameEntity _peekLabel;
	protected GameEntity _peekValue;
	protected GameEntity _peekButton;
	protected int _selectedPeek;
	protected GameEntity _goalIndicatorLabel;
	protected GameEntity _goalIndicatorValue;
	protected GameEntity _goalIndicatorButton;
	protected int _selectedGoalIndicator;
	protected GameEntity _invisibleWallsLabel;
	protected GameEntity _invisibleWallsValue;
	protected GameEntity _invisibleWallsButton;
	protected int _selectedInvisibleWalls;
	
	public DebugScreen() {
		_type = TMScreenType.DEBUG;
		_screenMusic = Sound.CONTINUE_MUSIC;
	}
	
	@Override
	public void onHandleMessage(Message message) {
		if (message.Type == MessageType.BUTTON_CLICKED) {
			GameEntity entity = message.getData();
			if (entity == _startButton) {
				_screenData.setCode(TMScreenCode.TRANSITION);
				_screenData.setActionScreen(TMScreenType.LEVEL);
				_screenData.setInput(getLevelSettings());
				TMManager.Level.setCurrentLevel(0);
			}
			else if (entity == _mazeSizeButton) {
				_selectedMazeSize++;
				if (_selectedMazeSize > 3)
					_selectedMazeSize = 1;
				switch (_selectedMazeSize) {
				case (MazeSize.Small):
					TMManager.Sprite.release(_mazeSizeValue.Attributes.Sprite);
					_mazeSizeValue.Attributes.Sprite = TMManager.Sprite.make(TMImage.DEBUG_TEXT_MAZE_SIZE_VALUE_SMALL);
					break;
				case (MazeSize.Medium):
					TMManager.Sprite.release(_mazeSizeValue.Attributes.Sprite);
					_mazeSizeValue.Attributes.Sprite = TMManager.Sprite.make(TMImage.DEBUG_TEXT_MAZE_SIZE_VALUE_MEDIUM);
					break;
				case (MazeSize.Large):
					TMManager.Sprite.release(_mazeSizeValue.Attributes.Sprite);
					_mazeSizeValue.Attributes.Sprite = TMManager.Sprite.make(TMImage.DEBUG_TEXT_MAZE_SIZE_VALUE_LARGE);
					break;
				}
				_mazeSizeValue.Attributes.Sprite.UseCamera = false;
			}
			else if (entity == _zoomButton) {
				_selectedZoom++;
				if (_selectedZoom > 3)
					_selectedZoom = 1;
				switch (_selectedZoom) {
				case (Zoom.Minimum):
					TMManager.Sprite.release(_zoomValue.Attributes.Sprite);
					_zoomValue.Attributes.Sprite = TMManager.Sprite.make(TMImage.DEBUG_TEXT_ZOOM_VALUE_MINIMUM);
					break;
				case (Zoom.Medium):
					TMManager.Sprite.release(_zoomValue.Attributes.Sprite);
					_zoomValue.Attributes.Sprite = TMManager.Sprite.make(TMImage.DEBUG_TEXT_ZOOM_VALUE_MEDIUM);
					break;
				case (Zoom.Maximum):
					TMManager.Sprite.release(_zoomValue.Attributes.Sprite);
					_zoomValue.Attributes.Sprite = TMManager.Sprite.make(TMImage.DEBUG_TEXT_ZOOM_VALUE_MAXIMUM);
					break;
				}
				_zoomValue.Attributes.Sprite.UseCamera = false;
			}
			else if (entity == _peekButton) {
				_selectedPeek++;
				if (_selectedPeek > 3)
					_selectedPeek = 1;
				switch (_selectedPeek) {
				case (LevelSettingPeekType.Off):
					TMManager.Sprite.release(_peekValue.Attributes.Sprite);
					_peekValue.Attributes.Sprite = TMManager.Sprite.make(TMImage.DEBUG_TEXT_PEEK_VALUE_OFF);
					break;
				case (LevelSettingPeekType.Normal):
					TMManager.Sprite.release(_peekValue.Attributes.Sprite);
					_peekValue.Attributes.Sprite = TMManager.Sprite.make(TMImage.DEBUG_TEXT_PEEK_VALUE_NORMAL);
					break;
				case (LevelSettingPeekType.Maximum):
					TMManager.Sprite.release(_peekValue.Attributes.Sprite);
					_peekValue.Attributes.Sprite = TMManager.Sprite.make(TMImage.DEBUG_TEXT_PEEK_VALUE_MAXIMUM);
					break;
				}
				_peekValue.Attributes.Sprite.UseCamera = false;
			}
			else if (entity == _goalIndicatorButton) {
				_selectedGoalIndicator++;
				if (_selectedGoalIndicator > 2)
					_selectedGoalIndicator = 1;
				switch (_selectedGoalIndicator) {
				case (GoalIndicator.On):
					TMManager.Sprite.release(_goalIndicatorValue.Attributes.Sprite);
					_goalIndicatorValue.Attributes.Sprite = TMManager.Sprite.make(TMImage.DEBUG_TEXT_GOAL_INDICATOR_VALUE_ON);
					break;
				case (GoalIndicator.Off):
					TMManager.Sprite.release(_goalIndicatorValue.Attributes.Sprite);
					_goalIndicatorValue.Attributes.Sprite = TMManager.Sprite.make(TMImage.DEBUG_TEXT_GOAL_INDICATOR_VALUE_OFF);
					break;
				}
				_goalIndicatorValue.Attributes.Sprite.UseCamera = false;
			}
			else if (entity == _invisibleWallsButton) {
				_selectedInvisibleWalls++;
				if (_selectedInvisibleWalls > 5)
					_selectedInvisibleWalls = 1;
				switch (_selectedInvisibleWalls) {
				case (LevelSettingInvisibleWallType.Off):
					TMManager.Sprite.release(_invisibleWallsValue.Attributes.Sprite);
					_invisibleWallsValue.Attributes.Sprite = TMManager.Sprite.make(TMImage.DEBUG_TEXT_INVISIBLE_WALLS_VALUE_OFF);
					break;
				case (LevelSettingInvisibleWallType.Toggle):
					TMManager.Sprite.release(_invisibleWallsValue.Attributes.Sprite);
					_invisibleWallsValue.Attributes.Sprite = TMManager.Sprite.make(TMImage.DEBUG_TEXT_INVISIBLE_WALLS_VALUE_TOGGLE);
					break;
				case (LevelSettingInvisibleWallType.FadeSlow):
					TMManager.Sprite.release(_invisibleWallsValue.Attributes.Sprite);
					_invisibleWallsValue.Attributes.Sprite = TMManager.Sprite.make(TMImage.DEBUG_TEXT_INVISIBLE_WALLS_VALUE_FADE_SLOW);
					break;
				case (LevelSettingInvisibleWallType.FadeFast):
					TMManager.Sprite.release(_invisibleWallsValue.Attributes.Sprite);
					_invisibleWallsValue.Attributes.Sprite = TMManager.Sprite.make(TMImage.DEBUG_TEXT_INVISIBLE_WALLS_VALUE_FADE_FAST);
					break;
				case (LevelSettingInvisibleWallType.Always):
					TMManager.Sprite.release(_invisibleWallsValue.Attributes.Sprite);
					_invisibleWallsValue.Attributes.Sprite = TMManager.Sprite.make(TMImage.DEBUG_TEXT_INVISIBLE_WALLS_VALUE_ALWAYS);
					break;
				}
				_invisibleWallsValue.Attributes.Sprite.UseCamera = false;
			}
		}
	}

	@Override
	public void onInit(Object input) {		
		_entities.add(EntityHelper.scrollingGraphic(TMImage.SCROLLING_STONE_WALL, TMSpriteLayer.BACKGROUND1, Direction.LEFT, 1f, Global.Data.ScrollingBackgroundPos, TMGlobal.Renderer.Width * 1.8f, true));
		
		_mazeSizeLabel = debugLabel(TMImage.DEBUG_TEXT_MAZE_SIZE_LABEL, 0);
		_mazeSizeValue = debugValue(TMImage.DEBUG_TEXT_MAZE_SIZE_VALUE_MEDIUM, 0);
		_mazeSizeButton = debugButton(TMImage.DEBUG_BUTTON_CHANGE, 0);
		_selectedMazeSize = MazeSize.Medium;
		_entities.add(_mazeSizeLabel);
		_entities.add(_mazeSizeValue);
		_entities.add(_mazeSizeButton);
		
		_zoomLabel = debugLabel(TMImage.DEBUG_TEXT_ZOOM_LABEL, 1);
		_zoomValue = debugValue(TMImage.DEBUG_TEXT_ZOOM_VALUE_MEDIUM, 1);
		_zoomButton = debugButton(TMImage.DEBUG_BUTTON_CHANGE, 1);
		_selectedZoom = Zoom.Medium;
		_entities.add(_zoomLabel);
		_entities.add(_zoomValue);
		_entities.add(_zoomButton);
		
		_peekLabel = debugLabel(TMImage.DEBUG_TEXT_PEEK_LABEL, 2);
		_peekValue = debugValue(TMImage.DEBUG_TEXT_PEEK_VALUE_NORMAL, 2);
		_peekButton = debugButton(TMImage.DEBUG_BUTTON_CHANGE, 2);
		_selectedPeek = LevelSettingPeekType.Normal;
		_entities.add(_peekLabel);
		_entities.add(_peekValue);
		_entities.add(_peekButton);
		
		_goalIndicatorLabel = debugLabel(TMImage.DEBUG_TEXT_GOAL_INDICATOR_LABEL, 3);
		_goalIndicatorValue = debugValue(TMImage.DEBUG_TEXT_GOAL_INDICATOR_VALUE_ON, 3);
		_goalIndicatorButton = debugButton(TMImage.DEBUG_BUTTON_CHANGE, 3);
		_selectedGoalIndicator = GoalIndicator.On;
		_entities.add(_goalIndicatorLabel);
		_entities.add(_goalIndicatorValue);
		_entities.add(_goalIndicatorButton);
		
		_invisibleWallsLabel = debugLabel(TMImage.DEBUG_TEXT_INVISIBLE_WALLS_LABEL, 4);
		_invisibleWallsValue = debugValue(TMImage.DEBUG_TEXT_INVISIBLE_WALLS_VALUE_OFF, 4);
		_invisibleWallsButton = debugButton(TMImage.DEBUG_BUTTON_CHANGE, 4);
		_selectedInvisibleWalls = LevelSettingInvisibleWallType.Off;
		_entities.add(_invisibleWallsLabel);
		_entities.add(_invisibleWallsValue);
		_entities.add(_invisibleWallsButton);
		
		float buttonBuffer = Global.Renderer.Height / 100f;
		_startButton = EntityHelper.button(TMImage.CONTINUE_BUTTON, TMSpriteLayer.UI_LOW, false, Global.Renderer.Width / 2.5f, Global.Renderer.Width / 5f, true, Global.Renderer.Width / 2f, buttonBuffer + Global.Renderer.Width / 10f, AreaType.Rectangle);
		_entities.add(_startButton);
		
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

	protected GameEntity debugLabel(int image, int yIndex) {
		float buttonWidth = Global.Renderer.Width / 3f;
		float buttonHeight = buttonWidth / 4f;
		float buttonBuffer = Global.Renderer.Height / 100f;
		return EntityHelper.graphic(image, TMSpriteLayer.UI_LOW, false, buttonWidth, buttonHeight, false, 0f, Global.Renderer.Height - (buttonBuffer + buttonHeight) - (buttonHeight + buttonBuffer) * yIndex);
	}
	
	protected GameEntity debugValue(int image, int yIndex) {
		float buttonWidth = Global.Renderer.Width / 3f;
		float buttonHeight = buttonWidth / 4f;
		float buttonBuffer = Global.Renderer.Height / 100f;
		return EntityHelper.graphic(image, TMSpriteLayer.UI_LOW, false, buttonWidth, buttonHeight, false, Global.Renderer.Width / 3f, Global.Renderer.Height - (buttonBuffer + buttonHeight) - (buttonHeight + buttonBuffer) * yIndex);
	}
	
	protected GameEntity debugButton(int image, int yIndex) {
		float buttonWidth = Global.Renderer.Width / 3f;
		float buttonHeight = buttonWidth / 4f;
		float buttonBuffer = Global.Renderer.Height / 100f;
		return EntityHelper.button(image, TMSpriteLayer.UI_LOW, false, buttonWidth, buttonHeight, false, Global.Renderer.Width * 2f / 3f, Global.Renderer.Height - (buttonBuffer + buttonHeight) - (buttonHeight + buttonBuffer) * yIndex, AreaType.Rectangle);
	}

	protected LevelSettings getLevelSettings() {
		LevelSettings settings = new LevelSettings();
		
		// Maze Size
		switch (_selectedMazeSize) {
		case (MazeSize.Small):
			settings.RandomMazeWidth = TMGameSettings.MAZE_SIZE_SMALL;
			settings.RandomMazeHeight = TMGameSettings.MAZE_SIZE_SMALL;
			break;
		case (MazeSize.Medium):
			settings.RandomMazeWidth = TMGameSettings.MAZE_SIZE_MEDIUM;
			settings.RandomMazeHeight = TMGameSettings.MAZE_SIZE_MEDIUM;
			break;
		case (MazeSize.Large):
			settings.RandomMazeWidth = TMGameSettings.MAZE_SIZE_LARGE;
			settings.RandomMazeHeight = TMGameSettings.MAZE_SIZE_LARGE;
			break;
		}
		
		// Zoom
		switch (_selectedZoom) {
		case (Zoom.Minimum):
			settings.CellWidth = TMGameSettings.GetZoomMinimum();
			settings.BackgroundTileCount = TMGameSettings.BACKGROUND_TILE_COUNT_MAXIMUM;
			break;
		case (Zoom.Medium):
			settings.CellWidth = TMGameSettings.GetZoomMedium();
			settings.BackgroundTileCount = TMGameSettings.BACKGROUND_TILE_COUNT_MEDIUM;
			break;
		case (Zoom.Maximum):
			settings.CellWidth = TMGameSettings.GetZoomMaximum();
			settings.BackgroundTileCount = TMGameSettings.BACKGROUND_TILE_COUNT_MINIMUM;
			break;
		}
		
		// Peek
		settings.PeekType = _selectedPeek;
		
		// Enable Goal Indicator
		switch (_selectedGoalIndicator) {
		case (GoalIndicator.On):
			settings.EnableGoalIndicator = true;
			break;
		case (GoalIndicator.Off):
			settings.EnableGoalIndicator = false;
			break;
		}
		
		// Invisible Walls
		settings.HazardEnableInvisibleWalls = _selectedInvisibleWalls;
		
		settings.BackgroundImage = TMImage.BACKGROUND_SAND;
		settings.HazardEnableChaseSpikes = false;
		settings.HazardEnableFastPlayer = false;
		settings.HazardEnableReverseTouch = false;
		settings.HazardEnableSpikeRings = false;
		settings.HazardEnableSpotlights = false;
		settings.HazardEnableTimer = false;
		settings.HazardEnableWind = false;
		settings.HazardTimerValue = 0;
		settings.Item1 = 0;
		settings.Item2 = 0;
		settings.Item3 = 0;
		settings.RandomizeMaze = true;
		
		settings.WallSprites = new WallSpriteSet();
		
		// TODO: make this configurable?
		settings.EnableActionButton = false;

		return settings;
	}
}
