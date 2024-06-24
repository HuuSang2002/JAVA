/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author DoHuy
 */
public class NhanVienDTO {

    public String MaNV;
    public String TenNV;
    public String DiaChi;
    public String GioiTinh;
    public String SDT;
    public String Email;
    public String TrangThai;

    public NhanVienDTO() {
    }

    public NhanVienDTO(String MaNV, String TenNV, String DiaChi, String GioiTinh, float Luong, String SDT, String Email, String TrangThai) {
        this.MaNV = MaNV;
        this.TenNV = TenNV;
        this.DiaChi = DiaChi;
        this.GioiTinh = GioiTinh;
        this.SDT = SDT;
        this.Email = Email;
        this.TrangThai = TrangThai;
    }

    public String getMaNV() {
        return MaNV;
    }

    public String getTenNV() {
        return TenNV;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }



    public String getSDT() {
        return SDT;
    }

    public String getEmail() {
        return Email;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public void setTenNV(String TenNV) {
        this.TenNV = TenNV;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public void setGioiTinh(String GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

   

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }

}


