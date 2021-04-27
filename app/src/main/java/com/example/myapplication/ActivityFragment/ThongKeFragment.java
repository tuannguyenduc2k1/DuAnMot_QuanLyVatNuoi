package com.example.myapplication.ActivityFragment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
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
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class ThongKeFragment extends Fragment {
    View view;
    HoaDonNhapDAO hoaDonNhapDAO;
    HoaDonXuatDAO hoaDonXuatDAO;
    List<BarEntry> lstVisitors;

    public ThongKeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_thong_ke, container, false);


//so do tron
//        double lai = hoaDonXuatDAO.getDoanhThuXuat() - hoaDonNhapDAO.getDoanhThuNhap();
//        double tongThe = hoaDonXuatDAO.getDoanhThuXuat()+hoaDonNhapDAO.getDoanhThuNhap()+lai;
//        double tiTrongNhap = Math.round((hoaDonNhapDAO.getDoanhThuNhap()/tongThe)*100);
//        double tiTrongXuat = Math.round((hoaDonXuatDAO.getDoanhThuXuat()/tongThe)*100);
//        double tiTrongLai = Math.round((lai/tongThe)*100);
//
//        lstVisitors.add(new PieEntry((float) hoaDonNhapDAO.getDoanhThuNhap(),"Tổng Nhập :"+tiTrongNhap+"%"));
//        lstVisitors.add(new PieEntry((float) hoaDonXuatDAO.getDoanhThuXuat(),"Tổng Xuất :"+tiTrongXuat+"%"));
//        lstVisitors.add(new PieEntry((float) lai,"Lãi :"+tiTrongLai+"%"));
//
//
//        PieDataSet pieDataSet = new PieDataSet(lstVisitors,"");
//        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
//        pieDataSet.setValueTextColor(Color.BLACK);
//        pieDataSet.setValueTextSize(16f);
//
//        PieData pieData = new PieData(pieDataSet);
//        pieChart.setData(pieData);
//        pieChart.getDescription().setEnabled(false);
//        pieChart.setCenterText("Tổng : "+tongThe);
//        pieChart.animate();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        BarChart barChart = view.findViewById(R.id.barChart);
        lstVisitors = new ArrayList<>();
        hoaDonNhapDAO = new HoaDonNhapDAO(getActivity());
        hoaDonXuatDAO = new HoaDonXuatDAO(getActivity());
        lstVisitors.add(new BarEntry(-1, (int) hoaDonNhapDAO.getDoanhThuNhap()));
        lstVisitors.add(new BarEntry(1, (int) hoaDonXuatDAO.getDoanhThuXuat()));


        BarDataSet barDataSet = new BarDataSet(lstVisitors, "Thong Ke");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setLabel("Hóa Đơn Nhập,Hóa Đơn Xuất");
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);

        //barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Doanh Thu");
        barChart.animateY(2000);
//        try {
//            hoaDonNhapDAO.getAll();
//            hoaDonXuatDAO.getAll();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

    }
}
