package com.eje.fallapart.screens;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.eje.fallapart.FallApartGame;

public class OptionScreen extends AbstractScreen{

	public OptionScreen(FallApartGame game) {
		super(game);
	}
	
	
	public void show(){
		
		super.show();
		
		Table table = super.getTable();
		table.clear();
		Image image = new Image(game.getAssetManager()
								.get("game.atlas", TextureAtlas.class)
								.findRegion("blackboard"));
		table.setBackground(image.getDrawable());
		
		Label label = new Label("Opciones",getSkin());
		table.add(label).spaceBottom(30).colspan(2);
		
		final CheckBox soundCheckbox = new CheckBox("",getSkin());
		soundCheckbox.setChecked(game.getPreferencesManager().isSoundEnabled());
		soundCheckbox.addListener(new ChangeListener(){

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				boolean enabled = soundCheckbox.isChecked();
				game.getPreferencesManager().setSoundEnabled(enabled);
				game.getSoundManager().setEnabled(enabled);
				
			}
		});
		table.row();
		table.add("Sonidos").pad(20);
		table.add(soundCheckbox);

		final CheckBox musicCheckBox = new CheckBox("",getSkin());
		musicCheckBox.setChecked(game.getPreferencesManager().isMusicEnabled());
		musicCheckBox.addListener(new ChangeListener(){

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				boolean enabled = musicCheckBox.isChecked();
				game.getPreferencesManager().setMusicEnabled(enabled);
				game.getMusicManager().setEnabled(enabled);
			}
		});
		table.row();
		table.add("Musica").pad(20);;
		table.add(musicCheckBox);

		Slider volumeSlider = new Slider(0f,1f,0.1f,false,getSkin(),"default");
		volumeSlider.setValue(game.getPreferencesManager().getVolume());
		volumeSlider.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				float value = ((Slider)actor).getValue();
				game.getPreferencesManager().setVolume(value);
				game.getSoundManager().setVolume(value);
			}
		});
		table.row();
		table.add("Volumen").pad(20);;
		table.add(volumeSlider);
		
		TextButton backButton = new TextButton("Regresar",getSkin());
		backButton.addListener(new ClickListener(){
			public void clicked(InputEvent event, float x, float y){
				game.setScreen(new MenuScreen(game));
			}
		});
		table.row();
		table.add(backButton).colspan(2);
		
	}

}
