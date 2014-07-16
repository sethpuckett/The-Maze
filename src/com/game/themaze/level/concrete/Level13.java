package com.game.themaze.level.concrete;

import com.game.loblib.entity.GameEntity;
import com.game.loblib.utility.Global;
import com.game.loblib.utility.android.FixedSizeArray;
import com.game.the_maze.R;
import com.game.themaze.behavior.CircleMovementBehavior;
import com.game.themaze.entity.EntityHelper;
import com.game.themaze.gameitem.GameItemType;
import com.game.themaze.graphics.TMImage;
import com.game.themaze.graphics.TMSpriteLayer;
import com.game.themaze.level.Level;
import com.game.themaze.utility.DoorOpenType;
import com.game.themaze.utility.DoorType;
import com.game.themaze.utility.TMManager;

// Big spike pinwheel
public class Level13 extends Level {
	
	public Level13() {
		_settings.ResourceId = R.raw.level_14;
		_settings.CellWidth = (int)(Global.Renderer.Width / 16f);
		_settings.BackgroundImage = TMImage.BACKGROUND_ORNATE2;
		_settings.Item1 = GameItemType.RED_KEY;
		_settings.EnableActionButton = true;
	}
	
	@Override
	public void onInit() {
		float cellWidth = TMManager.Level.getCellWidth();
		
		FixedSizeArray<GameEntity> arr1 = new FixedSizeArray<GameEntity>(11);
		int capacity = arr1.getCapacity();
		for (int i = 0; i < capacity; i++) {
			if (i != 7 && i != 8) {
				GameEntity spike = EntityHelper.spikes(TMImage.SPIKES_SINGLE, 1, 1, 2, true);
				spike.addBehavior(new CircleMovementBehavior(xGridPosition(49), yGridPosition(23), 
						(cellWidth * 13f) * ((float)i / (float)capacity), 6000, true, (float)Math.PI / 2f));
				arr1.add(spike);
			}
		}
		_entities.addAll(arr1);
		
		FixedSizeArray<GameEntity> arr2 = new FixedSizeArray<GameEntity>(11);
		capacity = arr2.getCapacity();
		for (int i = 0; i < capacity; i++) {
			if (i != 5 && i != 6) {
				GameEntity spike = EntityHelper.spikes(TMImage.SPIKES_SINGLE, 1, 1, 2, true);
				spike.addBehavior(new CircleMovementBehavior(xGridPosition(49), yGridPosition(23), 
						(cellWidth * 13f) * ((float)i / (float)capacity), 6000, true, (float)Math.PI));
				arr2.add(spike);
			}
		}
		_entities.addAll(arr2);
		
		FixedSizeArray<GameEntity> arr3 = new FixedSizeArray<GameEntity>(11);
		capacity = arr3.getCapacity();
		for (int i = 0; i < capacity; i++) {
			if (!(i >= 2 && i <= 4)) {
				GameEntity spike = EntityHelper.spikes(TMImage.SPIKES_SINGLE, 1, 1, 2, true);
				spike.addBehavior(new CircleMovementBehavior(xGridPosition(49), yGridPosition(23), 
						(cellWidth * 13f) * ((float)i / (float)capacity), 6000, true, (float)Math.PI * 3f / 2f));
				arr3.add(spike);
			}
		}
		_entities.addAll(arr3);
		
		FixedSizeArray<GameEntity> arr4 = new FixedSizeArray<GameEntity>(11);
		capacity = arr4.getCapacity();
		for (int i = 0; i < capacity; i++) {
			if (i != 5 && i != 6) {
				GameEntity spike = EntityHelper.spikes(TMImage.SPIKES_SINGLE, 1, 1, 2, true);
				spike.addBehavior(new CircleMovementBehavior(xGridPosition(49), yGridPosition(23), 
						(cellWidth * 13f) * ((float)i / (float)capacity), 6000, true));
				arr4.add(spike);
			}
		}
		_entities.addAll(arr4);
		
		GameEntity key = EntityHelper.gameItem(1, 1, GameItemType.RED_KEY);
		key.addBehavior(new CircleMovementBehavior(xGridPosition(49), yGridPosition(23), 
				(cellWidth * 39f) / 11, 6000, true, (float)Math.PI * 3f / 2f));
		_entities.add(key);
		
		int redDoor = makeDoor(48, 37, 46, 37, true, DoorType.RED, DoorOpenType.SINGLE, false);
		makeDoorTrigger(redDoor, 48, 35, 2, 2);
		
		_entities.add(EntityHelper.levelGraphic(TMImage.TRIGGER_BUTTON_SMALL, TMSpriteLayer.BACKGROUND2, 34, 40, 2, 2));
		int door1 = makeDoor(37, 40, 39, 40, true);
		makeDoorTrigger(door1, 34, 40, 2, 2);
		
		_entities.add(EntityHelper.levelGraphic(TMImage.TRIGGER_BUTTON_SMALL, TMSpriteLayer.BACKGROUND2, 62, 40, 2, 2));
		int door2 = makeDoor(59, 40, 57, 40, true);
		makeDoorTrigger(door2, 62, 40, 2, 2);
	
	}
}
