package com.example.myapplication.HoaDonFragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.myapplication.Adapter.AdapterHoaDonNhap;
import com.example.myapplication.DAO.HoaDonNhapDAO;
import com.example.myapplication.DAO.LoaiDongVatDAO;
import com.example.myapplication.Dialog.DialogDongVatUpdate;
import com.example.myapplication.Dialog.DialogHoaDonNhap;
import com.example.myapplication.Dialog.DialogHoaDonNhapUpdate;
import com.example.myapplication.Model.HoaDonNhap;
import com.example.myapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class HoaDonNhapFragment extends Fragment {
    View mView;
    FloatingActionButton btnAdd;
    EditText timkiemnhap;
    ListView listView;
    HoaDonNhapDAO hoaDonNhapDAO;
    public static List<HoaDonNhap> lstHoaDonNhap = new ArrayList<>();
    AdapterHoaDonNhap adapterHoaDonNhap ;
    public HoaDonNhapFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView =  inflater.inflate(R.layout.fragment_hoa_don_nhap, container, false);
        btnAdd = (FloatingActionButton)mView.findViewById(R.id.btn_action_float_add_hoa_don_nhap);
        timkiemnhap = (EditText) mView.findViewById(R.id.timkiemhoadonnhap);
        listView = (ListView) mView.findViewById(R.id.lv_hoa_don_nhap);
        registerForContextMenu(listView);
        hoaDonNhapDAO = new HoaDonNhapDAO(getActivity());

        try {
            lstHoaDonNhap = hoaDonNhapDAO.getAll();

        } catch (ParseException e) {
            e.printStackTrace();
        }

        adapterHoaDonNhap = new AdapterHoaDonNhap(getActivity(),lstHoaDonNhap);
        listView.setAdapter(adapterHoaDonNhap);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                HoaDonNhap hoaDonNhap = (HoaDonNhap) adapterView.getItemAtPosition(position);
                Intent intent = new Intent(getActivity(), DialogHoaDonNhapUpdate.class);
                Bundle b = new Bundle();
                b.putString("m",lstHoaDonNhap.get(position).getmMaHoaDonNhap());
                b.putString("GIANHAP",String.valueOf( lstHoaDonNhap.get(position).getmGiaNhap()));
                b.putString("SOLUONGNHAP", String.valueOf(lstHoaDonNhap.get(position).getmSoLuongNhap()));
                b.putString("NGAYNHAP",lstHoaDonNhap.get(position).getmNgayNhap());
                b.putString("GHICHU", lstHoaDonNhap.get(position).getmGhiChuNhap());
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), DialogHoaDonNhap.class));
            }
        });
        listView.setTextFilterEnabled(true);
        timkiemnhap.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                System.out.println("Text [" + s + "] - Start [" + start + "] - Before [" + before + "] - Count [" + count + "]");
                if (count < before) {
                    adapterHoaDonNhap.resetData();

                }
                adapterHoaDonNhap.getFilter().filter(s.toString());
//                String text = timkiemnhap.getText().toString().toLowerCase(Locale.getDefault());
//                filter(text);

            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
//        adapterHoaDonNhap.getFilter();
//        adapterHoaDonNhap.resetData();
        return mView;
    }

    //load lai du lieu
    @Override
    public void onResume() {
        super.onResume();
//        lstHoaDonNhap.clear();

        try {
            lstHoaDonNhap = hoaDonNhapDAO.getAll();
            adapterHoaDonNhap.changeDataset(lstHoaDonNhap);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }


}