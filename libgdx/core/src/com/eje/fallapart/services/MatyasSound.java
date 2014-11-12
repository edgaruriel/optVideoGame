package com.eje.fallapart.services;

public enum MatyasSound {

	CLICK("sounds/click.wav"),
	DROP("sounds/drop.mp3"),
	GAME("sounds/game.mp3"),
	GOOD("sounds/good.mp3"),
	HIT("sounds/hit.mp3"),
	KIDS("sounds/kids.mp3"),
	LANZAR("sounds/lanzar.mp3"),
	OPEN("sounds/open.wav"),
	WRRONG("sounds/wrong.mp3");
	
	private final String fileName;
	
	private MatyasSound(String fileName){
		this.fileName = fileName;
	}
	
	public String getFileName(){
		return fileName;
	}
	
}
