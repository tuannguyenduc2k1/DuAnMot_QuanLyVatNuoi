package com.example.myapplication.Dialog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.DAO.LoaiDongVatDAO;
import com.example.myapplication.Model.LoaiDongVat;
import com.example.myapplication.R;

import java.util.List;

public class DialogLoaiDongVatUpdate extends AppCompatActivity {
    EditText edtLoaiDongVatUpdate;
    Button  btnLoaiDongVatUpdate;
    ListView listView;
    String ldv;
    LoaiDongVatDAO loaiDongVatDAO;
    List<LoaiDongVat> loaiDongVatList;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loai_dong_vat_update);
        edtLoaiDongVatUpdate = findViewById(R.id.ed_nhap_title_update_loai_dong_vat);
        btnLoaiDongVatUpdate = findViewById(R.id.btn_luu_loai_dong_vat_update);
        listView = findViewById(R.id.lv_loai_dong_vat);

        Intent intent = getIntent();
        Bundle c = intent.getExtras();
        ldv = c.getString("LOAIDONGVAT");
        edtLoaiDongVatUpdate.setText(ldv);
        loaiDongVatDAO = new LoaiDongVatDAO(this);

        btnLoaiDongVatUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ldv = edtLoaiDongVatUpdate.getText().toString();
                LoaiDongVat loaiDongVat = new LoaiDongVat(ldv);
                int index = (int) listView.getSelectedItemId();

            }
        });
    }
}