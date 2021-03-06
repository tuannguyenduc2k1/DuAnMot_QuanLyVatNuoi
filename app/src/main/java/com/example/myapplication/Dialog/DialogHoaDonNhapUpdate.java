package com.example.myapplication.Dialog;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.DAO.DongVatDAO;
import com.example.myapplication.DAO.HoaDonNhapDAO;
import com.example.myapplication.DAO.LoaiDongVatDAO;
import com.example.myapplication.Model.LoaiDongVat;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static java.lang.Double.parseDouble;


public class DialogHoaDonNhapUpdate extends AppCompatActivity {
    private String mMadongvat = "";
    private EditText edtMaHoaDonNhap, edtGiaNhap,edtSoLuongNhap,edtNgayNhap,edtGhiChu;
    private Button btnLuu;
    private HoaDonNhapDAO hoaDonNhapDAO;
    String mhdn,gn,sln,nn,gc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_hoa_don_nhap_update);
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        unit();

        Intent in = getIntent();
        Bundle b = in.getExtras();
        mhdn = b.getString("m");
        gn = b.getString("GIANHAP");
        sln = b.getString("SOLUONGNHAP");
        nn = b.getString("NGAYNHAP");
        gc = b.getString("GHICHU");
        edtMaHoaDonNhap.setText(mhdn);
        edtGiaNhap.setText(gn);
        edtSoLuongNhap.setText(sln);
        edtNgayNhap.setText(nn);
        edtGhiChu.setText(gc);
        hoaDonNhapDAO = new HoaDonNhapDAO(DialogHoaDonNhapUpdate.this);




        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mhd =edtMaHoaDonNhap.getText().toString();
                String gnhap = edtGiaNhap.getText().toString();
                String sluong = edtSoLuongNhap.getText().toString();
                String nnhap =edtNgayNhap.getText().toString();
                String ghichu =edtGhiChu.getText().toString();

            try {
                if (TextUtils.isEmpty(mhd) || TextUtils.isEmpty(gnhap)|| TextUtils.isEmpty(sluong) || TextUtils.isEmpty(nnhap) || TextUtils.isEmpty(nnhap) ) {
                    Toast.makeText(DialogHoaDonNhapUpdate.this, "Kh??ng ???????c ????? tr???ng ", Toast.LENGTH_SHORT).show();
                }else if (Integer.parseInt(sluong)  < 1){
                    Toast.makeText(DialogHoaDonNhapUpdate.this, "S??? l?????ng ph???i l???n h??n 0", Toast.LENGTH_SHORT).show();
                }else {
                    hoaDonNhapDAO.update(mhdn,mhd,gnhap,sluong,nnhap,ghichu);
                    Toast.makeText(getApplicationContext(), "Update th??nh c??ng", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }
            }catch (Exception e){
                Toast.makeText(DialogHoaDonNhapUpdate.this, "Kh??ng ????ng ?????nh d???ng", Toast.LENGTH_SHORT).show();

            }

            }
        });
        edtNgayNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(DialogHoaDonNhapUpdate.this, new DatePickerDialog.OnDateSetListener() {
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
    private void unit() {
        edtMaHoaDonNhap = findViewById(R.id.ed_nhap_maHoaDonNhap_update);
        edtGiaNhap = findViewById(R.id.ed_nhap_gia_nhap_hoaDonNhap_update);
        edtSoLuongNhap = findViewById(R.id.ed_nhap_so_luong_hoaDonNhap_update);
        edtNgayNhap = findViewById(R.id.ed_nhap_ngay_nhap_hoa_don_nhap_update);
        edtGhiChu = findViewById(R.id.ed_nhap_ghi_chu_hoaDonNhap_update);
        btnLuu = findViewById(R.id.btn_luu_hoa_don_nhap_update);
    }

    public void quayLai_HDN_update(View view) {
        finish();
    }
}