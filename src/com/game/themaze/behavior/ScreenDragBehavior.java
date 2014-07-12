package com.game.themaze.behavior;

import com.game.loblib.behavior.Behavior;
import com.game.loblib.exception.UndefinedVertexException;
import com.game.loblib.input.GameMotionEvent;
import com.game.loblib.input.ITouchListener;
import com.game.loblib.input.MotionType;
import com.game.loblib.input.TouchData;
import com.game.loblib.messaging.IMessageHandler;
import com.game.loblib.messaging.Message;
import com.game.loblib.messaging.MessageType;
import com.game.loblib.utility.Global;
import com.game.loblib.utility.Logger;
import com.game.loblib.utility.Manager;
import com.game.loblib.utility.area.Area;
import com.game.loblib.utility.area.AreaType;
import com.game.loblib.utility.area.Rectangle;
import com.game.loblib.utility.area.Vertex;
import com.game.themaze.messaging.TMMessageType;

public class ScreenDragBehavior extends Behavior implements ITouchListener, IMessageHandler {
	
	protected static final float MOMENTUM_CUTOFF = Global.Renderer.Width / 500f;
	protected static final float MOMENTUM_MAX = Global.Renderer.Width / 24f;
	
	protected boolean _screenTouch = false; // true if screen is touched
	protected boolean _dragging; // true if screen is touched and dragged past _dragThreshold
	protected boolean _sliding; // true if screen touch has ended but momentum movement is still in action
	protected boolean _touchUpdate; // used to prevent behavior updates before latest touch data has been received
	protected float _touchDelay; // stores amount of time since last touch data received
	protected Vertex _startLocation = new Vertex(); // location where touch that started drag began
	protected Vertex _currentLocation = new Vertex(); // latest location in drag
	protected Vertex _currentTouchLocation = new Vertex(); // latest location according to touch events (can change multiple times per update cycle)
	protected Vertex _distanceChange = new Vertex(); // stores distance moved in last update
	protected Vertex _momentum = new Vertex(); // used to calculate momentum movement when touch stops
	protected float _dragThreshold; // distance touch must move to began drag
	protected final TouchData _touchData = new TouchData(this, AreaType.Rectangle, false, false, true, MotionType.ACTION_DOWN | MotionType.ACTION_MOVE | MotionType.ACTION_UP);
	
	
	public ScreenDragBehavior() {
		_type = TMBehaviorType.SCREEN_DRAG;
		_dragThreshold = 500f;
	}
	
	public ScreenDragBehavior(float threshold) {
		_type = TMBehaviorType.SCREEN_DRAG;
		_dragThreshold = threshold;
	}
	
	// Returns the distance dragged in the X direction since the previous update cycle
	public float getCurrentXDragDistance() {
		if (_dragging || _sliding)
			return _distanceChange.X;
		else
			return 0f;
	}
	
	// Returns the distance dragged in the Y direction since the previous update cycle
	public float getCurrentYDragDistance() {
		if (_dragging || _sliding)
			return _distanceChange.Y;
		else
			return 0f;
	}
	
	// Returns the total distance dragged since the previous update cycle
	public float getCurrentDragDistance() {
		float speed = 0;
		if (_dragging || _sliding) {
			try {
				speed = Vertex.magnitude(_distanceChange);
			}
			catch (UndefinedVertexException e) {
				Logger.e(_tag, "Undefined vertex");
			}
		}
		return speed;
	}
	
	// Returns the total distance dragged in the X direction
	public float getXDragDistance() {
		float dist = 0f;
		if (_dragging || _sliding)
			dist = _currentLocation.X - _startLocation.X;
		return dist;
	}
	
	// Returns the total distance dragged in the Y direction
	public float getYDragDistance() {
		float dist = 0f;
		if (_dragging || _sliding)
			dist = _currentLocation.Y - _startLocation.Y;
		return dist;
	}
	
	// Returns the total distance dragged
	public float getDragDistance() {
		float dist = 0f;
		if (_dragging || _sliding) {
			try {
				dist = Vertex.distance(_currentLocation, _startLocation);
			}
			catch (UndefinedVertexException e) {
				Logger.e(_tag, "Undefined vertex");
			}
		}
		return dist;
	}
	
	// Returns the location where the drag started
	public void getStartLocation(Vertex start) {
		Area.sync(start, _startLocation);
	}
	
	// Returns the current drag location
	public void getCurrentLocation(Vertex prev) {
		if (_screenTouch)
			Area.sync(prev, _currentLocation);
		else
			prev.Undefined = true;
	}
	
	// Returns true if screen is sliding
	public boolean isSliding() {
		return _sliding;
	}
	
	// Stops screen sliding
	public void stopSliding() {
		_sliding = false;
		_momentum.X = 0f;
		_momentum.Y = 0f;
	}
	
	@Override
	protected void onEnable() {
		if (Global.Renderer.isSurfaceCreated())
			_touchData.TouchArea.setSize(Global.Renderer.Width, Global.Renderer.Height);
		
		_dragging = false;
		_sliding = false;
		_screenTouch = false;
		_touchUpdate = false;
		
		Manager.Input.subscribe(_touchData);
		Manager.Message.subscribe(this, MessageType.SCREEN_SIZE_SET);
	}
	
	@Override
	protected void onDisable() {
		_dragging = false;
		_sliding = false;
		_screenTouch = false;
		
		Manager.Message.unsubscribe(this, MessageType.ALL);
		Manager.Input.unsubscribe(_touchData);
	}
	
	@Override
	protected void onUpdate(float updateRatio) {
		if (_dragging) {
			// if new touch data received calculate new momentum and update current location; otherwise update time since last touch data received
			if(_touchUpdate) {
				try {
					// Calculate new momentum and update currentLocation
					float totalRatio = (3f * _touchDelay) / 50f;
					Vertex.sub(_currentTouchLocation, _currentLocation, _distanceChange);
					Vertex.mul(_distanceChange, totalRatio, _momentum);
					Area.sync(_currentLocation, _currentTouchLocation);
					_touchDelay = 0f;
					_touchUpdate = false;
				}
				catch (UndefinedVertexException e) {
					Logger.e(_tag, "Undefined vertex");
				}
			}
			else {
				_distanceChange.X = 0f;
				_distanceChange.Y = 0f;
				_touchDelay += (50f * updateRatio) / 3f;
			}
		}
		else if (_sliding) {
			float newXMomentum = 0f;
			float newYMomentum = 0f;
			
			try {
				
				Vertex.mul(_momentum, updateRatio, _distanceChange);
				
				if (_momentum.X > 0f)
					newXMomentum = _momentum.X - ((float)Math.log(_momentum.X + 1f) * updateRatio / 4f);
				else
					newXMomentum = _momentum.X + ((float)Math.log(Math.abs(_momentum.X) + 1f) * updateRatio / 4f);
				
				if (_momentum.Y > 0f)
					newYMomentum = _momentum.Y - ((float)Math.log(_momentum.Y + 1f) * updateRatio / 4f);
				else
					newYMomentum = _momentum.Y + ((float)Math.log(Math.abs(_momentum.Y) + 1f) * updateRatio / 4f);
				
				_momentum.X = newXMomentum;
				_momentum.Y = newYMomentum;
				
				if (Math.abs(_momentum.X) < MOMENTUM_CUTOFF && Math.abs(_momentum.Y) < MOMENTUM_CUTOFF) {
					_momentum.X = 0f;
					_momentum.Y = 0f;
					_distanceChange.X = 0f;
					_distanceChange.Y = 0f;
					_sliding = false;
					
					Manager.Message.sendMessage(TMMessageType.DRAG_MOMENTUM_STOP, _entity);
				}
			}
			catch (UndefinedVertexException e) {
				Logger.e(_tag, "Undefined vertex");
			}
		}
	}
	
	@Override
	protected void setTag() {
		_tag.setLength(0);
		_tag.append(_entity.getTag());
		_tag.append(": ScreenDragBehavior");
	}
	
	protected void stopDragCheck() {
		if (_dragging) {
			// start slide if moving fast enough
			if (Math.abs(_momentum.X) > MOMENTUM_CUTOFF && Math.abs(_momentum.Y) > MOMENTUM_CUTOFF) {
				_sliding = true;
				
				// limit slide speed to maximum
				if (_momentum.X > 0)
					_momentum.X = Math.min(_momentum.X, MOMENTUM_MAX);
				else
					_momentum.X = Math.max(_momentum.X, -MOMENTUM_MAX);
				
				if (_momentum.Y > 0)
					_momentum.Y = Math.min(_momentum.Y, MOMENTUM_MAX);
				else
					_momentum.Y = Math.max(_momentum.Y, -MOMENTUM_MAX);
			}
			
			_dragging = false;
				
			Manager.Message.sendMessage(TMMessageType.DRAG_STOP, _entity);
			if (!_sliding)
				Manager.Message.sendMessage(TMMessageType.DRAG_MOMENTUM_STOP, _entity);
		}
	}
	
	@Override
	public void onTouchEvent(GameMotionEvent event) {
		switch (event.Type) {
		case MotionType.ACTION_DOWN:
			_screenTouch = true;
			_touchUpdate = true;
			_touchDelay = 0f;
			Area.sync(_currentTouchLocation, event.ScreenCoords);
			Area.sync(_startLocation, event.ScreenCoords);
			break;
		case MotionType.ACTION_MOVE:
			if (_enabled && _screenTouch) {
				try {
					if (!_dragging && Vertex.distanceSquared(event.ScreenCoords, _startLocation) >= Math.pow(_dragThreshold, 2f)) {
						_dragging = true;
						Area.sync(_currentLocation, event.ScreenCoords);
						Manager.Message.sendMessage(MessageType.DRAG_START, _entity);	
					}
				}
				catch (UndefinedVertexException e) {
					Logger.e(_tag, "undefined vertex");
				}
				
				Area.sync(_currentTouchLocation, event.ScreenCoords);
				_touchUpdate = true;
			}
			break;
		case MotionType.ACTION_UP:
			stopDragCheck();
			_screenTouch = false;
			break;
		}
	}

	@Override
	public void onTouchAbort(GameMotionEvent event) {
		stopDragCheck();
	}

	@Override
	public void handleMessage(Message message) {
		if (message.Type == MessageType.SCREEN_SIZE_SET) {
			Vertex newSize = message.getData();
			Area.sync(((Rectangle)_touchData.TouchArea).Size, newSize);
		}
	}

}
