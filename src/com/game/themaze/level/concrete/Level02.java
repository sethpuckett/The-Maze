package com.game.themaze.level.concrete;


import com.game.loblib.entity.GameEntity;
import com.game.loblib.utility.Global;
import com.game.loblib.utility.Manager;
import com.game.loblib.utility.android.FixedSizeArray;
import com.game.the_maze.R;
import com.game.themaze.entity.EntityHelper;
import com.game.themaze.graphics.TMImage;
import com.game.themaze.graphics.TMSpriteLayer;
import com.game.themaze.level.Level;

// Introduction - action button
public class Level02 extends Level {
	
	protected FixedSizeArray<GameEntity> _triggers;
	protected FixedSizeArray<GameEntity> _triggerWalls;
	protected FixedSizeArray<GameEntity> _arrows;
	
	public Level02() {
		_settings.ResourceId = R.raw.level_02;
		_settings.CellWidth = (int)(Global.Renderer.Width / 12f);
		_settings.EnableActionButton = true;
		_settings.BackgroundImage = TMImage.BACKGROUND_TILE_REDWHITE;
	}
	
	@Override
	public void init() {
		super.init();
		
		_triggers = new FixedSizeArray<GameEntity>(20);
		_triggers.add(EntityHelper.trigger(19, 42, 2, 2));
		_triggers.add(EntityHelper.trigger(19, 36, 2, 2));
		_triggers.add(EntityHelper.trigger(16, 39, 2, 2));
		_triggers.add(EntityHelper.trigger(22, 39, 2, 2));
		
		_triggers.add(EntityHelper.trigger(19, 21, 2, 2));
		_triggers.add(EntityHelper.trigger(19, 15, 2, 2));
		_triggers.add(EntityHelper.trigger(16, 18, 2, 2));
		_triggers.add(EntityHelper.trigger(22, 18, 2, 2));
		
		_triggers.add(EntityHelper.trigger(28, 21, 2, 2));
		_triggers.add(EntityHelper.trigger(28, 15, 2, 2));
		_triggers.add(EntityHelper.trigger(25, 18, 2, 2));
		_triggers.add(EntityHelper.trigger(31, 18, 2, 2));
		
		_triggers.add(EntityHelper.trigger(43, 30, 2, 2));
		_triggers.add(EntityHelper.trigger(43, 24, 2, 2));
		_triggers.add(EntityHelper.trigger(40, 27, 2, 2));
		_triggers.add(EntityHelper.trigger(46, 27, 2, 2));
		
		_triggers.add(EntityHelper.trigger(43, 39, 2, 2));
		_triggers.add(EntityHelper.trigger(43, 33, 2, 2));
		_triggers.add(EntityHelper.trigger(40, 36, 2, 2));
		_triggers.add(EntityHelper.trigger(46, 36, 2, 2));
		
		_entities.addAll(_triggers);
		_actionTriggers.addAll(_triggers);
		
		_arrows = new FixedSizeArray<GameEntity>(20);
		_arrows.add(EntityHelper.levelGraphic(TMImage.ARROW_DOWN, TMSpriteLayer.BACKGROUND2, 19, 42, 2, 2));
		_arrows.add(EntityHelper.levelGraphic(TMImage.ARROW_UP, TMSpriteLayer.BACKGROUND2, 19, 36, 2, 2));
		_arrows.add(EntityHelper.levelGraphic(TMImage.ARROW_RIGHT, TMSpriteLayer.BACKGROUND2, 16, 39, 2, 2));
		_arrows.add(EntityHelper.levelGraphic(TMImage.ARROW_LEFT, TMSpriteLayer.BACKGROUND2, 22, 39, 2, 2));
		
		_arrows.add(EntityHelper.levelGraphic(TMImage.ARROW_DOWN, TMSpriteLayer.BACKGROUND2, 19, 21, 2, 2));
		_arrows.add(EntityHelper.levelGraphic(TMImage.ARROW_UP, TMSpriteLayer.BACKGROUND2, 19, 15, 2, 2));
		_arrows.add(EntityHelper.levelGraphic(TMImage.ARROW_RIGHT, TMSpriteLayer.BACKGROUND2, 16, 18, 2, 2));
		_arrows.add(EntityHelper.levelGraphic(TMImage.ARROW_LEFT, TMSpriteLayer.BACKGROUND2, 22, 18, 2, 2));
		
		_arrows.add(EntityHelper.levelGraphic(TMImage.ARROW_DOWN, TMSpriteLayer.BACKGROUND2, 28, 21, 2, 2));
		_arrows.add(EntityHelper.levelGraphic(TMImage.ARROW_UP, TMSpriteLayer.BACKGROUND2, 28, 15, 2, 2));
		_arrows.add(EntityHelper.levelGraphic(TMImage.ARROW_RIGHT, TMSpriteLayer.BACKGROUND2, 25, 18, 2, 2));
		_arrows.add(EntityHelper.levelGraphic(TMImage.ARROW_LEFT, TMSpriteLayer.BACKGROUND2, 31, 18, 2, 2));
		
		_arrows.add(EntityHelper.levelGraphic(TMImage.ARROW_DOWN, TMSpriteLayer.BACKGROUND2, 43, 30, 2, 2));
		_arrows.add(EntityHelper.levelGraphic(TMImage.ARROW_UP, TMSpriteLayer.BACKGROUND2, 43, 24, 2, 2));
		_arrows.add(EntityHelper.levelGraphic(TMImage.ARROW_RIGHT, TMSpriteLayer.BACKGROUND2, 40, 27, 2, 2));
		_arrows.add(EntityHelper.levelGraphic(TMImage.ARROW_LEFT, TMSpriteLayer.BACKGROUND2, 46, 27, 2, 2));
		
		_arrows.add(EntityHelper.levelGraphic(TMImage.ARROW_DOWN, TMSpriteLayer.BACKGROUND2, 43, 39, 2, 2));
		_arrows.add(EntityHelper.levelGraphic(TMImage.ARROW_UP, TMSpriteLayer.BACKGROUND2, 43, 33, 2, 2));
		_arrows.add(EntityHelper.levelGraphic(TMImage.ARROW_RIGHT, TMSpriteLayer.BACKGROUND2, 40, 36, 2, 2));
		_arrows.add(EntityHelper.levelGraphic(TMImage.ARROW_LEFT, TMSpriteLayer.BACKGROUND2, 46, 36, 2, 2));
		
		_entities.addAll(_arrows);
		
		_triggerWalls = new FixedSizeArray<GameEntity>(20);
		_triggerWalls.add(EntityHelper.wall(19, 41, 2, true));
		_triggerWalls.add(EntityHelper.wall(19, 38, 2, true));
		_triggerWalls.add(EntityHelper.wall(18, 39, 2, false));
		_triggerWalls.add(EntityHelper.wall(21, 39, 2, false));
		
		_triggerWalls.add(EntityHelper.wall(19, 20, 2, true));
		_triggerWalls.add(EntityHelper.wall(19, 17, 2, true));
		_triggerWalls.add(EntityHelper.wall(18, 18, 2, false));
		_triggerWalls.add(EntityHelper.wall(21, 18, 2, false));
		
		_triggerWalls.add(EntityHelper.wall(28, 20, 2, true));
		_triggerWalls.add(EntityHelper.wall(28, 17, 2, true));
		_triggerWalls.add(EntityHelper.wall(27,18, 2, false));
		_triggerWalls.add(EntityHelper.wall(30, 18, 2, false));
		
		_triggerWalls.add(EntityHelper.wall(43, 29, 2, true));
		_triggerWalls.add(EntityHelper.wall(43, 26, 2, true));
		_triggerWalls.add(EntityHelper.wall(42, 27, 2, false));
		_triggerWalls.add(EntityHelper.wall(45, 27, 2, false));
		
		_triggerWalls.add(EntityHelper.wall(43, 38, 2, true));
		_triggerWalls.add(EntityHelper.wall(43, 35, 2, true));
		_triggerWalls.add(EntityHelper.wall(42, 36, 2, false));
		_triggerWalls.add(EntityHelper.wall(45, 36, 2, false));
		
		_entities.addAll(_triggerWalls);
	}
	
	@Override
	public void enableBehaviors() {
		super.enableBehaviors();
		
		_triggers.get(2).disableBehaviors();
		_triggers.get(3).disableBehaviors();
		_arrows.get(2).disableBehaviors();
		_arrows.get(3).disableBehaviors();
		_triggerWalls.get(2).disableBehaviors();
		_triggerWalls.get(3).disableBehaviors();
		_triggers.get(6).disableBehaviors();
		_triggers.get(7).disableBehaviors();
		_arrows.get(6).disableBehaviors();
		_arrows.get(7).disableBehaviors();
		_triggerWalls.get(6).disableBehaviors();
		_triggerWalls.get(7).disableBehaviors();
		_triggers.get(10).disableBehaviors();
		_triggers.get(11).disableBehaviors();
		_arrows.get(10).disableBehaviors();
		_arrows.get(11).disableBehaviors();
		_triggerWalls.get(10).disableBehaviors();
		_triggerWalls.get(11).disableBehaviors();
		_triggers.get(12).disableBehaviors();
		_triggers.get(13).disableBehaviors();
		_arrows.get(12).disableBehaviors();
		_arrows.get(13).disableBehaviors();
		_triggerWalls.get(12).disableBehaviors();
		_triggerWalls.get(13).disableBehaviors();
		_triggers.get(16).disableBehaviors();
		_triggers.get(17).disableBehaviors();
		_arrows.get(16).disableBehaviors();
		_arrows.get(17).disableBehaviors();
		_triggerWalls.get(16).disableBehaviors();
		_triggerWalls.get(17).disableBehaviors();
	}
	
	@Override
	protected void onActionClicked() {
		if (Manager.Trigger.hit(_triggers.get(0)) ||
			Manager.Trigger.hit(_triggers.get(1))) {
			_triggerWalls.get(0).disableBehaviors();
			_triggerWalls.get(1).disableBehaviors();
			_triggerWalls.get(2).enableBehaviors();
			_triggerWalls.get(3).enableBehaviors();
			_triggers.get(0).disableBehaviors();
			_triggers.get(1).disableBehaviors();
			_triggers.get(2).enableBehaviors();
			_triggers.get(3).enableBehaviors();
			_arrows.get(0).disableBehaviors();
			_arrows.get(1).disableBehaviors();
			_arrows.get(2).enableBehaviors();
			_arrows.get(3).enableBehaviors();
		}
		else if (Manager.Trigger.hit(_triggers.get(2)) ||
				 Manager.Trigger.hit(_triggers.get(3))) {
			_triggerWalls.get(0).enableBehaviors();
			_triggerWalls.get(1).enableBehaviors();
			_triggerWalls.get(2).disableBehaviors();
			_triggerWalls.get(3).disableBehaviors();
			_triggers.get(0).enableBehaviors();
			_triggers.get(1).enableBehaviors();
			_triggers.get(2).disableBehaviors();
			_triggers.get(3).disableBehaviors();
			_arrows.get(0).enableBehaviors();
			_arrows.get(1).enableBehaviors();
			_arrows.get(2).disableBehaviors();
			_arrows.get(3).disableBehaviors();
		}
		else if (Manager.Trigger.hit(_triggers.get(4)) ||
				 Manager.Trigger.hit(_triggers.get(5))) {
			_triggerWalls.get(4).disableBehaviors();
			_triggerWalls.get(5).disableBehaviors();
			_triggerWalls.get(6).enableBehaviors();
			_triggerWalls.get(7).enableBehaviors();
			_triggers.get(4).disableBehaviors();
			_triggers.get(5).disableBehaviors();
			_triggers.get(6).enableBehaviors();
			_triggers.get(7).enableBehaviors();
			_arrows.get(4).disableBehaviors();
			_arrows.get(5).disableBehaviors();
			_arrows.get(6).enableBehaviors();
			_arrows.get(7).enableBehaviors();
		}
		else if (Manager.Trigger.hit(_triggers.get(6)) ||
				 Manager.Trigger.hit(_triggers.get(7))) {
			_triggerWalls.get(4).enableBehaviors();
			_triggerWalls.get(5).enableBehaviors();
			_triggerWalls.get(6).disableBehaviors();
			_triggerWalls.get(7).disableBehaviors();
			_triggers.get(4).enableBehaviors();
			_triggers.get(5).enableBehaviors();
			_triggers.get(6).disableBehaviors();
			_triggers.get(7).disableBehaviors();
			_arrows.get(4).enableBehaviors();
			_arrows.get(5).enableBehaviors();
			_arrows.get(6).disableBehaviors();
			_arrows.get(7).disableBehaviors();
		}
		else if (Manager.Trigger.hit(_triggers.get(8)) ||
				 Manager.Trigger.hit(_triggers.get(9))) {
			_triggerWalls.get(8).disableBehaviors();
			_triggerWalls.get(9).disableBehaviors();
			_triggerWalls.get(10).enableBehaviors();
			_triggerWalls.get(11).enableBehaviors();
			_triggers.get(8).disableBehaviors();
			_triggers.get(9).disableBehaviors();
			_triggers.get(10).enableBehaviors();
			_triggers.get(11).enableBehaviors();
			_arrows.get(8).disableBehaviors();
			_arrows.get(9).disableBehaviors();
			_arrows.get(10).enableBehaviors();
			_arrows.get(11).enableBehaviors();
		}
		else if (Manager.Trigger.hit(_triggers.get(10)) ||
				 Manager.Trigger.hit(_triggers.get(11))) {
			_triggerWalls.get(8).enableBehaviors();
			_triggerWalls.get(9).enableBehaviors();
			_triggerWalls.get(10).disableBehaviors();
			_triggerWalls.get(11).disableBehaviors();
			_triggers.get(8).enableBehaviors();
			_triggers.get(9).enableBehaviors();
			_triggers.get(10).disableBehaviors();
			_triggers.get(11).disableBehaviors();
			_arrows.get(8).enableBehaviors();
			_arrows.get(9).enableBehaviors();
			_arrows.get(10).disableBehaviors();
			_arrows.get(11).disableBehaviors();
		}
		else if (Manager.Trigger.hit(_triggers.get(12)) ||
				 Manager.Trigger.hit(_triggers.get(13))) {
			_triggerWalls.get(12).disableBehaviors();
			_triggerWalls.get(13).disableBehaviors();
			_triggerWalls.get(14).enableBehaviors();
			_triggerWalls.get(15).enableBehaviors();
			_triggers.get(12).disableBehaviors();
			_triggers.get(13).disableBehaviors();
			_triggers.get(14).enableBehaviors();
			_triggers.get(15).enableBehaviors();
			_arrows.get(12).disableBehaviors();
			_arrows.get(13).disableBehaviors();
			_arrows.get(14).enableBehaviors();
			_arrows.get(15).enableBehaviors();
		}
		else if (Manager.Trigger.hit(_triggers.get(14)) ||
				 Manager.Trigger.hit(_triggers.get(15))) {
			_triggerWalls.get(12).enableBehaviors();
			_triggerWalls.get(13).enableBehaviors();
			_triggerWalls.get(14).disableBehaviors();
			_triggerWalls.get(15).disableBehaviors();
			_triggers.get(12).enableBehaviors();
			_triggers.get(13).enableBehaviors();
			_triggers.get(14).disableBehaviors();
			_triggers.get(15).disableBehaviors();
			_arrows.get(12).enableBehaviors();
			_arrows.get(13).enableBehaviors();
			_arrows.get(14).disableBehaviors();
			_arrows.get(15).disableBehaviors();
		}
		else if (Manager.Trigger.hit(_triggers.get(16)) ||
				 Manager.Trigger.hit(_triggers.get(17))) {
			_triggerWalls.get(16).disableBehaviors();
			_triggerWalls.get(17).disableBehaviors();
			_triggerWalls.get(18).enableBehaviors();
			_triggerWalls.get(19).enableBehaviors();
			_triggers.get(16).disableBehaviors();
			_triggers.get(17).disableBehaviors();
			_triggers.get(18).enableBehaviors();
			_triggers.get(19).enableBehaviors();
			_arrows.get(16).disableBehaviors();
			_arrows.get(17).disableBehaviors();
			_arrows.get(18).enableBehaviors();
			_arrows.get(19).enableBehaviors();
		}
		else if (Manager.Trigger.hit(_triggers.get(18)) ||
				 Manager.Trigger.hit(_triggers.get(19))) {
			_triggerWalls.get(16).enableBehaviors();
			_triggerWalls.get(17).enableBehaviors();
			_triggerWalls.get(18).disableBehaviors();
			_triggerWalls.get(19).disableBehaviors();
			_triggers.get(16).enableBehaviors();
			_triggers.get(17).enableBehaviors();
			_triggers.get(18).disableBehaviors();
			_triggers.get(19).disableBehaviors();
			_arrows.get(16).enableBehaviors();
			_arrows.get(17).enableBehaviors();
			_arrows.get(18).disableBehaviors();
			_arrows.get(19).disableBehaviors();
		}
	}
}
