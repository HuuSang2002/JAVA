/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author DoHuy
 */
public class TaiKhoanDTO {

    
    public String TenDN;
    public String MatKhau;
    public String getTenDN() {
        return TenDN;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setTenDN(String TenDN) {
        this.TenDN = TenDN;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    public TaiKhoanDTO() {
    }

    public TaiKhoanDTO(String TenDN, String MatKhau) {
        this.TenDN = TenDN;
        this.MatKhau = MatKhau;
    }
    
    
}
