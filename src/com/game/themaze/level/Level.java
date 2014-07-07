package com.game.themaze.level;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;

import com.game.loblib.entity.GameEntity;
import com.game.loblib.graphics.Image;
import com.game.loblib.messaging.IMessageHandler;
import com.game.loblib.messaging.Message;
import com.game.loblib.messaging.MessageType;
import com.game.loblib.utility.Direction;
import com.game.loblib.utility.Global;
import com.game.loblib.utility.Logger;
import com.game.loblib.utility.Manager;
import com.game.loblib.utility.MathHelper;
import com.game.loblib.utility.TimerType;
import com.game.loblib.utility.Tuple;
import com.game.loblib.utility.android.FixedSizeArray;
import com.game.loblib.utility.area.AreaType;
import com.game.loblib.utility.area.Vertex;
import com.game.themaze.behavior.BlinkBehavior;
import com.game.themaze.behavior.CameraDragBehavior;
import com.game.themaze.behavior.DoorBehavior;
import com.game.themaze.behavior.EntityLinkBehavior;
import com.game.themaze.behavior.GameItemBehavior;
import com.game.themaze.behavior.GoalIndicatorBehavior;
import com.game.themaze.behavior.PatrolDestinationBehavior;
import com.game.themaze.behavior.TMBehaviorType;
import com.game.themaze.behavior.TimerBehavior;
import com.game.themaze.entity.EntityHelper;
import com.game.themaze.gameitem.GameItemActivationType;
import com.game.themaze.gameitem.GameItemHelper;
import com.game.themaze.gameitem.GameItemType;
import com.game.themaze.graphics.TMImage;
import com.game.themaze.graphics.TMSpriteLayer;
import com.game.themaze.messaging.TMMessageType;
import com.game.themaze.utility.DoorOpenType;
import com.game.themaze.utility.DoorType;
import com.game.themaze.utility.TMManager;

public class Level implements IMessageHandler {
	protected final static StringBuffer _tag = new StringBuffer("Level");
	
	public final static int CELL_COUNT = 100;
	
	protected GameEntity _player;
	protected GameEntity _goal;
	protected GameEntity _controlBar;
	protected GameEntity _cameraButton;
	protected GameEntity _cameraSetter;
	protected GameEntity _actionButton;
	protected GameEntity _goalIndicator;
	protected GameEntity _itemBorder1;
	protected GameEntity _itemBorder2;
	protected GameEntity _itemBorder3;
	protected GameEntity _itemButton1;
	protected GameEntity _itemButton2;
	protected GameEntity _itemButton3;
	protected GameEntity _popupRedKey;
	protected GameEntity _popupBlueKey;
	protected GameEntity _popupYellowKey;
	protected FixedSizeArray<GameEntity> _mapWalls = null;
	protected FixedSizeArray<GameEntity> _mapSpikes = null;
	protected FixedSizeArray<GameEntity> _borders = null;
	protected FixedSizeArray<GameEntity> _doorTriggers = null;
	protected FixedSizeArray<GameEntity> _actionTriggers = null;
	protected FixedSizeArray<GameEntity> _doors = null;
	protected FixedSizeArray<GameEntity> _entities = null;
	
	protected boolean _actionButtonActive;
	protected boolean _item1Active;
	protected boolean _item2Active;
	protected boolean _item3Active;
	protected boolean _playerDamaged = false;
	protected boolean _cameraMode = false;
	protected int _playerDirection;
	protected int _goalDirection;
	protected int[] _activatedItem = new int[1]; // hack. Java is stupid.
	protected Vertex _startLocation = null;
	protected Vertex _goalLocation = null;
	protected Vertex _size = null;
	protected Vertex _offset = null;
	protected LevelSettings _settings = new LevelSettings();
	
	private char[] _mapCells;

	///////////////////////
	// Public Methods
	///////////////////////
	public void pause() {
		Manager.Message.unsubscribe(this, MessageType.ALL);
	}
	
	public void unpause() {		
		Manager.Message.subscribe(this, MessageType.BUTTON_CLICKED | 
				TMMessageType.DAMAGE | 
				TMMessageType.TIMER_ALARM |
				TMMessageType.ITEM_HIT |
				TMMessageType.ENABLE_ITEM |
				TMMessageType.DISABLE_ITEM |
				TMMessageType.TRIGGER_HIT |
				TMMessageType.TRIGGER_RELEASED);
	}
	
	public void close() {
		_mapWalls = null;
		_borders = null;
		_entities = null;
		
		Manager.Message.unsubscribe(this, MessageType.ALL);
	}
	
	public void update() {
		if (_cameraMode) {
			Global.Camera.setCenter(_cameraSetter.Attributes.Area.Position);
			// update position in case camera rejects position change;
			_cameraSetter.Attributes.Area.setPosition(Global.Camera.CameraArea.getCenterX(), Global.Camera.CameraArea.getCenterY());
		}
	}
	
	public void enableBehaviors() {	
		int count = _entities.getCount();
		for (int i = 0; i < count; i++)
			_entities.get(i).enableBehaviors();
		
		if (_itemButton1 != null)
			_itemButton1.disableBehaviors();
		if (_itemButton2 != null)
			_itemButton2.disableBehaviors();
		if (_itemButton3 != null)
			_itemButton3.disableBehaviors();
		if (_actionButton != null)
			_actionButton.disableBehaviors(TMBehaviorType.BUTTON);
		
		_popupRedKey.disableBehaviors();
		_popupBlueKey.disableBehaviors();
		_popupYellowKey.disableBehaviors();
	}
	
	@Override
	public void handleMessage(Message message) {
		if (message.Type == MessageType.BUTTON_CLICKED) {
			GameEntity entity = message.getData();
			if (entity == _cameraButton) {
				toggleCameraMode();
				//Manager.Sound.play(1);
			}
			else if (entity == _actionButton) {
				actionClicked();
			}
			else if (entity == _itemButton1) {
				_activatedItem[0] = _settings.Item1;
				if (_item1Active) {
					_item1Active = false;
					Manager.Message.sendMessage(TMMessageType.DISABLE_ITEM, _activatedItem);
					_itemBorder1.Attributes.Sprite.setFrame(0);
				}
				else {
					_item1Active = true;
					Manager.Message.sendMessage(TMMessageType.ENABLE_ITEM, _activatedItem);
					_itemBorder1.Attributes.Sprite.setFrame(1);
				}
			}
			else if (entity == _itemButton2) {
				_activatedItem[0] = _settings.Item2;
				if (_item2Active) {
					_item2Active = false;
					Manager.Message.sendMessage(TMMessageType.DISABLE_ITEM, _activatedItem);
					_itemBorder2.Attributes.Sprite.setFrame(0);
				}
				else {
					_item2Active = true;
					Manager.Message.sendMessage(TMMessageType.ENABLE_ITEM, _activatedItem);
					_itemBorder2.Attributes.Sprite.setFrame(1);
				}
			}
			else if (entity == _itemButton3) {
				_activatedItem[0] = _settings.Item3;
				if (_item3Active) {
					_item3Active = false;
					Manager.Message.sendMessage(TMMessageType.DISABLE_ITEM, _activatedItem);
					_itemBorder3.Attributes.Sprite.setFrame(0);
				}
				else {
					_item3Active = true;
					Manager.Message.sendMessage(TMMessageType.ENABLE_ITEM, _activatedItem);
					_itemBorder3.Attributes.Sprite.setFrame(1);
				}
			}
		}
		else if (message.Type == TMMessageType.DAMAGE) {
			GameEntity entity = message.getData();
			if (entity == _player && !_playerDamaged) {
				_playerDamaged = true;
				// stop all movement
				int entityCount = _entities.getCount();
				for (int i = 0; i < entityCount; i++)
					_entities.get(i).disableBehaviors(TMBehaviorType.PLAYER_MOVE | TMBehaviorType.DESTINATION_MOVE | TMBehaviorType.MOVEMENT_MODIFIER | TMBehaviorType.CIRCLE_MOVE);
				// player blinks briefly before going to death screen
				BlinkBehavior bb = new BlinkBehavior(0, 100);
				TimerBehavior tb = new TimerBehavior(TimerType.SINGLE, 1500f);
				_player.addBehavior(bb);
				_player.addBehavior(tb);
				_player.enableBehaviors(TMBehaviorType.BLINK | TMBehaviorType.TIMER);
			}
		}
		else if (message.Type == MessageType.TIMER_ALARM) {
			GameEntity entity = message.getData();
			if (entity == _player)
				Manager.Message.sendMessage(TMMessageType.PLAYER_DEATH);
		}
		else if (message.Type == TMMessageType.ITEM_HIT) {
			GameEntity entity = message.getData();
			entity.disableBehaviors();
			enableGameItemButton(((GameItemBehavior)entity.getBehavior(TMBehaviorType.GAME_ITEM)).getGameItemType());
		}
		else if (message.Type == TMMessageType.ENABLE_ITEM) {
			enableGameItem(((int[])message.getData())[0]);
		}
		else if (message.Type == TMMessageType.DISABLE_ITEM) {
			disableGameItem(((int[])message.getData())[0]);
		}
		else if (message.Type == MessageType.TRIGGER_HIT) {
			GameEntity trigger = message.getData();
			if (_settings.EnableActionButton && 
					!_actionButtonActive && 
					_actionTriggers.find(trigger, false) != -1 && 
					enableActionCheck(trigger)) {
				enableActionButton();
			}
				
		}
		else if (message.Type == MessageType.TRIGGER_RELEASED) {
			GameEntity trigger = message.getData();
			if (_settings.EnableActionButton && 
					_actionButtonActive &&
					_actionTriggers.find(trigger, false) != -1) {
				disableActionButton();
			}
			_popupRedKey.disableBehaviors();
			_popupBlueKey.disableBehaviors();
			_popupYellowKey.disableBehaviors();
		}
	}
	
	public void init() {
		TMManager.Level.setCellWidth(_settings.CellWidth);
		
		int cellWidth = _settings.CellWidth;
		_playerDamaged = false;
		
		_actionTriggers = new FixedSizeArray<GameEntity>(64);
		_doors = new FixedSizeArray<GameEntity>(64);
		_doorTriggers = new FixedSizeArray<GameEntity>(64);
		_entities = new FixedSizeArray<GameEntity>(2048);
		
		if (_settings.RandomizeMaze)
			createRandomMaze();
		else
			loadFromFile(_settings.ResourceId);
		
		_entities.addAll(_mapWalls);
		_entities.addAll(_mapSpikes);
		
		Global.Camera.CoveredArea.setPosition(0, 0);
		Global.Camera.CoveredArea.setSize(CELL_COUNT * cellWidth, CELL_COUNT * cellWidth);
		
		_borders = new FixedSizeArray<GameEntity>(4);
		_borders.add(EntityHelper.wall(TMImage.WALL_HORIZONTAL_BLACK, 0 - (int)_offset.X, 0 - (int)_offset.Y, CELL_COUNT, true));
		_borders.add(EntityHelper.wall(TMImage.WALL_HORIZONTAL_BLACK, 0 - (int)_offset.X, CELL_COUNT - 1 - (int)_offset.Y, CELL_COUNT, true));
		_borders.add(EntityHelper.wall(TMImage.WALL_VERTICAL_BLACK, 0 - (int)_offset.X, 1 - (int)_offset.Y, CELL_COUNT - 2, false));
		_borders.add(EntityHelper.wall(TMImage.WALL_VERTICAL_BLACK, CELL_COUNT - 1 - (int)_offset.X, 1 - (int)_offset.Y, CELL_COUNT - 2, false));
		int borderCount = _borders.getCount();
		for (int i = 0; i < borderCount; i++)
			_entities.add(_borders.get(i));
		
		_player = EntityHelper.player(_startLocation.X, _startLocation.Y, _playerDirection);	
		_entities.add(_player);
		_goal = EntityHelper.goal(_goalLocation.X, _goalLocation.Y, _goalDirection);
		_entities.add(_goal);
		_controlBar = EntityHelper.graphic(TMImage.CONTROL_BAR, TMSpriteLayer.UI_LOW, 
				false, Global.Renderer.Width, TMManager.Level.getControlBarHeight(), false, 0, 0);
		_entities.add(_controlBar);
		if (!_settings.DisableCamera) {
			_cameraButton = EntityHelper.button(TMImage.CAMERA_BTN, TMSpriteLayer.UI_HIGH,
					false, Global.Renderer.Width / 6f, Global.Renderer.Width / 6f, false, 
					Global.Renderer.Width - (11f * Global.Renderer.Width / 60f), Global.Renderer.Width / 120f, 
					AreaType.Circle);
		}
		else {
			_cameraButton = EntityHelper.graphic(TMImage.CAMERA_BTN, TMSpriteLayer.UI_HIGH, 
					false, Global.Renderer.Width / 6f, Global.Renderer.Width / 6f, false, 
					Global.Renderer.Width - (11f * Global.Renderer.Width / 60f), Global.Renderer.Width / 120f);
			_entities.add(EntityHelper.graphic(TMImage.BUTTON_BARS, TMSpriteLayer.UI_TOP, false, Global.Renderer.Width / 6f + 2f, 
					Global.Renderer.Width / 6f + 2f, false, 
					Global.Renderer.Width - (11f * Global.Renderer.Width / 60f) - 1f, Global.Renderer.Width / 120f - 1f));
		}
		_entities.add(_cameraButton);
		_cameraSetter = new GameEntity();
		_cameraSetter.addBehavior(new CameraDragBehavior());
		_entities.add(_cameraSetter);
		
		if (_settings.EnableActionButton) {
			_actionButton =  EntityHelper.button(TMImage.ACTION_BTN, TMSpriteLayer.UI_HIGH,
					false, Global.Renderer.Width / 6f, Global.Renderer.Width / 6f, false, 
					Global.Renderer.Width - (11f * Global.Renderer.Width / 60f) * 2f, Global.Renderer.Width / 120f, AreaType.Circle);
			_entities.add(_actionButton);
		}
		
		if (!_settings.DisableGoalIndicator) {
			_goalIndicator = new GameEntity();
			_goalIndicator.Attributes.Sprite = Manager.Sprite.make(TMImage.GOAL_INDICATOR);
			_goalIndicator.Attributes.Sprite.UseCamera = true;
			_goalIndicator.addBehavior(new GoalIndicatorBehavior(_goal));
			_entities.add(_goalIndicator);
		}
		
		if (_settings.Item1 != GameItemType.UNKNOWN){
			setGameItem(_settings.Item1, 1);
			_entities.add(_itemButton1);
			_entities.add(_itemBorder1);
		}
		if (_settings.Item2 != GameItemType.UNKNOWN) {
			setGameItem(_settings.Item2, 2);
			_entities.add(_itemButton2);
			_entities.add(_itemBorder2);
		}
		if (_settings.Item3 != GameItemType.UNKNOWN) {
			setGameItem(_settings.Item3, 3);
			_entities.add(_itemButton3);
			_entities.add(_itemBorder3);
		}
		
		if (_settings.BackgroundImage != Image.NONE) {
			float totalSize = CELL_COUNT * cellWidth;
			_entities.add(EntityHelper.tiledGraphic(_settings.BackgroundImage, TMSpriteLayer.BACKGROUND1, 
					totalSize / _settings.BackgroundTileCount, 0, 0, totalSize));
		}
		
		_popupRedKey = EntityHelper.delayedGraphic(TMImage.POPUP_REDKEY, TMSpriteLayer.UI_LOW, false, Global.Renderer.Width * .9f, Global.Renderer.Width * .1125f, true, Global.Renderer.Width / 2f, Global.Renderer.Height - Global.Renderer.Width * .2f,2000f,500f);
		_popupBlueKey = EntityHelper.delayedGraphic(TMImage.POPUP_BLUEKEY, TMSpriteLayer.UI_LOW, false, Global.Renderer.Width * .9f, Global.Renderer.Width * .1125f, true,Global.Renderer.Width / 2f,Global.Renderer.Height - Global.Renderer.Width * .2f,2000f,500f);
		_popupYellowKey = EntityHelper.delayedGraphic(TMImage.POPUP_YELLOWKEY, TMSpriteLayer.UI_LOW, false, Global.Renderer.Width * .9f, Global.Renderer.Width * .1125f, true, Global.Renderer.Width / 2f, Global.Renderer.Height - Global.Renderer.Width * .2f,2000f,500f);
		_entities.add(_popupRedKey);
		_entities.add(_popupBlueKey);
		_entities.add(_popupYellowKey);
		
		Global.Camera.Anchor = _player;
		Global.Camera.Threshold.setPosition(Global.Renderer.Width / 8f, Global.Renderer.Width / 6f);
		Global.Camera.centerOnAnchor();
		Manager.Message.subscribe(this, MessageType.BUTTON_CLICKED | 
				TMMessageType.DAMAGE | 
				TMMessageType.TIMER_ALARM |
				TMMessageType.ITEM_HIT|
				TMMessageType.ENABLE_ITEM |
				TMMessageType.DISABLE_ITEM |
				TMMessageType.TRIGGER_HIT |
				TMMessageType.TRIGGER_RELEASED);
	}

	////////////////////////
	// Protected Methods
	////////////////////////
	protected void enableGameItem(int gameItemType) {
		// Nothing to do
	}
	
	protected boolean onEnableActionCheck(GameEntity trigger) {
		return true;
	}
	
	protected void onActionClicked() {
		// Nothing to do
	}

	////////////////////////
	// Public Final Methods
	////////////////////////
	public final FixedSizeArray<GameEntity> getEntities() {
		return _entities;
	}
	
	////////////////////////
	// Protected Final Methods
	////////////////////////
	protected final boolean isItemActive(int gameItemType) {
		boolean result = false;
		
		if (_settings.Item1 == gameItemType)
			result = _item1Active;
		else if (_settings.Item2 == gameItemType)
			result = _item2Active;
		else if (_settings.Item3 == gameItemType)
			result = _item3Active;
		
		return result;
	}

	protected final void enableActionButton() {
		if (_actionButton != null) {
			_actionButton.Attributes.Sprite.animate();
			_actionButton.enableBehaviors(TMBehaviorType.BUTTON);
			_actionButtonActive = true;
		}
		else
			Logger.e(_tag, "cannot enable action button; button not added to this level");
	}
	
	protected final void disableActionButton() {
		if (_actionButton != null) {
			_actionButton.Attributes.Sprite.stopAnimation();
			_actionButton.disableBehaviors(TMBehaviorType.BUTTON);
			_actionButton.Attributes.Sprite.setFrame(0);
			_actionButtonActive = false;
		}
		else
			Logger.e(_tag, "cannot disable action button; button not added to this level");
	}

	protected final void addAnchorDestination(GameEntity anchor, int xGrid, int yGrid) {
		((PatrolDestinationBehavior)anchor.getBehavior(TMBehaviorType.PATROL_DESTINATION)).addDestination(xGridPosition(xGrid), yGridPosition(yGrid));
	}
	
	protected final float xGridPosition(float xGrid) {
		return (_offset.X + xGrid) * TMManager.Level.getCellWidth();
	}
	
	protected final float yGridPosition(float yGrid) {
		return (_offset.Y + yGrid) * TMManager.Level.getCellWidth();
	}

	protected final int makeDoor(int closedXGrid, int closedYGrid, int openXGrid, int openYGrid, boolean horizontal) {
		return makeDoor(closedXGrid, closedYGrid, openXGrid, openYGrid, horizontal, DoorType.NEUTRAL, DoorOpenType.SINGLE, false);
	}
	
	protected final int makeDoor(int closedXGrid, int closedYGrid, int openXGrid, int openYGrid, boolean horizontal, int doorType, int doorOpenType, boolean open) {
		int image = Image.UNKNOWN;
		if (horizontal) {
			switch (doorType) {
			case DoorType.NEUTRAL:
				image = TMImage.DOOR_LOCKED_HORIZONTAL_NEUTRAL;
				break;
			case DoorType.RED:
				image = TMImage.DOOR_LOCKED_HORIZONTAL_RED;
				break;
			case DoorType.BLUE:
				image = TMImage.DOOR_LOCKED_HORIZONTAL_BLUE;
				break;
			case DoorType.YELLOW:
				image = TMImage.DOOR_LOCKED_HORIZONTAL_YELLOW; 
				break;
			default:
				Logger.e(_tag, "Cannot make door; Invalid door type");
				return 0;
			}
		}
		else {
			switch (doorType) {
			case DoorType.NEUTRAL:
				image = TMImage.DOOR_LOCKED_VERTICAL_NEUTRAL;
				break;
			case DoorType.RED:
				image = TMImage.DOOR_LOCKED_VERTICAL_RED;
				break;
			case DoorType.BLUE:
				image = TMImage.DOOR_LOCKED_VERTICAL_BLUE;
				break;
			case DoorType.YELLOW:
				image = TMImage.DOOR_LOCKED_VERTICAL_YELLOW; 
				break;
			default:
				Logger.e(_tag, "Cannot make door; Invalid door type");
				return 0;
			}
		}
		
		GameEntity doorAnchor = EntityHelper.doorAnchor(closedXGrid, closedYGrid, openXGrid, openYGrid, doorType, doorOpenType, open);
		_doors.add(doorAnchor);
		_entities.add(doorAnchor);
		_entities.add(EntityHelper.anchorWall(doorAnchor, image, 2, horizontal, 0, 0));
		
		return (_doors.getCount() - 1); 
	}

	protected final void makeDoorTrigger(int doorId, int xGrid, int yGrid, int xSize, int ySize) {
		GameEntity trigger = EntityHelper.trigger(xGrid, yGrid, xSize, ySize);
		trigger.addBehavior(new EntityLinkBehavior(_doors.get(doorId)));
		_doorTriggers.add(trigger);
		_actionTriggers.add(trigger);
		_entities.add(trigger);
	}
	
	protected final boolean enableActionCheck(GameEntity trigger) {
		boolean result = true;
		if (_doorTriggers.find(trigger, false) != -1) 
		{
			GameEntity door = ((EntityLinkBehavior)trigger.getBehavior(TMBehaviorType.ENTITY_LINK)).getLinkedEntity();
			DoorBehavior db = (DoorBehavior)door.getBehavior(TMBehaviorType.DOOR_ANCHOR);
			switch (db.getDoorType()) {
			case DoorType.NEUTRAL:
				if (db.isOpen() && db.getDoorOpenType() != DoorOpenType.TOGGLE)
					result = false;
				break;
			case DoorType.RED:
				if (!isItemActive(GameItemType.RED_KEY)) {
					result = false;
					_popupRedKey.enableBehaviors();
				}
				else if (db.isOpen() && db.getDoorOpenType() != DoorOpenType.TOGGLE)
					result = false;
				break;
			case DoorType.BLUE:
				if (!isItemActive(GameItemType.BLUE_KEY)) {
					result = false;
					_popupBlueKey.enableBehaviors();
				}
				else if (db.isOpen() && db.getDoorOpenType() != DoorOpenType.TOGGLE)
					result = false;
				break;
			case DoorType.YELLOW:
				if (!isItemActive(GameItemType.YELLOW_KEY)) {
					result = false;
					_popupYellowKey.enableBehaviors();
				}
				else if (db.isOpen() && db.getDoorOpenType() != DoorOpenType.TOGGLE)
					result = false;
				break;
			default:
				Logger.e(_tag, "Invalid door type");
				return false;
			}
		}
		
		if (result)
			return onEnableActionCheck(trigger);
		else
			return false;
	}
	
	////////////////////////
	// Private Methods
	////////////////////////	
	private void toggleCameraMode() {
		if (_cameraMode) {
			_cameraMode = false;
			Global.Camera.Threshold.setPosition(Global.Renderer.Width / 6f, Global.Renderer.Width / 4f);
			_cameraButton.Attributes.Sprite.setFrame(0);
			_cameraSetter.disableBehaviors();
			
			_player.getBehavior(TMBehaviorType.TOUCH_DESTINATION).enable();
		}
		else {
			_cameraMode = true;
			Global.Camera.Threshold.setPosition(Global.Renderer.Width, Global.Renderer.Width);
			_cameraButton.Attributes.Sprite.setFrame(1);
			_cameraSetter.Attributes.Area.setPosition(Global.Camera.CameraArea.getCenterX(), Global.Camera.CameraArea.getCenterY());
			_cameraSetter.enableBehaviors();
			
			_player.Attributes.Destination.setPosition(_player.Attributes.Area.getCenterX(), _player.Attributes.Area.getCenterY());
			_player.Attributes.Direction.Undefined = true;
			_player.getBehavior(TMBehaviorType.TOUCH_DESTINATION).disable();
		}
	}
	
	private void disableGameItem(int gameItemType) {
		switch (gameItemType) {
		case GameItemType.SPEED_BOOTS:
			int cellWidth = TMManager.Level.getCellWidth();
			_player.Attributes.Speed = (float)cellWidth/11f;
		default:
			Logger.e(_tag, "Cannot disable; invalid gameItemType");	
		}
	}
	
	private void enableGameItemButton (int gameItemType) {
		if (_settings.Item1 == gameItemType) {
			_itemButton1.enableBehaviors();
			if (GameItemHelper.getActivationType(gameItemType) != GameItemActivationType.TOGGLE) {
				_itemBorder1.Attributes.Sprite.setFrame(1);
				_item1Active = true;
			}
		}
		else if (_settings.Item2 == gameItemType) {
			_itemButton2.enableBehaviors();
			if (GameItemHelper.getActivationType(gameItemType) != GameItemActivationType.TOGGLE) {
				_itemBorder2.Attributes.Sprite.setFrame(1);
				_item2Active = true;
			}
		}
		else if (_settings.Item3 == gameItemType) {
			_itemButton3.enableBehaviors();
			if (GameItemHelper.getActivationType(gameItemType) != GameItemActivationType.TOGGLE) {
				_itemBorder3.Attributes.Sprite.setFrame(1);
				_item3Active = true;
			}
		}
		
		if (GameItemHelper.getActivationType(gameItemType) == GameItemActivationType.CONTINUOUS)
			Manager.Message.sendMessage(TMMessageType.ENABLE_ITEM, gameItemType);
	}
	
	private void setGameItem(int gameItemType, int position) {
		GameEntity item = null;
		GameEntity border = null;
		if (GameItemHelper.getActivationType(gameItemType) == GameItemActivationType.TOGGLE) {
			item = EntityHelper.button(GameItemHelper.getMenuSprite(gameItemType),
					TMSpriteLayer.UI_TOP, false, Global.Renderer.Width / 6f, Global.Renderer.Width / 6f, false, 
					Global.Renderer.Width / 60f + ((position - 1) * (11f * Global.Renderer.Width / 60f)), Global.Renderer.Width / 120f, 
					AreaType.Rectangle);
			border = EntityHelper.graphic(TMImage.GAME_ITEM_BORDER,
					TMSpriteLayer.UI_HIGH, false, Global.Renderer.Width / 6f, Global.Renderer.Width / 6f, false, 
					Global.Renderer.Width / 60f + ((position - 1) * (11f * Global.Renderer.Width / 60f)), Global.Renderer.Width / 120f);
		}
		else {
			item = EntityHelper.graphic(GameItemHelper.getMenuSprite(gameItemType),
					TMSpriteLayer.UI_TOP, false, Global.Renderer.Width / 6f, Global.Renderer.Width / 6f, false, 
					Global.Renderer.Width / 60f + ((position - 1) * (11f * Global.Renderer.Width / 60f)), Global.Renderer.Width / 120f);
			border = EntityHelper.graphic(TMImage.GAME_ITEM_BORDER,
					TMSpriteLayer.UI_HIGH, false, Global.Renderer.Width / 6f, Global.Renderer.Width / 6f, false, 
					Global.Renderer.Width / 60f + ((position - 1) * (11f * Global.Renderer.Width / 60f)), Global.Renderer.Width / 120f);
		}
		
		switch (position) {
		case 1:
			_itemButton1 = item;
			_itemBorder1 = border;
			break;
		case 2:
			_itemButton2 = item;
			_itemBorder2 = border;
			break;
		case 3:
			_itemButton3 = item;
			_itemBorder3 = border;
			break;
		}
	}
	
	private void actionClicked() {
		int triggerCount = _doorTriggers.getCount();
		for (int i = 0; i < triggerCount; i++) {
			GameEntity trigger = _doorTriggers.get(i);
			if (Manager.Trigger.hit(trigger)) {
				GameEntity door = ((EntityLinkBehavior)trigger.getBehavior(TMBehaviorType.ENTITY_LINK)).getLinkedEntity();
				DoorBehavior db = (DoorBehavior)door.getBehavior(TMBehaviorType.DOOR_ANCHOR);
				switch (db.getDoorType()) {
				//TODO: this enabled DOOR_ANCHOR, but DESTINATION_MOVE is what's been disabled. fix.
				case DoorType.NEUTRAL:
					if (!db.isOpen()) {
						db.open();
						disableActionButton();
					}
					else if (db.getDoorOpenType() == DoorOpenType.TOGGLE) {
						db.close();
						disableActionButton();
					}
					break;
				case DoorType.RED:
					if (isItemActive(GameItemType.RED_KEY)) {
						if (!db.isOpen()) {
							db.open();
							disableActionButton();
						}
						else if (db.getDoorOpenType() == DoorOpenType.TOGGLE) {
							db.close();
							disableActionButton();
						}
					}
					break;
				case DoorType.BLUE:
					if (isItemActive(GameItemType.BLUE_KEY)) {
						if (!db.isOpen()) {
							db.open();
							disableActionButton();
						}
						else if (db.getDoorOpenType() == DoorOpenType.TOGGLE) {
							db.close();
							disableActionButton();
						}
					}
					break;
				case DoorType.YELLOW:
					if (isItemActive(GameItemType.YELLOW_KEY)) {
						if (!db.isOpen()) {
							db.open();
							disableActionButton();
						}
						else if (db.getDoorOpenType() == DoorOpenType.TOGGLE) {
							db.close();
							disableActionButton();
						}
					}
					break;
				default:
					Logger.e(_tag, "Invalid door type");
					break;
				}
			}
		}
		onActionClicked();
	}

	private void loadFromFile(int resourceId) {
		_size = new Vertex();
		_offset = new Vertex();
		
		// Open Resource //////////////////////////////
		BufferedReader bin = null;
		{
			InputStream is = Global.Context.getResources().openRawResource(resourceId);
			try {
				bin = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				Logger.e(_tag, "Unsupported encoding");
			}
		}
		
		// Set Header Details ////////////////////////
		{
			try {
				String header = bin.readLine();
				String[] vars = header.split(";");
				if (vars.length != 6) {
					Logger.e(_tag, "invalid header length");
					return;
				}
				int width = 0;
				int height = 0;
				int xOffset = 0;
				int yOffset = 0;
				try {
					width = Integer.parseInt(vars[0]);
					height = Integer.parseInt(vars[1]);
					xOffset = Integer.parseInt(vars[2]);
					yOffset = Integer.parseInt(vars[3]);
					switch(Integer.parseInt(vars[4])) {
					case 0:
						_playerDirection = Direction.UP;
						break;
					case 1:
						_playerDirection = Direction.RIGHT;
						break;
					case 2:
						_playerDirection = Direction.DOWN;
						break;
					case 3:
						_playerDirection = Direction.LEFT;
						break;
					}
					switch(Integer.parseInt(vars[5])) {
					case 0:
						_goalDirection = Direction.UP;
						break;
					case 1:
						_goalDirection = Direction.RIGHT;
						break;
					case 2:
						_goalDirection = Direction.DOWN;
						break;
					case 3:
						_goalDirection = Direction.LEFT;
						break;
					}
				} catch (NumberFormatException e) {
					Logger.e(_tag, "invalid header; var not a number");
				}
				
				_size.X = width;
				_size.Y = height;
				_offset.X = xOffset;
				_offset.Y = yOffset;
				TMManager.Level.setOffset(_offset.X, _offset.Y);
			} catch (IOException e) {
				Logger.e(_tag, "Error reading file");
			}
		}
		
		// Read Maze Cells ////////////////////////////////////////////
		int totalCells = (int)(_size.X * _size.Y);
		_mapCells = new char[totalCells];
		{
			try {
				int nextChar;
				int index = 0;
				while ((nextChar = bin.read()) != -1) {
					if ((char)nextChar == '0' || 
						(char)nextChar == '1' ||
						(char)nextChar == '2' ||
						(char)nextChar == '3' ||
						(char)nextChar == '4' ||
						(char)nextChar == '5') {
						_mapCells[index] = (char)nextChar;
						index++;
						if (index > totalCells) {
							Logger.e(_tag, "Too many cells");
							return;
						}
					}
				}
				if (index != totalCells) {
					Logger.e(_tag, "Not enough cells");
					return;
				}
				
			} catch (IOException e) {
				Logger.e(_tag, "Error reading file");
				return;
			}
		}
		
		// Construct maze using loaded data
		constructMaze();
	}
	
	private void createRandomMaze() {
		if (_settings.RandomMazeWidth < 4 || _settings.RandomMazeWidth > CELL_COUNT || (_settings.RandomMazeWidth - 1) % 3 != 0)
			Logger.e(_tag, "RandomMazeWidth needs to be in the range 4 - " + CELL_COUNT + " and RandomMazeWidth - 1 needs to be divisible by 3");
		if (_settings.RandomMazeHeight < 4 || _settings.RandomMazeHeight > CELL_COUNT ||(_settings.RandomMazeHeight - 1) % 3 != 0)
			Logger.e(_tag, "RandomMazeHeight needs to be in the range 4 - " + CELL_COUNT + " and RandomMazeHeight - 1 needs to be divisible by 3");
		
		Random rand = new Random();
		_size = new Vertex();
		_offset = new Vertex();
		
		_size.X = _settings.RandomMazeWidth;
		_size.Y = _settings.RandomMazeHeight;
		_offset.X = (CELL_COUNT - _settings.RandomMazeWidth) / 2;
		_offset.Y = (CELL_COUNT - _settings.RandomMazeHeight) / 2;
		TMManager.Level.setOffset(_offset.X, _offset.Y);
		
		//_playerDirection = Direction.GetRandomCardinalDirection(rand);
		
		int totalCells = (int)(_size.X * _size.Y);
		_mapCells = new char[totalCells];
		
		//recursiveBacktrackMaze(rand);
		primsMaze(rand);
		constructMaze();
	}
	
	@SuppressWarnings("unused")
	private void recursiveBacktrackMaze(Random rand) {
		
		// for the purpose of the backtrack algorithm only treat open spaces as cells. 
		int btSizeX = ((int)_size.X - 1) / 3;
		int btSizeY = ((int)_size.Y - 1) / 3;
		
		boolean[][] cellCheckArr = new boolean[btSizeX][btSizeY];
		boolean[][] horWallArr = new boolean[btSizeX][btSizeY + 1];
		boolean[][] verWallArr = new boolean[btSizeX + 1][btSizeY];
	
		// TODO: check to ensure start is not too close to goal 
		// TODO: randomize start direction; i.e. shouldn't always be at top
		int btStartX = rand.nextInt(btSizeX);
		int btStartY = rand.nextInt(btSizeY - 1) + 1;
		
		cellCheckArr[btStartX][btStartY] = true;
		
		rbmCheckCells(cellCheckArr, horWallArr, verWallArr, rand, btStartX, btStartY, true);
		
		// set goal
		_mapCells[((btStartX * 3) + 2) + (btStartY * (int)_size.X * 3) + (int)_size.X] = CellType.GOAL;
		
		for (int x = 0; x < btSizeX + 1; x++) {
			for (int y = 0; y < btSizeY + 1; y++) {
				if (x < btSizeX && !horWallArr[x][y])
				{
					// a single wall is 4 cells long (to cover path and connect to adjacent wall areas)
					int startIndex = (x * 3) + (y * (int)_size.X * 3);
					_mapCells[startIndex] = CellType.WALL;
					_mapCells[startIndex + 1] = CellType.WALL;
					_mapCells[startIndex + 2] = CellType.WALL;
					_mapCells[startIndex + 3] = CellType.WALL;
				}
				if (y < btSizeY && !verWallArr[x][y]) {
					// a single wall is 4 cells long (to cover path and connect to adjacent wall areas)
					int startIndex = (x * 3) + (y * (int)_size.X * 3);
					_mapCells[startIndex] = CellType.WALL;
					_mapCells[startIndex + (int)_size.X] = CellType.WALL;
					_mapCells[startIndex + (int)_size.X * 2] = CellType.WALL;
					_mapCells[startIndex + (int)_size.X * 3] = CellType.WALL;
				}
			}
		}
		
		// set player start area
		// TODO: randomize this
		/*_mapCells[3] = CellType.WALL;
		_mapCells[4] = CellType.WALL;
		_mapCells[5] = CellType.WALL;
		_mapCells[6] = CellType.WALL;
		_mapCells[3 + (int)_size.X] = CellType.WALL;
		_mapCells[3 + (int)_size.X * 2] = CellType.WALL;
		_mapCells[6 + (int)_size.X] = CellType.WALL;
		_mapCells[6 + (int)_size.X * 2] = CellType.WALL;
		_mapCells[5 + (int)_size.X] = CellType.START;
		_playerDirection = Direction.DOWN;*/
		_mapCells[(int)_size.X + 1] = CellType.START;
		
	}
	
	// Checks all cells randomly and recursively clearing walls as it goes
	private void rbmCheckCells(boolean[][] cellCheckArr, boolean[][] horWallArr, boolean[][] verWallArr, Random rand, int curX, int curY, boolean firstCell) {
		int btSizeX = ((int)_size.X - 1) / 3;
		int btSizeY = ((int)_size.Y - 1) / 3;
		
		int[] dirArr = new int[] {0, 1, 2, 3};
		
		// Randomize directions to check
		MathHelper.ShuffleArray(dirArr, rand);
		
		// if this is the first cell set goal direction
		if (firstCell) {
			if (dirArr[0] == 0) _goalDirection = Direction.UP;
			else if (dirArr[0] == 1) _goalDirection = Direction.RIGHT;
			else if (dirArr[0] == 2) _goalDirection = Direction.DOWN;
			else if (dirArr[0] == 3) _goalDirection = Direction.LEFT;
		}
		
		// Check all directions
		for (int i = 0; i < dirArr.length; i++) {

			switch (dirArr[i]) {
			// Up
			case 0:
				if (curY > 0 && !cellCheckArr[curX][curY - 1]) {
					cellCheckArr[curX][curY - 1] = true;
					horWallArr[curX][curY] = true;
					rbmCheckCells(cellCheckArr, horWallArr, verWallArr, rand, curX, curY - 1, false);
				}
				break;
			// Right
			case 1:
				if (curX < btSizeX - 1 && !cellCheckArr[curX + 1][curY]) {
					cellCheckArr[curX + 1][curY] = true;
					verWallArr[curX + 1][curY] = true;
					rbmCheckCells(cellCheckArr, horWallArr, verWallArr, rand, curX + 1, curY, false);
				}
				break;
			// Down
			case 2:
				if (curY < btSizeY - 1 && !cellCheckArr[curX][curY + 1]) {
					cellCheckArr[curX][curY + 1] = true;
					horWallArr[curX][curY + 1] = true;
					rbmCheckCells(cellCheckArr, horWallArr, verWallArr, rand, curX, curY + 1, false);
				}
				break;
			// Left
			case 3:
				if (curX > 0 && !cellCheckArr[curX - 1][curY]) {
					cellCheckArr[curX - 1][curY] = true;
					verWallArr[curX][curY] = true;
					rbmCheckCells(cellCheckArr, horWallArr, verWallArr, rand, curX - 1, curY, false);
				}
				break;
			}
		}
	}
	
	private void primsMaze(Random rand) {
		// for the purpose of prims algorithm only treat open spaces as cells. 
		int pSizeX = ((int)_size.X - 1) / 3;
		int pSizeY = ((int)_size.Y - 1) / 3;
		
		//set of tuples: toCheck - starts with all cells; removed when cell added to inSet or frontier
		//set of tuples: inSet - initially draws random cell from toCheck, after that draws from frontier
		//set of tuples: frontier - when cell added to inSet all adjacent cells (that aren't already in frontier) are added to frontier
		
		ArrayList<Tuple<Integer, Integer>> inSet = new ArrayList<Tuple<Integer, Integer>>();
		ArrayList<Tuple<Integer, Integer>> frontier = new ArrayList<Tuple<Integer, Integer>>();
		boolean[][] horWallArr = new boolean[pSizeX][pSizeY + 1];
		boolean[][] verWallArr = new boolean[pSizeX + 1][pSizeY];
	
		// TODO: check to ensure start is not too close to goal 
		// TODO: randomize start direction; i.e. shouldn't always be at top
		int pStartX = rand.nextInt(pSizeX);
		int pStartY = rand.nextInt(pSizeY - 1) + 1;
		
		Tuple<Integer, Integer> newInSet = new Tuple<Integer, Integer>(pStartX, pStartY);
		inSet.add(newInSet);
		addToFrontier(frontier, inSet, newInSet, pSizeX, pSizeY);
		
		while (frontier.size() > 0) {
			Tuple<Integer, Integer> randFrontier = getRandomFromFrontier(frontier, rand);
			inSet.add(randFrontier);
			int removeDirection = primsRemoveWall(horWallArr, verWallArr, randFrontier, inSet, rand);
			// if there is only 1 element left in frontier make that the goal and make goal face open cell
			if (frontier.size() == 1) {
				_mapCells[((randFrontier.x * 3) + 2) + (randFrontier.y * (int)_size.X * 3) + (int)_size.X] = CellType.GOAL;
				_goalDirection = removeDirection;
			}
			addToFrontier(frontier, inSet, randFrontier, pSizeX, pSizeY);
			int frontierIndex = tupleFind(frontier, randFrontier.x, randFrontier.y);
			frontier.remove(frontierIndex);
		}
		
		for (int x = 0; x < pSizeX + 1; x++) {
			for (int y = 0; y < pSizeY + 1; y++) {
				if (x < pSizeX && !horWallArr[x][y])
				{
					// a single wall is 4 cells long (to cover path and connect to adjacent wall areas)
					int startIndex = (x * 3) + (y * (int)_size.X * 3);
					_mapCells[startIndex] = CellType.WALL;
					_mapCells[startIndex + 1] = CellType.WALL;
					_mapCells[startIndex + 2] = CellType.WALL;
					_mapCells[startIndex + 3] = CellType.WALL;
				}
				if (y < pSizeY && !verWallArr[x][y]) {
					// a single wall is 4 cells long (to cover path and connect to adjacent wall areas)
					int startIndex = (x * 3) + (y * (int)_size.X * 3);
					_mapCells[startIndex] = CellType.WALL;
					_mapCells[startIndex + (int)_size.X] = CellType.WALL;
					_mapCells[startIndex + (int)_size.X * 2] = CellType.WALL;
					_mapCells[startIndex + (int)_size.X * 3] = CellType.WALL;
				}
			}
		}
		
		//TODO: randomize start
		_mapCells[(int)_size.X + 1] = CellType.START;
		
	}
	
	private <X, Y> boolean tupleMatch(Tuple<X, Y> tuple, X x, Y y) {
		return tuple.x == x && tuple.y == y;
	}
	
	// returns index of specified x,y tuple in ArrayList; -1 returned if tuple not found
	private int tupleFind(ArrayList<Tuple<Integer, Integer>> arr, int x, int y) {
		for (int i = 0; i < arr.size(); i++) {
			if (tupleMatch(arr.get(i), x, y))
				return i;
		}

		return -1;
	}
	
	// Adds tuples surrounding newInSet to frontier, if they are not already there or in inSet
	private void addToFrontier(ArrayList<Tuple<Integer, Integer>> frontier, ArrayList<Tuple<Integer, Integer>> inSet, Tuple<Integer, Integer> newInSet, int sizeX, int sizeY) {
		// add up
		if (newInSet.y > 0 && !cellChecked(frontier, inSet, newInSet.x, newInSet.y - 1)) {
			Tuple<Integer, Integer> newUp = new Tuple<Integer, Integer>(newInSet.x, newInSet.y - 1);
			frontier.add(newUp);
		}
		// add right
		if (newInSet.x < sizeX - 1 && !cellChecked(frontier, inSet, newInSet.x + 1, newInSet.y)) {
			Tuple<Integer, Integer> newRight = new Tuple<Integer, Integer>(newInSet.x + 1, newInSet.y);
			frontier.add(newRight);
		}
		// add down
		if (newInSet.y < sizeY - 1 && !cellChecked(frontier, inSet, newInSet.x, newInSet.y + 1)) {
			Tuple<Integer, Integer> newDown = new Tuple<Integer, Integer>(newInSet.x, newInSet.y + 1);
			frontier.add(newDown);
		}
		// add right
		if (newInSet.x > 0 && !cellChecked(frontier, inSet, newInSet.x - 1, newInSet.y)) {
			Tuple<Integer, Integer> newLeft = new Tuple<Integer, Integer>(newInSet.x - 1, newInSet.y);
			frontier.add(newLeft);
		}
	}
	
	// returns true if the specified cell has already been added to frontier or inSet
	private boolean cellChecked(ArrayList<Tuple<Integer, Integer>> frontier, ArrayList<Tuple<Integer, Integer>> inSet, int x, int y) {
		return (tupleFind(frontier, x, y) != -1 || tupleFind(inSet, x, y) != -1);
	}
	
	// removes wall between 'cell' and random adjacent cell within inSet; returns direction from checked cell to inSet cell
	private int primsRemoveWall(boolean[][] horWallArr, boolean[][] verWallArr, Tuple<Integer, Integer> cell, ArrayList<Tuple<Integer, Integer>> inSet, Random rand) {
		int[] dirArr = new int[] {0, 1, 2, 3};
		
		// Randomize directions to check
		MathHelper.ShuffleArray(dirArr, rand);
		
		// find direction with cell in inSet
		int index = -1;
		int dir = -1;
		for (int i = 0; i < dirArr.length; i ++) {
			dir = dirArr[i];
			switch (dirArr[i]) {
			case 0:			// Up
				index = tupleFind(inSet, cell.x, cell.y - 1);
				break;
			case 1:			// Right
				index = tupleFind(inSet, cell.x + 1, cell.y);
				break;
			case 2:			// Down
				index = tupleFind(inSet, cell.x, cell.y + 1);
				break;
			case 3:			// Left
				index = tupleFind(inSet, cell.x - 1, cell.y);
				break;
			}
			// break loop if adjacent cell was found
			if (index > -1)
				break;
		}
		
		if (index == -1)
			Logger.e(_tag, "no adjacent cell was found in inSet");
		
		// remove wall based on direction
		switch (dir) {
			case 0:			// Up
				horWallArr[cell.x][cell.y] = true;
				return Direction.UP;
			case 1:			// Right
				verWallArr[cell.x + 1][cell.y] = true;
				return Direction.RIGHT;
			case 2:			// Down
				horWallArr[cell.x][cell.y + 1] = true;
				return Direction.DOWN;
			case 3:			// Left
				verWallArr[cell.x][cell.y] = true;
				return Direction.LEFT;
		}
		
		// error
		return Direction.NONE;
		
		
		
	}
	
	private Tuple<Integer, Integer> getRandomFromFrontier(ArrayList<Tuple<Integer, Integer>> frontier, Random rand) {
		int index = rand.nextInt(frontier.size());
		return frontier.get(index);
	}
	
	private void constructMaze() {
		_mapWalls = new FixedSizeArray<GameEntity>(4096);
		_mapSpikes = new FixedSizeArray<GameEntity>(256);
		_startLocation = new Vertex();
		_goalLocation = new Vertex();
		
		if (_mapCells == null || _mapCells.length == 0)
			Logger.e(_tag, "Level can not be constructed; map cells have not been set");
		
		// First pass. Get wall/spike type for each cell & start/goal //////////////////
		int[] cellTypeArr = new int[_mapCells.length];
		{
			boolean startFound = false;
			boolean goalFound = true;
			for (int yIndex = 0; yIndex < _size.Y; yIndex++) {
				for (int xIndex = 0; xIndex < _size.X; xIndex++) {
					int currentIndex = xIndex + (yIndex * (int)_size.X);
					if (_mapCells[currentIndex] == CellType.WALL || _mapCells[currentIndex] == CellType.SPIKE)
						cellTypeArr[currentIndex] = getWallSpriteType(_mapCells, currentIndex);
					else {
						cellTypeArr[currentIndex] = WallType.NONE;
						if (_mapCells[currentIndex] == CellType.START) {
							_startLocation.X = (((int)_offset.X + xIndex + 1) * _settings.CellWidth);
							_startLocation.Y = (((int)_offset.Y + (int)_size.Y - yIndex) * _settings.CellWidth);
							startFound = true;
						}
						else if (_mapCells[currentIndex] == CellType.GOAL) {
							_goalLocation.X = (((int)_offset.X + xIndex + 1) * _settings.CellWidth);
							_goalLocation.Y = (((int)_offset.Y + (int)_size.Y - yIndex) * _settings.CellWidth);
							goalFound = true;
						}
					}
				}
			}
			if (!startFound || !goalFound) {
				Logger.e(_tag, "Start or goal not found");
				return;
			}
		}
		
		// Second pass. Apply primary mask to spikes
		{
			for (int yIndex = 0; yIndex < _size.Y; yIndex++) {
				for (int xIndex = 0; xIndex < _size.X; xIndex++) {
					int currentIndex = xIndex + (yIndex * (int)_size.X);
					int cell = cellTypeArr[currentIndex];
					if ((cell & WallType.SPIKE_MASK) > 0) {
						if (cell == (WallType.SPIKE_MASK | WallType.U) ||
							cell == (WallType.SPIKE_MASK | WallType.UD) ||
							cell == (WallType.SPIKE_MASK | WallType.D)) {
							int rightIndex = currentIndex + 1;
							if (rightIndex < cellTypeArr.length && rightIndex % _size.X != 0 && cellTypeArr[rightIndex] == cell)
								cellTypeArr[currentIndex] |= WallType.PRIMARY_MASK;
						}
						else {
							int upIndex = currentIndex - (int)_size.X;
							//this check causes problems with if the vertical is length 5.
							// two down from top grid is UD, but two down from bottom is U.
							// cell types don't match so primary mask is applied incorrectly
							if (upIndex >= 0 && cellTypeArr[upIndex] == cell)
								cellTypeArr[currentIndex] |= WallType.PRIMARY_MASK;
						}
					}
				}
			}
		}
				
		// Third pass. Build Horizontal walls/spikes ////////////////
		{
			boolean trackingWall = false;
			boolean trackingSpike = false;
			int trackIndex = 0;
			for (int yIndex = 0; yIndex < _size.Y; yIndex++) {
				for (int xIndex = 0; xIndex < _size.X; xIndex++) {
					int currentIndex = xIndex + (yIndex * (int)_size.X);
					int cell = cellTypeArr[currentIndex];
					if (!trackingWall && !trackingSpike) {
						// find any non-vertical wall cell
						if(cell > -1 &&
							(cell & ~(WallType.SPIKE_MASK | WallType.PRIMARY_MASK)) != WallType.U &&
							(cell & ~(WallType.SPIKE_MASK | WallType.PRIMARY_MASK)) != WallType.D &&
							(cell & ~(WallType.SPIKE_MASK | WallType.PRIMARY_MASK)) != WallType.UD) {
							if ((cell & WallType.SPIKE_MASK) > 0 && (cell & WallType.PRIMARY_MASK) > 0)
								trackingSpike = true;
							else if ((cell & WallType.SPIKE_MASK) == 0 && cell >= 0)
								trackingWall = true;
							trackIndex = currentIndex;
						}
						if (trackingWall && cell == WallType.SINGLE) {
							_mapWalls.add(EntityHelper.wall(getWallImage(WallType.SINGLE), TMSpriteLayer.MAZE_HIGH, trackIndex - (yIndex * (int)_size.X) + 1, (int)_size.Y - yIndex, 1, true));
							trackingWall = false;
						}
					}
					else {
						// find end cell (any non-continuous left pointing cell)
						if (trackingWall &&
							(cell & WallType.L) > 0 &&
							cell != WallType.LR) {
								_mapWalls.add(EntityHelper.wall(getWallImage(cellTypeArr[trackIndex]), 
										getWallImage(cellTypeArr[currentIndex]),
										_settings.WallSprites.LR,
										trackIndex - (yIndex * (int)_size.X) + 1, 
										(int)_size.Y - yIndex, 
										currentIndex - trackIndex + 1, 
										true));
								trackingWall = false;
						}
						else if (trackingSpike &&
							(cell == (WallType.SPIKE_MASK | WallType.PRIMARY_MASK)) || // single check
							(((cell & (WallType.SPIKE_MASK | WallType.L)) > WallType.SPIKE_MASK) &&
							((cell & ~WallType.PRIMARY_MASK) !=  (WallType.SPIKE_MASK | WallType.LR)))) {
								boolean longSpike = currentIndex + 1 < _mapCells.length && _mapCells[currentIndex + 1] == CellType.SPIKE;
								_mapSpikes.add(EntityHelper.spikes(getWallImage(cellTypeArr[trackIndex]), 
										getWallImage(cellTypeArr[currentIndex]),
										_settings.WallSprites.SLR,
										trackIndex - (yIndex * (int)_size.X) + 1, 
										(int)_size.Y - yIndex, 
										longSpike ? currentIndex - trackIndex + 2 : currentIndex - trackIndex + 1, 
										true));
								trackingSpike = false;
								if (longSpike)
									xIndex++;
								if (xIndex >= _size.X) {
									yIndex++;
									xIndex = 0;
								}
						}
					}
				}
			}
		}

		// Fourth pass. Build vertical walls /////////////////////////////////
		{
			boolean trackingWall = false;
			boolean trackingSpike = false;
			int trackIndex = 0;
			for (int xIndex = 0; xIndex < _size.X; xIndex++) {
				for (int yIndex = 0; yIndex < _size.Y; yIndex++) {
					int currentIndex = xIndex + (yIndex * (int)_size.X);
					int cell = cellTypeArr[currentIndex];
					if (!trackingWall && !trackingSpike) {
						// find vertical wall cell
						if(cell > -1 &&
							((cell & ~(WallType.SPIKE_MASK | WallType.PRIMARY_MASK)) == WallType.U ||
							(cell & ~(WallType.SPIKE_MASK | WallType.PRIMARY_MASK)) == WallType.D ||
							(cell & ~(WallType.SPIKE_MASK | WallType.PRIMARY_MASK)) == WallType.UD)) {
							if ((cell & WallType.SPIKE_MASK) > 0 && (cell & WallType.PRIMARY_MASK) > 0)
								trackingSpike = true;
							else if ((cell & WallType.SPIKE_MASK) == 0 && cell >= 0)
								trackingWall = true;
							trackIndex = currentIndex;
						}
					}
					else {
						if (trackingWall) {
							if (((cell & ~WallType.SPIKE_MASK) & WallType.U) > 0 &&
								(cell & ~WallType.SPIKE_MASK) != WallType.UD) {
								if ((cell & ~WallType.SPIKE_MASK) == WallType.U) {
									_mapWalls.add(EntityHelper.wall(_settings.WallSprites.U,
											getWallImage(cellTypeArr[trackIndex]),
											_settings.WallSprites.UD,
											xIndex + 1, 
											(int)_size.Y - yIndex, 
											((currentIndex - trackIndex) / (int)_size.X) + 1, 
											false));
									trackingWall = false;
								}
								else {
									_mapWalls.add(EntityHelper.wall(_settings.WallSprites.UD,
											getWallImage(cellTypeArr[trackIndex]),
											_settings.WallSprites.UD,
											xIndex + 1, 
											(int)_size.Y - yIndex + 1, 
											((currentIndex - trackIndex) / (int)_size.X), 
											false));
									trackingWall = false;
								}
							}
						}
						else if (trackingSpike) {
							if ((cell & (WallType.SPIKE_MASK | WallType.U)) > WallType.SPIKE_MASK &&
								(cell & ~WallType.PRIMARY_MASK) != (WallType.SPIKE_MASK | WallType.UD)) {
								boolean longSpike = false;
								if ((cell & ~WallType.PRIMARY_MASK) == (WallType.SPIKE_MASK | WallType.U)) {
									longSpike = currentIndex + (int)_size.X < _mapCells.length && _mapCells[currentIndex + (int)_size.X] == CellType.SPIKE;
									_mapSpikes.add(EntityHelper.spikes(_settings.WallSprites.SU,
											getWallImage(cellTypeArr[trackIndex]),
											_settings.WallSprites.SUD,
											xIndex + 1, 
											longSpike ? (int)_size.Y - yIndex - 1 : (int)_size.Y - yIndex, 
											longSpike ? ((currentIndex - trackIndex) / (int)_size.X) + 2 : ((currentIndex - trackIndex) / (int)_size.X) + 1, 
											false));
									trackingSpike = false;
								}
								else {
									_mapSpikes.add(EntityHelper.spikes(_settings.WallSprites.SUD,
											getWallImage(cellTypeArr[trackIndex]),
											_settings.WallSprites.SUD,
											xIndex + 1, 
											(int)_size.Y - yIndex + 1, 
											((currentIndex - trackIndex) / (int)_size.X), 
											false));
									trackingSpike = false;
								}
								if (longSpike)
									yIndex++;
								if (yIndex >= _size.Y) {
									xIndex++;
									yIndex = 0;
								}
							}
						}
					}	
				}
			}
		}
	}
	
	private int getWallSpriteType(char[] maze, int index) {
		boolean spike = maze[index] == CellType.SPIKE;
		int type = WallType.NONE;
		if (maze[index] == CellType.WALL)
			type = WallType.SINGLE;
		else if (maze[index] == CellType.SPIKE)
			type = WallType.SINGLE | WallType.SPIKE_MASK;
		
		if (type != WallType.NONE) {
			if (spike) {
				int downIndex = index + ((int)_size.X * 2);
				int upIndex =index - ((int)_size.X * 2);
				int leftIndex = index - 2;
				int rightIndex = index + 2;
				
				int downCloseIndex = index + (int)_size.X;
				int upCloseIndex = index - (int)_size.X;
				int leftCloseIndex = index - 1;
				int rightCloseIndex = index + 1;
				
				if (downIndex < maze.length && maze[downIndex] == CellType.SPIKE && maze[downCloseIndex] == CellType.SPIKE)
					type |= WallType.D;
				if (upIndex >= 0 && maze[upIndex] ==  CellType.SPIKE && maze[upCloseIndex] == CellType.SPIKE)
					type |= WallType.U;
				if (leftIndex >= 0 && (leftIndex + 1) % _size.X != 0 && maze[leftIndex] == CellType.SPIKE && maze[leftCloseIndex] == CellType.SPIKE)
					type |= WallType.L;
				if (rightIndex < maze.length && rightIndex % _size.X != 0 && maze[rightIndex] == CellType.SPIKE && maze[rightCloseIndex] == CellType.SPIKE)
					type |= WallType.R;
				
				//special case
				if (type == WallType.SPIKE_MASK &&
					leftCloseIndex >= 0 && 
					(leftCloseIndex + 1) % _size.X != 0 && 
					maze[leftCloseIndex] == CellType.SPIKE &&
					rightCloseIndex < maze.length && 
					rightCloseIndex % _size.X != 0 && 
					maze[rightCloseIndex] == CellType.SPIKE)
					type |= WallType.L;
			}
			else {
				int downIndex = index + (int)_size.X;
				int upIndex = index - (int)_size.X;
				int leftIndex = index - 1;
				int rightIndex = index + 1;
				
				if (downIndex < maze.length && maze[downIndex] == CellType.WALL)
					type |= WallType.D;
				if (upIndex >= 0 && maze[upIndex] ==  CellType.WALL)
					type |= WallType.U;
				if (leftIndex >= 0 && (leftIndex + 1) % _size.X != 0 && maze[leftIndex] == CellType.WALL)
					type |= WallType.L;
				if (rightIndex < maze.length && rightIndex % _size.X != 0 && maze[rightIndex] == CellType.WALL)
					type |= WallType.R;
			}
		}
		
		return type;
	}

	private int getWallImage(int type) {
		if ((type & WallType.SPIKE_MASK) > 0) {
			if ((type & ~(WallType.SPIKE_MASK | WallType.PRIMARY_MASK)) == WallType.L)
				return _settings.WallSprites.SL;
			else if ((type & ~(WallType.SPIKE_MASK | WallType.PRIMARY_MASK)) == WallType.U)
				return _settings.WallSprites.SU;
			else if ((type & ~(WallType.SPIKE_MASK | WallType.PRIMARY_MASK)) == WallType.R)
				return _settings.WallSprites.SR;
			else if ((type & ~(WallType.SPIKE_MASK | WallType.PRIMARY_MASK)) == WallType.D)
				return _settings.WallSprites.SD;
			else if ((type & ~(WallType.SPIKE_MASK | WallType.PRIMARY_MASK)) == WallType.LU)
				return _settings.WallSprites.SLU;
			else if ((type & ~(WallType.SPIKE_MASK | WallType.PRIMARY_MASK)) == WallType.LR)
				return _settings.WallSprites.SLR;
			else if ((type & ~(WallType.SPIKE_MASK | WallType.PRIMARY_MASK)) == WallType.LD)
				return _settings.WallSprites.SLD;
			else if ((type & ~(WallType.SPIKE_MASK | WallType.PRIMARY_MASK)) == WallType.UR)
				return _settings.WallSprites.SUR;
			else if ((type & ~(WallType.SPIKE_MASK | WallType.PRIMARY_MASK)) == WallType.UD)
				return _settings.WallSprites.SUD;
			else if ((type & ~(WallType.SPIKE_MASK | WallType.PRIMARY_MASK)) == WallType.RD)
				return _settings.WallSprites.SRD;
			else if ((type & ~(WallType.SPIKE_MASK | WallType.PRIMARY_MASK)) == WallType.LUR)
				return _settings.WallSprites.SLUR;
			else if ((type & ~(WallType.SPIKE_MASK | WallType.PRIMARY_MASK)) == WallType.LUD)
				return _settings.WallSprites.SLUD;
			else if ((type & ~(WallType.SPIKE_MASK | WallType.PRIMARY_MASK)) == WallType.LRD)
				return _settings.WallSprites.SLRD;
			else if ((type & ~(WallType.SPIKE_MASK | WallType.PRIMARY_MASK)) == WallType.URD)
				return _settings.WallSprites.SURD;
			else if ((type & ~(WallType.SPIKE_MASK | WallType.PRIMARY_MASK)) == WallType.LURD)
				return _settings.WallSprites.SLURD;
			else if ((type & ~(WallType.SPIKE_MASK | WallType.PRIMARY_MASK)) == WallType.SINGLE)
				return _settings.WallSprites.SSingle;
			else {
				Logger.e(_tag, "Invalid spike type");
				return Image.NONE;
			}
		}
		else {
			if (type == WallType.L)
				return _settings.WallSprites.L;
			else if (type == WallType.U)
				return _settings.WallSprites.U;
			else if (type == WallType.R)
				return _settings.WallSprites.R;
			else if (type == WallType.D)
				return _settings.WallSprites.D;
			else if (type == WallType.LU)
				return _settings.WallSprites.LU;
			else if (type == WallType.LR)
				return _settings.WallSprites.LR;
			else if (type == WallType.LD)
				return _settings.WallSprites.LD;
			else if (type == WallType.UR)
				return _settings.WallSprites.UR;
			else if (type == WallType.UD)
				return _settings.WallSprites.UD;
			else if (type == WallType.RD)
				return _settings.WallSprites.RD;
			else if (type == WallType.LUR)
				return _settings.WallSprites.LUR;
			else if (type == WallType.LUD)
				return _settings.WallSprites.LUD;
			else if (type == WallType.LRD)
				return _settings.WallSprites.LRD;
			else if (type == WallType.URD)
				return _settings.WallSprites.URD;
			else if (type == WallType.LURD)
				return _settings.WallSprites.LURD;
			else if (type == WallType.SINGLE)
				return _settings.WallSprites.Single;
			else {
				Logger.e(_tag, "Invalid wall type");
				return Image.NONE;
			}
		}
	}

}
