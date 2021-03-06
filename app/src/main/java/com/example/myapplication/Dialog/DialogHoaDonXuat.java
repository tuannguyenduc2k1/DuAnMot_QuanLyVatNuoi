package com.example.myapplication.Dialog;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Adapter.AdapterHoaDonNhap;
import com.example.myapplication.DAO.DongVatDAO;
import com.example.myapplication.DAO.HoaDonNhapDAO;
import com.example.myapplication.DAO.HoaDonXuatDAO;
import com.example.myapplication.Model.DongVat;
import com.example.myapplication.Model.HoaDonNhap;
import com.example.myapplication.Model.HoaDonXuat;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DialogHoaDonXuat extends AppCompatActivity {
    //    Context context;
    EditText edtNgayXuat, edtMaHoaDonXuat, edtGiaXuat, edtSoLuong, edtGhiChu;
    Button btnThem,quaylai;
    //    List<HoaDonNhap> lstHoaDonNhap = new ArrayList<>();
    List<DongVat> lstDongVat = new ArrayList<>();
    HoaDonXuatDAO hoaDonXuatDAO;
    DongVatDAO dongVatDAO;
//    AdapterHoaDonNhap adapterHoaDonNhap;
    ListView listView;
    Spinner spinnerMaDongVat;
    String maDongVat = "";
    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_hoa_don_xuat_add);
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        spinnerMaDongVat = findViewById(R.id.spiner_maDongVat_hoaDonXuat_add);
        getMaDongVat();
        edtMaHoaDonXuat = findViewById(R.id.ed_nhap_maHoaDonXuat_add);
        edtGiaXuat = findViewById(R.id.ed_nhap_gia_nhap_hoaDonXuat_add);
        edtSoLuong = findViewById(R.id.ed_nhap_so_luong_hoaDonXuat_add);
        edtNgayXuat = findViewById(R.id.ed_nhap_ngay_nhap_hoa_don_xuat_add);
        edtGhiChu = findViewById(R.id.ed_nhap_ghi_chu_hoaDonXuat_add);
        btnThem = findViewById(R.id.btn_luu_hoa_don_xuat_add);
        quaylai = findViewById(R.id.btn_huy_hoa_don_xuat_add);
        listView = findViewById(R.id.lv_hoa_don_xuat);

        //su kien chon Spinner
        spinnerMaDongVat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                maDongVat = lstDongVat.get(spinnerMaDongVat.getSelectedItemPosition()).getmMaDongVat();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //su kien chon ngay
        edtNgayXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(DialogHoaDonXuat.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
                        String date = day+"/"+month+"/"+year;
                        edtNgayXuat.setText(date);

                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
        quaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mdx =edtMaHoaDonXuat.getText().toString();
                String gdx =edtGiaXuat.getText().toString();
                String sldx =edtSoLuong.getText().toString();
                String ngdx =edtNgayXuat.getText().toString();
                String gcdx =edtGhiChu.getText().toString();

                try {
                    if (TextUtils.isEmpty(mdx) || TextUtils.isEmpty(sldx) || TextUtils.isEmpty(gdx) || TextUtils.isEmpty(ngdx) ){
                        Toast.makeText(DialogHoaDonXuat.this, "Kh??ng ???????c ????? tr???ng", Toast.LENGTH_SHORT).show();
                    }else if(Integer.parseInt(sldx) < 1){
                        Toast.makeText(DialogHoaDonXuat.this, "S??? l?????ng ph???i l???n h??n 0", Toast.LENGTH_SHORT).show();

                    } else {
                        hoaDonXuatDAO = new HoaDonXuatDAO(DialogHoaDonXuat.this);//
                        HoaDonXuat hoaDonXuat = new HoaDonXuat(maDongVat, mdx, Double.parseDouble(gdx), Integer.parseInt(sldx), ngdx, gcdx);
                        if (hoaDonXuatDAO.insertHoaDonXuat(hoaDonXuat) > 0) {
                            ;
                            onBackPressed();
                            Toast.makeText(DialogHoaDonXuat.this, "Them Thanh Cong", Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (Exception ex) {
                    Log.e("L???i:", ex.toString());
                    Toast.makeText(DialogHoaDonXuat.this, "Kh??ng ????ng ?????nh d???ng", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    public void getMaDongVat() {
        dongVatDAO = new DongVatDAO(this);
        lstDongVat = DongVatDAO.getAllTheLoai();
        ArrayAdapter<DongVat> adapter = new ArrayAdapter<DongVat>(this, android.R.layout.simple_spinner_item, lstDongVat);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMaDongVat.setAdapter(adapter);
    }


}