package com.example.myapplication.Dialog;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.DAO.HoaDonNhapDAO;
import com.example.myapplication.DAO.HoaDonXuatDAO;
import com.example.myapplication.R;

import java.util.Calendar;


public class DialogHoaDonXuatUpdate extends AppCompatActivity {
    private String mMadongvat = "";
    private EditText edtMaHoaDonXuat, edtGiaXuat,edtSoLuongXuat,edtNgayXuat,edtGhiChu;
    private Button btnLuu;
    private HoaDonXuatDAO hoaDonXuatDAO;
    String mhdx,gx,slx,nx,gc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_hoa_don_xuat_update);
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        unit();

        Intent in = getIntent();
        Bundle bb = in.getExtras();
        mhdx = bb.getString("MAHOADONXUAT");
        gx = bb.getString("GIAXUAT");
        slx = bb.getString("SOLUONGXUAT");
        nx = bb.getString("NGAYXUAT");
        gc = bb.getString("GHICHUXUAT");
        edtMaHoaDonXuat.setText(mhdx);
        edtGiaXuat.setText(gx);
        edtSoLuongXuat.setText(slx);
        edtNgayXuat.setText(nx);
        edtGhiChu.setText(gc);
        hoaDonXuatDAO = new HoaDonXuatDAO(DialogHoaDonXuatUpdate.this);




        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hoaDonXuatDAO.update(mhdx,edtMaHoaDonXuat.getText().toString(),edtGiaXuat.getText().toString(),edtSoLuongXuat.getText().toString(),edtNgayXuat.getText().toString(),edtGhiChu.getText().toString())>0){
                    Toast.makeText(getApplicationContext(), "Update thành công", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }else{
                    Toast.makeText(getApplicationContext(), "Update thất bại ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        edtNgayXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(DialogHoaDonXuatUpdate.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
                        String date = day+"/"+month+"/"+year;
                        edtNgayXuat.setText(date);

                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

    }
    private void unit() {
        edtMaHoaDonXuat = findViewById(R.id.ed_nhap_maHoaDonXuat_update);
        edtGiaXuat = findViewById(R.id.ed_nhap_gia_nhap_hoaDonXuat_update);
        edtSoLuongXuat = findViewById(R.id.ed_nhap_so_luong_hoaDonXuat_update);
        edtNgayXuat = findViewById(R.id.ed_nhap_ngay_nhap_hoa_don_xuat_update);
        edtGhiChu = findViewById(R.id.ed_nhap_ghi_chu_hoaDonXuat_update);
        btnLuu = findViewById(R.id.btn_luu_hoa_don_xuat_update);
    }

}