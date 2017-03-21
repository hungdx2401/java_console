/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.java.controllers;

import console.java.entities.Admin;
import console.java.models.DAO;
import console.java.models.GlobalConfig;
import console.java.models.JModel;
import console.java.utilities.JUntilities;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DongHo
 */
public class JAdminController {

     /**
      * Hàm này load danh sách Admin vào bảng
      *
      * @param table
      */
     public static void loadAdmins(JTable table) {
	  DefaultTableModel model = (DefaultTableModel) table.getModel();
	  model.setRowCount(0);
	  List<Admin> listAdmin = JModel.getAllAdmin();
	  listAdmin.forEach((admin) -> {
	       model.addRow(admin.toArray());
	  });
     }

     public static int searchAdmin(String keyword, int option, JTable table) {
	  List<Admin> listAdmin = new ArrayList<>();
	  Admin admin;
	  String column;
	  switch (option) {
	       case 1:
		    System.out.println("--- Tìm kiếm theo id admin ---");
		    column = "id";
		    break;
	       case 2:
		    System.out.println("--- Tìm kiếm theo tên admin ---");
		    column = "name";
		    break;
	       default:
		    System.out.println("--- Tìm kiếm theo email admin ---");
		    column = "email";
		    break;
	  }
	  String strQuery = "SELECT * FROM " + GlobalConfig.getADMINS_TABLE() + " WHERE " + column + " LIKE '%"
		  + keyword + "%';";
	  ResultSet rs;
	  try {
	       rs = DAO.getConnection().createStatement().executeQuery(strQuery);
	       while (rs.next()) {
		    admin = JUntilities.getAdminFromResults(rs);
		    listAdmin.add(admin);
	       }
	       DefaultTableModel model = (DefaultTableModel) table.getModel();
	       model.setRowCount(0);
	       listAdmin.forEach((admin1) -> {
		    model.addRow(admin1.toArray());
	       });
	  } catch (SQLException ex) {
	       System.err.println("Có lỗi xảy ra! " + ex);
	       JUntilities.alert("Lỗi: " + ex);
	       return -1;
	  }
	  return listAdmin.size();
     }
}
