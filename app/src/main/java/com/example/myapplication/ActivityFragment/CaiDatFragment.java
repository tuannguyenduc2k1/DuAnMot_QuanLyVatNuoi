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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.GenericLifecycleObserver;

import com.bumptech.glide.Glide;
import com.example.myapplication.Dangnhap.Login;
import com.example.myapplication.Dangnhap.Registration;
import com.example.myapplication.Model.UserDangNhap;
import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class CaiDatFragment extends Fragment {

    ImageView dangxuat, image_profile;
    TextView hovaten, diachi, gmail, sodienthoai, gioitinh, tentrangtrai, ngaysinh;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    String profileid;

    public CaiDatFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cai_dat,container,false);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        SharedPreferences preferences = getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        profileid = preferences.getString("profileid","none");

        dangxuat  =  view.findViewById(R.id.dangxuat);
        image_profile  =  view.findViewById(R.id.image_profile);
        hovaten  =  view.findViewById(R.id.pr_hovaten);
        diachi  =  view.findViewById(R.id.diachi);
        sodienthoai  =  view.findViewById(R.id.sodienthoai);
        gmail  =  view.findViewById(R.id.gmail);
        gioitinh  =  view.findViewById(R.id.gioitinh);
        tentrangtrai  =  view.findViewById(R.id.tentrangtrai);
        ngaysinh  =  view.findViewById(R.id.ngaysinh);
        userInfo();

        if (profileid.equals(firebaseUser.getUid())){

        }

        dangxuat.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               FirebaseAuth.getInstance().signOut();
               startActivity(new Intent(getActivity(), Login.class));
           }
       });
     return view;
    }
    private void userInfo(){
//        DatabaseReference all = FirebaseDatabase.getInstance().getReference();
        Query query = FirebaseDatabase.getInstance().getReference("Users");
        query.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    UserDangNhap user = snapshot.getValue(UserDangNhap.class);

                    hovaten.setText(snapshot.getValue().toString());
                    gmail.setText(user.getGmail());
                    sodienthoai.setText(user.getSodienthoai());
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

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