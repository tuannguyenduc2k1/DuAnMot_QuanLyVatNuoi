package com.example.myapplication.Dialog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Adapter.AdapterLoaiDongVat;
import com.example.myapplication.DAO.LoaiDongVatDAO;
import com.example.myapplication.Model.LoaiDongVat;
import com.example.myapplication.PetFragment.DongVatFragment;
import com.example.myapplication.PetFragment.LoaiDongVatFragment;
import com.example.myapplication.R;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class DialogLoaiDongVatUpdate extends AppCompatActivity {
    EditText edtLoaiDongVatUpdate;
    Button  btnLoaiDongVatUpdate;
    ListView listView;
    String ldv;
    LoaiDongVatDAO loaiDongVatDAO;
    AdapterLoaiDongVat adapterLoaiDongVat;
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
        loaiDongVatDAO = new LoaiDongVatDAO(DialogLoaiDongVatUpdate.this);

    }

    public void quayLai(View view) {
        finish();
    }

    public void updateDongVat(View view)  {

        String ud_ldv = edtLoaiDongVatUpdate.getText().toString();


        if (TextUtils.isEmpty(ud_ldv)){
            Toast.makeText(getApplicationContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
        }
        else if (loaiDongVatDAO.updateInforLoaiDongVat(ldv,ud_ldv)>0){
            Toast.makeText(getApplicationContext(), "Update thành công", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(getApplicationContext(), "Update thất bại ", Toast.LENGTH_SHORT).show();
        }
    }

}