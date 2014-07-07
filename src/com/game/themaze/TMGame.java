package com.game.themaze;

import com.game.loblib.LobLibGame;
import com.game.loblib.messaging.IMessageHandler;
import com.game.loblib.messaging.Message;
import com.game.loblib.messaging.MessageType;
import com.game.loblib.utility.ComponentFactory;
import com.game.loblib.utility.Manager;
import com.game.themaze.utility.TMGlobal;
import com.game.themaze.utility.TMManager;

public class TMGame extends LobLibGame implements IMessageHandler{
	protected static StringBuffer _tag = new StringBuffer("TMGame");
	
	@Override
	public void init(ComponentFactory factory) {
		_manager = new TMManager();
		super.init(factory);
		
		Manager.Message.subscribe(this, MessageType.SURFACE_CREATED);
	}
	
	@Override
	public void handleMessage(Message message) {
		if (message.Type == MessageType.SURFACE_CREATED) {
	        TMGlobal.TextManager.addText("CALIBRI SMALL", "CALIBRI.TTF", 48, 6, 6);
	        TMGlobal.TextManager.addText("CALIBRI BIG", "CALIBRI.TTF", 72, 8, 8);
		}
	}
	
	@Override
	public void onPause() {
		Manager.Message.unsubscribe(this, MessageType.ALL);		
		super.onPause();
	}
	
	@Override
	public void onResume() {
		Manager.Message.subscribe(this, MessageType.SURFACE_CREATED);
		super.onResume();
	}
	
	@Override
	public void onStop() {
		Manager.Message.unsubscribe(this, MessageType.ALL);		
		super.onStop();
	}
}

