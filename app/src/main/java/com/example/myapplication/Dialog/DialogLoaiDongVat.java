package com.example.myapplication.Dialog;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Adapter.AdapterLoaiDongVat;
import com.example.myapplication.DAO.LoaiDongVatDAO;
import com.example.myapplication.Model.LoaiDongVat;
import com.example.myapplication.R;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class DialogLoaiDongVat extends AppCompatActivity {

    private EditText edt_ldv;
    private Button btn_Add;
    private ListView lv;
    Context context;
    List<LoaiDongVat> loaiDongVatList;
    AdapterLoaiDongVat adapterLoaiDongVat;
    LoaiDongVatDAO loaiDongVatDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loai_dong_vat_add);
        edt_ldv = findViewById(R.id.ed_nhap_title_add_loai_dong_vat);
        btn_Add = findViewById(R.id.btn_luu_loai_dong_vat_add);
        lv = findViewById(R.id.lv_loai_dong_vat);
        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    loaiDongVatDAO = new LoaiDongVatDAO(DialogLoaiDongVat.this);
                    String _ldv = edt_ldv.getText().toString();

                if (TextUtils.isEmpty(_ldv) ){
                    Toast.makeText(DialogLoaiDongVat.this, "Không được để trống ", Toast.LENGTH_SHORT).show();
                }else {
                    try {
                        LoaiDongVat ldv = new LoaiDongVat(_ldv);
                        if (loaiDongVatDAO.insertLoaiDongVat(ldv) > 0) {
                            onBackPressed();
                            Toast.makeText(DialogLoaiDongVat.this, "Them Thanh Cong", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(DialogLoaiDongVat.this, "Them That Bai", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception ex) {
                        Log.e("Lỗi:", ex.toString());
                    }

                }
                }



        });
    }

    public void quayLaii(View view) {

        finish();
    }
}