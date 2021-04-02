package com.example.myapplication.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DatabaseName = "QuanLiDongVatDb";
    public Context context;
    private static final int version = 1 ;

    public DBHelper(@Nullable Context context) {
        super(context, DatabaseName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableLoaiDongVat = "create table LoaiDongVat("+"loaiDongVat TEXT PRIMARY KEY )";
        db.execSQL(createTableLoaiDongVat);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
