package com.game.themaze.behavior;

import com.game.loblib.behavior.Behavior;
import com.game.loblib.graphics.Sprite;
import com.game.loblib.utility.Manager;
import com.game.loblib.utility.MathHelper;
import com.game.loblib.utility.android.FixedSizeArray;
import com.game.loblib.utility.area.Area;
import com.game.loblib.utility.area.Vertex;

public class WallRenderBehavior extends Behavior {

	protected FixedSizeArray<Sprite> _sprites;
	protected int _layer;
	protected boolean _horizontal;
	protected int _firstImage;
	protected int _lastImage;
	protected int _midImage;
	protected int _cellWidth;
	protected Vertex _prevPosition = new Vertex();

	public WallRenderBehavior(boolean horizontal, int layer, int image, int cellWidth) {
		_type = TMBehaviorType.WALL_RENDER;
		_layer = layer;
		_horizontal = horizontal;
		_firstImage = image;
		_lastImage = image;
		_midImage = image;
		_cellWidth = cellWidth;
	}
	
	public WallRenderBehavior(boolean horizontal, int layer, int firstImage, int lastImage, int midImage, int cellWidth) {
		_type = TMBehaviorType.WALL_RENDER;
		_layer = layer;
		_horizontal = horizontal;
		_firstImage = firstImage;
		_lastImage = lastImage;
		_midImage = midImage;
		_cellWidth = cellWidth;
	}

	public FixedSizeArray<Sprite> getSprites() {
		return _sprites;
	}

	public void setAlpha(float alpha) {
		int count = _sprites.getCount();
		for (int i = 0; i < count; i++)
			_sprites.get(i).Alpha = alpha;
	}

	@Override
	public void init() {
		Area.sync(_prevPosition, _entity.Attributes.Area.Position);
		Sprite initSprite = Manager.Sprite.make(_firstImage);
		if (_horizontal) {
			float heightRatio = (float)_cellWidth / initSprite.getFrameHeight();
			// check if only 1 sprite is needed
//			if (_entity.Attributes.Area.Size.X <= (initSprite.getFrameWidth() * heightRatio)) {
//				_sprites = new FixedSizeArray<Sprite>(1);
//				Area.sync(initSprite.Area, _entity.Attributes.Area);
//				initSprite.Frames = (int[]) initSprite.Frames.clone();
//				initSprite.Frames[2] = (int)(_entity.Attributes.Area.Size.X / heightRatio);
//				Area.sync(initSprite.Area, _entity.Attributes.Area);
//				_sprites.add(initSprite);
//			}
//			else {
				Sprite midSprite = Manager.Sprite.make(_midImage);
				Sprite lastSprite = Manager.Sprite.make(_lastImage);
				
				float initFrameWidth = initSprite.getFrameWidth() * heightRatio;
				float midFrameWidth = midSprite.getFrameWidth() * heightRatio;
				float lastFrameWidth = lastSprite.getFrameWidth() * heightRatio;
				
				int spriteCount = 0;
				if (initFrameWidth + lastFrameWidth >= _entity.Attributes.Area.Size.X)
					spriteCount = 2;
				else {
					float midSectionLength = _entity.Attributes.Area.Size.X - initFrameWidth - lastFrameWidth;
					spriteCount = (int)(midSectionLength / midFrameWidth) + (midSectionLength % midFrameWidth > 0 ? 1 : 0) + 2;
				}
				
				Manager.Sprite.release(midSprite);
				Manager.Sprite.release(lastSprite);
				
				float lengthUsed = 0;
				
				_sprites = new FixedSizeArray<Sprite>(spriteCount);
				// add initial sprite
				float initLengthUsed = Math.min(_entity.Attributes.Area.Size.X - _cellWidth, initFrameWidth);
				initSprite.Area.setSize(initLengthUsed, _cellWidth);
				initSprite.Area.setPosition(_entity.Attributes.Area.Position.X, _entity.Attributes.Area.Position.Y);
				initSprite.Frames = (int[]) initSprite.Frames.clone();
				initSprite.Frames[2] = (int)(initLengthUsed / heightRatio);
				_sprites.add(initSprite);
				lengthUsed += initLengthUsed;


				for (int i = 1; i < spriteCount; i++) {
					// mid sprite
					if (i < spriteCount - 2) {
						Sprite sprite = Manager.Sprite.make(_midImage);
						sprite.Area.setSize(midFrameWidth, _cellWidth);
						sprite.Area.setPosition(_entity.Attributes.Area.Position.X + lengthUsed, _entity.Attributes.Area.Position.Y);
						lengthUsed += midFrameWidth;
						_sprites.add(sprite);
					}
					// final mid-sprite, cut short if necessary to leave room for last sprite
					else if (i == spriteCount - 2) {
						float remainingLength = _entity.Attributes.Area.Size.X - lengthUsed - lastFrameWidth;
						Sprite sprite = Manager.Sprite.make(_midImage);
						sprite.Area.setSize(remainingLength, _cellWidth);
						sprite.Area.setPosition(_entity.Attributes.Area.Position.X + lengthUsed, _entity.Attributes.Area.Position.Y);
						sprite.Frames = (int[]) sprite.Frames.clone();
						sprite.Frames[2] = (int)(remainingLength / heightRatio);
						lengthUsed += remainingLength;
						_sprites.add(sprite);
					}
					// last sprite
					else if (lengthUsed < _entity.Attributes.Area.Size.X) {
						float remainingLength = _entity.Attributes.Area.Size.X - lengthUsed;
						Sprite sprite = Manager.Sprite.make(_lastImage);
						sprite.Area.setSize(remainingLength, _cellWidth);
						sprite.Area.setPosition(_entity.Attributes.Area.Position.X + lengthUsed, _entity.Attributes.Area.Position.Y);
						sprite.Frames = (int[]) sprite.Frames.clone();
						sprite.Frames[0] = (int) (sprite.Frames[2] - (remainingLength / heightRatio));
						sprite.Frames[2] = (int)(remainingLength / heightRatio);
						_sprites.add(sprite);
					}
				//}
			}
		}
		else {
			float widthRatio = (float)_cellWidth / initSprite.getFrameWidth();
//			if (_entity.Attributes.Area.Size.Y <= (initSprite.getFrameHeight() * widthRatio)) {
//				_sprites = new FixedSizeArray<Sprite>(1);
//				Area.sync(initSprite.Area, _entity.Attributes.Area);
//				initSprite.Frames = (int[]) initSprite.Frames.clone();
//				initSprite.Frames[1] = (int)(_entity.Attributes.Area.Size.Y / widthRatio);
//				initSprite.Frames[3] = -(int)(_entity.Attributes.Area.Size.Y / widthRatio);
//				Area.sync(initSprite.Area, _entity.Attributes.Area);
//				_sprites.add(initSprite);
//			}
//			else {
				Sprite midSprite = Manager.Sprite.make(_midImage);
				Sprite lastSprite = Manager.Sprite.make(_lastImage);
				
				float initFrameHeight = initSprite.getFrameHeight() * widthRatio;
				float midFrameHeight = midSprite.getFrameHeight() * widthRatio;
				float lastFrameHeight = lastSprite.getFrameHeight() * widthRatio;
				
				int spriteCount = 0;
				if (initFrameHeight + lastFrameHeight >= _entity.Attributes.Area.Size.Y)
					spriteCount = 2;
				else {
					float midSectionLength = _entity.Attributes.Area.Size.Y - initFrameHeight - lastFrameHeight;
					spriteCount = (int)(midSectionLength / midFrameHeight) + (midSectionLength % midFrameHeight > 0 ? 1 : 0) + 2;
				}
				
				Manager.Sprite.release(midSprite);
				Manager.Sprite.release(lastSprite);
				
				float lengthUsed = 0;
				
				_sprites = new FixedSizeArray<Sprite>(spriteCount);
				
				float initLengthUsed = Math.min(_entity.Attributes.Area.Size.Y - _cellWidth, initFrameHeight);
				// add initial sprite
				initSprite.Area.setSize(_cellWidth, initLengthUsed);
				initSprite.Area.setPosition(_entity.Attributes.Area.Position.X, _entity.Attributes.Area.Position.Y);
				initSprite.Frames = (int[]) initSprite.Frames.clone();
				initSprite.Frames[3] = -(int)(initLengthUsed / widthRatio);
				_sprites.add(initSprite);
				lengthUsed += initLengthUsed;
				
				for (int i = 1; i < spriteCount; i++) {
					// mid sprite
					if (i < spriteCount - 2) {
						Sprite sprite = Manager.Sprite.make(_midImage);
						sprite.Area.setSize(_cellWidth, midFrameHeight);
						sprite.Area.setPosition(_entity.Attributes.Area.Position.X, _entity.Attributes.Area.Position.Y + lengthUsed);
						_sprites.add(sprite);
						lengthUsed += midFrameHeight;
					}
					// final mid-sprite, cut short if necessary to leave room for last sprite
					else if (i == spriteCount - 2) {
						float remainingLength = _entity.Attributes.Area.Size.Y - lengthUsed - lastFrameHeight;
						Sprite sprite = Manager.Sprite.make(_midImage);
						sprite.Area.setSize(_cellWidth, remainingLength);
						sprite.Area.setPosition(_entity.Attributes.Area.Position.X, _entity.Attributes.Area.Position.Y + lengthUsed);
						sprite.Frames = (int[]) sprite.Frames.clone();
						sprite.Frames[3] = -(int)(remainingLength / widthRatio);
						_sprites.add(sprite);
						lengthUsed += remainingLength;
					}
					else if (lengthUsed < _entity.Attributes.Area.Size.Y) {
						float remainingLength = _entity.Attributes.Area.Size.Y - lengthUsed;
						Sprite sprite = Manager.Sprite.make(_lastImage);
						sprite.Area.setSize(_cellWidth, remainingLength);
						sprite.Area.setPosition(_entity.Attributes.Area.Position.X, _entity.Attributes.Area.Position.Y + lengthUsed);
						sprite.Frames = (int[]) sprite.Frames.clone();
						sprite.Frames[1] = (int)((remainingLength / widthRatio));
						sprite.Frames[3] = -(int)((remainingLength / widthRatio));
						_sprites.add(sprite);
					}
				}
			//}
		}
	}

	@Override
	protected void onUpdate(float updateRatio) {
		float xChange = _entity.Attributes.Area.Position.X - _prevPosition.X;
		float yChange = _entity.Attributes.Area.Position.Y - _prevPosition.Y;
		boolean locationChange = !MathHelper.floatEqZero(xChange) || !MathHelper.floatEqZero(yChange);
		if (locationChange)
			Area.sync(_prevPosition, _entity.Attributes.Area.Position);
		
		int count = _sprites.getCount();
		for (int i = 0; i < count; i++) {
			if (locationChange)
				_sprites.get(i).Area.changePosition(xChange, yChange);
			Manager.Sprite.draw(_sprites.get(i), _layer);
		}
	}

	@Override
	protected void onDestroy() {
		int count = _sprites.getCount();
		for (int i = 0; i < count; i++) {
			Manager.Sprite.release(_sprites.get(i));
		}
		_sprites = null;
	}

	@Override
	protected void setTag() {
		// TODO Auto-generated method stub

	}

}
