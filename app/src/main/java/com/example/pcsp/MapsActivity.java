package com.example.pcsp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
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
        setContentView(R.layout.activity_maps);
        Log.i("_MapsActivity","Google Map 쨔잔");

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(37.235766, 127.054257);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Sweet Joo Home"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//        CameraUpdate zoom = CameraUpdateFactory.zoomTo(16);
//        mMap.animateCamera(zoom);

        for (int idx = 0; idx < 10; idx++) {
            // 1. 마커 옵션 설정 (만드는 과정)
            MarkerOptions makerOptions = new MarkerOptions();
            makerOptions // LatLng에 대한 어레이를 만들어서 이용할 수도 있다.
                    .position(new LatLng(37.52487 + idx, 126.92723))
                    .title("마커" + idx); // 타이틀.

            // 2. 마커 생성 (마커를 나타냄)
            mMap.addMarker(makerOptions);
        }
        // 카메라를 위치로 옮긴다.
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(37.52487, 126.92723)));

    }
}
