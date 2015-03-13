package com.scpdemo.DisiAndroid.Main;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import com.scpdemo.DisiAndroid.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Renan on 12/02/2015.
 */
public class SplashScreen extends Activity {

    private static final long SPLASH_SCREEN_DELAY = 5000;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        // Set portrait orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // Hide title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.splashscreem);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                // Start the next activity
                Intent mainIntent = new Intent().setClass(
                        SplashScreen.this, Activity_Login.class);
                startActivity(mainIntent);

                // Close the activity so the user won't able to go back this
                // activity pressing Back button
                finish();
            }
        };

        // Simulate a long loading process on application startup.
        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);

        setContentView(R.layout.splashscreem);
        Resources res = getResources();
        Drawable drawable = res.getDrawable(R.drawable.shape_presentacion);
        TextView tv_shape =(TextView)findViewById(R.id.tv_principal);
        tv_shape.setBackground(drawable);
    }
}
