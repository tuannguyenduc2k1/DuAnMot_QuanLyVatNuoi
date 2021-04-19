package com.example.myapplication.Dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.myapplication.Adapter.AdapterHoaDonNhap;
import com.example.myapplication.DAO.DongVatDAO;
import com.example.myapplication.DAO.HoaDonNhapDAO;
import com.example.myapplication.HoaDonFragment.HoaDonNhapFragment;
import com.example.myapplication.Model.DongVat;
import com.example.myapplication.Model.HoaDonNhap;
import com.example.myapplication.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static java.lang.Double.parseDouble;

public class DialogHoaDonNhap extends AppCompatActivity {
//    Context context;
    EditText edtNgayNhap,edtMaHoaDonNhap,edtGiaNhap,edtSoLuong,edtGhiChu;
//    List<HoaDonNhap> lstHoaDonNhap = new ArrayList<>();
    List<DongVat> lstDongVat = new ArrayList<>();
    HoaDonNhapDAO hoaDonNhapDAO;
    DongVatDAO dongVatDAO;
//    AdapterHoaDonNhap adapterHoaDonNhap;
    ListView listView;
    Spinner spinnerMaDongVat;
    String maDongVat = "";
    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_hoa_don_nhap_add);
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        spinnerMaDongVat = findViewById(R.id.spiner_maDongVat_hoaDonNhap_add);
        getMaDongVat();
        edtMaHoaDonNhap = findViewById(R.id.ed_nhap_maHoaDonNhap_add);
        edtGiaNhap = findViewById(R.id.ed_nhap_gia_nhap_hoaDonNhap_add);
        edtSoLuong = findViewById(R.id.ed_nhap_so_luong_hoaDonNhap_add);
        edtNgayNhap = findViewById(R.id.ed_nhap_ngay_nhap_hoa_don_nhap_add);
        edtGhiChu = findViewById(R.id.ed_nhap_ghi_chu_hoaDonNhap_add);
        listView = findViewById(R.id.lv_hoa_don_nhap);



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

        edtNgayNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(DialogHoaDonNhap.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
                        String date = day+"/"+month+"/"+year;
                        edtNgayNhap.setText(date);

                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
    }

    public void getMaDongVat(){
        dongVatDAO = new DongVatDAO(this);
        lstDongVat = DongVatDAO.getAllTheLoai();

        ArrayAdapter<DongVat> adapter = new ArrayAdapter<DongVat>(this, android.R.layout.simple_spinner_item,lstDongVat);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMaDongVat.setAdapter(adapter);
    }


    public void btnLuuHoaDonNhap(View view) {
            hoaDonNhapDAO = new HoaDonNhapDAO(DialogHoaDonNhap.this);
            String _mahoadon = edtMaHoaDonNhap.getText().toString();
            String gianhap = edtGiaNhap.getText().toString();
            String soluong =  edtSoLuong.getText().toString();
            String ngaynhap= edtNgayNhap.getText().toString();
            String ghichu = edtGhiChu.getText().toString();
            try {
                if (TextUtils.isEmpty(_mahoadon) || TextUtils.isEmpty(gianhap)|| TextUtils.isEmpty(soluong) || TextUtils.isEmpty(ngaynhap)  ){
                    Toast.makeText(DialogHoaDonNhap.this, "Không được để trống ", Toast.LENGTH_SHORT).show();
                }else if (Integer.parseInt(soluong) < 1){
                    Toast.makeText(DialogHoaDonNhap.this, "Số lượng phải lơn hơn 0 ", Toast.LENGTH_SHORT).show();
                }
                else {
                    try{
                        HoaDonNhap hoaDonNhap = new HoaDonNhap(maDongVat,_mahoadon,Double.parseDouble(gianhap),Integer.parseInt(soluong),ngaynhap ,ghichu);
                        if(hoaDonNhapDAO.insertHoaDonNhap(hoaDonNhap) > 0 ){
                            onBackPressed();
                            Toast.makeText(DialogHoaDonNhap.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(DialogHoaDonNhap.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception ex){
                        Log.e("Lỗi:", ex.toString());
                        Toast.makeText(DialogHoaDonNhap.this, "Không đúng định dạng", Toast.LENGTH_SHORT).show();

                    }
                }
            }catch (Exception e){

            }




    }

    public void quayLai_HDN(View view) {
        finish();
    }
}
