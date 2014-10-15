package com.eje.skull;

import com.eje.framework.Game;
import com.eje.framework.Graphics;
import com.eje.framework.Graphics.PixmapFormat;
import com.eje.framework.Screen;

public class LoadingScreen extends Screen {

	public LoadingScreen(Game game) {
        super(game);
    }

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
        Assets.background = g.newPixmap("bg.png", PixmapFormat.RGB565);
        //Assets.logo = g.newPixmap("logo.png", PixmapFormat.ARGB4444);
        Assets.mainMenu = g.newPixmap("menu.png", PixmapFormat.ARGB4444);
        Assets.numbers = g.newPixmap("numeros.png", PixmapFormat.ARGB4444);
        Assets.ready = g.newPixmap("ready.png", PixmapFormat.ARGB4444);
        Assets.pause = g.newPixmap("menupausa.png", PixmapFormat.ARGB4444);
        Assets.gameOver = g.newPixmap("gameover.png", PixmapFormat.ARGB4444);
        Assets.player = g.newPixmap("player.png", PixmapFormat.ARGB4444);
        Assets.pauseBtn = g.newPixmap("pausaBtn.png", PixmapFormat.ARGB4444);
        Assets.leftBorder = g.newPixmap("left.png", PixmapFormat.ARGB4444);
        Assets.rightBorder = g.newPixmap("right.png", PixmapFormat.ARGB4444);
        Assets.enemy1 = g.newPixmap("enemy1.png", PixmapFormat.ARGB4444);
        Assets.enemy2 = g.newPixmap("enemy2.png", PixmapFormat.ARGB4444);
        Assets.enemy3 = g.newPixmap("enemy3.png", PixmapFormat.ARGB4444);
        //Settings.load(game.getFileIO());
        game.setScreen(new MainMenuScreen(game));
		
	}

	@Override
	public void present(float deltaTime) {
		// TODO Auto-generated method stub
		
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
