package com.example.pcsp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pcsp.VO.UserVO;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {

    private GoogleMap mMap;
    UserVO userVO;
    TextView textView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent();
        switch (item.getItemId()){
            case R.id.btn_carkey:
                ComponentName cname_carkey = new ComponentName("com.example.pcsp","com.example.pcsp.CarKeyActivity");
                intent.setComponent(cname_carkey);
                intent.putExtra("data","차키");
                break;

            case R.id.btn_menu:
                ComponentName cname_menu = new ComponentName("com.example.pcsp","com.example.pcsp.MyPageActivity");
                intent.setComponent(cname_menu);
                intent.putExtra("data","매뉴");
                break;

//            case R.id.btn_login:
//                ComponentName cname_login = new ComponentName("com.example.pcsp","com.example.pcsp.LoginActivity");
//                intent.setComponent(cname_login);
//                intent.putExtra("data","로그인");
//                break;

//            case R.id.btn_splash:
//                ComponentName cname_splash = new ComponentName("com.example.pcsp","com.example.pcsp.SplashActivity");
//                intent.setComponent(cname_splash);
//                intent.putExtra("data","스프레쉬");
        }
        startActivity(intent);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent user_intent = getIntent();
        textView = (TextView)findViewById(R.id.userName);
        userVO = user_intent.getParcelableExtra("USERVO");
        textView.setText(userVO.getUserName());

//        Intent login_intent = getIntent();
//        if (login_intent != null){
//            userVO = login_intent.getParcelableExtra("USERVO");
//            Log.i("From_LoginActivity","Service에서 USERVO 가져옴" + userVO.getUserName());
//        }
//
        Button btn = (Button)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                ComponentName componentName = new ComponentName("com.example.pcsp","com.example.pcsp.MapsActivity");
                intent.setComponent(componentName);
                intent.putExtra("check", "check");
                startActivity(intent);
                Log.i("_MAINACTIVITY","화면 이동");
            }
        });

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String test = intent.getExtras().getString("test");
        Log.i("test", "test");

        if (test != null) {
            textView.setText(test);
            Log.i("test", "test라고 찍힘");
        }
    }
}
