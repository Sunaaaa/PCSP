package com.example.pcsp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Intent intent = getIntent();
        Toast.makeText(getApplicationContext(), intent.getStringExtra("data"), Toast.LENGTH_SHORT).show();

        ImageView iv = (ImageView)findViewById(R.id.sp_image);

        Glide.with(this).load(R.raw.joos_splash).into(iv);


    }
}
