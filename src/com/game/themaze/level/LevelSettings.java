package com.game.themaze.level;

import com.game.loblib.graphics.Image;
import com.game.the_maze.R;
import com.game.themaze.gameitem.GameItemType;

public class LevelSettings {
	
	public static class LevelSettingPeekType {
		public static final int Unknown = 0;
		public static final int Off = 1;
		public static final int Normal = 2;
		public static final int Maximum = 3;
	}
	
	public static class LevelSettingInvisibleWallType {
		public static final int Unknown = 0;
		public static final int Off = 1;		// Walls are visible
		public static final int Toggle = 2;		// Walls are invisible until touched
		public static final int FadeSlow = 3;	// Walls are invisible until touched; and then slowly fade back to invisible
		public static final int FadeFast = 4;	// Walls are invisible until touched; and then quickly fade back to invisible
		public static final int Always = 5;		// Walls are always invisible
	}
	
	// Determines how large each cell (and other entities) will appear; A larger value effectively moves the camera 'closer'
	public int CellWidth = 25;
	// File containing map; Ignored if RandomizeMaze == true
	public int ResourceId = R.raw.sample;
	// Determines how far (or if it all) player can scroll the map in peek mode
	public int PeekType = LevelSettingPeekType.Normal;
	// If true red goal direction indicator is displayed
	public boolean EnableGoalIndicator = true;
	// If true action button is displayed, but not enabled without action triggers
	public boolean EnableActionButton = true;
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
	public int HazardEnableInvisibleWalls = LevelSettingInvisibleWallType.Off;
}
