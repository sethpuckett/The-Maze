package com.game.themaze.level.concrete;

import com.game.loblib.entity.GameEntity;
import com.game.loblib.utility.Global;
import com.game.loblib.utility.Manager;
import com.game.loblib.utility.android.FixedSizeArray;
import com.game.loblib.utility.area.Vertex;
import com.game.the_maze.R;
import com.game.themaze.behavior.CircleMovementBehavior;
import com.game.themaze.behavior.ReverseTouchDestinationBehavior;
import com.game.themaze.behavior.TMBehaviorType;
import com.game.themaze.entity.EntityHelper;
import com.game.themaze.graphics.TMImage;
import com.game.themaze.graphics.TMSpriteLayer;
import com.game.themaze.level.Level;
import com.game.themaze.utility.TMManager;

// Backwards movement
public class Level11 extends Level {
	
	protected Vertex _playerCenter;
	protected FixedSizeArray<GameEntity> _triggers;
	protected FixedSizeArray<GameEntity> _triggerWalls;
	protected FixedSizeArray<GameEntity> _arrows;
	
	public Level11() {
		_settings.ResourceId = R.raw.level_19;
		_settings.CellWidth = (int)(Global.Renderer.Width / 12f);
		_settings.BackgroundImage = TMImage.BACKGROUND_COPPER;
		_settings.EnableActionButton = true;
		_settings.DisableCamera = true;
	}
	
	@Override
	public void init() {
		super.init();
		int cellWidth = TMManager.Level.getCellWidth();

		_player.removeBehaviors(TMBehaviorType.TOUCH_DESTINATION);
		_player.addBehavior(new ReverseTouchDestinationBehavior(_player.Attributes.Area.Position));
		
		_entities.add(spikeRing(44, 32));
		_entities.add(spikeRing(14, 39));
		_entities.add(spikeRing(56, 44));
		_entities.add(spikeRing(41, 53));
		_entities.add(spikeRing(26, 62));
		
		GameEntity anchor = EntityHelper.patrolAnchor(45, 91, (float)cellWidth / 12f, 0);
		addAnchorDestination(anchor, 39, 91);
		addAnchorDestination(anchor, 51, 91);
		_entities.add(anchor);
		_entities.add(EntityHelper.anchorSpikes(anchor, TMImage.SPIKES_SINGLE, TMImage.SPIKES_SINGLE, TMImage.SPIKES_SINGLE, 2, true, 0, 0));
		
		GameEntity anchor2 = EntityHelper.patrolAnchor(39, 85, (float)cellWidth / 6f, 0);
		addAnchorDestination(anchor2, 39, 85);
		addAnchorDestination(anchor2, 51, 85);
		_entities.add(anchor2);
		_entities.add(EntityHelper.anchorSpikes(anchor2, TMImage.SPIKES_SINGLE, TMImage.SPIKES_SINGLE, TMImage.SPIKES_SINGLE, 2, true, 0, 0));
		
		GameEntity anchor3 = EntityHelper.patrolAnchor(45, 79, (float)cellWidth / 12f, 0);
		addAnchorDestination(anchor3, 51, 79);
		addAnchorDestination(anchor3, 39, 79);
		_entities.add(anchor3);
		_entities.add(EntityHelper.anchorSpikes(anchor3, TMImage.SPIKES_SINGLE, TMImage.SPIKES_SINGLE, TMImage.SPIKES_SINGLE, 2, true, 0, 0));
		
		GameEntity anchor4 = EntityHelper.patrolAnchor(51, 85, (float)cellWidth / 12f, 0);
		addAnchorDestination(anchor4, 51, 91);
		addAnchorDestination(anchor4, 51, 79);
		_entities.add(anchor4);
		_entities.add(EntityHelper.anchorSpikes(anchor4, TMImage.SPIKES_SINGLE, TMImage.SPIKES_SINGLE, TMImage.SPIKES_SINGLE, 2, true, 0, 0));
		
		
		_triggers = new FixedSizeArray<GameEntity>(8);
		_triggers.add(EntityHelper.trigger(31, 46, 2, 2));
		_triggers.add(EntityHelper.trigger(31, 40, 2, 2));
		_triggers.add(EntityHelper.trigger(28, 43, 2, 2));
		_triggers.add(EntityHelper.trigger(34, 43, 2, 2));
		_triggers.add(EntityHelper.trigger(52, 64, 2, 2));
		_triggers.add(EntityHelper.trigger(52, 58, 2, 2));
		_triggers.add(EntityHelper.trigger(49, 61, 2, 2));
		_triggers.add(EntityHelper.trigger(55, 61, 2, 2));
		_actionTriggers.addAll(_triggers);
		_entities.addAll(_triggers);
		
		_arrows = new FixedSizeArray<GameEntity>(8);
		_arrows.add(EntityHelper.levelGraphic(TMImage.ARROW_DOWN, TMSpriteLayer.BACKGROUND2, 31, 46, 2, 2));
		_arrows.add(EntityHelper.levelGraphic(TMImage.ARROW_UP, TMSpriteLayer.BACKGROUND2, 31, 40, 2, 2));
		_arrows.add(EntityHelper.levelGraphic(TMImage.ARROW_RIGHT, TMSpriteLayer.BACKGROUND2, 28, 43, 2, 2));
		_arrows.add(EntityHelper.levelGraphic(TMImage.ARROW_LEFT, TMSpriteLayer.BACKGROUND2, 34, 43, 2, 2));
		_arrows.add(EntityHelper.levelGraphic(TMImage.ARROW_DOWN, TMSpriteLayer.BACKGROUND2, 52, 64, 2, 2));
		_arrows.add(EntityHelper.levelGraphic(TMImage.ARROW_UP, TMSpriteLayer.BACKGROUND2, 52, 58, 2, 2));
		_arrows.add(EntityHelper.levelGraphic(TMImage.ARROW_RIGHT, TMSpriteLayer.BACKGROUND2, 49, 61, 2, 2));
		_arrows.add(EntityHelper.levelGraphic(TMImage.ARROW_LEFT, TMSpriteLayer.BACKGROUND2, 55, 61, 2, 2));
		_entities.addAll(_arrows);
		
		
		_triggerWalls = new FixedSizeArray<GameEntity>(8);
		_triggerWalls.add(EntityHelper.wall(31, 45, 2, true));
		_triggerWalls.add(EntityHelper.wall(31, 42, 2, true));
		_triggerWalls.add(EntityHelper.wall(30, 43, 2, false));
		_triggerWalls.add(EntityHelper.wall(33, 43, 2, false));
		_triggerWalls.add(EntityHelper.wall(52, 63, 2, true));
		_triggerWalls.add(EntityHelper.wall(52, 60, 2, true));
		_triggerWalls.add(EntityHelper.wall(51, 61, 2, false));
		_triggerWalls.add(EntityHelper.wall(54, 61, 2, false));
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
		
		_triggers.get(4).disableBehaviors();
		_triggers.get(5).disableBehaviors();
		_arrows.get(4).disableBehaviors();
		_arrows.get(5).disableBehaviors();
		_triggerWalls.get(4).disableBehaviors();
		_triggerWalls.get(5).disableBehaviors();
	}
	
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
	}
	
	protected GameEntity spikeRing(int xGrid, int yGrid) {
		float cellWidth = TMManager.Level.getCellWidth();
		GameEntity spike = EntityHelper.spikes(TMImage.SPIKES_SINGLE, 1, 1, 2, true);
		spike.addBehavior(new CircleMovementBehavior(xGridPosition(xGrid), yGridPosition(yGrid), 
					cellWidth * 3f, 1250, false));
		return spike;
	}
}
