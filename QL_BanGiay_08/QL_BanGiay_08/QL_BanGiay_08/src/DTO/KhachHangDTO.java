/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author DoHuy
 */
public class KhachHangDTO {

    
    public String TenKH;
    public String MaKH;
    public String DiaChi;
    public String Sdt;
    public String Email;
    public String TrangThai;
    public KhachHangDTO() {
        
    }

    public KhachHangDTO(String MaKH, String TenKH, String DiaChi, String Sdt, String Email, String TrangThai) {
        this.MaKH = MaKH;
        this.TenKH = TenKH;
        this.DiaChi = DiaChi;
        this.Sdt = Sdt;
        this.Email = Email;
        this.TrangThai = TrangThai;
    }

    public String getMaKH() {
        return MaKH;
    }

    public String getTenKH() {
        return TenKH;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public String getSdt() {
        return Sdt;
    }

    public String getEmail() {
        return Email;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }

    public void setTenKH(String TenKH) {
        this.TenKH = TenKH;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public void setSdt(String Sdt) {
        this.Sdt = Sdt;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }
    

    
}


