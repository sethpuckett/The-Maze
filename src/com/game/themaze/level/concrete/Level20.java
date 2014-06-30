package com.game.themaze.level.concrete;

import java.util.Random;

import com.game.loblib.entity.GameEntity;
import com.game.loblib.graphics.Sprite;
import com.game.loblib.messaging.Message;
import com.game.loblib.utility.FadeType;
import com.game.loblib.utility.Global;
import com.game.loblib.utility.Logger;
import com.game.loblib.utility.Manager;
import com.game.the_maze.R;
import com.game.themaze.behavior.AttachBehavior;
import com.game.themaze.behavior.DoorBehavior;
import com.game.themaze.behavior.FadeChainBehavior;
import com.game.themaze.behavior.TMBehaviorType;
import com.game.themaze.behavior.TriggerPathBehavior;
import com.game.themaze.entity.EntityHelper;
import com.game.themaze.graphics.TMImage;
import com.game.themaze.graphics.TMSpriteLayer;
import com.game.themaze.level.Level;
import com.game.themaze.messaging.TMMessageType;
import com.game.themaze.utility.TMManager;

// Path memory
public class Level20 extends Level {
	
	protected GameEntity _button1;
	protected GameEntity _button2;
	protected GameEntity _button3;
	protected GameEntity _fadeChain1;
	protected GameEntity _fadeChain2;
	protected GameEntity _fadeChain3;
	protected GameEntity _spikeAnchor1;
	protected GameEntity _spikeAnchor2;
	protected GameEntity _spikeAnchor3;
	protected GameEntity _pathArea1;
	protected GameEntity _pathArea2;
	protected GameEntity _pathArea3;
	
	protected boolean _fadeChainActive;
	
	public Level20() {
		_settings.ResourceId = R.raw.level_18;
		_settings.CellWidth = (int)(Global.Renderer.Width / 16f);
		_settings.BackgroundImage = TMImage.BACKGROUND_SAND;
		_settings.EnableActionButton = true;
		_settings.DisableCamera = true;
	}
	
	@Override
	public void init() {
		super.init();
		float cellWidth = TMManager.Level.getCellWidth();
		
		_fadeChainActive = false;
		
		// Buttons
		_button1 = addButton(16, 47);
		_button2 = addButton(38, 47);
		_button3 = addButton(60, 47);
		
		// Spike Anchors & Rows
		_spikeAnchor1 = addSpikeRow(14, 37, 59);
		_spikeAnchor2 = addSpikeRow(36, 37, 59);
		_spikeAnchor3 = addSpikeRow(58, 37, 59);
		
		// Tile Graphics
		addTileGraphic(20, 39);
		addTileGraphic(42, 39);
		addTileGraphic(64, 39);
		
		// Camera Rail
		GameEntity cameraRail = new GameEntity();
		AttachBehavior ab = new AttachBehavior(_player, cellWidth * 4f, 0f);
			ab.enableFixedY((49 + _offset.Y) * cellWidth);
		cameraRail.addBehavior(ab);
		_entities.add(cameraRail);
		Global.Camera.Anchor = cameraRail;
		Global.Camera.Threshold.setPosition(cellWidth, cellWidth);
		
		Random rand = new Random();
		
		// Path + Fade Chain 1
		_fadeChain1 = new GameEntity();
		FadeChainBehavior fcb1 = new FadeChainBehavior(TMSpriteLayer.BACKGROUND2, .4f, 120f, 900f, 0f, 120f, FadeType.SINGLE);
		GameEntity path1 = new GameEntity();
		_pathArea1 = EntityHelper.trigger(20, 39, 8, 20);
		TriggerPathBehavior tpb1 = new TriggerPathBehavior(_pathArea1);
		switch (rand.nextInt(4)) {
		case 0:
			addChainAndPath(fcb1, tpb1, 20, 49, 22, 49, 22, 47, 22, 45, 22, 43, 24, 43, 24, 45, 24, 47, 24, 47, 24, 49, 26, 49);
			break;
		case 1:
			addChainAndPath(fcb1, tpb1, 20, 55, 22, 55, 22, 53, 22, 51, 22, 49, 22, 47, 22, 45, 22, 43, 22, 41, 24, 41, 26, 41);
			break;
		case 2:
			addChainAndPath(fcb1, tpb1, 20, 45, 22, 45, 22, 47, 22, 49, 22, 51, 22, 53, 22, 55, 24, 55, 24, 53, 24, 51, 26, 51);
			break;
		case 3:
			addChainAndPath(fcb1, tpb1, 20, 39, 22, 39, 24, 39, 24, 41, 24, 43, 24, 45, 24, 47, 24, 49, 24, 51, 24, 53, 26, 53);
			break;
		}
		_fadeChain1.addBehavior(fcb1);
		_entities.add(_fadeChain1);
		path1.addBehavior(tpb1);
		_entities.add(_pathArea1);
		_entities.add(path1);
		
		// Path + Fade Chain 2
		_fadeChain2 = new GameEntity();
		FadeChainBehavior fcb2 = new FadeChainBehavior(TMSpriteLayer.BACKGROUND2, .4f, 120f, 900f, 0f, 120f, FadeType.SINGLE);
		GameEntity path2 = new GameEntity();
		_pathArea2 = EntityHelper.trigger(42, 39, 8, 20);
		TriggerPathBehavior tpb2 = new TriggerPathBehavior(_pathArea2);
		switch (rand.nextInt(4)) {
		case 0:
			addChainAndPath(fcb2, tpb2, 42, 55, 44, 55, 44, 53, 46, 53, 46, 51, 46, 49, 46, 47, 44, 47, 44, 45, 44, 43, 44, 41, 46, 41, 48, 41);
			break;
		case 1:
			addChainAndPath(fcb2, tpb2, 42, 53, 44, 53, 44, 55, 44, 57, 46, 57, 46, 55, 46, 53, 46, 51, 46, 49, 44, 49, 44, 47, 46, 47, 48, 47);
			break;
		case 2:
			addChainAndPath(fcb2, tpb2, 42, 45, 44, 45, 44, 47, 44, 49, 46, 49, 46, 47, 46, 45, 46, 43, 44, 43, 44, 41, 44, 39, 46, 39, 48, 39);
			break;
		case 3:
			addChainAndPath(fcb2, tpb2, 42, 43, 44, 43, 44, 41, 46, 41, 46, 43, 46, 45, 46, 47, 46, 49, 44, 49, 44, 51, 44, 53, 46, 53, 48, 53);
			break;
		}
		_fadeChain2.addBehavior(fcb2);
		_entities.add(_fadeChain2);
		path2.addBehavior(tpb2);
		_entities.add(_pathArea2);
		_entities.add(path2);
		
		// Path + Fade Chain 3
		_fadeChain3 = new GameEntity();
		FadeChainBehavior fcb3 = new FadeChainBehavior(TMSpriteLayer.BACKGROUND2, .4f, 120f, 900f, 0f, 120f, FadeType.SINGLE);
		GameEntity path3 = new GameEntity();
		_pathArea3 = EntityHelper.trigger(64, 39, 8, 20);
		TriggerPathBehavior tpb3 = new TriggerPathBehavior(_pathArea3);
		switch (rand.nextInt(4)) {
		case 0:
			addChainAndPath(fcb3, tpb3, 64, 57, 66, 57, 66, 55, 66, 53, 68, 53, 68, 51, 68, 49, 68, 47, 66, 47, 66, 45, 68, 45, 68, 43, 68, 41, 68, 39, 70, 39);
			break;
		case 1:
			addChainAndPath(fcb3, tpb3, 64, 51, 66, 51, 66, 53, 66, 55, 68, 55, 68, 53, 68, 51, 68, 49, 68, 47, 66, 47, 66, 45, 66, 43, 68, 43, 68, 41, 70, 41);
			break;
		case 2:
			addChainAndPath(fcb3, tpb3, 64, 47, 66, 47, 66, 45, 68, 45, 68, 47, 68, 49, 66, 49, 66, 51, 66, 53, 66, 55, 66, 57, 68, 57, 68, 55, 68, 53, 70, 53);
			break;
		case 3:
			addChainAndPath(fcb3, tpb3, 64, 41, 66, 41, 66, 43, 68, 43, 68, 45, 68, 47, 66, 47, 66, 49, 66, 51, 68, 51, 68, 53, 66, 53, 66, 55, 68, 55, 70, 55);
			break;
		}
		_fadeChain3.addBehavior(fcb3);
		_entities.add(_fadeChain3);
		path3.addBehavior(tpb3);
		_entities.add(_pathArea3);
		_entities.add(path3);
		
		Manager.Message.subscribe(this, TMMessageType.TRIGGER_PATH_FAIL | TMMessageType.FADE_CHAIN_COMPLETE);
	}
	
	@Override
	public void unpause() {
		super.unpause();
		Manager.Message.subscribe(this, TMMessageType.TRIGGER_PATH_FAIL | TMMessageType.FADE_CHAIN_COMPLETE);
	}
	
	@Override
	public void enableBehaviors() {
		super.enableBehaviors();
		
		_fadeChain1.disableBehaviors();
		_fadeChain2.disableBehaviors();
		_fadeChain3.disableBehaviors();
	}

	@Override
	public void handleMessage(Message message) {
		super.handleMessage(message);
		
		if (message.Type == TMMessageType.TRIGGER_PATH_FAIL) {
			if (Manager.Trigger.hit(_pathArea1)) {
				((DoorBehavior)_spikeAnchor1.getBehavior(TMBehaviorType.DOOR_ANCHOR)).open();
			}
			else if (Manager.Trigger.hit(_pathArea2)) {
				((DoorBehavior)_spikeAnchor2.getBehavior(TMBehaviorType.DOOR_ANCHOR)).open();
			}
			else if (Manager.Trigger.hit(_pathArea3)) {
				((DoorBehavior)_spikeAnchor3.getBehavior(TMBehaviorType.DOOR_ANCHOR)).open();
			}
		}
		else if (message.Type == TMMessageType.FADE_CHAIN_COMPLETE) {
			GameEntity chain = message.getData();
			if (chain == _fadeChain1 || chain == _fadeChain2 || chain == _fadeChain3) {
				_fadeChainActive = false;
				if (Manager.Trigger.hit(_button1) || Manager.Trigger.hit(_button2) || Manager.Trigger.hit(_button3))
					enableActionButton();
 			}	
		}
	}
	
	@Override
	protected boolean onEnableActionCheck(GameEntity trigger) {
		return !_fadeChainActive;
	}
	
	@Override
	protected void onActionClicked() {
		_fadeChainActive = true;
		disableActionButton();
		
		if (Manager.Trigger.hit(_button1))
			_fadeChain1.enableBehaviors();
		else if (Manager.Trigger.hit(_button2))
			_fadeChain2.enableBehaviors();
		else if (Manager.Trigger.hit(_button3))
			_fadeChain3.enableBehaviors();
	}

	// creates button graphic+trigger and adds to _entities; returns trigger
	protected GameEntity addButton(int xGrid, int yGrid) {
		_entities.add(EntityHelper.levelGraphic(TMImage.TRIGGER_BUTTON_LARGE, TMSpriteLayer.BACKGROUND1, xGrid, yGrid, 4, 4));
		GameEntity trigger = EntityHelper.trigger(xGrid, yGrid, 4, 4);
		_actionTriggers.add(trigger);
		_entities.add(trigger);
		return trigger;
	}
	
	// creates anchor+spikes and adds to entities; returns anchor
	protected GameEntity addSpikeRow(int xGrid, int yGridClosed, int yGridOpen) {
		float cellWidth = TMManager.Level.getCellWidth();
		GameEntity anchor = EntityHelper.doorAnchor(xGrid, yGridClosed, xGrid, yGridOpen);
		anchor.Attributes.Speed = cellWidth / 2f;
		GameEntity spikes = EntityHelper.spikes(TMImage.SPIKES_R, TMImage.SPIKES_L, TMImage.SPIKES_LR, xGrid, yGridClosed, 16, true);
		spikes.addBehavior(new AttachBehavior(anchor));
		_entities.add(spikes);		
		_entities.add(anchor);
		return anchor;
	}
	
	protected void addTileGraphic(int xGrid, int yGrid) {
		GameEntity tile1 = EntityHelper.levelGraphic(TMImage.STONE_TILE, TMSpriteLayer.BACKGROUND1, xGrid, yGrid, 8, 8);
		GameEntity tile2 = EntityHelper.levelGraphic(TMImage.STONE_TILE, TMSpriteLayer.BACKGROUND1, xGrid, yGrid + 8, 8, 8);
		GameEntity tile3 = EntityHelper.levelGraphic(TMImage.STONE_TILE, TMSpriteLayer.BACKGROUND1, xGrid, yGrid + 16, 8, 4);
		tile3.Attributes.Sprite.Frames = tile3.Attributes.Sprite.Frames.clone();
		tile3.Attributes.Sprite.Frames[1] = 256;
		tile3.Attributes.Sprite.Frames[3] = -256;
		_entities.add(tile1);
		_entities.add(tile2);
		_entities.add(tile3);
	}
	
	protected void addChainAndPath(FadeChainBehavior chain, TriggerPathBehavior path, int... gridCoords) {
		if (gridCoords.length % 2 != 0) {
			Logger.e(_tag, "Cannot create path & chain; invalid number of coordinates");
			return;
		}
		
		addChainSprites(chain, gridCoords);
		addPathTriggers(path, gridCoords);
	}
	
	protected void addChainSprites(FadeChainBehavior chain, int... gridCoords) {
		float cellWidth = TMManager.Level.getCellWidth();
		for (int i = 0; i < gridCoords.length; i += 2) {
			Sprite sprite = Manager.Sprite.make(TMImage.STONE_TILE_LIT);
			sprite.Area.setSize(cellWidth * 2f, cellWidth * 2f);
			sprite.Area.setPosition(xGridPosition(gridCoords[i]), yGridPosition(gridCoords[i+1]));
			chain.addSprite(sprite);
		}
	}
	
	protected void addPathTriggers(TriggerPathBehavior path, int... gridCoords) {
		for (int i = 0; i < gridCoords.length; i += 2) {
			GameEntity trigger = EntityHelper.trigger(gridCoords[i], gridCoords[i+1], 2, 2);
			_entities.add(trigger);
			path.addPathTrigger(trigger);
		}
	}
}
