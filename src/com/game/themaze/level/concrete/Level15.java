package com.game.themaze.level.concrete;

import com.game.loblib.utility.Direction;
import com.game.loblib.utility.Global;
import com.game.the_maze.R;
import com.game.themaze.entity.EntityHelper;
import com.game.themaze.graphics.TMImage;
import com.game.themaze.graphics.TMSpriteLayer;
import com.game.themaze.level.Level;

// Spotlights
public class Level15 extends Level {
	
	public Level15() {
		_settings.ResourceId = R.raw.level_05;
		_settings.CellWidth = (int)(Global.Renderer.Width / 16f);
		_settings.BackgroundImage = TMImage.BACKGROUND_DIRT_GRAY;
	}
	
	@Override
	public void init() {
		super.init();

		_entities.add(EntityHelper.scrollingGraphic(TMImage.BLACK_HOLES, TMSpriteLayer.FOREGROUND, Direction.LEFT, 1f, 0f, Global.Renderer.Width * 1.8f, true));
	}
}