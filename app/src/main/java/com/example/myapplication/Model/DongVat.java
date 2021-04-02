package com.example.myapplication.Model;

 public class DongVat {
     private String mLoaiDongVat;
     private String mMaDongVat;
     private int mSoLuongDongVat;
     private String mGhiChu;

     public DongVat() {
     }

     public DongVat(String mLoaiDongVat, String mMaDongVat, int mSoLuongDongVat, String mGhiChu) {
         this.mLoaiDongVat = mLoaiDongVat;
         this.mMaDongVat = mMaDongVat;
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
         return "DongVat{" +
                 "mLoaiDongVat='" + mLoaiDongVat + '\'' +
                 ", mMaDongVat='" + mMaDongVat + '\'' +
                 ", mSoLuongDongVat=" + mSoLuongDongVat +
                 ", mGhiChu='" + mGhiChu + '\'' +
                 '}';
     }
 }
