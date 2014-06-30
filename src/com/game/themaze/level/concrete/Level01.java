package com.game.themaze.level.concrete;

import com.game.loblib.utility.Global;
import com.game.the_maze.R;
import com.game.themaze.graphics.TMImage;
import com.game.themaze.level.Level;

//Introduction Level
public class Level01 extends Level {
	
	public Level01() {
		_settings.ResourceId = R.raw.level_01;
		_settings.CellWidth = (int)(Global.Renderer.Width / 12f);
		_settings.BackgroundImage = TMImage.BACKGROUND_COPPER;
	}
}
