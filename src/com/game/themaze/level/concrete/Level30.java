package com.game.themaze.level.concrete;

import com.game.loblib.collision.ICollisionSender;
import com.game.loblib.entity.GameEntity;
import com.game.loblib.messaging.Message;
import com.game.loblib.messaging.MessageType;
import com.game.loblib.utility.Manager;
import com.game.loblib.utility.TweenType;
import com.game.themaze.behavior.CollisionSenderBehavior;
import com.game.themaze.behavior.TMBehaviorType;
import com.game.themaze.behavior.TweenBehavior;
import com.game.themaze.behavior.WallRenderBehavior;
import com.game.themaze.entity.EntityHelper;
import com.game.themaze.graphics.TMImage;
import com.game.themaze.graphics.TMSpriteLayer;
import com.game.themaze.level.Level;

public class Level30 extends Level {
	public Level30() {
	}
	
	@Override
	public void init() {
		super.init();
		
		_entities.add(EntityHelper.tiledGraphic(TMImage.BACKGROUND_DIRT_GRAY, TMSpriteLayer.BACKGROUND1, 500f, 0, 0, 4000f));
		
		int count = _mapWalls.getCount();
		for (int i = 0; i < count; i++) {
			GameEntity wall = _mapWalls.get(i);
			((WallRenderBehavior)wall.getBehavior(TMBehaviorType.WALL_RENDER)).setAlpha(0);
			TweenBehavior tween = new TweenBehavior(
					TweenType.ALPHA, 
					1f, 
					200, 
					((WallRenderBehavior)wall.getBehavior(TMBehaviorType.WALL_RENDER)).getSprites());
			wall.addBehavior(tween);
		}
		
		Manager.Message.subscribe(this, MessageType.COLLISION);
	}

	@Override
	public void enableBehaviors() {
		super.enableBehaviors();
		
		int wallCount = _mapWalls.getCount();
		for (int i = 0; i < wallCount; i++)
			_entities.get(i).disableBehaviors(TMBehaviorType.TWEEN);
	}
	
	@Override
	public void unpause() {
		super.unpause();
		Manager.Message.subscribe(this, MessageType.BUTTON_CLICKED | MessageType.COLLISION);
	}

	@Override
	public void handleMessage(Message message) {
		super.handleMessage(message);
		
		if (message.Type == MessageType.COLLISION) {
			ICollisionSender sender = message.getData();
			int wallCount = _mapWalls.getCount();
			for (int i = 0; i < wallCount; i++) {
				if (((CollisionSenderBehavior)_mapWalls.get(i).getBehavior(TMBehaviorType.COLLISION_SENDER)) == sender) {
					_mapWalls.get(i).getBehavior(TMBehaviorType.TWEEN).enable();
					break;
				}
			}
		}
	}
}
