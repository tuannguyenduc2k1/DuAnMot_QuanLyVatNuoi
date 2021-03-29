package com.example.myapplication.PetFragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.Dialog.DialogLoaiDongVat;
import com.example.myapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class LoaiDongVatFragment extends Fragment {
    private View mView;
    private FloatingActionButton btnAdd;

    public LoaiDongVatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_loai_dong_vat, container, false);
        btnAdd = (FloatingActionButton)mView.findViewById(R.id.btn_action_float_add_loai_dong_vat);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), DialogLoaiDongVat.class));

            }
        });
        return mView;
    }
}