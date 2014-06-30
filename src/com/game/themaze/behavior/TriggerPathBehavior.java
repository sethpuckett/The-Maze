package com.game.themaze.behavior;

import com.game.loblib.behavior.Behavior;
import com.game.loblib.entity.GameEntity;
import com.game.loblib.messaging.IMessageHandler;
import com.game.loblib.messaging.Message;
import com.game.loblib.messaging.MessageType;
import com.game.loblib.utility.Logger;
import com.game.loblib.utility.Manager;
import com.game.loblib.utility.android.FixedSizeArray;
import com.game.themaze.messaging.TMMessageType;

public class TriggerPathBehavior extends Behavior implements IMessageHandler {

	protected final int MAX_PATH_TRIGGERS = 16;
	
	protected GameEntity _pathAreaTrigger;
	protected FixedSizeArray<GameEntity> _pathTriggers;
	
	
	protected int _currentTriggerIndex;
	protected int _nextTriggerIndex;
	protected boolean _inBounds;
	protected boolean _outOfBounds;
	
	public TriggerPathBehavior() {
		_type = TMBehaviorType.TRIGGER_PATH;
		_pathTriggers = new FixedSizeArray<GameEntity>(MAX_PATH_TRIGGERS);
	}
	
	public TriggerPathBehavior(GameEntity pathAreaTrigger) {
		_type = TMBehaviorType.TRIGGER_PATH;
		_pathTriggers = new FixedSizeArray<GameEntity>(MAX_PATH_TRIGGERS);
		
		_pathAreaTrigger = pathAreaTrigger;
	}
	
	public void addPathTrigger(GameEntity trigger) {
		if (_pathTriggers.getCount() >= MAX_PATH_TRIGGERS)
			Logger.e(_tag, "Cannot add path trigger; max triggers exceded");
		else
			_pathTriggers.add(trigger);
	}
	
	public void setTotalAreaTrigger(GameEntity trigger) {
		_pathAreaTrigger = trigger;
	}
	
	@Override
	public void init() {
		_currentTriggerIndex = -1;
		_nextTriggerIndex = 0;
		_inBounds = false;
		_outOfBounds = false;
		
		Manager.Message.subscribe(this, MessageType.TRIGGER_HIT | MessageType.TRIGGER_RELEASED);
	}
	
	@Override
	public void onEnable() {
		Manager.Message.subscribe(this, MessageType.TRIGGER_HIT | MessageType.TRIGGER_RELEASED);
	}
	
	@Override
	public void onDisable() {
		Manager.Message.unsubscribe(this, MessageType.ALL);
	}
	
	@Override
	protected void onUpdate(float updateRatio) {
		if (_outOfBounds) {
			Manager.Message.sendMessage(TMMessageType.TRIGGER_PATH_FAIL, _entity);
			disable();
		}
	}
	
	@Override
	public void handleMessage(Message message) {
		if (message.Type == MessageType.TRIGGER_HIT) {
			GameEntity trigger = message.getData();
			if (trigger == _pathAreaTrigger && !_inBounds) 
				_outOfBounds = true;
			else if (_nextTriggerIndex != -1 && trigger == _pathTriggers.get(_nextTriggerIndex)) {
				_inBounds = true;
				_outOfBounds = false;
				
				_currentTriggerIndex++;
				
				if (_nextTriggerIndex == _pathTriggers.getCount() - 1)
					_nextTriggerIndex = -1;
				else
					_nextTriggerIndex++;
			}

		}
		else if (message.Type == MessageType.TRIGGER_RELEASED) {
			GameEntity trigger = message.getData();
			if (trigger == _pathAreaTrigger) {
				_inBounds = false;
				_outOfBounds = false;
				_currentTriggerIndex = -1;
				_nextTriggerIndex = 0;
			}
			else if (_currentTriggerIndex != -1 && trigger == _pathTriggers.get(_currentTriggerIndex)) {
				_outOfBounds = true;
				_inBounds = false;
			}
			
			
		}
	}
	
	@Override
	protected void setTag() {
		_tag.setLength(0);
		_tag.append(_entity.getTag());
		_tag.append(": TriggerPathBehavior");
	}
}
