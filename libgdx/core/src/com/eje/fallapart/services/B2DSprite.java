package com.eje.fallapart.services;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.eje.fallapart.screens.AbstractScreen;

public class B2DSprite {

	protected Body body;
	protected Animation animation;
	protected float width;
	protected float height;
	private Sprite sprite;
	
	public B2DSprite(Body body) {
		this.body = body;
		animation = new Animation();
		sprite = new Sprite(new Texture("player.png"));
	}
	
	public void setAnimation(TextureRegion reg, float delay) {
		setAnimation(new TextureRegion[] { reg }, delay);
	}
	
	public void setAnimation(TextureRegion[] reg, float delay) {
		animation.setFrames(reg, delay);
		width = reg[0].getRegionWidth();
		height = reg[0].getRegionHeight();
	}
	
	public void update(float dt) {
		animation.update(dt);
	}
	
	public void render(SpriteBatch sb) {
		sb.begin();
		
		sprite.setPosition(body.getPosition().x-1.5f, body.getPosition().y-1.5f);
		sprite.setRegion(animation.getFrame());
		sprite.setSize(3, 3);
		sprite.draw(sb);
		//sb.draw(animation.getFrame(), (body.getPosition().x/100 ), (int) (body.getPosition().y/100 ));
		sb.end();
	}
	
	public Body getBody() { return body; }
	public Vector2 getPosition() { return body.getPosition(); }
	public float getWidth() { return width; }
	public float getHeight() { return height; }
}
