package com.hyco.kompassen;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class Compass extends AppCompatActivity implements SensorEventListener{
    private SensorManager sensorManager;
    private Sensor rotationVector;

    private Float direction;
    private TextView tvDirection;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        rotationVector = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        tvDirection = (TextView) findViewById(R.id.direction);
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        direction = (1 + event.values[2])*180;




        tvDirection.setText(String.format("%.2f",direction));

    }

    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, rotationVector, SensorManager.SENSOR_DELAY_UI);
    }

    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
