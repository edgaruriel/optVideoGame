package com.eje.fallapart.game;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.eje.fallapart.FallApartGame;
import com.eje.fallapart.screens.AbstractScreen;
import com.eje.fallapart.services.Animation;

public class Obstaculo {

	private Sprite sprite;
	protected Body body;
	private Random randPos;
	private final int maxPos = 11;
	private final int minPos = 2;
	
	public Obstaculo(Body body) {
		this.body = body;
		randPos = new Random();
		sprite = new Sprite(new Texture("enemy1.png"));
	}

	public void update(float dt, float velocidad) {
		if(body.getPosition().y > 30){
			reset();
		}
		body.setLinearVelocity(0,velocidad);
		//Gdx.app.log(FallApartGame.LOG, "enemy pos " + body.getPosition().y );
	}
	
	public void render(SpriteBatch sb) {
		sb.begin();
		
		sprite.setPosition(body.getPosition().x-2f, body.getPosition().y-3f);
		sprite.setSize(4, 4);
		sprite.draw(sb);
		sb.end();
	}
	
	public void reset(){
		int randomNum = randPos.nextInt((maxPos - minPos) + 1) + minPos;
		randomNum *= AbstractScreen.PPM;
		body.setTransform(new Vector2(randomNum/AbstractScreen.PPM,-4), 0);
		
		//bdef.position.set(randomNum/AbstractScreen.PPM,0);
	}
}
