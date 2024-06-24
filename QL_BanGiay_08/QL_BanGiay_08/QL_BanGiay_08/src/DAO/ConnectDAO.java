/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DoHuy
 */
public class ConnectDAO {
     Connection con;

    private void KetNoi() {
        String user = "sa";
        String pass = "123";
        String url = "jdbc:sqlserver://localhost:1433;" + "databaseName=QL_BAN_GIAY_09; trustServerCertificate = true";
        try {
            con = DriverManager.getConnection(url, user, pass);
        } catch (SQLException ex) {
//            Logger.getLogger(KhacHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
