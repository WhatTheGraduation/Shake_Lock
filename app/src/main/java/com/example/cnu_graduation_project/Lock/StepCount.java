package com.example.cnu_graduation_project.Lock;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.cnu_graduation_project.ClientActivity;
import com.example.cnu_graduation_project.R;
import com.example.cnu_graduation_project.Service.ForegroundService;

public class StepCount extends ClientActivity implements SensorEventListener {
    String TAG = "StepCount";
    SensorManager sm;
    Sensor sensor_step_detector;
    int maxStep=10;
    static int step=0;
    public static TextView stepView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lock);
        sm = (SensorManager)getSystemService(SENSOR_SERVICE);   // 센서 매니저 생성
        sensor_step_detector = sm.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);  // 스템 감지 센서 등록
        stepView=findViewById(R.id.warn_message);
    }

    @Override
    protected void onResume(){
        super.onResume();
        sm.registerListener(this, sensor_step_detector, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause(){
        super.onPause();
        sm.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy){

    }

    // 센서값이 변할때
    @Override
    public void onSensorChanged(SensorEvent event){

        // 센서 유형이 스텝감지 센서인 경우 걸음수 +1
        switch (event.sensor.getType()){
            case Sensor.TYPE_STEP_DETECTOR:
                LockActivity.step++;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        stepView.setText(maxStep-step+"");
                    }
                });
                if(LockActivity.step==maxStep){
                    Intent serviceIntent = new Intent(StepCount.this, ForegroundService.class);
                    serviceIntent.setAction("startForeground");

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        ContextCompat.startForegroundService(StepCount.this, serviceIntent);
                        finish();
                    } else {
                        startService(serviceIntent);
                    }
                    finish();
                }
                Log.d(TAG,LockActivity.step+"");
                break;
        }
    }

}
