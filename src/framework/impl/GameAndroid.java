package framework.impl;

import android.graphics.Bitmap;
import android.os.PowerManager.WakeLock;
import android.view.Window;
import android.view.WindowManager;
import framework.Audio;
import framework.FileIO;
import framework.Game;
import framework.Graphics;
import framework.Input;
import framework.Screen;

public class GameAndroid extends Activity implements Game{
	
	WakeLock wakeLock;
	
	public void onCreate(Bundle savedInstance){
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		boolean isLandscape = getResources().getConfiguration().orientation == Configuration;
		int frameBufferWidth = isLandscape?480:320;
		int frameBufferHeight = isLandscape?320:480;
		Bitmap frameBuffer = Bitmap.createBitmap(src);
	}

	@Override
	public Input getInput() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FileIO getFileIO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Graphics getGraphics() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Audio getAudio() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setScreen(Screen screen) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Screen getCurrentScreen() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Screen getStartScreen() {
		// TODO Auto-generated method stub
		return null;
	}

}
