package com.game.themaze.level.concrete;

import com.game.loblib.entity.GameEntity;
import com.game.loblib.utility.Global;
import com.game.loblib.utility.android.FixedSizeArray;
import com.game.the_maze.R;
import com.game.themaze.behavior.CircleMovementBehavior;
import com.game.themaze.entity.EntityHelper;
import com.game.themaze.graphics.TMImage;
import com.game.themaze.level.Level;
import com.game.themaze.utility.TMManager;

// Multiple bouncing spike rings
public class Level08 extends Level {
	
	public Level08() {
		_settings.ResourceId = R.raw.level_15;
		_settings.CellWidth = (int)(Global.Renderer.Width / 32f);
		_settings.BackgroundImage = TMImage.BACKGROUND_PAPER;
		_settings.BackgroundTileCount = 8;
	}
	
	@Override
	public void init() {
		super.init();
		float cellWidth = TMManager.Level.getCellWidth();
		
		GameEntity wheelAnchor1 = EntityHelper.patrolAnchor(1, 49, cellWidth / 11f);
		addAnchorDestination(wheelAnchor1, 1, 49);
		addAnchorDestination(wheelAnchor1, 49, 98);
		addAnchorDestination(wheelAnchor1, 98, 49);
		addAnchorDestination(wheelAnchor1, 49, 1);
		_entities.add(wheelAnchor1);
		FixedSizeArray<GameEntity> wheelSpikes1 = new FixedSizeArray<GameEntity>(12);
		int capacity1 = wheelSpikes1.getCapacity();
		for (int i = 0; i < capacity1; i++) {
			GameEntity spike = EntityHelper.spikes(TMImage.SPIKES_SINGLE, 1, 1, 2, true);
			spike.addBehavior(new CircleMovementBehavior(wheelAnchor1.Attributes.Area.Position, 
					cellWidth * 5f, 4000, true, (float)Math.PI * 2f * i / (float)capacity1));
			wheelSpikes1.add(spike);
		}
		_entities.addAll(wheelSpikes1);
		
		GameEntity wheelAnchor2 = EntityHelper.patrolAnchor(1, 24, cellWidth / 11f);
		addAnchorDestination(wheelAnchor2, 1, 24);
		addAnchorDestination(wheelAnchor2, 73, 98);
		addAnchorDestination(wheelAnchor2, 98, 73);
		addAnchorDestination(wheelAnchor2, 24, 1);
		_entities.add(wheelAnchor2);
		FixedSizeArray<GameEntity> wheelSpikes2 = new FixedSizeArray<GameEntity>(10);
		int capacity2 = wheelSpikes2.getCapacity();
		for (int i = 0; i < capacity2; i++) {
			GameEntity spike = EntityHelper.spikes(TMImage.SPIKES_SINGLE, 1, 1, 2, true);
			spike.addBehavior(new CircleMovementBehavior(wheelAnchor2.Attributes.Area.Position, 
					cellWidth * 5f, 4000, true, (float)Math.PI * 2f * i / (float)capacity2));
			wheelSpikes2.add(spike);
		}
		_entities.addAll(wheelSpikes2);
		
		GameEntity wheelAnchor3 = EntityHelper.patrolAnchor(98, 24, cellWidth / 12f);
		addAnchorDestination(wheelAnchor3, 98, 24);
		addAnchorDestination(wheelAnchor3, 73, 1);
		addAnchorDestination(wheelAnchor3, 1, 73);
		addAnchorDestination(wheelAnchor3, 24, 98);
		_entities.add(wheelAnchor3);
		FixedSizeArray<GameEntity> wheelSpikes3 = new FixedSizeArray<GameEntity>(10);
		int capacity3 = wheelSpikes3.getCapacity();
		for (int i = 0; i < capacity3; i++) {
			GameEntity spike = EntityHelper.spikes(TMImage.SPIKES_SINGLE, 1, 1, 2, true);
			spike.addBehavior(new CircleMovementBehavior(wheelAnchor3.Attributes.Area.Position, 
					cellWidth * 5f, 4000, true, (float)Math.PI * 2f * i / (float)capacity3));
			wheelSpikes3.add(spike);
		}
		_entities.addAll(wheelSpikes3);
		
		GameEntity wheelAnchor4 = EntityHelper.patrolAnchor(73, 1, cellWidth / 12f);
		addAnchorDestination(wheelAnchor4, 73, 1);
		addAnchorDestination(wheelAnchor4, 98, 49);
		addAnchorDestination(wheelAnchor4, 73, 98);
		addAnchorDestination(wheelAnchor4, 24, 1);
		addAnchorDestination(wheelAnchor4, 1, 49);
		addAnchorDestination(wheelAnchor4, 24, 98);
		_entities.add(wheelAnchor4);
		FixedSizeArray<GameEntity> wheelSpikes4 = new FixedSizeArray<GameEntity>(10);
		int capacity4 = wheelSpikes4.getCapacity();
		for (int i = 0; i < capacity4; i++) {
			GameEntity spike = EntityHelper.spikes(TMImage.SPIKES_SINGLE, 1, 1, 2, true);
			spike.addBehavior(new CircleMovementBehavior(wheelAnchor4.Attributes.Area.Position, 
					cellWidth * 5f, 4000, true, (float)Math.PI * 2f * i / (float)capacity4));
			wheelSpikes4.add(spike);
		}
		_entities.addAll(wheelSpikes4);
		
		GameEntity wheelAnchor5 = EntityHelper.patrolAnchor(49, 1, cellWidth / 13f);
		addAnchorDestination(wheelAnchor5, 49, 1);
		addAnchorDestination(wheelAnchor5, 98, 24);
		addAnchorDestination(wheelAnchor5, 1, 73);
		addAnchorDestination(wheelAnchor5, 49, 98);
		addAnchorDestination(wheelAnchor5, 98, 73);
		addAnchorDestination(wheelAnchor5, 1, 24);
		_entities.add(wheelAnchor5);
		FixedSizeArray<GameEntity> wheelSpikes5 = new FixedSizeArray<GameEntity>(10);
		int capacity5 = wheelSpikes5.getCapacity();
		for (int i = 0; i < capacity5; i++) {
			GameEntity spike = EntityHelper.spikes(TMImage.SPIKES_SINGLE, 1, 1, 2, true);
			spike.addBehavior(new CircleMovementBehavior(wheelAnchor5.Attributes.Area.Position, 
					cellWidth * 5f, 4000, true, (float)Math.PI * 2f * i / (float)capacity5));
			wheelSpikes5.add(spike);
		}
		_entities.addAll(wheelSpikes5);
	}
	
	
}
