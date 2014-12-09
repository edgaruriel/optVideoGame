package com.eje.fallapart.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.eje.fallapart.FallApartGame;
import com.eje.fallapart.services.MatyasMusic;
import com.eje.fallapart.services.MatyasSound;

public class IntroScreen extends AbstractScreen{

	public IntroScreen(FallApartGame game) {
		super(game);
	}

	@Override
	public void show(){
		super.show();
		Gdx.input.setInputProcessor(stage);
		if(game.getPreferencesManager().isMusicEnabled())
			game.getMusicManager().play(MatyasMusic.MENU_MUSIC);
		
		Table table = super.getTable();
		table.clear();
		Image image = new Image(getSkin(), "bg_main");
		
		table.setBackground(image.getDrawable());
		
		Button startGameButton = new Button( getSkin(),"btnJugar");
		
		startGameButton.addListener(new ClickListener(){
			public void clicked(InputEvent event, float x, float y){
				
				stage.addAction(Actions.sequence(Actions.delay(0.25f),
						Actions.fadeOut(0.75f),
						new Action(){
							public boolean act(float delta){
								//game.getMusicManager().stop();
								game.getSoundManager().play(MatyasSound.CLICK);
								game.setScreen(new GameScreen(game));
								return true;
							}
						}));
			}
		});
		startGameButton.setScale(0.5f);
		startGameButton.addAction(Actions.scaleTo(1f,1f,1f));
		stage.addActor(startGameButton);
		Image logo = new Image(getSkin(), "logo");
		table.add(logo);
		table.row();
		table.add().size(10, 200);
		table.row();
		Label scoreLbl = new Label("Record " + game.getPreferencesManager().getScore(), getSkin());
		scoreLbl.setFontScale(2);
		table.add(scoreLbl);
		table.row();
		table.add().size(10, 100);
		table.row();
		table.add(startGameButton).padBottom(10);
		
		final CheckBox musicCheckBox = new CheckBox("",getSkin());
		musicCheckBox.setChecked(game.getPreferencesManager().isMusicEnabled());
		musicCheckBox.addListener(new ChangeListener(){

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				boolean enabled = musicCheckBox.isChecked();
				
				game.getPreferencesManager().setMusicEnabled(enabled);
				game.getMusicManager().setEnabled(enabled);
				if(enabled)
					game.getMusicManager().play(MatyasMusic.MENU_MUSIC);
			}
		});
		table.row();
		table.add().size(10, 80);
		table.row();
		table.add(musicCheckBox);
	}
	
	@Override	
	public void render(float delta){
		super.render(delta);
	}
}
