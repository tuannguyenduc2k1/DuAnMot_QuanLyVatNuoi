package com.example.myapplication.ActivityFragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.bumptech.glide.Glide;
import com.example.myapplication.Dangnhap.Login;

import com.example.myapplication.Model.UserDangNhap;
import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;


public class CaiDatFragment extends Fragment {
    CircleImageView image_profile;
    ImageButton dangxuat;
    TextView hovaten, diachi, gmail, sodienthoai, tentrangtrai;

    FirebaseFirestore firebaseFirestore ;
    FirebaseAuth firebaseAuth;
    Button chinhsua;
    UserDangNhap userDangNhap;
    String UserID;
    public CaiDatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cai_dat,container,false);


        dangxuat  =  view.findViewById(R.id.dangxuat);
        hovaten  =  view.findViewById(R.id.pr_hovaten);
        diachi  =  view.findViewById(R.id.diachi);
        sodienthoai  =  view.findViewById(R.id.sodienthoai);
        gmail  =  view.findViewById(R.id.gmail);
        tentrangtrai  =  view.findViewById(R.id.tentrangtrai);
        chinhsua  =  view.findViewById(R.id.chinhsuaprofile);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        UserID = firebaseAuth.getCurrentUser().getUid();

        DocumentReference documentReference = firebaseFirestore.collection("Users").document(UserID);

        documentReference.addSnapshotListener( new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {

                hovaten.setText(documentSnapshot.getString("Hovaten"));
                sodienthoai.setText(documentSnapshot.getString("Sodienthoai"));
                gmail.setText(documentSnapshot.getString("Tendangnhap"));
                diachi.setText(documentSnapshot.getString("Diachi"));
                tentrangtrai.setText(documentSnapshot.getString("TenFarm"));
            }
        });


        chinhsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View layout = getLayoutInflater().inflate(R.layout.dialog_edit_profile, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                ImageView back = layout.findViewById(R.id.backprofile);
                builder.setView(layout);
                AlertDialog alertDialog = builder.create();

                Button luu;
                EditText ed_hovaten, ed_diachi, ed_gmail, ed_sodienthoai, ed_tentrangtrai;

                luu  =  layout.findViewById(R.id.pr_luu);
                ed_hovaten  =  layout.findViewById(R.id.pr_hovaten);
                ed_diachi  =  layout.findViewById(R.id.pr_diachi);
                ed_sodienthoai  =  layout.findViewById(R.id.pr_sodienthoai);
                ed_gmail  =  layout.findViewById(R.id.pr_gmail);
                ed_tentrangtrai  =  layout.findViewById(R.id.pr_tentrangtrai);

                DocumentReference documentReference = firebaseFirestore.collection("Users").document(UserID);
                documentReference.addSnapshotListener( new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {

                        ed_hovaten.setText(documentSnapshot.getString("Hovaten"));
                        ed_sodienthoai.setText(documentSnapshot.getString("Sodienthoai"));
                        ed_gmail.setText(documentSnapshot.getString("Tendangnhap"));
                        ed_diachi.setText(documentSnapshot.getString("Diachi"));
                        ed_tentrangtrai.setText(documentSnapshot.getString("Tentrangtrai"));
                    }

                });


                luu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                               UserDangNhap userDangNhap = new UserDangNhap(ed_hovaten.getText().toString(),ed_diachi.getText().toString(),
                                       ed_sodienthoai.getText().toString(),ed_gmail.getText().toString(),ed_tentrangtrai.getText().toString());

                               firebaseFirestore.collection("Users").document(UserID).set(userDangNhap);
                    }
                });

                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });

                alertDialog.show();

            }
        });



        dangxuat.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               View layout = getLayoutInflater().inflate(R.layout.dialog_caidat_profile, null);
               AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                TextView hengio =  layout.findViewById(R.id.hengio);
//                TextView chinhsua =  layout.findViewById(R.id.chinhsuaprofile);
                TextView dl_dangxuat =  layout.findViewById(R.id.dl_dangxuat);
               builder.setView(layout);
               AlertDialog alertDialog = builder.create();
               dl_dangxuat.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       FirebaseAuth.getInstance().signOut();
                       startActivity(new Intent(getActivity(), Login.class));

                   }
               });


               alertDialog.show();
           }
       });
     return view;
    }

    private void showDataEdit() {
        DocumentReference documentReference = firebaseFirestore.collection("Users").document(UserID);
        documentReference.addSnapshotListener( new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {

                hovaten.setText(documentSnapshot.getString("Hovaten"));
                sodienthoai.setText(documentSnapshot.getString("Sodienthoai"));
                gmail.setText(documentSnapshot.getString("Tendangnhap"));
                diachi.setText(documentSnapshot.getString("Diachi"));
                tentrangtrai.setText(documentSnapshot.getString("Tentrangtrai"));
            }

        });
    }

    private void unit() {


    }
    private void uinit() {
    }

}