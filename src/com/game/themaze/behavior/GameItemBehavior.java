package com.game.themaze.behavior;

import com.game.loblib.behavior.Behavior;


public class GameItemBehavior extends Behavior {

	protected int _gameItemType;
	
	public GameItemBehavior(int gameItemType) {
		_type = TMBehaviorType.GAME_ITEM;
		_gameItemType = gameItemType;
	}
	
	public int getGameItemType() {
		return _gameItemType;
	}
	
	@Override
	protected void setTag() {
		_tag.setLength(0);
		_tag.append(_entity.getTag());
		_tag.append(": GameItemBehavior");
	}

}
