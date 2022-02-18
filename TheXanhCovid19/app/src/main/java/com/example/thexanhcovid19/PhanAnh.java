package com.example.thexanhcovid19;

import java.util.Date;

public class PhanAnh {

    private String HoTen;

    private String SoCMND;

    private String DienThoai;

    private String Email;

    private String TieuDe;

    private String NoiDung;

    private Date NgayPhanAnh;

    @Override
    public String toString() {
        return "PhanAnh{" +
                "HoTen='" + HoTen + '\'' +
                ", SoCMND='" + SoCMND + '\'' +
                ", DienThoai='" + DienThoai + '\'' +
                ", Email='" + Email + '\'' +
                ", TieuDe='" + TieuDe + '\'' +
                ", NoiDung='" + NoiDung + '\'' +
                ", NgayPhanAnh=" + NgayPhanAnh +
                '}';
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public String getSoCMND() {
        return SoCMND;
    }

    public void setSoCMND(String soCMND) {
        SoCMND = soCMND;
    }

    public String getDienThoai() {
        return DienThoai;
    }

    public void setDienThoai(String dienThoai) {
        DienThoai = dienThoai;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getTieuDe() {
        return TieuDe;
    }

    public void setTieuDe(String tieuDe) {
        TieuDe = tieuDe;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String noiDung) {
        NoiDung = noiDung;
    }

    public Date getNgayPhanAnh() {
        return NgayPhanAnh;
    }

    public void setNgayPhanAnh(Date ngayPhanAnh) {
        NgayPhanAnh = ngayPhanAnh;
    }

    public PhanAnh(String hoTen, String soCMND, String dienThoai, String email, String tieuDe, String noiDung, Date ngayPhanAnh) {
        HoTen = hoTen;
        SoCMND = soCMND;
        DienThoai = dienThoai;
        Email = email;
        TieuDe = tieuDe;
        NoiDung = noiDung;
        NgayPhanAnh = ngayPhanAnh;
    }
}
