package com.example.myapplication.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.DAO.HoaDonNhapDAO;
import com.example.myapplication.Model.HoaDonNhap;
import com.example.myapplication.Model.LoaiDongVat;
import com.example.myapplication.R;

import java.util.List;
import java.util.zip.Inflater;

public class AdapterHoaDonNhap extends BaseAdapter {
    public Activity context;
    public LayoutInflater inflater;
    List<HoaDonNhap> lstHoaDonNhap;
    HoaDonNhapDAO hoaDonNhapDAO;

    public AdapterHoaDonNhap(Activity context,  List<HoaDonNhap> lstHoaDonNhap) {
        super();
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.lstHoaDonNhap = lstHoaDonNhap;
        hoaDonNhapDAO = new HoaDonNhapDAO(context);
    }

    @Override
    public int getCount() {
        return lstHoaDonNhap.size();
    }

    @Override
    public Object getItem(int position) {
        return lstHoaDonNhap.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View converView, ViewGroup viewGroup) {
        ViewHoder hoder;
        if (converView == null){
            hoder = new ViewHoder();
            converView = inflater.inflate(R.layout.row_item_hoa_don_nhap,null);
            hoder.txtMaHoaDonNhapVatNuoi = converView.findViewById(R.id.tv_ma_hoa_don_nhap_item);
            hoder.txtNgayNhap = converView.findViewById(R.id.tv_ngay_nhap_hoa_don_nhap_item);
            hoder.txtGiaNhap = converView.findViewById(R.id.tv_gia_nhap_hoa_don_nhap_item);
            hoder.imgDelete = converView.findViewById(R.id.img_delete_hoa_don_nhap_item);
            hoaDonNhapDAO = new HoaDonNhapDAO(context);
            HoaDonNhap hdn = lstHoaDonNhap.get(position);
            converView.setTag(hoder);
            hoder.txtMaHoaDonNhapVatNuoi.setText(""+hdn.getmMaHoaDonNhap());
            hoder.txtNgayNhap.setText(""+hdn.getmNgayNhap());
            hoder.txtGiaNhap.setText(""+hdn.getmGiaNhap());

            hoder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    hoaDonNhapDAO.delete(lstHoaDonNhap.get(position).getmMaHoaDonNhap());
                    lstHoaDonNhap.remove(position);
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();
                }
            });

        }

        return converView;
    }
    public static class ViewHoder{
        TextView txtMaHoaDonNhapVatNuoi;
        TextView txtNgayNhap;
        TextView txtGiaNhap;
        ImageView imgDelete;
//        ImageView imgUpdate;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void changeDataset(List<HoaDonNhap> items){
        this.lstHoaDonNhap = items;
        notifyDataSetChanged();
    }
}
