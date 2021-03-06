package com.hyco.kompassen;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;


public class Compass extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor rotationVector;

    private String riktning;
    private Float currentDegree = 0f;
    private TextView tvDirection;
    private ImageView image;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        rotationVector = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        tvDirection = (TextView) findViewById(R.id.direction);
        image = (ImageView) findViewById(R.id.image_compass);
        riktning = "North";
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Float degree = event.values[2];
        if(degree < 0) {
            degree = (degree + 2)*180;
        } else {
            degree = degree*180;
        }

        if(degree < 45/2 || degree > (360 - 45/2)) {
            riktning = "North";
        }
        if(degree > 45/2 && degree < (90-45/2)) {
            riktning = "Northwest";
        }
        if(degree > (90-45/2) && degree < (90 + 45/2)) {
            riktning = "West";
        }
        if(degree > (90 + 45/2) && degree < (180 - 45/2)) {
            riktning = "Southwest";
        }
        if(degree > (180 - 45/2) && degree < (180 + 45/2)) {
            riktning = "South";
        }
        if(degree > (225 - 45/2) && degree < (225 + 45/2)) {
            riktning = "Southeast";
        }
        if(degree > (270 - 45/2) && degree < (270 + 45/2)) {
            riktning = "East";
        }
        if(degree > (315 - 45/2) && degree < (315 + 45/2)) {
            riktning = "Northeast";
        }

        tvDirection.setText(String.format("%.2f",  (360 - degree)) + (char) 176 + " " + riktning);

        RotateAnimation ra = new RotateAnimation(currentDegree, degree, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        // how long the animation will take place
        ra.setDuration(210);

        // set the animation after the end of the reservation status
        ra.setFillAfter(true);

        // Start the animation
        image.startAnimation(ra);
        currentDegree = degree;
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
