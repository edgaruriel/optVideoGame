package com.eje.fallapart.services;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.eje.fallapart.FallApartGame;

public class SoundManager {
	
	private float volume = 1f;
	private boolean enabled = true;
	
	private AssetManager manager;
	
	public SoundManager(AssetManager manager){
		this.manager = manager;
	}
	
	public void play(MatyasSound sound){
		if (!enabled) return;
		
		Sound soundToPlay = manager.get(sound.getFileName());
		if (soundToPlay==null) return;
		
		Gdx.app.log(FallApartGame.LOG, "Reproduciendo sonido: " + sound.name());
		soundToPlay.play(volume);
		
	}

	public float getVolume() {
		return volume;
	}

	public void setVolume(float volume) {
		this.volume = volume;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
