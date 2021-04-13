package com.example.myapplication.PetFragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.myapplication.Adapter.AdapterLoaiDongVat;
import com.example.myapplication.DAO.LoaiDongVatDAO;
import com.example.myapplication.Dialog.DialogLoaiDongVat;
import com.example.myapplication.Dialog.DialogLoaiDongVatUpdate;
import com.example.myapplication.Model.LoaiDongVat;
import com.example.myapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class LoaiDongVatFragment extends Fragment {
    private View mView;
    private FloatingActionButton btnFloatingActionButton;
    //private EditText edt_ldv;
    public static List<LoaiDongVat> loaiDongVatList = new ArrayList<>();
    ListView lv_ldv;
    LoaiDongVatDAO loaiDongVatDAO;
    AdapterLoaiDongVat adapterLoaiDongVat;

    public LoaiDongVatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_loai_dong_vat, container, false);
        lv_ldv = (ListView)mView.findViewById(R.id.lv_loai_dong_vat);
        registerForContextMenu(lv_ldv);
        loaiDongVatDAO = new LoaiDongVatDAO(getActivity());
        try {
            loaiDongVatList = loaiDongVatDAO.getAll();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        adapterLoaiDongVat = new AdapterLoaiDongVat(getActivity(),loaiDongVatList);
        lv_ldv.setAdapter(adapterLoaiDongVat);

        lv_ldv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //xu li onclick khi bam vao item
                startActivity(new Intent(getActivity(), DialogLoaiDongVat.class));
                Intent intent = new Intent(getActivity(), DialogLoaiDongVatUpdate.class);
                Bundle b = new Bundle();
                b.putString("LOAIDONGVAT", loaiDongVatList.get(position).getmLoaiDongVat());
                intent.putExtras(b);
                startActivity(intent);

            }
        });
        btnFloatingActionButton = (FloatingActionButton)mView.findViewById(R.id.btn_action_float_add_loai_dong_vat);
        btnFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),DialogLoaiDongVat.class));
            }
        });


        return mView;

    }
    //load lai du lieu
    @Override
    public void onResume() {
        super.onResume();
        loaiDongVatList.clear();
        try {
            loaiDongVatList = loaiDongVatDAO.getAll();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            adapterLoaiDongVat.changeDataset(loaiDongVatDAO.getAll());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}