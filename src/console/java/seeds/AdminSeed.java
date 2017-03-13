/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.java.seeds;

import console.java.models.DAO;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author DongHo
 */
public class AdminSeed {

     public static void main(String[] args) throws SQLException {
	  // Database: online_shop
	  Statement stt = DAO.getConnection().createStatement();
	  // Xóa dữ liệu cũ
	  stt.execute("TRUNCATE TABLE admins;");
	  // Thêm dữ liệu mẫu
	  stt.execute("INSERT INTO admins (name, email, pass) VALUES ('admin','admin@admin.com','admin');");
	  stt.execute("INSERT INTO admins (name, email, pass) VALUES ('guest','guest@admin.com','guest');");
	  stt.execute("INSERT INTO admins (name, email, pass) VALUES ('dong','dong@admin.com','1');");
	  stt.execute("INSERT INTO admins (name, email, pass) VALUES ('hung','hung@admin.com','1');");
	  stt.execute("INSERT INTO admins (name, email, pass) VALUES ('thang','thang@admin.com','1');");
	  stt.execute("INSERT INTO admins (name, email, pass) VALUES ('truong','truong@admin.com','1');");
	  stt.execute("INSERT INTO admins (name, email, pass) VALUES ('quang','quang@admin.com','1');");
	  System.out.println("------ Chèn dữ liệu mẫu thành công! ------");
	  // Đăng nhập với tài khoản: admin/admin hoặc guest/guest
     }
}
