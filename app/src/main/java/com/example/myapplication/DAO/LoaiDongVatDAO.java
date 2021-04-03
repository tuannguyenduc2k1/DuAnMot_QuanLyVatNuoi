package com.example.myapplication.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.Database.DBHelper;
import com.example.myapplication.Model.LoaiDongVat;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class LoaiDongVatDAO {
    public static final String TABLE_NAME ="LoaiDongVat";
    public static final String SQL_LOAI_DONG_VAT = "CREATE TABLE LoaiDongVat (loaiDongVat text primary key );";
    public static final String TAG ="LoaiDongVatDAO";
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public LoaiDongVatDAO(Context context){
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    //insert
    public long insertLoaiDongVat(LoaiDongVat ldv){
        ContentValues values = new ContentValues();
        values.put(Name.loaiDongVat,ldv.getLoaiDongVat());
        return db.insert("LoaiDongVat",null,values);
    }
    //update
    public int update(LoaiDongVat ldv){
        ContentValues values = new ContentValues();
        values.put(Name.loaiDongVat,ldv.getLoaiDongVat());
        return db.update("LoaiDongVat",values,"loaiDongVat=?",new String[]{String.valueOf(ldv.getLoaiDongVat())}) ;
    }
    //delete
    public int delete(LoaiDongVat ldv){
        return db.delete("LoaiDongVat","loaiDongVat=?",new String[]{String.valueOf(ldv.getLoaiDongVat())});
    }
    //get all
    public List<LoaiDongVat> getAll() throws ParseException{
        String sql =" select * from LoaiDongVat ";
            return getData(sql);
    }

    private List<LoaiDongVat> getData(String sql,String... selectionArgs) throws ParseException{
        List<LoaiDongVat> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            LoaiDongVat ldv = new LoaiDongVat();
            ldv.setLoaiDongVat(c.getString(c.getColumnIndex(Name.loaiDongVat)));
            list.add(ldv);
        }
        return list;
    }
    private static class Name{
        public static String loaiDongVat = "loaiDongVat";
    }

}
