package com.game.themaze.level;

import com.game.themaze.graphics.TMImage;
import com.game.themaze.utility.TMGameSettings;
import com.game.themaze.utility.TMGlobal;

public class RandomLevel extends Level {
	public RandomLevel() {
		_settings.CellWidth = ((TMGameSettings)TMGlobal.Settings).getMedZoom();
		_settings.BackgroundImage = TMImage.BACKGROUND_SAND;
		_settings.RandomizeMaze = true;
		_settings.RandomMazeHeight = TMGameSettings.LARGE_MAZE_SIZE;
		_settings.RandomMazeWidth = TMGameSettings.LARGE_MAZE_SIZE;
	}
}
