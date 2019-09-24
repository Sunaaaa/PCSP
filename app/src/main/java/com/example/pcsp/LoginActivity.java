package com.example.pcsp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pcsp.VO.UserVO;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {

    EditText ed_id, ed_pw;
    String uid, upw;
    Button btn_login;
    UserVO userVO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Intent intent = getIntent();
        String a = intent.getStringExtra("data");

        Toast.makeText(getApplicationContext(), a, Toast.LENGTH_SHORT);

        ed_id = (EditText) findViewById(R.id.userId);
        ed_pw = (EditText) findViewById(R.id.userPw);

        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uid = ed_id.getText().toString();
                upw = ed_pw.getText().toString();

                Intent intent = new Intent();
                ComponentName cname = new ComponentName("com.example.pcsp",
                        "com.example.pcsp.PCSPService");
                intent.setComponent(cname);
                intent.putExtra("ID", uid);
                intent.putExtra("PW", upw);
                // 서비스 클래스를 찾아서 객체화 시키고 실행!
                startService(intent);

                // 서비스 결과가 "Login_OK"이면, MainActivity로 이동
            }
        });


    }

    public void doLogin() {
        Intent intent = new Intent();
        ComponentName cname_loginok = new ComponentName("com.example.pcsp",
                "com.example.pcsp.PCSPService");
        intent.setComponent(cname_loginok);
        intent.putExtra("USERVO", userVO);
        startActivity(intent);

    }

    public void makeDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(LoginActivity.this);
        alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();     //닫기
            }
        });
        alert.setMessage("존재하지 않는 아이디 또는 비밀번호 입니다.");
        alert.show();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String login_result = intent.getExtras().getString("LOGIN_RESULT");
        Log.i("LOGIN_RESULT_SERVICE", "");

        if (login_result != null) {
            String[] userData = login_result.split(",");
            if (userData[0].equals("OK")) {
                Log.i("LOGIN_OK","로그인 성공");
//                doLogin();
            }
            if (userData[0].equals("FAIL")) {
                Log.i("LOGIN_FAIL","로그인 실패");
                makeDialog();
            }
        }
    }

}

