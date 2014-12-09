package com.eje.fallapart.game;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.eje.fallapart.FallApartGame;
import com.eje.fallapart.screens.AbstractScreen;

public class Pared {

	private Sprite sprite;
	private float posY;
	private int posX;
	
	public Pared(String textura,int posY,boolean isRight) {

		this.posY = posY;
		sprite = new Sprite(new Texture(textura));
		sprite.setSize(1, 10);
		if(isRight)
			posX = 13;
		else
			posX = 0;
	}

	public void update(float dt, float velocidad) {
		
		if(sprite.getY()> 30){
			posY = -10;
		}
		
		//Gdx.app.log(FallApartGame.LOG, "delta " + dt + " y " + sprite.getY());
		posY += dt*velocidad;
		
		
	}
	
	public void render(SpriteBatch sb) {
		sb.begin();
		sprite.setPosition(posX, posY);
		sprite.draw(sb);
		sb.end();
	}
}
