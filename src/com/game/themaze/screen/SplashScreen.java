package com.game.themaze.screen;

import com.game.loblib.screen.Screen;
import com.game.loblib.utility.Global;
import com.game.themaze.entity.EntityHelper;
import com.game.themaze.graphics.TMImage;
import com.game.themaze.graphics.TMSpriteLayer;

public class SplashScreen extends Screen {
	
	protected float _elapsedTime = 0;
	
	public SplashScreen() {
		_type = TMScreenType.SPLASH_SCREEN;
	}
	
	@Override
	public void onActiveUpdate(float updateRatio) {
		_elapsedTime += (updateRatio / 60f);
		if (_elapsedTime > 2f) {
			_screenData.setCode(TMScreenCode.TRANSITION);
			_screenData.setActionScreen(TMScreenType.TITLE_SCREEN);
		}
	}
	
	@Override
	public void onInit(Object input) {
		_entities.add(EntityHelper.graphic(TMImage.SPLASH_LOGO, TMSpriteLayer.UI_LOW, false, Global.Renderer.Width * .75f,
				Global.Renderer.Width * .75f, true, Global.Renderer.Width / 2, Global.Renderer.Height / 2));
		_entities.add(EntityHelper.graphic(TMImage.WHITE, TMSpriteLayer.BACKGROUND1, false, 
				Global.Renderer.Width, Global.Renderer.Height, false, 0, 0));
	}

	@Override
	public void onPause() {
		// Nothing to do
	}

	@Override
	public void onUnpause() {
		// Nothing to do
	}

	@Override
	public void onClose() {
		// Nothing to do
	}
}
