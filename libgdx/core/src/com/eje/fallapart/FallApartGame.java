package com.eje.fallapart;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.eje.fallapart.game.World;
import com.eje.fallapart.screens.SplashScreen;
import com.eje.fallapart.services.MusicManager;
import com.eje.fallapart.services.PreferencesManager;
import com.eje.fallapart.services.ProfileManager;
import com.eje.fallapart.services.SoundManager;
import com.eje.fallapart.world.WorldRenderer;

public class FallApartGame extends Game {
	
	/**
	 * Para log
	 */
	public static final String LOG = FallApartGame.class.getSimpleName();
	
	/**
	 * Modo de prueba
	 */
	public static final boolean DEV_MODE = false;
	
	/**
	 * Tasa de comparacion de la resolucion base del juego
	 * con la resolucion de la pantalla
	 */
	public float factorX, factorY;
	
	/**
	 * Administrador de recursos
	 */
	private AssetManager manager = new AssetManager();
	
	/**
	 * Administrador de sonidos
	 */
	private SoundManager soundManager;
	
	
	/**
	 * Administrador de musica de fondo
	 */
	private MusicManager musicManager;
	
	/**
	 * Administrador del estado interno del juego
	 */
	private ProfileManager profileManager;
	
	/**
	 * Administrador de las preferencias de usuario
	 */
	private PreferencesManager preferencesManager;
	
	private World world;
	
	public WorldRenderer renderer;
	
	public AssetManager getAssetManager(){
		return manager;
	}
	
	public SoundManager getSoundManager(){
		return soundManager;
	}
	
	public MusicManager getMusicManager(){
		return musicManager;
	}
	
	public ProfileManager getProfileManager(){
		return profileManager;
	}
	
	public PreferencesManager getPreferencesManager(){
		return preferencesManager;
	}
	
	public World getWorld(){
		return world;
	}
	
	public FallApartGame(){
		
	}

	@Override
	public void create() {
		Gdx.app.log(FallApartGame.LOG, "Creando juego en " + Gdx.app.getType());
		
		preferencesManager = new PreferencesManager();
		
		profileManager = new ProfileManager();
		world = new World(profileManager.retrieveGameState());
		
		musicManager = new MusicManager(manager);
		
		soundManager = new SoundManager(manager);
		
	}
	
	
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		Gdx.app.log(FallApartGame.LOG, "Cambiando resolucion a: " + width + "x" + height);
		
		factorX = 720f/(float)width;
		factorY = 1280f/(float)height;
		
		if (getScreen()==null){
			setScreen(new SplashScreen(this));
		}
		
		
	}
}
