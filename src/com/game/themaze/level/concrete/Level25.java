package com.game.themaze.level.concrete;

import com.game.loblib.entity.GameEntity;
import com.game.loblib.graphics.Image;
import com.game.loblib.utility.Global;
import com.game.loblib.utility.android.FixedSizeArray;
import com.game.the_maze.R;
import com.game.themaze.behavior.AttachBehavior;
import com.game.themaze.behavior.MimicRenderBehavior;
import com.game.themaze.entity.EntityHelper;
import com.game.themaze.graphics.TMImage;
import com.game.themaze.graphics.TMSpriteLayer;
import com.game.themaze.level.Level;
import com.game.themaze.level.LevelSettings.LevelSettingPeekType;
import com.game.themaze.utility.TMManager;

// Three guys - simultaneous
public class Level25 extends Level {

	protected GameEntity _playerUp;
	protected GameEntity _playerDown;
	protected GameEntity _goalUp;
	protected GameEntity _goalDown;
	protected FixedSizeArray<GameEntity> _invisibleWalls;
	protected GameEntity _cameraRail;
	
	public Level25() {
		_settings.ResourceId = R.raw.level_06;
		_settings.CellWidth = (int)(Global.Renderer.Width / 32f);
		_settings.PeekType = LevelSettingPeekType.Off;
		_settings.BackgroundImage = TMImage.BACKGROUND_TILE_BLACK;
	}
	
	@Override
	public void onInit() {
		
		float cellWidth = TMManager.Level.getCellWidth();
		
		_playerUp = new GameEntity();
		_playerUp.Attributes.Area.setSize((3f * cellWidth / 4f), cellWidth);
		_playerUp.addBehavior(new MimicRenderBehavior(_player, TMSpriteLayer.PLAYER));
		_playerUp.addBehavior(new AttachBehavior(_player, 0f, 15 * cellWidth));
		_entities.add(_playerUp);
		
		_playerDown = new GameEntity();
		_playerDown.Attributes.Area.setSize((3f * cellWidth / 4f), cellWidth);
		_playerDown.addBehavior(new MimicRenderBehavior(_player, TMSpriteLayer.PLAYER));
		_playerDown.addBehavior(new AttachBehavior(_player, 0f, -15 * cellWidth));
		_entities.add(_playerDown);
		
		_goalUp = new GameEntity();
		_goalUp.Attributes.Area.setSize(cellWidth * 1.8f, cellWidth * 1.8f);
		_goalUp.addBehavior(new MimicRenderBehavior(_goal, TMSpriteLayer.PLAYER));
		_goalUp.addBehavior(new AttachBehavior(_goal, 0f, 15 * cellWidth));
		_entities.add(_goalUp);
		
		_goalDown = new GameEntity();
		_goalDown.Attributes.Area.setSize(cellWidth * 1.8f, cellWidth * 1.8f);
		_goalDown.addBehavior(new MimicRenderBehavior(_goal, TMSpriteLayer.PLAYER));
		_goalDown.addBehavior(new AttachBehavior(_goal, 0f, -15 * cellWidth));
		_entities.add(_goalDown);
		
		_invisibleWalls = new FixedSizeArray<GameEntity>(43);
		_invisibleWalls.add(EntityHelper.wall(Image.NONE, 14, 23, 2, false));
		_invisibleWalls.add(EntityHelper.wall(Image.NONE, 12, 25, 6, true));
		_invisibleWalls.add(EntityHelper.wall(Image.NONE, 17, 25, 4, false));
		_invisibleWalls.add(EntityHelper.wall(Image.NONE, 17, 28, 7, true));
		_invisibleWalls.add(EntityHelper.wall(Image.NONE, 17, 31, 7, true)); 
		_invisibleWalls.add(EntityHelper.wall(Image.NONE, 20, 32, 2, false)); 
		_invisibleWalls.add(EntityHelper.wall(Image.NONE, 20, 25, 9, true)); 
		_invisibleWalls.add(EntityHelper.wall(Image.NONE, 26, 31, 4, false)); 
		_invisibleWalls.add(EntityHelper.wall(Image.NONE, 26, 31, 6, true)); 
		_invisibleWalls.add(EntityHelper.wall(Image.NONE, 29, 34, 15, true)); 
		_invisibleWalls.add(EntityHelper.wall(Image.NONE, 31, 25, 3, false)); 
		_invisibleWalls.add(EntityHelper.wall(Image.NONE, 34, 25, 10, true)); 
		_invisibleWalls.add(EntityHelper.wall(Image.NONE, 34, 29, 2, false)); 
		_invisibleWalls.add(EntityHelper.wall(Image.NONE, 37, 28, 10, true)); 
		_invisibleWalls.add(EntityHelper.wall(Image.NONE, 43, 29, 6, false)); 
		_invisibleWalls.add(EntityHelper.wall(Image.NONE, 46, 29, 3, false)); 
		_invisibleWalls.add(EntityHelper.wall(Image.NONE, 46, 34, 3, false)); 
		_invisibleWalls.add(EntityHelper.wall(Image.NONE, 47, 31, 3, true)); 
		_invisibleWalls.add(EntityHelper.wall(Image.NONE, 47, 34, 3, true)); 
		_invisibleWalls.add(EntityHelper.wall(Image.NONE, 49, 28, 4, true)); 
		_invisibleWalls.add(EntityHelper.wall(Image.NONE, 49, 32, 2, false)); 
		_invisibleWalls.add(EntityHelper.wall(Image.NONE, 52, 23, 5, false)); 
		_invisibleWalls.add(EntityHelper.wall(Image.NONE, 52, 34, 4, true)); 
		_invisibleWalls.add(EntityHelper.wall(Image.NONE, 55, 23, 3, false)); 
		_invisibleWalls.add(EntityHelper.wall(Image.NONE, 55, 28, 4, false)); 
		_invisibleWalls.add(EntityHelper.wall(Image.NONE, 55, 35, 2, false)); 
		_invisibleWalls.add(EntityHelper.wall(Image.NONE, 56, 31, 2, true)); 
		_invisibleWalls.add(EntityHelper.wall(Image.NONE, 59, 34, 3, true)); 
		_invisibleWalls.add(EntityHelper.wall(Image.NONE, 61, 25, 4, true)); 
		_invisibleWalls.add(EntityHelper.wall(Image.NONE, 61, 26, 6, false)); 
		_invisibleWalls.add(EntityHelper.wall(Image.NONE, 62, 31, 3, true)); 
		_invisibleWalls.add(EntityHelper.wall(Image.NONE, 64, 28, 7, true)); 
		_invisibleWalls.add(EntityHelper.wall(Image.NONE, 67, 29, 2, false)); 
		_invisibleWalls.add(EntityHelper.wall(Image.NONE, 67, 25, 4, true)); 
		_invisibleWalls.add(EntityHelper.wall(Image.NONE, 70, 32, 3, false)); 
		_invisibleWalls.add(EntityHelper.wall(Image.NONE, 21, 22, 2, true)); 
		_invisibleWalls.add(EntityHelper.wall(Image.NONE, 50, 22, 2, true)); 
		_invisibleWalls.add(EntityHelper.wall(Image.NONE, 62, 22, 2, true)); 
		_invisibleWalls.add(EntityHelper.wall(Image.NONE, 13, 37, 2, true)); 
		_invisibleWalls.add(EntityHelper.wall(Image.NONE, 29, 37, 2, true)); 
		_invisibleWalls.add(EntityHelper.wall(Image.NONE, 44, 37, 2, true)); 
		_invisibleWalls.add(EntityHelper.wall(Image.NONE, 56, 37, 2, true)); 
		_invisibleWalls.add(EntityHelper.wall(Image.NONE, 68, 37, 2, true)); 
		_entities.addAll(_invisibleWalls);
		
		_cameraRail = new GameEntity();
		AttachBehavior ab = new AttachBehavior(_player);
			ab.enableFixedY((30 + _offset.Y) * cellWidth);
		_cameraRail.addBehavior(ab);
		_entities.add(_cameraRail);
		
		Global.Camera.Anchor = _cameraRail;
		Global.Camera.Threshold.setPosition(10, 10);
	}
}
