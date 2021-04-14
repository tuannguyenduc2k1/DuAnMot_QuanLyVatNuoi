package com.example.myapplication.PetFragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.myapplication.Adapter.AdapterDongVat;
import com.example.myapplication.Adapter.AdapterLoaiDongVat;
import com.example.myapplication.DAO.DongVatDAO;
import com.example.myapplication.DAO.LoaiDongVatDAO;
import com.example.myapplication.Dialog.DialogDongVat;
import com.example.myapplication.Dialog.DialogDongVatUpdate;
import com.example.myapplication.Dialog.DialogLoaiDongVat;
import com.example.myapplication.Dialog.DialogLoaiDongVatUpdate;
import com.example.myapplication.Model.DongVat;
import com.example.myapplication.Model.LoaiDongVat;
import com.example.myapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class DongVatFragment extends Fragment {
    private View mView;
    private FloatingActionButton btnFloatingActionButton;
    public static List<DongVat> lsDongvat = new ArrayList<>();
    private ListView listView;
    DongVatDAO dongVatDAO;
    AdapterDongVat adapterDongVat ;

    public DongVatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView =  inflater.inflate(R.layout.fragment_dong_vat, container, false);
        listView = (ListView)mView.findViewById(R.id.lv_dong_vat);
        registerForContextMenu(listView);
        btnFloatingActionButton = (FloatingActionButton)mView.findViewById(R.id.btn_action_float_add_dong_vat);
        dongVatDAO = new DongVatDAO(getActivity());
        lsDongvat = dongVatDAO.getAllTheLoai();
        adapterDongVat = new AdapterDongVat(getActivity(),lsDongvat);
        listView.setAdapter(adapterDongVat);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(),DialogDongVatUpdate.class);
                Bundle b = new Bundle();
                b.putString("LOAIDONGVAT",lsDongvat.get(position).getmLoaiDongVat());
                b.putString("MADONGVAT",lsDongvat.get(position).getmMaDongVat());
                b.putString("SOLUONG", String.valueOf(lsDongvat.get(position).getmSoLuongDongVat()));
                b.putString("GHICHU", lsDongvat.get(position).getmGhiChu());
                intent.putExtras(b);
                startActivity(intent);
            }
        });


        btnFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),DialogDongVat.class));
            }
        });

        return mView;
    }
        @Override
    public void onResume() {
        super.onResume();
        lsDongvat.clear();
        lsDongvat = dongVatDAO.getAllTheLoai();
        adapterDongVat.changeDataset(lsDongvat);
    }
}