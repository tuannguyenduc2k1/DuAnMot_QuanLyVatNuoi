package com.example.myapplication.Dialog;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Adapter.AdapterDongVat;
import com.example.myapplication.Adapter.AdapterLoaiDongVat;
import com.example.myapplication.DAO.DongVatDAO;
import com.example.myapplication.DAO.LoaiDongVatDAO;
import com.example.myapplication.Model.DongVat;
import com.example.myapplication.Model.LoaiDongVat;
import com.example.myapplication.PetFragment.LoaiDongVatFragment;
import com.example.myapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class DialogDongVat extends AppCompatActivity {
    private List<LoaiDongVat> listdongVat = new ArrayList<>();
    private String mMadongvat = "";
    private EditText madongvat, soluong,ghichu;
    private Spinner spnloaidongvat;
    private Button huy,luu;
    private  LoaiDongVatDAO loaiDongVatDAO;
    private DongVatDAO dongVatDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_dong_vat_add);

        unit();
        getTheLoai();
        unitUI();
        
    }

    private void unitUI() {
        spnloaidongvat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mMadongvat = listdongVat.get(spnloaidongvat.getSelectedItemPosition()).getmLoaiDongVat();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                 }
            });
            Intent in = getIntent();//load data into form
            Bundle b = in.getExtras();
            if (b != null) {
                madongvat.setText(b.getString("MADONGVAT"));
                soluong.setText(b.getString("SOLUONG"));
                ghichu.setText(b.getString("GHICHU"));
                String loaidongvat = b.getString("LOAIDONGVAT");
                spnloaidongvat.setSelection(checkPositionTheLoai(loaidongvat));
        }

        luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                dongVatDAO = new DongVatDAO(DialogDongVat.this);
                DongVat dongVat = new DongVat(mMadongvat,madongvat.getText().toString(),Integer.parseInt(soluong.getText().toString()),ghichu.getText().toString());
                if (dongVatDAO.insertDongVat(dongVat) > 0) {
                    onBackPressed();
                    Toast.makeText(getApplicationContext(), "Thêm thành công",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Thêm thất bại",
                            Toast.LENGTH_SHORT).show();
                }
                } catch (Exception ex) {
                    String s = soluong.getText().toString();
                    if (madongvat.getText().length() == 0 || ghichu.getText().length() == 0) {
                        Toast.makeText(getApplicationContext(), "Bạn chưa nhập đầy đủ thông tin ", Toast.LENGTH_SHORT).show();
                    }
                    try {
                        Integer.parseInt(s);
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Kiểm tra định dạng số lượng ", Toast.LENGTH_SHORT).show();
                    }
                }

            }

    });
    }

    private void unit() {
        madongvat = findViewById(R.id.ed_nhap_maDongVat_dongVat__add);
        soluong = findViewById(R.id.ed_nhap_so_luong_dongVat_add);
        ghichu = findViewById(R.id.ed_nhap_ghi_chu_dongVat_add);
        spnloaidongvat = findViewById(R.id.spiner_loaiDongVat_dongVat_add);
        huy = findViewById(R.id.btn_huy_dong_vat_add);
        luu = findViewById(R.id.btn_luu_dong_vat_add);
    }

    public void LuuDongVat_add(View view) {
    }
    public void getTheLoai() {

        loaiDongVatDAO = new LoaiDongVatDAO(DialogDongVat.this);
        listdongVat = loaiDongVatDAO.getAllTheLoai();
        ArrayAdapter<LoaiDongVat> dataAdapter = new ArrayAdapter<LoaiDongVat>(this,
                android.R.layout.simple_spinner_item, listdongVat);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnloaidongvat.setAdapter(dataAdapter);

    }
    public int checkPositionTheLoai(String loaiDongVat) {
        for (int i = 0; i < listdongVat.size(); i++) {
            if (loaiDongVat.equals(listdongVat.get(i).getmLoaiDongVat())) {
                return i;
            }
        }
        return 0;
    }

    public void showBook(View view) {
        finish();
    }

    public void cancel(View view) {
        finish();
    }
}