package com.example.myapplication.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.Database.DBHelper;
import com.example.myapplication.Model.DongVat;
import com.example.myapplication.Model.LoaiDongVat;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class DongVatDAO {
    public static final String TABLE_NAME = "DongVat";
    public static final String SQL_DONG_VAT = "CREATE TABLE DongVat(maDongVat text primary key , loaiDongVat text , soLuong int , ghiChu text )";
    public static final String TAG = "DongVatDAO";

    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public DongVatDAO(Context context){
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insertDongVat(DongVat dv){
        ContentValues values = new ContentValues();
        values.put(Name.maDongVat,dv.getmMaDongVat());
        values.put(Name.loaiDongVat,dv.getmLoaiDongVat());
        values.put(Name.soLuongDongVat,dv.getmSoLuongDongVat());
        values.put(Name.ghiChuDongVat,dv.getmGhiChu());

        return db.insert("DongVat",null,values);
    }
    //update
    public int update(DongVat dv){
        ContentValues values = new ContentValues();
        values.put(Name.maDongVat,dv.getmMaDongVat());
        values.put(Name.loaiDongVat,dv.getmLoaiDongVat());
        values.put(Name.soLuongDongVat,dv.getmSoLuongDongVat());
        values.put(Name.ghiChuDongVat,dv.getmGhiChu());
        return db.update("DongVat",values,"maDongVat=?",new String[]{String.valueOf(dv.getmMaDongVat())}) ;
    }
    //delete
    public int delete(DongVat dv){
        return db.delete("DongVat","maDongVat=?",new String[]{String.valueOf(dv.getmMaDongVat())});
    }
    //get all
    public List<DongVat> getAll() throws ParseException {
        String sql =" select * from DongVat ";
        return getData(sql);
    }

    private List<DongVat> getData(String sql,String... selectionArgs) throws ParseException{
        List<DongVat> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            DongVat dv = new DongVat();
            dv.setmMaDongVat(c.getString(c.getColumnIndex(Name.maDongVat)));
            dv.setmLoaiDongVat(c.getString(c.getColumnIndex(Name.loaiDongVat)));
            dv.setmSoLuongDongVat(c.getInt(c.getColumnIndex(Name.soLuongDongVat)));
            dv.setmGhiChu(c.getString(c.getColumnIndex(Name.ghiChuDongVat)));
            list.add(dv);
        }
        return list;
    }


    private static class Name{
        public static String maDongVat = "maDongVat";
        public static String loaiDongVat = "loaiDongVat";
        public static String soLuongDongVat = "soLuong";
        public static String ghiChuDongVat = "ghiChu";


    }
}
