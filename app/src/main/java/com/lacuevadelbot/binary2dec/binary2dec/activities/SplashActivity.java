package com.lacuevadelbot.binary2dec.binary2dec.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.lacuevadelbot.binary2dec.R;

public class SplashActivity extends AppCompatActivity {

    TextView mTextViewDe;
    TextView mTextViewLCDB;
    ImageView mImageIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // ANIMACCIONES
        Animation animationUp = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_arriba);
        Animation animationDown = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_abajo);

        mTextViewDe = findViewById(R.id.deTextView);
        mTextViewLCDB = findViewById(R.id.lcdbTextView);
        mImageIcon = findViewById(R.id.logoImageView);

        mTextViewDe.setAnimation(animationDown);
        mTextViewLCDB.setAnimation(animationDown);
        mImageIcon.setAnimation(animationUp);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 4000);

    }
}