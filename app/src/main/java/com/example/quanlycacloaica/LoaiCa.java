package com.example.quanlycacloaica;

public class LoaiCa {
    private String id;
    private String tenKH;
    private String tenThuong;
    private String dacTinh;
    private String mauSac;

    @Override
    public String toString() {
        return "LoaiCa{" +
                "id='" + id + '\'' +
                ", tenKH='" + tenKH + '\'' +
                ", tenThuong='" + tenThuong + '\'' +
                ", dacTinh='" + dacTinh + '\'' +
                ", mauSac='" + mauSac + '\'' +
                '}';
    }

    public LoaiCa() {
    }

    public LoaiCa( String tenKH, String tenThuong, String dacTinh, String mauSac) {

        this.tenKH = tenKH;
        this.tenThuong = tenThuong;
        this.dacTinh = dacTinh;
        this.mauSac = mauSac;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getTenThuong() {
        return tenThuong;
    }

    public void setTenThuong(String tenThuong) {
        this.tenThuong = tenThuong;
    }

    public String getDacTinh() {
        return dacTinh;
    }

    public void setDacTinh(String dacTinh) {
        this.dacTinh = dacTinh;
    }

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }
}
