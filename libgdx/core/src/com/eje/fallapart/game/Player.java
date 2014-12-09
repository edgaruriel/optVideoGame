package com.eje.fallapart.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.eje.fallapart.services.B2DSprite;

public class Player extends B2DSprite{
	
	public Player(Body body) {
		
		super(body);
		Texture tex = new Texture("playerframes.png");
		TextureRegion[] sprites = new TextureRegion[3];
		for(int i = 0; i < sprites.length; i++) {
			sprites[i] = new TextureRegion(tex, i * 91, 0, 91, 86);
		}
		
		animation.setFrames(sprites, 1 / 4f);
		
		width = sprites[0].getRegionWidth();
		height = sprites[0].getRegionHeight();
		
	}
}
