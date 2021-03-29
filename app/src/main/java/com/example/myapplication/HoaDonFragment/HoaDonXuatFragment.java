package com.example.myapplication.HoaDonFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.myapplication.Dialog.DialogHoaDonNhap;
import com.example.myapplication.Dialog.DialogHoaDonXuat;
import com.example.myapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class HoaDonXuatFragment extends Fragment {
    View mView;
    FloatingActionButton btnAdd;
    public HoaDonXuatFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView =  inflater.inflate(R.layout.fragment_hoa_don_xuat, container, false);
        btnAdd = (FloatingActionButton)mView.findViewById(R.id.btn_action_float_add_hoa_don_xuat);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), DialogHoaDonXuat.class));
            }
        });
        return mView;
    }
}