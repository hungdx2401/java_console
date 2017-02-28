/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.java.models;

import console.java.entities.Admin;
import java.sql.Statement;

/**
 *
 * @author Thang
 */
public class AdminsModel {
    public static void update(Admin admin){
        try {
            String updateQuery = "UPDATE admin SET name='%s'"
                    + ",email='%s',pass='%s',updated_at=NOW() "
                    + "WHERE id =" + admin.getId();
            String update = String.format(updateQuery
                    ,admin.getName(),
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
}
