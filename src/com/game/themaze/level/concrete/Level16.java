package com.game.themaze.level.concrete;

import com.game.loblib.collision.ICollisionSender;
import com.game.loblib.entity.GameEntity;
import com.game.loblib.graphics.Sprite;
import com.game.loblib.messaging.Message;
import com.game.loblib.messaging.MessageType;
import com.game.loblib.utility.FadeType;
import com.game.loblib.utility.Global;
import com.game.loblib.utility.Manager;
import com.game.loblib.utility.android.FixedSizeArray;
import com.game.the_maze.R;
import com.game.themaze.behavior.CollisionSenderBehavior;
import com.game.themaze.behavior.FadeBehavior;
import com.game.themaze.behavior.TMBehaviorType;
import com.game.themaze.behavior.WallRenderBehavior;
import com.game.themaze.graphics.TMImage;
import com.game.themaze.level.Level;

// Invisible walls
public class Level16 extends Level {
	
	public Level16() {
		_settings.ResourceId = R.raw.level_16;
		_settings.CellWidth = (int)(Global.Renderer.Width / 16f);
		_settings.BackgroundImage = TMImage.BACKGROUND_MARBLE_GREEN;
	}
	
	@Override
	public void init() {
		super.init();
		
		int wallCount = _mapWalls.getCount();
		for (int i = 0; i < wallCount; i++) {
			GameEntity wall = _mapWalls.get(i);
			FixedSizeArray<Sprite> wallSprites = ((WallRenderBehavior)wall.getBehavior(TMBehaviorType.WALL_RENDER)).getSprites();
			wall.addBehavior(new FadeBehavior(250f, 5000f, false, FadeType.SINGLE, wallSprites));
		}
		
		Manager.Message.subscribe(this, MessageType.COLLISION |
				MessageType.FADE_IN_COMPLETE);
	}
	
	@Override
	public void unpause() {
		Manager.Message.subscribe(this, MessageType.COLLISION |
				MessageType.FADE_IN_COMPLETE);
	}
	
	@Override
	public void handleMessage(Message message) {
		super.handleMessage(message);
		
		if (message.Type == MessageType.COLLISION) {
			ICollisionSender sender = message.getData();
			int wallCount = _mapWalls.getCount();
			for (int i = 0; i < wallCount; i++) {
				if (((CollisionSenderBehavior)_mapWalls.get(i).getBehavior(TMBehaviorType.COLLISION_SENDER)) == sender) {
					((FadeBehavior)_mapWalls.get(i).getBehavior(TMBehaviorType.FADE)).fadeIn();
					break;
				}
			}
		}
		else if (message.Type == MessageType.FADE_IN_COMPLETE) {
			GameEntity entity = message.getData();
			((FadeBehavior)entity.getBehavior(TMBehaviorType.FADE)).fadeOut();
		}
	}
}
