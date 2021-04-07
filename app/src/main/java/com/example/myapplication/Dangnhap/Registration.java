package com.example.myapplication.Dangnhap;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.TestLooperManager;
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
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Registration extends AppCompatActivity {
    Button BtnRegistration;
    EditText Tendangnhap;
    EditText Sodienthoai;
    EditText MatKhau;
    EditText Hovaten;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    ProgressDialog pd;
    String str_hovaten ;
    String str_tendangnhap ;
    String str_sodienthoai ;
    String str_matKhau ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        firebaseAuth = FirebaseAuth.getInstance();
        unit();
        unitUI();
    }
    private void unitUI() {
        BtnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd = new ProgressDialog(Registration.this);
                pd.setMessage("Đang Load");
                pd.show();
                str_hovaten = Hovaten.getText().toString();
                str_tendangnhap = Tendangnhap.getText().toString();
                str_matKhau = MatKhau.getText().toString();
                str_sodienthoai = Sodienthoai.getText().toString();

                if (TextUtils.isEmpty(str_tendangnhap) || TextUtils.isEmpty(str_matKhau) || TextUtils.isEmpty(str_hovaten) || TextUtils.isEmpty(str_sodienthoai) ){
                    pd.dismiss();
                    Toast.makeText(Registration.this, "Không được để trống",Toast.LENGTH_LONG).show();
                } else if(str_matKhau.length()<6){
                    pd.dismiss();
                    Toast.makeText(Registration.this, "Mật khẩu không được để dưới 6 kí tự ",Toast.LENGTH_LONG).show();
                } else {
                    registration(str_hovaten,str_tendangnhap,str_matKhau,str_sodienthoai);
                }
            }
        });
    }

    private  void registration  (String hovaten,String tendangnhap,String sodienthoai, String matkhau){
        firebaseAuth.createUserWithEmailAndPassword(tendangnhap,matkhau)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                    String userid = firebaseUser.getUid();

                    databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(userid);

                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("id",userid);
                    hashMap.put("hoten",hovaten);
                    hashMap.put("tendangnhap",tendangnhap);
                    hashMap.put("matkhau",matkhau);
                    hashMap.put("sdt",sodienthoai);
                    hashMap.put("bio","");
                    hashMap.put("Image","https://firebasestorage.googleapis.com/v0/b/quan-ly-vat-nuoi.appspot.com/o/profile.png?alt=media&token=a71de2b2-8d20-4d7e-94dd-9c7ea830ff95");
                    databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                pd.dismiss();
                                Intent intent = new Intent(Registration.this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        }
                    });
                } else {
                    pd.dismiss();
                    Toast.makeText(Registration.this, "Đăng nhập thất bại",Toast.LENGTH_LONG).show();

                }
            }
        });
    }
    private void unit() {
        BtnRegistration = findViewById(R.id.btn_dangKi);
        Tendangnhap = findViewById(R.id.edt_dkemail);
        MatKhau = findViewById(R.id.ext_dkmatkhau);
        Hovaten =  findViewById(R.id.ext_user_name);
        Sodienthoai = findViewById(R.id.ext_sodienthaoi);
    }
}