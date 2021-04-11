package com.example.myapplication.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.myapplication.Database.DBHelper;
import com.example.myapplication.Model.DongVat;

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
        if (checkPrimaryKey(dv.getmMaDongVat())) {
            int result = db.update(TABLE_NAME, values, "DongVat=?", new
                    String[]{dv.getmMaDongVat()});
            if (result == 0) {
                return -1;
            }
        } else {
            try {
                if (db.insert(TABLE_NAME, null, values) == -1) {
                    return -1;
                }
            } catch (Exception ex) {
                Log.e(TAG, ex.toString());
            }
        }
        return 1;
    }
    //update
    public int update(String maSach, String a, String b , int f, String d) {
        ContentValues values = new ContentValues();
        values.put("maDongVat", a);
        values.put("loaiDongVat", b);
        values.put("soLuong", f);
        values.put("ghiChu", d);

        int result = db.update(TABLE_NAME, values, "maSach=?", new
                String[]{maSach});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    //delete
    public int delete(DongVat dv){
        return db.delete("maDongVat","maDongVat=?",new String[]{String.valueOf(dv.getmMaDongVat())});
    }
    //get all
    public List<DongVat> getAll() throws ParseException {
        String sql =" select * from DongVat ";
        return getData(sql);
    }
    // check
    public boolean checkPrimaryKey(String strPrimaryKey) {
        //SELECT
        String[] columns = {"maDongVat"};
        //WHERE clause
        String selection = "maDongVat=?";
        //WHERE clause arguments
        String[] selectionArgs = {strPrimaryKey};
        Cursor c = null;
        try {
            c = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null,
                    null);
            c.moveToFirst();
            int i = c.getCount();
            c.close();
            return i > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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

    public List<DongVat> getAllTheLoai() {
        List<DongVat> lsdongvay = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            DongVat dv = new DongVat();
            dv.setmLoaiDongVat(c.getString(0));
            dv.setmMaDongVat(c.getString(1));
            dv.setmSoLuongDongVat(c.getInt(2));
            dv.setmGhiChu(c.getString(3));
            lsdongvay.add(dv);
            Log.d("//=====", dv.toString());
            c.moveToNext();
        }
        c.close();
        return lsdongvay;
    }

    private static class Name{
        public static String maDongVat = "maDongVat";
        public static String loaiDongVat = "loaiDongVat";
        public static String soLuongDongVat = "soLuong";
        public static String ghiChuDongVat = "ghiChu";


    }
}
