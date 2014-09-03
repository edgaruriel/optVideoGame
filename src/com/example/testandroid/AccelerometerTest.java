package com.example.testandroid;

import java.util.List;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

public class AccelerometerTest extends ActionBarActivity implements
		SensorEventListener {

	TextView x, y, z;

	private Sensor mAccelerometer;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_accelerometer_test);

		x = (TextView) findViewById(R.id.ejex);

		y = (TextView) findViewById(R.id.ejey);

		z = (TextView) findViewById(R.id.ejez);
	}

	protected void onResume() {
		super.onResume();
		SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
		List<Sensor> sensors = sm.getSensorList(Sensor.TYPE_ACCELEROMETER);
		if (sensors.size() > 0)//Verifica que el dispositivo tenga acelerometro
		{
			sm.registerListener(this, sensors.get(0),
					SensorManager.SENSOR_DELAY_GAME);
		}
	}

	protected void onPause() {
		SensorManager mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		mSensorManager.unregisterListener(this, mAccelerometer);
		super.onPause();
	}

	protected void onStop() {
		SensorManager mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		mSensorManager.unregisterListener(this, mAccelerometer);
		super.onStop();
	}

	@Override
	public void onSensorChanged(SensorEvent event) {

		
		this.x.setText("X = " + event.values[SensorManager.DATA_X]);

		this.y.setText("Y = " + event.values[SensorManager.DATA_Y]);

		this.z.setText("Z = " + event.values[SensorManager.DATA_Z]);
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}
}
