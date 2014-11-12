package com.eje.fallapart.services;

import com.badlogic.gdx.audio.Music;

public enum MatyasMusic {

	MENU_MUSIC("sounds/storm_watch.mp3"),
	TITLE_MUSIC("sounds/storm_watch.mp3");
	
	private final String fileName;
	private Music musicResource;
	
	private MatyasMusic(String fileName){
		this.fileName = fileName;
	}
	
	public String getFileName(){
		return fileName;
	}

	public Music getMusicResource() {
		return musicResource;
	}

	public void setMusicResource(Music musicResource) {
		this.musicResource = musicResource;
	}
	
}
