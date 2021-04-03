package com.example.myapplication.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.myapplication.DAO.DongVatDAO;
import com.example.myapplication.DAO.HoaDonNhapDAO;
import com.example.myapplication.DAO.HoaDonXuatDAO;
import com.example.myapplication.DAO.LoaiDongVatDAO;
import com.example.myapplication.Model.DongVat;
import com.example.myapplication.Model.HoaDonXuat;
import com.example.myapplication.Model.LoaiDongVat;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DatabaseName = "QuanLiDongVatDb";
    private static final int version = 1 ;
    public Context context;

    public DBHelper(@Nullable Context context) {
        super(context, DatabaseName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(LoaiDongVatDAO.SQL_LOAI_DONG_VAT);
        db.execSQL(DongVatDAO.SQL_DONG_VAT);
        db.execSQL(HoaDonXuatDAO.SQL_HOA_DON_XUAT);
        db.execSQL(HoaDonNhapDAO.SQL_HOA_DON_NHAP);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists " + LoaiDongVatDAO.TABLE_NAME);
        db.execSQL("Drop table if exists " + DongVatDAO.TABLE_NAME);
        db.execSQL("Drop table if exists " + HoaDonXuatDAO.TABLE_NAME);
        db.execSQL("Drop table if exists " + HoaDonNhapDAO.TABLE_NAME);
        onCreate(db);

    }
}
