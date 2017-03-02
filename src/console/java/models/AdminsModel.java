/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.java.models;

import console.java.entities.Admin;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import console.java.utilities.ScannerUtilities;

/**
 *
 * @author Thang
 */
public class AdminsModel {

    public static void update(Admin admin) {
        try {
            String updateQuery = "UPDATE admin SET name='%s'"
                    + ",email='%s',pass='%s',updated_at=NOW() "
                    + "WHERE id =" + admin.getId();
            String update = String.format(updateQuery,
                    admin.getName(),
                    admin.getEmail(),
                    admin.getPassword());
            Statement stt = DAO.getConnection().createStatement();
            stt.execute(update);
            System.out.println("Update thanh cong !!!");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Da xay ra loi !!!");
        }
    }

    public static void insert() {
        String id = "";
        String name = "";
        String email = "";
        String pass = "";
        String status = "";

        System.out.println("Nhap Ho va Ten: ");
        name = ScannerUtilities.getString();
        System.out.println("Nhap Email: ");
        email = ScannerUtilities.getString();
        System.out.println("Nhap Mat Khau: ");
        pass = ScannerUtilities.getString();
        try {
            Statement statement = DAO.getConnection().createStatement();
            String sqlString = "INSERT INTO admin (name, email, pass) "
                    + "VALUES('" + name + "', '" + email + "', '" + pass + "')";
            statement.execute(sqlString);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Loi khi them Admin!");
        }
    }

    public static void getAllAdmin() {
        System.out.println("_________Danh Sach Admin_________");
        try {
            Statement statement = DAO.getConnection().createStatement();
            String sqlString = "SELECT * FROM admin";
            ResultSet rs = statement.executeQuery(sqlString);

            while (rs.next()) {
                System.out.println("-------------------------");
                System.out.println("----ID: " + rs.getString("id"));
                System.out.println("----Ho va Ten: " + rs.getString("name"));
                System.out.println("----Email: " + rs.getString("email"));
                System.out.println("----Mat Khau: " + rs.getString("pass"));
                System.out.println("----Tinh Trang: " + rs.getString("status"));
                System.out.println("----Ngay Tao: " + rs.getString("created_at"));
                System.out.println("----Ngay Sua: " + rs.getString("updated_at"));
                System.out.println("-------------------------");
            }
        } catch (Exception e) {
            System.out.println("Loi Hien Thi Admin!");
        }
    }
    
    public static void delete() {
        int id;

        System.out.println("Nhap ID Admin muon xoa: ");
        id = ScannerUtilities.getInt();
        try {
            Statement statement = DAO.getConnection().createStatement();
            String sqlString1 = "SELECT * FROM admin Where id = '" + id + "'";
            ResultSet rs = statement.executeQuery(sqlString1);
            if (rs.next() == false) {
                System.out.println("Khong co ID nhu tren!");
            } else {
                
                String choice = "";
                boolean loop = true;
           
                System.out.println("-------------------------");
                System.out.println("----ID: " + rs.getString("id"));
                System.out.println("----Ho va Ten: " + rs.getString("name"));
                System.out.println("----Email: " + rs.getString("email"));
                System.out.println("----Mat Khau: " + rs.getString("pass"));
                System.out.println("----Tinh Trang: " + rs.getString("status"));
                System.out.println("----Ngay Tao: " + rs.getString("created_at"));
                System.out.println("----Ngay Sua: " + rs.getString("updated_at"));
                System.out.println("-------------------------");
                
                while (true) {
                    System.out.println("------------------------------------------");
                    System.out.print("Ban muon tiep tuc khong? (yes/no): ");
                    choice = new Scanner(System.in).nextLine();
                    if (!"yYnN".contains(choice)) {
                        System.out.println("Ban hay nhap (Y/N)");
                    }
                    if (!"yY".contains(choice)) {
                        System.out.println("Ban da thoat!");
                    } else {
                        try {
                            String sqlString = "DELETE FROM admin Where id = " + id;
                            statement.execute(sqlString);
                            System.out.println("Xoa thanh cong Admin!");
                            break;
                        } catch (Exception e) {
                            System.out.println("Loi Them Admin!");
                        }
                    }

                }

            }
        } catch (Exception e) {
            System.out.println("Error!");
        }
    }
}