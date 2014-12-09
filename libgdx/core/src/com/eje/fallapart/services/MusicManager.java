package com.eje.fallapart.services;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.eje.fallapart.FallApartGame;

public class MusicManager {
	private boolean enabled = true;
	
	private MatyasMusic musicBeingPlayed;
	
	private AssetManager manager;
	
	public MusicManager(AssetManager manager){
		this.manager = manager;
	}
	
	public void play(MatyasMusic music ){
		
		stop();
		
		Music musicResource = manager.get(music.getFileName());
		
		if (musicResource==null) return;
		Gdx.app.log(FallApartGame.LOG, "Reproduciendo musica " + music.getFileName());
		
		musicResource.setVolume(0.5f);
		musicResource.setLooping(true);
		musicResource.play();
		
		musicBeingPlayed = music;
		musicBeingPlayed.setMusicResource(musicResource);
		
		
		
	}
	
	public void stop(){
		if (musicBeingPlayed==null) return;
		
		Gdx.app.log(FallApartGame.LOG, "Deteniendo musica " + musicBeingPlayed.getFileName());
		Music musicResource = musicBeingPlayed.getMusicResource();
		musicResource.stop();
		
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
		if (enabled){
			if (musicBeingPlayed!=null)
				play(musicBeingPlayed);
		}else{
			stop();
		}
	}
}
