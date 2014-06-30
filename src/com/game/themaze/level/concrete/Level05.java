package com.game.themaze.level.concrete;

import com.game.loblib.entity.GameEntity;
import com.game.loblib.messaging.Message;
import com.game.loblib.utility.Global;
import com.game.loblib.utility.Manager;
import com.game.the_maze.R;
import com.game.themaze.behavior.PatrolDestinationBehavior.PatrolType;
import com.game.themaze.behavior.TMBehaviorType;
import com.game.themaze.entity.EntityHelper;
import com.game.themaze.gameitem.GameItemType;
import com.game.themaze.graphics.TMImage;
import com.game.themaze.level.Level;
import com.game.themaze.messaging.TMMessageType;
import com.game.themaze.utility.DoorOpenType;
import com.game.themaze.utility.DoorType;
import com.game.themaze.utility.TMManager;

// Obstacle course
public class Level05 extends Level {
	
	protected GameEntity _spikeWallAnchor;

	public Level05() {
		_settings.ResourceId = R.raw.level_11;
		_settings.CellWidth = (int)(Global.Renderer.Width / 12f);
		_settings.BackgroundImage = TMImage.BACKGROUND_METAL;
		_settings.Item1 = GameItemType.RED_KEY;
		_settings.EnableActionButton = true;
	}
	
	@Override
	public void init() {
		super.init();
		int cellWidth = TMManager.Level.getCellWidth();
		
		float slowSpeed = (float)cellWidth / 15f;
		float medSpeed = (float)cellWidth / 7f;
		float fastSpeed = (float)cellWidth / 4f;
		
		addMovingSpike(49, 89, 55, 89, slowSpeed, 0);
		addMovingSpike(63, 89, 57, 89, slowSpeed, 0);
		addMovingSpike(65, 89, 71, 89, slowSpeed, 0);
		addMovingSpike(79, 89, 73, 89, slowSpeed, 0);
		addMovingSpike(55, 91, 49, 91, slowSpeed, 0);
		addMovingSpike(57, 91, 63, 91, slowSpeed, 0);
		addMovingSpike(71, 91, 65, 91, slowSpeed, 0);
		addMovingSpike(73, 91, 79, 91, slowSpeed, 0);
		
		addMovingSpike(82, 73, 82, 77, fastSpeed, 900);
		addMovingSpike(78, 77, 78, 73, fastSpeed, 900);
		addMovingSpike(74, 73, 74, 77, fastSpeed, 900);
		addMovingSpike(70, 77, 70, 73, fastSpeed, 900);
		
		GameEntity anchor = EntityHelper.patrolAnchor(55, 67, medSpeed);
		addAnchorDestination(anchor, 55, 67);
		addAnchorDestination(anchor, 49, 67);
		addAnchorDestination(anchor, 49, 81);
		addAnchorDestination(anchor, 55, 81);
		addAnchorDestination(anchor, 55, 79);
		addAnchorDestination(anchor, 49, 79);
		addAnchorDestination(anchor, 49, 77);
		addAnchorDestination(anchor, 55, 77);
		addAnchorDestination(anchor, 55, 75);
		addAnchorDestination(anchor, 49, 75);
		addAnchorDestination(anchor, 49, 73);
		addAnchorDestination(anchor, 55, 73);
		addAnchorDestination(anchor, 55, 71);
		addAnchorDestination(anchor, 49, 71);
		addAnchorDestination(anchor, 49, 69);
		addAnchorDestination(anchor, 55, 69);
		_entities.add(anchor);
		_entities.add(EntityHelper.anchorSpikes(anchor, TMImage.SPIKES_SINGLE, TMImage.SPIKES_SINGLE, TMImage.SPIKES_SINGLE, 2, true, 0, 0));
		
		GameEntity anchor2 = EntityHelper.patrolAnchor(49, 67, medSpeed);
		addAnchorDestination(anchor2, 49, 67);
		addAnchorDestination(anchor2, 55, 67);
		addAnchorDestination(anchor2, 55, 81);
		addAnchorDestination(anchor2, 49, 81);
		addAnchorDestination(anchor2, 49, 79);
		addAnchorDestination(anchor2, 55, 79);
		addAnchorDestination(anchor2, 55, 77);
		addAnchorDestination(anchor2, 49, 77);
		addAnchorDestination(anchor2, 49, 75);
		addAnchorDestination(anchor2, 55, 75);
		addAnchorDestination(anchor2, 55, 73);
		addAnchorDestination(anchor2, 49, 73);
		addAnchorDestination(anchor2, 49, 71);
		addAnchorDestination(anchor2, 55, 71);
		addAnchorDestination(anchor2, 55, 69);
		addAnchorDestination(anchor2, 49, 69);

		_entities.add(anchor2);
		_entities.add(EntityHelper.anchorSpikes(anchor2, TMImage.SPIKES_SINGLE, TMImage.SPIKES_SINGLE, TMImage.SPIKES_SINGLE, 2, true, 0, 0));
		
		_spikeWallAnchor = EntityHelper.patrolAnchor(49, 58, slowSpeed, 0, 0, PatrolType.ONCE_THROUGH);
		addAnchorDestination(_spikeWallAnchor, 49, 58);
		addAnchorDestination(_spikeWallAnchor, 49, 81);
		_entities.add(_spikeWallAnchor);
		_entities.add(EntityHelper.anchorSpikes(_spikeWallAnchor, TMImage.SPIKES_R, TMImage.SPIKES_L, TMImage.SPIKES_LR, 8, true, 0, 0));
		
		_entities.add(EntityHelper.gameItem(53, 64, GameItemType.RED_KEY));
		
		int doorId = makeDoor(88, 87, 90, 87, true, DoorType.RED, DoorOpenType.SINGLE, false);
		makeDoorTrigger(doorId, 88, 88, 2, 2);
		
		Manager.Message.subscribe(this, TMMessageType.DESTINATION_REACHED);
	}
	
	@Override
	public void unpause() {
		super.unpause();
		Manager.Message.subscribe(this, TMMessageType.DESTINATION_REACHED);
	}
	
	@Override
	public void enableBehaviors() {
		super.enableBehaviors();
		
		_spikeWallAnchor.disableBehaviors(TMBehaviorType.DESTINATION_MOVE);
	}
	
	@Override
	public void handleMessage(Message message) {
		super.handleMessage(message);
		
		if (message.Type == TMMessageType.ITEM_HIT) {
			_spikeWallAnchor.enableBehaviors();
		}
	}
	
	protected void addMovingSpike(int xGrid1, int yGrid1, int xGrid2, int yGrid2, float speed, int holdTime) {
		GameEntity anchor = EntityHelper.patrolAnchor(xGrid1, yGrid1, speed, holdTime);
		addAnchorDestination(anchor, xGrid1, yGrid1);
		addAnchorDestination(anchor, xGrid2, yGrid2);
		_entities.add(anchor);
		_entities.add(EntityHelper.anchorSpikes(anchor, TMImage.SPIKES_SINGLE, TMImage.SPIKES_SINGLE, TMImage.SPIKES_SINGLE, 2, true, 0, 0));
	}
}
