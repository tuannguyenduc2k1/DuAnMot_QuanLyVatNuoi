package com.example.myapplication.Model;

import java.util.Date;

public class HoaDonXuat {
    private String mMaDongVat;
    private String mMaHoaDonXuat;
    private Double mGiaXuat;
    private int mSoLuongXuat;
    private String mNgayXuat;
    private String mGhiChuXuat;

    public HoaDonXuat() {
    }

    public HoaDonXuat(String mMaDongVat, String mMaHoaDonXuat, Double mGiaXuat, int mSoLuongXuat, String mNgayXuat, String mGhiChuXuat) {
        this.mMaDongVat = mMaDongVat;
        this.mMaHoaDonXuat = mMaHoaDonXuat;
        this.mGiaXuat = mGiaXuat;
        this.mSoLuongXuat = mSoLuongXuat;
        this.mNgayXuat = mNgayXuat;
        this.mGhiChuXuat = mGhiChuXuat;
    }

    public String getmMaDongVat() {
        return mMaDongVat;
    }

    public void setmMaDongVat(String mMaDongVat) {
        this.mMaDongVat = mMaDongVat;
    }

    public String getmMaHoaDonXuat() {
        return mMaHoaDonXuat;
    }

    public void setmMaHoaDonXuat(String mMaHoaDonXuat) {
        this.mMaHoaDonXuat = mMaHoaDonXuat;
    }

    public Double getmGiaXuat() {
        return mGiaXuat;
    }

    public void setmGiaXuat(Double mGiaXuat) {
        this.mGiaXuat = mGiaXuat;
    }

    public int getmSoLuongXuat() {
        return mSoLuongXuat;
    }

    public void setmSoLuongXuat(int mSoLuongXuat) {
        this.mSoLuongXuat = mSoLuongXuat;
    }

    public String getmNgayXuat() {
        return mNgayXuat;
    }

    public void setmNgayXuat(String mNgayXuat) {
        this.mNgayXuat = mNgayXuat;
    }

    public String getmGhiChuXuat() {
        return mGhiChuXuat;
    }

    public void setmGhiChuXuat(String mGhiChuXuat) {
        this.mGhiChuXuat = mGhiChuXuat;
    }

    @Override
    public String toString() {
        return "HoaDonXuat{" +
                "mMaDongVat='" + mMaDongVat + '\'' +
                ", mMaHoaDonXuat='" + mMaHoaDonXuat + '\'' +
                ", mGiaXuat=" + mGiaXuat +
                ", mSoLuongXuat=" + mSoLuongXuat +
                ", mNgayXuat='" + mNgayXuat + '\'' +
                ", mGhiChuXuat='" + mGhiChuXuat + '\'' +
                '}';
    }
}
