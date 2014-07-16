package com.game.themaze.level.concrete;

import com.game.loblib.entity.GameEntity;
import com.game.loblib.graphics.Sprite;
import com.game.loblib.messaging.Message;
import com.game.loblib.messaging.MessageType;
import com.game.loblib.utility.FadeType;
import com.game.loblib.utility.Global;
import com.game.loblib.utility.Manager;
import com.game.loblib.utility.TimerType;
import com.game.loblib.utility.android.FixedSizeArray;
import com.game.the_maze.R;
import com.game.themaze.behavior.FadeBehavior;
import com.game.themaze.behavior.FadeChainBehavior;
import com.game.themaze.behavior.TMBehaviorType;
import com.game.themaze.entity.EntityHelper;
import com.game.themaze.graphics.TMImage;
import com.game.themaze.graphics.TMSpriteLayer;
import com.game.themaze.level.Level;
import com.game.themaze.utility.TMManager;

public class Level19 extends Level {
	
	protected GameEntity _colorFadeChain;
	protected GameEntity _initTimer;
	
	protected GameEntity _red;
	protected GameEntity _blue;
	protected GameEntity _yellow;
	protected FixedSizeArray<GameEntity> _redSpikes;
	protected FixedSizeArray<GameEntity> _blueSpikes;
	protected FixedSizeArray<GameEntity> _yellowSpikes;
	protected boolean _redOff = false;
	protected boolean _blueOff = false;
	protected boolean _yellowOff = false;
	
	public Level19() {
		_settings.ResourceId = R.raw.level_01;
		_settings.CellWidth = (int)(Global.Renderer.Width / 16f);
		_settings.BackgroundImage = TMImage.BACKGROUND_SAND;
	}
	
	@Override
	public void onInit() {
		
		Sprite red = Manager.Sprite.make(TMImage.RED);
		red.Area.setSize(Global.Renderer.Width, Global.Renderer.Height - TMManager.Level.getControlBarHeight());
		red.Area.setPosition(0, TMManager.Level.getControlBarHeight());
		red.UseCamera = false;
		Sprite blue = Manager.Sprite.make(TMImage.BLUE);
		blue.Area.setSize(Global.Renderer.Width, Global.Renderer.Height - TMManager.Level.getControlBarHeight());
		blue.Area.setPosition(0, TMManager.Level.getControlBarHeight());
		blue.UseCamera = false;
		Sprite yellow = Manager.Sprite.make(TMImage.YELLOW);
		yellow.Area.setSize(Global.Renderer.Width, Global.Renderer.Height - TMManager.Level.getControlBarHeight());
		yellow.Area.setPosition(0, TMManager.Level.getControlBarHeight());
		yellow.UseCamera = false;
		
		FadeChainBehavior fcb = new FadeChainBehavior(TMSpriteLayer.UI_LOW, .75f, 750, 750, 1500, 4000, FadeType.CONTINUOUS, 9000f);
		fcb.addSprite(red);
		fcb.addSprite(blue);
		fcb.addSprite(yellow);
		
		_colorFadeChain = new GameEntity();
		_colorFadeChain.addBehavior(fcb);
		
		_entities.add(_colorFadeChain);
		
		_initTimer = EntityHelper.timer(TimerType.SINGLE, 3000f);
		_entities.add(_initTimer);
		
		_redSpikes = new FixedSizeArray<GameEntity>(32);
		_redSpikes.add(EntityHelper.singleSpike(TMImage.SPIKE_RED, 14, 9));
		_redSpikes.get(0).addBehavior(new FadeBehavior(500, 500, true, FadeType.SINGLE));
		_entities.addAll(_redSpikes);
		
		Manager.Message.subscribe(this, MessageType.TIMER_ALARM | MessageType.FADE_STATE_CHANGE);
	}
	
	@Override
	public void onUnpause() {
		super.unpause();
		Manager.Message.subscribe(this, MessageType.TIMER_ALARM | MessageType.FADE_STATE_CHANGE);
	}
	
	@Override
	public void enableBehaviors() {
		super.enableBehaviors();
		
		_colorFadeChain.disableBehaviors();
		
		int redCount = _redSpikes.getCount();
		for (int i = 0; i < redCount; i++)
			_redSpikes.get(i).disableBehaviors(TMBehaviorType.FADE);
		int blueCount = _blueSpikes.getCount();
		for (int i = 0; i < blueCount; i++)
			_blueSpikes.get(i).disableBehaviors(TMBehaviorType.FADE);
		int yellowCount = _yellowSpikes.getCount();
		for (int i = 0; i <yellowCount; i++)
			_yellowSpikes.get(i).disableBehaviors(TMBehaviorType.FADE);
	}

	@Override
	public void handleMessage(Message message) {
		super.handleMessage(message);
		
		if (message.Type == MessageType.TIMER_ALARM) {
			GameEntity timer = message.getData();
			if (timer == _initTimer) {
				_colorFadeChain.enableBehaviors();
			}
		}
		else if (message.Type == MessageType.FADE_STATE_CHANGE) {
			GameEntity chain = message.getData();
			if (chain == _colorFadeChain) {
				if (((FadeChainBehavior)chain.getBehavior(TMBehaviorType.FADE_CHAIN)).getState(0) == (_redOff ? FadeChainBehavior.STATE_FADEOUT : FadeChainBehavior.STATE_FADEIN)) {
					int redCount = _redSpikes.getCount();
					for (int i = 0; i < redCount; i++) {
						if (_redOff)
							((FadeBehavior)_redSpikes.get(i).getBehavior(TMBehaviorType.FADE)).fadeIn();
						else
							((FadeBehavior)_redSpikes.get(i).getBehavior(TMBehaviorType.FADE)).fadeOut();
					}
					_redOff = !_redOff;
				}
				else if (((FadeChainBehavior)chain.getBehavior(TMBehaviorType.FADE_CHAIN)).getState(1) == (_blueOff ? FadeChainBehavior.STATE_FADEOUT : FadeChainBehavior.STATE_FADEIN)) {
					int blueCount = _blueSpikes.getCount();
					for (int i = 0; i < blueCount; i++) {
						if (_blueOff)
							((FadeBehavior)_blueSpikes.get(i).getBehavior(TMBehaviorType.FADE)).fadeIn();
						else
							((FadeBehavior)_blueSpikes.get(i).getBehavior(TMBehaviorType.FADE)).fadeOut();
					}
					_blueOff = !_blueOff;
				}
				else if (((FadeChainBehavior)chain.getBehavior(TMBehaviorType.FADE_CHAIN)).getState(2) == (_yellowOff ? FadeChainBehavior.STATE_FADEOUT : FadeChainBehavior.STATE_FADEIN)) {
					int yellowCount = _yellowSpikes.getCount();
					for (int i = 0; i < yellowCount; i++) {
						if (_yellowOff)
							((FadeBehavior)_yellowSpikes.get(i).getBehavior(TMBehaviorType.FADE)).fadeIn();
						else
							((FadeBehavior)_yellowSpikes.get(i).getBehavior(TMBehaviorType.FADE)).fadeOut();
					}
					_yellowOff = !_yellowOff;
				}
			}
		}
	}
}
