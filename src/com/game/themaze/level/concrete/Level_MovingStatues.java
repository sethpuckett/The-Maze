package com.game.themaze.level.concrete;

import com.game.loblib.collision.ICollisionSender;
import com.game.loblib.entity.GameEntity;
import com.game.loblib.messaging.Message;
import com.game.loblib.messaging.MessageType;
import com.game.loblib.utility.Color;
import com.game.loblib.utility.Manager;
import com.game.the_maze.R;
import com.game.themaze.entity.EntityHelper;
import com.game.themaze.graphics.TMImage;
import com.game.themaze.graphics.TMSpriteLayer;
import com.game.themaze.level.Level;
import com.game.themaze.utility.TMManager;

public class Level_MovingStatues extends Level {
	boolean _entranceSide;
	boolean _entranceSideChange;
	int _triggerLane;
	boolean[] _wallsEnabled;
	
	GameEntity _background;
	
	GameEntity _entranceAreaTrigger;
	GameEntity _goalAreaTrigger;
	
	GameEntity[] _pathTriggers;
	
	GameEntity[] _triggerWalls;

	GameEntity[] _statues;
	GameEntity[] _symbols;
	
	public Level_MovingStatues() {
		_settings.ResourceId = R.raw.level_08;
	}
	
	@Override
	public void onInit() {
		
		_entranceSide = true;
		_entranceSideChange = false;
		_triggerLane = 0;
		
		_wallsEnabled = new boolean[5];
		for (int i = 0; i < _wallsEnabled.length; i++)
			_wallsEnabled[i] = true;
		
		_entities.add(EntityHelper.tiledGraphic(TMImage.BACKGROUND_DIRT_GRAY, TMSpriteLayer.BACKGROUND1, 500f, 0, 0, 4000f));
		
		_triggerWalls = new GameEntity[5];
		_triggerWalls[0] = EntityHelper.wall(13, 49, 3, false);
		_triggerWalls[1] = EntityHelper.wall(14, 49, 3, false);
		_triggerWalls[2] = EntityHelper.wall(15, 49, 3, false);
		_triggerWalls[3] = EntityHelper.wall(16, 49, 3, false);
		_triggerWalls[4] = EntityHelper.wall(17, 49, 3, false);
		for (int i = 0; i < _triggerWalls.length; i++)
			_entities.add(_triggerWalls[i]);
		
		_entranceAreaTrigger = EntityHelper.trigger(5, 4, 2, 15);
		_entities.add(_entranceAreaTrigger);
		_goalAreaTrigger = EntityHelper.trigger(10, 4, 2, 15);
		_entities.add(_goalAreaTrigger);
		
		_pathTriggers = new GameEntity[5];
		_pathTriggers[0] = EntityHelper.trigger(7, 4, 3, 2);
		_pathTriggers[1] = EntityHelper.trigger(7, 7, 3, 2);
		_pathTriggers[2] = EntityHelper.trigger(7, 10, 3, 3);
		_pathTriggers[3] = EntityHelper.trigger(7, 14, 3, 2);
		_pathTriggers[4] = EntityHelper.trigger(7, 17, 3, 2);
		for (int i = 0; i < _pathTriggers.length; i++)
			_entities.add(_pathTriggers[i]);
		
		_statues = new GameEntity[20];
		_statues[0] = statue(6, 59, Color.WHITE);
		_statues[1] = statue(7, 59, Color.RED);
		_statues[2] = statue(8, 59, Color.ORANGE);
		_statues[3] = statue(9, 59, Color.BLUE);
		_statues[4] = statue(10, 59, Color.GREEN);
		_statues[5] = statueBack(6, 41, Color.WHITE);
		_statues[6] = statueBack(7, 41, Color.RED);
		_statues[7] = statueBack(8, 41, Color.ORANGE);
		_statues[8] = statueBack(9, 41, Color.BLUE);
		_statues[9] = statueBack(10, 41, Color.GREEN);
		_statues[10] = statue(13, 53, Color.WHITE);
		_statues[11] = statue(14, 53, Color.RED);
		_statues[12] = statue(15, 53, Color.ORANGE);
		_statues[13] = statue(16, 53, Color.BLUE);
		_statues[14] = statue(17, 53, Color.GREEN);
		_statues[15] = statueBack(13, 47, Color.WHITE);
		_statues[16] = statueBack(14, 47, Color.RED);
		_statues[17] = statueBack(15, 47, Color.ORANGE);
		_statues[18] = statueBack(16, 47, Color.BLUE);
		_statues[19] = statueBack(17, 47, Color.GREEN);
		for (int i = 0; i < _statues.length; i++)
			_entities.add(_statues[i]);
		
		_symbols = new GameEntity[15];
		_symbols[0] = symbol(7, 56, Color.RED);
		_symbols[1] = symbol(8, 56, Color.WHITE);
		_symbols[2] = symbol(9, 56, Color.ORANGE);
		_symbols[3] = symbol(7, 53, Color.BLUE);
		_symbols[4] = symbol(8, 53, Color.ORANGE);
		_symbols[5] = symbol(9, 53, Color.RED);
		_symbols[6] = symbol(7, 50, Color.WHITE);
		_symbols[7] = symbol(8, 50, Color.BLUE);
		_symbols[8] = symbol(9, 50, Color.GREEN);
		_symbols[9] = symbol(7, 47, Color.ORANGE);
		_symbols[10] = symbol(8, 47, Color.GREEN);
		_symbols[11] = symbol(9, 47, Color.BLUE);
		_symbols[12] = symbol(7, 44, Color.GREEN);
		_symbols[13] = symbol(8, 44, Color.WHITE);
		_symbols[14] = symbol(9, 44, Color.RED);
		for (int i = 0; i < _symbols.length; i++)
			_entities.add(_symbols[i]);
		
		Manager.Message.subscribe(this, MessageType.TRIGGER_HIT);
	}
	
	@Override
	public void onUnpause() {
		Manager.Message.subscribe(this, MessageType.TRIGGER_HIT | MessageType.TRIGGER_RELEASED);
	}	
	
	@Override
	public void handleMessage(Message message) {
		super.handleMessage(message);
		
		if (message.Type == MessageType.TRIGGER_HIT) {
			ICollisionSender sender = message.getData();
			if (_entranceAreaTrigger == sender) {
				_entranceSide = true;
				if (_entranceSideChange) {
					switch (_triggerLane) {
					case 0:
						if (_wallsEnabled[0]) {
							_triggerWalls[0].disableBehaviors();
							_wallsEnabled[0] = false;
						}
						else {
							_triggerWalls[0].enableBehaviors();
							_wallsEnabled[0] = true;
						}
						if (_wallsEnabled[1]) {
							_triggerWalls[1].disableBehaviors();
							_wallsEnabled[1] = false;
						}
						else {
							_triggerWalls[1].enableBehaviors();
							_wallsEnabled[1] = true;
						}
						if (_wallsEnabled[2]) {
							_triggerWalls[2].disableBehaviors();
							_wallsEnabled[2] = false;
						}
						else {
							_triggerWalls[2].enableBehaviors();
							_wallsEnabled[2] = true;
						}
						break;
					case 1:
						if (_wallsEnabled[1]) {
							_triggerWalls[1].disableBehaviors();
							_wallsEnabled[1] = false;
						}
						else {
							_triggerWalls[1].enableBehaviors();
							_wallsEnabled[1] = true;
						}
						if (_wallsEnabled[2]) {
							_triggerWalls[2].disableBehaviors();
							_wallsEnabled[2] = false;
						}
						else {
							_triggerWalls[2].enableBehaviors();
							_wallsEnabled[2] = true;
						}
						if (_wallsEnabled[3]) {
							_triggerWalls[3].disableBehaviors();
							_wallsEnabled[3] = false;
						}
						else {
							_triggerWalls[3].enableBehaviors();
							_wallsEnabled[3] = true;
						}
						break;
					case 2:
						if (_wallsEnabled[0]) {
							_triggerWalls[0].disableBehaviors();
							_wallsEnabled[0] = false;
						}
						else {
							_triggerWalls[0].enableBehaviors();
							_wallsEnabled[0] = true;
						}
						if (_wallsEnabled[3]) {
							_triggerWalls[3].disableBehaviors();
							_wallsEnabled[3] = false;
						}
						else {
							_triggerWalls[3].enableBehaviors();
							_wallsEnabled[3] = true;
						}
						if (_wallsEnabled[4]) {
							_triggerWalls[4].disableBehaviors();
							_wallsEnabled[4] = false;
						}
						else {
							_triggerWalls[4].enableBehaviors();
							_wallsEnabled[4] = true;
						}
						break;
					case 3:
						if (_wallsEnabled[2]) {
							_triggerWalls[2].disableBehaviors();
							_wallsEnabled[2] = false;
						}
						else {
							_triggerWalls[2].enableBehaviors();
							_wallsEnabled[2] = true;
						}
						if (_wallsEnabled[3]) {
							_triggerWalls[3].disableBehaviors();
							_wallsEnabled[3] = false;
						}
						else {
							_triggerWalls[3].enableBehaviors();
							_wallsEnabled[3] = true;
						}
						if (_wallsEnabled[4]) {
							_triggerWalls[4].disableBehaviors();
							_wallsEnabled[4] = false;
						}
						else {
							_triggerWalls[4].enableBehaviors();
							_wallsEnabled[4] = true;
						}
						break;
					case 4:
						if (_wallsEnabled[0]) {
							_triggerWalls[0].disableBehaviors();
							_wallsEnabled[0] = false;
						}
						else {
							_triggerWalls[0].enableBehaviors();
							_wallsEnabled[0] = true;
						}
						if (_wallsEnabled[1]) {
							_triggerWalls[1].disableBehaviors();
							_wallsEnabled[1] = false;
						}
						else {
							_triggerWalls[1].enableBehaviors();
							_wallsEnabled[1] = true;
						}
						if (_wallsEnabled[4]) {
							_triggerWalls[4].disableBehaviors();
							_wallsEnabled[4] = false;
						}
						else {
							_triggerWalls[4].enableBehaviors();
							_wallsEnabled[4] = true;
						}
						break;
					}
					_triggerLane = 0;
				}
				_entranceSideChange = false;
			}
			else if (_goalAreaTrigger == sender) {
				_entranceSide = false;
				if (!_entranceSideChange) {
					switch (_triggerLane) {
					case 0:
						if (_wallsEnabled[0]) {
							_triggerWalls[0].disableBehaviors();
							_wallsEnabled[0] = false;
						}
						else {
							_triggerWalls[0].enableBehaviors();
							_wallsEnabled[0] = true;
						}
						if (_wallsEnabled[1]) {
							_triggerWalls[1].disableBehaviors();
							_wallsEnabled[1] = false;
						}
						else {
							_triggerWalls[1].enableBehaviors();
							_wallsEnabled[1] = true;
						}
						if (_wallsEnabled[2]) {
							_triggerWalls[2].disableBehaviors();
							_wallsEnabled[2] = false;
						}
						else {
							_triggerWalls[2].enableBehaviors();
							_wallsEnabled[2] = true;
						}
						break;
					case 1:
						if (_wallsEnabled[1]) {
							_triggerWalls[1].disableBehaviors();
							_wallsEnabled[1] = false;
						}
						else {
							_triggerWalls[1].enableBehaviors();
							_wallsEnabled[1] = true;
						}
						if (_wallsEnabled[2]) {
							_triggerWalls[2].disableBehaviors();
							_wallsEnabled[2] = false;
						}
						else {
							_triggerWalls[2].enableBehaviors();
							_wallsEnabled[2] = true;
						}
						if (_wallsEnabled[3]) {
							_triggerWalls[3].disableBehaviors();
							_wallsEnabled[3] = false;
						}
						else {
							_triggerWalls[3].enableBehaviors();
							_wallsEnabled[3] = true;
						}
						break;
					case 2:
						if (_wallsEnabled[0]) {
							_triggerWalls[0].disableBehaviors();
							_wallsEnabled[0] = false;
						}
						else {
							_triggerWalls[0].enableBehaviors();
							_wallsEnabled[0] = true;
						}
						if (_wallsEnabled[3]) {
							_triggerWalls[3].disableBehaviors();
							_wallsEnabled[3] = false;
						}
						else {
							_triggerWalls[3].enableBehaviors();
							_wallsEnabled[3] = true;
						}
						if (_wallsEnabled[4]) {
							_triggerWalls[4].disableBehaviors();
							_wallsEnabled[4] = false;
						}
						else {
							_triggerWalls[4].enableBehaviors();
							_wallsEnabled[4] = true;
						}
						break;
					case 3:
						if (_wallsEnabled[2]) {
							_triggerWalls[2].disableBehaviors();
							_wallsEnabled[2] = false;
						}
						else {
							_triggerWalls[2].enableBehaviors();
							_wallsEnabled[2] = true;
						}
						if (_wallsEnabled[3]) {
							_triggerWalls[3].disableBehaviors();
							_wallsEnabled[3] = false;
						}
						else {
							_triggerWalls[3].enableBehaviors();
							_wallsEnabled[3] = true;
						}
						if (_wallsEnabled[4]) {
							_triggerWalls[4].disableBehaviors();
							_wallsEnabled[4] = false;
						}
						else {
							_triggerWalls[4].enableBehaviors();
							_wallsEnabled[4] = true;
						}
						break;
					case 4:
						if (_wallsEnabled[0]) {
							_triggerWalls[0].disableBehaviors();
							_wallsEnabled[0] = false;
						}
						else {
							_triggerWalls[0].enableBehaviors();
							_wallsEnabled[0] = true;
						}
						if (_wallsEnabled[1]) {
							_triggerWalls[1].disableBehaviors();
							_wallsEnabled[1] = false;
						}
						else {
							_triggerWalls[1].enableBehaviors();
							_wallsEnabled[1] = true;
						}
						if (_wallsEnabled[4]) {
							_triggerWalls[4].disableBehaviors();
							_wallsEnabled[4] = false;
						}
						else {
							_triggerWalls[4].enableBehaviors();
							_wallsEnabled[4] = true;
						}
						break;
					}
					_triggerLane = 0;
				}
				_entranceSideChange = true;
			}
			else if (_pathTriggers[0] == sender)
				_triggerLane = 0;
			else if (_pathTriggers[1] == sender)
				_triggerLane = 1;
			else if (_pathTriggers[2] == sender)
				_triggerLane = 2;
			else if (_pathTriggers[3] == sender)
				_triggerLane = 3;
			else if (_pathTriggers[4] == sender)
				_triggerLane = 4;
			else if (_pathTriggers[5] == sender)
				_triggerLane = 5;
		}
	}

	/*********************************
	 * Entities
	 ********************************/
	protected GameEntity statue(int xPos, int yPos, int color) {
		int image = 0;
		switch (color) {
			case Color.WHITE:
				image = TMImage.STATUE_WHITE;
				break;
			case Color.RED:
				image = TMImage.STATUE_RED;
				break;
			case Color.ORANGE:
				image = TMImage.STATUE_ORANGE;
				break;
			case Color.BLUE:
				image = TMImage.STATUE_BLUE;
				break;
			case Color.GREEN:
				image = TMImage.STATUE_GREEN;
				break;
		}
		int cellWidth = TMManager.Level.getCellWidth();
		return EntityHelper.graphic(image, TMSpriteLayer.BACKGROUND2,
				true, cellWidth, cellWidth, false, xPos * cellWidth, yPos * cellWidth);
	}
	
	protected GameEntity statueBack(int xPos, int yPos, int color) {
		int image = 0;
		switch (color) {
			case Color.WHITE:
				image = TMImage.STATUE_BACK_WHITE;
				break;
			case Color.RED:
				image = TMImage.STATUE_BACK_RED;
				break;
			case Color.ORANGE:
				image = TMImage.STATUE_BACK_ORANGE;
				break;
			case Color.BLUE:
				image = TMImage.STATUE_BACK_BLUE;
				break;
			case Color.GREEN:
				image = TMImage.STATUE_BACK_GREEN;
				break;
		}
		int cellWidth = TMManager.Level.getCellWidth();
		return EntityHelper.graphic(image, TMSpriteLayer.BACKGROUND2,
				true, cellWidth, cellWidth, false, xPos * cellWidth, yPos * cellWidth);
	}
	
	protected GameEntity symbol(int xPos, int yPos, int color) {
		int image = 0;
		switch (color) {
			case Color.WHITE:
				image = TMImage.SYMBOL_WHITE;
				break;
			case Color.RED:
				image = TMImage.SYMBOL_RED;
				break;
			case Color.ORANGE:
				image = TMImage.SYMBOL_ORANGE;
				break;
			case Color.BLUE:
				image = TMImage.SYMBOL_BLUE;
				break;
			case Color.GREEN:
				image = TMImage.SYMBOL_GREEN;
				break;
		}
		int cellWidth = TMManager.Level.getCellWidth();
		return EntityHelper.graphic(image, TMSpriteLayer.BACKGROUND2,
				true, cellWidth, cellWidth, false, xPos * cellWidth, yPos * cellWidth);
	}
}
