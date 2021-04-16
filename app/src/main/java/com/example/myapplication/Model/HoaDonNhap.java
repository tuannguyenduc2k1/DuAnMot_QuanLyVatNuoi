package com.example.myapplication.Model;

import java.util.Date;

public class HoaDonNhap {
    private String mMaDongVat;
    private String mMaHoaDonNhap;
    private Double mGiaNhap;
    private int mSoLuongNhap;
    private String mNgayNhap;
    private String mGhiChuNhap;

    public HoaDonNhap() {
    }

    public HoaDonNhap(String mMaDongVat, String mMaHoaDonNhap, Double mGiaNhap, int mSoLuongNhap, String mNgayNhap, String mGhiChuNhap) {
        this.mMaDongVat = mMaDongVat;
        this.mMaHoaDonNhap = mMaHoaDonNhap;
        this.mGiaNhap = mGiaNhap;
        this.mSoLuongNhap = mSoLuongNhap;
        this.mNgayNhap = mNgayNhap;
        this.mGhiChuNhap = mGhiChuNhap;
    }

    public String getmMaDongVat() {
        return mMaDongVat;
    }

    public void setmMaDongVat(String mMaDongVat) {
        this.mMaDongVat = mMaDongVat;
    }

    public String getmMaHoaDonNhap() {
        return mMaHoaDonNhap;
    }

    public void setmMaHoaDonNhap(String mMaHoaDonNhap) {
        this.mMaHoaDonNhap = mMaHoaDonNhap;
    }

    public Double getmGiaNhap() {
        return mGiaNhap;
    }

    public void setmGiaNhap(Double mGiaNhap) {
        this.mGiaNhap = mGiaNhap;
    }

    public int getmSoLuongNhap() {
        return mSoLuongNhap;
    }

    public void setmSoLuongNhap(int mSoLuongNhap) {
        this.mSoLuongNhap = mSoLuongNhap;
    }

    public String getmNgayNhap() {
        return mNgayNhap;
    }

    public void setmNgayNhap(String mNgayNhap) {
        this.mNgayNhap = mNgayNhap;
    }

    public String getmGhiChuNhap() {
        return mGhiChuNhap;
    }

    public void setmGhiChuNhap(String mGhiChuNhap) {
        this.mGhiChuNhap = mGhiChuNhap;
    }

    @Override
    public String toString() {
        return "HoaDonNhap{" +
                "mMaDongVat='" + getmMaDongVat() + '\'' +
                ", mMaHoaDonNhap='" + getmMaHoaDonNhap() + '\'' +
                ", mGiaNhap=" + mGiaNhap +
                ", mSoLuongNhap=" + mSoLuongNhap +
                ", mNgayNhap='" + mNgayNhap + '\'' +
                ", mGhiChuNhap='" + mGhiChuNhap + '\'' +
                '}';
    }
}