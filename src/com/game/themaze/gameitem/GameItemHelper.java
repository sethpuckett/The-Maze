package com.game.themaze.gameitem;

import com.game.loblib.graphics.Image;
import com.game.themaze.graphics.TMImage;

public class GameItemHelper {
	public static int getLevelSprite(int gameItemType) {
		switch (gameItemType) {
		case GameItemType.RED_KEY:
			return TMImage.RED_KEY;
		case GameItemType.BLUE_KEY:
			return TMImage.BLUE_KEY;
		case GameItemType.YELLOW_KEY:
			return TMImage.YELLOW_KEY;
		case GameItemType.SPEED_BOOTS:
			return TMImage.SPEED_BOOTS_LEVEL;
		case GameItemType.LAMP:
			return TMImage.LAMP;
		default:
			return Image.UNKNOWN;
		}
	}
	
	public static int getMenuSprite(int gameItemType) {
		switch (gameItemType) {
		case GameItemType.RED_KEY:
			return TMImage.RED_KEY;
		case GameItemType.BLUE_KEY:
			return TMImage.BLUE_KEY;
		case GameItemType.YELLOW_KEY:
			return TMImage.YELLOW_KEY;
		case GameItemType.SPEED_BOOTS:
			return TMImage.SPEED_BOOTS;
		case GameItemType.LAMP:
			return TMImage.LAMP;
		default:
			return Image.UNKNOWN;
		}
	}
	
	public static int getActivationType(int gameItemType) {
		switch (gameItemType) {
		case GameItemType.RED_KEY:
		case GameItemType.YELLOW_KEY:
		case GameItemType.BLUE_KEY:
			return GameItemActivationType.PASSIVE;
		case GameItemType.SPEED_BOOTS:
		case GameItemType.LAMP:
			return GameItemActivationType.TOGGLE;
		default:
			return GameItemActivationType.UNKNOWN;
		}
	}
}
