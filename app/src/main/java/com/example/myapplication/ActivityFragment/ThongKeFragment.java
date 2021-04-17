package com.example.myapplication.ActivityFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.myapplication.DAO.HoaDonNhapDAO;
import com.example.myapplication.DAO.HoaDonXuatDAO;
import com.example.myapplication.Model.HoaDonXuat;
import com.example.myapplication.R;


public class ThongKeFragment extends Fragment {

    TextView hovaten, diachi, gmail;
    HoaDonXuatDAO hoaDonXuatDAO;
    public ThongKeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thong_ke,container,false);
        hovaten = view.findViewById(R.id.thongke_homnay);


        return view;
    }
}