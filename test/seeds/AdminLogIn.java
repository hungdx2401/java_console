/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seeds;

import console.java.models.DAO;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author daolinh
 */
public class AdminLogIn {
    public static void main(String[] args) throws SQLException {
        Statement stt = DAO.getConnection().createStatement();
        stt.execute("TRUNCATE TABLE admins");
        stt.execute("insert into admins (name,email,pass) values('thang','thang@gmail.com','12345')");
        stt.execute("insert into admins (name,email,pass) values('admin','admin@gmail.com','admin')");
        System.out.println("Them du lieu mau thanh cong!");
    }
}