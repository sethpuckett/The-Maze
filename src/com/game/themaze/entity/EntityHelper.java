package com.game.themaze.entity;

import com.game.loblib.collision.CollisionLayer;
import com.game.loblib.entity.GameEntity;
import com.game.loblib.graphics.Image;
import com.game.loblib.utility.FullScreenType;
import com.game.loblib.utility.Global;
import com.game.loblib.utility.Logger;
import com.game.loblib.utility.Manager;
import com.game.loblib.utility.area.AreaType;
import com.game.themaze.behavior.AttachBehavior;
import com.game.themaze.behavior.ButtonBehavior;
import com.game.themaze.behavior.CollisionCheckBehavior;
import com.game.themaze.behavior.CollisionSenderBehavior;
import com.game.themaze.behavior.DamageCheckBehavior;
import com.game.themaze.behavior.DelayedRenderBehavior;
import com.game.themaze.behavior.DestinationMoveBehavior;
import com.game.themaze.behavior.DoorBehavior;
import com.game.themaze.behavior.GameItemBehavior;
import com.game.themaze.behavior.GameItemCheckBehavior;
import com.game.themaze.behavior.GoalReachBehavior;
import com.game.themaze.behavior.MonsterRenderBehavior;
import com.game.themaze.behavior.PatrolDestinationBehavior;
import com.game.themaze.behavior.PatrolDestinationBehavior.PatrolType;
import com.game.themaze.behavior.PlayerMoveBehavior;
import com.game.themaze.behavior.PlayerRenderBehavior;
import com.game.themaze.behavior.RenderBehavior;
import com.game.themaze.behavior.ScrollingTileBehavior;
import com.game.themaze.behavior.TMBehaviorType;
import com.game.themaze.behavior.TileRenderBehavior;
import com.game.themaze.behavior.TimerBehavior;
import com.game.themaze.behavior.TouchDestinationBehavior;
import com.game.themaze.behavior.WallRenderBehavior;
import com.game.themaze.collision.TMCollisionLayer;
import com.game.themaze.gameitem.GameItemHelper;
import com.game.themaze.graphics.TMImage;
import com.game.themaze.graphics.TMSpriteLayer;
import com.game.themaze.utility.Debug;
import com.game.themaze.utility.DoorOpenType;
import com.game.themaze.utility.DoorType;
import com.game.themaze.utility.TMManager;

public class EntityHelper {
	public static final StringBuffer _tag = new StringBuffer("EntityHelper");

	public static GameEntity graphic(int image, int layer, boolean useCamera, float sizeX, float sizeY, boolean center, float positionX, float positionY) {
		GameEntity entity = new GameEntity();
		if (center) {
			entity.Attributes.Area.setCenter(positionX, positionY);
			entity.Attributes.Area.MaintainCenter = true;
		}
		else {
			entity.Attributes.Area.setPosition(positionX, positionY);
			entity.Attributes.Area.MaintainCenter = false;
		}
		entity.Attributes.Area.setSize(sizeX, sizeY); 
		entity.Attributes.Sprite = Manager.Sprite.make(image);
		entity.Attributes.Sprite.UseCamera = useCamera;
		entity.addBehavior(new RenderBehavior(layer));
		return entity;
	}
	
	public static GameEntity delayedGraphic(int image, int layer, boolean useCamera, float sizeX, float sizeY, boolean center, float positionX, float positionY, float delayTime, float fadeInTime) {
		GameEntity entity = new GameEntity();
		if (center) {
			entity.Attributes.Area.setCenter(positionX, positionY);
			entity.Attributes.Area.MaintainCenter = true;
		}
		else {
			entity.Attributes.Area.setPosition(positionX, positionY);
			entity.Attributes.Area.MaintainCenter = false;
		}
		entity.Attributes.Area.setSize(sizeX, sizeY); 
		entity.Attributes.Sprite = Manager.Sprite.make(image);
		entity.Attributes.Sprite.UseCamera = useCamera;
		entity.addBehavior(new DelayedRenderBehavior(layer, delayTime, fadeInTime));
		return entity;
	}
	
	public static GameEntity fullscreenGraphic(int image, int layer) {
		return fullscreenGraphic(image, layer, FullScreenType.FixedBoth);
	}
	
	public static GameEntity fullscreenGraphic(int image, int layer, FullScreenType type) {
		float sizeX = 0;
		float sizeY = 0;
		float posX = 0;
		float posY = 0;
		
		GameEntity entity = graphic(image, layer, false, 1, 1, false, 0, 0);
		float rawImageX = entity.Attributes.Sprite.Frames[2];
		float rawImageY = entity.Attributes.Sprite.Frames[1];
		
		switch (type) {
		case FixedBoth:
			sizeX = Global.Renderer.Width;
			sizeY = Global.Renderer.Height;
			posX = 0;
			posY = 0;
			break;
		case FixedX:
			sizeX = Global.Renderer.Width;
			sizeY = rawImageY * (sizeX / rawImageX);
			posX = 0;
			posY = - ((sizeY - Global.Renderer.Height) / 2f);
			break;
		case FixedY:
			sizeY = Global.Renderer.Height;
			sizeX = rawImageX * (sizeY / rawImageY);
			posY = 0;
			posX = - ((sizeX - Global.Renderer.Width) / 2f);
			break;
		default:
			Logger.e(_tag, "Invalid FullScreenType");
			return null;
		}
		
		entity.Attributes.Area.setSize(sizeX, sizeY);
		entity.Attributes.Area.setPosition(posX, posY);
		
		return entity;
	}
	
	public static GameEntity scrollingGraphic(int image, int layer, int direction, float speed) {
		return scrollingGraphic(image, layer, direction, speed, 0f, 0f, true);
	}
	
	public static GameEntity scrollingGraphic(int image, int layer, int direction, float speed, float startPos) {
		return scrollingGraphic(image, layer, direction, speed, startPos, 0f, true);
	}
	
	public static GameEntity scrollingGraphic(int image, int layer, int direction, float speed, float startPos, float fixedLength, boolean fixedVertical) {
		GameEntity entity = new GameEntity();
		entity.addBehavior(new ScrollingTileBehavior(image, layer, direction, speed, startPos, fixedLength, fixedVertical));
		return entity;
	}
	
	public static GameEntity tiledGraphic(int image, int layer, float tileLength, float areaPositionX, float areaPositionY, float areaLength) {
		GameEntity entity = new GameEntity();
		entity.addBehavior(new TileRenderBehavior(image, layer, tileLength, areaPositionX, areaPositionY, areaLength));
		return entity;
	}
	
	public static GameEntity button(int image, int layer, boolean useCamera, float sizeX, float sizeY, boolean center, float positionX, float positionY, AreaType areaType) {
		GameEntity entity = graphic(image, layer, useCamera, sizeX, sizeY, center, positionX, positionY);
		entity.addBehavior(new ButtonBehavior(areaType, useCamera));
		return entity;
	}
	
	public static GameEntity button(int image, int layer, boolean useCamera, float sizeX, float sizeY, boolean center, float positionX, float positionY, AreaType areaType, boolean clickOnRelease) {
		GameEntity entity = graphic(image, layer, useCamera, sizeX, sizeY, center, positionX, positionY);
		entity.addBehavior(new ButtonBehavior(areaType, useCamera, clickOnRelease));
		return entity;
	}

	public static GameEntity timer(int timerType, float time) {
		GameEntity entity = new GameEntity();
		entity.addBehavior(new TimerBehavior(timerType, time));
		return entity;
	}

	public static GameEntity wall(int xGrid, int yGrid, int length, boolean horizontal) {
		if (horizontal)
			return wall(TMImage.WALL_R, TMImage.WALL_L, TMImage.WALL_LR, xGrid, yGrid, length, horizontal);
		else
			return wall(TMImage.WALL_U, TMImage.WALL_D, TMImage.WALL_UD, xGrid, yGrid, length, horizontal);
	}
	
	public static GameEntity wall(int image, int xGrid, int yGrid, int length, boolean horizontal) {
		return wall(image, image, image, TMSpriteLayer.MAZE_HIGH, xGrid, yGrid, length, horizontal);
	}
	
	public static GameEntity wall(int image, int layer, int xGrid, int yGrid, int length, boolean horizontal) {
		return wall(image, image, image, layer, xGrid, yGrid, length, horizontal);
	}
	
	public static GameEntity wall(int firstImage, int lastImage, int midImage, int xGrid, int yGrid, int length, boolean horizontal) {
		return wall(firstImage, lastImage, midImage, TMSpriteLayer.MAZE_HIGH, xGrid, yGrid, length, horizontal);
	}
	
	public static GameEntity wall(int firstImage, int lastImage, int midImage, int layer, int xGrid, int yGrid, int length, boolean horizontal) {
		int cellWidth = TMManager.Level.getCellWidth();
		float x, y, width, height;
		x = cellWidth * (xGrid + TMManager.Level.getXOffset());
		y = cellWidth * (yGrid + TMManager.Level.getYOffset());
		if (horizontal) {
			width = cellWidth * length;
			height = cellWidth;
		}
		else {
			width = cellWidth;
			height = cellWidth * length;
		}
		
		GameEntity entity = new GameEntity();
		entity.Attributes.Area.MaintainCenter = false;
		entity.Attributes.Area.setPosition(x, y);
		entity.Attributes.Area.setSize(width, height);
		if (firstImage != Image.NONE)
			entity.addBehavior(new WallRenderBehavior(horizontal, layer, firstImage, lastImage, midImage, cellWidth));
		entity.addBehavior(new CollisionSenderBehavior(AreaType.Rectangle, TMCollisionLayer.MAIN_BLOCK));
		return entity;
	}
/*
	public static GameEntity spikes(int xGrid, int yGrid, int length, boolean horizontal) {
		if (horizontal)
			return spikes(Image.SPIKES_LR, xGrid, yGrid, length, horizontal);
		else
			return spikes(Image.SPIKES_UD, xGrid, yGrid, length, horizontal);
	}
	
	public static GameEntity spikes(int image, int xGrid, int yGrid, int length, boolean horizontal) {
		return spikes(image, image, image, xGrid, yGrid, length, horizontal);
	}
	
	public static GameEntity spikes(int firstImage, int lastImage, int midImage, int xGrid, int yGrid, int length, boolean horizontal) {
		int cellWidth = Manager.Level.getCellWidth();
		float x, y, width, height;
		x = cellWidth * (xGrid + Manager.Level.getXOffset());
		y = cellWidth * (yGrid + Manager.Level.getYOffset());
		if (horizontal) {
			y += cellWidth * .1f;
			width = cellWidth * length;
			height = cellWidth * 1.8f;
		}
		else {
			x += cellWidth * .1f;
			width = cellWidth * 1.8f;
			height = cellWidth * length;
		}
		
		GameEntity entity = new GameEntity();
		entity.Attributes.Area.MaintainCenter = false;
		entity.Attributes.Area.setPosition(x, y);
		entity.Attributes.Area.setSize(width, height);
		if (firstImage != Image.NONE)
			entity.addBehavior(new WallRenderBehavior(horizontal, SpriteLayer.MAZE_LOW, firstImage, lastImage, midImage, (int)(cellWidth * 1.8f)));
		entity.addBehavior(new CollisionSenderBehavior(AreaType.Rectangle, CollisionLayer.DAMAGE));
		return entity;
	}
	*/
	public static GameEntity spikes(int xGrid, int yGrid, int length, boolean horizontal) {
		if (horizontal)
			return spikes(TMImage.SPIKES_LR, xGrid, yGrid, length, horizontal);
		else
			return spikes(TMImage.SPIKES_UD, xGrid, yGrid, length, horizontal);
	}
	
	public static GameEntity spikes(int image, int xGrid, int yGrid, int length, boolean horizontal) {
		return spikes(image, image, image, xGrid, yGrid, length, horizontal);
	}
	
	public static GameEntity spikes(int firstImage, int lastImage, int midImage, int xGrid, int yGrid, int length, boolean horizontal) {
		int cellWidth = TMManager.Level.getCellWidth();
		float x, y, width, height;
		x = cellWidth * (xGrid + TMManager.Level.getXOffset());
		y = cellWidth * (yGrid + TMManager.Level.getYOffset());
		if (horizontal) {
			width = cellWidth * length;
			height = cellWidth * 2f;
		}
		else {
			width = cellWidth * 2f;
			height = cellWidth * length;
		}
		
		GameEntity entity = new GameEntity();
		entity.Attributes.Area.MaintainCenter = false;
		entity.Attributes.Area.setPosition(x, y);
		entity.Attributes.Area.setSize(width, height);
		if (firstImage != Image.NONE)
			entity.addBehavior(new WallRenderBehavior(horizontal, TMSpriteLayer.MAZE_LOW, firstImage, lastImage, midImage, (int)(cellWidth * 2f)));
		entity.addBehavior(new CollisionSenderBehavior(AreaType.Rectangle, TMCollisionLayer.DAMAGE));
		return entity;
	}
	
	public static GameEntity singleSpike(int xGrid, int yGrid) {
		return singleSpike(TMImage.SPIKES_SINGLE, xGrid, yGrid);
	}
	
	public static GameEntity singleSpike(int image, int xGrid, int yGrid) {
		int cellWidth = TMManager.Level.getCellWidth();
		float x, y;
		x = cellWidth * (xGrid + TMManager.Level.getXOffset());
		y = cellWidth * (yGrid + TMManager.Level.getYOffset());
		
		GameEntity entity = new GameEntity();
		entity.Attributes.Area.MaintainCenter = false;
		entity.Attributes.Area.setPosition(x, y);
		entity.Attributes.Area.setSize(cellWidth * 2f, cellWidth * 2f);
		if (image != Image.NONE) {
			entity.Attributes.Sprite = Manager.Sprite.make(image);
			entity.addBehavior(new RenderBehavior(TMSpriteLayer.MAZE_LOW));
		}
		entity.addBehavior(new CollisionSenderBehavior(AreaType.Rectangle, TMCollisionLayer.DAMAGE));
		return entity;
	}
	
	public static GameEntity trigger(int xGrid, int yGrid, int xSize, int ySize) {
		int cellWidth = TMManager.Level.getCellWidth();
		GameEntity entity = new GameEntity();
		entity.Attributes.Area.Position.X = (TMManager.Level.getXOffset() + xGrid) * cellWidth;
		entity.Attributes.Area.Position.Y = (TMManager.Level.getYOffset() + yGrid) * cellWidth;
		entity.Attributes.Area.Size.X = xSize * cellWidth;
		entity.Attributes.Area.Size.Y = ySize * cellWidth;
		entity.addBehavior(new CollisionSenderBehavior(AreaType.Rectangle, CollisionLayer.TRIGGER));
		if (Debug.ShowTriggers) {
			entity.Attributes.Sprite = Manager.Sprite.make(TMImage.RED);
			entity.Attributes.Sprite.Alpha = .4f;
			entity.addBehavior(new RenderBehavior(TMSpriteLayer.FOREGROUND));
		}
		return entity;
	}
	
	public static GameEntity player(float xPos, float yPos, int direction) {
		int cellWidth = TMManager.Level.getCellWidth();
		GameEntity entity = new GameEntity();
		entity.Attributes.Direction.Undefined = true;
		entity.Attributes.Destination.Undefined = true;
		entity.Attributes.Area.setCenter(xPos, yPos);
		entity.Attributes.Area.setSize( (3f * cellWidth / 4f), cellWidth);
		entity.Attributes.Speed = (float)cellWidth / 11f;
		entity.Attributes.Sprite = Manager.Sprite.make(TMImage.PLAYER_HUMAN);
		entity.addBehavior(new PlayerRenderBehavior(TMSpriteLayer.PLAYER));
		((PlayerRenderBehavior)entity.getBehavior(TMBehaviorType.PLAYER_RENDER)).setDirection(direction);
		entity.addBehavior(new TouchDestinationBehavior());
		entity.addBehavior(new PlayerMoveBehavior());
		entity.addBehavior(new GoalReachBehavior());
		if (!Debug.Invincible)
			entity.addBehavior(new DamageCheckBehavior());
		entity.addBehavior(new GameItemCheckBehavior());
		entity.addBehavior(new CollisionCheckBehavior(CollisionLayer.TRIGGER));
		return entity;
	}

	public static GameEntity goal(float xPos, float yPos, int direction) {
		int cellWidth = TMManager.Level.getCellWidth();
		GameEntity entity = new GameEntity();
		entity.Attributes.Area.setCenter(xPos, yPos);
		entity.Attributes.Area.setSize(cellWidth * 1.8f, cellWidth * 1.8f);
		entity.Attributes.Sprite = TMManager.Sprite.make(TMImage.MONSTER);
		entity.addBehavior(new MonsterRenderBehavior(TMSpriteLayer.PLAYER, direction));
		
		
		
		//GameEntity entity = EntityHelper.graphic(Image.MONSTER, SpriteLayer.PLAYER,
		//		true, cellWidth, cellWidth, true, xPos, yPos);
		entity.addBehavior(new CollisionSenderBehavior(AreaType.Rectangle, TMCollisionLayer.GOAL));
		return entity;
	}
	
	public static GameEntity levelGraphic(int image, int layer, float xGrid, float yGrid, float xSize, float ySize) {
		int cellWidth = TMManager.Level.getCellWidth();
		return EntityHelper.graphic(image, layer, true, xSize * cellWidth, ySize * cellWidth, false,
				(TMManager.Level.getXOffset() + xGrid) * cellWidth, (TMManager.Level.getYOffset() + yGrid) * cellWidth);
	}
	
	public static GameEntity delayedLevelGraphic(int image, int layer, float xGrid, float yGrid, float xSize, float ySize, float delayTime, float fadeInTime) {
		int cellWidth = TMManager.Level.getCellWidth();
		return EntityHelper.delayedGraphic(image, layer, true, xSize * cellWidth, ySize * cellWidth, false,
				(TMManager.Level.getXOffset() + xGrid) * cellWidth, (TMManager.Level.getYOffset() + yGrid) * cellWidth, delayTime, fadeInTime);
	}
	
	public static GameEntity patrolAnchor(int xGrid, int yGrid, float speed) {
		return patrolAnchor (xGrid, yGrid, speed, 0, 0);
	}
	
	public static GameEntity patrolAnchor(int xGrid, int yGrid, float speed, int holdTime) {
		return patrolAnchor(xGrid, yGrid, speed, holdTime, 0);
	}

	public static GameEntity patrolAnchor(int xGrid, int yGrid, float speed, int holdTime, int startOffset) {
		return patrolAnchor(xGrid, yGrid, speed, holdTime, startOffset, PatrolType.CONTINUOUS);
	}
	
	public static GameEntity patrolAnchor(int xGrid, int yGrid, float speed, int holdTime, int startOffset, int patrolType) {
		int cellWidth = TMManager.Level.getCellWidth();	
		GameEntity anchor = new GameEntity();
		anchor.Attributes.Area.Position.X = (TMManager.Level.getXOffset() + xGrid) * cellWidth;
		anchor.Attributes.Area.Position.Y = (TMManager.Level.getYOffset() + yGrid) * cellWidth;
		anchor.Attributes.Speed = speed;
		anchor.addBehavior(new DestinationMoveBehavior());
		anchor.addBehavior(new PatrolDestinationBehavior(holdTime, startOffset, patrolType));
		return anchor;
	}

	public static GameEntity anchorWall(GameEntity anchor, int length, boolean horizontal, int xGridOffset, int yGridOffset) {
		int cellWidth = TMManager.Level.getCellWidth();
		GameEntity wall = wall(0, 0, length, horizontal);
		wall.addBehavior(new AttachBehavior(anchor, xGridOffset * cellWidth, yGridOffset * cellWidth));
		return wall;
	}
	
	public static GameEntity anchorWall(GameEntity anchor, int image, int length, boolean horizontal, int xGridOffset, int yGridOffset) {
		int cellWidth = TMManager.Level.getCellWidth();
		GameEntity wall = wall(image, TMSpriteLayer.MAZE_LOW, 0, 0, length, horizontal);
		wall.addBehavior(new AttachBehavior(anchor, xGridOffset * cellWidth, yGridOffset * cellWidth));
		return wall;
	}
	
	public static GameEntity anchorSpikes(GameEntity anchor, int length, boolean horizontal, int xGridOffset, int yGridOffset) {
		int cellWidth = TMManager.Level.getCellWidth();
		GameEntity spikes = spikes(0, 0, length, horizontal);
		spikes.addBehavior(new AttachBehavior(anchor, xGridOffset * cellWidth, yGridOffset * cellWidth));
		return spikes;
	}
	
	public static GameEntity anchorSpikes(GameEntity anchor, int firstImage, int lastImage, int midImage, int length, boolean horizontal, int xGridOffset, int yGridOffset) {
		int cellWidth = TMManager.Level.getCellWidth();
		GameEntity spikes = spikes(firstImage, lastImage, midImage, 0, 0, length, horizontal);
		spikes.addBehavior(new AttachBehavior(anchor, xGridOffset * cellWidth, yGridOffset * cellWidth));
		return spikes;
	}
	
	public static GameEntity gameItem(int xGrid, int yGrid, int gameItemType) {
		return gameItem(xGrid, yGrid, gameItemType, false);
	}
	
	public static GameEntity gameItem(int xGrid, int yGrid, int gameItemType, boolean center) {
		int cellWidth = TMManager.Level.getCellWidth();		
		GameEntity entity = new GameEntity();
		entity.Attributes.Area.MaintainCenter = true;
		if (center) {
			entity.Attributes.Area.setCenter(TMManager.Level.getXOffset() + xGrid * cellWidth,
				(TMManager.Level.getYOffset() + yGrid) * cellWidth);
		}
		else {
			entity.Attributes.Area.Position.X = (TMManager.Level.getXOffset() + xGrid) * cellWidth;
			entity.Attributes.Area.Position.Y = (TMManager.Level.getYOffset() + yGrid) * cellWidth;
		}
		entity.Attributes.Area.setSize(cellWidth, cellWidth);
		entity.Attributes.Sprite = Manager.Sprite.make(GameItemHelper.getLevelSprite(gameItemType));
		entity.addBehavior(new RenderBehavior(TMSpriteLayer.BACKGROUND2));
		entity.addBehavior(new CollisionSenderBehavior(AreaType.Rectangle, TMCollisionLayer.GAME_ITEM));
		entity.addBehavior(new GameItemBehavior(gameItemType));
		return entity;		
	}
	
	public static GameEntity doorAnchor(int closedXGrid, int closedYGrid, int openXGrid, int openYGrid) {
		return doorAnchor(closedXGrid, closedYGrid, openXGrid, openYGrid, DoorType.NEUTRAL, DoorOpenType.SINGLE, false);
	}
	
	public static GameEntity doorAnchor(int closedXGrid, int closedYGrid, int openXGrid, int openYGrid, int doorType, int doorOpenType, boolean open) {
		int cellWidth = TMManager.Level.getCellWidth();	
		GameEntity anchor = new GameEntity();
		anchor.Attributes.Speed = TMManager.Level.getDoorSpeed();
		anchor.addBehavior(new DestinationMoveBehavior());
		anchor.addBehavior(new DoorBehavior(TMManager.Level.getXOffset() + closedXGrid * cellWidth,
				TMManager.Level.getYOffset() + closedYGrid * cellWidth,
				TMManager.Level.getXOffset() + openXGrid * cellWidth,
				TMManager.Level.getYOffset() + openYGrid * cellWidth,
				doorType, 
				doorOpenType,
				open));
		return anchor;
	}
}
