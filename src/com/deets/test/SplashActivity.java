package com.deets.test;

import com.cardsui.example.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;


public class SplashActivity extends Activity{
    private static String TAG = SplashActivity.class.getName();
    private static long SLEEP_TIME = 5;    // Sleep for some time
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);

     Log.d("SplashActivity","THIS IS CREATED");
     this.requestWindowFeature(Window.FEATURE_NO_TITLE);    // Removes title bar
     this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,     WindowManager.LayoutParams.FLAG_FULLSCREEN);    // Removes notification bar

     setContentView(R.layout.splash);
     
     EditText search = (EditText)this.findViewById(R.id.search_bar);
     search.setOnClickListener(new OnClickListener()
     {

        @Override
        public void onClick(View arg0)
        {
            handler = new Handler();
            handler.postDelayed(new Runnable()
            {
                public void run()
                {
                    Intent intent = new Intent(getCtx(), MainActivity.class);
                    getCtx().startActivity(intent);
                    finish();
                }
            }, 8500);
        }
         
     });

         
     // Start timer and launch main activity
     /*
     IntentLauncher launcher = new IntentLauncher();
     launcher.start();
     */
   }

    private class IntentLauncher extends Thread {
     @Override
     /**
      * Sleep for some time and than start new activity.
      */
     public void run() {
        try {
           // Sleeping
           Thread.sleep(SLEEP_TIME*1000);
        } catch (Exception e) {
           Log.e(TAG, e.getMessage());
        }

        // Start main activity
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        SplashActivity.this.startActivity(intent);
        SplashActivity.this.finish();
     }
     
   }
    
    private SplashActivity getCtx()
    {
        return this;
    }
}
