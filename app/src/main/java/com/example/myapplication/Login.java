package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.ActivityFragment.MainActivity;

public class Login extends AppCompatActivity {
    Button btnLogin;
    TextView txt_dang_ki;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txt_dang_ki = findViewById(R.id.txt_dangki);
        btnLogin = findViewById(R.id.btn_dangnhap);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
            }
        });
        txt_dang_ki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Registration.class);
                startActivity(intent);
            }
        });
    }


}