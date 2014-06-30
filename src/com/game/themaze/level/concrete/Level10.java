package com.game.themaze.level.concrete;

import com.game.loblib.entity.GameEntity;
import com.game.loblib.messaging.Message;
import com.game.loblib.messaging.MessageType;
import com.game.loblib.utility.Global;
import com.game.loblib.utility.Logger;
import com.game.loblib.utility.Manager;
import com.game.loblib.utility.TimerType;
import com.game.loblib.utility.TweenType;
import com.game.loblib.utility.android.FixedSizeArray;
import com.game.the_maze.R;
import com.game.themaze.behavior.DoorBehavior;
import com.game.themaze.behavior.RenderBehavior;
import com.game.themaze.behavior.TMBehaviorType;
import com.game.themaze.behavior.TimerBehavior;
import com.game.themaze.behavior.TweenBehavior;
import com.game.themaze.entity.EntityHelper;
import com.game.themaze.gameitem.GameItemType;
import com.game.themaze.graphics.TMImage;
import com.game.themaze.graphics.TMSpriteLayer;
import com.game.themaze.level.Level;
import com.game.themaze.messaging.TMMessageType;
import com.game.themaze.utility.TMManager;

// Speed shoes - spike maze
public class Level10 extends Level {
	
	protected GameEntity _trigger1;
	protected GameEntity _trigger2;
	protected GameEntity _trigger3;
	protected GameEntity _trigger4;
	
	protected GameEntity _doorAnchor1;
	protected GameEntity _doorAnchor2;
	protected GameEntity _doorAnchor3;
	protected GameEntity _doorAnchor4;
	
	protected GameEntity _timer1;
	protected GameEntity _timer2;
	protected GameEntity _timer3;
	protected GameEntity _timer4;
	
	protected GameEntity _clockGrahic;
	protected GameEntity _activeTimer;
	protected int _currentCountdownCheck;
	
	protected FixedSizeArray<GameEntity> _countdownNumbers;
	
	public Level10() {
		_settings.ResourceId = R.raw.level_10;
		_settings.CellWidth = (int)(Global.Renderer.Width / 12f);
		_settings.EnableActionButton = true;
		_settings.Item1 = GameItemType.SPEED_BOOTS;
		_settings.BackgroundImage = TMImage.BACKGROUND_ICE2;
	}
	
	@Override
	public void update() {
		super.update();
		
		if (_activeTimer != null) {
			if (_currentCountdownCheck > 0) {
				if (((TimerBehavior)_activeTimer.getBehavior(TMBehaviorType.TIMER)).getRemainingTime() <= _currentCountdownCheck * 1000) {
					if (_currentCountdownCheck < 10)
						_countdownNumbers.get(_currentCountdownCheck).disableBehaviors();
					_currentCountdownCheck--;
					_countdownNumbers.get(_currentCountdownCheck).Attributes.Sprite.Alpha = 1f;
					_countdownNumbers.get(_currentCountdownCheck).enableBehaviors();
				}
			}
		}
	}
	
	@Override
	public void init() {
		super.init();
		
		_trigger1 = EntityHelper.trigger(11, 43, 4, 4);
		_trigger2 = EntityHelper.trigger(51, 58, 4, 4);
		_trigger3 = EntityHelper.trigger(70, 68, 4, 4);
		_trigger4 = EntityHelper.trigger(76, 24, 4, 4);
		_entities.add(_trigger1);
		_entities.add(_trigger2);
		_entities.add(_trigger3);
		_entities.add(_trigger4);
		_actionTriggers.add(_trigger1);
		_actionTriggers.add(_trigger2);
		_actionTriggers.add(_trigger3);
		_actionTriggers.add(_trigger4);
		
		_entities.add(EntityHelper.levelGraphic(TMImage.TRIGGER_BUTTON_LARGE, TMSpriteLayer.BACKGROUND2, 11, 43, 4, 4));
		_entities.add(EntityHelper.levelGraphic(TMImage.TRIGGER_BUTTON_LARGE, TMSpriteLayer.BACKGROUND2, 51, 58, 4, 4));
		_entities.add(EntityHelper.levelGraphic(TMImage.TRIGGER_BUTTON_LARGE, TMSpriteLayer.BACKGROUND2, 70, 68, 4, 4));
		_entities.add(EntityHelper.levelGraphic(TMImage.TRIGGER_BUTTON_LARGE, TMSpriteLayer.BACKGROUND2, 76, 24, 4, 4));
		
		_countdownNumbers = new FixedSizeArray<GameEntity>(10);
		for (int i = 0; i < 10; i++)
			_countdownNumbers.add(TimerNumber(i));
		_entities.addAll(_countdownNumbers);
		
		_clockGrahic = EntityHelper.graphic(TMImage.CLOCK, TMSpriteLayer.UI_LOW, false, 50, 50,
				false, 8, TMManager.Level.getControlBarHeight() + 8);
		_entities.add(_clockGrahic);
		
		_doorAnchor1 = EntityHelper.doorAnchor(47, 51, 47, 47);
		_entities.add(_doorAnchor1);
		_entities.add(EntityHelper.anchorSpikes(_doorAnchor1, TMImage.SPIKES_U, TMImage.SPIKES_D, TMImage.SPIKES_UD, 4, false, 0, 0));
		
		_doorAnchor2 = EntityHelper.doorAnchor(89, 80, 86, 80);
		_entities.add(_doorAnchor2);
		_entities.add(EntityHelper.anchorSpikes(_doorAnchor2, TMImage.SPIKES_R, TMImage.SPIKES_L, TMImage.SPIKES_LR, 3, true, 0, 0));
		
		_doorAnchor3 = EntityHelper.doorAnchor(81, 5, 81, 2);
		_entities.add(_doorAnchor3);
		_entities.add(EntityHelper.anchorSpikes(_doorAnchor3, TMImage.SPIKES_U, TMImage.SPIKES_D, TMImage.SPIKES_UD, 3, false, 0, 0));
		
		_doorAnchor4 = EntityHelper.doorAnchor(20, 35, 18, 35);
		_entities.add(_doorAnchor4);
		_entities.add(EntityHelper.anchorSpikes(_doorAnchor4, TMImage.SPIKES_SINGLE, TMImage.SPIKES_SINGLE, TMImage.SPIKES_SINGLE, 2, true, 0, 0));
		
		_timer1 = EntityHelper.timer(TimerType.SINGLE, 15000);
		_timer2 = EntityHelper.timer(TimerType.SINGLE, 15000);
		_timer3 = EntityHelper.timer(TimerType.SINGLE, 22000);
		_timer4 = EntityHelper.timer(TimerType.SINGLE, 23000);
		_entities.add(_timer1);
		_entities.add(_timer2);
		_entities.add(_timer3);
		_entities.add(_timer4);
		
		_entities.add(EntityHelper.gameItem(5, 40, GameItemType.SPEED_BOOTS));
		
		Manager.Message.subscribe(this, MessageType.TIMER_ALARM | 
				TMMessageType.DAMAGE);
	}	

	@Override
	public void unpause() {
		super.unpause();
		Manager.Message.subscribe(this, MessageType.TIMER_ALARM | 
				TMMessageType.DAMAGE);
	}
	
	@Override
	public void enableBehaviors() {
		super.enableBehaviors();
		
		int count = _countdownNumbers.getCount();
		for (int i = 0; i < count; i++) {
			GameEntity entity = _countdownNumbers.get(i);
			entity.disableBehaviors(TMBehaviorType.RENDER);
			entity.disableBehaviors(TMBehaviorType.TWEEN);
		}
		
		_clockGrahic.disableBehaviors();
		
		_timer1.disableBehaviors();
		_timer2.disableBehaviors();
		_timer3.disableBehaviors();
		_timer4.disableBehaviors();
	}
	
	@Override
	public void handleMessage(Message message) {
		super.handleMessage(message);
		
		if (message.Type == MessageType.TIMER_ALARM) {
			GameEntity entity = message.getData();
			if (entity == _timer1)
				timeUp(_doorAnchor1);
			else if (entity == _timer2)
				timeUp(_doorAnchor2);
			else if (entity == _timer3)
				timeUp(_doorAnchor3);
			else if (entity == _timer4)
				timeUp(_doorAnchor4);
		}
	}
	
	@Override
	protected void onActionClicked() {
		if (Manager.Trigger.hit(_trigger1))
			activateTunnel(_timer1, _doorAnchor1);
		else if (Manager.Trigger.hit(_trigger2))
			activateTunnel(_timer2, _doorAnchor2);
		else if (Manager.Trigger.hit(_trigger3))
			activateTunnel(_timer3, _doorAnchor3);
		else if (Manager.Trigger.hit(_trigger4))
			activateTunnel(_timer4, _doorAnchor4);
	}
	
	@Override
	protected void enableGameItem(int gameItemType) {
		switch (gameItemType) {
		case GameItemType.SPEED_BOOTS:
			int cellWidth = TMManager.Level.getCellWidth();
			_player.Attributes.Speed = (float)cellWidth/7f;
		default:
			Logger.e(_tag, "Cannot enable; invalid gameItemType");				
		}
	}
	
	protected void activateTunnel(GameEntity timer, GameEntity door) {
		if (timer != _timer1)
			_timer1.disableBehaviors();
		if (timer != _timer2)
			_timer2.disableBehaviors();
		if (timer != _timer3)
			_timer3.disableBehaviors();
		if (timer != _timer4)
			_timer4.disableBehaviors();
		
		((TimerBehavior)timer.getBehavior(TMBehaviorType.TIMER)).init();
		timer.enableBehaviors(TMBehaviorType.TIMER);
		_activeTimer = timer;
		if (_currentCountdownCheck > 0 && _currentCountdownCheck < 10)
			_countdownNumbers.get(_currentCountdownCheck).disableBehaviors();
		_currentCountdownCheck = 10;
		_clockGrahic.enableBehaviors();
		
		((DoorBehavior)door.getBehavior(TMBehaviorType.DOOR_ANCHOR)).open();
		
	}
	
	protected void timeUp(GameEntity door) {
		_activeTimer = null;
		_countdownNumbers.get(0).disableBehaviors();
		_clockGrahic.disableBehaviors();
		((DoorBehavior)door.getBehavior(TMBehaviorType.DOOR_ANCHOR)).close();
	}

	protected GameEntity TimerNumber(int number) {
		GameEntity entity = new GameEntity();
		entity.Attributes.Area.setPosition(64, TMManager.Level.getControlBarHeight() + 10);
		entity.Attributes.Area.MaintainCenter = false;
		entity.Attributes.Area.setSize(50, 50); 
		switch (number) {
		case 0:
			entity.Attributes.Sprite = Manager.Sprite.make(TMImage.TIMER_ZERO);
			break;
		case 1:
			entity.Attributes.Sprite = Manager.Sprite.make(TMImage.TIMER_ONE);
			break;
		case 2:
			entity.Attributes.Sprite = Manager.Sprite.make(TMImage.TIMER_TWO);
			break;
		case 3:
			entity.Attributes.Sprite = Manager.Sprite.make(TMImage.TIMER_THREE);
			break;
		case 4:
			entity.Attributes.Sprite = Manager.Sprite.make(TMImage.TIMER_FOUR);
			break;
		case 5:
			entity.Attributes.Sprite = Manager.Sprite.make(TMImage.TIMER_FIVE);
			break;
		case 6:
			entity.Attributes.Sprite = Manager.Sprite.make(TMImage.TIMER_SIX);
			break;
		case 7:
			entity.Attributes.Sprite = Manager.Sprite.make(TMImage.TIMER_SEVEN);
			break;
		case 8:
			entity.Attributes.Sprite = Manager.Sprite.make(TMImage.TIMER_EIGHT);
			break;
		case 9:
			entity.Attributes.Sprite = Manager.Sprite.make(TMImage.TIMER_NINE);
			break;
		}
		entity.Attributes.Sprite.UseCamera = false;
		entity.addBehavior(new RenderBehavior(TMSpriteLayer.UI_LOW));
		entity.addBehavior(new TweenBehavior(TweenType.ALPHA, -1f, 1100));
		
		return entity;
	}
}
