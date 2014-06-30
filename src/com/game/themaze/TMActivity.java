package com.game.themaze;

import android.os.Bundle;

import com.game.loblib.LobLibActivity;
import com.game.themaze.utility.TMComponentFactory;
import com.game.themaze.utility.TMGameSettings;
import com.game.themaze.utility.TMGlobal;

public class TMActivity extends LobLibActivity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
		_componentFactory = new TMComponentFactory();
        super.onCreate(savedInstanceState);
        TMGlobal.TMSettings = new TMGameSettings();
    } 
	
    @Override
    protected void onPause() {
    	super.onPause();
    }
    
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    } 
    
    @Override
    protected void onResume() {
    	super.onResume();
    }
}