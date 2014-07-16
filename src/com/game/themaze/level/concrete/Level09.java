package com.game.themaze.level.concrete;

import com.game.loblib.entity.GameEntity;
import com.game.loblib.utility.Direction;
import com.game.loblib.utility.Global;
import com.game.loblib.utility.Logger;
import com.game.loblib.utility.android.FixedSizeArray;
import com.game.the_maze.R;
import com.game.themaze.behavior.CircleMovementBehavior;
import com.game.themaze.entity.EntityHelper;
import com.game.themaze.graphics.TMImage;
import com.game.themaze.level.Level;
import com.game.themaze.utility.TMManager;

// Spike Pac-man
public class Level09 extends Level {
	
	public Level09() {
		_settings.ResourceId = R.raw.level_13;
		_settings.CellWidth = (int)(Global.Renderer.Width / 16f);
		_settings.BackgroundImage = TMImage.BACKGROUND_ORNATE;
	}
	
	@Override
	public void onInit() {

		_entities.addAll(spikeRing(18, 75, false, Direction.RIGHT));
		//_entities.addAll(stationaryRing(16, 73, Direction.UP, Direction.DOWN));
		_entities.addAll(spikeWall(18, 75, false, Direction.RIGHT, true, 5, 7));
		_entities.addAll(spikeWall(18, 75, false, Direction.RIGHT, false, 5, 7));
		
		//_entities.addAll(spikeRing(33, 75, false, Direction.RIGHT));
		_entities.addAll(stationaryRing(32, 74, Direction.DOWN, Direction.RIGHT));
		_entities.addAll(spikeWall(33, 75, false, Direction.RIGHT, true));
		_entities.addAll(spikeWall(33, 75, false, Direction.RIGHT, false));
		
		//_entities.addAll(spikeRing(48, 75, true, Direction.LEFT));
		_entities.addAll(stationaryRing(47, 74, Direction.LEFT, Direction.UP));
		_entities.addAll(spikeWall(48, 75, true, Direction.LEFT, true));
		_entities.addAll(spikeWall(48, 75, true, Direction.LEFT, false));
		
		//_entities.addAll(spikeRing(18, 60, false, Direction.LEFT));
		_entities.addAll(stationaryRing(17, 59, Direction.UP, Direction.DOWN));
		_entities.addAll(spikeWall(18, 60, false, Direction.LEFT, true));
		_entities.addAll(spikeWall(18, 60, false, Direction.LEFT, false));
		
		//_entities.addAll(spikeRing(33, 60, false, Direction.LEFT));
		_entities.addAll(stationaryRing(32, 59, Direction.RIGHT, Direction.UP));
		_entities.addAll(spikeWall(33, 60, false, Direction.LEFT, true));
		_entities.addAll(spikeWall(33, 60, false, Direction.LEFT, false));
		
		//_entities.addAll(spikeRing(48, 60, false, Direction.RIGHT));
		_entities.addAll(stationaryRing(47, 59, Direction.DOWN, Direction.LEFT));
		_entities.addAll(spikeWall(48, 60, false, Direction.RIGHT, true));
		_entities.addAll(spikeWall(48, 60, false, Direction.RIGHT, false));
		
		//_entities.addAll(spikeRing(18, 45, true, Direction.LEFT));
		_entities.addAll(stationaryRing(17, 44, Direction.UP, Direction.RIGHT));
		_entities.addAll(spikeWall(18, 45, true, Direction.LEFT, true));
		_entities.addAll(spikeWall(18, 45, true, Direction.LEFT, false));
		
		//_entities.addAll(spikeRing(33, 45, true, Direction.RIGHT));
		_entities.addAll(stationaryRing(32, 44, Direction.LEFT, Direction.RIGHT));
		_entities.addAll(spikeWall(33, 45, true, Direction.RIGHT, true));
		_entities.addAll(spikeWall(33, 45, true, Direction.RIGHT, false));
		
		//_entities.addAll(spikeRing(48, 45, false, Direction.LEFT));
		_entities.addAll(stationaryRing(47, 44, Direction.LEFT, Direction.UP));
		_entities.addAll(spikeWall(48, 45, false, Direction.LEFT, true));
		_entities.addAll(spikeWall(48, 45, false, Direction.LEFT, false));
	}
	
	protected FixedSizeArray<GameEntity> stationaryRing(int xGrid, int yGrid, int openDirection1, int openDirection2) {
		float cellWidth = TMManager.Level.getCellWidth();
		FixedSizeArray<GameEntity> arr = new FixedSizeArray<GameEntity>(28);
		float capacity = arr.getCapacity();
		for (float i = 0; i < capacity; i++) {
			if (!((i / capacity < .05 || i / capacity > .95) && (openDirection1 == Direction.RIGHT || openDirection2 == Direction.RIGHT) ||
					(i / capacity > .20 && i / capacity < .30) && (openDirection1 == Direction.UP || openDirection2 == Direction.UP) ||
					(i / capacity > .45 && i / capacity < .55) && (openDirection1 == Direction.LEFT || openDirection2 == Direction.LEFT) ||
					(i / capacity > .70 && i / capacity < .80) && (openDirection1 == Direction.DOWN || openDirection2 == Direction.DOWN))) {
				GameEntity spike = EntityHelper.spikes(TMImage.SPIKES_SINGLE, 1, 1, 2, true);
				spike.Attributes.Area.setCirclePosition(xGridPosition(xGrid), yGridPosition(yGrid), cellWidth * 7f, (float)((Math.PI * 2f) * ((float)i / (float)capacity)));
				arr.add(spike);
			}
		}
		return arr;
	}
	
	protected FixedSizeArray<GameEntity> spikeRing(int xGrid, int yGrid, boolean clockwise, int initDirection) {
		float initOffset = 0f;
		switch (initDirection) {
		case Direction.RIGHT:
			initOffset = (float)Math.PI / 4f;
			break;
		case Direction.UP:
			initOffset = (float)Math.PI * 3f / 4f;
			break;
		case Direction.LEFT:
			initOffset = (float)Math.PI * 5f / 4f;
			break;
		case Direction.DOWN:
			initOffset = (float)Math.PI * 7f / 4f;
			break;
		default:
			Logger.e(_tag, "Cannot set init direction; invalid direction");
		}
		
		float cellWidth = TMManager.Level.getCellWidth();
		FixedSizeArray<GameEntity> arr = new FixedSizeArray<GameEntity>(18);
		int capacity = arr.getCapacity();
		for (int i = 0; i < capacity; i++) {
			GameEntity spike = EntityHelper.spikes(TMImage.SPIKES_SINGLE, 1, 1, 2, true);
			spike.addBehavior(new CircleMovementBehavior(xGridPosition(xGrid), yGridPosition(yGrid), 
					cellWidth * 7f, 7000, clockwise, initOffset + (float)((Math.PI * 2f) * ((float)i / ((float)capacity + 4f)))));
			arr.add(spike);
		}
		return arr;
	}
	
	protected FixedSizeArray<GameEntity> spikeWall(int xGrid, int yGrid, boolean clockwise, int initDirection, boolean wall1) {
		return spikeWall(xGrid, yGrid,clockwise,initDirection, wall1, 6, 8);
	}
	
	protected FixedSizeArray<GameEntity> spikeWall(int xGrid, int yGrid, boolean clockwise, int initDirection, boolean wall1, int spikeCount, int wallLength) {
		float initOffset = 0f;
		switch (initDirection) {
		case Direction.RIGHT:
			initOffset = (float)Math.PI / 4f;
			break;
		case Direction.UP:
			initOffset = (float)Math.PI * 3f / 4f;
			break;
		case Direction.LEFT:
			initOffset = (float)Math.PI * 5f / 4f;
			break;
		case Direction.DOWN:
			initOffset = (float)Math.PI * 7f / 4f;
			break;
		default:
			Logger.e(_tag, "Cannot set init direction; invalid direction");
		}
		if (!wall1)
			initOffset += (float)((Math.PI * 2f) * (17f / 22f));
		
		
		float cellWidth = TMManager.Level.getCellWidth();
		FixedSizeArray<GameEntity> arr = new FixedSizeArray<GameEntity>(spikeCount);
		int capacity = arr.getCapacity();
		for (int i = 0; i < capacity; i++) {
			GameEntity spike = EntityHelper.spikes(TMImage.SPIKES_SINGLE, 10, 90, 2, true);
			spike.addBehavior(new CircleMovementBehavior(xGridPosition(xGrid), yGridPosition(yGrid), 
					(cellWidth * (float)wallLength) * ((float)i / (float)capacity), 7000, clockwise, initOffset));
			arr.add(spike);
		}
		return arr;
	}
}
