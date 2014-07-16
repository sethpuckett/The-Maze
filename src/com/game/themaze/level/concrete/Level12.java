package com.game.themaze.level.concrete;

import java.util.Random;

import com.game.loblib.entity.GameEntity;
import com.game.loblib.graphics.Sprite;
import com.game.loblib.messaging.Message;
import com.game.loblib.messaging.MessageType;
import com.game.loblib.utility.Direction;
import com.game.loblib.utility.Global;
import com.game.loblib.utility.Manager;
import com.game.loblib.utility.TimerType;
import com.game.loblib.utility.TweenType;
import com.game.loblib.utility.android.FixedSizeArray;
import com.game.the_maze.R;
import com.game.themaze.behavior.BlinkBehavior;
import com.game.themaze.behavior.MovementModifierBehavior;
import com.game.themaze.behavior.ScrollingTileBehavior;
import com.game.themaze.behavior.TMBehaviorType;
import com.game.themaze.behavior.TimerBehavior;
import com.game.themaze.behavior.TweenBehavior;
import com.game.themaze.entity.EntityHelper;
import com.game.themaze.graphics.TMImage;
import com.game.themaze.graphics.TMSpriteLayer;
import com.game.themaze.level.Level;
import com.game.themaze.messaging.TMMessageType;
import com.game.themaze.utility.TMManager;

// Wind
public class Level12 extends Level {
	
	protected boolean _windStarted;
	protected int _windDirection;
	protected GameEntity _windCountdownTimer;
	protected GameEntity _warningTimer;
	protected GameEntity _windSustainTimer;
	protected GameEntity _warningGraphicLeft;
	protected GameEntity _warningGraphicRight;
	protected GameEntity _warningGraphicUp;
	protected GameEntity _warningGraphicDown;
	protected GameEntity _windStartTrigger;
	protected GameEntity _windGraphic;
	protected GameEntity _fogGraphic;
	
	public Level12() {
		_settings.ResourceId = R.raw.level_08;
		_settings.CellWidth = (int)(Global.Renderer.Width / 12f);
		_settings.BackgroundImage = TMImage.BACKGROUND_GLASS;
	}
	
	@Override
	public void onInit() {
		
		_windStarted = false;

		MovementModifierBehavior mmb = new MovementModifierBehavior();
		mmb.setPower((float)TMManager.Level.getCellWidth() / 9f);
		_player.addBehavior(mmb);
		
		_windStartTrigger = EntityHelper.trigger(49, 5, 4, 3);
		_entities.add(_windStartTrigger);
		
		_windCountdownTimer = EntityHelper.timer(TimerType.SINGLE, 3000f);
		_warningTimer = EntityHelper.timer(TimerType.SINGLE, 1750f);
		_windSustainTimer = EntityHelper.timer(TimerType.SINGLE, 2500f);
		_entities.add(_windCountdownTimer);
		_entities.add(_warningTimer);
		_entities.add(_windSustainTimer);
		
		_warningGraphicLeft = EntityHelper.graphic(TMImage.WARNING_LEFT, TMSpriteLayer.UI_LOW, false, Global.Renderer.Width / 8f,
				Global.Renderer.Height - TMManager.Level.getControlBarHeight(), false, 0, TMManager.Level.getControlBarHeight());
		_warningGraphicLeft.addBehavior(new BlinkBehavior(50f, 200f));
		_warningGraphicRight = EntityHelper.graphic(TMImage.WARNING_RIGHT, TMSpriteLayer.UI_LOW, false, Global.Renderer.Width / 8f,
				Global.Renderer.Height - TMManager.Level.getControlBarHeight(), false, Global.Renderer.Width - Global.Renderer.Width / 8f, TMManager.Level.getControlBarHeight());
		_warningGraphicRight.addBehavior(new BlinkBehavior(50f, 200f));
		_warningGraphicUp = EntityHelper.graphic(TMImage.WARNING_UP, TMSpriteLayer.UI_LOW, false, Global.Renderer.Width,
				Global.Renderer.Width / 8f, false, 0, Global.Renderer.Height - Global.Renderer.Width / 8f);
		_warningGraphicUp.addBehavior(new BlinkBehavior(50f, 200f));
		_warningGraphicDown = EntityHelper.graphic(TMImage.WARNING_DOWN, TMSpriteLayer.UI_LOW, false, Global.Renderer.Width,
				Global.Renderer.Width / 8f, false, 0, TMManager.Level.getControlBarHeight());
		_warningGraphicDown.addBehavior(new BlinkBehavior(50f, 200f));
		_entities.add(_warningGraphicLeft);
		_entities.add(_warningGraphicRight);
		_entities.add(_warningGraphicUp);
		_entities.add(_warningGraphicDown);
		
		_fogGraphic = EntityHelper.scrollingGraphic(TMImage.TRANSPARENT_FOG_LIGHT, TMSpriteLayer.FOREGROUND, Direction.RIGHT, .5f, 0f, Global.Renderer.Width * 1.8f, true);
		FixedSizeArray<Sprite> fgSprites = ((ScrollingTileBehavior)_fogGraphic.getBehavior(TMBehaviorType.SCROLLING_TILE)).getSprites();
		TweenBehavior ftb = new TweenBehavior(TweenType.ALPHA, -.4f, 750, fgSprites);
		_fogGraphic.addBehavior(ftb);
		_entities.add(_fogGraphic);
		
		_windGraphic = EntityHelper.scrollingGraphic(TMImage.TRANSPARENT_FOG_LIGHT, TMSpriteLayer.FOREGROUND, Direction.LEFT, 1.2f, 0f, Global.Renderer.Width * 1.8f, true);
		FixedSizeArray<Sprite> wgSprites = ((ScrollingTileBehavior)_windGraphic.getBehavior(TMBehaviorType.SCROLLING_TILE)).getSprites();
		TweenBehavior tb = new TweenBehavior(TweenType.ALPHA, .5f, 500, wgSprites);
		_windGraphic.addBehavior(tb);
		_entities.add(_windGraphic);
		
		Manager.Message.subscribe(this, MessageType.TIMER_ALARM | MessageType.TRIGGER_HIT | TMMessageType.DAMAGE);
	}
	
	@Override
	public void onUnpause() {
		super.unpause();
		Manager.Message.subscribe(this, MessageType.TIMER_ALARM | MessageType.TRIGGER_HIT  | TMMessageType.DAMAGE);
	}
	
	@Override
	public void enableBehaviors() {
		super.enableBehaviors();
		
		_player.disableBehaviors(TMBehaviorType.MOVEMENT_MODIFIER);
		_warningGraphicLeft.disableBehaviors();
		_warningGraphicRight.disableBehaviors();
		_warningGraphicUp.disableBehaviors();
		_warningGraphicDown.disableBehaviors();
		_windCountdownTimer.disableBehaviors();
		_warningTimer.disableBehaviors();
		_windSustainTimer.disableBehaviors();
		_fogGraphic.disableBehaviors(TMBehaviorType.TWEEN);
		_windGraphic.disableBehaviors(TMBehaviorType.TWEEN);
		((ScrollingTileBehavior)_windGraphic.getBehavior(TMBehaviorType.SCROLLING_TILE)).setAlpha(0f);
		((ScrollingTileBehavior)_fogGraphic.getBehavior(TMBehaviorType.SCROLLING_TILE)).setAlpha(.4f);
	}
	
	@Override
	public void handleMessage(Message message) {
		super.handleMessage(message);
		
		if (message.Type == MessageType.TIMER_ALARM) {
			GameEntity entity = message.getData();
			if (entity == _warningTimer)
				warningFinish();
			else if (entity == _windCountdownTimer)
				windCountdownFinish();
			else if (entity == _windSustainTimer)
				windSustainFinish();
		}
		else if (message.Type == MessageType.TRIGGER_HIT) {
			if (!_windStarted) {
				GameEntity entity = message.getData();
				if (entity == _windStartTrigger) {
					_windStarted = true;
					resetTimers();
					_windCountdownTimer.enableBehaviors();
				}
			}
		}
	}

	protected void resetTimers() {
		Random random = new Random();
		((TimerBehavior)_windCountdownTimer.getBehavior(TMBehaviorType.TIMER)).setTime(1000 + random.nextInt(1500));
		((TimerBehavior)_windSustainTimer.getBehavior(TMBehaviorType.TIMER)).setTime(1500 + random.nextInt(2000));
	}
	
	protected void windCountdownFinish() {
		Random random = new Random();
		_warningTimer.enableBehaviors(TMBehaviorType.TIMER);
		
		_windDirection = 2 + random.nextInt(4);
		
		switch (_windDirection) {
		case Direction.LEFT:
			_warningGraphicRight.enableBehaviors();
			break;
		case Direction.RIGHT:
			_warningGraphicLeft.enableBehaviors();
			break;
		case Direction.UP:
			_warningGraphicDown.enableBehaviors();
			break;
		case Direction.DOWN:
			_warningGraphicUp.enableBehaviors();
			break;
		}
		
		_warningTimer.enableBehaviors();
	}
	
	protected void warningFinish() {
		_warningGraphicLeft.disableBehaviors();
		((BlinkBehavior)_warningGraphicLeft.getBehavior(TMBehaviorType.BLINK)).init();
		_warningGraphicRight.disableBehaviors();
		((BlinkBehavior)_warningGraphicRight.getBehavior(TMBehaviorType.BLINK)).init();
		_warningGraphicUp.disableBehaviors();
		((BlinkBehavior)_warningGraphicUp.getBehavior(TMBehaviorType.BLINK)).init();
		_warningGraphicDown.disableBehaviors();
		((BlinkBehavior)_warningGraphicDown.getBehavior(TMBehaviorType.BLINK)).init();
		
		((MovementModifierBehavior)_player.getBehavior(TMBehaviorType.MOVEMENT_MODIFIER)).setDirection(_windDirection);
		_player.enableBehaviors(TMBehaviorType.MOVEMENT_MODIFIER);
		
		((TweenBehavior)_fogGraphic.getBehavior(TMBehaviorType.TWEEN)).setTweenTime(500f);
		((TweenBehavior)_fogGraphic.getBehavior(TMBehaviorType.TWEEN)).setTotalChange(-.4f);
		_fogGraphic.enableBehaviors(TMBehaviorType.TWEEN);
		
		((TweenBehavior)_windGraphic.getBehavior(TMBehaviorType.TWEEN)).setTotalChange(.8f);
		((ScrollingTileBehavior)_windGraphic.getBehavior(TMBehaviorType.SCROLLING_TILE)).setDirection(_windDirection);
		_windGraphic.enableBehaviors(TMBehaviorType.TWEEN);
	
		_windSustainTimer.enableBehaviors();
	}
	
	protected void windSustainFinish() {
		_player.disableBehaviors(TMBehaviorType.MOVEMENT_MODIFIER);
		_player.Attributes.Area.getCenter(_player.Attributes.Destination);
		resetTimers();
		_windCountdownTimer.enableBehaviors();
		
		((TweenBehavior)_fogGraphic.getBehavior(TMBehaviorType.TWEEN)).setTweenTime(500f);
		((TweenBehavior)_fogGraphic.getBehavior(TMBehaviorType.TWEEN)).setTotalChange(.4f);
		_fogGraphic.enableBehaviors(TMBehaviorType.TWEEN);
		
		((TweenBehavior)_windGraphic.getBehavior(TMBehaviorType.TWEEN)).setTotalChange(-.8f);
		_windGraphic.enableBehaviors(TMBehaviorType.TWEEN);
	}

}
