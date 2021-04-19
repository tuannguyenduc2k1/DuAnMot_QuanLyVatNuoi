package com.example.myapplication.ActivityFragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.service.controls.templates.ControlTemplate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.myapplication.DAO.HoaDonNhapDAO;
import com.example.myapplication.DAO.HoaDonXuatDAO;
import com.example.myapplication.Model.HoaDonNhap;
import com.example.myapplication.Model.HoaDonXuat;
import com.example.myapplication.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.ParseException;
import java.util.ArrayList;


public class ThongKeFragment extends Fragment {
    View view;
    HoaDonNhapDAO hoaDonNhapDAO  ;
    HoaDonXuatDAO hoaDonXuatDAO ;
    public ThongKeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_thong_ke,container,false);
        PieChart pieChart = view.findViewById(R.id.pieChart);
        ArrayList<PieEntry> visitors = new ArrayList<>();
        hoaDonNhapDAO = new HoaDonNhapDAO(getActivity());
        hoaDonXuatDAO = new HoaDonXuatDAO(getActivity());


        visitors.add(new PieEntry((float) hoaDonNhapDAO.getDoanhThuNhap(),"Tổng Nhập"));
        visitors.add(new PieEntry((float) hoaDonXuatDAO.getDoanhThuXuat(),"Tổng Xuất"));
        visitors.add(new PieEntry((float) (hoaDonXuatDAO.getDoanhThuXuat() - hoaDonNhapDAO.getDoanhThuNhap()),"Lãi"));


        PieDataSet pieDataSet = new PieDataSet(visitors,"SƠ ĐỒ THỐNG KÊ");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);

        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText("Tổng : "+String.valueOf(hoaDonXuatDAO.getDoanhThuXuat() + hoaDonNhapDAO.getDoanhThuNhap()));
        pieChart.animate();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            hoaDonXuatDAO.getAll();
            hoaDonNhapDAO.getAll();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}