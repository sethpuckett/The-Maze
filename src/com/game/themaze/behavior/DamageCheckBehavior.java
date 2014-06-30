package com.game.themaze.behavior;

import com.game.loblib.collision.ICollisionSender;
import com.game.loblib.utility.Manager;
import com.game.themaze.collision.TMCollisionLayer;
import com.game.themaze.messaging.TMMessageType;

public class DamageCheckBehavior extends CollisionCheckBehavior {

	public DamageCheckBehavior() {
		super(TMCollisionLayer.DAMAGE);
		_type = TMBehaviorType.DAMAGE_CHECK;
	}
	
	@Override
	protected void onCollision(ICollisionSender sender) {
		Manager.Message.sendMessage(TMMessageType.DAMAGE, _entity);
	}

	
	@Override
	protected void setTag() {
		_tag.setLength(0);
		_tag.append(_entity.getTag());
		_tag.append(": GoalReachBehavior");
	}

}
