package com.example.myapplication.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.Database.DBHelper;
import com.example.myapplication.Model.HoaDonNhap;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class HoaDonNhapDAO {
    public static final String TABLE_NAME = "HoaDonNhap";
    public static final String SQL_HOA_DON_NHAP = "CREATE TABLE HoaDonNhap(maHoaDonNhap text primary key , maDongVat text , giaNhap double , soLuongNhap int , ngayNhap date , ghiChu date )";
    public static final String TAG = "HoaDonNhapDAO";


    private static SQLiteDatabase db;
    private DBHelper dbHelper;


    public HoaDonNhapDAO(Context context) {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

public long insertHoaDonNhap(HoaDonNhap hdn){
    ContentValues values = new ContentValues();
    values.put(Name.maDongVat,hdn.getmMaDongVat());
    values.put(Name.maHoaDonNhap,hdn.getmMaHoaDonNhap());
    values.put(Name.giaNhap,hdn.getmGiaNhap());
    values.put(Name.soLuongNhap,hdn.getmSoLuongNhap());
    values.put(Name.ngayNhap,hdn.getmNgayNhap());
    values.put(Name.ghiChu,hdn.getmGhiChuNhap());

    return db.insert("HoaDonNhap",null,values);
}
    //update
    public int update(HoaDonNhap hdn){
        ContentValues values = new ContentValues();
        values.put(Name.maDongVat,hdn.getmMaDongVat());
        values.put(Name.maHoaDonNhap,hdn.getmMaHoaDonNhap());
        values.put(Name.giaNhap,hdn.getmGiaNhap());
        values.put(Name.soLuongNhap,hdn.getmSoLuongNhap());
        values.put(Name.ngayNhap,hdn.getmNgayNhap());
        values.put(Name.ghiChu,hdn.getmGhiChuNhap());

        return db.update("HoaDonNhap",values,"maHoaDonNhap=?",new String[]{String.valueOf(hdn.getmMaHoaDonNhap())}) ;
    }

    public long delete(String id) {
        return  db.delete("HoaDonNhap", "maHoaDonNhap=?", new String[]{id});

    }

    public List<HoaDonNhap> getAll() throws ParseException {
        String sql = "SELECT * FROM HoaDonNhap";
        return getData(sql);
    }
    private List<HoaDonNhap> getData(String sql, String... selectionArgs) throws ParseException {
        List<HoaDonNhap> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            HoaDonNhap obj = new HoaDonNhap();
            obj.setmMaDongVat(c.getString(c.getColumnIndex(Name.maDongVat)));
            obj.setmMaHoaDonNhap(c.getString(c.getColumnIndex(Name.maHoaDonNhap)));
            obj.setmGiaNhap(c.getDouble(c.getColumnIndex(Name.giaNhap)));
            obj.setmSoLuongNhap(c.getInt(c.getColumnIndex(Name.soLuongNhap)));
            obj.setmNgayNhap(c.getString(c.getColumnIndex(Name.ngayNhap)));
            obj.setmGhiChuNhap(c.getString(c.getColumnIndex(Name.ghiChu)));
            list.add(obj);
        }
        return list;
    }

    private static class Name {
        public static String maDongVat = "maDongVat";
        public static String maHoaDonNhap = "maHoaDonNhap";
        public static String giaNhap = "giaNhap";
        public static String soLuongNhap = "soLuongNhap";
        public static String ngayNhap = "ngayNhap";
        public static String ghiChu = "ghiChu";

    }
}
