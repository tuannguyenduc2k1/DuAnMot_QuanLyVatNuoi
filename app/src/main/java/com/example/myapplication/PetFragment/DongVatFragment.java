package com.example.myapplication.PetFragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.myapplication.Dialog.DialogDongVat;
import com.example.myapplication.Dialog.DialogLoaiDongVat;
import com.example.myapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class DongVatFragment extends Fragment {
    private View mView;
    private FloatingActionButton btnAdd;

    public DongVatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView =  inflater.inflate(R.layout.fragment_dong_vat, container, false);
        btnAdd = (FloatingActionButton)mView.findViewById(R.id.btn_action_float_add_dong_vat);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), DialogDongVat.class));

            }
        });
        return mView;
    }
}