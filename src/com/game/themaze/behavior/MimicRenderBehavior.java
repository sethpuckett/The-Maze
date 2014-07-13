package com.game.themaze.behavior;

import com.game.loblib.behavior.Behavior;
import com.game.loblib.entity.GameEntity;
import com.game.loblib.utility.Manager;
import com.game.loblib.utility.area.Area;
import com.game.loblib.utility.area.Rectangle;
public class MimicRenderBehavior extends Behavior {

	protected GameEntity _mimicEntity;
	protected int _layer;
	protected Rectangle _positionBackup = new Rectangle();
	protected boolean _customAlphaEnabled;
	protected float _customAlpha;
	
	public MimicRenderBehavior(GameEntity mimicEntity, int layer) {
		_mimicEntity = mimicEntity;
		_layer = layer;
		_type = TMBehaviorType.MIMIC_RENDER;
	}
	
	public void enableCustomAlpha(float alpha) {
		_customAlpha = alpha;
		_customAlphaEnabled = true;
	}
	
	public void disableCustomAlpha() {
		_customAlphaEnabled = false;
	}
	
	@Override
	public void onUpdate(float updateRatio) {
		float backupAlpha = _mimicEntity.Attributes.Sprite.Alpha;
		Area.sync(_positionBackup, _mimicEntity.Attributes.Sprite.Area);
		Area.sync(_mimicEntity.Attributes.Sprite.Area, _entity.Attributes.Area);
		if (_customAlphaEnabled)
			_mimicEntity.Attributes.Sprite.Alpha = _customAlpha;
		if (_mimicEntity.Attributes.Sprite.isValid())
			Manager.Sprite.draw(_mimicEntity.Attributes.Sprite, _entity.ScreenLevel, _layer);	
		Area.sync(_mimicEntity.Attributes.Sprite.Area, _positionBackup);
		if (_customAlphaEnabled)
			_mimicEntity.Attributes.Sprite.Alpha = backupAlpha;
	}
	
	@Override
	protected void setTag() {
		_tag.setLength(0);
		_tag.append(_entity.getTag());
		_tag.append(": MimicRenderBehavior");
	}
}
