package com.game.themaze.level;

import com.game.loblib.graphics.Image;
import com.game.the_maze.R;
import com.game.themaze.gameitem.GameItemType;

public class LevelSettings {
	// Determines how large each cell (and other entities) will appear; A larger value effectively moves the camera 'closer'
	public int CellWidth = 25;
	// File containing map; Ignored if RandomizeMaze == true
	public int ResourceId = R.raw.sample;
	// If true player cannot go into camera mode to scroll through the map
	public boolean DisableCamera = false;
	// If true red goal direction indicator is not displayed
	public boolean DisableGoalIndicator = false;
	// If true action button is displayed, but not enabled without action triggers
	public boolean EnableActionButton = false;
	public int Item1 = GameItemType.UNKNOWN;
	public int Item2 = GameItemType.UNKNOWN;
	public int Item3 = GameItemType.UNKNOWN;
	public int BackgroundImage = Image.NONE;
	// Number of times to tile background image; A larger number will result in a smaller, more tiled background
	public int BackgroundTileCount = 8;
	// Custom wall sprite set
	public WallSpriteSet WallSprites = new WallSpriteSet();
	
	// Options below deal with random maze generation; Ignored unless RandomizeMaze == true
	public boolean RandomizeMaze = false;
	// Width - 1 should be divisible by 3 for proper hall/wall count
	public int RandomMazeWidth = 0;
	// Height - 1 should be divisible by 3 for proper hall/wall count
	public int RandomMazeHeight = 0;
	
	// Options below deal with hazards that can be enabled for randomized mazes
	public boolean HazardEnableSpikeRings = false;
	public boolean HazardEnableFastPlayer = false;
	public boolean HazardEnableTimer = false;
	// Amount of time, in seconds, player has to complete level
	public int HazardTimerValue = 0;
	public boolean HazardEnableReverseTouch = false;
	public boolean HazardEnableWind = false;
	public boolean HazardEnableChaseSpikes = false;
	public boolean HazardEnableSpotlights = false;
	public boolean HazardEnableInvisibleWalls = false;
}
