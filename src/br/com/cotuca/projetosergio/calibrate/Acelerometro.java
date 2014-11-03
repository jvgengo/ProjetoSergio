package br.com.cotuca.projetosergio.calibrate;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import br.com.cotuca.projetosergio.config.DeviceSettings;

public class Acelerometro implements SensorEventListener {

	private AccelerometerDelegate delegate;

	private float currentAccelerationX;
	private float currentAccelerationY;

	private float calibratedAccelerationX;
	private float calibratedAccelerationY; 
	
	private SensorManager sensorManager;

	private int calibrated;

	static Acelerometro sharedAccelerometer = null;

	public static Acelerometro sharedAccelerometer() {
		if (sharedAccelerometer == null) {
			sharedAccelerometer = new Acelerometro();
		}
		return sharedAccelerometer;
	}

	public Acelerometro() {
		this.catchAccelerometer();
	}

	public void catchAccelerometer() {

		sensorManager = DeviceSettings.getSensormanager();
		sensorManager.registerListener(this,
				sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_GAME);

	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onSensorChanged(SensorEvent acceleration) {
		
		if(calibrated < 100){
			this.calibratedAccelerationX += acceleration.values[0];
			this.calibratedAccelerationY += acceleration.values[1];
			
			System.out.println(acceleration.values[0]);
			System.out.println(acceleration.values[1]);
			
			calibrated++;
			
			if (calibrated == 100 ) {
				this.calibratedAccelerationX /= 100;
				this.calibratedAccelerationY /= 100;
			}

			return;
			
		} 
				
		// Read acceleration
		this.currentAccelerationX = acceleration.values[0] - this.calibratedAccelerationX;
		this.currentAccelerationY = acceleration.values[1] - this.calibratedAccelerationY;

	
		// Dispatch Accelerometer Read
		if (this.delegate != null) {
			this.delegate.accelerometerDidAccelerate(currentAccelerationX, currentAccelerationY);
		}

	}

	public void setDelegate(AccelerometerDelegate delegate) {
		this.delegate = delegate;
	}

	public AccelerometerDelegate getDelegate() {
		return delegate;
	}
}
