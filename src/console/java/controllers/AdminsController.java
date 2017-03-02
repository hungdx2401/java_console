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
            int option = ScannerUtilities.choiceInput(1,2,3);
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
        String name = "";
        String email = "";
        String password = "";
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
                name = rs.getString("name");
                email = rs.getString("email");
                password = rs.getString("pass");
                System.out.println("Ten            :" + name);
                System.out.println("Email          :" + email);
                System.out.println("Password       :" + password);
                ++count;
            }
            //Kiem tra xem co admin hay khong
            if (count == 0) {
                String choice = "";
                    System.out.println("------------------------------------------");
                    System.out.print("Ban muon them admin khong? (yes/no): ");
                    choice = new Scanner(System.in).nextLine();
                    if(!"yYnN".contains(choice)){
                        System.out.println("Ban hay nhap (Y/N)");
                    }
                    if (!"yY".contains(choice)) {
                        System.out.println("Ban da thoat!");
                    } else {
                        try {
                            AdminsModel.insert();
                            AdminsModel.getAllAdmin();
                        } catch (Exception e) {
                            System.out.println("Loi Them Admin!");
                        }
                    }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Khong the them !!!");
        }
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
                //Nhap thong tin cua admin moi
                System.out.println("Nhap ten moi           :");
                String newName = ScannerUtilities.getString();
                System.out.println("Nhap email moi         :");
                String newEmail = ScannerUtilities.getString();
                System.out.println("Nhap password moi      :");
                String newPassword = ScannerUtilities.getString();
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
            String name = ScannerUtilities.getString();
            System.out.println("Pass Admin:");
            String password = ScannerUtilities.getString();
            int login = AdminsModel.loginAdmin(name, password);
            if (login >0 ) {
                break;
            }
        }
        MainMenu.mainMenu();
    }
}
