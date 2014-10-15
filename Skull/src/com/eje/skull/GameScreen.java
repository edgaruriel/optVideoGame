package com.eje.skull;

import java.util.List;

import com.eje.framework.Game;
import com.eje.framework.Graphics;
import com.eje.framework.Pixmap;
import com.eje.framework.Input.TouchEvent;
import com.eje.framework.Screen;

public class GameScreen extends Screen{

	 enum GameState {
	        Ready,
	        Running,
	        Paused,
	        GameOver
	}
	
	GameState state = GameState.Ready;
	World world;
	int oldScore = 0;
    String score = "0";
    
	public GameScreen(Game game) {
        super(game);
        world = new World();
    }

	@Override
	public void update(float deltaTime) {
		 List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
	        game.getInput().getKeyEvents();
	        
	        if(state == GameState.Ready)
	            updateReady(touchEvents);
	        if(state == GameState.Running)
	            updateRunning(touchEvents, deltaTime);
	        if(state == GameState.Paused)
	            updatePaused(touchEvents);
	        if(state == GameState.GameOver)
	            updateGameOver(touchEvents);     
		
	}

	private void updateReady(List<TouchEvent> touchEvents) {
        if(touchEvents.size() > 0)
            state = GameState.Running;
    }
	
	private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {        
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) {
                if(event.x < 70 && event.y < 80) {
                    state = GameState.Paused;
                    return;
                }
            }else{
            	world.skeleton.turn();
            }
            
        }
        
        world.update(deltaTime);
        if(world.gameOver) {
            state = GameState.GameOver;
        }
        
        if(oldScore != world.score) {
            oldScore = world.score;
            score = "" + oldScore;
        }
        
    }

    private void updatePaused(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) {
                if(event.x > 40 && event.x <= 200) {
                    if(event.y > 100 && event.y <= 148) {
                        state = GameState.Running;
                        return;
                    }                    
                    if(event.y > 148 && event.y < 196) {
                        game.setScreen(new MainMenuScreen(game)); 
                        return;
                    }
                }
            }
        }
    }

    private void updateGameOver(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) {
                    game.setScreen(new MainMenuScreen(game));
                    return;
                
            }
        }
    }
    
	@Override
	public void present(float deltaTime) {
		Graphics g = game.getGraphics();
        
        g.drawPixmap(Assets.background, 0, 0);
        g.drawPixmap(Assets.leftBorder, 0, 0);
        g.drawPixmap(Assets.rightBorder, 280, 0);
        drawWorld(world);
        if(state == GameState.Ready) 
            drawReadyUI();
        if(state == GameState.Running)
            drawRunningUI();
        if(state == GameState.Paused)
            drawPausedUI();
        if(state == GameState.GameOver)
            drawGameOverUI();
        drawText(g, score, g.getWidth() / 2 - score.length()*20 / 2, g.getHeight() - 42);
		
	}

	private void drawReadyUI() {
        Graphics g = game.getGraphics();
        
        g.drawPixmap(Assets.ready, 47, 100);
        //g.drawLine(0, 416, 480, 416, Color.BLACK);
    }
    
    private void drawRunningUI() {
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.pauseBtn, 0, 0);
    }
    
    private void drawPausedUI() {
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.pause, 40, 100);
    }

    private void drawGameOverUI() {
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.gameOver, 62, 100);
    }
    
    private void drawWorld(World world) {
    	Graphics g = game.getGraphics();
    	Skeleton skeleton = world.skeleton;
    	Enemy enemy = world.enemy;
    	
    	Pixmap stainPixmap = null;
        if(enemy.type == Enemy.TYPE_1)
            stainPixmap = Assets.enemy1;
        if(enemy.type == Enemy.TYPE_2)
            stainPixmap = Assets.enemy2;
        if(enemy.type == Enemy.TYPE_3)
            stainPixmap = Assets.enemy3;
        int x = enemy.x * 32;
        int y = enemy.y * 32;      
        g.drawPixmap(stainPixmap, x, y);    
        
    	x = skeleton.x * 32 + 56;
    	y = skeleton.y * 32 + 64;
    	Pixmap player = Assets.player;
    	g.drawPixmap(player, x - player.getWidth() / 2, y - player.getWidth() / 2);
    }
    
    public void drawText(Graphics g, String line, int x, int y) {
        int len = line.length();
        for (int i = 0; i < len; i++) {
            char character = line.charAt(i);

            if (character == ' ') {
                x += 20;
                continue;
            }

            int srcX = 0;
            int srcWidth = 0;
            if (character == '.') {
                srcX = 200;
                srcWidth = 10;
            } else {
                srcX = (character - '0') * 20;
                srcWidth = 20;
            }

            g.drawPixmap(Assets.numbers, x, y, srcX, 0, srcWidth, 32);
            x += srcWidth;
        }
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
}
