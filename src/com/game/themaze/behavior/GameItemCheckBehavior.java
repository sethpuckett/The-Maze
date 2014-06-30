package com.game.themaze.behavior;

import com.game.loblib.behavior.Behavior;
import com.game.loblib.collision.ICollisionSender;
import com.game.loblib.utility.Manager;
import com.game.themaze.collision.TMCollisionLayer;
import com.game.themaze.messaging.TMMessageType;

public class GameItemCheckBehavior extends CollisionCheckBehavior {

	public GameItemCheckBehavior() {
		super(TMCollisionLayer.GAME_ITEM);
		_type = TMBehaviorType.GAME_ITEM_CHECK;
	}
	
	@Override
	protected void onCollision(ICollisionSender sender) {
		Manager.Message.sendMessage(TMMessageType.ITEM_HIT, ((Behavior)(sender)).getEntity());
	}
	
	@Override
	protected void setTag() {
		_tag.setLength(0);
		_tag.append(_entity.getTag());
		_tag.append(": GameItemCheckBehavior");
	}
}
