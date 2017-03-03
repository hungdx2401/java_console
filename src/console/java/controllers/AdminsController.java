/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.java.controllers;

import console.java.entities.Admin;
import console.java.models.AdminsModel;
import console.java.models.DAO;
import static console.java.models.ProducstModel.update;
import console.java.utilities.ScannerUtilities;
import console.java.views.AdminViews;
import console.java.views.MainMenu;
import console.java.views.ProductsViews;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author Thang
 */
public class AdminsController {

    public static void searchAdmin() {

        ResultSet rs;
        Admin admin;
        int count = 0;
        boolean continueBoolean = true;

        while (continueBoolean) {
            AdminViews.searchOption();
            int option = ScannerUtilities.choiceInput(1, 2, 3);
            System.out.print("Nhap vao tu khoa muon tim kiem: ");
            String keywordStr = ScannerUtilities.getString();
            rs = AdminsModel.searchAdmin(keywordStr, option);
            try {
                while (rs.next()) {
                    admin = AdminsModel.getAdminFromResultSet(rs);
                    AdminViews.printAdmin(admin);
                    ++count;
                }
            } catch (SQLException ex) {
                System.err.println("Co loi xay ra! " + ex);
            }
            if (count == 0) {
                System.out.println("Khong tim thay san pham!");
            } else {
                System.out.println("Tim thay " + count + " Admin.");
            }
            continueBoolean = AdminViews.continueBoolean();
        }
    }

    public static void processInsert() {
        AdminsModel.insert();
        AdminsModel.getAllAdmin();
    }

    public static void processDelete() {
        AdminsModel.delete();
    }

    public static void processList() {
        AdminsModel.getAllAdmin();
    }

    public static boolean processUpdate() {
        String oldName = "";
        String oldEmail = "";
        String oldPassword = "";
        boolean continueBoolean = true;
        while (continueBoolean) {
            try {
                int count = 0;
                System.out.println("Nhap id admin : ");
                int id = ScannerUtilities.getInt();
                Statement stt = DAO.getConnection().createStatement();
                String sql = String.format("SELECT * "
                        + "FROM admin WHERE id = '%d'", id);
                ResultSet rs = stt.executeQuery(sql);
                //In ra thong tin san pham truoc khi sua
                while (rs.next()) {
                    System.out.println("Thong tin Admin hien tai la:");
                    oldName = rs.getString("name");
                    oldEmail = rs.getString("email");
                    oldPassword = rs.getString("pass");
                    System.out.println("Ten            :" + oldName);
                    System.out.println("Email          :" + oldEmail);
                    System.out.println("Password       :" + oldPassword);
                    ++count;
                }
                //Kiem tra xem co admin hay khong
                if (count == 0) {
                    System.err.println("Khong co admin !!!");
                    return false;
                }
                System.out.println("Moi ban nhap thong tin moi cho Admin hien tai:");
                //Nhap thong tin cua admin moi
                System.out.println("Nhap ten moi           :");
                String newName = ScannerUtilities.getString(8);
                System.out.println("Nhap email moi         :");
                String newEmail = ScannerUtilities.getString();
                System.out.println("Nhap password moi      :");
                String newPassword = ScannerUtilities.getString(5);
                //Gan gia tri cu neu de trong
                Admin admin = new Admin();
                if (newName.isEmpty()) {
                    newName = oldName;
                }
                if (newEmail.isEmpty()) {
                    newEmail = oldEmail;
                }
                if (newPassword.isEmpty()) {
                    newPassword = oldPassword;
                }
                admin.setId(id);
                admin.setName(newName);
                admin.setEmail(newEmail);
                admin.setPassword(newPassword);
                //Goi den model va hoi co muon tiep tuc khong
                AdminsModel.update(admin);
                continueBoolean = ProductsViews.continueBoolean();
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Khong the update !!!");
            }
        }
        return true;
    }

    public static void loginAdmin() {
        while (true) {
            System.out.println("Name Admin:");
            String name = ScannerUtilities.getString(1);
            System.out.println("Pass Admin:");
            String password = ScannerUtilities.getString(1);
            int login = AdminsModel.loginAdmin(name, password);
            if (login > 0) {
                break;
            }
        }
        MainMenu.mainMenu();
    }
}
