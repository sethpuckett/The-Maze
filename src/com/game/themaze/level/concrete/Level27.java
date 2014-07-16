package com.game.themaze.level.concrete;

import com.game.loblib.entity.GameEntity;
import com.game.loblib.utility.Global;
import com.game.loblib.utility.android.FixedSizeArray;
import com.game.the_maze.R;
import com.game.themaze.behavior.CircleMovementBehavior;
import com.game.themaze.behavior.PatrolDestinationBehavior.PatrolType;
import com.game.themaze.entity.EntityHelper;
import com.game.themaze.graphics.TMImage;
import com.game.themaze.level.Level;
import com.game.themaze.utility.TMManager;

// Inside big spike ring
public class Level27 extends Level {
	public Level27() {
		_settings.ResourceId = R.raw.level_17;
		_settings.CellWidth = (int)(Global.Renderer.Width / 32f);
		_settings.BackgroundImage = TMImage.BACKGROUND_PAPER;
		_settings.BackgroundTileCount = 8;
	}
	
	@Override
	public void onInit() {
		float cellWidth = TMManager.Level.getCellWidth();
		
		GameEntity wheelAnchor = EntityHelper.patrolAnchor(10, 84, cellWidth / 35f, 0, 0, PatrolType.ONCE_THROUGH);
		addAnchorDestination(wheelAnchor, 10, 84);
		addAnchorDestination(wheelAnchor, 32, 81);
		addAnchorDestination(wheelAnchor, 49, 90);
		addAnchorDestination(wheelAnchor, 62, 69);
		addAnchorDestination(wheelAnchor, 46, 52);
		addAnchorDestination(wheelAnchor, 73, 61);
		addAnchorDestination(wheelAnchor, 80, 55);
		addAnchorDestination(wheelAnchor, 71, 27);
		addAnchorDestination(wheelAnchor, 62, 22);
		addAnchorDestination(wheelAnchor, 32, 28);
		addAnchorDestination(wheelAnchor, 0, 0);
		
		_entities.add(wheelAnchor);
		FixedSizeArray<GameEntity> wheelSpikes = new FixedSizeArray<GameEntity>(35);
		int capacity = wheelSpikes.getCapacity();
		for (int i = 0; i < capacity; i++) {
			GameEntity spike = EntityHelper.spikes(TMImage.SPIKES_SINGLE, 1, 1, 2, true);
			spike.addBehavior(new CircleMovementBehavior(wheelAnchor.Attributes.Area.Position, 
					cellWidth * 14f, 6000, false, (float)Math.PI * 2f * i / (float)capacity));
			wheelSpikes.add(spike);
		}
		_entities.addAll(wheelSpikes);
	}
}
