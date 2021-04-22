package com.example.myapplication.Adapter;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.TinTuc;
import com.example.myapplication.R;

import java.util.List;

public class AdapterHome extends BaseAdapter {
    Context context;
    List<TinTuc> tinTucList;

    public AdapterHome(Context context, List<TinTuc> tinTucList){
        this.context = context;
        this.tinTucList = tinTucList;
    }


    @Override
    public int getCount() {
        return tinTucList.size();
    }

    @Override
    public Object getItem(int position) {
        return tinTucList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public static class ViewHoder{
        //TextView tv_link;
        TextView tv_title;
        //TextView tv_des;
        TextView tv_pubDate;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoder viewHoder;
        if(convertView==null){
            viewHoder = new ViewHoder();

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row_item_show_tin_tuc,null);

            //viewHoder.tv_link = convertView.findViewById(R.id.tv_id);
            viewHoder.tv_title = convertView.findViewById(R.id.tv_title);
            //viewHoder.tv_des = convertView.findViewById(R.id.tv_des);
            viewHoder.tv_pubDate = convertView.findViewById(R.id.tv_pubDate);
            convertView.setTag(viewHoder);
        }else{
            viewHoder = (ViewHoder) convertView.getTag();
        }
        //viewHoder.tv_link.setText("");
        viewHoder.tv_title.setText(tinTucList.get(position).getTitle());
        //viewHoder.tv_des.setText("");
        viewHoder.tv_pubDate.setText(tinTucList.get(position).getPubDate());

        return convertView;
    }


    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}
