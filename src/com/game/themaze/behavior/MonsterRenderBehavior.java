package com.game.themaze.behavior;

import com.game.loblib.graphics.Sprite;
import com.game.loblib.utility.Direction;

public class MonsterRenderBehavior extends RenderBehavior {
	protected int _currentAnimation = 0;
	protected int _direction = 0;
	
	public MonsterRenderBehavior(int layer, int direction) {
		super(layer);
		_type = TMBehaviorType.MONSTER_RENDER;
		_direction = direction;
	}

	@Override
	protected void setTag() {
		_tag.setLength(0);
		_tag.append(_entity.getTag());
		_tag.append(": MonsterRenderBehavior");
	}
	
	@Override
	public void init() {
		super.init();
		setDirection(_direction);
		_entity.Attributes.Sprite.animate();
	}
	
	public void setDirection(int direction) {
		Sprite sprite = _entity.Attributes.Sprite;
		_direction = direction;
		
		switch (direction) {
		case Direction.UP:
			if (_currentAnimation!= 3)
				sprite.setAnimation(_currentAnimation = 3);
			break;
		case Direction.RIGHT:
			if (_currentAnimation!= 2)
				sprite.setAnimation(_currentAnimation = 2);
			break;
		case Direction.DOWN:
			if (_currentAnimation!= 0)
				sprite.setAnimation(_currentAnimation = 0);
			break;
		case Direction.LEFT:
			if (_currentAnimation!= 1)
				sprite.setAnimation(_currentAnimation = 1);
			break;
		}
	}
}
