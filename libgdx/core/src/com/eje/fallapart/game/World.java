package com.eje.fallapart.game;

public class World {

	private GameState gameState;
	
	
	public World(GameState gameState){
		this.setGameState(gameState);
	}


	public GameState getGameState() {
		return gameState;
	}


	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}
}
