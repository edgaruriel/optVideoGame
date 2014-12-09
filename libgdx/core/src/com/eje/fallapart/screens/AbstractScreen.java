package com.eje.fallapart.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.eje.fallapart.FallApartGame;
import com.eje.fallapart.services.Inputs;
import com.eje.fallapart.services.InputsProcessor;

public class AbstractScreen implements Screen{

	public static final int VIEWPORT_WIDTH = 720;
	public static final int VIEWPORT_HEIGHT = 1280;
	public static final int PPM = 100;
	public static final float STEP = 1 / 60f;
	
	protected final FallApartGame game;
	
	protected final Stage stage;
	
	private Skin skin;
	
	private TextureAtlas atlas;
	private BitmapFont font;
	private Table table;
	
	public AbstractScreen(FallApartGame game){
		this.game = game;
		this.stage = new Stage(new StretchViewport(VIEWPORT_WIDTH,VIEWPORT_HEIGHT));
	}
	
	public String getName(){
		return getClass().getSimpleName();
	}
	
	protected Skin getSkin(){
		if (skin==null){
			skin = new Skin(Gdx.files.internal("uiskin.json"), new TextureAtlas("uiskin.atlas"));
			//atlas = new TextureAtlas("uiskin.pack");
			//skin = new Skin();
			//skin.addRegions(atlas);

		}
		return skin;
	}
	
	protected Table getTable(){
		if (table==null){
			table = new Table(getSkin());
			table.setFillParent(true);
			stage.addActor(table);
		}
		return table;
		
	}
	
	public void show(){
		Gdx.input.setInputProcessor(stage);
	}
	
	public void resize(int width, int height) {
	    stage.getViewport().update(width, height, false);
	}
	
	public void render(float delta){
		stage.act(delta);
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.draw();
		
	}
	
	@Override
	public void hide() {
		Gdx.app.log(FallApartGame.LOG, "Ocultar pantalla " + getName());
		dispose();
		
	}

	@Override
	public void pause() {
		Gdx.app.log(FallApartGame.LOG, "Ir a pausa en pantalla " + getName());
		
	}

	@Override
	public void resume() {
		Gdx.app.log(FallApartGame.LOG, "Resumir pantalla " + getName());
		
	}

	@Override
	public void dispose() {
		Gdx.app.log(FallApartGame.LOG, "Eliminar recursos de pantalla " + getName());

		
	}
	
	
}
