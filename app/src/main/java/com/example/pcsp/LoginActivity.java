package com.example.pcsp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {
/*
    class LoginCheckRunnable implements Runnable{

        private String id, pw;
        private Handler handler;

        public LoginCheckRunnable(String id, String pw, Handler handler) {
            this.id = id;
            this.pw = pw;
            this.handler = handler;
        }

        @Override
        public void run() {
            String url = "http://70.12.115.72:80/bookSearch/searchTitle?USER_ID=" + id;
            try{
                // url을 URL 객체로 만들어 실제 url의 주소로 접속할 수 있다. (method 사용 가능)
                URL urlObject = new URL(url);
                HttpURLConnection con = (HttpURLConnection)urlObject.openConnection();

                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String input = "";
                StringBuffer sb = new StringBuffer();

                // input=br.readLine() : 서버가 보내주는 데이터를 다 읽음
                while ((input=br.readLine())!= null){
                    sb.append(input);
                }
                Log.i("LOGIN_USER_DATA", sb.toString());
                // 얻어온 결과 JSON 문자열을 Jackson Library를 이용해서 Java 객체 형태 (String[])로 변형

                // Jackson Library를 이용하여 JSON을 원래 형태 (String[])로 변환
                ObjectMapper mapper = new ObjectMapper();

                // sb를 읽어서 String[] 의 형태로 변형되어 resultArr로 떨어짐
                String[] resultArr = mapper.readValue(sb.toString(), String[].class);

                // Handler을 통해 String[] 을 쏴줌
                Bundle bundle = new Bundle();
                // Key - Value 쌍으로 바구니에 담는다
                bundle.putStringArray("USER", resultArr);

                Message msg = new Message();
                msg.setData(bundle);

                // Activity의 Handler가 받음
                handler.sendMessage(msg);

            } catch (Exception e){
                Log.i("DATA_ERROR", e.toString());
            }
        }
    }
*/
    EditText ed_id, ed_pw;
    String uid, upw;
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Intent intent = getIntent();
        String a = intent.getStringExtra("data");

        Toast.makeText(getApplicationContext(), a, Toast.LENGTH_SHORT);

        ed_id = (EditText)findViewById(R.id.userId);
        ed_pw = (EditText)findViewById(R.id.userPw);

        btn_login = (Button)findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uid = ed_id.getText().toString();
                upw = ed_pw.getText().toString();

                Intent intent = new Intent();
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.pcsp",
                        "com.example.pcsp.PCSPService");
                i.setComponent(cname);
                i.putExtra("ID",uid);
                i.putExtra("PW",upw);
                // 서비스 클래스를 찾아서 객체화 시키고 실행!
                startService(i);

                intent.setAction("CHATTING_WAITING_ROOM");
                i.putExtra("ID",uid);
                i.putExtra("PW",upw);
                startActivity(intent);
            }
        });


    }
}
