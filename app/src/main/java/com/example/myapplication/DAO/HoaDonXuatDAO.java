package com.example.myapplication.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.Database.DBHelper;
import com.example.myapplication.Model.DongVat;
import com.example.myapplication.Model.HoaDonXuat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class HoaDonXuatDAO {
    public static final String TABLE_NAME = "HoaDonXuat";
    public static final String SQL_HOA_DON_XUAT = "CREATE TABLE HoaDonXuat(maHoaDonXuatVatNuoi text primary key , maDongVat text  , giaXuat double , soLuongXuat int , ngayXuat date , ghiChu text )";
    public static final String TAG = "HoaDonXuatDAO";
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public HoaDonXuatDAO(Context context) {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public long insertHoaDonXuat(HoaDonXuat hdx){
        ContentValues values = new ContentValues();
        values.put(Name.maDongVat,hdx.getmMaDongVat());
        values.put(Name.maHoaDonXuat,hdx.getmMaHoaDonXuat());
        values.put(Name.giaXuat,hdx.getmGiaXuat());
        values.put(Name.soLuongXuat,hdx.getmSoLuongXuat());
        values.put(Name.ngayXuat,sdf.format(hdx.getmNgayXuat()));
        values.put(Name.ghiChu,hdx.getmGhiChuXuat());

        return db.insert("HoaDonXuat",null,values);
    }
    //update
    public int update(HoaDonXuat hdx){
        ContentValues values = new ContentValues();
        values.put(Name.maDongVat,hdx.getmMaDongVat());
        values.put(Name.maHoaDonXuat,hdx.getmMaHoaDonXuat());
        values.put(Name.giaXuat,hdx.getmGiaXuat());
        values.put(Name.soLuongXuat,hdx.getmSoLuongXuat());
        values.put(Name.ngayXuat,sdf.format(hdx.getmNgayXuat()));
        values.put(Name.ghiChu,hdx.getmGhiChuXuat());

        return db.update("HoaDonXuat",values,"maHoaDonXuat=?",new String[]{String.valueOf(hdx.getmMaHoaDonXuat())}) ;
    }

    public int delete(String id) {
        return db.delete("HoaDonXuat", "maHoaDonXuat=?", new String[]{id});
    }

    public List<HoaDonXuat> getAll() throws ParseException {
        String sql = "SELECT * FROM HoaDonXuat";
        return getData(sql);
    }
    private List<HoaDonXuat> getData(String sql, String... selectionArgs) throws ParseException {
        List<HoaDonXuat> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            HoaDonXuat obj = new HoaDonXuat();
            obj.setmMaDongVat(c.getString(c.getColumnIndex(Name.maDongVat)));
            obj.setmMaHoaDonXuat(c.getString(c.getColumnIndex(Name.maHoaDonXuat)));
            obj.setmGiaXuat(c.getDouble(c.getColumnIndex(Name.giaXuat)));
            obj.setmSoLuongXuat(c.getInt(c.getColumnIndex(Name.soLuongXuat)));
            obj.setmNgayXuat(sdf.parse(c.getString(c.getColumnIndex(Name.ngayXuat))));
            obj.setmGhiChuXuat(c.getString(c.getColumnIndex(Name.ghiChu)));
            list.add(obj);
        }
        return list;
    }

    private static class Name {
        public static String maDongVat = "maDongVat";
        public static String maHoaDonXuat = "maHoaDonXuat";
        public static String giaXuat = "giaXuat";
        public static String soLuongXuat = "soLuongXuat";
        public static String ngayXuat = "ngayXuat";
        public static String ghiChu = "ghiChu";

    }
}
