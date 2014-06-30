package com.game.themaze.behavior;

import com.game.loblib.behavior.Behavior;
import com.game.loblib.entity.GameEntity;
import com.game.loblib.utility.Direction;
import com.game.loblib.utility.Global;
import com.game.loblib.utility.Logger;
import com.game.loblib.utility.Manager;
import com.game.loblib.utility.area.Intersection;
import com.game.loblib.utility.area.RectangleCollision;
import com.game.loblib.utility.area.Vertex;
import com.game.the_maze.R;
import com.game.themaze.graphics.TMSpriteLayer;
import com.game.themaze.utility.TMManager;

public class GoalIndicatorBehavior extends Behavior {

	protected Vertex _startCenter = new Vertex();
	protected Vertex _goalCenter = new Vertex();
	protected RectangleCollision _collision = new RectangleCollision();
	
	protected GameEntity _goal;
	
	public GoalIndicatorBehavior(GameEntity goal) {
		_type = TMBehaviorType.GOAL_INDICATOR;
		_goal = goal;
	}
	
	@Override
	public void init() {
		if (_entity.Attributes.Sprite.Texture.ResourceId != R.drawable.goal_indicator)
			Logger.e(_tag, "goal indicator behavior expects goal indicator sprite");
	}
	
	@Override
	protected void onUpdate(float updateRatio) {
		Global.Camera.CameraArea.getCenter(_startCenter);
		_goal.Attributes.Area.getCenter(_goalCenter);
		float cellWidth = TMManager.Level.getCellWidth();
		
		if (Intersection.check(_startCenter, _goalCenter, Global.Camera.CameraArea, _collision)) {
			switch (_collision.CollisionDirection1) {
			case Direction.LEFT:
				_entity.Attributes.Sprite.Area.setSize(cellWidth / 2f, cellWidth);
				_entity.Attributes.Sprite.setFrame(0);
				break;
			case Direction.RIGHT:
				_entity.Attributes.Sprite.Area.setSize(cellWidth / 2f, cellWidth);
				_entity.Attributes.Sprite.setFrame(1);
				break;
			case Direction.DOWN:
				_entity.Attributes.Sprite.Area.setSize(cellWidth, cellWidth / 2f);
				_entity.Attributes.Sprite.setFrame(2);
				break;
			case Direction.UP:
				_entity.Attributes.Sprite.Area.setSize(cellWidth, cellWidth / 2f);
				_entity.Attributes.Sprite.setFrame(3);
				break;
			}
			_entity.Attributes.Sprite.Area.setCenter(_collision.Collision1);
			Manager.Sprite.draw(_entity.Attributes.Sprite, TMSpriteLayer.FOREGROUND);
		}
	}
	
	@Override
	protected void setTag() {
		_tag.setLength(0);
		_tag.append(_entity.getTag());
		_tag.append(": GoalIndicatorBehavior");
	}

}
