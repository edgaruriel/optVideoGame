package com.eje.fallapart.services;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;

public class InputsProcessor extends InputAdapter{

	public boolean mouseMoved(int x, int y) {
		Inputs.x = x;
		Inputs.y = y;
		return true;
	}
	
	public boolean touchDragged(int x, int y, int pointer) {
		Inputs.x = x;
		Inputs.y = y;
		Inputs.down = true;
		return true;
	}
	
	public boolean touchDown(int x, int y, int pointer, int button) {
		
		Inputs.x = x;
		Inputs.y = y;
		Inputs.down = true;
		return true;
	}
	
	public boolean touchUp(int x, int y, int pointer, int button) {
		Inputs.x = x;
		Inputs.y = y;
		Inputs.down = false;
		return true;
	}
	
	public boolean keyDown(int k) {
		if(k == Keys.Z) Inputs.setKey(Inputs.BUTTON1, true);
		if(k == Keys.X) Inputs.setKey(Inputs.BUTTON2, true); 
		return true;
	}
	
	public boolean keyUp(int k) {
		if(k == Keys.Z) Inputs.setKey(Inputs.BUTTON1, false);
		if(k == Keys.X) Inputs.setKey(Inputs.BUTTON2, false); 
		return true;
	}
}
