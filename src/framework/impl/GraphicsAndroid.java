package framework.impl;

import java.io.IOException;
import java.io.InputStream;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import framework.Graphics;
import framework.Pixmap;

public class GraphicsAndroid implements Graphics{

	AssetManager assets;
	Bitmap frameBuffer;
	Canvas canvas;
	Rect src = new Rect();
	Rect dst = new Rect();
	Paint paint;
	
	public GraphicsAndroid(AssetManager assets, Bitmap frameBuffer){
		this.assets=assets;
		this.frameBuffer=frameBuffer;
		this.canvas=new Canvas(frameBuffer);
		this.paint=new Paint();
	}
	
	@Override
	public Pixmap newPixmap(String fileName, PixmapFormat format) {
		// TODO Auto-generated method stub
		InputStream in = null;
		Bitmap bitmap = null;
		
		try {
			in = assets.open(fileName);
			bitmap = BitmapFactory.decodeStream(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new PixmapAndroid(bitmap, format);
	}

	@Override
	public void clear(int r, int g, int b) {
		// TODO Auto-generated method stub
		canvas.drawRGB(r, g, b);
	}

	@Override
	public void drawPixel(int x, int y, int color) {
		// TODO Auto-generated method stub
		paint.setColor(color);
		canvas.drawPoint(x, y, paint);
	}

	@Override
	public void drawLine(int x, int y, int x2, int y2, int color) {
		// TODO Auto-generated method stub
		paint.setColor(color);
		canvas.drawLine(x, y, x2, y2, paint);
	}

	@Override
	public void drawRect(int x, int y, int width, int height, int color) {
		// TODO Auto-generated method stub
		paint.setColor(color);
		canvas.drawRect(x, y, x+width, y+height, paint);
	}

	@Override
	public void drawPixmap(Pixmap pixmap, int x, int y) {
		// TODO Auto-generated method stub
		canvas.drawBitmap(bitmap, matrix, paint);
	}

	@Override
	public void drawPixmap(Pixmap pixmap, int x, int y, int srcX, int srcY,
			int srcWidth, int srcHeight) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

}
