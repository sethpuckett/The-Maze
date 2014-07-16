package com.game.themaze.level.concrete;

import com.game.loblib.entity.GameEntity;
import com.game.loblib.utility.Global;
import com.game.the_maze.R;
import com.game.themaze.entity.EntityHelper;
import com.game.themaze.gameitem.GameItemType;
import com.game.themaze.graphics.TMImage;
import com.game.themaze.level.Level;
import com.game.themaze.utility.DoorOpenType;
import com.game.themaze.utility.DoorType;
import com.game.themaze.utility.TMManager;

// Obstacle course
public class Level06 extends Level {
	
	public Level06() {
		_settings.ResourceId = R.raw.level_12;
		_settings.CellWidth = (int)(Global.Renderer.Width / 12f);
		_settings.BackgroundImage = TMImage.BACKGROUND_METAL2;
		_settings.Item1 = GameItemType.RED_KEY;
		_settings.Item2 = GameItemType.BLUE_KEY;
		_settings.EnableActionButton = true;
	}
	
	@Override
	public void onInit() {
		
		int cellWidth = TMManager.Level.getCellWidth();
		
		addHallSpike(22, 40, 22, 43, 0);
		addHallSpike(27, 40, 27, 43, 250);
		addHallSpike(32, 40, 32, 43, 500);
		
		GameEntity anchor1 = EntityHelper.patrolAnchor(7, 41, (float)cellWidth / 4f, 1000);
		addAnchorDestination(anchor1, 7, 41);
		addAnchorDestination(anchor1, 7, 33);
		addAnchorDestination(anchor1, 15, 33);
		addAnchorDestination(anchor1, 15, 41);
		_entities.add(anchor1);
		_entities.add(EntityHelper.anchorSpikes(anchor1, TMImage.SPIKES_SINGLE, TMImage.SPIKES_SINGLE, TMImage.SPIKES_SINGLE, 2, true, 0, 0));
		
		GameEntity anchor2 = EntityHelper.patrolAnchor(15, 33, (float)cellWidth / 4f, 1000);
		addAnchorDestination(anchor2, 15, 33);
		addAnchorDestination(anchor2, 15, 41);
		addAnchorDestination(anchor2, 7, 41);
		addAnchorDestination(anchor2, 7, 33);
		_entities.add(anchor2);
		_entities.add(EntityHelper.anchorSpikes(anchor2, TMImage.SPIKES_SINGLE, TMImage.SPIKES_SINGLE, TMImage.SPIKES_SINGLE, 2, true, 0, 0));
		
		GameEntity anchor3 = EntityHelper.patrolAnchor(62, 34, (float)cellWidth / 8f);
		addAnchorDestination(anchor3, 62, 34);
		addAnchorDestination(anchor3, 68, 34);
		addAnchorDestination(anchor3, 68, 30);
		addAnchorDestination(anchor3, 62, 30);
		_entities.add(anchor3);
		_entities.add(EntityHelper.anchorSpikes(anchor3, TMImage.SPIKES_SINGLE, TMImage.SPIKES_SINGLE, TMImage.SPIKES_SINGLE, 2, true, 0, 0));
		
		GameEntity anchor4 = EntityHelper.patrolAnchor(43, 21, (float)cellWidth / 16f);
		addAnchorDestination(anchor4, 43, 21);
		addAnchorDestination(anchor4, 54, 21);
		_entities.add(anchor4);
		_entities.add(EntityHelper.anchorSpikes(anchor4, TMImage.SPIKES_U, TMImage.SPIKES_D, TMImage.SPIKES_UD, 8, false, 0, 0));
		
		GameEntity anchor5 = EntityHelper.patrolAnchor(48, 21, (float)cellWidth / 16f);
		addAnchorDestination(anchor5, 48, 21);
		addAnchorDestination(anchor5, 59, 21);
		_entities.add(anchor5);
		_entities.add(EntityHelper.anchorSpikes(anchor5, TMImage.SPIKES_U, TMImage.SPIKES_D, TMImage.SPIKES_UD, 8, false, 0, 0));
	
		_entities.add(EntityHelper.gameItem(12, 5, GameItemType.RED_KEY, true));
		_entities.add(EntityHelper.gameItem(66, 28, GameItemType.BLUE_KEY, true));
		
		int redDoor1 = makeDoor(39, 45, 39, 43, false, DoorType.RED, DoorOpenType.SINGLE, false);
		makeDoorTrigger(redDoor1, 37, 45, 2, 2);
		
		int redDoor2 = makeDoor(15, 32, 17, 32, true, DoorType.RED, DoorOpenType.SINGLE, false);
		makeDoorTrigger(redDoor2, 15, 33, 2, 2);
		makeDoorTrigger(redDoor2, 15, 30, 2, 2);
		
		int blueDoor = makeDoor(50, 18,50, 15, false, DoorType.BLUE, DoorOpenType.SINGLE, false);
		makeDoorTrigger(blueDoor, 48, 18, 2, 2);

		
	}
	
	protected void addHallSpike(int xGridStart, int yGridStart, int xGridEnd, int yGridEnd, int startOffset) {
		int cellWidth = TMManager.Level.getCellWidth();
		GameEntity anchor = EntityHelper.patrolAnchor(xGridStart, yGridStart, (float)cellWidth / 4f, 750, startOffset);
		addAnchorDestination(anchor, xGridStart, yGridStart);
		addAnchorDestination(anchor, xGridEnd, yGridEnd);
		_entities.add(anchor);
		_entities.add(EntityHelper.anchorSpikes(anchor, TMImage.SPIKES_U, TMImage.SPIKES_D, TMImage.SPIKES_UD, 4, false, 0, 0));
	}
}
