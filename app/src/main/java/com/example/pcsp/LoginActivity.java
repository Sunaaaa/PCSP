package com.example.pcsp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
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
    String uid, upw, logininfo;
    Button btn_login;
    UserVO userVO;
    PCSPService mPcspService;
    Boolean isService = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Intent service_intent = new Intent();
        ComponentName cname = new ComponentName("com.example.pcsp",
                "com.example.pcsp.PCSPService");
        service_intent.setComponent(cname);

        // 서비스 클래스를 찾아서 객체화 시키고 실행!
        startService(service_intent);

        Intent getIntent = getIntent();

        ServiceConnection conn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                PCSPService.MyBinder mb = (PCSPService.MyBinder) iBinder;
                mPcspService = mb.getService();
                isService = true;
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                isService = false;
            }
        };
        Intent intent = new Intent(LoginActivity.this, PCSPService.class);
        bindService(intent, conn, Context.BIND_ABOVE_CLIENT);


        ed_id = (EditText) findViewById(R.id.userId);
        ed_pw = (EditText) findViewById(R.id.userPw);

        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uid = ed_id.getText().toString();
                upw = ed_pw.getText().toString();
                Log.i("USERINFO에 대한 정보",uid + "," + upw);
                logininfo = uid + "," + upw;

                if (!isService) {
                    Toast.makeText(getApplicationContext(),
                            "서비스중이 아닙니다, 데이터받을수 없음",
                            Toast.LENGTH_LONG).show();
                }
                if(mPcspService == null){
                    Toast.makeText(getApplicationContext(),
                            "서비스가 바인딩이 안됬어 ㅠㅠ",
                            Toast.LENGTH_LONG).show();
                } else{
                    if( !uid.isEmpty() ) {
                        if (!upw.isEmpty()) {
                            mPcspService.clientToServer("USERLOGIN", logininfo);
                            Log.i("USERINFO를 보낸다!",logininfo);
                            ed_id.setText("");
                            ed_pw.setText("");
                        }
                    }
                }
            }
        });
    }

    public void doLogin() {
        Intent intent = new Intent();
        ComponentName cname_loginok = new ComponentName("com.example.pcsp",
                "com.example.pcsp.MainActivity");
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
        Log.i("LOGIN_RESULT_SERVICE", "과연 로그인 결과는!!!!!");

        if (login_result != null) {
            if (login_result.equals("OK")) {
                Log.i("LOGIN_OK","로그인 성공");
                userVO = intent.getParcelableExtra("USERINFO");
                Log.i("LOGIN_OK",userVO.getUserName());
                doLogin();
            }
            if (login_result.equals("FAIL")) {
                Log.i("LOGIN_FAIL","로그인 실패");
                makeDialog();
            }
        }
    }

}

