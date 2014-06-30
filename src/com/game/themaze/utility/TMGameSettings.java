package com.game.themaze.utility;

import android.content.SharedPreferences.Editor;

import com.game.loblib.utility.GameSettings;
import com.game.loblib.utility.Global;
import com.game.loblib.utility.Logger;

public class TMGameSettings extends GameSettings {

	
	public void setLevelComplete(int level, boolean complete) {
		if (level < 0 || level > 34) {
			Logger.e(_tag, "Level out of range");
			return;
		}
		Editor editor = _prefs.edit();
		editor.putBoolean("level_complete_" + level, complete);
		editor.commit();
	}
	
	public boolean getLevelComplete(int level) {
		if (level < 0 || level > 34) {
			Logger.e(_tag, "Level out of range");
			return false;
		}
		return _prefs.getBoolean("level_complete_" + level, false);
	}
	
	public int getCompletedLevelCount() {
		int total = 0;
		for (int i = 0; i < 34; i++) {
			if (getLevelComplete(i))
				total++;
		}
		return total;
	}

	public void setCurrentChapter(int chapter) {
		if (chapter < 0 || chapter > 6) {
			Logger.e(_tag, "Chapter out of range");
			return;
		}
		Editor editor = _prefs.edit();
		editor.putInt("current_chapter", chapter);
		editor.commit();
	}
	
	public int getCurrentChapter() {
		return _prefs.getInt("current_chapter", 0);
	}

	public int getMinZoom() {
		return (int)(Global.Renderer.Width / 32f);
	}
	
	public int getMedZoom() {
		return (int)(Global.Renderer.Width / 16f);
	}
	
	public int getMaxZoom() {
		return (int)(Global.Renderer.Width / 8f);
	}
	
	public final static int SMALL_MAZE_SIZE = 58;
	
	public final static int MEDIUM_MAZE_SIZE = 76;
	
	public final static int LARGE_MAZE_SIZE = 94;
}
