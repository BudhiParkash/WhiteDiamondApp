package com.example.whitediamond.ui.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.whitediamond.MainActivity;
import com.example.whitediamond.R;

public class SplashScreen extends AppCompatActivity {

    private ImageView mSplashlogo;
    private Animation splashAnimation;
//    Handler handler;
//    Runnable runnable;
    private String tokken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        initView();
        FullScreencall();
        SharedPreferences prefs = getSharedPreferences("ProfileData", MODE_PRIVATE);
        tokken = prefs.getString("tokken", "no tokkens");

        splashAnimation = AnimationUtils.loadAnimation(this , R.anim.splashanimation);

        mSplashlogo.setAnimation(splashAnimation);

        splashAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
               new Handler(getMainLooper()).postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       if (tokken.equals("no tokkens")) {
                           Intent intent = new Intent(SplashScreen.this , Login.class);
                           startActivity(intent);
                           finish();

                       } else {
                           Intent intent = new Intent(SplashScreen.this , MainActivity.class);
                           startActivity(intent);
                           finish();
                       }
                   }
               },500);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
    public void FullScreencall() {
        if(Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if(Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }
    private void initView () {
        mSplashlogo = findViewById(R.id.splashlogo);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.splashstatusbarcolor));
        }

    }
}