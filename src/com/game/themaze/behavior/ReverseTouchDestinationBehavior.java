package com.game.themaze.behavior;

import com.game.loblib.input.GameMotionEvent;
import com.game.loblib.input.MotionType;
import com.game.loblib.utility.Global;
import com.game.loblib.utility.area.Area;
import com.game.loblib.utility.area.Vertex;
import com.game.themaze.utility.TMManager;


public class ReverseTouchDestinationBehavior extends TouchDestinationBehavior{

	protected Vertex _focus;
	
	public ReverseTouchDestinationBehavior(Vertex focus) {
		_type = TMBehaviorType.REVERSE_TOUCH_DESTINATION;
		
		_focus = focus;
	}

	@Override
	protected void setTag() {
		_tag.setLength(0);
		_tag.append(_entity.getTag());
		_tag.append(": TouchDestinationBehavior");
	}
	
	@Override
	public void onTouchEvent(GameMotionEvent event) {
		switch (event.Type) {
		case MotionType.ACTION_DOWN:
			if (event.ScreenCoords.Y > TMManager.Level.getControlBarHeight())
				_screenTouch = true;
			else
				break;
		case MotionType.ACTION_MOVE:
			if (_screenTouch || event.ScreenCoords.Y > TMManager.Level.getControlBarHeight()) {
				_screenTouch = true;
				_entity.Attributes.Destination.Undefined = false;
				_entity.Attributes.Destination.X = _focus.X + (_focus.X - event.CameraCoords.X);
				_entity.Attributes.Destination.Y = _focus.Y + (_focus.Y - event.CameraCoords.Y + TMManager.Level.getControlBarHeight());
				Area.sync(_cameraLocation, Global.Camera.CameraArea.Position);
			}
			break;
		case MotionType.ACTION_UP:
		case MotionType.ACTION_POINTER_UP:
			_entity.Attributes.Destination.Undefined = true;
			_screenTouch = false;
			break;
		}
	}
}
