package com.game.themaze.behavior;

import com.game.loblib.behavior.Behavior;
import com.game.loblib.collision.ICollisionSender;
import com.game.loblib.exception.UndefinedVertexException;
import com.game.loblib.utility.Logger;
import com.game.loblib.utility.Manager;
import com.game.loblib.utility.area.Area;
import com.game.loblib.utility.area.IArea;
import com.game.loblib.utility.area.Vertex;
import com.game.themaze.collision.TMCollisionLayer;

public class PlayerMoveBehavior extends Behavior {

	protected long _collisionLayers = TMCollisionLayer.MAIN_BLOCK;
	
	public PlayerMoveBehavior() {
		_type = TMBehaviorType.PLAYER_MOVE;
	}
	
	@Override
	protected void onUpdate(float updateRatio) {
		try {
			// allocate and set variables
			Vertex direction = Manager.Vertex.allocate();
			Vertex destination = Manager.Vertex.allocate();
			float speed;
			Vertex center = Manager.Vertex.allocate();
			Vertex velocity = Manager.Vertex.allocate();
			Area.sync(direction, _entity.Attributes.Direction);
			Area.sync(destination, _entity.Attributes.Destination);
			speed = _entity.Attributes.Speed;
			_entity.Attributes.Area.getCenter(center);
			
			// update
			updatePosition(center, direction, speed, destination, updateRatio);
			Vertex.normalize(center, destination, direction);
			
			// set entity attributes
			_entity.Attributes.Area.setCenter(center);
			Area.sync(_entity.Attributes.Direction, direction);
			
			// release allocated objects
			Manager.Vertex.release(direction);
			Manager.Vertex.release(destination);
			Manager.Vertex.release(center);
			Manager.Vertex.release(velocity);
		}
		catch (UndefinedVertexException e) {
			Logger.e(_tag, "Undefined Vertex", e);
		}
	}
	
	@Override
	protected void setTag() {
		_tag.setLength(0);
		_tag.append(_entity.getTag());
		_tag.append(": PlayerMoveBehavior");
	}
	
	protected void updatePosition(Vertex center, Vertex direction, float speed, Vertex destination, float  multiplier) throws UndefinedVertexException {
 		// do nothing if resting on destination
		if (destination.equals(center)&& direction.Undefined)
			return;
		if (!direction.Undefined) {
			// allocate variables
			Vertex potentialCenter = Manager.Vertex.allocate();
			Vertex totalMovement = Manager.Vertex.allocate();
			Area.sync(potentialCenter, center);
			
			setPotentialCenter(center, direction, speed, destination, multiplier, totalMovement, potentialCenter);
			// set position if no collision
			ICollisionSender sender = Manager.Collision.getCollision(potentialCenter, _collisionLayers);
			if ((totalMovement.X == 0 && totalMovement.Y == 0) || sender == null)
				Area.sync(center, potentialCenter);
			// otherwise find best position w/ no collision
			else {
				Vertex.sub(potentialCenter, totalMovement);
				setNoCollisionPotentialCenter(potentialCenter, totalMovement);
				Area.sync(center, potentialCenter);
			}
			
			// free allocations
			Manager.Vertex.release(potentialCenter);
			Manager.Vertex.release(totalMovement);
		}
	}
	
	protected void setPotentialCenter(Vertex center, Vertex direction, float speed, Vertex destination, float multiplier, Vertex totalMovement, Vertex potentialCenter) throws UndefinedVertexException {
		if (!destination.Undefined && Vertex.distanceSquared(center, destination) < (_entity.Attributes.Speed * 2f)) {
			Vertex.sub(destination, center, totalMovement);		// get total movement
			Area.sync(potentialCenter, destination);			// set potential center
		}
		else {
			Vertex.mul(direction, speed, totalMovement); 		// get raw movement
			Vertex.mul(totalMovement, multiplier);				// adjust w/ multiplier
			Vertex.add(center, totalMovement, potentialCenter);	// set potential center
		}
	}
	
	protected void setNoCollisionPotentialCenter(Vertex potentialCenter, Vertex totalMovement) {		
		boolean xHit = true;
		
		// check X movement
		potentialCenter.X += totalMovement.X;
		ICollisionSender sender = Manager.Collision.getCollision(potentialCenter, _collisionLayers);
		if (sender != null) {
			IArea area = sender.getArea();
			if (totalMovement.X > 0)
				potentialCenter.X = area.getPositionX() - 1;
			else
				potentialCenter.X = area.getPositionX() + area.getWidth() + 1;
		}
		else {
			// if X movement did not cause collision reset for now
			// we want to move along axis that causes col. first
			potentialCenter.X -= totalMovement.X;
			xHit = false;
		}
		
		// check Y movement
		potentialCenter.Y += totalMovement.Y;
		sender = Manager.Collision.getCollision(potentialCenter, _collisionLayers);
		if (sender != null) {
			IArea area = sender.getArea();
			if (totalMovement.Y > 0)
				potentialCenter.Y = area.getPositionY() - 1;
			else
				potentialCenter.Y = area.getPositionY() + area.getHeight() + 1;
		}
		
		// recheck X movement
		if (!xHit) {
			potentialCenter.X += totalMovement.X;
			sender = Manager.Collision.getCollision(potentialCenter, _collisionLayers);
			if (sender != null) {
				IArea area = sender.getArea();
				if (totalMovement.X > 0)
					potentialCenter.X = area.getPositionX() - 1;
				else
					potentialCenter.X = area.getPositionX() + area.getWidth() + 1;
			}
		}
	}

}
