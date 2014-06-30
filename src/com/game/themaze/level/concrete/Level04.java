package com.game.themaze.level.concrete;

import java.util.Random;

import com.game.loblib.entity.GameEntity;
import com.game.loblib.graphics.Image;
import com.game.loblib.messaging.Message;
import com.game.loblib.messaging.MessageType;
import com.game.loblib.utility.Global;
import com.game.loblib.utility.Manager;
import com.game.loblib.utility.android.FixedSizeArray;
import com.game.the_maze.R;
import com.game.themaze.entity.EntityHelper;
import com.game.themaze.graphics.TMImage;
import com.game.themaze.graphics.TMSpriteLayer;
import com.game.themaze.level.Level;
import com.game.themaze.utility.TMManager;

// Introduction - puzzle
public class Level04 extends Level {
	
	protected FixedSizeArray<GameEntity> _runes;
	protected FixedSizeArray<GameEntity> _pathTriggers;
	protected GameEntity _goalCheckTrigger;
	protected boolean[] _pathHits;
	protected int _goalPath = 0;
	protected boolean _reset = false;
	
	
	public Level04() {
		_settings.ResourceId = R.raw.level_04;
		_settings.CellWidth = (int)(Global.Renderer.Width / 20f);
		_settings.DisableCamera = true;
		_settings.DisableGoalIndicator = true;
		_settings.BackgroundImage = TMImage.WHITE;
		_settings.BackgroundTileCount = 1;
		_settings.WallSprites.setSimpleImages(TMImage.WALL_HORIZONTAL_BLACK, 
				TMImage.WALL_VERTICAL_BLACK, Image.NONE, Image.NONE);
	}
	
	@Override
	public void init() {
		super.init();
		
		_runes = new FixedSizeArray<GameEntity>(14);
		_runes.add(EntityHelper.levelGraphic(TMImage.LEVEL_04_RUNE_C, TMSpriteLayer.BACKGROUND2, 28, 36, 2, 2));
		_runes.add(EntityHelper.levelGraphic(TMImage.LEVEL_04_RUNE_R, TMSpriteLayer.BACKGROUND2, 28, 30, 2, 2));
		_runes.add(EntityHelper.levelGraphic(TMImage.LEVEL_04_RUNE_A, TMSpriteLayer.BACKGROUND2, 28, 36, 2, 2));
		_runes.add(EntityHelper.levelGraphic(TMImage.LEVEL_04_RUNE_U, TMSpriteLayer.BACKGROUND2, 28, 36, 2, 2));
		_runes.add(EntityHelper.levelGraphic(TMImage.LEVEL_04_RUNE_A, TMSpriteLayer.BACKGROUND2, 28, 36, 2, 2));
		_runes.add(EntityHelper.levelGraphic(TMImage.LEVEL_04_RUNE_U, TMSpriteLayer.BACKGROUND2, 28, 36, 2, 2));
		_runes.add(EntityHelper.levelGraphic(TMImage.LEVEL_04_RUNE_T, TMSpriteLayer.BACKGROUND2, 28, 36, 2, 2));
		_runes.add(EntityHelper.levelGraphic(TMImage.LEVEL_04_RUNE_D, TMSpriteLayer.BACKGROUND2, 28, 36, 2, 2));
		_runes.add(EntityHelper.levelGraphic(TMImage.LEVEL_04_RUNE_B, TMSpriteLayer.BACKGROUND2, 28, 36, 2, 2));
		_runes.add(EntityHelper.levelGraphic(TMImage.LEVEL_04_RUNE_R, TMSpriteLayer.BACKGROUND2, 28, 36, 2, 2));
		_runes.add(EntityHelper.levelGraphic(TMImage.LEVEL_04_RUNE_T, TMSpriteLayer.BACKGROUND2, 28, 36, 2, 2));
		_runes.add(EntityHelper.levelGraphic(TMImage.LEVEL_04_RUNE_M, TMSpriteLayer.BACKGROUND2, 28, 36, 2, 2));
		_runes.add(EntityHelper.levelGraphic(TMImage.LEVEL_04_RUNE_T, TMSpriteLayer.BACKGROUND2, 28, 36, 2, 2));
		_runes.add(EntityHelper.levelGraphic(TMImage.LEVEL_04_RUNE_N, TMSpriteLayer.BACKGROUND2, 28, 36, 2, 2));
		_entities.addAll(_runes);
		
		_pathTriggers = new FixedSizeArray<GameEntity>(8);
		_pathTriggers.add(EntityHelper.trigger(54,51, 3, 2));
		_pathTriggers.add(EntityHelper.trigger(54,48, 3, 2));
		_pathTriggers.add(EntityHelper.trigger(54,45, 3, 2));
		_pathTriggers.add(EntityHelper.trigger(54,42, 3, 2));
		_pathTriggers.add(EntityHelper.trigger(54,24, 3, 2));
		_pathTriggers.add(EntityHelper.trigger(54,21, 3, 2));
		_pathTriggers.add(EntityHelper.trigger(54,18, 3, 2));
		_pathTriggers.add(EntityHelper.trigger(54,15, 3, 2));
		_entities.addAll(_pathTriggers);
		
		_goalCheckTrigger = EntityHelper.trigger(86, 33, 2, 2);
		_entities.add(_goalCheckTrigger);
		
		_pathHits = new boolean[8];
		
		randomizeRunes();
		
		Manager.Message.subscribe(this, MessageType.TRIGGER_HIT);
	}
	
	@Override
	public void update() {
		super.update();
		if (_reset) {
			_reset = false;
			resetMaze();
		}
	}
	
	@Override
	public void handleMessage(Message message) {
		super.handleMessage(message);
		
		if (message.Type == MessageType.TRIGGER_HIT) {
			GameEntity trigger = message.getData();
			if (trigger == _goalCheckTrigger ) {
				boolean solutionFound = true;
				for (int i = 0; i < 8; i++) {
					if (i != _goalPath) {
						if (_pathHits[i]) {
							solutionFound = false;
							break;
						}
					}
				}
				if (solutionFound)
					_goalCheckTrigger.disableBehaviors();
				else
					_reset = true;
			}
			else if (trigger == _pathTriggers.get(0))
				_pathHits[0] = true;
			else if (trigger == _pathTriggers.get(1))
				_pathHits[1] = true;
			else if (trigger == _pathTriggers.get(2))
				_pathHits[2] = true;
			else if (trigger == _pathTriggers.get(3))
				_pathHits[3] = true;
			else if (trigger == _pathTriggers.get(4))
				_pathHits[4] = true;
			else if (trigger == _pathTriggers.get(5))
				_pathHits[5] = true;
			else if (trigger == _pathTriggers.get(6))
				_pathHits[6] = true;
			else if (trigger == _pathTriggers.get(7))
				_pathHits[7] = true;
		}
	}
	
	protected void resetMaze() {
		_player.Attributes.Area.changePosition(-71f * TMManager.Level.getCellWidth(), 0f);
		Global.Camera.move(-71f * TMManager.Level.getCellWidth(), 0f);
		for (int i = 0; i < 8; i++)
			_pathHits[i] = false;
		randomizeRunes();
	}
	
	protected void randomizeRunes() {
		
		Node root = new Node(null,
				new Node(_runes.get(0),
						new Node(_runes.get(2),
								new Node(_runes.get(6), null, null, true),
								new Node(_runes.get(7), null, null)),
						new Node(_runes.get(3),
								new Node(_runes.get(8), null, null),
								new Node(_runes.get(9), null, null))),
				new Node(_runes.get(1),
						new Node(_runes.get(4),
								new Node(_runes.get(10), null, null),
								new Node(_runes.get(11), null, null)),
						new Node(_runes.get(5),
								new Node(_runes.get(12), null, null),
								new Node(_runes.get(13), null, null))));
		
		root.Child0.Rune.Attributes.Area.setPosition(xGridPosition(28), yGridPosition(36));
		root.Child1.Rune.Attributes.Area.setPosition(xGridPosition(28), yGridPosition(30));
		
		root.Child0.Child0.Rune.Attributes.Area.setPosition(xGridPosition(39), yGridPosition(45));
		root.Child0.Child1.Rune.Attributes.Area.setPosition(xGridPosition(39), yGridPosition(39));
		
		root.Child1.Child0.Rune.Attributes.Area.setPosition(xGridPosition(39), yGridPosition(27));
		root.Child1.Child1.Rune.Attributes.Area.setPosition(xGridPosition(39), yGridPosition(21));
		
		root.Child0.Child0.Child0.Rune.Attributes.Area.setPosition(xGridPosition(42), yGridPosition(51));
		root.Child0.Child0.Child1.Rune.Attributes.Area.setPosition(xGridPosition(42), yGridPosition(45));
		
		root.Child0.Child1.Child0.Rune.Attributes.Area.setPosition(xGridPosition(42), yGridPosition(39));
		root.Child0.Child1.Child1.Rune.Attributes.Area.setPosition(xGridPosition(45), yGridPosition(36));
		
		root.Child1.Child0.Child0.Rune.Attributes.Area.setPosition(xGridPosition(45), yGridPosition(30));
		root.Child1.Child0.Child1.Rune.Attributes.Area.setPosition(xGridPosition(42), yGridPosition(27));
		
		root.Child1.Child1.Child0.Rune.Attributes.Area.setPosition(xGridPosition(42), yGridPosition(21));
		root.Child1.Child1.Child1.Rune.Attributes.Area.setPosition(xGridPosition(42), yGridPosition(15));
		
		if (root.Child0.Child0.Child0.Goal)
			_goalPath = 0;
		else if (root.Child0.Child0.Child1.Goal)
			_goalPath = 1;
		else if (root.Child0.Child1.Child0.Goal)
			_goalPath = 2;
		else if (root.Child0.Child1.Child1.Goal)
			_goalPath = 3;
		else if (root.Child1.Child0.Child0.Goal)
			_goalPath = 4;
		else if (root.Child1.Child0.Child1.Goal)
			_goalPath = 5;
		else if (root.Child1.Child1.Child0.Goal)
			_goalPath = 6;
		else if (root.Child1.Child1.Child1.Goal)
			_goalPath = 7;
	}

	protected class Node {
		public Node Child0 = null;
		public Node Child1 = null;
		public GameEntity Rune;
		public boolean Goal;
		
		public Node(GameEntity rune, Node child0, Node child1) {
			Rune = rune;
			Goal = false;
			if (new Random().nextInt(2) == 0) {
				Child0 = child0;
				Child1 = child1;
			}
			else {
				Child0 = child1;
				Child1 = child0;
			}
		}
		
		public Node(GameEntity rune, Node child0, Node child1, boolean goal) {
			Rune = rune;
			Goal = goal;
			if (new Random().nextInt(2) == 0) {
				Child0 = child0;
				Child1 = child1;
			}
			else {
				Child0 = child1;
				Child1 = child0;
			}
		}
	
	}
}
