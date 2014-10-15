package com.eje.skull;

import java.util.List;

import android.os.Debug;
import android.util.Log;

import com.eje.framework.Game;
import com.eje.framework.Graphics;
import com.eje.framework.Input.TouchEvent;
import com.eje.framework.Screen;

public class MainMenuScreen extends Screen {

	public MainMenuScreen(Game game) {
        super(game);               
    }

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();       
        
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) {
                if(inBounds(event, 50, 220, 225, 96) ) {
                	Log.i("touch", "true");
                    game.setScreen(new GameScreen(game));
                    return;
                }else{
                	Log.i("touch", "false");
                }
            }
        }
		
	}

	@Override
	public void present(float deltaTime) {
		Graphics g = game.getGraphics();
        
        g.drawPixmap(Assets.background, 0, 0);
        //g.drawPixmap(Assets.logo, 32, 20);
        g.drawPixmap(Assets.mainMenu, 50, 220);
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}  
	
	private boolean inBounds(TouchEvent event, int x, int y, int width, int height) {
        if(event.x > x && event.x < x + width - 1 && 
           event.y > y && event.y < y + height - 1) 
            return true;
        else
            return false;
    }
}
