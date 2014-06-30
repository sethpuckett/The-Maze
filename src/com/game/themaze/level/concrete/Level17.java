package com.game.themaze.level.concrete;

import com.game.loblib.entity.GameEntity;
import com.game.loblib.messaging.Message;
import com.game.loblib.messaging.MessageType;
import com.game.loblib.utility.FadeType;
import com.game.loblib.utility.Global;
import com.game.loblib.utility.Logger;
import com.game.loblib.utility.Manager;
import com.game.loblib.utility.TimerType;
import com.game.loblib.utility.android.FixedSizeArray;
import com.game.the_maze.R;
import com.game.themaze.behavior.AttachBehavior;
import com.game.themaze.behavior.FadeBehavior;
import com.game.themaze.behavior.FadeChainBehavior;
import com.game.themaze.behavior.PatrolDestinationBehavior.PatrolType;
import com.game.themaze.behavior.TMBehaviorType;
import com.game.themaze.entity.EntityHelper;
import com.game.themaze.gameitem.GameItemType;
import com.game.themaze.graphics.TMImage;
import com.game.themaze.graphics.TMSpriteLayer;
import com.game.themaze.level.Level;
import com.game.themaze.messaging.TMMessageType;
import com.game.themaze.utility.DoorOpenType;
import com.game.themaze.utility.DoorType;
import com.game.themaze.utility.TMManager;

// Blinking spikes
public class Level17 extends Level {
	
	protected FixedSizeArray<GameEntity> _spikes;
	protected FixedSizeArray<GameEntity> _anchors;
	protected GameEntity _tunnelTimer;
	protected int _tunnelIndex;
	protected GameEntity _cornerTimer;
	
	public Level17() {
		_settings.ResourceId = R.raw.level_20;
		_settings.CellWidth = (int)(Global.Renderer.Width / 16f);
		_settings.BackgroundImage = TMImage.BACKGROUND_ICE;
		_settings.EnableActionButton = true;
		_settings.Item1 = GameItemType.RED_KEY;
		_settings.Item2 = GameItemType.SPEED_BOOTS;
	}
	
	@Override
	public void init() {
		super.init();
		int cellWidth = TMManager.Level.getCellWidth();
		
		GameEntity fadeChain1 = new GameEntity();
		FadeChainBehavior fcb1 = new FadeChainBehavior(TMSpriteLayer.BACKGROUND2, .6f, 300f, 550f, 100f, 125f, FadeType.CONTINUOUS, 500f);
		addChainSprites(fcb1, 69, 59, 71, 59, 73, 59, 75, 59);
		fadeChain1.addBehavior(fcb1);
		_entities.add(fadeChain1);
		
		GameEntity fadeChain2 = new GameEntity();
		FadeChainBehavior fcb2 = new FadeChainBehavior(TMSpriteLayer.BACKGROUND2, .6f, 300f, 550f, 100f, 125f, FadeType.CONTINUOUS, 500f);
		addChainSprites(fcb2, 75, 63, 73, 63, 71, 63, 69, 63);
		fadeChain2.addBehavior(fcb2);
		_entities.add(fadeChain2);
		
		GameEntity fadeChain3 = new GameEntity();
		FadeChainBehavior fcb3 = new FadeChainBehavior(TMSpriteLayer.BACKGROUND2, .6f, 250f, 250f, 250f, 500f, FadeType.CONTINUOUS, 1250f);
		addChainSprites(fcb3, 58, 51, 62, 49, 60, 47, 62, 47);
		fadeChain3.addBehavior(fcb3);
		_entities.add(fadeChain3);
		
		GameEntity fadeChain4 = new GameEntity();
		FadeChainBehavior fcb4 = new FadeChainBehavior(TMSpriteLayer.BACKGROUND2, .6f, 250f, 250f, 250f, 500f, FadeType.CONTINUOUS, 1250f);
		addChainSprites(fcb4, 48, 47, 46, 47, 50, 51, 46, 49);
		fadeChain4.addBehavior(fcb4);
		_entities.add(fadeChain4);
		
		GameEntity fadeChain5 = new GameEntity();
		FadeChainBehavior fcb5 = new FadeChainBehavior(TMSpriteLayer.BACKGROUND2, .6f, 250f, 250f, 250f, 500f, FadeType.CONTINUOUS, 1250f);
		addChainSprites(fcb5, 50, 58, 48, 60, 50, 60, 46, 56);
		fadeChain5.addBehavior(fcb5);
		_entities.add(fadeChain5);
		
		GameEntity fadeChain6 = new GameEntity();
		FadeChainBehavior fcb6 = new FadeChainBehavior(TMSpriteLayer.BACKGROUND2, .6f, 250f, 250f, 250f, 500f, FadeType.CONTINUOUS, 1250f);
		addChainSprites(fcb6, 45, 36, 49, 36, 51, 38, 49, 40);
		fadeChain6.addBehavior(fcb6);
		_entities.add(fadeChain6);
		
		GameEntity fadeChain7 = new GameEntity();
		FadeChainBehavior fcb7 = new FadeChainBehavior(TMSpriteLayer.BACKGROUND2, .6f, 250f, 250f, 250f, 500f, FadeType.CONTINUOUS, 1750f);
		addChainSprites(fcb7, 45, 34, 51, 32, 51, 28, 47, 30, 53, 30);
		fadeChain7.addBehavior(fcb7);
		_entities.add(fadeChain7);
		
		GameEntity fadeChain8 = new GameEntity();
		FadeChainBehavior fcb8 = new FadeChainBehavior(TMSpriteLayer.BACKGROUND2, .6f, 250f, 250f, 250f, 500f, FadeType.CONTINUOUS, 2250f);
		addChainSprites(fcb8, 55, 40, 55, 34, 57, 28, 55, 32, 57, 40, 55, 36);
		fadeChain8.addBehavior(fcb8);
		_entities.add(fadeChain8);
		
		GameEntity fadeChain9 = new GameEntity();
		FadeChainBehavior fcb9 = new FadeChainBehavior(TMSpriteLayer.BACKGROUND2, .6f, 100f, 100f, 1000f, 1100f, FadeType.CONTINUOUS, 1000f);
		addChainSprites(fcb9,37, 70, 37, 76);
		fadeChain9.addBehavior(fcb9);
		_entities.add(fadeChain9);
		
		GameEntity fadeChain10 = new GameEntity();
		FadeChainBehavior fcb10 = new FadeChainBehavior(TMSpriteLayer.BACKGROUND2, 1f, 750f, 750f, 500f, 250f, FadeType.CONTINUOUS, 5000f);
		addChainSprites(fcb10, 46, 82, 50, 74, 50, 68, 44, 72, 50, 78, 46, 76, 50, 82, 44, 68, 42, 72, 50, 76, 48, 68, 42, 68, 46, 78, 50, 72, 46, 80, 50, 70, 46, 68, 46, 74, 50, 80, 46, 72);
		fadeChain10.addBehavior(fcb10);
		_entities.add(fadeChain10);
		
		GameEntity fadeChain11 = new GameEntity();
		FadeChainBehavior fcb11 = new FadeChainBehavior(TMSpriteLayer.BACKGROUND2, .6f, 250f, 250f, 250f, 500f, FadeType.CONTINUOUS, 1250f);
		addChainSprites(fcb11, 19, 77, 23, 75, 21, 79, 19, 79);
		fadeChain11.addBehavior(fcb11);
		_entities.add(fadeChain11);
		
		GameEntity fadeChain12 = new GameEntity();
		FadeChainBehavior fcb12 = new FadeChainBehavior(TMSpriteLayer.BACKGROUND2, .6f, 250f, 250f, 250f, 500f, FadeType.CONTINUOUS, 1250f);
		addChainSprites(fcb12, 30, 74, 32, 74, 28, 79, 32, 76);
		fadeChain12.addBehavior(fcb12);
		_entities.add(fadeChain12);
		
		_spikes = new FixedSizeArray<GameEntity>(20);
		_anchors = new FixedSizeArray<GameEntity>(20);
		
		_spikes.add(EntityHelper.singleSpike(36, 61));
		_anchors.add(EntityHelper.patrolAnchor(36, 61, (float)cellWidth / 4f, 750, 0, PatrolType.CONTINUOUS));
		addAnchorDestination(_anchors.get(0), 36, 61);
		addAnchorDestination(_anchors.get(0), 42, 61);
		_spikes.get(0).addBehavior(new AttachBehavior(_anchors.get(0)));
		_spikes.get(0).addBehavior(new FadeBehavior(200f, 200f, false, FadeType.CONTINUOUS));
		_entities.add(_spikes.get(0));
		_entities.add(_anchors.get(0));
		
		_spikes.add(EntityHelper.singleSpike(60, 64));
		_anchors.add(EntityHelper.patrolAnchor(60, 64, (float)cellWidth / 4f, 750, 0, PatrolType.CONTINUOUS));
		addAnchorDestination(_anchors.get(1), 60, 64);
		addAnchorDestination(_anchors.get(1), 60, 76);
		_spikes.get(1).addBehavior(new AttachBehavior(_anchors.get(1)));
		_spikes.get(1).addBehavior(new FadeBehavior(400f, 400f, false, FadeType.CONTINUOUS));
		_entities.add(_spikes.get(1));
		_entities.add(_anchors.get(1));
		
		_spikes.add(EntityHelper.singleSpike(60, 88));
		_anchors.add(EntityHelper.patrolAnchor(60, 88, (float)cellWidth / 4f, 0, 0, PatrolType.SINGLE));
		addAnchorDestination(_anchors.get(2), 60, 88);
		addAnchorDestination(_anchors.get(2), 66, 88);
		_spikes.get(2).addBehavior(new AttachBehavior(_anchors.get(2)));
		_spikes.get(2).addBehavior(new FadeBehavior(200f, 200f, false, FadeType.CONTINUOUS));
		_entities.add(_spikes.get(2));
		_entities.add(_anchors.get(2));
		
		_spikes.add(EntityHelper.singleSpike(60, 94));
		_anchors.add(EntityHelper.patrolAnchor(60, 94, (float)cellWidth / 4f, 0, 0, PatrolType.SINGLE));
		addAnchorDestination(_anchors.get(3), 60, 94);
		addAnchorDestination(_anchors.get(3), 60, 88);
		_spikes.get(3).addBehavior(new AttachBehavior(_anchors.get(3)));
		_spikes.get(3).addBehavior(new FadeBehavior(200f, 200f, false, FadeType.CONTINUOUS));
		_entities.add(_spikes.get(3));
		_entities.add(_anchors.get(3));
		
		_spikes.add(EntityHelper.singleSpike(42, 88));
		_anchors.add(EntityHelper.patrolAnchor(42, 88, (float)cellWidth / 4f, 0, 0, PatrolType.SINGLE));
		addAnchorDestination(_anchors.get(4), 42, 88);
		addAnchorDestination(_anchors.get(4), 42, 94);
		_spikes.get(4).addBehavior(new AttachBehavior(_anchors.get(4)));
		_spikes.get(4).addBehavior(new FadeBehavior(200f, 200f, false, FadeType.CONTINUOUS));
		_entities.add(_spikes.get(4));
		_entities.add(_anchors.get(4));
		
		_spikes.add(EntityHelper.singleSpike(36, 88));
		_anchors.add(EntityHelper.patrolAnchor(36, 88, (float)cellWidth / 4f, 0, 0, PatrolType.SINGLE));
		addAnchorDestination(_anchors.get(5), 36, 88);
		addAnchorDestination(_anchors.get(5), 42, 88);
		_spikes.get(5).addBehavior(new AttachBehavior(_anchors.get(5)));
		_spikes.get(5).addBehavior(new FadeBehavior(200f, 200f, false, FadeType.CONTINUOUS));
		_entities.add(_spikes.get(5));
		_entities.add(_anchors.get(5));
		
		// TUNNEL
		
		_spikes.add(EntityHelper.singleSpike(24, 68));
		_anchors.add(EntityHelper.patrolAnchor(24, 68, (float)cellWidth / 4f, 0, 0, PatrolType.SINGLE));
		addAnchorDestination(_anchors.get(6), 25, 68);
		addAnchorDestination(_anchors.get(6), 17, 68);
		_spikes.get(6).addBehavior(new AttachBehavior(_anchors.get(6)));
		_spikes.get(6).addBehavior(new FadeBehavior(267f, 267f, false, FadeType.CONTINUOUS));
		_entities.add(_spikes.get(6));
		_entities.add(_anchors.get(6));
		
		_spikes.add(EntityHelper.singleSpike(19, 68));
		_anchors.add(EntityHelper.patrolAnchor(19, 68, (float)cellWidth / 4f, 0, 0, PatrolType.SINGLE));
		addAnchorDestination(_anchors.get(7), 17, 68);
		addAnchorDestination(_anchors.get(7), 25, 68);
		_spikes.get(7).addBehavior(new AttachBehavior(_anchors.get(7)));
		_spikes.get(7).addBehavior(new FadeBehavior(267f, 267f, false, FadeType.CONTINUOUS));
		_entities.add(_spikes.get(7));
		_entities.add(_anchors.get(7));
		
		_spikes.add(EntityHelper.singleSpike(23, 66));
		_anchors.add(EntityHelper.patrolAnchor(23, 66, (float)cellWidth / 4f, 0, 0, PatrolType.SINGLE));
		addAnchorDestination(_anchors.get(8), 25, 66);
		addAnchorDestination(_anchors.get(8), 17, 66);
		_spikes.get(8).addBehavior(new AttachBehavior(_anchors.get(8)));
		_spikes.get(8).addBehavior(new FadeBehavior(267f, 267f, false, FadeType.CONTINUOUS));
		_entities.add(_spikes.get(8));
		_entities.add(_anchors.get(8));
		
		_spikes.add(EntityHelper.singleSpike(20, 66));
		_anchors.add(EntityHelper.patrolAnchor(20, 66, (float)cellWidth / 4f, 0, 0, PatrolType.SINGLE));
		addAnchorDestination(_anchors.get(9), 17, 66);
		addAnchorDestination(_anchors.get(9), 25, 66);
		_spikes.get(9).addBehavior(new AttachBehavior(_anchors.get(9)));
		_spikes.get(9).addBehavior(new FadeBehavior(267f, 267f, false, FadeType.CONTINUOUS));
		_entities.add(_spikes.get(9));
		_entities.add(_anchors.get(9));
		
		_spikes.add(EntityHelper.singleSpike(22, 64));
		_anchors.add(EntityHelper.patrolAnchor(22, 64, (float)cellWidth / 4f, 0, 0, PatrolType.SINGLE));
		addAnchorDestination(_anchors.get(10), 25, 64);
		addAnchorDestination(_anchors.get(10), 17, 64);
		_spikes.get(10).addBehavior(new AttachBehavior(_anchors.get(10)));
		_spikes.get(10).addBehavior(new FadeBehavior(267f, 267f, false, FadeType.CONTINUOUS));
		_entities.add(_spikes.get(10));
		_entities.add(_anchors.get(10));
		
		_spikes.add(EntityHelper.singleSpike(21, 64));
		_anchors.add(EntityHelper.patrolAnchor(21, 64, (float)cellWidth / 4f, 0, 0, PatrolType.SINGLE));
		addAnchorDestination(_anchors.get(11), 17, 64);
		addAnchorDestination(_anchors.get(11), 25, 64);
		_spikes.get(11).addBehavior(new AttachBehavior(_anchors.get(11)));
		_spikes.get(11).addBehavior(new FadeBehavior(267f, 267f, false, FadeType.CONTINUOUS));
		_entities.add(_spikes.get(11));
		_entities.add(_anchors.get(11));
		
		_spikes.add(EntityHelper.singleSpike(21, 62));
		_anchors.add(EntityHelper.patrolAnchor(21, 62, (float)cellWidth / 4f, 0, 0, PatrolType.SINGLE));
		addAnchorDestination(_anchors.get(12), 25, 62);
		addAnchorDestination(_anchors.get(12), 17, 62);
		_spikes.get(12).addBehavior(new AttachBehavior(_anchors.get(12)));
		_spikes.get(12).addBehavior(new FadeBehavior(267f, 267f, false, FadeType.CONTINUOUS));
		_entities.add(_spikes.get(12));
		_entities.add(_anchors.get(12));
		
		_spikes.add(EntityHelper.singleSpike(22, 62));
		_anchors.add(EntityHelper.patrolAnchor(22, 62, (float)cellWidth / 4f, 0, 0, PatrolType.SINGLE));
		addAnchorDestination(_anchors.get(13), 17, 62);
		addAnchorDestination(_anchors.get(13), 25, 62);
		_spikes.get(13).addBehavior(new AttachBehavior(_anchors.get(13)));
		_spikes.get(13).addBehavior(new FadeBehavior(267f, 267f, false, FadeType.CONTINUOUS));
		_entities.add(_spikes.get(13));
		_entities.add(_anchors.get(13));
		
		_spikes.add(EntityHelper.singleSpike(20, 60));
		_anchors.add(EntityHelper.patrolAnchor(20, 60, (float)cellWidth / 4f, 0, 0, PatrolType.SINGLE));
		addAnchorDestination(_anchors.get(14), 25, 60);
		addAnchorDestination(_anchors.get(14), 17, 60);
		_spikes.get(14).addBehavior(new AttachBehavior(_anchors.get(14)));
		_spikes.get(14).addBehavior(new FadeBehavior(267f, 267f, false, FadeType.CONTINUOUS));
		_entities.add(_spikes.get(14));
		_entities.add(_anchors.get(14));
		
		_spikes.add(EntityHelper.singleSpike(23, 60));
		_anchors.add(EntityHelper.patrolAnchor(23, 60, (float)cellWidth / 4f, 0, 0, PatrolType.SINGLE));
		addAnchorDestination(_anchors.get(15), 17, 60);
		addAnchorDestination(_anchors.get(15), 25, 60);
		_spikes.get(15).addBehavior(new AttachBehavior(_anchors.get(15)));
		_spikes.get(15).addBehavior(new FadeBehavior(267f, 267f, false, FadeType.CONTINUOUS));
		_entities.add(_spikes.get(15));
		_entities.add(_anchors.get(15));
		
		_spikes.add(EntityHelper.singleSpike(19, 58));
		_anchors.add(EntityHelper.patrolAnchor(19, 58, (float)cellWidth / 4f, 0, 0, PatrolType.SINGLE));
		addAnchorDestination(_anchors.get(16), 25, 58);
		addAnchorDestination(_anchors.get(16), 17, 58);
		_spikes.get(16).addBehavior(new AttachBehavior(_anchors.get(16)));
		_spikes.get(16).addBehavior(new FadeBehavior(267f, 267f, false, FadeType.CONTINUOUS));
		_entities.add(_spikes.get(16));
		_entities.add(_anchors.get(16));
		
		_spikes.add(EntityHelper.singleSpike(24, 58));
		_anchors.add(EntityHelper.patrolAnchor(24, 58, (float)cellWidth / 4f, 0, 0, PatrolType.SINGLE));
		addAnchorDestination(_anchors.get(17), 17, 58);
		addAnchorDestination(_anchors.get(17), 25, 58);
		_spikes.get(17).addBehavior(new AttachBehavior(_anchors.get(17)));
		_spikes.get(17).addBehavior(new FadeBehavior(267f, 267f, false, FadeType.CONTINUOUS));
		_entities.add(_spikes.get(17));
		_entities.add(_anchors.get(17));

		_spikes.add(EntityHelper.singleSpike(18, 56));
		_anchors.add(EntityHelper.patrolAnchor(18, 56, (float)cellWidth / 4f, 0, 0, PatrolType.SINGLE));
		addAnchorDestination(_anchors.get(18), 25, 56);
		addAnchorDestination(_anchors.get(18), 17, 56);
		_spikes.get(18).addBehavior(new AttachBehavior(_anchors.get(18)));
		_spikes.get(18).addBehavior(new FadeBehavior(267f, 267f, false, FadeType.CONTINUOUS));
		_entities.add(_spikes.get(18));
		_entities.add(_anchors.get(18));
		
		_spikes.add(EntityHelper.singleSpike(25, 56));
		_anchors.add(EntityHelper.patrolAnchor(25, 56, (float)cellWidth / 4f, 0, 0, PatrolType.SINGLE));
		addAnchorDestination(_anchors.get(19), 17, 56);
		addAnchorDestination(_anchors.get(19), 25, 56);
		_spikes.get(19).addBehavior(new AttachBehavior(_anchors.get(19)));
		_spikes.get(19).addBehavior(new FadeBehavior(267f, 267f, false, FadeType.CONTINUOUS));
		_entities.add(_spikes.get(19));
		_entities.add(_anchors.get(19));
		
		_tunnelIndex = 0;
		_tunnelTimer = EntityHelper.timer(TimerType.CONTINUOUS, 200f);
		_entities.add(_tunnelTimer);
		
		_cornerTimer = EntityHelper.timer(TimerType.CONTINUOUS, 800f);
		_entities.add(_cornerTimer);
		
		int redDoor = makeDoor(38, 82, 38, 80, false, DoorType.RED, DoorOpenType.SINGLE, false);
		makeDoorTrigger(redDoor, 39, 82, 2, 2);
		
		_entities.add(EntityHelper.gameItem(58, 92, GameItemType.RED_KEY, true));
		_entities.add(EntityHelper.gameItem(54, 29, GameItemType.SPEED_BOOTS, true));
		
		Manager.Message.subscribe(this, TMMessageType.DESTINATION_REACHED | TMMessageType.PATROL_START | 
				MessageType.TIMER_ALARM);
	}
	
	@Override
	public void unpause() {
		super.unpause();
		Manager.Message.subscribe(this, TMMessageType.DESTINATION_REACHED | TMMessageType.PATROL_START |
				MessageType.TIMER_ALARM);
	}
	
	@Override
	public void enableBehaviors() {
		super.enableBehaviors();
		
		int count = _spikes.getCount();
		for (int i = 0; i < count; i++)
			((FadeBehavior)_spikes.get(i).getBehavior(TMBehaviorType.FADE)).fadeIn();
	}
	
	@Override
	public void handleMessage(Message message) {
		super.handleMessage(message);
		
		if (message.Type == TMMessageType.DESTINATION_REACHED) {
			GameEntity entity = message.getData();
			int anchorIndex = _anchors.find(entity, false);
			if (anchorIndex > -1)
				((FadeBehavior)_spikes.get(anchorIndex).getBehavior(TMBehaviorType.FADE)).stop(false);
		}
		else if (message.Type == TMMessageType.PATROL_START) {
			GameEntity entity = message.getData();
			int anchorIndex = _anchors.find(entity, false);
			if (anchorIndex > -1)
				((FadeBehavior)_spikes.get(anchorIndex).getBehavior(TMBehaviorType.FADE)).fadeIn();
		}
		else if (message.Type == MessageType.TIMER_ALARM) {
			GameEntity timer = message.getData();
			if (timer == _tunnelTimer) {
				_anchors.get(_tunnelIndex + 6).enableBehaviors(TMBehaviorType.PATROL_DESTINATION);
				_anchors.get(_tunnelIndex + 7).enableBehaviors(TMBehaviorType.PATROL_DESTINATION);
				
				_tunnelIndex += 2;
				if (_tunnelIndex > 12)
					_tunnelIndex = 0;
			}
			else if (timer == _cornerTimer) {
				_anchors.get(2).enableBehaviors(TMBehaviorType.PATROL_DESTINATION);
				_anchors.get(3).enableBehaviors(TMBehaviorType.PATROL_DESTINATION);
				_anchors.get(4).enableBehaviors(TMBehaviorType.PATROL_DESTINATION);
				_anchors.get(5).enableBehaviors(TMBehaviorType.PATROL_DESTINATION);
			}
		}
	}
	
	@Override
	protected void enableGameItem(int gameItemType) {
		switch (gameItemType) {
		case GameItemType.SPEED_BOOTS:
			int cellWidth = TMManager.Level.getCellWidth();
			_player.Attributes.Speed = (float)cellWidth/7f;
		default:
			Logger.e(_tag, "Cannot enable; invalid gameItemType");				
		}
	}
	
	protected void addChainSprites(FadeChainBehavior chain, int... gridCoords) {
		for (int i = 0; i < gridCoords.length; i += 2) {
			GameEntity spike = EntityHelper.singleSpike(gridCoords[i], gridCoords[i+1]);
			_entities.add(spike);
			chain.addSprite(spike.Attributes.Sprite);
		}
	}

}
