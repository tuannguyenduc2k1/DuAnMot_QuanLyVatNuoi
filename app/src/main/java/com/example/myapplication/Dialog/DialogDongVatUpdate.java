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
        ldv = b.getString("LOAIDONGVAT");
        mdv = b.getString("MATHELOAI");
        sl = b.getString("SOLUONG");
        gc = b.getString("GHICHU");
        in.putExtras(b);
        startActivity(in);

        madongvat.setText(mdv);
        soluong.setText(sl);
        ghichu.setText(gc);

        luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (dongVatDAO.update(mldv, madongvat.getText().toString(),Integer.parseInt(soluong.getText().toString()),ghichu.getText().toString())  > 0) {
                        Toast.makeText(getApplicationContext(), "Lưu thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Lưu thất bại",
                                Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception ex) {
                    String s = soluong.getText().toString();
                    if (madongvat.getText().length() == 0 || ghichu.getText().length() == 0
                    ) {
                        Toast.makeText(getApplicationContext(), "Bạn chưa nhập đầy đủ thông tin ", Toast.LENGTH_SHORT).show();
                    }
                    try {
                        Integer.parseInt(s);
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Kiểm tra định dạng giá bán và số lượng ", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
    private void unit() {
        madongvat = findViewById(R.id.ed_nhap_maDongVat_dongVat__update);
        soluong = findViewById(R.id.ed_nhap_so_luong_dongVat_update);
        ghichu = findViewById(R.id.ed_nhap_ghi_chu_dongVat_update);
        huy = findViewById(R.id.btn_huy_dong_vat_add);
        luu = findViewById(R.id.btn_luu_dong_vat_update);
    }

}