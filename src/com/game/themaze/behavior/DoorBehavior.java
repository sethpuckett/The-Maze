package com.game.themaze.behavior;

import com.game.loblib.utility.Logger;
import com.game.loblib.utility.area.Area;
import com.game.themaze.utility.DoorOpenType;
import com.game.themaze.utility.DoorType;

public class DoorBehavior extends PatrolDestinationBehavior {

	protected boolean _initOpen;
	protected float _xClosed;
	protected float _yClosed;
	protected float _xOpen;
	protected float _yOpen;
	protected int _doorType;	
	protected int _doorOpenType;	
	
	protected boolean _open;
	
	public DoorBehavior(float xClosed, float yClosed, float xOpen, float yOpen) {
		super();
		_type = TMBehaviorType.DOOR_ANCHOR;
		_initOpen = false;
		_xClosed = xClosed;
		_yClosed = yClosed;
		_xOpen = xOpen;
		_yOpen = yOpen;
		_doorType = DoorType.NEUTRAL;
		_doorOpenType = DoorOpenType.SINGLE;
	}
	
	public DoorBehavior(float xClosed, float yClosed, float xOpen, float yOpen, int doorType, int doorOpenType, boolean open) {
		super();
		_type = TMBehaviorType.DOOR_ANCHOR;
		_initOpen = open;
		_xClosed = xClosed;
		_yClosed = yClosed;
		_xOpen = xOpen;
		_yOpen = yOpen;
		_doorType = doorType;
		_doorOpenType = doorOpenType;
	}
	
	@Override
	public void init() {
		super.init();
		
		_open = _initOpen;
		_destinations.clear();
		setPatrolType(PatrolType.SINGLE);
		addDestination(_xClosed, _yClosed);
		addDestination(_xOpen, _yOpen);
		
		if (_open)
			_entity.Attributes.Area.setPosition(_xOpen, _yOpen);
		else
			_entity.Attributes.Area.setPosition(_xClosed, _yClosed);
	}
	
	@Override
	public void onEnable() {
		if (_destinations.getCount() < 2)
			Logger.e(_tag, "PatrolDestinationBehavior requires at least 2 destinations");
		
		if (_open)
			_currentDestinationIndex = 1;
		else
			_currentDestinationIndex = 0;
		
		if (_entity.Attributes.Destination != _destinations.get(_currentDestinationIndex))
			Area.sync(_entity.Attributes.Destination, _destinations.get(_currentDestinationIndex));
	}
	
	@Override
	protected void setTag() {
		_tag.setLength(0);
		_tag.append(_entity.getTag());
		_tag.append(": DoorMoveBehavior");
	}

	public void open() {
		if (!_open && !_enabled) {
			_open = true;
			enable();
		}
	}
	
	public void close() {
		if (_open && !_enabled) {
			_open = false;
			enable();
		}
	}

	public void toggle() {
		if (_open)
			close();
		else
			open();
	}
	
	public boolean isOpen() {
		return _open;
	}
	
	public int getDoorType() {
		return _doorType;
	}

	public int getDoorOpenType() {
		return _doorOpenType;
	}
}
