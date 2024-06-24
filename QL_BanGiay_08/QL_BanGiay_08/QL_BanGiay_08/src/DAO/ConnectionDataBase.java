/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
/**
 *
 * @author ADMIN
 */
public class ConnectionDataBase {
    public static Connection conn = null;
    
    public void ConnectionDataBase(String severName, String dbName, String userName, String password,String port)
    {
        String url = "jdbc:sqlserver://"+severName+":"+port+";"+"databaseName="+dbName;
        try{
            conn=DriverManager.getConnection(url, userName, password);
            JOptionPane.showMessageDialog(null,"Kết nối thành công");
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Kết nối thất bại");
        } 
    }

}

