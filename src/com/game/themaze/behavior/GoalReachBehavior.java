package com.game.themaze.behavior;

import com.game.loblib.collision.ICollisionSender;
import com.game.loblib.utility.Manager;
import com.game.themaze.collision.TMCollisionLayer;
import com.game.themaze.messaging.TMMessageType;

public class GoalReachBehavior extends CollisionCheckBehavior {

	public GoalReachBehavior() {
		super(TMCollisionLayer.GOAL);
		_type = TMBehaviorType.GOAL_REACH;
	}
	
	@Override
	protected void onCollision(ICollisionSender sender) {
		Manager.Message.sendMessage(TMMessageType.GOAL_REACHED, _entity);
	}

	@Override
	protected void setTag() {
		_tag.setLength(0);
		_tag.append(_entity.getTag());
		_tag.append(": GoalReachBehavior");
	}

}
