package com.eje.fallapart.services;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class PreferencesManager {

	private static final String PREFERENCES_NAME = "Matyas";
	
	private static final String PREFERENCES_VOLUME = "volume";
	private static final String PREFERENCES_SOUND = "sound";
	private static final String PREFERENCES_MUSIC = "music";
	
	Preferences preferences;
	
	private Preferences getPreferences(){
		if (preferences==null)
			preferences = Gdx.app.getPreferences(PREFERENCES_NAME);
		
		return preferences;
	}
	
	
	public boolean isSoundEnabled(){
		return getPreferences().getBoolean(PREFERENCES_SOUND, true);
	}
	public boolean isMusicEnabled(){
		return getPreferences().getBoolean(PREFERENCES_MUSIC, true);
	}
	public float getVolume(){
		return getPreferences().getFloat(PREFERENCES_VOLUME,1f);
	}
	
	public void setSoundEnabled(boolean enabled){
		getPreferences().putBoolean(PREFERENCES_SOUND, enabled);
		getPreferences().flush();
		
		
	}
	public void setMusicEnabled(boolean enabled){
		getPreferences().putBoolean(PREFERENCES_MUSIC, enabled);
		getPreferences().flush();
		
	}
	public void setVolume(float volume){
		getPreferences().putFloat(PREFERENCES_VOLUME, volume);
		getPreferences().flush();
	}
	

}
