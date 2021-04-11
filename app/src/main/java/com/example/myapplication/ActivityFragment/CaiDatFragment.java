package com.example.myapplication.ActivityFragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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

import de.hdodenhof.circleimageview.CircleImageView;


public class CaiDatFragment extends Fragment {
    CircleImageView image_profile;
    ImageView dangxuat;
    TextView hovaten, diachi, gmail, sodienthoai, gioitinh, tentrangtrai, ngaysinh;

    FirebaseFirestore firebaseFirestore ;
    FirebaseAuth firebaseAuth;

    String UserID;
    public CaiDatFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cai_dat,container,false);


        dangxuat  =  view.findViewById(R.id.dangxuat);
        image_profile  =  view.findViewById(R.id.image_profile);
        hovaten  =  view.findViewById(R.id.pr_hovaten);
        diachi  =  view.findViewById(R.id.diachi);
        sodienthoai  =  view.findViewById(R.id.sodienthoai);
        gmail  =  view.findViewById(R.id.gmail);
        gioitinh  =  view.findViewById(R.id.gioitinh);
        tentrangtrai  =  view.findViewById(R.id.tentrangtrai);
        ngaysinh  =  view.findViewById(R.id.ngaysinh);


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
            }

        });

//        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//        firebaseUser = firebaseAuth.getCurrentUser();
//        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                UserDangNhap userDangNhap = snapshot.getValue(UserDangNhap.class);
//                assert userDangNhap !=null;
//                hovaten.setText(userDangNhap.getHovaten());
//                if (userDangNhap.getImage_profile().equals("default")){
//                    image_profile.setImageResource(R.drawable.ic_baseline_add_24);
//                }else {
////                    Glide.with(getApplicatiomContext()).load(userDangNhap.getImage_profile()).into(image_profile);
//                }
//         }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//
//        userInfo();
//
//        if (profileid.equals(firebaseUser.getUid())){
//
//        }

        dangxuat.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               FirebaseAuth.getInstance().signOut();
               startActivity(new Intent(getActivity(), Login.class));
           }
       });
     return view;
    }

    private void unit() {
    }

    private void userInfo(){
//        DatabaseReference all = FirebaseDatabase.getInstance().getReference();
//        Query query = FirebaseDatabase.getInstance().getReference("Users");
//        query.addChildEventListener(new ChildEventListener() {
//                @Override
//                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                    UserDangNhap user = snapshot.getValue(UserDangNhap.class);
//
//                    hovaten.setText(snapshot.getValue().toString());
//                    gmail.setText(user.getGmail());
//                    sodienthoai.setText(user.getSodienthoai());
//                }
//
//                @Override
//                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//                }
//
//                @Override
//                public void onChildRemoved(@NonNull DataSnapshot snapshot) {
//
//                }
//
//                @Override
//                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//
//                }
//            });

//                reference  = FirebaseDatabase.getInstance().getReference("Users");
//                    reference.addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                            UserDangNhap user = snapshot.getValue(UserDangNhap.class);
//
//                            Glide.with(getContext()).load(user.getImage_profile()).into(image_profile);
//                            hovaten.setText(user.getHovaten());
//                            gmail.setText(user.getGmail());
//                            sodienthoai.setText(user.getSodienthoai());
//                        }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
    }
    private void uinit() {
    }

}