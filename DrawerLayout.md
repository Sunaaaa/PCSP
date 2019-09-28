## DrawerLayout

### activity_main

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.drawerlayout.widget.DrawerLayout
    android:id="@+id/drawerLayout"
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@android:color/holo_orange_light"
    tools:context=".MainActivity">

    <!--배경이 될 Layout-->
    <RelativeLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="#2030"
        android:gravity="center_horizontal">

        <Button
            android:id="@+id/btn_OpenDrawer"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_alignParentTop="true"
            android:layout_centerHorizontal="true"
            android:text="드로어 Open" />

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_centerInParent="true"
            android:text="구글 맵 위치"
            android:textColor="#f33"
            android:textSize="24dp" />

    </RelativeLayout>

    <!--드로어 화면이 될 Layout-->
    <RelativeLayout
        android:id="@+id/drawer"
        android:layout_width="300dp"
        android:layout_height="match_parent"
        android:gravity="center_horizontal"
        android:background="@android:color/holo_purple"
        android:layout_gravity="start">

        <LinearLayout
            android:orientation="vertical"
            android:layout_width="match_parent"
            android:layout_height="wrap_content">

        </LinearLayout>

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_alignParentTop="true"
            android:layout_centerHorizontal="true"
            android:layout_marginTop="23dp"
            android:text="사용자 메뉴"
            android:textColor="#f70"
            android:textSize="24dp" />

        <Button
            android:id="@+id/btn_CloseDrawer"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_alignParentBottom="true"
            android:layout_centerHorizontal="true"
            android:layout_marginBottom="10dp"
            android:text="드로어 닫기" />

    </RelativeLayout>

</androidx.drawerlayout.widget.DrawerLayout>
```

<br>

<br>

#### Error 해결

<android.support.v4.widget.DrawerLayout> 대신 <androidx.drawerlayout.widget.DrawerLayout>을 사용하면 에러 해결!

<br>

<br>

### 드로어 화면의 슬라이딩 방향

- android:layout_gravity = "start"   OR   android:layout_gravity = "left" : 좌측 슬라이드 메뉴
- android:layout_gravity = "end"   OR   android:layout_gravity = "right" : 우측 슬라이드 메뉴

<br>

<br>

#### 

#### MainActivity.java

```java
package com.example.plication2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 전체 화면인 DrawrLayout 객체 참조
        final DrawerLayout mDrawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);

        // Drawer 화면 (뷰) 객체 참조
        final View mDrawerView = (View)findViewById(R.id.drawer);

        // 드로어 화면을 열고 닫을 버튼 객체 참조
        Button btn_openDrawer = (Button)findViewById(R.id.btn_OpenDrawer);
        Button btn_closeDrawer = (Button)findViewById(R.id.btn_CloseDrawer);

        // 드로어 화면을 여는 버튼 이벤트
        btn_openDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(mDrawerView);
            }
        });

        // 드로어 화면을 닫는 버튼 이벤트
        btn_closeDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.closeDrawer(mDrawerView);
            }
        });

    }
}

```



<br>

<br>

### 구현 화면

#### 메인 화면

![1569682710694](https://user-images.githubusercontent.com/39547788/65818379-08877e00-e24c-11e9-860e-1e6e4a564bcf.jpg)

<br><br>

#### 드로어 화면

![1569682730926](https://user-images.githubusercontent.com/39547788/65818378-07eee780-e24c-11e9-9c2c-9149224f07de.jpg)

<br>

<br>

#### 

