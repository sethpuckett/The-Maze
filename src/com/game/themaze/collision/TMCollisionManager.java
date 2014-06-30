package com.game.themaze.collision;

import com.game.loblib.collision.CollisionManager;
import com.game.loblib.messaging.IMessageHandler;
import com.game.loblib.messaging.Message;
import com.game.loblib.utility.area.Rectangle;
import com.game.themaze.level.Level;
import com.game.themaze.messaging.TMMessageType;
import com.game.themaze.utility.TMManager;

public class TMCollisionManager extends CollisionManager implements IMessageHandler {
	
	@Override
	public void init() {
		TMManager.Message.subscribe(this, TMMessageType.LEVEL_CELL_WIDTH_SET);
	}
	
	@Override
	public void handleMessage(Message message) {
		//update grid size/position when level cell width changes
		if (message.Type == TMMessageType.LEVEL_CELL_WIDTH_SET) {
			float width = (float)Level.CELL_COUNT * (float)TMManager.Level.getCellWidth() / (float)PARTITION_ROW;
			for (int y = PARTITION_ROW - 1; y >= 0; y--) {
				for (int x = 0; x < PARTITION_ROW; x++) {
					Rectangle rec = _grid.get((y * PARTITION_ROW) + x);
					rec.setSize(width, width);
					rec.setPosition(x * width, y * width);
				}
			}
		}
	}
}
