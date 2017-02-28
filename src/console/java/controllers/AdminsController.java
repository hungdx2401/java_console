/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.java.controllers;

import console.java.entity.Admin;
import console.java.model.AdminsModel;
import console.java.model.DAO;
import static console.java.model.ProductModels.update;
import console.java.utilities.ScannerUtilities;
import console.java.views.ProductViews;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Thang
 */
public class AdminsController {
    public static boolean processUpdate(){
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
                String sql = String.format("SELECT * FROM admin WHERE id = '%d'", id);
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
                //Nhap thong tin moi cua admin
                System.out.println("Chu y : Neu ban khong muon sua ,hay de trong va tiep tuc !!!");
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
                admin.setName(newName);
                admin.setEmail(newEmail);
                admin.setPassword(newPassword);
                //Goi den model va hoi co muon tiep tuc khong
                AdminsModel.update(admin);
                continueBoolean = ProductViews.continueBoolean();
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Khong the update !!!");
            }
        }
        return true;
    }
}
