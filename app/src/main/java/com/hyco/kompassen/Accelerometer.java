package com.hyco.kompassen;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import junit.framework.TestCase;

import org.w3c.dom.Text;



public class Accelerometer extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;

    private TextView yValue;
    private TextView xValue;
    private TextView zValue;
    private Float y;
    private Float x;
    private Float z;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);

        sensorManager =  (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        yValue = (TextView) findViewById(R.id.yValue);
        xValue = (TextView) findViewById(R.id.xValue);
        zValue = (TextView) findViewById(R.id.zValue);


    }

    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void onSensorChanged(SensorEvent event) {
        //if(event.accuracy == SensorManager.SENSOR_STATUS_UNRELIABLE) {
            //return;
        //}
        x = event.values[0];
        y = event.values[1];
        z = event.values[2];
        yValue.setText("Y: " + String.format("%.2f",y));
        xValue.setText("X: " + String.format("%.2f",x));
        zValue.setText("Z: " + String.format("%.2f",z));

    }
}
