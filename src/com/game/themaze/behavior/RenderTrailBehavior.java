package com.game.themaze.behavior;

import com.game.loblib.behavior.Behavior;
import com.game.loblib.graphics.Sprite;
import com.game.loblib.utility.Manager;
import com.game.loblib.utility.android.FixedSizeArray;
import com.game.loblib.utility.area.Area;
import com.game.loblib.utility.area.Vertex;
import com.game.themaze.graphics.TMImage;

public class RenderTrailBehavior extends Behavior {

	protected FixedSizeArray<Sprite> _spriteTail = new FixedSizeArray<Sprite>(64);
	protected FixedSizeArray<Sprite> _toDelete = new FixedSizeArray<Sprite>(64);
	protected final Vertex _lastPosition = new Vertex();
	protected float _shrinkRate = 2.5f;
	protected float _fadeRate = .05f;
	protected float _alphaCutoff = .01f;
	protected float _sizeCutoff = 1f;
	protected int _layer;
	
	public RenderTrailBehavior(int layer) {
		_type = TMBehaviorType.RENDER_TRAIL;
		_layer = layer;
	}
	
	@Override
	protected void onUpdate(float updateRatio) {
		if (!_entity.Attributes.Area.Position.equals(_lastPosition)) {
			Sprite sprite = Manager.Sprite.make(TMImage.PLAYER);
			sprite.Area.setSize(_entity.Attributes.Area.Size);
			sprite.Area.setPosition(_lastPosition.X, _lastPosition.Y);
			sprite.Alpha = .5f;
			_spriteTail.add(sprite);
			Area.sync(_lastPosition, _entity.Attributes.Area.Position);
		}
		
		int count = _spriteTail.getCount();
		for (int i = 0; i < count; i++) {
			final Sprite sprite = _spriteTail.get(i);
			if (sprite.Area.getWidth() < _sizeCutoff || sprite.Alpha < _alphaCutoff)
				_toDelete.add(sprite);
			else {
				sprite.Area.changeSize(-_shrinkRate * updateRatio, -_shrinkRate * updateRatio);
				sprite.Alpha -= (_fadeRate * updateRatio);
				Manager.Sprite.draw(sprite, _layer);
			}
		}
		int delCount = _toDelete.getCount();
		if (delCount > 0) {
			for (int j = 0; j < delCount; j++)
				Manager.Sprite.release(_toDelete.get(j));
			_spriteTail.removeAll(_toDelete, false);
			_toDelete.clear();
		}
	}

	@Override
	protected void onDisable() {
		int count = _spriteTail.getCount();
		if (count > 0) {
			for (int j = 0; j < count; j++)
				Manager.Sprite.release(_spriteTail.get(j));
		}
		_spriteTail.clear();
		_toDelete.clear();
	}

	@Override
	protected void onDestroy() {
		int count = _spriteTail.getCount();
		if (count > 0) {
			for (int j = 0; j < count; j++)
				Manager.Sprite.release(_spriteTail.get(j));
		}
		_spriteTail.clear();
		_toDelete.clear();
	}
	
	@Override
	protected void setTag() {
		_tag.setLength(0);
		_tag.append(_entity.getTag());
		_tag.append(": RenderTrailBehavior");
	}

}
