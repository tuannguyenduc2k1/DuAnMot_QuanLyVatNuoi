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
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.DAO.HoaDonNhapDAO;
import com.example.myapplication.Model.DongVat;
import com.example.myapplication.Model.HoaDonNhap;
import com.example.myapplication.Model.HoaDonXuat;
import com.example.myapplication.Model.LoaiDongVat;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class AdapterHoaDonNhap extends BaseAdapter implements Filterable {
    public Activity context;
    public LayoutInflater inflater;

    List<HoaDonNhap> lstHoaDonNhap;
    List<HoaDonNhap> lstGetHoaDonNhap;

    HoaDonNhapDAO hoaDonNhapDAO;

    private Filter hoaDonnhapFilter;

    public AdapterHoaDonNhap(Activity context, List<HoaDonNhap> lstHoaDonNhap) {
        super();
        this.context = context;
        this.lstHoaDonNhap = lstHoaDonNhap;
        this.lstGetHoaDonNhap = lstHoaDonNhap;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

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
        if (converView == null) {
            hoder = new ViewHoder();
            converView = inflater.inflate(R.layout.row_item_hoa_don_nhap, null);
            hoder.txtMaHoaDonNhapVatNuoi = converView.findViewById(R.id.tv_ma_hoa_don_nhap_item);
            hoder.txtNgayNhap = converView.findViewById(R.id.tv_ngay_nhap_hoa_don_nhap_item);
            //hoder.txtGiaNhap = converView.findViewById(R.id.tv_gia_nhap_hoa_don_nhap_item);
            hoder.txtMaDongVat = converView.findViewById(R.id.tv_ma_dong_vat_nhap_item);
            hoder.txtTongGiaNhap = converView.findViewById(R.id.tv_tong_gia_hoa_don_nhap_item);
            hoder.imgDelete = converView.findViewById(R.id.img_delete_hoa_don_nhap_item);
            hoaDonNhapDAO = new HoaDonNhapDAO(context);
            HoaDonNhap hdn = lstHoaDonNhap.get(position);
            converView.setTag(hoder);
            hoder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    hoaDonNhapDAO.delete(lstHoaDonNhap.get(position).getmMaHoaDonNhap());
                    lstHoaDonNhap.remove(position);
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();
                }
            });

        } else
            hoder = (ViewHoder) converView.getTag();
        HoaDonNhap hdn = lstHoaDonNhap.get(position);
        converView.setTag(hoder);
        hoder.txtMaHoaDonNhapVatNuoi.setText("Mã Hóa Đơn Nhập : " + hdn.getmMaHoaDonNhap());
        hoder.txtMaDongVat.setText("" + hdn.getmMaDongVat());
        hoder.txtTongGiaNhap.setText("" + Double.toString(hdn.getmSoLuongNhap() * hdn.getmGiaNhap()) + "vnd");
        hoder.txtNgayNhap.setText("" + hdn.getmNgayNhap());
        //hoder.txtGiaNhap.setText(""+hdn.getmGiaNhap());

        return converView;
    }

    public static class ViewHoder {
        TextView txtMaHoaDonNhapVatNuoi;
        TextView txtNgayNhap;
        TextView txtMaDongVat;
        TextView txtTongGiaNhap;
        //TextView txtGiaNhap;
        ImageView imgDelete;
//        ImageView imgUpdate;
    }

    public Filter getFilter() {
        if (hoaDonnhapFilter == null)
            hoaDonnhapFilter = new CustomFilter();
        return hoaDonnhapFilter;
    }

    public void resetData() {
        lstHoaDonNhap =  lstGetHoaDonNhap  ;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void changeDataset(List<HoaDonNhap> items) {
        this.lstHoaDonNhap = items;
        notifyDataSetChanged();
    }

    private class CustomFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint == null || constraint.length() == 0) {
                results.values = lstGetHoaDonNhap;
                results.count = lstGetHoaDonNhap.size();
            } else {
                List<HoaDonNhap> lshoadonnhap = new ArrayList<HoaDonNhap>();
                for (HoaDonNhap p : lstHoaDonNhap) {
                    if
                    (p.getmMaHoaDonNhap().toUpperCase().startsWith(constraint.toString().toUpperCase()))
                        lshoadonnhap.add(p);
                }
                results.values = lshoadonnhap;
                results.count = lshoadonnhap.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.count == 0)
                notifyDataSetInvalidated();
            else {
                lstHoaDonNhap = (List<HoaDonNhap>) results.values;
                notifyDataSetChanged();
            }
        }
        }
    }

