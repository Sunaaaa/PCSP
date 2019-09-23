package com.example.pcsp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

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



            }
        });


    }
}
