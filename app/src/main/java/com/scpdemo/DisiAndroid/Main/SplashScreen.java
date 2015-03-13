package com.scpdemo.DisiAndroid.Main;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;

import com.scpdemo.DisiAndroid.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Renan on 12/02/2015.
 */
public class SplashScreen extends Activity {
    // Set the duration of the splash screen
    private static final long SPLASH_SCREEN_DELAY = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


     //   setContentView(R.layout.splashscreem);
     //   Resources res= getResources();
     //   Resources drawable = res.getDrawable(R.drawable.shape_presentacion);
      //  textView tv_shape =(TextView) findViewByid(tv_shape);

      //  tv_shape.setBackgroundDrawable(drawable);


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
    }
}
