package com.game.themaze.utility;

import com.game.loblib.utility.ComponentFactory;
import com.game.loblib.utility.Manager;
import com.game.themaze.level.LevelManager;

public class TMManager extends Manager {
	protected static StringBuffer _tag = new StringBuffer("TMManager");
	
	public static LevelManager Level;
	
	@Override
	public void init(ComponentFactory factory) {
		Level = ((TMComponentFactory)factory).CreateLevelManager();
		
		super.init(factory);
	}
}
