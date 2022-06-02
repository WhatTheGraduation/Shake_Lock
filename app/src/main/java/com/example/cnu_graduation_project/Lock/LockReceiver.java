package com.example.cnu_graduation_project.Lock;

import static com.example.cnu_graduation_project.TaskTag.WINDOW_ON;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Vibrator;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.cnu_graduation_project.Lock.LockActivity;

import java.util.TimerTask;

public class LockReceiver extends BroadcastReceiver {
    String TAG="LockReceiver";
    @RequiresApi(api = Build.VERSION_CODES.O_MR1)
    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            Log.d(TAG, "Screen Off");
            WINDOW_ON=false;
            Log.d(TAG,"Screen"+WINDOW_ON);
        }
        if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            Log.d(TAG, "Screen On");

            Intent i = new Intent(context, LockActivity.class);

            /**
             * 화면 종료 인식시 리시버 호출
             */
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
            /**
             * 화면 종료 인식시 리시버 호출
             */
            WINDOW_ON=true;
            Log.d(TAG,"Screen"+WINDOW_ON);
        }
    }

}
