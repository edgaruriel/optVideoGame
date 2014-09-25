package framework.impl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class FastRenderViewAndroid  extends SurfaceView implements Runnable{
	GameAndroid game;
	Bitmap frameBuffer;
	Thread renderThread;
	SurfaceHolder holder;
	volatile boolean running = false;
	
	public FastRenderViewAndroid(GameAndroid game, Bitmap frameBuffer) {
		super(game);
		this.game=game;
		this.frameBuffer=frameBuffer;
		this.holder = getHolder();
	}
	
	public void pause(){
		running = false;
		while(true){
			try{
				renderThread.join();
				break;
			}catch(InterruptedException e){
				
			}
		}
	}
	
	public void resume(){
		running=true;
		renderThread = new Thread(this);
		renderThread.start();
	}
	
	@Override
	public void run(){
		Rect rect = null;
		long startTime = System.nanoTime();
		while(running){
			if(!holder.getSurface().isValid())
				continue;
			
			float deltaTime = (System.nanoTime()-startTime);
			startTime = System.nanoTime();
			
			game.getCurrentScreen().update(deltaTime);
			game.getCurrentScreen().present(deltaTime);
			
			Canvas canvas = holder.lockCanvas();
			canvas.getClipBounds(rect);
			canvas.drawBitmap(frameBuffer, null, rect,null);
			holder.unlockCanvasAndPost(canvas);
		}
	}
}
