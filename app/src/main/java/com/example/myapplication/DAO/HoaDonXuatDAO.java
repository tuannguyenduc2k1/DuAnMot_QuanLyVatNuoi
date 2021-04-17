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
    public static final String SQL_HOA_DON_XUAT = "CREATE TABLE HoaDonXuat(maHoaDonXuat text primary key , maDongVat text  , giaXuat double , soLuongXuat int , ngayXuat date , ghiChu date )";
    public static final String TAG = "HoaDonXuatDAO";


    private static SQLiteDatabase db;
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
        values.put(Name.ngayXuat,hdx.getmNgayXuat());
        values.put(Name.ghiChu,hdx.getmGhiChuXuat());

        return db.insert("HoaDonXuat",null,values);
    }
    //update
    public int update(String hoaDonXuat,String maHDX,String giaXuat,String slx,String ngayXuat,String ghiChu){
        ContentValues values = new ContentValues();
        values.put(Name.maDongVat,hoaDonXuat);
        values.put(Name.maHoaDonXuat,maHDX);
        values.put(Name.giaXuat,giaXuat);
        values.put(Name.soLuongXuat,slx);
        values.put(Name.ngayXuat,ngayXuat);
        values.put(Name.ghiChu,ghiChu);

        int result = db.update("HoaDonXuat",values,"maHoaDonXuat=?",new String[]{hoaDonXuat}) ;
        if (result == 0) {
            return -1;
        }
        return 1;
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
            obj.setmNgayXuat(c.getString(c.getColumnIndex(Name.ngayXuat)));
            obj.setmGhiChuXuat(c.getString(c.getColumnIndex(Name.ghiChu)));
            list.add(obj);
        }
        return list;
    }
    public double getDoanhThuTheoNgay() {
        double doanhThu = 0;
        String sSQL = "SELECT SUM(tongtienlai) from (SELECT SUM((HoaDonXuat.soLuongXuat * HoaDonXuat.giaXuat) - (HoaDonNhap.giaNhap * HoaDonNhap.soLuongNhap)) as 'tongtienlai' "
                + "FROM HoaDonXuat INNER JOIN HoaDonXuat on HoaDonXuat.maHoaDonXuat = HoaDonNhap.maHoaDonNhap "
                + "INNER JOIN HoaDonXuat on  HoaDonXuat.maDongVat = HoaDonNhap.maDongVat  where HoaDonXuat.ngayXuat = date('now') " +
                "GROUP BY HoaDonXuat.maDongVat)tmp";
        Cursor c = db.rawQuery(sSQL, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            doanhThu = c.getDouble(0);
            c.moveToNext();
        }
        c.close();
        return doanhThu;
    }
//    public double getDoanhThuTheoThang() {
//        double doanhThu = 0;
//        String sSQL = "SELECT SUM(tongtien) from (SELECT SUM(Sach.giaBia * HoaDonChiTiet.soLuong) as 'tongtien' " + "FROM HoaDon INNER JOIN HoaDonChiTiet on HoaDon.maHoaDon = HoaDonChiTiet.maHoaDon " + "INNER JOIN Sach on HoaDonChiTiet.maSach = Sach.maSach where strftime('%m',HoaDon.ngayMua) = strftime('%m','now') GROUP BY HoaDonChiTiet.maSach)tmp";
//        Cursor c = db.rawQuery(sSQL, null);
//        c.moveToFirst();
//        while (c.isAfterLast() == false) {
//            doanhThu = c.getDouble(0);
//            c.moveToNext();
//        }
//        c.close();
//        return doanhThu;
//    }
//
//    public double getDoanhThuTheoNam() {
//        double doanhThu = 0;
//        String sSQL = "SELECT SUM(tongtien) from (SELECT SUM(Sach.giaBia * HoaDonChiTiet.soLuong) as 'tongtien' " + "FROM HoaDon INNER JOIN HoaDonChiTiet on HoaDon.maHoaDon = HoaDonChiTiet.maHoaDon " + "INNER JOIN Sach on HoaDonChiTiet.maSach = Sach.maSach where strftime('%Y',HoaDon.ngayMua) = strftime('%Y','now') GROUP BY HoaDonChiTiet.maSach)tmp";
//        Cursor c = db.rawQuery(sSQL, null);
//        c.moveToFirst();
//        while (c.isAfterLast() == false) {
//            doanhThu = c.getDouble(0);
//            c.moveToNext();
//        }
//        c.close();
//        return doanhThu;
//    }

    private static class Name {
        public static String maDongVat = "maDongVat";
        public static String maHoaDonXuat = "maHoaDonXuat";
        public static String giaXuat = "giaXuat";
        public static String soLuongXuat = "soLuongXuat";
        public static String ngayXuat = "ngayXuat";
        public static String ghiChu = "ghiChu";

    }
}
