package com.game.themaze.screen;

import com.game.loblib.messaging.Message;
import com.game.loblib.screen.Screen;
import com.game.loblib.sound.Sound;

public class EndGameScreen extends Screen {

	public EndGameScreen() {
		_type = TMScreenType.END_GAME;
		_screenMusic = Sound.CONTINUE_MUSIC;
	}
	
	@Override
	public void onHandleMessage(Message message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onInit(Object input) {
		//TODO: testing
		_screenData.setCode(TMScreenCode.TRANSITION);
		_screenData.setActionScreen(TMScreenType.TITLE_SCREEN);
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUnpause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onActiveUpdate(float updateRatio) {
		// TODO Auto-generated method stub
		
	}

}
