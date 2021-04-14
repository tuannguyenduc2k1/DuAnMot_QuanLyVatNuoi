package com.example.myapplication.Dialog;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Adapter.AdapterHoaDonNhap;
import com.example.myapplication.DAO.DongVatDAO;
import com.example.myapplication.DAO.HoaDonNhapDAO;
import com.example.myapplication.DAO.HoaDonXuatDAO;
import com.example.myapplication.Model.DongVat;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class DialogHoaDonXuat extends AppCompatActivity {
    //    Context context;
    EditText edtNgayXuat,edtMaHoaDonXuat,edtGiaXuat,edtSoLuong,edtGhiChu;
    //    List<HoaDonNhap> lstHoaDonNhap = new ArrayList<>();
    List<DongVat> lstDongVat = new ArrayList<>();
    HoaDonXuatDAO hoaDonXuatDAO;
    DongVatDAO dongVatDAO;
    AdapterHoaDonNhap adapterHoaDonNhap;
    ListView listView;
    Spinner spinnerMaDongVat;
    String maDongVat = "";
    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_hoa_don_xuat_add);
    }
}