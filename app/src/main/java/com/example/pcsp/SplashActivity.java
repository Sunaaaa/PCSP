package com.example.pcsp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.pcsp.SharedPreferences.SaveSharedPreference;

public class SplashActivity extends AppCompatActivity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView iv1 = (ImageView)findViewById(R.id.sp_image1);
        ImageView iv2 = (ImageView)findViewById(R.id.sp_image2);
        ImageView iv3 = (ImageView)findViewById(R.id.sp_image3);
        ImageView iv4 = (ImageView)findViewById(R.id.sp_image4);

        Glide.with(this).load(R.raw.joos_splash).into(iv1);
        Glide.with(this).load(R.raw.yjc_s2).into(iv2);
        Glide.with(this).load(R.raw.yjc_s3).into(iv3);
        Glide.with(this).load(R.raw.yjc_s4).into(iv4);

        Handler mHandler = new Handler();
        mHandler.postDelayed(new SplashHandler(), 2000);
        Log.i("SplashAcitivty", "SplashAcitivty");

    }

    private class SplashHandler implements Runnable{

        @Override
        public void run() {

            // call LoginActivity
            if(SaveSharedPreference.getUserName(SplashActivity.this).length() == 0) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                SplashActivity.this.finish();
            } else {
                // Call MapsActivity
                startActivity(new Intent(getApplicationContext(), MapsActivity.class));
                SplashActivity.this.finish();
            }
        }
    }
    @Override
    public void onBackPressed() {
        //초반 플래시 화면에서 넘어갈때 뒤로가기 버튼 못누르게 함
    }
}
