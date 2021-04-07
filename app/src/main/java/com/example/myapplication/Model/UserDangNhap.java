package com.example.myapplication.Model;

public class UserDangNhap {

    private String image_profile;
    private String hovaten;
    private String diachi;
    private String sodienthoai;
    private String gmail;
    private String gioitinh;
    private String tentrangtrai;
    private String ngaysinh;

    public UserDangNhap() {
    }

    public String getImage_profile() {
        return image_profile;
    }

    public void setImage_profile(String image_profile) {
        this.image_profile = image_profile;
    }

    public String getHovaten() {
        return hovaten;
    }

    public void setHovaten(String hovaten) {
        this.hovaten = hovaten;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getTentrangtrai() {
        return tentrangtrai;
    }

    public void setTentrangtrai(String tentrangtrai) {
        this.tentrangtrai = tentrangtrai;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public UserDangNhap(String image_profile, String hovaten, String diachi, String sodienthoai, String gmail, String gioitinh, String tentrangtrai, String ngaysinh) {
        this.image_profile = image_profile;
        this.hovaten = hovaten;
        this.diachi = diachi;
        this.sodienthoai = sodienthoai;
        this.gmail = gmail;
        this.gioitinh = gioitinh;
        this.tentrangtrai = tentrangtrai;
        this.ngaysinh = ngaysinh;
    }
}
