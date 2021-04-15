package com.example.myapplication.Model;

 public class DongVat {
     private String mMaDongVat;
     private String mLoaiDongVat;
     private int mSoLuongDongVat;
     private String mGhiChu;

     public DongVat() {
     }

     public DongVat(String mMaDongVat, String mLoaiDongVat, int mSoLuongDongVat, String mGhiChu) {
         this.mMaDongVat = mMaDongVat;
         this.mLoaiDongVat = mLoaiDongVat;
         this.mSoLuongDongVat = mSoLuongDongVat;
         this.mGhiChu = mGhiChu;
     }

     public String getmLoaiDongVat() {
         return mLoaiDongVat;
     }

     public void setmLoaiDongVat(String mLoaiDongVat) {
         this.mLoaiDongVat = mLoaiDongVat;
     }

     public String getmMaDongVat() {
         return mMaDongVat;
     }

     public void setmMaDongVat(String mMaDongVat) {
         this.mMaDongVat = mMaDongVat;
     }

     public int getmSoLuongDongVat() {
         return mSoLuongDongVat;
     }

     public void setmSoLuongDongVat(int mSoLuongDongVat) {
         this.mSoLuongDongVat = mSoLuongDongVat;
     }

     public String getmGhiChu() {
         return mGhiChu;
     }

     public void setmGhiChu(String mGhiChu) {
         this.mGhiChu = mGhiChu;
     }

     @Override
     public String toString() {
         return ""+getmLoaiDongVat();
     }
 }
