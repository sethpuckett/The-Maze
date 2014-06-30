package com.game.themaze.behavior;

import com.game.loblib.graphics.Sprite;
import com.game.loblib.utility.Direction;
import com.game.loblib.utility.MathHelper;
import com.game.loblib.utility.area.Vertex;

public class PlayerRenderBehavior extends RenderBehavior {
	
	protected int _currentAnimation = 0;
	protected boolean _animating = false;
	
	public PlayerRenderBehavior(int layer) {
		super(layer);
		_type = TMBehaviorType.PLAYER_RENDER;
	}

	@Override
	protected void setTag() {
		_tag.setLength(0);
		_tag.append(_entity.getTag());
		_tag.append(": PlayerRenderBehavior");
	}
	
	public void setDirection(int direction) {
		Sprite sprite = _entity.Attributes.Sprite;
		
		switch (direction) {
		case Direction.UP:
			if (_currentAnimation!= 0)
				sprite.setAnimation(_currentAnimation = 0);
			break;
		case Direction.RIGHT_UP:
			if (_currentAnimation!= 1)
				sprite.setAnimation(_currentAnimation = 1);
			break;
		case Direction.RIGHT:
			if (_currentAnimation!= 2)
				sprite.setAnimation(_currentAnimation = 2);
			break;
		case Direction.RIGHT_DOWN:
			if (_currentAnimation!= 3)
				sprite.setAnimation(_currentAnimation = 3);
			break;
		case Direction.DOWN:
			if (_currentAnimation!= 4)
				sprite.setAnimation(_currentAnimation = 4);
			break;
		case Direction.LEFT_DOWN:
			if (_currentAnimation!= 5)
				sprite.setAnimation(_currentAnimation = 5);
			break;
		case Direction.LEFT:
			if (_currentAnimation!= 6)
				sprite.setAnimation(_currentAnimation = 6);
			break;
		case Direction.LEFT_UP:
			if (_currentAnimation!= 7)
				sprite.setAnimation(_currentAnimation = 7);
			break;
		}
	}
	
	@Override
	public void onUpdate(float updateRatio) {
		super.onUpdate(updateRatio);
		
		Vertex direction = _entity.Attributes.Direction;
		Sprite sprite = _entity.Attributes.Sprite;
		
		if (direction.Undefined) {
			sprite.setFrame(0);
			sprite.stopAnimation();
			_animating = false;
		}
		else if (MathHelper.floatEq(direction.X, 0)) {
			if (direction.Y > 0) {
				if (_currentAnimation != 2)
					sprite.setAnimation(_currentAnimation = 2);
			}
			else {
				if (_currentAnimation != 0) // 6
					sprite.setAnimation(_currentAnimation = 0); //6
			}
			if (!_animating) {
				_animating = true;
				sprite.animate();
			}
		}
		else if (MathHelper.floatEq(direction.Y, 0)) {
			if (direction.X > 0) {
				if (_currentAnimation != 0)
					sprite.setAnimation(_currentAnimation = 0);
			}
			else {
				if (_currentAnimation != 4)
					sprite.setAnimation(_currentAnimation = 4);
			}
			if (!_animating) {
				_animating = true;
				sprite.animate();
			}
		}
		else
		{
			float angle = MathHelper.angle(direction);
			if (angle < MathHelper.PI / 8 || angle >= Math.PI * 15 / 8)
				setDirection(Direction.RIGHT);
			else if (angle < MathHelper.PI * 3 / 8)
				setDirection(Direction.RIGHT_UP);
			else if (angle < MathHelper.PI * 5 / 8)
				setDirection(Direction.UP);
			else if (angle < MathHelper.PI * 7 / 8)
				setDirection(Direction.LEFT_UP);
			else if (angle < MathHelper.PI * 9 / 8)
				setDirection(Direction.LEFT);
			else if (angle < MathHelper.PI * 11 / 8)
				setDirection(Direction.LEFT_DOWN);
			else if (angle < MathHelper.PI * 13 / 8)
				setDirection(Direction.DOWN);
			else
				setDirection(Direction.RIGHT_DOWN);
			
			if (!_animating) {
				_animating = true;
				sprite.animate();
			}
		}
	}
}
