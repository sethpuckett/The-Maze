package com.game.themaze;

import com.game.loblib.LobLibGame;
import com.game.loblib.utility.ComponentFactory;
import com.game.themaze.utility.TMManager;

public class TMGame extends LobLibGame {
	protected static StringBuffer _tag = new StringBuffer("TMGame");
	
	@Override
	public void init(ComponentFactory factory) {
		_manager = new TMManager();
		super.init(factory);
	}
}

