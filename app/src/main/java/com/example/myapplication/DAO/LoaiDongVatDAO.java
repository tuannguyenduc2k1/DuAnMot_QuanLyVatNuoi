package com.example.myapplication.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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
        values.put(Name.loaiDongVat,ldv.getmLoaiDongVat());
        try {
            if (db.insert(TABLE_NAME, null, values) == -1) {
                return -1;
            }
        } catch (Exception ex) {
            Log.e(TAG, ex.toString());
        }
        return 1;
    }
    public List<LoaiDongVat> getAllTheLoai() {
        List<LoaiDongVat> lsdongvay = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            LoaiDongVat ee = new LoaiDongVat();
            ee.setmLoaiDongVat(c.getString(0));
            lsdongvay.add(ee);
            Log.d("//=====", ee.toString());
            c.moveToNext();
        }
        c.close();
        return lsdongvay;
    }
    //update
    public int update(String ldv,String s){
        ContentValues values = new ContentValues();
        values.put("loaidongvat", s);
        int result = db.update(TABLE_NAME, values, "loaidongvat=?", new
                String[]{ldv});
        if (result == 0) {
            return -1;
        }
        return 1;
    }
    //delete
    public int delete(String ldv){

        return db.delete("loaidongvat","loaidongvat=?",new String[]{ldv});
    }
    //get all
//    public List<LoaiDongVat> getAll() throws ParseException{
//        String sql =" select * from LoaiDongVat ";
//            return getData(sql);
//    }

//    public List<LoaiDongVat> getData(String sql,String... selectionArgs) throws ParseException{
//        List<LoaiDongVat> list = new ArrayList<>();
//        Cursor c = db.rawQuery(sql,selectionArgs);
//        while (c.moveToNext()){
//            LoaiDongVat ldv = new LoaiDongVat();
//            ldv.setmLoaiDongVat(c.getString(c.getColumnIndex(Name.loaiDongVat)));
//            list.add(ldv);
//        }
//        return list;
//    }
    private static class Name{
        public static String loaiDongVat = "loaidongvat";
    }



}
