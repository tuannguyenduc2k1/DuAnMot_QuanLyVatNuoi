package com.example.myapplication.HoaDonFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.myapplication.Adapter.AdapterHoaDonNhap;
import com.example.myapplication.Adapter.AdapterHoaDonXuat;
import com.example.myapplication.DAO.HoaDonNhapDAO;
import com.example.myapplication.DAO.HoaDonXuatDAO;
import com.example.myapplication.Dialog.DialogHoaDonNhap;
import com.example.myapplication.Dialog.DialogHoaDonNhapUpdate;
import com.example.myapplication.Dialog.DialogHoaDonXuat;
import com.example.myapplication.Dialog.DialogHoaDonXuatUpdate;
import com.example.myapplication.Model.HoaDonNhap;
import com.example.myapplication.Model.HoaDonXuat;
import com.example.myapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class HoaDonXuatFragment extends Fragment {
    View mView;
    FloatingActionButton floatingActionButton;
    ListView listView;
    HoaDonXuatDAO hoaDonXuatDAO;
    public static List<HoaDonXuat> lstHoaDonXuat = new ArrayList<>();
    AdapterHoaDonXuat adapterHoaDonXuat;
    public HoaDonXuatFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView =  inflater.inflate(R.layout.fragment_hoa_don_xuat, container, false);
        floatingActionButton = (FloatingActionButton)mView.findViewById(R.id.btn_action_float_add_hoa_don_xuat);
        listView = (ListView)mView.findViewById(R.id.lv_hoa_don_xuat);
        registerForContextMenu(listView);
        hoaDonXuatDAO = new HoaDonXuatDAO(getActivity());
        try {
            lstHoaDonXuat = hoaDonXuatDAO.getAll();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        adapterHoaDonXuat = new AdapterHoaDonXuat(getActivity(),lstHoaDonXuat);
        listView.setAdapter(adapterHoaDonXuat);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(getActivity(), DialogHoaDonXuatUpdate.class);
                Bundle bb = new Bundle();
                bb.putString("MAHOADONXUAT",lstHoaDonXuat.get(position).getmMaHoaDonXuat());
                bb.putString("GIAXUAT",String.valueOf( lstHoaDonXuat.get(position).getmGiaXuat()));
                bb.putString("SOLUONGXUAT", String.valueOf(lstHoaDonXuat.get(position).getmSoLuongXuat()));
                bb.putString("NGAYXUAT", String.valueOf(lstHoaDonXuat.get(position).getmNgayXuat()));
                bb.putString("GHICHUXUAT", lstHoaDonXuat.get(position).getmGhiChuXuat());
                intent.putExtras(bb);
                startActivity(intent);
            }
        });


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), DialogHoaDonXuat.class));
            }
        });
        return mView;
    }
    //load lai du lieu
    @Override
    public void onResume() {
        super.onResume();
        lstHoaDonXuat.clear();
        try {
            lstHoaDonXuat = hoaDonXuatDAO.getAll();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        adapterHoaDonXuat.changeDataset(lstHoaDonXuat);
    }
}