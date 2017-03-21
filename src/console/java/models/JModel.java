/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.java.models;

import console.java.entities.SessionAdmin;
import console.java.entities.Admin;
import console.java.entities.Product;
import console.java.utilities.JUntilities;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DongHo
 */
public class JModel {

     /**
      * Hàm này để đăng nhập vào chương trình.
      *
      * @param email
      * @param password
      * @return
      */
     public static int loginAdmin(String email, String password) {
	  int count = 0;
	  try {
	       String checklogin = String.format("SELECT * FROM " + GlobalConfig.getADMINS_TABLE() + " WHERE email = '%s' AND pass = '%s'", email, password);
	       ResultSet rs = DAO.getConnection().createStatement().executeQuery(checklogin);
	       while (rs.next()) {
		    ++count;
		    SessionAdmin.setId(rs.getInt("id"));
		    SessionAdmin.setName(rs.getString("name"));
		    SessionAdmin.setEmail(rs.getString("email"));
	       }
	       if (count > 0) {
		    System.out.println("Đăng nhập thành công.");
	       } else {
		    System.out.println("Thông tin không chính xác!");
		    return -1;
	       }
	  } catch (SQLException e) {
	       System.out.println("Loi kiem tra Admin");
	  }
	  return count;
     }

     /**
      * Hàm này lấy toàn bộ danh sách Admin trả về một List các Admin
      *
      * @return
      */
     public static List<Admin> getAllAdmin() {
	  List<Admin> adminList = new ArrayList<>();
	  ResultSet rs;
	  int total;
	  try {
	       rs = DAO.getConnection().createStatement().executeQuery("select count(*) from " + GlobalConfig.getADMINS_TABLE());
	       rs.next();
	       total = rs.getInt(1);
	  } catch (SQLException ex) {
	       System.err.println("Có lỗi! " + ex);
	       JUntilities.alert("Có lỗi! " + ex);
	       return adminList;
	  }
	  int perPage = GlobalConfig.getNUMBER_ADMINS_PER_PAGE();
	  int totalPages;
	  int offset;
	  String strQuery;
	  int pageNumber = 1;
	  Admin admin;
	  if (total == 0) {
	       System.out.println("Không có dữ liệu!");
	       JUntilities.alert("Không có dữ liệu!");
	  } else {
	       if (total % perPage == 0) {
		    totalPages = total / perPage;
	       } else {
		    totalPages = total / perPage + 1;
	       }
	       offset = (pageNumber - 1) * perPage;

	       String Query = String.format("SELECT * FROM " + GlobalConfig.getADMINS_TABLE() + " LIMIT %s OFFSET %s;", perPage, offset);
	       ResultSet results;
	       try {
		    results = DAO.getConnection().createStatement().executeQuery(Query);
		    while (results.next()) {
			 admin = JUntilities.getAdminFromResults(results);
			 adminList.add(admin);
		    }
		    results.first();
	       } catch (SQLException ex) {
		    System.err.println("Có lỗi xảy ra! " + ex);
		    JUntilities.alert("Có lỗi xảy ra! " + ex);
	       }
	  }
	  return adminList;
     }

     /**
      * Hàm này lấy tất cả danh sách sản phẩm trong database và trả về một List
      * các sản phẩm.
      *
      * @return
      */
     public static List<Product> getAllProduct() {
	  List<Product> productList = new ArrayList<>();
	  ResultSet rs;
	  int total;
	  try {
	       rs = DAO.getConnection().createStatement().executeQuery("select count(*) from " + GlobalConfig.getPRODUCTS_TABLE());
	       rs.next();
	       total = rs.getInt(1);
	  } catch (SQLException ex) {
	       System.err.println("Có lỗi! " + ex);
	       JUntilities.alert("Có lỗi! " + ex);
	       return productList;
	  }
	  int perPage = GlobalConfig.getNUMBER_ADMINS_PER_PAGE();
	  int totalPages;
	  int offset;
	  String strQuery;
	  int pageNumber = 1;
	  Product product;
	  if (total == 0) {
	       System.out.println("Không có dữ liệu!");
	       JUntilities.alert("Không có dữ liệu!");
	  } else {
	       if (total % perPage == 0) {
		    totalPages = total / perPage;
	       } else {
		    totalPages = total / perPage + 1;
	       }
	       offset = (pageNumber - 1) * perPage;

	       String Query = String.format("SELECT * FROM " + GlobalConfig.getPRODUCTS_TABLE() + " LIMIT %s OFFSET %s;", perPage, offset);
	       ResultSet results;
	       try {
		    results = DAO.getConnection().createStatement().executeQuery(Query);
		    while (results.next()) {
			 product = JUntilities.getProductFromResults(results);
			 productList.add(product);
		    }
		    results.first();
	       } catch (SQLException ex) {
		    System.err.println("Có lỗi xảy ra! " + ex);
		    JUntilities.alert("Có lỗi xảy ra! " + ex);
	       }
	  }
	  return productList;
     }

     /**
      * Model xịn, đa năng và chuyên dụng...
      *
      * @param <T>
      * @param obj
      * @throws SQLException
      */
     public static <T> void insert(T obj) throws SQLException {
	  StringBuilder columns = new StringBuilder();
	  StringBuilder values = new StringBuilder();
	  StringBuilder joins = new StringBuilder();
	  try {
	       Field[] fields = obj.getClass().getDeclaredFields();
	       String pre1 = "";
	       String pre2 = "'";
	       for (Field f : fields) {
		    f.setAccessible(true);
		    if (f.getName().equals("id")) {
			 continue;
		    }
		    columns.append(pre1).append(f.getName());
		    joins.append(pre1).append(f.getName()).append(" = '").append(f.get(obj)).append("'");
		    if (f.getType().toString().equals("boolean")) {
			 if (f.get(obj).toString().equals("true")) {
			      values.append(pre2).append("1");
			 } else {
			      values.append(pre2).append("0");
			 }
		    } else {
			 values.append(pre2).append(f.get(obj));
		    }
		    pre1 = ", ";
		    pre2 = "', '";
	       }
	  } catch (IllegalAccessException | IllegalArgumentException | SecurityException e) {
	       System.err.println("Lỗi: " + e);
	  }
	  values.append("'");
	  StringBuilder querry = new StringBuilder();
	  if ((obj instanceof Table) && (columns.length() != 0) && (values.length() != 0)) {
	       Table tb = (Table) obj;
	       String table = tb.getTable();
	       int id = SessionAdmin.getIdToAction();
	       String str = SessionAdmin.getStrToAction();
	       String unique = tb.getUnique();
	       if (id < 0) {
		    querry.append("INSERT INTO ").append(table).append(" (").append(columns).append(") VALUES(").append(values).append(");");
	       } else if (id == 0) {
		    querry.append("UPDATE ").append(table).append(" SET ").append(joins).append(" WHERE ").append(unique).append(" = '").append(str).append("';");
	       } else {
		    querry.append("UPDATE ").append(table).append(" SET ").append(joins).append(" WHERE ").append(unique).append(" = '").append(id).append("';");
	       }
	       Statement stt = DAO.getConnection().createStatement();
	       stt.execute(querry.toString());
	       System.out.println("Thao tác thành công!");
	       JUntilities.alert("Thao tác thành công!");
	  } else {
	       System.err.println("Lỗi!...");
	       JUntilities.alert("Lỗi!...");
	  }
     }

     /**
      * Model xịn xóa bất cứ một đối tượng theo id
      *
      * @param <T>
      * @param obj
      * @param id
      * @throws SQLException
      */
     public static <T> void delete(T obj, int id) throws SQLException {
	  StringBuilder querry = new StringBuilder();
	  if (obj instanceof Table) {
	       Table tb = (Table) obj;
	       String table = tb.getTable();
	       if (id > 0) {
		    querry.append("DELETE FROM ").append(table).append(" WHERE ").append(tb.getUnique()).append("=").append(id);
	       } else {
		    querry.append("DELETE FROM ").append(table).append(" WHERE ").append(tb.getUnique()).append("='").append(SessionAdmin.getStrToAction()).append("';");
	       }
	  } else {
	       System.err.println("Lỗi!...");
	       JUntilities.alert("Lỗi!...");
	  }
	  Statement stt = DAO.getConnection().createStatement();
	  stt.execute(querry.toString());
	  JUntilities.alert("Xóa thành công!");
     }

     /**
      * Hàm này kiểm tra xem có trùng mã sản phẩm hay ko.
      *
      * @param str
      * @return
      */
     public static boolean checkExistanceProductsBarcode(String str) {
	  try {
	       Statement statement = DAO.getConnection().createStatement();
	       String sqlString1 = "SELECT * FROM " + GlobalConfig.getPRODUCTS_TABLE() + " WHERE barcode = '" + str + "'";
	       ResultSet rs = statement.executeQuery(sqlString1);
	       if (rs.next() == true) {
		    System.out.println("Da ton tai!!!");
		    return false;
	       }
	  } catch (SQLException e) {
	       System.out.println("Da co loi :" + e);
	       JUntilities.alert("Da co loi :" + e);
	  }
	  return true;
     }

     /**
      * Hàm này kiểm tra email đã được sử dụng chưa.
      *
      * @param email
      * @return
      */
     public static boolean checkExistanceEmail(String email) {
	  try {
	       Statement statement = DAO.getConnection().createStatement();
	       String sqlString1 = "SELECT * FROM " + GlobalConfig.getADMINS_TABLE() + " WHERE email = '" + email + "'";
	       ResultSet rs = statement.executeQuery(sqlString1);
	       if (rs.next() == true) {
		    System.out.println("Da ton tai!!");
		    return true;
	       }
	  } catch (SQLException e) {
	       System.out.println("Da co loi :" + e);
	       JUntilities.alert("Da co loi :" + e);
	  }
	  return false;
     }

}
