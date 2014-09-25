package framework.impl;

import android.graphics.Bitmap;
import framework.Graphics.PixmapFormat;
import framework.Pixmap;

public class PixmapAndroid implements Pixmap{
	
	Bitmap bitmap;
	PixmapFormat format;
	
	public PixmapAndroid(Bitmap bitmap, PixmapFormat format){
		this.bitmap = bitmap;
		this.format = format;
	}
	
	

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return bitmap.getWidth();
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return bitmap.getHeight();
	}

	@Override
	public PixmapFormat getFormat() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		bitmap.recycle();
	}

}
