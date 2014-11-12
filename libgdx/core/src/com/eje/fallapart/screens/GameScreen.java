package com.eje.fallapart.screens;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.eje.fallapart.FallApartGame;
import com.eje.fallapart.services.MatyasMusic;
import com.eje.fallapart.services.MatyasSound;

public class GameScreen extends AbstractScreen{

	public GameScreen(FallApartGame game) {
		super(game);
	}

	@Override
	public void show(){
		super.show();
		
		game.getMusicManager().play(MatyasMusic.MENU_MUSIC);
		
		Table table = super.getTable();
		table.clear();
		Image image = new Image(getSkin(), "bg_main");
		
		table.setBackground(image.getDrawable());
		
		Button pauseButton = new Button( getSkin(),"btnPausa");
		pauseButton.addListener(new ClickListener(){
			public void clicked(InputEvent event, float x, float y){
				stage.addAction(Actions.sequence(Actions.delay(0.25f),
						Actions.fadeOut(0.75f),
						new Action(){
							public boolean act(float delta){
								//game.getMusicManager().stop();
								game.getSoundManager().play(MatyasSound.CLICK);
								game.setScreen(new IntroScreen(game));
								return true;
							}
						}));
			}
		});
		pauseButton.setScale(0.2f);
		pauseButton.addAction(Actions.scaleTo(1f,1f,1f));
		stage.addActor(pauseButton);
		Image logo = new Image(getSkin(), "player");
		table.add(logo);
		table.row();
		table.add(pauseButton).padBottom(0);
	}
	
	@Override	
	public void render(float delta){
		super.render(delta);
	}
}
