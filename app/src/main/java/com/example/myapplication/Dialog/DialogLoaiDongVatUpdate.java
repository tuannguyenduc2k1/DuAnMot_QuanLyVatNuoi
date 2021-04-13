package com.example.myapplication.Dialog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.ActivityFragment.MainActivity;
import com.example.myapplication.DAO.LoaiDongVatDAO;
import com.example.myapplication.Model.LoaiDongVat;
import com.example.myapplication.PetFragment.DongVatFragment;
import com.example.myapplication.PetFragment.LoaiDongVatFragment;
import com.example.myapplication.R;

import java.util.List;

public class DialogLoaiDongVatUpdate extends AppCompatActivity {
    EditText edtLoaiDongVatUpdate;
    Button  btnLoaiDongVatUpdate;
    String ldv;
    LoaiDongVatDAO loaiDongVatDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loai_dong_vat_update);

        edtLoaiDongVatUpdate = findViewById(R.id.ed_nhap_title_update_loai_dong_vat);
        btnLoaiDongVatUpdate = findViewById(R.id.btn_luu_loai_dong_vat_update);

        loaiDongVatDAO = new LoaiDongVatDAO(this);


        Intent in = getIntent();
        Bundle c = in.getExtras();
        ldv = c.getString("LOAIDONGVAT");
        edtLoaiDongVatUpdate.setText(ldv);

        btnLoaiDongVatUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (loaiDongVatDAO.update(ldv, edtLoaiDongVatUpdate.getText().toString()) > 0) {
                    onBackPressed();
                    Toast.makeText(getApplicationContext(), "Lưu thành công", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
}