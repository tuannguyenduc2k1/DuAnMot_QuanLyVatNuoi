package com.example.myapplication.Dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.DAO.DongVatDAO;
import com.example.myapplication.DAO.LoaiDongVatDAO;
import com.example.myapplication.Model.DongVat;
import com.example.myapplication.Model.LoaiDongVat;
import com.example.myapplication.R;
import java.util.ArrayList;
import java.util.List;


public class DialogDongVatUpdate extends AppCompatActivity {
    private List<LoaiDongVat> listdongVat = new ArrayList<>();
    private String mMadongvat = "";

    private EditText madongvat, soluong,ghichu;
    private Spinner spnloaidongvat;
    private Button huy,luu;
    private DongVatDAO dongVatDAO;
    String mldv,ldv,mdv,sl,gc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_dong_vat_update);
        unit();


        dongVatDAO = new DongVatDAO(this);
        Intent in = getIntent();
        Bundle b = in.getExtras();
        //ldv = b.getString("LOAIDONGVAT");
        mdv = b.getString("MADONGVAT");
        gc = b.getString("GHICHU");
        in.putExtras(b);
        madongvat.setText(mdv);
        ghichu.setText(gc);


        luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dongVatDAO.update(mdv,madongvat.getText().toString(),ghichu.getText().toString())>0){
                    Toast.makeText(getApplicationContext(), "Update thành công", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }else{
                    Toast.makeText(getApplicationContext(), "Update thất bại ", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    private void unit() {
        madongvat = findViewById(R.id.ed_nhap_maDongVat_dongVat__update);
        //soluong = findViewById(R.id.ed_nhap_so_luong_dongVat_update);
        ghichu = findViewById(R.id.ed_nhap_ghi_chu_dongVat_update);
        luu = findViewById(R.id.btn_luu_dong_vat_update);
    }

    public void huy_update(View view) {
        finish();
    }

}