package com.game.themaze.level;

import com.game.themaze.utility.TMGameSettings;

public class RandomLevel extends Level {
	public RandomLevel() {
		_settings.CellWidth = TMGameSettings.GetZoomMedium();
		_settings.RandomizeMaze = true;
		_settings.RandomMazeHeight = TMGameSettings.MAZE_SIZE_LARGE;
		_settings.RandomMazeWidth = TMGameSettings.MAZE_SIZE_LARGE;
	}
}
