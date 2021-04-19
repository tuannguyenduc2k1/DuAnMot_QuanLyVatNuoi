package com.example.myapplication.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.DAO.DongVatDAO;
import com.example.myapplication.DAO.LoaiDongVatDAO;
import com.example.myapplication.Dialog.DialogDongVatUpdate;
import com.example.myapplication.Dialog.DialogLoaiDongVatUpdate;
import com.example.myapplication.Model.DongVat;
import com.example.myapplication.Model.LoaiDongVat;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterDongVat extends  BaseAdapter {
    public Activity context;
    public LayoutInflater inflater;
    List<DongVat> getSortSach;
    List<DongVat> getGetSortSach;
    DongVatDAO dongVatDAO;
    private Filter sachFilter;


    public AdapterDongVat (Activity context, List<DongVat> arraySach) {
        super();
        this.context = context;
        this.getSortSach = arraySach;
        this.getGetSortSach = arraySach;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        dongVatDAO = new DongVatDAO(context);
    }

    @Override
    public int getCount() {
        return getSortSach.size();
    }

    @Override
    public Object getItem(int position) {
        return getSortSach.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHoder hoder ;
        if(convertView == null){
            hoder = new ViewHoder();
            convertView = inflater.inflate(R.layout.row_item_dong_vat,null);
            hoder.DongVat = convertView.findViewById(R.id.dv_loaidongvat);
            hoder.maDongVat = convertView.findViewById(R.id.dv_madongvat);
            //hoder.soLuong = convertView.findViewById(R.id.dv_soluong);
            hoder.ghiChu = convertView.findViewById(R.id.dv_ghichu);
            hoder.imgDelete = convertView.findViewById(R.id.dv_xoa);

            hoder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dongVatDAO.delete(getSortSach.get(position).getmMaDongVat());
                    getSortSach.remove(position);
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();
                }
            });
            convertView.setTag(hoder);

        } else

            hoder = (ViewHoder) convertView.getTag();
            DongVat dongVat = getSortSach.get(position);
            convertView.setTag(hoder);
            hoder.maDongVat.setText("Con : "+dongVat.getmLoaiDongVat());
            hoder.DongVat.setText(dongVat.getmMaDongVat());
            //hoder.soLuong.setText(""+dongVat.getmSoLuongDongVat());
            hoder.ghiChu.setText(dongVat.getmGhiChu());
            return convertView;

    }
    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }



    public void changeDataset(List<DongVat> ldv) {
        this.getSortSach = ldv;
        notifyDataSetChanged();

    }
    public Filter getFilter() {
        if (sachFilter == null)
            sachFilter = new CustomFilter();
        return sachFilter;
    }
    public void resetData() {
        getSortSach = getGetSortSach;
    }
    public static class ViewHoder{
        TextView DongVat;
        TextView maDongVat;
         //TextView soLuong;
        TextView ghiChu;
        ImageView imgDelete;
        ImageView update;
        String ldv;
    }
    private class CustomFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            // We implement here the filter logic
            if (constraint == null || constraint.length() == 0) {
                results.values = getSortSach;
                results.count = getSortSach.size();
            } else {
                List<DongVat> lsSach = new ArrayList<DongVat>();
                for (DongVat p : getSortSach) {
                    if (p.getmMaDongVat().toUpperCase().startsWith(constraint.toString().toUpperCase()))
                        lsSach.add(p);
                }
                results.values = lsSach;
                results.count = lsSach.size();
            }
            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.count == 0)
                notifyDataSetInvalidated();
            else {
                getSortSach = (List<DongVat>) results.values;
                notifyDataSetChanged();
            }

        }
        }
    }
