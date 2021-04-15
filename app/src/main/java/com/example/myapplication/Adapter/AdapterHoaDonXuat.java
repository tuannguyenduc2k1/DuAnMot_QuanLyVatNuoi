package com.example.myapplication.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.DAO.HoaDonNhapDAO;
import com.example.myapplication.DAO.HoaDonXuatDAO;
import com.example.myapplication.Model.HoaDonNhap;
import com.example.myapplication.Model.HoaDonXuat;
import com.example.myapplication.R;

import java.util.List;

public class AdapterHoaDonXuat extends BaseAdapter {
    public Activity context;
    public LayoutInflater inflater;
    List<HoaDonXuat> lstHoaDonXuat;
    List<HoaDonXuat> lstGetHoaDonXuat;
    HoaDonXuatDAO hoaDonXuatDAO;

    public AdapterHoaDonXuat(Activity context, List<HoaDonXuat> lstHoaDonXuat) {
        super();
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.lstHoaDonXuat = lstHoaDonXuat;
        this.lstGetHoaDonXuat = lstHoaDonXuat;
        hoaDonXuatDAO = new HoaDonXuatDAO(context);
    }

    @Override
    public int getCount() {
        return lstHoaDonXuat.size();
    }

    @Override
    public Object getItem(int position) {
        return lstHoaDonXuat.get(position);
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
            converView = inflater.inflate(R.layout.row_item_hoa_don_xuat,null);
            hoder.txtMaHoaDonXuatVatNuoi = converView.findViewById(R.id.tv_ma_hoa_don_xuat_item);
            hoder.txtNgayXuat = converView.findViewById(R.id.tv_ngay_xuat_hoa_don_xuat_item);
            hoder.txtGiaXuat = converView.findViewById(R.id.tv_gia_xuat_hoa_don_xuat_item);
            hoder.imgDelete = converView.findViewById(R.id.img_delete_hoa_don_nhap_item);
            hoaDonXuatDAO = new HoaDonXuatDAO(context);
            HoaDonXuat hdx = lstHoaDonXuat.get(position);
            converView.setTag(hoder);
            hoder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    hoaDonXuatDAO.delete(lstHoaDonXuat.get(position).getmMaHoaDonXuat());
                    lstHoaDonXuat.remove(position);
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();
                }
            });

        }else
            hoder = (ViewHoder) converView.getTag();
            HoaDonXuat hdx = lstHoaDonXuat.get(position);
            converView.setTag(hoder);
            hoder.txtMaHoaDonXuatVatNuoi.setText(""+hdx.getmMaHoaDonXuat());
            hoder.txtNgayXuat.setText(""+hdx.getmNgayXuat());
            hoder.txtGiaXuat.setText(""+hdx.getmGiaXuat());
            
        return converView;
    }
    public static class ViewHoder{
        TextView txtMaHoaDonXuatVatNuoi;
        TextView txtNgayXuat;
        TextView txtGiaXuat;
        ImageView imgDelete;

    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void changeDataset(List<HoaDonXuat> items){
        this.lstHoaDonXuat = items;
        notifyDataSetChanged();
    }
}
