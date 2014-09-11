package com.example.testandroid;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class SurfaceViewTest extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_surface_view_test);
	}
	
	class FastRenderView extends SurfaceView implements Runnable{
		Thread renderThread = null;
		SurfaceHolder holder;
		volatile boolean running = false;

		public FastRenderView(Context context) {
			super(context);
			holder = getHolder();
			// TODO Auto-generated constructor stub
		}
		
		public void resume(){
			running = true;
			renderThread = new Thread(this);
			renderThread.start();
		}
		
		public void pause(){
			running = false;
			while(true){
				try {
					renderThread.join();
					break;
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			renderThread = null;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(running){
				if(!holder.getSurface().isValid())
					continue;
					
					Canvas canvas = holder.lockCanvas(); 
					drawSurface(canvas);
					holder.unlockCanvasAndPost(canvas);
			}
			
		}
		
		private void drawSurface(Canvas canvas){
			canvas.drawRGB(255, 0, 0);
		}
		
	}

}
