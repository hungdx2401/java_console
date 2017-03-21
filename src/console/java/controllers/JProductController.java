/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.java.controllers;

import console.java.entities.Product;
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
public class JProductController {
     
     

     /**
      * Hàm này load danh sách products vào bảng
      * @param table
      */
     public static void loadProducts(JTable table) {
	  DefaultTableModel model = (DefaultTableModel) table.getModel();
	  model.setRowCount(0);
	  List<Product> listProducts = JModel.getAllProduct();
	  listProducts.forEach((product) -> {
	       model.addRow(product.toArray());
	  });

     }

     public static int searchProduct(String keyword, int option, JTable table) {
	  List<Product> listProduct = new ArrayList<>();
	  Product product;
	  String column;
	  switch (option) {
	       case 1:
		    System.out.println("--- Tìm kiếm mã sản phẩm ---");
		    column = "barcode";
		    break;
	       case 2:
		    System.out.println("--- Tìm kiếm theo tên sản phẩm ---");
		    column = "name";
		    break;
	       default:
		    System.out.println("--- Tìm kiếm theo mô tả sản phẩm ---");
		    column = "description";
		    break;
	  }
	  String strQuery = "SELECT * FROM " + GlobalConfig.getPRODUCTS_TABLE() + " WHERE " + column + " LIKE '%"
		  + keyword + "%';";
	  ResultSet rs;
	  try {
	       rs = DAO.getConnection().createStatement().executeQuery(strQuery);
	       while (rs.next()) {
		    product = JUntilities.getProductFromResults(rs);
		    listProduct.add(product);
	       }
	       DefaultTableModel model = (DefaultTableModel) table.getModel();
	       model.setRowCount(0);
	       listProduct.forEach((pro1) -> {
		    model.addRow(pro1.toArray());
	       });
	  } catch (SQLException ex) {
	       System.err.println("Có lỗi xảy ra! " + ex);
	       JUntilities.alert("Lỗi: " + ex);
	       return -1;
	  }
	  return listProduct.size();
     }
}
