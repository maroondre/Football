package com.devdre.football.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.devdre.football.R;
import com.kaiguanjs.SplashLietener;
import com.kaiguanjs.utils.YQCUtils;


public class Splash extends Activity {

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        YQCUtils.splashAction(this, new SplashLietener() {
            @Override
            public void startMySplash(int version, String downUrl) {
                handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent=new Intent(Splash.this, Home.class);
                        startActivity(intent);
                        finish();
                    }
                },3000);
            }
        });



    }
}