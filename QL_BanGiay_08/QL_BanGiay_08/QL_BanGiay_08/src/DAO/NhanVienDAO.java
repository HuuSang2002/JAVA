/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.NhanVienDTO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.lang.model.util.Types;
import javax.swing.JOptionPane;

/**
 *
 * @author DoHuy
 */
public class NhanVienDAO {

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
    public ArrayList<DTO.NhanVienDTO> LayDanhSachNV() {
        ArrayList<DTO.NhanVienDTO> ds = new ArrayList<>();
        KetNoi();
        if (con != null) {
            try {
                String sql = "select * from nhanvien where TrangThai = 0";
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    DTO.NhanVienDTO nv = new DTO.NhanVienDTO();
                    nv.MaNV = rs.getString("MaNV");
                    nv.TenNV = rs.getString("TenNV");
                    nv.SDT = rs.getString("SDT");
                    nv.GioiTinh = rs.getString("GioiTinh");
                    nv.DiaChi = rs.getString("DiaChi");
                    nv.Email = rs.getString("Email");
                    nv.TrangThai = rs.getString("TrangThai");
                    ds.add(nv);
                }
            } catch (SQLException ex) {
                Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ds;
    }
    
      public ArrayList<DTO.NhanVienDTO> LayDanhSachProcNVkhoiPhuc() {
        ArrayList<DTO.NhanVienDTO> ds = new ArrayList<>();
        KetNoi();
        if (con != null) {
            try {
                CallableStatement st = con.prepareCall("{call dsNhanVien02}");
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    DTO.NhanVienDTO nv = new DTO.NhanVienDTO();
                    nv.MaNV = rs.getString("MaNV");
                    nv.TenNV = rs.getString("TenNV");
                    nv.SDT = rs.getString("SDT");
                    nv.GioiTinh = rs.getString("GioiTinh");
                    nv.DiaChi = rs.getString("DiaChi");
                    nv.Email = rs.getString("Email");
                    nv.TrangThai = rs.getString("TrangThai");
                    ds.add(nv);
                }
            } catch (SQLException ex) {
                Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ds;
    }

    public ArrayList<DTO.NhanVienDTO> LayDanhSachProcNV() {
        ArrayList<DTO.NhanVienDTO> ds = new ArrayList<>();
        KetNoi();
        if (con != null) {
            try {
                CallableStatement st = con.prepareCall("{call danhsachnhanvien1}");
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    DTO.NhanVienDTO nv = new DTO.NhanVienDTO();
                    nv.MaNV = rs.getString("MaNV");
                    nv.TenNV = rs.getString("TenNV");
                    nv.SDT = rs.getString("SDT");
                    nv.GioiTinh = rs.getString("GioiTinh");
                    nv.DiaChi = rs.getString("DiaChi");
                    nv.Email = rs.getString("Email");
                    nv.TrangThai = rs.getString("TrangThai");
                    ds.add(nv);
                }
            } catch (SQLException ex) {
                Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ds;
    }

    //Tìm kiếm khách hàng
    public ArrayList<DTO.NhanVienDTO> TimKiemNV(String giatri) {
        ArrayList<DTO.NhanVienDTO> ds = new ArrayList<>();
        KetNoi();
        if (con != null) {
            try {
                CallableStatement st = con.prepareCall("{call TimKiemNV(?)}");
                st.setString(1, giatri);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    DTO.NhanVienDTO nv = new DTO.NhanVienDTO();
                    nv.MaNV = rs.getString("MaNV");
                    nv.TenNV = rs.getString("TenNV");
                    nv.SDT = rs.getString("SDT");
                    nv.GioiTinh = rs.getString("GioiTinh");
                    nv.DiaChi = rs.getString("DiaChi");
                    nv.Email = rs.getString("Email");
                    nv.TrangThai = rs.getString("TrangThai");
                    ds.add(nv);
                }
            } catch (SQLException ex) {
                Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ds;
    }

//cập nhật thêm code---------------------------------------------------------------------------------------------------------
//    // Thêm nhân viên
//    public void ThemNhanVien(DTO.NhanVienDTO m) {
//        KetNoi();
//        try {
//            
//  
//            
//            CallableStatement st = con.prepareCall("{call themnhanvien(?,?,?,?,?,?)}");
//            st.setString(1,m.MaNV);
//            st.setString(2,m.TenNV);
//            st.setString(3,m.DiaChi);
//            st.setString(4,m.GioiTinh);
//            st.setString(5,m.SDT);
//            st.setString(6,m.Email);
//            st.executeUpdate();
//            JOptionPane.showMessageDialog(null, "Thêm thành công !");
//        } catch (SQLException ex) {
//            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//    }
    public String phatSinhMa() throws SQLException {
        String maNV = "";
        //con = KetNoi();
        if (con != null) {
            try {
                CallableStatement st = con.prepareCall("{call sinhmaNhanVien}");
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    String ma = rs.getString("MaNV");

                    String so = ma.substring(2, ma.length()).trim();
                    if (Integer.parseInt(so) >= 0 && Integer.parseInt(so) < 9) {
                        maNV = "NV" + "000" + (Integer.parseInt(so) + 1);
                    } else if (Integer.parseInt(so) >= 9 && Integer.parseInt(so) < 99) {
                        maNV = "NV" + "00" + (Integer.parseInt(so) + 1);
                    } else if (Integer.parseInt(so) >= 99 && Integer.parseInt(so) < 999) {
                        maNV = "NV" + "0" + (Integer.parseInt(so) + 1);
                    } else {
                        maNV = "NV" + (Integer.parseInt(so) + 1);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return maNV;
    }

    // Thêm nhân viên
    public void ThemNhanVien(DTO.NhanVienDTO m) throws SQLException {
        String maNV = phatSinhMa();
        KetNoi();
        try {
            CallableStatement st = con.prepareCall("{call themnhanvien(?,?,?,?,?,?)}");
            st.setString(1, maNV);
            st.setString(2, m.TenNV);
            st.setString(3, m.DiaChi);
            st.setString(4, m.GioiTinh);
            st.setString(5, m.SDT);
            st.setString(6, m.Email);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Thêm thành công !");
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

//---------------------------------------------------------------------------------------------------------    
    // Sửa Nhân viên theo mã nhân viên
//    public void SuaNhanVien(DTO.NhanVienDTO nv) {
//        KetNoi();
//        try {
//            CallableStatement st = con.prepareCall("{call SuaNhanVien(?,?,?,?,?,?)}");
//            // Set parameters using proper methods
//            st.setString(1, nv.getMaNV());
//            st.setString(2, nv.getTenNV());
//            st.setString(3, nv.getDiaChi());
//            st.setString(4, nv.getGioiTinh());
//            st.setString(5, nv.getSDT());
//            st.setString(6, nv.getEmail());
//            st.executeUpdate();
//            JOptionPane.showMessageDialog(null, "Sửa thành công !");
//        } catch (SQLException ex) {
//            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    //Sửa Nhân viên theo mã nhân viên
    public void SuaNhanVien(DTO.NhanVienDTO nv, String trangThai) {
        KetNoi();
        try {
            CallableStatement st = con.prepareCall("{call SuaNhanVien(?,?,?,?,?,?,?)}");
            // Set parameters using proper methods
            st.setString(1, nv.getMaNV());
            st.setString(2, nv.getTenNV());
            st.setString(3, nv.getDiaChi());
            st.setString(4, nv.getGioiTinh());
            st.setString(5, nv.getSDT());
            st.setString(6, nv.getEmail());
            
           st.setString(7, trangThai);
            
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Sửa thành công !");
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Xóa khách hàng
    public void XoaNhanVien(DTO.NhanVienDTO m) {
        KetNoi();
        try {
            CallableStatement st = con.prepareCall("{call XoaNhanVien(?)}");
            st.setString(1, m.MaNV);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Xóa thành công !");
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Xóa khách hàng
    public void XoaNhanVienMat(DTO.NhanVienDTO m) {
        KetNoi();
        try {
            CallableStatement st = con.prepareCall("{call XoaMatNV(?)}");
            st.setString(1, m.MaNV);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Xóa thành công !");
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void SuaNhanVien(NhanVienDTO m) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
