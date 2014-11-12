package com.eje.fallapart.screens;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.eje.fallapart.FallApartGame;
import com.eje.fallapart.services.MatyasMusic;
import com.eje.fallapart.services.MatyasSound;

public class SplashScreen extends AbstractScreen{

	public SplashScreen(FallApartGame game) {
		super(game);
		
	}
	
	@Override
	public void show(){
		super.show();
		
		Table table = super.getTable();
		
		Texture back = new Texture("bg_main.png");
		back.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		table.setBackground(new TextureRegionDrawable(new TextureRegion(back,0,0,720,1280)));
		
		
		game.getAssetManager().load("uiskin.atlas", com.badlogic.gdx.graphics.g2d.TextureAtlas.class);
		game.getAssetManager().load("fonts/default-32.fnt",BitmapFont.class);
		
		game.getAssetManager().load(MatyasSound.CLICK.getFileName(), Sound.class);
		game.getAssetManager().load(MatyasSound.DROP.getFileName(), Sound.class);
		game.getAssetManager().load(MatyasSound.GAME.getFileName(), Sound.class);
		game.getAssetManager().load(MatyasSound.GOOD.getFileName(), Sound.class);
		game.getAssetManager().load(MatyasSound.HIT.getFileName(), Sound.class);
		game.getAssetManager().load(MatyasSound.KIDS.getFileName(), Sound.class);
		game.getAssetManager().load(MatyasSound.LANZAR.getFileName(), Sound.class);
		game.getAssetManager().load(MatyasSound.OPEN.getFileName(), Sound.class);
		game.getAssetManager().load(MatyasSound.WRRONG.getFileName(), Sound.class);
		game.getAssetManager().load(MatyasMusic.MENU_MUSIC.getFileName(), Music.class);
		game.getAssetManager().load(MatyasMusic.TITLE_MUSIC.getFileName(), Music.class);
		
	}
	
	@Override
	public void render(float delta){
		super.render(delta);
		
		if (game.getAssetManager().update()){
			stage.addAction(Actions.sequence(Actions.delay(0.25f),
											Actions.fadeOut(0.75f),
											new Action(){
												public boolean act(float delta){
													game.setScreen(new IntroScreen(game));
													return true;
												}
											}));
		}
		
	}

}
