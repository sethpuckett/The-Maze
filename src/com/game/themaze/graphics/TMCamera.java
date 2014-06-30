package com.game.themaze.graphics;

import com.game.loblib.graphics.Camera;
import com.game.loblib.messaging.Message;
import com.game.loblib.messaging.MessageType;
import com.game.loblib.utility.area.Vertex;
import com.game.themaze.utility.TMManager;

public class TMCamera extends Camera {
	
	@Override
	public void handleMessage(Message message) {
		if (message.Type == MessageType.SCREEN_SIZE_SET) {
			Vertex v = message.getData();
			CameraArea.setSize(v.X, v.Y  - TMManager.Level.getControlBarHeight());

			_coveredAreaMinCenterX = (CameraArea.Size.X / 2f) + CoveredArea.Position.X;
			_coveredAreaMaxCenterX = CoveredArea.Position.X + CoveredArea.Size.X - (CameraArea.Size.X / 2f);
			_coveredAreaMinCenterY = (CameraArea.Size.Y / 2f) + CoveredArea.Position.Y;
			_coveredAreaMaxCenterY = CoveredArea.Position.Y + CoveredArea.Size.Y - (CameraArea.Size.Y / 2f);
		}
	}
}
