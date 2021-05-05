package nhom5.fit.tdc.edu.compass;

import androidx.appcompat.app.AppCompatActivity;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Sensor aSensor = null;
    private Sensor mSensor = null;
    private SensorManager Sensormanager;
    ImageView imageView;

    private float [] mGravityValues = new float[3];
    private float [] mAccelerationValues = new float[3];
    private float [] mRotationMatrix = new float[9];
    private float mLastDirectionInDeGress = 0f;

    private SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            calculateCompassRotation(event);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    private DevicePolicyManager deviceManager;
    //private ComponentName compName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);

        //deviceManager =(DevicePolicyManager) getSystemService(DEVICE_POLICY_SERVICE);
        //compName = new ComponentName(this, MyAdmin.class);

        Sensormanager=(SensorManager) getSystemService(SENSOR_SERVICE);
        aSensor =  Sensormanager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensor = Sensormanager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);


    }

    @Override
    protected void onResume() {
        super.onResume();
        if(aSensor != null && mSensor != null){
            Sensormanager.registerListener(sensorEventListener, aSensor, SensorManager.SENSOR_DELAY_FASTEST);
            Sensormanager.registerListener(sensorEventListener, mSensor, SensorManager.SENSOR_DELAY_FASTEST);
            Toast.makeText(MainActivity.this, "ok", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivity.this, "Khong ho tro ACCELEROMETER hoac MAGNETIC", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStop() {
        if(aSensor != null && mSensor !=null){
            Sensormanager.unregisterListener(sensorEventListener, aSensor);
        }else{
            Toast.makeText(MainActivity.this, "Khong ho tro ACCELEROMETER hoac MAGNETIC", Toast.LENGTH_SHORT).show();
        }
        super.onStop();
    }

    private void calculateCompassRotation(SensorEvent event){
        switch (event.sensor.getType()){
            case Sensor.TYPE_ACCELEROMETER:
                mAccelerationValues = event.values.clone();
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                mGravityValues = event.values.clone();
                break;
        }

        boolean success = SensorManager. getRotationMatrix(mRotationMatrix, null, mAccelerationValues, mGravityValues);
        if(success){
            float[] orientationValues = new float[3];
            SensorManager.getOrientation(mRotationMatrix, orientationValues);
            float azimuth = (float) Math.toDegrees(-orientationValues[0]);
            RotateAnimation rotateAnimation = new RotateAnimation(
                    mLastDirectionInDeGress , azimuth,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
            rotateAnimation.setDuration(1);
            rotateAnimation.setFillAfter(true);

            imageView.startAnimation(rotateAnimation);
            mLastDirectionInDeGress = azimuth;
        }
    }
}