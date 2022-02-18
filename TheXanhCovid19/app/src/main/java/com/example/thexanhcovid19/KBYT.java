package com.example.thexanhcovid19;

import java.util.Date;

public class KBYT {
    private String HoTen;
    private String SoCMND;
    private String GioiTinh;
    private String NgaySinh;
    private String DienThoai;
    private String SoTheBHYT;
    private String Email;
    private String ProvinceID;
    private String DistrictID;
    private String WardsID;
    private String DiaDiemCuThe;
    private String TiepXucKhuVuc;
    private String TiepXucNguoiBenh;
    private String TrieuChungNhiemBenh;

    @Override
    public String toString() {
        return "KBYT{" +
                "HoTen='" + HoTen + '\'' +
                ", SoCMND='" + SoCMND + '\'' +
                ", GioiTinh='" + GioiTinh + '\'' +
                ", NgaySinh=" + NgaySinh +
                ", DienThoai='" + DienThoai + '\'' +
                ", SoTheBHYT='" + SoTheBHYT + '\'' +
                ", Email='" + Email + '\'' +
                ", ProvinceID='" + ProvinceID + '\'' +
                ", DistrictID='" + DistrictID + '\'' +
                ", WardsID='" + WardsID + '\'' +
                ", DiaDiemCuThe='" + DiaDiemCuThe + '\'' +
                ", TiepXucKhuVuc='" + TiepXucKhuVuc + '\'' +
                ", TiepXucNguoiBenh='" + TiepXucNguoiBenh + '\'' +
                ", TrieuChungNhiemBenh='" + TrieuChungNhiemBenh + '\'' +
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

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        GioiTinh = gioiTinh;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        NgaySinh = ngaySinh;
    }

    public String getDienThoai() {
        return DienThoai;
    }

    public void setDienThoai(String dienThoai) {
        DienThoai = dienThoai;
    }

    public String getSoTheBHYT() {
        return SoTheBHYT;
    }

    public void setSoTheBHYT(String soTheBHYT) {
        SoTheBHYT = soTheBHYT;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getProvinceID() {
        return ProvinceID;
    }

    public void setProvinceID(String provinceID) {
        ProvinceID = provinceID;
    }

    public String getDistrictID() {
        return DistrictID;
    }

    public void setDistrictID(String districtID) {
        DistrictID = districtID;
    }

    public String getWardsID() {
        return WardsID;
    }

    public void setWardsID(String wardsID) {
        WardsID = wardsID;
    }

    public String getDiaDiemCuThe() {
        return DiaDiemCuThe;
    }

    public void setDiaDiemCuThe(String diaDiemCuThe) {
        DiaDiemCuThe = diaDiemCuThe;
    }

    public String getTiepXucKhuVuc() {
        return TiepXucKhuVuc;
    }

    public void setTiepXucKhuVuc(String tiepXucKhuVuc) {
        TiepXucKhuVuc = tiepXucKhuVuc;
    }

    public String getTiepXucNguoiBenh() {
        return TiepXucNguoiBenh;
    }

    public void setTiepXucNguoiBenh(String tiepXucNguoiBenh) {
        TiepXucNguoiBenh = tiepXucNguoiBenh;
    }

    public String getTrieuChungNhiemBenh() {
        return TrieuChungNhiemBenh;
    }

    public void setTrieuChungNhiemBenh(String trieuChungNhiemBenh) {
        TrieuChungNhiemBenh = trieuChungNhiemBenh;
    }

    public KBYT(String hoTen, String soCMND, String gioiTinh, String ngaySinh, String dienThoai, String soTheBHYT, String email, String provinceID, String districtID, String wardsID, String diaDiemCuThe, String tiepXucKhuVuc, String tiepXucNguoiBenh, String trieuChungNhiemBenh) {
        HoTen = hoTen;
        SoCMND = soCMND;
        GioiTinh = gioiTinh;
        NgaySinh = ngaySinh;
        DienThoai = dienThoai;
        SoTheBHYT = soTheBHYT;
        Email = email;
        ProvinceID = provinceID;
        DistrictID = districtID;
        WardsID = wardsID;
        DiaDiemCuThe = diaDiemCuThe;
        TiepXucKhuVuc = tiepXucKhuVuc;
        TiepXucNguoiBenh = tiepXucNguoiBenh;
        TrieuChungNhiemBenh = trieuChungNhiemBenh;
    }
}
