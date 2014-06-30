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

// Introduction - spikes
public class Level03 extends Level {
	public Level03() {
		_settings.ResourceId = R.raw.level_03;
		_settings.CellWidth = (int)(Global.Renderer.Width / 16f);
		_settings.BackgroundImage = TMImage.BACKGROUND_ICE;
		_settings.Item1 = GameItemType.RED_KEY;
		_settings.EnableActionButton = true;
	}
	
	@Override
	public void init() {
		super.init();
		int cellWidth = TMManager.Level.getCellWidth();
		
		_entities.add(EntityHelper.gameItem(54, 52, GameItemType.RED_KEY));
		
		int redId = makeDoor(58, 58, 58, 56, false, DoorType.RED, DoorOpenType.SINGLE, false);
		makeDoorTrigger(redId, 59, 58, 2, 2);
		
		GameEntity anchor1 = EntityHelper.patrolAnchor(30, 55, (float)cellWidth / 12f);
		addAnchorDestination(anchor1, 30, 55);
		addAnchorDestination(anchor1, 30, 49);
		_entities.add(anchor1);
		_entities.add(EntityHelper.anchorSpikes(anchor1, TMImage.SPIKES_SINGLE, TMImage.SPIKES_SINGLE, TMImage.SPIKES_SINGLE, 2, true, 0, 0));
		
		GameEntity anchor2 = EntityHelper.patrolAnchor(26, 49, (float)cellWidth / 12f);
		addAnchorDestination(anchor2, 26, 49);
		addAnchorDestination(anchor2, 26, 55);
		_entities.add(anchor2);
		_entities.add(EntityHelper.anchorSpikes(anchor2, TMImage.SPIKES_SINGLE, TMImage.SPIKES_SINGLE, TMImage.SPIKES_SINGLE, 2, true, 0, 0));
		
		GameEntity anchor3 = EntityHelper.patrolAnchor(22, 55, (float)cellWidth / 12f);
		addAnchorDestination(anchor3, 22, 55);
		addAnchorDestination(anchor3, 22, 49);
		_entities.add(anchor3);
		_entities.add(EntityHelper.anchorSpikes(anchor3, TMImage.SPIKES_SINGLE, TMImage.SPIKES_SINGLE, TMImage.SPIKES_SINGLE, 2, true, 0, 0));
	}
}
