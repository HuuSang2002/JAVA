/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.TaiKhoanDTO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author DoHuy
 */
public class TaiKhoanDAO {

    Connection con;

    private void KetNoi() {
        String user = "sa";
        String pass = "123";
        String url = "jdbc:sqlserver://localhost:1433;" + "databaseName=QL_BAN_GIAY_09; trustServerCertificate = true";
        try {
            con = DriverManager.getConnection(url, user, pass);
        } catch (SQLException ex) {
            Logger.getLogger(KhacHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Lấy danh sách nhân viên
    public ArrayList<DTO.TaiKhoanDTO> LayDSTaiKhoan() {
        ArrayList<DTO.TaiKhoanDTO> ds = new ArrayList<>();
        KetNoi();
        if (con != null) {
            try {
                String sql = "select * from TaiKhoan where TrangThai = 0";
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    DTO.TaiKhoanDTO tk = new DTO.TaiKhoanDTO();
                    tk.TenDN = rs.getString("TenDN");
                    tk.MatKhau = rs.getString("MatKhau");
                    ds.add(tk);
                }
            } catch (SQLException ex) {
                Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ds;
    }

    //Tìm kiếm khách hàng
    public ArrayList<DTO.TaiKhoanDTO> TimTaiKhoan(String giatri) {
        ArrayList<DTO.TaiKhoanDTO> ds = new ArrayList<>();
        KetNoi();
        if (con != null) {
            try {
                CallableStatement st = con.prepareCall("{call TimtaiKhoan(?)}");
                st.setString(1, giatri);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    DTO.TaiKhoanDTO tk = new DTO.TaiKhoanDTO();
                    tk.TenDN = rs.getString("TenDN");
                    tk.MatKhau = rs.getString("MatKhau");
                    ds.add(tk);
                }
            } catch (SQLException ex) {
                Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ds;
    }

//    // Thêm nhân viên
//    public void ThemTaiKhoan(DTO.TaiKhoanDTO m) {
//        //String matk1 = PhatSinhMa();
//        KetNoi();
//        try {
//            CallableStatement st = con.prepareCall("{call Themtaikhoan(?,?)}");
//            st.setString(1, m.TenDN);
//            st.setString(2, m.MatKhau);
//            st.executeUpdate();
//            JOptionPane.showMessageDialog(null, "Thêm thành công !");
//        } catch (SQLException ex) {
//            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//}
    
    
    public String phatSinhMa() throws SQLException {
        String matk = "";
        //con = KetNoi();
        if (con != null) {
            try {
                CallableStatement st = con.prepareCall("{call sinhmatk}");
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    String ma = rs.getString("TenDN");

                    String so = ma.substring(2, ma.length()).trim();
                    if (Integer.parseInt(so) >= 0 && Integer.parseInt(so) < 9) {
                        matk = "TK" + "000" + (Integer.parseInt(so) + 1);
                    } else if (Integer.parseInt(so) >= 9 && Integer.parseInt(so) < 99) {
                        matk = "TK" + "00" + (Integer.parseInt(so) + 1);
                    } else if (Integer.parseInt(so) >= 99 && Integer.parseInt(so) < 999) {
                        matk = "TK" + "0" + (Integer.parseInt(so) + 1);
                    } else {
                        matk = "TK" + (Integer.parseInt(so) + 1);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return matk;
    }
    
    
    // kiem tra mat khau đã tồn tại
    public boolean kiemTraMatKhauTrung(String matKhau) throws SQLException {
    KetNoi();
    try {
        CallableStatement st = con.prepareCall("{call kiemTraMatKhau(?)}");
        st.setString(1, matKhau);
        ResultSet rs = st.executeQuery();
        if (rs.next() && rs.getInt("Result") == 1) {
            return true; // Password exists
        }
    } catch (SQLException ex) {
        Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false; // Password does not exist
}

    public void ThemTaiKhoan(DTO.TaiKhoanDTO m) throws SQLException {
    if (kiemTraMatKhauTrung(m.getMatKhau())) {
        JOptionPane.showMessageDialog(null, "Mật khẩu đã tồn tại!");
        return;
    }
    
    String matk1 = phatSinhMa();
    KetNoi();
    try {
        CallableStatement st = con.prepareCall("{call Themtaikhoan(?,?)}");
        st.setString(1, matk1);
        st.setString(2, m.getMatKhau());
        st.executeUpdate();
        JOptionPane.showMessageDialog(null, "Thêm thành công!");
    } catch (SQLException ex) {
        Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
}


  // Sửa Nhân viên theo mã nhân viên
    public void SuaTaiKhoan(DTO.TaiKhoanDTO nv) throws SQLException {
         if (kiemTraMatKhauTrung(nv.getMatKhau())) {
        JOptionPane.showMessageDialog(null, "Mật khẩu đã tồn tại!");
        return;
    }
        
        KetNoi();
        try {
            CallableStatement st = con.prepareCall("{call SuaTaiKhoan(?,?)}");
            // Set parameters using proper methods
            st.setString(1, nv.getTenDN());
            st.setString(2, nv.getMatKhau());
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Sửa thành công !");
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Xóa khách hàng
    public void XoaTaiKhoan(DTO.TaiKhoanDTO m) {
        KetNoi();
        try {
            CallableStatement st = con.prepareCall("{call XoaTaiKhoan(?)}");
            st.setString(1, m.TenDN);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Xóa thành công !");
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    // Xóa khách hàng
    public void XoaTaiKhoanMat(DTO.TaiKhoanDTO m) {
        KetNoi();
        try {
            CallableStatement st = con.prepareCall("{call XoaMatTK(?)}");
            st.setString(1, m.TenDN);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Xóa thành công !");
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    


    
}

