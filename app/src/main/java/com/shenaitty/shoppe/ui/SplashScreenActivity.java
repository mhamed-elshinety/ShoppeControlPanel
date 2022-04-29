package com.shenaitty.shoppe.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.shenaitty.shoppe.R;
import com.shenaitty.shoppe.data.Constants;

public class SplashScreenActivity extends AppCompatActivity {

    private Animation topAnimation, bottomAnimation;
    private TextView appNameTextView, sloganTextView;
    private ImageView logoImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        defineFields();
        defineViews();
        startAnimations();
        startMainActivity();
    }

    public void defineFields(){
        topAnimation = AnimationUtils.loadAnimation(this,R.anim.anim_splash_top);
        bottomAnimation = AnimationUtils.loadAnimation(this,R.anim.anim_splash_bottom);
    }

    public void defineViews(){
        appNameTextView = findViewById(R.id.app_name_tv);
        sloganTextView = findViewById(R.id.slogan_tv);
        logoImageView = findViewById(R.id.logo_iv);
    }


    private void startAnimations() {
        if (bottomAnimation != null && topAnimation != null) {
            appNameTextView.setAnimation(bottomAnimation);
            sloganTextView.setAnimation(bottomAnimation);
            logoImageView.setAnimation(topAnimation);
        }
    }

    private void startMainActivity(){
        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashScreenActivity.this,MainActivity.class));
            finish();
        }, Constants.SPLASH_TIME_OUT);
    }
}