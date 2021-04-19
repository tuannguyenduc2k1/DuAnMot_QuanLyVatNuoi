package com.example.myapplication.Dangnhap;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.ActivityFragment.MainActivity;
import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Registration extends AppCompatActivity {
    Button BtnRegistration;
    EditText Tendangnhap;
    EditText Sodienthoai;
    EditText MatKhau;
    EditText Hovaten;
    EditText diachi;
    EditText tennongtrai;
    ProgressBar progressBar;
    private FirebaseAuth mAuth;
    FirebaseFirestore firebaseFirestore;
    String userID;
    ProgressDialog pd;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        unit();
        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        unitUI();
    }
    private void unitUI() {
        BtnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    pd = new ProgressDialog(Registration.this);
                    pd.setMessage("Please wait...");
                    pd.show();

                    String str_hovaten = Hovaten.getText().toString();
                    String str_tendangnhap = Tendangnhap.getText().toString();
                    String str_matKhau = MatKhau.getText().toString();
                    String str_sodienthoai = Sodienthoai.getText().toString();
                    String str_diachi = diachi.getText().toString();
                    String str_tennongtrai = tennongtrai.getText().toString();

                    //String P_day = "^(0?[1-9]|1[012])[- /.](0?[1-9]|[12][0-9]|3[01])[- /.](19|20)?[0-9]{2}$";

                    //String p_hoten = "[a-zA-Z ]+";
                    //String p_email = "^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$";
//                String p_sdt = "0[0-9]{9}";
                    //String p_scmt = "[0-9]{12}";
                    String regexDate = "^(0?[1-9]|1[012])[- /.](0?[1-9]|[12][0-9]|3[01])[- /.](19|20)?[0-9]{2}$";


                    if (TextUtils.isEmpty(str_tendangnhap) || TextUtils.isEmpty(str_matKhau)|| TextUtils.isEmpty(str_diachi) || TextUtils.isEmpty(str_tennongtrai) || TextUtils.isEmpty(str_hovaten) || TextUtils.isEmpty(str_sodienthoai) )
                    {
                        pd.dismiss();
                        Toast.makeText(Registration.this, "Không được để trống",Toast.LENGTH_LONG).show();
                        return;
                    } else if(str_matKhau.length()<6) {
                        pd.dismiss();
                        Toast.makeText(Registration.this, "Mật khẩu không được để dưới 6 kí tự ", Toast.LENGTH_LONG).show();
                        return;

                    } else if(!str_sodienthoai.matches(regexDate)) {

                        pd.dismiss();
                        Toast.makeText(Registration.this, "Sai số điện thoại ", Toast.LENGTH_LONG).show();
                    }else {
                        mAuth.createUserWithEmailAndPassword(str_tendangnhap,str_matKhau).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    userID = mAuth.getCurrentUser().getUid();
                                    DocumentReference documentReference = firebaseFirestore.collection("Users").document(userID);
                                    Map<String,Object> user = new HashMap<>();
                                    user.put("Bio","");
                                    user.put("Hovaten",str_hovaten);
                                    user.put("Tendangnhap",str_tendangnhap);
                                    user.put("Matkhau",str_matKhau);
                                    user.put("Sodienthoai",str_sodienthoai);
                                    user.put("Diachi",str_diachi);
                                    user.put("TenFarm",str_tennongtrai);
                                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Log.d("TAG","ID");
                                        }
                                    });
                                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                }else {
                                    pd.dismiss();
                                    Toast.makeText(Registration.this, "Thất bại", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }

                }catch (Exception e){

                }



            }
        });
    }
//                Map<String,Object> user = new HashMap<>();
//                user.put("Hovaten",str_hovaten);
//                user.put("Tendangnhap",str_tendangnhap);
//                user.put("Matkhau",str_matKhau);
//                user.put("Sodienthoai",str_sodienthoai);
//                db.collection("users")
//                        .add(user)
//                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                            @Override
//                            public void onSuccess(DocumentReference documentReference) {
//                                Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
//                            }
//                        })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Log.w("TAG", "Error adding document", e);
//                            }
//                        });



    private void unit() {
        BtnRegistration = findViewById(R.id.btn_dangKi);
        Tendangnhap = findViewById(R.id.edt_dkemail);
        MatKhau = findViewById(R.id.ext_matkhau);
        Hovaten =  findViewById(R.id.ext_user_name);
        Sodienthoai = findViewById(R.id.ext_sodienthaoi);
        diachi = findViewById(R.id.ext_diachi);
        tennongtrai = findViewById(R.id.ext_namefarm);
    }
}