package com.game.themaze.level.concrete;

import com.game.loblib.entity.GameEntity;
import com.game.loblib.utility.Global;
import com.game.loblib.utility.Manager;
import com.game.loblib.utility.area.Vertex;
import com.game.the_maze.R;
import com.game.themaze.behavior.AttachBehavior;
import com.game.themaze.behavior.MimicRenderBehavior;
import com.game.themaze.behavior.TMBehaviorType;
import com.game.themaze.collision.TMCollisionLayer;
import com.game.themaze.graphics.TMImage;
import com.game.themaze.graphics.TMSpriteLayer;
import com.game.themaze.level.Level;
import com.game.themaze.level.LevelSettings.LevelSettingPeekType;
import com.game.themaze.utility.TMManager;

// Three guys - switch
public class Level26 extends Level {
	
	protected GameEntity _player1;
	protected GameEntity _player2;
	protected GameEntity _cameraRail;
	protected int _state = 1;
	protected boolean _actionEnabled;
	protected Vertex _player1Center = new Vertex();
	
	public Level26() {
		_settings.ResourceId = R.raw.level_07;
		_settings.CellWidth = (int)(Global.Renderer.Width / 32f);
		_settings.EnableActionButton = true;
		_settings.PeekType = LevelSettingPeekType.Off;
		_settings.BackgroundImage = TMImage.BACKGROUND_TILE_BLACK;
	}
	
	@Override
	public void onInit() {
		
		float cellWidth = TMManager.Level.getCellWidth();
		
		_player2 = new GameEntity();
		_player2.Attributes.Area.setSize((3f * cellWidth / 4f), cellWidth);
		MimicRenderBehavior mrb2 = new MimicRenderBehavior(_player, TMSpriteLayer.PLAYER);
		mrb2.enableCustomAlpha(.3f);
		_player2.addBehavior(mrb2);
		_player2.addBehavior(new AttachBehavior(_player, 0f, 15 * cellWidth));
		_entities.add(_player2);
		
		_player1 = new GameEntity();
		_player1.Attributes.Area.setSize((3f * cellWidth / 4f), cellWidth);
		MimicRenderBehavior mrb1 = new MimicRenderBehavior(_player, TMSpriteLayer.PLAYER);
		mrb1.enableCustomAlpha(.3f);
		_player1.addBehavior(mrb1);
		_player1.addBehavior(new AttachBehavior(_player, 0f, -15 * TMManager.Level.getCellWidth()));
		_entities.add(_player1);
		
		_cameraRail = new GameEntity();
		AttachBehavior ab = new AttachBehavior(_player);
			ab.enableFixedY((24 + _offset.Y) * cellWidth);
		_cameraRail.addBehavior(ab);
		_entities.add(_cameraRail);
		
		Global.Camera.Anchor = _cameraRail;
		Global.Camera.Threshold.setPosition(10, 10);
	}
	
	@Override
	public void update() {
		super.update();
		_player1.Attributes.Area.getCenter(_player1Center);
		if (_actionEnabled) {
			if (Manager.Collision.getCollision(_player1Center, TMCollisionLayer.MAIN_BLOCK) != null) {
				_actionEnabled = false;
				disableActionButton();
			}
		}
		else {
			if (Manager.Collision.getCollision(_player1Center, TMCollisionLayer.MAIN_BLOCK) == null) {
				_actionEnabled = true;
				enableActionButton();
			}
		}
	}
	
	@Override
	protected void onActionClicked() {
		float cellWidth = TMManager.Level.getCellWidth();
		switch (_state) {
		case 0:
			_player.Attributes.Area.changePosition(0f, -15f * cellWidth);
			((AttachBehavior)_player1.getBehavior(TMBehaviorType.ATTACH)).setOffset(0f, -15f * cellWidth);
			((AttachBehavior)_player2.getBehavior(TMBehaviorType.ATTACH)).setOffset(0f, 15f * cellWidth);
			break;
		case 1:
			_player.Attributes.Area.changePosition(0f, -15f * cellWidth);
			((AttachBehavior)_player1.getBehavior(TMBehaviorType.ATTACH)).setOffset(0f, 30f * cellWidth);
			((AttachBehavior)_player2.getBehavior(TMBehaviorType.ATTACH)).setOffset(0f, 15f * cellWidth);
			break;
		case 2:
			_player.Attributes.Area.changePosition(0f, 30f * cellWidth);
			((AttachBehavior)_player1.getBehavior(TMBehaviorType.ATTACH)).setOffset(0f, -15f * cellWidth);
			((AttachBehavior)_player2.getBehavior(TMBehaviorType.ATTACH)).setOffset(0f, -30f * cellWidth);
			break;
		}
		
		_player.Attributes.Area.getCenter(_player.Attributes.Destination);
		
		_state++;
		if (_state > 2)
			_state = 0;
	}
}
