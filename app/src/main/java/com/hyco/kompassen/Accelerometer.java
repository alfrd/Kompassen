package com.hyco.kompassen;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by iamzzleeping on 29/03/16.
 */
public class Accelerometer extends AppCompatActivity {

    private SensorManager sensorManager;
    private Sensor accelerometer;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);
    }
}
