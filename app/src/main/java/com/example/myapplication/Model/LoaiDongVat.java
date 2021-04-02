package com.example.myapplication.Model;

public class LoaiDongVat {
    private String mLoaiDongVat;

    public LoaiDongVat() {
    }

    public LoaiDongVat(String loaiDongVat) {
        this.mLoaiDongVat = loaiDongVat;
    }

    public String getLoaiDongVat() {
        return mLoaiDongVat;
    }

    public void setLoaiDongVat(String loaiDongVat) {
        this.mLoaiDongVat = loaiDongVat;
    }

    @Override
    public String toString() {
        return "LoaiDongVat{" +
                "mLoaiDongVat='" + mLoaiDongVat + '\'' +
                '}';
    }
}
