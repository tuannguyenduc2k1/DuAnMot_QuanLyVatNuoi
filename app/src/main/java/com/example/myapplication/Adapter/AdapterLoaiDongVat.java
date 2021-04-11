package com.example.myapplication.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.DAO.LoaiDongVatDAO;
import com.example.myapplication.Model.LoaiDongVat;
import com.example.myapplication.R;

import java.util.List;

public class AdapterLoaiDongVat extends BaseAdapter  {
    public Activity context;
    public LayoutInflater inflater;
    List<LoaiDongVat> arrLoaiDongVat;
    LoaiDongVatDAO loaiDongVatDAO;


    public AdapterLoaiDongVat(Activity context, List<LoaiDongVat> arrLoaiDongVat) {
        this.context = context;
        this.arrLoaiDongVat = arrLoaiDongVat;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        loaiDongVatDAO = new LoaiDongVatDAO(context);
    }

    @Override
    public int getCount() {
        return arrLoaiDongVat.size();
    }

    @Override
    public Object getItem(int position) {
        return arrLoaiDongVat.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        ViewHoder hoder ;
        if(convertView == null){
            hoder = new ViewHoder();
            convertView = inflater.inflate(R.layout.row_item_loai_dong_vat,null);
            hoder.txtLoaiDongVat = convertView.findViewById(R.id.tv_name_row_item_loai_dong_vat);
            hoder.imgUpdate = convertView.findViewById(R.id.img_update_loai_dong_vat);
            hoder.imgDelete = convertView.findViewById(R.id.img_delete_loai_dong_vat);
            loaiDongVatDAO = new LoaiDongVatDAO(context);
            LoaiDongVat loaiDongVat = arrLoaiDongVat.get(position);
            convertView.setTag(hoder);
            hoder.txtLoaiDongVat.setText("Đây là con: "+loaiDongVat.getmLoaiDongVat());



            hoder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        loaiDongVatDAO.delete(arrLoaiDongVat.get(position).getmLoaiDongVat());
                        arrLoaiDongVat.remove(position);
                        Toast.makeText(context, "Xoa Thanh Cong", Toast.LENGTH_SHORT).show();
                    notifyDataSetInvalidated();

                }
            });
        }

        return convertView;
    }
    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }



    public void changeDataset(List<LoaiDongVat> ldv) {
        this.arrLoaiDongVat = ldv;
        notifyDataSetChanged();

    }
    public static class ViewHoder{
        TextView txtLoaiDongVat;
        ImageView imgDelete,imgUpdate;
        String ldv;

    }
}
