package com.example.myapplication.Model;

public class UserDangNhap {

    private String hovaten;
    private String diachi;
    private String sodienthoai;
    private String gmail;
    private String tentrangtrai;

    public UserDangNhap() {
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



    public String getTentrangtrai() {
        return tentrangtrai;
    }

    public void setTentrangtrai(String tentrangtrai) {
        this.tentrangtrai = tentrangtrai;
    }



    public UserDangNhap( String hovaten, String diachi, String sodienthoai, String gmail, String tentrangtrai) {
        this.hovaten = hovaten;
        this.diachi = diachi;
        this.sodienthoai = sodienthoai;
        this.gmail = gmail;
        this.tentrangtrai = tentrangtrai;
    }
}
