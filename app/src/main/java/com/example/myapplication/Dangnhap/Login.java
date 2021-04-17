package com.example.myapplication.Dangnhap;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.service.controls.actions.FloatAction;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.ActivityFragment.MainActivity;
import com.example.myapplication.R;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


import org.w3c.dom.Text;

public class Login extends AppCompatActivity {
    EditText tendangnhap, matkhau;
    Button btnLogin;
    TextView txt_dang_ki;
    Intent intent;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUserl;
    FirebaseFirestore firebaseFirestore;

    //hàm tự đọng lưu mật khẩu và đăng nhập
    @Override
    protected void onStart() {
        super.onStart();
        firebaseUserl = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUserl != null) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        unit();
        firebaseAuth = FirebaseAuth.getInstance();
        unitUI();
      /*  CaiDatFragment caiDatFragment = new CaiDatFragment();
        FragmentManager manager = getSupportFragmentManager();
      manager.beginTransaction().add(R.id.action_cai_dat,caiDatFragment).commit();

       */
    }

    private void unitUI() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog pd = new ProgressDialog(Login.this);
                pd.setMessage("Đang Load");
                pd.show();
                String edt_tendangnhap = tendangnhap.getText().toString();
                String edt_matkhau = matkhau.getText().toString();
                if (TextUtils.isEmpty(edt_tendangnhap) || TextUtils.isEmpty(edt_matkhau)){
                    pd.dismiss();
                    Toast.makeText(Login.this, "Không được để trống", Toast.LENGTH_SHORT).show();
                } else {
                    firebaseAuth.signInWithEmailAndPassword(edt_tendangnhap,edt_matkhau).
                    addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                         Intent intent = new Intent(Login.this,MainActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                        finish();

                            } else {
                                pd.dismiss();
                                Toast.makeText(Login.this, "Đang nhập thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }

        });

        txt_dang_ki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Login.this, Registration.class);
                startActivity(intent);
            }
        });
        }


    private void unit() {
        txt_dang_ki = findViewById(R.id.txt_dangki);
        btnLogin = findViewById(R.id.btn_dangnhap);
        tendangnhap = findViewById(R.id.edt_tendangnhap);
        matkhau = findViewById(R.id.ext_matkhau);
        firebaseAuth = FirebaseAuth.getInstance();
    }
}



