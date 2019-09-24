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

        ImageView iv1 = (ImageView)findViewById(R.id.sp_image1);
        ImageView iv2 = (ImageView)findViewById(R.id.sp_image2);
        ImageView iv3 = (ImageView)findViewById(R.id.sp_image3);
        ImageView iv4 = (ImageView)findViewById(R.id.sp_image4);

        Glide.with(this).load(R.raw.joos_splash).into(iv1);
        Glide.with(this).load(R.raw.joos_splash).into(iv2);
        Glide.with(this).load(R.raw.joos_splash).into(iv3);
        Glide.with(this).load(R.raw.joos_splash).into(iv4);


    }
}
