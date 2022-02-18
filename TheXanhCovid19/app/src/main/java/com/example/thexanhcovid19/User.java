package com.example.thexanhcovid19;

import java.io.Serializable;
import java.text.Normalizer;
import java.util.regex.Pattern;

public class User implements Serializable {
    private String ho;
    private String tenDem;
    private String ten;
    private String soCMND;
    private String userName;
    private String matKhau;
    private String dienThoai;
    private String gioiTinh;
    private String email;
    private String queQuan;
    private String diaChi;
    private String ngaySinh;
    private int soMuiTiem;
    private String loaiThe;

    public int getSoMuiTiem() {
        return soMuiTiem;
    }

    public void setSoMuiTiem(int soMuiTiem) {
        this.soMuiTiem = soMuiTiem;
    }

    public String getLoaiThe() {
        return loaiThe;
    }

    public void setLoaiThe(String loaiThe) {
        this.loaiThe = loaiThe;
    }

    @Override
    public String toString() {
        return
                "" + covertToString(ho) +
                " " + covertToString(tenDem) +
                " " + covertToString(ten) +
                " -- " + covertToString(loaiThe) +
                " -- " + soCMND +
                " -- " + dienThoai +
                " -- " + covertToString(gioiTinh) +
                " -- " + covertToString(queQuan) ;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQueQuan() {
        return queQuan;
    }

    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getTenDem() {
        return tenDem;
    }

    public void setTenDem(String tenDem) {
        this.tenDem = tenDem;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSoCMND() {
        return soCMND;
    }

    public void setSoCMND(String soCMND) {
        this.soCMND = soCMND;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }


    public static String covertToString(String value) {
        try {
            String temp = Normalizer.normalize(value, Normalizer.Form.NFD);
            Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
            return pattern.matcher(temp).replaceAll("");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
