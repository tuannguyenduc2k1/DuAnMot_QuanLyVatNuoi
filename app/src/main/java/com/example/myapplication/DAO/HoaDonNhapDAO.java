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
    public static final String SQL_HOA_DON_NHAP = "CREATE TABLE HoaDonNhap(maHoaDonNhap text primary key , maDongVat text , giaNhap double , soLuongNhap int , ngayNhap date , ghiChu text , tongtien double)";
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
    public long update(String hoaDonNhap,String maHDN,String giaNhap,String sln,String ngayNhap,String ghiChu){
        ContentValues values = new ContentValues();
        values.put(Name.maHoaDonNhap,maHDN);
        values.put(Name.giaNhap,giaNhap);
        values.put(Name.soLuongNhap,sln);
        values.put(Name.ngayNhap,ngayNhap);
        values.put(Name.ghiChu,ghiChu);
        int result =  db.update("HoaDonNhap",values,"maHoaDonNhap=?",new String[]{hoaDonNhap}) ;
        if (result == 0) {
            return -1;
        }
        return 1;
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

    public double getDoanhThuNhap(){
        double doanhNhap = 0;
        String sSQL = "SELECT SUM(soLuongNhap * giaNhap) from HoaDonNhap";
        Cursor c = db.rawQuery(sSQL,null);
        c.moveToFirst();
        while (c.isAfterLast()  == false){
            doanhNhap = c.getDouble(0);
            c.moveToNext();
        }
        c.close();
        return doanhNhap;
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
