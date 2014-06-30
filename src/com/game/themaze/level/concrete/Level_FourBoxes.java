package com.game.themaze.level.concrete;

import com.game.loblib.entity.GameEntity;
import com.game.loblib.utility.Global;
import com.game.loblib.utility.Manager;
import com.game.loblib.utility.area.AreaType;
import com.game.the_maze.R;
import com.game.themaze.behavior.CollisionSenderBehavior;
import com.game.themaze.behavior.TMBehaviorType;
import com.game.themaze.collision.TMCollisionLayer;
import com.game.themaze.entity.EntityHelper;
import com.game.themaze.graphics.TMImage;
import com.game.themaze.graphics.TMSpriteLayer;
import com.game.themaze.level.Level;

public class Level_FourBoxes extends Level {
	
	protected GameEntity _box1Trigger;
	protected GameEntity _box2Trigger;
	protected GameEntity _box3Trigger;
	protected GameEntity _box4Trigger;
	protected GameEntity _box1Icon;
	protected GameEntity _box2Icon;
	protected GameEntity _box3Icon;
	protected GameEntity _box4Icon;
	protected GameEntity _box1WallN;
	protected GameEntity _box1WallE;
	protected GameEntity _box1WallS;
	protected GameEntity _box1WallW;
	protected GameEntity _box2WallN;
	protected GameEntity _box2WallE;
	protected GameEntity _box2WallS;
	protected GameEntity _box2WallW;
	protected GameEntity _box3WallN;
	protected GameEntity _box3WallE;
	protected GameEntity _box3WallS;
	protected GameEntity _box3WallW;
	protected GameEntity _box4WallN;
	protected GameEntity _box4WallE;
	protected GameEntity _box4WallS;
	protected GameEntity _box4WallW;
	protected GameEntity _goalWall;
	protected GameEntity _box1Button;
	protected GameEntity _box2Button;
	protected GameEntity _box3Button;
	protected GameEntity _box4Button;
	protected int _box1State = 0;
	protected int _box2State = 0;
	protected int _box3State = 0;
	protected int _box4State = 0;
	protected boolean _goalOpen = false;
	
	public Level_FourBoxes() {
		_settings.ResourceId = R.raw.level_03;
		_settings.CellWidth = (int)(Global.Renderer.Width / 20f);
		_settings.EnableActionButton = true;
		_settings.BackgroundImage = TMImage.BACKGROUND_DIRT_CRACKED;
	}
	
	@Override
	public void init() {
		super.init();
		
		_box1Trigger = EntityHelper.trigger(15, 32, 4, 4);
		_box2Trigger = EntityHelper.trigger(33, 32, 4, 4);
		_box3Trigger = EntityHelper.trigger(15, 14, 4, 4);
		_box4Trigger = EntityHelper.trigger(33, 14, 4, 4);
		_entities.add(_box1Trigger);
		_entities.add(_box2Trigger);
		_entities.add(_box3Trigger);
		_entities.add(_box4Trigger);
		_actionTriggers.add(_box1Trigger);
		_actionTriggers.add(_box2Trigger);
		_actionTriggers.add(_box3Trigger);
		_actionTriggers.add(_box4Trigger);
		
		_box1Icon = EntityHelper.levelGraphic(TMImage.LEVEL_03_EMBLEM, TMSpriteLayer.FOREGROUND, 24, 41, 2, 2);
		_box1Icon.Attributes.Sprite.setFrame(2);
		_box2Icon = EntityHelper.levelGraphic(TMImage.LEVEL_03_EMBLEM, TMSpriteLayer.FOREGROUND, 42, 25, 2, 2);
		_box2Icon.Attributes.Sprite.setFrame(0);
		_box3Icon = EntityHelper.levelGraphic(TMImage.LEVEL_03_EMBLEM, TMSpriteLayer.FOREGROUND, 24, 7, 2, 2);
		_box3Icon.Attributes.Sprite.setFrame(0);
		_box4Icon = EntityHelper.levelGraphic(TMImage.LEVEL_03_EMBLEM, TMSpriteLayer.FOREGROUND, 42, 23, 2, 2);
		_box4Icon.Attributes.Sprite.setFrame(2);
		_entities.add(_box1Icon);
		_entities.add(_box2Icon);
		_entities.add(_box3Icon);
		_entities.add(_box4Icon);
		
		_box1WallN = EntityHelper.wall(TMImage.WALL_HORIZONTAL_2, 16, 40, 2, true);
		_box1WallE = EntityHelper.wall(TMImage.WALL_VERTICAL_2, 23, 33, 2, false);
		_box1WallS = EntityHelper.wall(TMImage.WALL_HORIZONTAL_2, 16, 27, 2, true);
		_box1WallW = EntityHelper.wall(TMImage.WALL_VERTICAL_2, 10, 33, 2, false);
		_entities.add(_box1WallN);
		_entities.add(_box1WallE);
		_entities.add(_box1WallS);
		_entities.add(_box1WallW);
		
		_box2WallN = EntityHelper.wall(TMImage.WALL_HORIZONTAL_2, 34, 40, 2, true);
		_box2WallE = EntityHelper.wall(TMImage.WALL_VERTICAL_2, 41, 33, 2, false);
		_box2WallS = EntityHelper.wall(TMImage.WALL_HORIZONTAL_2, 34, 27, 2, true);
		_box2WallW = EntityHelper.wall(TMImage.WALL_VERTICAL_2, 28, 33, 2, false);
		_entities.add(_box2WallN);
		_entities.add(_box2WallE);
		_entities.add(_box2WallS);
		_entities.add(_box2WallW);
		
		_box3WallN = EntityHelper.wall(TMImage.WALL_HORIZONTAL_2, 16, 22, 2, true);
		_box3WallE = EntityHelper.wall(TMImage.WALL_VERTICAL_2, 23, 15, 2, false);
		_box3WallS = EntityHelper.wall(TMImage.WALL_HORIZONTAL_2, 16, 9, 2, true);
		_box3WallW = EntityHelper.wall(TMImage.WALL_VERTICAL_2, 10, 15, 2, false);
		_entities.add(_box3WallN);
		_entities.add(_box3WallE);
		_entities.add(_box3WallS);
		_entities.add(_box3WallW);
		
		_box4WallN = EntityHelper.wall(TMImage.WALL_HORIZONTAL_2, 34, 22, 2, true);
		_box4WallE = EntityHelper.wall(TMImage.WALL_VERTICAL_2, 41, 15, 2, false);
		_box4WallS = EntityHelper.wall(TMImage.WALL_HORIZONTAL_2, 34, 9, 2, true);
		_box4WallW = EntityHelper.wall(TMImage.WALL_VERTICAL_2, 28, 15, 2, false);
		_entities.add(_box4WallN);
		_entities.add(_box4WallE);
		_entities.add(_box4WallS);
		_entities.add(_box4WallW);
		
		_goalWall = EntityHelper.levelGraphic(TMImage.DOOR_LOCKED_HORIZONTAL_RED, TMSpriteLayer.MAZE_LOW, 34, 8, 2, 1);
		_goalWall.addBehavior(new CollisionSenderBehavior(AreaType.Rectangle, TMCollisionLayer.MAIN_BLOCK));
		_entities.add(_goalWall);
		
		_box1Button = EntityHelper.levelGraphic(TMImage.TRIGGER_BUTTON_LARGE, TMSpriteLayer.BACKGROUND2, 15, 32, 4, 4);
		_box2Button = EntityHelper.levelGraphic(TMImage.TRIGGER_BUTTON_LARGE, TMSpriteLayer.BACKGROUND2, 33, 32, 4, 4);
		_box3Button = EntityHelper.levelGraphic(TMImage.TRIGGER_BUTTON_LARGE, TMSpriteLayer.BACKGROUND2, 15, 14, 4, 4);
		_box4Button = EntityHelper.levelGraphic(TMImage.TRIGGER_BUTTON_LARGE, TMSpriteLayer.BACKGROUND2, 33, 14, 4, 4);
		_entities.add(_box1Button);
		_entities.add(_box2Button);
		_entities.add(_box3Button);
		_entities.add(_box4Button);
		
		_box1State = 0;
		_box2State = 0;
		_box3State = 0;
		_box4State = 0;
		
		_goalOpen = false;
	}
	
	@Override
	public void enableBehaviors() {
		super.enableBehaviors();
		
		_actionButton.disableBehaviors(TMBehaviorType.BUTTON);
		_box1WallN.disableBehaviors();
		_box1WallE.disableBehaviors();
		_box2WallE.disableBehaviors();
		_box2WallW.disableBehaviors();
		_box3WallS.disableBehaviors();
		_box3WallW.disableBehaviors();
		_box4WallS.disableBehaviors();
		_box4WallW.disableBehaviors();
	}
	
	@Override
	protected void onActionClicked() {
		if (Manager.Trigger.hit(_box1Trigger)) {
			switch (_box1State) {
			case 0:
				_box1State++;
				_box1WallN.enableBehaviors();
				_box1WallS.disableBehaviors();
				_box1Icon.Attributes.Area.setPosition(xGridPosition(24), yGridPosition(25));
				_box1Icon.Attributes.Sprite.setFrame(4);
				break;
			case 1:
				_box1State++;
				_box1WallE.enableBehaviors();
				_box1WallW.disableBehaviors();
				_box1Icon.Attributes.Area.setPosition(xGridPosition(8), yGridPosition(25));
				_box1Icon.Attributes.Sprite.setFrame(1);
				break;
			case 2:
				_box1State++;
				_box1WallS.enableBehaviors();
				_box1WallN.disableBehaviors();
				_box1Icon.Attributes.Area.setPosition(xGridPosition(8), yGridPosition(41));
				_box1Icon.Attributes.Sprite.setFrame(3);
				break;
			case 3:
				_box1State = 0;
				_box1WallW.enableBehaviors();
				_box1WallE.disableBehaviors();
				_box1Icon.Attributes.Area.setPosition(xGridPosition(24), yGridPosition(41));
				_box1Icon.Attributes.Sprite.setFrame(2);
				break;
			}
		}
		else if (Manager.Trigger.hit(_box2Trigger)) {
			switch (_box2State) {
			case 0:
				_box2State++;
				_box2WallE.enableBehaviors();
				_box2WallW.enableBehaviors();
				_box2WallN.disableBehaviors();
				_box2WallS.disableBehaviors();
				_box2Icon.Attributes.Area.setPosition(xGridPosition(26), yGridPosition(25));
				_box2Icon.Attributes.Sprite.setFrame(5);
				break;
			case 1:
				_box2State++;
				_box2WallN.enableBehaviors();
				_box2WallS.enableBehaviors();
				_box2WallE.disableBehaviors();
				_box2WallW.disableBehaviors();
				_box2Icon.Attributes.Area.setPosition(xGridPosition(26), yGridPosition(41));
				_box2Icon.Attributes.Sprite.setFrame(3);
				break;
			case 2:
				_box2State++;
				_box2WallE.enableBehaviors();
				_box2WallW.enableBehaviors();
				_box2WallN.disableBehaviors();
				_box2WallS.disableBehaviors();
				_box2Icon.Attributes.Area.setPosition(xGridPosition(42), yGridPosition(41));
				_box2Icon.Attributes.Sprite.setFrame(2);
				break;
			case 3:
				_box2State = 0;
				_box2WallN.enableBehaviors();
				_box2WallS.enableBehaviors();
				_box2WallE.disableBehaviors();
				_box2WallW.disableBehaviors();
				_box2Icon.Attributes.Area.setPosition(xGridPosition(42), yGridPosition(25));
				_box2Icon.Attributes.Sprite.setFrame(0);
				break;
			}
		}
		else if (Manager.Trigger.hit(_box3Trigger)) {
			switch (_box3State) {
			case 0:
				_box3State++;
				_box3WallS.enableBehaviors();
				_box3WallN.disableBehaviors();
				_box3Icon.Attributes.Area.setPosition(xGridPosition(8), yGridPosition(7));
				_box3Icon.Attributes.Sprite.setFrame(1);
				break;
			case 1:
				_box3State++;
				_box3WallW.enableBehaviors();
				_box3WallE.disableBehaviors();
				_box3Icon.Attributes.Area.setPosition(xGridPosition(8), yGridPosition(23));
				_box3Icon.Attributes.Sprite.setFrame(3);
				break;
			case 2:
				_box3State++;
				_box3WallN.enableBehaviors();
				_box3WallS.disableBehaviors();
				_box3Icon.Attributes.Area.setPosition(xGridPosition(24), yGridPosition(23));
				_box3Icon.Attributes.Sprite.setFrame(6);
				break;
			case 3:
				_box3State = 0;
				_box3WallE.enableBehaviors();
				_box3WallW.disableBehaviors();
				_box3Icon.Attributes.Area.setPosition(xGridPosition(24), yGridPosition(7));
				_box3Icon.Attributes.Sprite.setFrame(0);
				break;
			}
		}
		else if (Manager.Trigger.hit(_box4Trigger)) {
			switch (_box4State) {
			case 0:
				_box4State++;
				_box4WallS.enableBehaviors();
				_box4WallN.disableBehaviors();
				_box4Icon.Attributes.Area.setPosition(xGridPosition(42), yGridPosition(7));
				_box4Icon.Attributes.Sprite.setFrame(0);
				break;
			case 1:
				_box4State++;
				_box4WallW.enableBehaviors();
				_box4WallE.disableBehaviors();
				_box4Icon.Attributes.Area.setPosition(xGridPosition(26), yGridPosition(7));
				_box4Icon.Attributes.Sprite.setFrame(1);
				break;
			case 2:
				_box4State++;
				_box4WallN.enableBehaviors();
				_box4WallS.disableBehaviors();
				_box4Icon.Attributes.Area.setPosition(xGridPosition(26), yGridPosition(23));
				_box4Icon.Attributes.Sprite.setFrame(7);
				break;
			case 3:
				_box4State = 0;
				_box4WallE.enableBehaviors();
				_box4WallW.disableBehaviors();
				_box4Icon.Attributes.Area.setPosition(xGridPosition(42), yGridPosition(23));
				_box4Icon.Attributes.Sprite.setFrame(2);
				break;
			}
		}
		
		if (_goalOpen) {
			_goalOpen = false;
			_goalWall.enableBehaviors();
			switch (_box1State) {
			case 0: _box1Icon.Attributes.Sprite.setFrame(2);break;
			case 1: _box1Icon.Attributes.Sprite.setFrame(4);break;
			case 2: _box1Icon.Attributes.Sprite.setFrame(1);break;
			case 3: _box1Icon.Attributes.Sprite.setFrame(3);break;
			}
			switch (_box2State) {
			case 0: _box2Icon.Attributes.Sprite.setFrame(0);break;
			case 1: _box2Icon.Attributes.Sprite.setFrame(5);break;
			case 2: _box2Icon.Attributes.Sprite.setFrame(3);break;
			case 3: _box2Icon.Attributes.Sprite.setFrame(2);break;
			}
			switch (_box3State) {
			case 0: _box3Icon.Attributes.Sprite.setFrame(0);break;
			case 1: _box3Icon.Attributes.Sprite.setFrame(1);break;
			case 2: _box3Icon.Attributes.Sprite.setFrame(3);break;
			case 3: _box3Icon.Attributes.Sprite.setFrame(6);break;
			}
			switch (_box4State) {
			case 0: _box4Icon.Attributes.Sprite.setFrame(2);break;
			case 1: _box4Icon.Attributes.Sprite.setFrame(0);break;
			case 2: _box4Icon.Attributes.Sprite.setFrame(1);break;
			case 3: _box4Icon.Attributes.Sprite.setFrame(7);break;
			}
		}
		else if (_box1State == 1 &&
			_box2State == 1 &&
			_box3State == 3 &&
			_box4State == 3) {
			_goalOpen = true;
			_box1Icon.Attributes.Sprite.setFrame(8);
			_box2Icon.Attributes.Sprite.setFrame(9);
			_box3Icon.Attributes.Sprite.setFrame(10);
			_box4Icon.Attributes.Sprite.setFrame(11);
			_goalWall.disableBehaviors();
		}
	}
}
