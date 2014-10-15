package com.eje.skull;

import com.eje.framework.Screen;
import com.eje.framework.impl.AndroidGame;

public class SkullGame extends AndroidGame{

	@Override
	public Screen getStartScreen() {
		return new LoadingScreen(this); 
	}

}
