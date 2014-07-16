package com.game.themaze.level;

import com.game.loblib.entity.GameEntity;
import com.game.loblib.utility.Global;
import com.game.loblib.utility.Logger;
import com.game.loblib.utility.Manager;
import com.game.loblib.utility.android.FixedSizeArray;
import com.game.themaze.messaging.TMMessageType;

public class LevelManager {
	protected static final StringBuffer _tag = new StringBuffer("LevelManager");
	
	protected int _cellWidth = 30;
	protected float _xOffset;
	protected float _yOffset;
	protected int _currentLevel;
	protected boolean _levelActive;
	protected Level _loadedLevel;
	
	public static final int MAX_LEVELS = 34;
	
	public void setCurrentLevel(int level) {
		if (_levelActive)
			Logger.e(_tag, "cannot set level; must close current level");
		else if (level < 0 || level > MAX_LEVELS)
			Logger.e(_tag, "cannot set level; level out of range");
		else
			_currentLevel = level;
	}
	
	public int getCurrentLevel() {
		return _currentLevel;
	}
	
	public void loadLevel() {
		loadLevel(null);
	}
	
	public void loadLevel(LevelSettings settings) {
		if (_levelActive)
			Logger.e(_tag, "cannot load level; must close current level");
		else if (_currentLevel < 0 || _currentLevel > MAX_LEVELS)
			Logger.e(_tag, "cannot start; level not set");
		else {
			_loadedLevel = LevelFactory.create(_currentLevel);
			_loadedLevel.init(settings);
		}
		
	}
	
	public void enableLevel() {
		if (_loadedLevel == null)
			Logger.e(_tag, "cannot enable level; no level loaded");
		else if (_levelActive)
			Logger.e(_tag, "level is already enabled");
		else {
			_loadedLevel.unpause();
			_levelActive = true;
		}
	}
	
	public void disableLevel() {
		if (_loadedLevel == null)
			Logger.e(_tag, "cannot disable level; no level loaded");
		else if (!_levelActive)
			Logger.e(_tag, "level is already disabled");
		else {
			_loadedLevel.pause();
			_levelActive = false;
		}
	}
	
	public void updateLevel() {
		if (_loadedLevel == null)
			Logger.e(_tag, "cannot update level; no level loaded");
		else
			_loadedLevel.update();
	}
	
	public void stop() {
		if (_loadedLevel == null)
			Logger.e(_tag, "cannot stop; no level loaded");
		else {
			_levelActive = false;
			_loadedLevel.close();
			_loadedLevel = null;
		}
	}

	public void setCellWidth(int cellWidth) {
		if (cellWidth > 0) {
			_cellWidth = cellWidth;
			Manager.Message.sendMessage(TMMessageType.LEVEL_CELL_WIDTH_SET);
		}
		else
			Logger.e(_tag, "cannot set cell width; invalid size");
	}
	
	public int getCellWidth() {
		return _cellWidth;
	}
	
	public float getDoorSpeed() {
		return (float)_cellWidth / 4f;
	}
	
	public void setOffset(float x, float y) {
		_xOffset = x;
		_yOffset = y;
	}
	
	public float getXOffset() {
		return _xOffset;
	}
	
	public float getYOffset() {
		return _yOffset;
	}
	
	public float getControlBarHeight() {
		return Global.Renderer.Width / 5f;
	}

	public void enableBehaviors() {
		if (_loadedLevel != null)
			_loadedLevel.enableBehaviors();
		else
			Logger.e(_tag, "cannot enable; no level loaded");
	}
	
	public FixedSizeArray<GameEntity> getLevelEntities() {
		if (_loadedLevel != null)
			return _loadedLevel.getEntities();
		else {
			Logger.e(_tag, "cannog get level entities; no level loaded");
			return null;
		}
	}
}